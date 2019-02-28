package domein;
 
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.Entity;
import javax.persistence.*;
 
/**
 *
 * @author Nante
 */
@Entity
@NamedQueries({
	@javax.persistence.NamedQuery(name="Lid.GetAll", query="SELECT e FROM Lid e"), 
	@javax.persistence.NamedQuery(name="Lid.GetLedenByVoornaam", query="SELECT e FROM Lid e WHERE e.voornaam = :lidVoornaam")
})
public class Lid implements Serializable, Exportable {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
 
    private String voornaam;
    private String familienaam;
    private String wachtwoord;
    private String gsm;
    private String telefoon_vast;
    private String straatnaam;
    private int huisnummer;
    private int postcode;
    private String stad;
    private String land;
    private String email;
    private String email_ouders;
    private GregorianCalendar geboortedatum;
    private GregorianCalendar inschrijvingsdatum;
    private List<Date> aanwezigheden;
 
    @Enumerated(EnumType.STRING)
    private Geslacht geslacht;
    @Enumerated(EnumType.STRING)
    private Graad graad;
 
    @javax.persistence.Transient
    private RolType type;
 
    @Transient
    private SimpleStringProperty voornaamProperty = new SimpleStringProperty();
 
    @Transient
    private SimpleStringProperty graadProperty = new SimpleStringProperty();
 
    @Transient
    private SimpleStringProperty typeProperty = new SimpleStringProperty();
   
    @Transient
    private SimpleStringProperty familienaamProperty = new SimpleStringProperty();
 
   
    public Lid() {
    }
 
    public Lid(String voornaam, String familienaam,String wachtwoord, String gsm, String telefoon_vast, String straatnaam, int huisnummer, int postcode, String stad, String land, String email, String email_ouders, GregorianCalendar geboortedatum, GregorianCalendar inschrijvingsdatum, List<Date> aanwezigheden, Geslacht geslacht, Graad graad, RolType type) {
        setVoornaam(voornaam);
        setFamilienaam(familienaam);
        setWachtwoord(wachtwoord);
        setGsm(gsm);
        setTelefoon_vast(telefoon_vast);
        setStraatnaam(straatnaam);
        setHuisnummer(huisnummer);
        setPostcode(postcode);
        setStad(stad);
        setLand(land);
        setEmail(email);
        setEmail_ouders(email_ouders);
        setGeboortedatum(geboortedatum);
        setInschrijvingsdatum(inschrijvingsdatum);
        setAanwezigheden(aanwezigheden);
        setGeslacht(geslacht);
        setGraad(graad);
        setType(type);
       
        fillSimpleProperties();
    }
 
   
    public Lid(String voornaam, String familienaam, Graad graad, String telefoon_vast, String email, String straatnaam, int postcode, String stad, RolType type) {
        setVoornaam(voornaam);
        setFamilienaam(familienaam);
        setGraad(graad);
        setTelefoon_vast(telefoon_vast);
        setEmail(email);
        setStraatnaam(straatnaam);
        setPostcode(postcode);
        setStad(stad);
        setType(type);
        fillSimpleProperties();
    }
 
    public void wijzigLid(String voornaam, Graad graad, RolType type) {
        setGraad(graad);
        setVoornaam(voornaam);
        setType(type);
        fillSimpleProperties();
 
    }
 
    public void wijzigLid(String voornaam, String familienaam, Graad graad, String telefoon_vast, String email, String straatnaam, int postcode, String stad, RolType type) {
        setVoornaam(voornaam);
        setFamilienaam(familienaam);
        setGraad(graad);
        setTelefoon_vast(telefoon_vast);
        setEmail(email);
        setStraatnaam(straatnaam);
        setPostcode(postcode);
        setStad(stad);
        setType(type);
        fillSimpleProperties();
    }
 
    public void fillSimpleProperties() {
        this.setGraadProperty(new SimpleStringProperty(this.getGraad().toString()));
        this.setVoornaamProperty(new SimpleStringProperty(this.getVoornaam()));
        this.setTypeProperty(new SimpleStringProperty(this.getType().toString()));
        this.setFamilienaamProperty(new SimpleStringProperty(this.getFamilienaam()));
    }
 
    @Override
    public String toString() {
        return String.format("%s %s met graad %s%nTel.: %s%nE-mail adres: %s%nAdres: %s %d in %s%n", voornaam, familienaam, graad.toString(), telefoon_vast, email, straatnaam, postcode, stad);
    }
 
    @Override
    public String excelFormat() {
        return String.format("%s,%s,%s,%s,%s,%s,%s%n", voornaam, familienaam, graad.toString(), telefoon_vast, email, straatnaam, postcode, stad);
 
    }
 
    @Override
    public String excelheaders() {
        return String.format("%s,%s,%s,%s,%s,%s,%s%n", "Voornaam", "familienaam", "Graad", "Telefoon_vast", "Email", "Straatnaam", "Postcode", "stad");
    }
 
    public javafx.beans.property.SimpleStringProperty getVoornaamProperty() {
        return this.voornaamProperty;
    }
 
    public void setVoornaamProperty(javafx.beans.property.SimpleStringProperty voornaamProperty) {
        this.voornaamProperty = voornaamProperty;
    }
 
    public javafx.beans.property.SimpleStringProperty getGraadProperty() {
        return this.graadProperty;
    }
 
    public void setGraadProperty(javafx.beans.property.SimpleStringProperty graadProperty) {
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
 
    public String getFamilienaam() {
        return familienaam;
    }
 
    public void setFamilienaam(String familienaam) {
        if (familienaam == null || familienaam.isEmpty()) {
            throw new IllegalArgumentException("familienaam mag niet leeg zijn.");
        }
        this.familienaam = familienaam;
    }
 
    public String getTelefoon_vast() {
        return telefoon_vast;
    }
 
    public void setTelefoon_vast(String telefoon_vast) {
        if (telefoon_vast == null || telefoon_vast.isEmpty()) {
            throw new IllegalArgumentException("Telefoon_vast mag niet leeg zijn.");
        }
        if (!(telefoon_vast.matches("0\\d{8}") || telefoon_vast.matches("00\\d{10}"))) {
            throw new IllegalArgumentException("Telefoon_vast is niet van het juiste formaat");
        }
        this.telefoon_vast = telefoon_vast;
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
 
    public String getStraatnaam() {
        return straatnaam;
    }
 
    public void setStraatnaam(String straatnaam) {
        if (straatnaam == null || straatnaam.isEmpty()) {
            throw new IllegalArgumentException("Straatnaam mag niet leeg zijn.");
        }
        this.straatnaam = straatnaam;
    }
 
    public int getPostcode() {
        return postcode;
    }
 
    public void setPostcode(int postcode) {
        if (postcode < 1000 || postcode > 9999) {
            throw new IllegalArgumentException("Geen geldige postcode");
        }
        this.postcode = postcode;
    }
 
    public String getStad() {
        return stad;
    }
 
    public void setStad(String stad) {
        if (stad == null || stad.isEmpty()) {
            throw new IllegalArgumentException("stad mag niet leeg zijn.");
        }
        this.stad = stad;
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
 
    public void setType(RolType type) {
        this.type = type;
    }
 
    public RolType getType() {
        return type;
    }
 
    public javafx.beans.property.SimpleStringProperty getTypeProperty() {
        return typeProperty;
    }
 
    public void setTypeProperty(javafx.beans.property.SimpleStringProperty typeProperty) {
        this.typeProperty = typeProperty;
    }
    public void voegAanwezigheidToe(Date date){
        aanwezigheden.add(date);
    }
 
    public int getHuisnummer() {
        return huisnummer;
    }
 
    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }
 
    public String getLand() {
        return land;
    }
 
    public void setLand(String land) {
        this.land = land;
    }
 
    public String getEmail_ouders() {
        return email_ouders;
    }
 
    public void setEmail_ouders(String email_ouders) {
        this.email_ouders = email_ouders;
    }
 
    public GregorianCalendar getGeboortedatum() {
        return geboortedatum;
    }
 
    public void setGeboortedatum(GregorianCalendar geboortedatum) {
        this.geboortedatum = geboortedatum;
    }
 
    public GregorianCalendar getInschrijvingsdatum() {
        return inschrijvingsdatum;
    }
 
    public void setInschrijvingsdatum(GregorianCalendar inschrijvingsdatum) {
        this.inschrijvingsdatum = inschrijvingsdatum;
    }
 
    public List<Date> getAanwezigheden() {
        return aanwezigheden;
    }
 
    public void setAanwezigheden(List<Date> aanwezigheden) {
        this.aanwezigheden = aanwezigheden;
    }
 
    public Geslacht getGeslacht() {
        return geslacht;
    }
 
    public void setGeslacht(Geslacht geslacht) {
        this.geslacht = geslacht;
    }
 
    public javafx.beans.property.SimpleStringProperty getFamilienaamProperty() {
        return familienaamProperty;
    }
 
    public void setFamilienaamProperty(javafx.beans.property.SimpleStringProperty familienaamProperty) {
        this.familienaamProperty = familienaamProperty;
    }
 
    public String getWachtwoord() {
        return wachtwoord;
    }
 
    private void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }
 
    public String getGsm() {
        return gsm;
    }
 
    public void setGsm(String gsm) {
        this.gsm = gsm;
    }
   
   
}