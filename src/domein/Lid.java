package domein;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.*;

/**
 *
 * @author Nante
 */
@Entity
public class Lid implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String naam;

    @Column(name = "graad")
    private Graad graad;

    @Transient
    private final Type type = Type.LID;

    public Lid() {
    }

    /**
     *
     * @param naam
     * @param graad
     */
    public Lid(String naam, Graad graad) {
        this.naam = naam;
        this.graad = graad;
    }

    public String getNaam() {
        return naam;
    }

    public Graad getGraad() {
        return graad;
    }

    public Type getType() {
        return type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
