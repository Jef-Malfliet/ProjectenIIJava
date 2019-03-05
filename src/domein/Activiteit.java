package domein;

import java.io.Serializable;
import java.util.*;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.*;

@Entity
public class Activiteit implements Serializable, IActiviteit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Transient
    private String naam;
    @Transient
    private Date startDatum;
    @Transient
    private Date eindDatum;
    @Transient
    private boolean stage;

    @OneToMany()
    private List<Lid> aanwezigen;

    @Transient
    private SimpleStringProperty naamProperty = new SimpleStringProperty();

    @Transient
    private SimpleStringProperty startDatumProperty = new SimpleStringProperty();

    @Transient
    private SimpleStringProperty eindDatumProperty = new SimpleStringProperty();

    @Transient
    private SimpleStringProperty stageProperty = new SimpleStringProperty();

    public Activiteit() {
    }

    public Activiteit(String naam, Date startDatum, Date eindDatum, boolean stage) {
        this.naam = naam;
        this.startDatum = startDatum;
        this.eindDatum = eindDatum;
        this.stage = stage;
    }

    public List<Lid> getAanwezigen() {
        return aanwezigen;
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
    public Date getStartDatum() {
        return startDatum;
    }

    private void setStartDatum(Date startDatum) {
        this.startDatum = startDatum;
    }

    @Override
    public Date getEindDatum() {
        return eindDatum;
    }

    private void setEindDatum(Date eindDatum) {
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

    public SimpleStringProperty getNaamProperty() {
        return naamProperty;
    }

    public void setNaamProperty(SimpleStringProperty naamProperty) {
        this.naamProperty = naamProperty;
    }

    public SimpleStringProperty getStartDatumProperty() {
        return startDatumProperty;
    }

    public void setStartDatumProperty(SimpleStringProperty startDatumProperty) {
        this.startDatumProperty = startDatumProperty;
    }

    public SimpleStringProperty getEindDatumProperty() {
        return eindDatumProperty;
    }

    public void setEindDatumProperty(SimpleStringProperty eindDatumProperty) {
        this.eindDatumProperty = eindDatumProperty;
    }

    public SimpleStringProperty getStageProperty() {
        return stageProperty;
    }

    public void setStageProperty(SimpleStringProperty stageProperty) {
        this.stageProperty = stageProperty;
    }

    public void updateActiviteit(String naam, Date beginDatum, Date eindDatum, boolean stage) {
        setNaam(naam);
        setStartDatum(startDatum);
        setEindDatum(eindDatum);
        setStage(stage);
    }

    public void fillSimpleProperties() {
        this.setNaamProperty(new SimpleStringProperty(this.getNaam()));
        this.setStartDatumProperty(new SimpleStringProperty(this.getStartDatum().toString()));
        this.setEindDatumProperty(new SimpleStringProperty(this.getEindDatum().toString()));
        this.setStageProperty(new SimpleStringProperty(this.isStage() ? "stage" : ""));
    }
}
