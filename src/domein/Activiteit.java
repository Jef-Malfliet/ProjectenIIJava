package domein;

import java.util.*;
import javax.persistence.*;

@Entity
public class Activiteit {

    @OneToMany()
    private List<Lid> aanwezigen;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Transient
    private Date startDatum;
    @Transient
    private Date eindDatum;
    @Transient
    private boolean stage;

    public Activiteit() {
    }

    public Activiteit(Date startDatum, Date eindDatum, boolean stage) {
        this.startDatum = startDatum;
        this.eindDatum = eindDatum;
        this.stage = stage;
    }

    public List<Lid> geefAanwezigen() {
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

    public void setAanwezigen(List<Lid> aanwezigen) {
        this.aanwezigen = aanwezigen;
    }

    public Date getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(Date startDatum) {
        this.startDatum = startDatum;
    }

    public Date getEindDatum() {
        return eindDatum;
    }

    public void setEindDatum(Date eindDatum) {
        this.eindDatum = eindDatum;
    }

    public boolean isStage() {
        return stage;
    }

    public void setStage(boolean stage) {
        this.stage = stage;
    }

    public long getId() {
        return id;
    }
}
