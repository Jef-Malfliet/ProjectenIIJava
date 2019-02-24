/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Mout
 */
@Entity
public class Overzicht implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;//db
    private OverzichtType type;//db
    @Temporal(TemporalType.DATE)
    private Date datum;//db

    @Transient
    private final SimpleStringProperty overzichtNaamProperty = new SimpleStringProperty();
    @Transient
    private final SimpleStringProperty typeProperty = new SimpleStringProperty();
    @Transient
    private final SimpleStringProperty datumProperty = new SimpleStringProperty();

    protected Overzicht() {
    }

    public Overzicht(String name, OverzichtType type) {
        setOverzichtNaamProperty(name);
        setTypeProperty(type);
        setDatumProperty(new Date());//huidige datum
        this.name = getOverzichtNaam();
        this.type = type;
        this.datum = new Date();
    }

    private void setOverzichtNaamProperty(String overzichtNaam) {
        overzichtNaamProperty.set(overzichtNaam);
    }

    public String getOverzichtNaam() {
        return overzichtNaamProperty.get();
    }

    public StringProperty getOverzichtNaamProperty() {
        return overzichtNaamProperty;
    }

    private void setTypeProperty(OverzichtType type) {
        typeProperty.set(type.name());
    }

    public String getType() {
        return typeProperty.get();
    }

    public StringProperty getTypeProperty() {
        return typeProperty;
    }

    private void setDatumProperty(Date datum) {
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        String strDate = dateFormat.format(datum);
        datumProperty.set(strDate);
    }

    public String getDatum() {
        return datumProperty.get();
    }

    public StringProperty getDatumProperty() {
        return datumProperty;
    }
}
