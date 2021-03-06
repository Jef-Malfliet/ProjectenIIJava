/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Jef
 */
public class DTOActiviteit implements IActiviteit {

    private long id;

    private String naam;
    private LocalDate beginDatum;
    private LocalDate eindDatum;
    private int maxAanwezigen;
    private ActiviteitType actvititeitType;
    private SimpleStringProperty naamProperty = new SimpleStringProperty();
    private SimpleStringProperty beginDatumProperty = new SimpleStringProperty();
    private SimpleStringProperty eindDatumProperty = new SimpleStringProperty();
    private SimpleStringProperty maxAanwezigenProperty = new SimpleStringProperty();
    private SimpleStringProperty actvititeitTypeProperty = new SimpleStringProperty();

    public DTOActiviteit(String naam, LocalDate beginDatum, LocalDate eindDatum, int maxAanwezigen, ActiviteitType type) {
        setNaam(naam);
        setBeginDatum(beginDatum);
        setEindDatum(eindDatum);
        setMaxAanwezigen(maxAanwezigen);
        setActiviteitType(type);
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    @Override
    public LocalDate getBeginDatum() {
        return beginDatum;
    }

    public void setBeginDatum(LocalDate beginDatum) {
        this.beginDatum = beginDatum;
    }

    @Override
    public LocalDate getEindDatum() {
        return eindDatum;
    }

    public void setEindDatum(LocalDate eindDatum) {
        this.eindDatum = eindDatum;
    }
    
    @Override
    public int getMaxAanwezigen() {
        return maxAanwezigen;
    }

    public void setMaxAanwezigen(int maxAanwezigen) {
        this.maxAanwezigen = maxAanwezigen;
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

    public void setBeginDatumProperty(SimpleStringProperty beginDatumProperty) {
        this.beginDatumProperty = beginDatumProperty;
    }

    @Override
    public SimpleStringProperty getEindDatumProperty() {
        return eindDatumProperty;
    }

    public void setEindDatumProperty(SimpleStringProperty eindDatumProperty) {
        this.eindDatumProperty = eindDatumProperty;
    }

    @Override
    public SimpleStringProperty getMaxAanwezigenProperty() {
        return maxAanwezigenProperty;
    }

    public void setMaxAanwezigenProperty(SimpleStringProperty maxAanwezigenProperty) {
        this.maxAanwezigenProperty = maxAanwezigenProperty;
    }

    @Override
    public ActiviteitType getActiviteitType() {
       return actvititeitType;
    }

    public void setActiviteitType(ActiviteitType type) {
        this.actvititeitType = type;
    }

    @Override
    public SimpleStringProperty getActiviteitTypeProperty() {
        return actvititeitTypeProperty;
    }

    public void setActiviteitTypeProperty(SimpleStringProperty typeProperty) {
        this.actvititeitTypeProperty = typeProperty;
    }
    
}