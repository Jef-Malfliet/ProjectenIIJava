package domein;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
    @NamedQuery(name = "Lid.GetAll", query = "SELECT e FROM Lid e"),
    @NamedQuery(name = "Lid.GetLedenByVoornaam", query = "SELECT e FROM Lid e WHERE e.voornaam = :lidVoornaam")
})
public class Lid implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String voornaam;

    @Enumerated(EnumType.STRING)
    private Graad graad;

    @Transient
    private final Type type = Type.LID;

    @Transient
    private SimpleStringProperty voornaamProperty = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty graadProperty = new SimpleStringProperty();
    @Transient
    private PropertyChangeSupport subject;
    
    
    public Lid() {
    }

    /**
     *
     * @param voornaam
     * @param graad
     */
    public Lid(String voornaam, Graad graad) {
        setVoornaam(voornaam);
        setGraad(graad);
        subject = new PropertyChangeSupport(this);
        fillSimpleProperties();
    }
    
    
    public String getVoornaam() {
        return voornaam;
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

    private void setVoornaam(String voornaam) {
        if (voornaam == null || voornaam.isEmpty()) {
            throw new IllegalArgumentException("Voornaam mag niet leeg zijn");
        }
        this.voornaam = voornaam;
    }

    private void setGraad(Graad graad) {
        this.graad = graad;
    }

    public void wijzigLid(String voornaam, Graad graad) {
        setGraad(graad);
        setVoornaam(voornaam);
        subject.firePropertyChange("lid",this,this);
        //geeft nog nullpointer :( 
        
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
        this.setVoornaamProperty(new SimpleStringProperty(this.getVoornaam()));
    }

    public SimpleStringProperty getVoornaamProperty() {
        return this.voornaamProperty;
    }

    /**
     *
     * @param naamProperty
     */
    public void setVoornaamProperty(SimpleStringProperty voornaamProperty) {
        this.voornaamProperty = voornaamProperty;
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
    
    public void addPropertyChangeListener(PropertyChangeListener pc1){
        subject.addPropertyChangeListener(pc1);
        pc1.propertyChange(new PropertyChangeEvent(pc1,"lid",null,this));
        
    }
     public void removePropertyChangeListener(PropertyChangeListener pc1){
        subject.removePropertyChangeListener(pc1);
       
        
    }

}
