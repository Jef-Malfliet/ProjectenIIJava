package domein;

import java.io.Serializable;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.Entity;
import javax.persistence.*;

/**
 *
 * @author Nante
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Lid.GetAll", query = "SELECT e FROM Lid e")
})
public class Lid implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String naam;

    @Enumerated(EnumType.STRING)
    private Graad graad;

    @Transient
    private final Type type = Type.LID;

    @Transient
    private SimpleStringProperty naamProperty = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty graadProperty = new SimpleStringProperty();

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
        fillSimpleProperties();
    }

    public String getNaam() {
        return naam;
    }

    public Graad getGraad() {

        return this.graad;
    }

    @Transient
    public Type getType() {
        return type;
    }

    @Id
    public Long getId() {
        return id;
    }

    //is deze wel nodig?
    public void setId(Long id) {
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

    public void wijzigLid(String naam, Graad graad) {
        setGraad(graad);
        setNaam(naam);
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
    
    public void fillSimpleProperties(){
        this.setGraadProperty(new SimpleStringProperty(this.getGraad().toString()));
        this.setNaamProperty(new SimpleStringProperty(this.getNaam()));
    }

    public SimpleStringProperty getNaamProperty() {
        return this.naamProperty;
    }

    /**
     *
     * @param naamProperty
     */
    public void setNaamProperty(SimpleStringProperty naamProperty) {
        this.naamProperty = naamProperty;
    }

    public SimpleStringProperty getGraadProperty() {
        return this.graadProperty;
    }

    /**
     *
     * @param attribute
     */
    public void setGraadProperty(SimpleStringProperty attribute) {
        this.graadProperty = attribute;
    }

}
