package domein;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Kampioenschap implements IKampioenschap, Exportable<Kampioenschap> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Lid> aanwezigen;
    private LocalDate datum;
    private String naam;
    private static Exportable<Kampioenschap> exportable;

    @Transient
    private SimpleStringProperty datumProperty = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty naamProperty = new SimpleStringProperty();

    public Kampioenschap(String naam, LocalDate datum) {
        setNaam(naam);
        setDatum(datum);
        aanwezigen = new ArrayList<>();
        fillSimpleProperties();
    }

    protected Kampioenschap() {
    }

    /**
     *
     * @param lid
     */
    public void lidInschrijven(Lid lid) {
        aanwezigen.add(lid);
    }

    @Override
    public List<Lid> geefAanwezigen() {
        return aanwezigen;
    }

    /**
     *
     * @param lid
     */
    public void lidUitschrijven(Lid lid) {
        aanwezigen.remove(lid);
    }

    public static Exportable<Kampioenschap> getExportable() {
        return exportable;
    }

    public static void setExportable(Exportable<Kampioenschap> exportable) {
        Kampioenschap.exportable = exportable;
    }

    @Override
    public String excelFormat(Kampioenschap object) {
        return exportable.excelFormat(this);
    }

    @Override
    public String excelheaders() {
        return exportable.excelheaders();
    }

    @Override
    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    @Override
    public SimpleStringProperty getDatumProperty() {
        return this.datumProperty;
    }

    public void setDatumProperty(SimpleStringProperty datumProperty) {
        this.datumProperty = datumProperty;
    }

    @Override
    public SimpleStringProperty getNaamProperty() {
        return naamProperty;
    }

    public void setNaamProperty(SimpleStringProperty naamProperty) {
        this.naamProperty = naamProperty;
    }

    public void fillSimpleProperties() {
        this.setDatumProperty(new SimpleStringProperty(datum.toString()));
        this.setNaamProperty(new SimpleStringProperty(naam));
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

}
