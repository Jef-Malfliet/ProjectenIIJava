package domein;

import java.time.LocalDate;
import java.util.*;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

@Entity
@Access(AccessType.FIELD)
public class Kampioenschap implements IKampioenschap, Exportable<Kampioenschap> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToMany(cascade = CascadeType.REFRESH)
    private List<Lid> aanwezigen;
//    private LocalDate datum;
//    private String naam;
    private static Exportable<Kampioenschap> exportable;
    //private LeeftijdCategorie leeftijdCategorie;

    @Transient
    private SimpleStringProperty datumProperty = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty naamProperty = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty leeftijdCategorieProperty = new SimpleStringProperty();

    public Kampioenschap(String naam, LocalDate datum, LeeftijdCategorie categorie) {
        setName(naam);
        setDate(datum);
        setLeeftijdCategorie(categorie);
        aanwezigen = new ArrayList<>();
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
    public SimpleStringProperty getDatumProperty() {
        return this.datumProperty;
    }

    @Override
    @Access(AccessType.PROPERTY)
    @Column(name = "Datum", nullable = false)
    public LocalDate getDate() {
        return LocalDate.parse(datumProperty.get());
    }

    public void setDate(LocalDate datum) {
        this.datumProperty.set(datum.toString());
    }

    @Override
    public SimpleStringProperty getNaamProperty() {
        return naamProperty;
    }

    @Override
    @Access(AccessType.PROPERTY)
    @Column(name = "Naam", nullable = false, length = 50)
    public String getName() {
        return naamProperty.get();
    }

    public void setName(String naam) {
        this.naamProperty.set(naam);
    }

    @Override
    public SimpleStringProperty getLeeftijdCategorieProperty() {
        return leeftijdCategorieProperty;
    }

    @Override
    @Access(AccessType.PROPERTY)
    @Column(name = "Leeftijdcategorie", nullable = false)
    public LeeftijdCategorie getLeeftijdCategorie() {
        return LeeftijdCategorie.valueOf(leeftijdCategorieProperty.get());
    }

    public void setLeeftijdCategorie(LeeftijdCategorie categorie) {
        this.leeftijdCategorieProperty.set(categorie.toString());
    }

    public long getId() {
        return id;
    }
    
    

}
