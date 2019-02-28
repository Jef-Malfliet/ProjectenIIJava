package domein;

import java.util.*;
import javax.persistence.*;

@Entity
public class Activiteit {

    @OneToMany()
    private List<Lid> aanwezigen;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Transient
    private Date startDatum;
    @Transient
    private Date eindDatum;
    @Transient
    private boolean stage;

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

}
