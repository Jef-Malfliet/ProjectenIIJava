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

    private String naam;

    private LocalDate beginDatum;

    private LocalDate eindDatum;

    private boolean stage;

    private int maxAanwezigen;

    @ManyToMany(cascade = CascadeType.REFRESH)
    private ObservableList<Lid> aanwezigen;

    @Transient
    private SimpleStringProperty naamProperty = new SimpleStringProperty();

    @Transient
    private SimpleStringProperty beginDatumProperty = new SimpleStringProperty();

    @Transient
    private SimpleStringProperty eindDatumProperty = new SimpleStringProperty();

    @Transient
    private SimpleStringProperty stageProperty = new SimpleStringProperty();

    @Transient
    private SimpleStringProperty maxAanwezigenProperty = new SimpleStringProperty();

    private static Exportable<Activiteit> exportable;

    public Activiteit() {
    }

    public Activiteit(IActiviteit act) {
        this(act.getNaam(), act.getBeginDatum(), act.getEindDatum(), act.isStage(), act.getMaxAanwezigen());
    }

    public Activiteit(String naam, LocalDate beginDatum, LocalDate eindDatum, boolean stage, int maxAanwezigen) {
        setNaam(naam);
        setBeginDatum(beginDatum);
        this.eindDatum = eindDatum;
        this.stage = stage;
        this.maxAanwezigen = maxAanwezigen;
        this.aanwezigen = FXCollections.observableArrayList();
    }

    @Access(AccessType.PROPERTY)
    public List<Lid> getAanwezigen() {
        return aanwezigen;
    }
   
    public void setAanwezigen(List<Lid> aanwezigen) {
        this.aanwezigen = FXCollections.observableArrayList(aanwezigen);
    }

    
    /**
     *
     * @param lid
     */
    public void lidInschrijven(Lid lid) {
        aanwezigen.add(lid);
    }

    /**
     *
     * @param lid
     */
    public void lidUitschrijven(Lid lid) {
        aanwezigen.remove(lid);
    }

    @Override
    public LocalDate getBeginDatum() {
        return beginDatum;
    }

    private void setBeginDatum(LocalDate startDatum) {
        this.beginDatum = startDatum;
    }

    @Override
    public LocalDate getEindDatum() {
        return eindDatum;
    }

    private void setEindDatum(LocalDate eindDatum) {
        this.eindDatum = eindDatum;
    }

    @Override
    public boolean isStage() {
        return stage;
    }

    private void setStage(boolean stage) {
        this.stage = stage;
    }

    private void setNaam(String naam) {
        this.naam = naam;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getNaam() {
        return naam;
    }

    @Override
    public SimpleStringProperty getNaamProperty() {
        return naamProperty;
    }

    public void setNaamProperty(SimpleStringProperty naamProperty) {
        this.naamProperty = naamProperty;
    }

    @Override
    public SimpleStringProperty getBeginDatumProperty() {
        return beginDatumProperty;
    }

    public void setBeginDatumProperty(SimpleStringProperty startDatumProperty) {
        this.beginDatumProperty = startDatumProperty;
    }

    @Override
    public SimpleStringProperty getEindDatumProperty() {
        return eindDatumProperty;
    }

    public void setEindDatumProperty(SimpleStringProperty eindDatumProperty) {
        this.eindDatumProperty = eindDatumProperty;
    }

    @Override
    public SimpleStringProperty getStageProperty() {
        return stageProperty;
    }

    public void setStageProperty(SimpleStringProperty stageProperty) {
        this.stageProperty = stageProperty;
    }

    @Override
    public int getMaxAanwezigen() {
        return maxAanwezigen;
    }

    public void setMaxAanwezigen(int maxAanwezigen) {
        this.maxAanwezigen = maxAanwezigen;
    }

    public void fillSimpleProperties() {
        this.setNaamProperty(new SimpleStringProperty(this.getNaam()));
        this.setBeginDatumProperty(new SimpleStringProperty(this.getBeginDatum().toString()));
        this.setEindDatumProperty(new SimpleStringProperty(this.getEindDatum().toString()));
        this.setStageProperty(new SimpleStringProperty(this.isStage() ? "stage" : "geen stage"));
        this.setMaxAanwezigenProperty(new SimpleStringProperty(String.format("%d", this.maxAanwezigen)));
    }

    @Override
    public SimpleStringProperty getMaxAanwezigenProperty() {
        return maxAanwezigenProperty;
    }

    public void setMaxAanwezigenProperty(SimpleStringProperty maxAanwezigenProperty) {
        this.maxAanwezigenProperty = maxAanwezigenProperty;
    }

    public final void wijzigActiviteit(String naam, LocalDate beginDatum, LocalDate eindDatum, boolean stage, int maxAanwezigen) {
        setNaam(naam);
        setBeginDatum(eindDatum);
        setEindDatum(eindDatum);
        setStage(stage);
        setMaxAanwezigen(maxAanwezigen);
        fillSimpleProperties();
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

}
