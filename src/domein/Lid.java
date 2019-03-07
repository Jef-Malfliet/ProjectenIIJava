package domein;

import java.io.Serializable;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.Entity;
import javax.persistence.*;

/**
 *
 * @author Nante
 */
@Entity
@NamedQueries({
    @javax.persistence.NamedQuery(name = "Lid.GetAll", query = "SELECT e FROM Lid e"),
    @javax.persistence.NamedQuery(name = "Lid.GetLedenByVoornaam", query = "SELECT e FROM Lid e WHERE e.voornaam = :lidVoornaam")
})
public class Lid implements Serializable, Exportable, ILid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String voornaam;
    private String familienaam;
    private String wachtwoord;
    private String gsm;
    private String telefoon_vast;
    private String straatnaam;
    private String huisnummer;
    private String busnummer;
    private String postcode;
    private String stad;
    private String land;
    private String rijksregisternummer;
    private String email;
    private String email_ouders;
    private LocalDate geboortedatum;
    private LocalDate inschrijvingsdatum;
    private List<LocalDate> aanwezigheden;
    private LesType lessen;

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
    public Lid(DTOLid lid){
         this(lid.getVoornaam(), lid.getFamilienaam(), lid.getWachtwoord(), lid.getGsm(), lid.getTelefoon_vast(), lid.getStraatnaam(), lid.getHuisnummer(), lid.getBusnummer(), lid.getPostcode(), lid.getStad(),
                lid.getLand(), lid.getRijksregisternummer(), lid.getEmail(), lid.getEmail_ouders(), lid.getGeboortedatum(), lid.getInschrijvingsdatum(), lid.getAanwezigheden(), lid.getGeslacht(), lid.getGraad(), lid.getType(), lid.getLessen());
    }

    public Lid(String voornaam, String familienaam, String wachtwoord, String gsm, String telefoon_vast, String straatnaam, String huisnummer, String busnummer, String postcode, String stad, String land, String rijksregisternummer, String email, String email_ouders, LocalDate geboortedatum, LocalDate inschrijvingsdatum, List<LocalDate> aanwezigheden, Geslacht geslacht, Graad graad, RolType type, LesType lessen) {
        wijzigLid(voornaam, familienaam, wachtwoord, gsm, telefoon_vast, straatnaam, huisnummer, busnummer, postcode, stad, land, rijksregisternummer, email, email_ouders, geboortedatum, inschrijvingsdatum, aanwezigheden, geslacht, graad, type, lessen);

    }

    public final void wijzigLid(String voornaam, String familienaam, String wachtwoord, String gsm, String telefoon_vast, String straatnaam, String huisnummer, String busnummer, String postcode, String stad, String land, String rijksregisternummer, String email, String email_ouders, LocalDate geboortedatum, LocalDate inschrijvingsdatum, List<LocalDate> aanwezigheden, Geslacht geslacht, Graad graad, RolType type, LesType lessen) {
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
        // fillSimpleProperties();
    }

    public void fillSimpleProperties() {
        this.setGraadProperty(new SimpleStringProperty(this.getGraad().toString()));
        this.setVoornaamProperty(new SimpleStringProperty(this.getVoornaam()));
        this.setTypeProperty(new SimpleStringProperty(this.getType().toString()));
        this.setFamilienaamProperty(new SimpleStringProperty(this.getFamilienaam()));
        this.setLessenProperty(new SimpleStringProperty(this.getLessen().toString()));
        this.setInschrijvingsDatumProperty(new SimpleStringProperty(this.getInschrijvingsdatum().toString()));
    }

    @Override
    public String toString() {
        return String.format("%s %s met graad %s%nTel.: %s%nE-mail adres: %s%nAdres: %s %s in %s%n", voornaam, familienaam, graad.toString(), telefoon_vast, email, straatnaam, postcode, stad);
    }

    @Override
    public String excelFormat() {
        return String.format("%s,%s,%s,%s,%s,%s,%s%n", voornaam, familienaam, graad.toString(), telefoon_vast, email, straatnaam, postcode, stad);

    }

    @Override
    public String excelheaders() {
        return String.format("%s,%s,%s,%s,%s,%s,%s%n", "Voornaam", "familienaam", "Graad", "Telefoon_vast", "Email", "Straatnaam", "Postcode", "stad");
    }

    public SimpleStringProperty getVoornaamProperty() {
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

    @Override
    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        if (voornaam == null || voornaam.isEmpty()) {
            throw new IllegalArgumentException("Voornaam mag niet leeg zijn.");
        }
        this.voornaam = voornaam;
    }

    @Override
    public String getFamilienaam() {
        return familienaam;
    }

    public void setFamilienaam(String familienaam) {
        if (familienaam == null || familienaam.isEmpty()) {
            throw new IllegalArgumentException("familienaam mag niet leeg zijn.");
        }
        this.familienaam = familienaam;
    }

    @Override
    public String getTelefoon_vast() {
        return telefoon_vast;
    }

    public void setTelefoon_vast(String telefoon_vast) {
        if (telefoon_vast == null || telefoon_vast.isEmpty()) {
            throw new IllegalArgumentException("Vaste telefoonnummer mag niet leeg zijn");
        }
        if (!telefoon_vast.equals("/") && !(telefoon_vast.matches("0\\d{8}") || telefoon_vast.matches("00\\d{11}"))) {
            throw new IllegalArgumentException("Telefoon_vast is niet van het juiste formaat");
        }
        this.telefoon_vast = telefoon_vast;
    }

    @Override
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

    @Override
    public String getStraatnaam() {
        return straatnaam;
    }

    public void setStraatnaam(String straatnaam) {
        if (straatnaam == null || straatnaam.isEmpty()) {
            throw new IllegalArgumentException("Straatnaam mag niet leeg zijn.");
        }
        this.straatnaam = straatnaam;
    }

    @Override
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        if (postcode == null || postcode.isEmpty()) {
            throw new IllegalArgumentException("Postcode mag niet leeg zijn");
        }
        if (!(postcode.matches("[1-9][0-9]{3}"))) {
            throw new IllegalArgumentException("postcode ongeldig");
        }
        this.postcode = postcode;
    }

    @Override
    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        if (stad == null || stad.isEmpty()) {
            throw new IllegalArgumentException("stad mag niet leeg zijn.");
        }
        this.stad = stad;
    }

    @Override
    public Graad getGraad() {
        return graad;
    }

    public void setGraad(Graad graad) {
        if (graad == null) {
            throw new IllegalArgumentException("Graad mag niet null zijn");
        }
        this.graad = graad;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setType(RolType type) {
        this.type = type;
    }

    @Override
    public RolType getType() {
        return type;
    }

    @Override
    public javafx.beans.property.SimpleStringProperty getTypeProperty() {
        return typeProperty;
    }

    public void setTypeProperty(javafx.beans.property.SimpleStringProperty typeProperty) {
        this.typeProperty = typeProperty;
    }

    private void voegAanwezigheidToe(LocalDate date) {
        aanwezigheden.add(date);
    }

    @Override
    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        if (huisnummer == null || huisnummer.isEmpty()) {
            throw new IllegalArgumentException("Huisnummer mag niet leeg zijn");
        }
        this.huisnummer = huisnummer;
    }

    @Override
    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        if (land == null || land.isEmpty()) {
            throw new IllegalArgumentException("Land mag niet leeg zijn");
        }
        this.land = land;
    }

    @Override
    public String getEmail_ouders() {
        return email_ouders;
    }

    public void setEmail_ouders(String email_ouders) {
        if (email_ouders == null || email_ouders.isEmpty()) {
            throw new IllegalArgumentException("Het email adres van de ouders mag niet leeg zijn. Indien geen ouderemailadres, vul / in");
        }
        if (!email_ouders.equals("/") && !email_ouders.matches("^([a-zA-Z0-9éèà]+[a-zA-Z0-9.-]*)@([a-zA-Z]+)[.]([a-z]+)([.][a-z]+)*$")) {
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
    public LocalDate getInschrijvingsdatum() {
        return inschrijvingsdatum;
    }

    public void setInschrijvingsdatum(LocalDate inschrijvingsdatum) {
        this.inschrijvingsdatum = inschrijvingsdatum;
    }

    @Override
    public List<LocalDate> getAanwezigheden() {
        return aanwezigheden;
    }

    public void setAanwezigheden(List<LocalDate> aanwezigheden) {
        this.aanwezigheden = aanwezigheden;
    }

    public Geslacht getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(Geslacht geslacht) {
        this.geslacht = geslacht;
    }

    public SimpleStringProperty getFamilienaamProperty() {
        return familienaamProperty;
    }

    public void setFamilienaamProperty(SimpleStringProperty familienaamProperty) {
        this.familienaamProperty = familienaamProperty;
    }

    @Override
    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        if (wachtwoord == null || wachtwoord.isEmpty()) {
            throw new IllegalArgumentException("Het lid moet een wachtwoord hebben.");
        }
        this.wachtwoord = wachtwoord;
    }

    @Override
    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        if (gsm == null || gsm.isEmpty()) {
            throw new IllegalArgumentException("Gsm mag niet leeg zijn.");
        }
        if (!(gsm.matches("0\\d{9}") || gsm.matches("00\\d{11}"))) {
            throw new IllegalArgumentException("Gsm is niet van het juiste formaat");
        }
        this.gsm = gsm;
    }

    @Override
    public String getBusnummer() {
        return busnummer;
    }

    public void setBusnummer(String busnummer) {
        if (busnummer == null || busnummer.isEmpty()) {
            throw new IllegalArgumentException("Busnummer mag niet leeg zijn. Indien het lid geen busnummer heeft, vul dan / in");
        }
        this.busnummer = busnummer;
    }

    private String generateWachtwoord() {
        SecureRandom r = new SecureRandom();
        StringBuilder initials = new StringBuilder();
        initials.append(voornaam.charAt(0));
        String[] famNaamSplit = familienaam.split(" ");
        for (String deel : famNaamSplit) {
            initials.append(deel.charAt(0));
        }
        for (int i = 0; i < 5; i++) {
            initials.append(r.nextInt(10));
        }
        return initials.toString();
    }

    @Override
    public LesType getLessen() {
        return lessen;
    }

    public void setLessen(LesType lessen) {
        this.lessen = lessen;
    }

    @Override
    public String getRijksregisternummer() {
        return rijksregisternummer;
    }

    public void setRijksregisternummer(String rijksregisternummer) {
        if (rijksregisternummer == null || rijksregisternummer.isEmpty()) {
            throw new IllegalArgumentException("Rijksregisternummer mag niet leeg zijn");
        }

        String pattern = "^([0-9]{2}).(0[1-9]|1[0-2]).((0[1-9])|(1[0-9])|(2[0-9]|3[0-1]))-([0-9]{3}).([0-9]{2})$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(rijksregisternummer);
        if (!m.matches()) {
            throw new IllegalArgumentException("Rijksregisternummer voldoet niet aan het juiste formaat");
        }

        int controle = 0;
        int getal = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(m.group(1)).append(m.group(2)).append(m.group(3)).append(m.group(7));
        try {
            getal = Integer.parseInt(sb.toString());
            controle = Integer.parseInt(m.group(8));//controle cijfer
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        int modulo = getal % 97;
        if (97 - modulo != controle) {
            throw new IllegalArgumentException("Controlegetal klopt niet");
        }

        this.rijksregisternummer = rijksregisternummer;
    }

    @Override
    public SimpleStringProperty getLessenProperty() {
        return lessenProperty;
    }

    private void setLessenProperty(SimpleStringProperty simpleStringProperty) {
        this.lessenProperty = simpleStringProperty;
    }

    @Override
    public SimpleStringProperty getInschrijvingsDatumProperty() {
        return inschrijvingsDatumProperty;
    }

    private void setInschrijvingsDatumProperty(SimpleStringProperty simpleStringProperty) {
        this.inschrijvingsDatumProperty = simpleStringProperty;
    }

}
