package domein;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Kampioenschap implements IKampioenschap, Exportable<Kampioenschap> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToMany()
    private List<Lid> aanwezigen;
    @Temporal(TemporalType.DATE)
    private LocalDate datum;
    private int[] gewichtcategorie;
    private static Exportable<Kampioenschap> exportable;

    @Transient
    private SimpleStringProperty datumProperty = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty gewichtcategorieProperty = new SimpleStringProperty();

    /**
     *
     * @param datum
     * @param gewichtcategorie
     */
    public Kampioenschap(LocalDate datum, int[] gewichtcategorie) {
        this.datum = datum;
        this.gewichtcategorie = gewichtcategorie;
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
    public int[] getGewichtcategorie() {
        return gewichtcategorie;
    }

    public void setGewichtcategorie(int[] gewichtcategorie) {
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

    public void fillSimpleProperties() {                        
        this.setDatumProperty(new SimpleStringProperty(datum.toString()));
        this.setGewichtcategorieënProperty(new SimpleStringProperty(Arrays.toString(gewichtcategorie)));
    }

}
