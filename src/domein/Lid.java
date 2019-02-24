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

    @Enumerated(EnumType.STRING)
    private RolType type = RolType.LID;

    @Transient
    private SimpleStringProperty voornaamProperty = new SimpleStringProperty();

    @Transient
    private SimpleStringProperty graadProperty = new SimpleStringProperty();
    
    @Transient
    private SimpleStringProperty typeProperty = new SimpleStringProperty();

    public Lid() {
    }

    public Lid(String voornaam, String achternaam, Graad graad, String telefoon, String email, String straat, int postcode, String gemeente, RolType type) {
        setVoornaam(voornaam);
        setAchternaam(achternaam);
        setGraad(graad);
        setTelefoon(telefoon);
        setEmail(email);
        setStraat(straat);
        setPostcode(postcode);
        setGemeente(gemeente);
        setType(type);
        fillSimpleProperties();
    }

    public void wijzigLid(String voornaam, Graad graad, RolType type) {
        setGraad(graad);
        setVoornaam(voornaam);
        setType(type);
        fillSimpleProperties();

    }

    public void wijzigLid(String voornaam, String achternaam, Graad graad, String telefoon, String email, String straat, int postcode, String gemeente, RolType type) {
        setVoornaam(voornaam);
        setAchternaam(achternaam);
        setGraad(graad);
        setTelefoon(telefoon);
        setEmail(email);
        setStraat(straat);
        setPostcode(postcode);
        setGemeente(gemeente);
        setType(type);
        fillSimpleProperties();
    }

    public void fillSimpleProperties() {
        this.setGraadProperty(new SimpleStringProperty(this.getGraad().toString()));
        this.setVoornaamProperty(new SimpleStringProperty(this.getVoornaam()));
        this.setTypeProperty(new SimpleStringProperty(this.getType().toString()));
    }

    @Override
    public String toString() {
        return String.format("%s %s met graad %s%nTel.: %s%nE-mail adres: %s%nAdres: %s %d in %s%n", voornaam, achternaam, graad.toString(), telefoon, email, straat, postcode, gemeente);
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
        if (!(telefoon.matches("0\\d{9}") || telefoon.matches("00\\d{11}"))) {
            throw new IllegalArgumentException("Telefoon is niet van het juiste formaat");
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
        if (!email.matches("^([a-zA-Z0-9éèà]+[a-zA-Z0-9.-]*)@([a-zA-Z]+)[.]([a-z]+)([.][a-z]+)*$")) {
            throw new IllegalArgumentException("E-mail voldoet niet aan het juiste patroon(voorbeeld@voorbeeld.iets");
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
        if(postcode < 1000 || postcode > 9999){
            throw new IllegalArgumentException("Geen geldige postcode");
        }
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
        if (graad == null) {
            throw new IllegalArgumentException("Graad mag niet null zijn");
        }
        this.graad = graad;
    }

    public long getId() {
        return id;
    }

    public void setType(RolType type){
        this.type=type;
    }
    public RolType getType() {
        return type;
    }

    public SimpleStringProperty getTypeProperty() {
        return typeProperty;
    }

    public void setTypeProperty(SimpleStringProperty typeProperty) {
        this.typeProperty = typeProperty;
    }
    
    
    
}
