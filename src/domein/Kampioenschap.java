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
    private String[] gewichtcategorie;
    private String[] leeftijdscategorieën;
    private static Exportable<Kampioenschap> exportable;

    @Transient
    private SimpleStringProperty datumProperty = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty gewichtcategorieProperty = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty leeftijdscategorieProperty = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty naamProperty = new SimpleStringProperty();

    public Kampioenschap(String naam, LocalDate datum, String[] gewichtcategorie, String[] leeftijdscategorieën) {
        setNaam(naam);
        setDatum(datum);
        setGewichtcategorie(gewichtcategorie);
        setLeeftijdscategorieën(leeftijdscategorieën);
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
    public String[] getGewichtcategorie() {
        return gewichtcategorie;
    }

    public void setGewichtcategorie(String[] gewichtcategorie) {
        this.gewichtcategorie = gewichtcategorie;
    }

    @Override
    public SimpleStringProperty getDatumProperty() {
        return this.datumProperty;
    }

    public void setDatumProperty(SimpleStringProperty datumProperty) {
        this.datumProperty = datumProperty;
    }

    @Override
    public SimpleStringProperty getGewichtcategorieënProperty() {
        return this.gewichtcategorieProperty;
    }

    public void setGewichtcategorieënProperty(SimpleStringProperty gewichtProperty) {
        this.gewichtcategorieProperty = gewichtProperty;
    }

    @Override
    public SimpleStringProperty getLeeftijdcategorieënProperty() {
        return this.leeftijdscategorieProperty;
    }

    public void setLeeftijdcategorieënProperty(SimpleStringProperty leeftijdProperty) {
        this.leeftijdscategorieProperty = leeftijdProperty;
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
        this.setGewichtcategorieënProperty(new SimpleStringProperty(Arrays.stream(gewichtcategorie).collect(Collectors.joining(" / "))));
        this.setLeeftijdcategorieënProperty(new SimpleStringProperty(Arrays.stream(leeftijdscategorieën).collect(Collectors.joining(" / "))));
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

    public void setLeeftijdscategorieën(String[] leeftijdscategorieën) {
        this.leeftijdscategorieën = leeftijdscategorieën;
    }

    public String[] getLeeftijdscategorieën() {
        return leeftijdscategorieën;
    }

}
