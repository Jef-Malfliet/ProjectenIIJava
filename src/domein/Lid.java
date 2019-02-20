package domein;

import java.io.Serializable;
import java.util.Objects;
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
        setNaam(naam);
        setGraad(graad);
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

    //is deze wel nodig?
    private void setId(Long id) {
        this.id = id;
    }

    private void setNaam(String naam) {
        if (naam == null || naam.isEmpty()) {
            throw new IllegalArgumentException("Naam mag niet leeg zijn");
        }
        this.naam = naam;
    }

    private void setGraad(Graad graad) {
        this.graad = graad;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lid other = (Lid) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
