package domein;

import java.io.Serializable;
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
    private String achternaam;
    private String telefoon;
    private String email;
    private String straat;
    private int postcode;
    private String gemeente;

    @Enumerated(EnumType.STRING)
    private Graad graad;

    @Transient
    private final Type type = Type.LID;

    @Transient
    private SimpleStringProperty voornaamProperty = new SimpleStringProperty();

    @Transient
    private SimpleStringProperty graadProperty = new SimpleStringProperty();

    public Lid() {
    }

    public Lid(String voornaam, String achternaam, Graad graad, String telefoon, String email, String straat, int postcode, String gemeente) {
        setVoornaam(voornaam);
        setAchternaam(achternaam);
        setGraad(graad);
        setTelefoon(telefoon);
        setEmail(email);
        setStraat(straat);
        setPostcode(postcode);
        setGemeente(gemeente);
        fillSimpleProperties();
    }

    public void wijzigLid(String voornaam, Graad graad) {
        setGraad(graad);
        setVoornaam(voornaam);
        fillSimpleProperties();

    }

    public void fillSimpleProperties() {
        this.setGraadProperty(new SimpleStringProperty(this.getGraad().toString()));
        this.setVoornaamProperty(new SimpleStringProperty(this.getVoornaam()));
    }

    @Override
    public String toString() {
        return String.format("%s %s met graad %s%nTel.: %s%nE-mail adres: %s%nAdres: %s %d in %s", voornaam,achternaam,graad.toString(),telefoon,email,straat,postcode,gemeente);
    }

    public SimpleStringProperty getVoornaamProperty() {
        return this.voornaamProperty;
    }

    public void setVoornaamProperty(SimpleStringProperty voornaamProperty) {
        this.voornaamProperty = voornaamProperty;
    }

    public SimpleStringProperty getGraadProperty() {
        return this.graadProperty;
    }

    public void setGraadProperty(SimpleStringProperty graadProperty) {
        this.graadProperty = graadProperty;
    }

    public Type getType() {
        return type;
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

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        if (voornaam == null || voornaam.isEmpty()) {
            throw new IllegalArgumentException("Voornaam mag niet leeg zijn.");
        }
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        if (achternaam == null || achternaam.isEmpty()) {
            throw new IllegalArgumentException("Achternaam mag niet leeg zijn.");
        }
        this.achternaam = achternaam;
    }

    public String getTelefoon() {
        return telefoon;
    }

    public void setTelefoon(String telefoon) {
        if (telefoon == null || telefoon.isEmpty()) {
            throw new IllegalArgumentException("Telefoon mag niet leeg zijn.");
        }
        this.telefoon = telefoon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("E-mail mag niet leeg zijn.");
        }
        this.email = email;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        if (straat == null || straat.isEmpty()) {
            throw new IllegalArgumentException("Straat mag niet leeg zijn.");
        }
        this.straat = straat;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        if (gemeente == null || gemeente.isEmpty()) {
            throw new IllegalArgumentException("Gemeente mag niet leeg zijn.");
        }
        this.gemeente = gemeente;
    }

    public Graad getGraad() {
        return graad;
    }

    public void setGraad(Graad graad) {
        this.graad = graad;
    }

    public long getId() {
        return id;
    }
}
