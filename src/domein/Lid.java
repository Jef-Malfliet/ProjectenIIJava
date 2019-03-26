package domein;

import java.io.Serializable;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.Entity;
import javax.persistence.*;
import static util.Validatie.*;

/**
 *
 * @author Nante
 */
@NamedQueries({
    @javax.persistence.NamedQuery(name = "Lid.GetAll", query = "SELECT e FROM Lid e"), //@javax.persistence.NamedQuery(name = "Lid.GetLedenByVoornaam", query = "SELECT e FROM Lid e WHERE e.voornaam = :lidVoornaam")
})
@Entity
@Table(name="Lid")
@Access(AccessType.FIELD)
public class Lid implements ILid, Serializable, Exportable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String wachtwoord;
    @Column()
    private String gsm;
    private String telefoon_vast;
    private String straatnaam;
    private String huisnummer;
    private String busnummer;
    private String postcode;
    private String stad;
    private Land land;
    private String rijksregisternummer;
    private String email;
    private String email_ouders;
    private LocalDate geboortedatum;
    private List<LocalDate> aanwezigheden;
    private static Exportable<Lid> exportable;

    @Enumerated(EnumType.STRING)
    @Column(name = "Geslacht", nullable = false)
    private Geslacht geslacht;
    @Transient
    private SimpleStringProperty voornaamProperty = new SimpleStringProperty();

    @Transient
    private SimpleStringProperty graadProperty = new SimpleStringProperty();

    @Transient
    private SimpleStringProperty typeProperty = new SimpleStringProperty();

    @Transient
    private SimpleStringProperty familienaamProperty = new SimpleStringProperty();

    @Transient
    private SimpleStringProperty lessenProperty = new SimpleStringProperty();

    @Transient
    private SimpleStringProperty inschrijvingsDatumProperty = new SimpleStringProperty();

    public Lid() {
    }
    

    public Lid(ILid lid) {
        this(lid.getVoornaam(), lid.getFamilienaam(), lid.getWachtwoord(), lid.getGsm(), lid.getTelefoon_vast(), lid.getStraatnaam(), lid.getHuisnummer(), lid.getBusnummer(), lid.getPostcode(), lid.getStad(),
                lid.getLand(), lid.getRijksregisternummer(), lid.getEmail(), lid.getEmail_ouders(), lid.getGeboortedatum(), lid.getInschrijvingsdatum(), lid.getAanwezigheden(), lid.getGeslacht(), lid.getGraad(), lid.getType(), lid.getLessen());

    }

    public Lid(DTOLid lid) {
        this(lid.getVoornaam(), lid.getFamilienaam(), lid.getWachtwoord(), lid.getGsm(), lid.getTelefoon_vast(), lid.getStraatnaam(), lid.getHuisnummer(), lid.getBusnummer(), lid.getPostcode(), lid.getStad(),
                lid.getLand(), lid.getRijksregisternummer(), lid.getEmail(), lid.getEmail_ouders(), lid.getGeboortedatum(), lid.getInschrijvingsdatum(), lid.getAanwezigheden(), lid.getGeslacht(), lid.getGraad(), lid.getType(), lid.getLessen());
    }

    public Lid(String voornaam, String familienaam, String wachtwoord, String gsm, String telefoon_vast, String straatnaam, String huisnummer, String busnummer, String postcode, String stad, Land land, String rijksregisternummer, String email, String email_ouders, LocalDate geboortedatum, LocalDate inschrijvingsdatum, List<LocalDate> aanwezigheden, Geslacht geslacht, Graad graad, RolType type, LesType lessen) {
        wijzigLid(voornaam, familienaam, wachtwoord, gsm, telefoon_vast, straatnaam, huisnummer, busnummer, postcode, stad, land, rijksregisternummer, email, email_ouders, geboortedatum, inschrijvingsdatum, aanwezigheden, geslacht, graad, type, lessen);

    }

    public final void wijzigLid(String voornaam, String familienaam, String wachtwoord, String gsm, String telefoon_vast, String straatnaam, String huisnummer, String busnummer, String postcode, String stad, Land land, String rijksregisternummer, String email, String email_ouders, LocalDate geboortedatum, LocalDate inschrijvingsdatum, List<LocalDate> aanwezigheden, Geslacht geslacht, Graad graad, RolType type, LesType lessen) {
        setVoornaam(voornaam);
        setFamilienaam(familienaam);
        setWachtwoord(wachtwoord);
        setGsm(gsm);
        setTelefoon_vast(telefoon_vast);
        setStraatnaam(straatnaam);
        setHuisnummer(huisnummer);
        setBusnummer(busnummer);
        setPostcode(postcode);
        setStad(stad);
        setLand(land);
        setRijksregisternummer(rijksregisternummer);
        setEmail(email);
        setEmail_ouders(email_ouders);
        setGeboortedatum(geboortedatum);
        setInschrijvingsdatum(inschrijvingsdatum);
        setAanwezigheden(aanwezigheden);
        setGeslacht(geslacht);
        setGraad(graad);
        setType(type);
        setLessen(lessen);
    }

    private void voegAanwezigheidToe(LocalDate date) {
        aanwezigheden.add(date);
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

    @Override
    public String toString() {
        return String.format("%s %s met graad %s%nTel.: %s%nE-mail adres: %s%nAdres: %s %s in %s%n", voornaamProperty.get(), familienaamProperty.get(), graadProperty.get(), telefoon_vast, email, straatnaam, postcode, stad);
    }

    @Override
    @Access(AccessType.PROPERTY)
    @Column(name = "Voornaam", nullable = false, length = 50)
    public String getVoornaam() {
        return voornaamProperty.get();
    }

    public void setVoornaam(String voornaam) {
        if (isNullOrEmpty(voornaam)) {
            throw new IllegalArgumentException("Voornaam mag niet leeg zijn.");
        }
        voornaamProperty.set(voornaam);

    }

    @Override
    public SimpleStringProperty getVoornaamProperty() {
        return this.voornaamProperty;
    }

    @Override
    @Access(AccessType.PROPERTY)
    @Column(name = "Familienaam", nullable = false, length = 50)
    public String getFamilienaam() {
        return familienaamProperty.get();
    }

    public void setFamilienaam(String familienaam) {
        if (isNullOrEmpty(familienaam)) {
            throw new IllegalArgumentException("familienaam mag niet leeg zijn.");
        }
        familienaamProperty.set(familienaam);
    }

    @Override
    public String getTelefoon_vast() {
        return telefoon_vast;
    }

    public void setTelefoon_vast(String telefoon_vast) {

        if (isNullOrEmpty(telefoon_vast)) {
            throw new IllegalArgumentException("Vaste telefoonnummer mag niet leeg zijn");
        }

        if (!(isValidLeeg(telefoon_vast) || isVasteTelefoonNummer(telefoon_vast))) {
            throw new IllegalArgumentException("Telefoon_vast is niet van het juiste formaat");
        }
        this.telefoon_vast = telefoon_vast;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (isNullOrEmpty(email)) {
            throw new IllegalArgumentException("E-mail mag niet leeg zijn.");
        }
        if (!isGeldigEmailAdres(email)) {
            throw new IllegalArgumentException("E-mail voldoet niet aan het juiste patroon(voorbeeld@voorbeeld.iets");
        }
        this.email = email;
    }

    @Override
    public String getStraatnaam() {
        return straatnaam;
    }

    public void setStraatnaam(String straatnaam) {
        if (isNullOrEmpty(straatnaam)) {
            throw new IllegalArgumentException("Straatnaam mag niet leeg zijn.");
        }
        this.straatnaam = straatnaam;
    }

    @Override
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        if (isNullOrEmpty(postcode)) {
            throw new IllegalArgumentException("Postcode mag niet leeg zijn");
        }
        if (!isPostcode(postcode)) {
            throw new IllegalArgumentException("postcode ongeldig");
        }
        this.postcode = postcode;
    }

    @Override
    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        if (isNullOrEmpty(stad)) {
            throw new IllegalArgumentException("stad mag niet leeg zijn.");
        }
        this.stad = stad;
    }

    @Override
    @Access(AccessType.PROPERTY)
    @Column(name = "Graad")
    public Graad getGraad() {

        return Graad.valueOf(graadProperty.get());
    }

    public void setGraad(Graad graad) {
        if (isNull(graad)) {
            throw new IllegalArgumentException("Graad mag niet null zijn");
        }
        graadProperty.set(graad.toString());
    }

    @Override
    public javafx.beans.property.SimpleStringProperty getGraadProperty() {
        return this.graadProperty;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setType(RolType type) {
        if (isNull(type)) {
            throw new IllegalArgumentException("Type mag niet null zijn");
        }
        typeProperty.set(type.toString());
    }

    @Override
    @Access(AccessType.PROPERTY)
    @Column(name = "Roltype")
    public RolType getType() {
        return RolType.valueOf(typeProperty.get());
    }

    @Override
    public javafx.beans.property.SimpleStringProperty getTypeProperty() {
        return typeProperty;
    }

    @Override
    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        if (isNullOrEmpty(huisnummer)) {
            throw new IllegalArgumentException("Huisnummer mag niet leeg zijn");
        }
        this.huisnummer = huisnummer;
    }

    @Override
    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        if (isNull(land)) {
            throw new IllegalArgumentException("Land mag niet leeg zijn");
        }
        this.land = land;
    }

    @Override
    public String getEmail_ouders() {
        return email_ouders;
    }

    public void setEmail_ouders(String email_ouders) {
        if (isNullOrEmpty(email_ouders)) {
            throw new IllegalArgumentException("Het email adres van de ouders mag niet leeg zijn. Indien geen ouderemailadres, vul / in");
        }
        if (!(isValidLeeg(email_ouders) || isGeldigEmailAdres(email_ouders))) {
            throw new IllegalArgumentException("E-mail voldoet niet aan het juiste patroon(voorbeeld@voorbeeld.iets");
        }
        this.email_ouders = email_ouders;
    }

    @Override
    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    @Override
    @Column(nullable = false)
    public LocalDate getInschrijvingsdatum() {
        return LocalDate.parse(inschrijvingsDatumProperty.get());
    }

    public void setInschrijvingsdatum(LocalDate inschrijvingsdatum) {
        inschrijvingsDatumProperty.set(inschrijvingsdatum.toString());
    }

    @Override
    public SimpleStringProperty getInschrijvingsDatumProperty() {
        return inschrijvingsDatumProperty;
    }

    @Override
    @Access(AccessType.PROPERTY)
    @Column(name = "Lessen")
    public LesType getLessen() {
        return LesType.valueOf(lessenProperty.get());
    }

    public void setLessen(LesType lessen) {
        lessenProperty.set(lessen.toString());
    }

    @Override
    public SimpleStringProperty getLessenProperty() {
        return lessenProperty;
    }

    @Override
    public List<LocalDate> getAanwezigheden() {
        return aanwezigheden;
    }

    public void setAanwezigheden(List<LocalDate> aanwezigheden) {
        this.aanwezigheden = aanwezigheden;
    }

    @Override

    public Geslacht getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(Geslacht geslacht) {
        if (isNull(geslacht)) {
            throw new IllegalArgumentException("Graad mag niet null zijn");
        }
        this.geslacht = geslacht;
    }

    @Override
    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        if (isNullOrEmpty(wachtwoord)) {
            throw new IllegalArgumentException("Het lid moet een wachtwoord hebben.");
        }
        this.wachtwoord = wachtwoord;
    }

    @Override
    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        if (isNullOrEmpty(gsm)) {
            throw new IllegalArgumentException("Gsm mag niet leeg zijn.");
        }
        if (!isGsmNummer(gsm)) {
            throw new IllegalArgumentException("Gsm is niet van het juiste formaat");
        }
        this.gsm = gsm;
    }

    @Override
    public String getBusnummer() {
        return busnummer;
    }

    public void setBusnummer(String busnummer) {
        if (isNullOrEmpty(busnummer)) {
            throw new IllegalArgumentException("Busnummer mag niet leeg zijn. Indien het lid geen busnummer heeft, vul dan / in");
        }
        this.busnummer = busnummer;
    }

    private String generateWachtwoord() {
        SecureRandom r = new SecureRandom();
        StringBuilder initials = new StringBuilder();
        initials.append(voornaamProperty.get().charAt(0));
        String[] famNaamSplit = familienaamProperty.get().split(" ");
        for (String deel : famNaamSplit) {
            initials.append(deel.charAt(0));
        }
        for (int i = 0; i < 5; i++) {
            initials.append(r.nextInt(10));
        }
        return initials.toString();
    }

    @Override
    public String getRijksregisternummer() {
        return rijksregisternummer;
    }

    public void setRijksregisternummer(String rijksregisternummer) {
        if (isNullOrEmpty(rijksregisternummer)) {
            throw new IllegalArgumentException("Rijksregisternummer mag niet leeg zijn");
        }
        if (!rijksregisternummerIsCorrect(rijksregisternummer)) {
            throw new IllegalArgumentException("Rijksregisternummer voldoet niet aan het juiste formaat");
        }
        this.rijksregisternummer = rijksregisternummer;
    }

    public static Exportable<Lid> getExportable() {
        return exportable;
    }

    public static void setExportable(Exportable<Lid> exportable) {
        Lid.exportable = exportable;
    }

    @Override
    public String excelFormat(Object object) {
        return exportable.excelFormat(this);
    }

    @Override
    public String excelheaders() {
        return exportable.excelheaders();
    }

    @Override
    public SimpleStringProperty getFamilienaamProperty() {
        return familienaamProperty;
    }

}
