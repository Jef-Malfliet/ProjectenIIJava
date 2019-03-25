package domein;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)
public class Activiteit implements Serializable, IActiviteit, Exportable<Activiteit> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private ObservableList<Lid> aanwezigen;

    @Transient
    private SimpleStringProperty naamProperty = new SimpleStringProperty();

    @Transient
    private SimpleStringProperty beginDatumProperty = new SimpleStringProperty();

    @Transient
    private SimpleStringProperty eindDatumProperty = new SimpleStringProperty();

    @Transient
    private SimpleStringProperty maxAanwezigenProperty = new SimpleStringProperty();

    @Transient
    private SimpleStringProperty activiteitTypeProperty = new SimpleStringProperty();

    private static Exportable<Activiteit> exportable;

    public Activiteit() {
    }

    public Activiteit(IActiviteit act) {
        this(act.getNaam(), act.getBeginDatum(), act.getEindDatum(), act.getMaxAanwezigen(), act.getActiviteitType());
    }

    public Activiteit(String naam, LocalDate beginDatum, LocalDate eindDatum, int maxAanwezigen, ActiviteitType type) {
        setNaam(naam);
        setBeginDatum(beginDatum);
        setEindDatum(eindDatum);
        setMaxAanwezigen(maxAanwezigen);
        this.aanwezigen = FXCollections.observableArrayList();
        setActiviteitType(type);
    }

    @Access(AccessType.PROPERTY)
    @ManyToMany(cascade = CascadeType.REFRESH)

    public List<Lid> getAanwezigen() {
        return aanwezigen;
    }
    
    //Alleen zodat JPA werkt, nooit gebruiken.
    public void setAanwezigen(List<Lid> aanwezigen){
        this.aanwezigen= FXCollections.observableArrayList(aanwezigen);
    }

    /**
     *
     * @param lid
     */
    public void lidInschrijven(Lid lid) {
        if (lid == null) {
            throw new IllegalArgumentException("Lid mag niet null zijn");
        }
        aanwezigen.add(lid);
    }

    /**
     *
     * @param lid
     */
    public void lidUitschrijven(Lid lid) {
        if (lid == null) {
            throw new IllegalArgumentException("Lid mag niet null zijn");
        }
        if(!aanwezigen.contains(lid)){
            throw new IllegalArgumentException("Lid staat nog niet op aanwezig");
        }
        aanwezigen.remove(lid);
    }

    @Override
    @Access(AccessType.PROPERTY)
    @Column(name = "BeginDatum", nullable = false)
    public LocalDate getBeginDatum() {
        return LocalDate.parse(beginDatumProperty.get());
    }

    private void setBeginDatum(LocalDate beginDatum) {
        if (beginDatum == null) {
            throw new IllegalArgumentException("Begindatum mag niet null zijn");
        }
        
        beginDatumProperty.set(beginDatum.toString());
    }

    @Override
    @Access(AccessType.PROPERTY)
    @Column(name = "EindDatum", nullable = false)
    public LocalDate getEindDatum() {
        return LocalDate.parse(eindDatumProperty.get());
    }

    private void setEindDatum(LocalDate eindDatum) {
        if (eindDatum == null) {
            throw new IllegalArgumentException("Einddatum mag niet null zijn");
        }
        if (eindDatum.isBefore(this.getBeginDatum())) {
            throw new IllegalArgumentException("Einddatum mag niet voor begindatum liggen");
        }
        eindDatumProperty.set(eindDatum.toString());
    }

    private void setNaam(String naam) {
        if (naam == null || naam.trim().equals("")) {
            throw new IllegalArgumentException("Naam mag niet leeg of null zijn");
        }
        naamProperty.set(naam);
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public SimpleStringProperty getNaamProperty() {
        return naamProperty;
    }

    @Override
    public SimpleStringProperty getBeginDatumProperty() {
        return beginDatumProperty;
    }

    @Override
    public SimpleStringProperty getEindDatumProperty() {
        return eindDatumProperty;
    }

    public void setMaxAanwezigen(int maxAanwezigen) {
        if (maxAanwezigen <= 0) {
            throw new IllegalArgumentException("Maximun aantal aanwezigen mag niet kleiner of gelijk zijn aan 0");
        }
        maxAanwezigenProperty.set(String.format("%d", maxAanwezigen));
    }

    @Override
    public SimpleStringProperty getMaxAanwezigenProperty() {
        return maxAanwezigenProperty;
    }

    @Override
    public javafx.beans.property.SimpleStringProperty getActiviteitTypeProperty() {
        return activiteitTypeProperty;
    }

    private void setActiviteitType(ActiviteitType type) {
        if (type.equals(ActiviteitType.ALLES)) {
            throw new IllegalArgumentException("Gelieve een geldig type in te geven");
        }
        activiteitTypeProperty.set(type.toString());
    }

    @Override
    @Access(AccessType.PROPERTY)
    @Column(name = "Naam", nullable = false, length = 50)
    public String getNaam() {
        return naamProperty.get();
    }

    public final void wijzigActiviteit(String naam, LocalDate beginDatum, LocalDate eindDatum, int maxAanwezigen, ActiviteitType type) {
        setNaam(naam);
        setBeginDatum(eindDatum);
        setEindDatum(eindDatum);
        setMaxAanwezigen(maxAanwezigen);
        setActiviteitType(type);
    }

    public static Exportable<Activiteit> getExportable() {
        return exportable;
    }

    public static void setExportable(Exportable<Activiteit> exportable) {
        Activiteit.exportable = exportable;
    }

    @Override
    public String excelFormat(Activiteit object) {
        return exportable.excelFormat(this);
    }

    @Override
    public String excelheaders() {
        return exportable.excelheaders();
    }

    @Override
    @Access(AccessType.PROPERTY)
    @Column(name = "MaxAanwezigen", nullable = false)
    public int getMaxAanwezigen() {
        return Integer.parseInt(maxAanwezigenProperty.get());
    }

    @Override
    @Access(AccessType.PROPERTY)
    @Column(name = "ActiviteitType", nullable = false)
    public ActiviteitType getActiviteitType() {
        return ActiviteitType.valueOf(activiteitTypeProperty.get());
    }

}
