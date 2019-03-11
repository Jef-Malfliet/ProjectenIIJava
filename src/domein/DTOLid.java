/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Nante
 */
public class DTOLid implements ILid{

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
    private Land land;
    private String rijksregisternummer;
    private String email;
    private String email_ouders;
    private LocalDate geboortedatum;
    private LocalDate inschrijvingsdatum;
    private List<LocalDate> aanwezigheden;
    private LesType lessen;
    private Geslacht geslacht;
    private Graad graad;
    private RolType type;

    public DTOLid(String voornaam, String familienaam, String wachtwoord, String gsm, String telefoon_vast, String straatnaam, String huisnummer, String busnummer, String postcode, String stad, Land land, String rijksregisternummer, String email, String email_ouders, LocalDate geboortedatum, LocalDate inschrijvingsdatum, List<LocalDate> aanwezigheden, Geslacht geslacht, Graad graad, RolType type, LesType lessen) {
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

    
    
    public void setId(long id) {
        this.id = id;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public void setFamilienaam(String familienaam) {
        this.familienaam = familienaam;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public void setTelefoon_vast(String telefoon_vast) {
        this.telefoon_vast = telefoon_vast;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public void setBusnummer(String busnummer) {
        this.busnummer = busnummer;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public void setRijksregisternummer(String rijksregisternummer) {
        this.rijksregisternummer = rijksregisternummer;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmail_ouders(String email_ouders) {
        this.email_ouders = email_ouders;
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public void setInschrijvingsdatum(LocalDate inschrijvingsdatum) {
        this.inschrijvingsdatum = inschrijvingsdatum;
    }

    public void setAanwezigheden(List<LocalDate> aanwezigheden) {
        this.aanwezigheden = aanwezigheden;
    }

    public void setLessen(LesType lessen) {
        this.lessen = lessen;
    }

    public void setGeslacht(Geslacht geslacht) {
        this.geslacht = geslacht;
    }

    public void setGraad(Graad graad) {
        this.graad = graad;
    }

    public void setType(RolType type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public String getGsm() {
        return gsm;
    }

    public String getTelefoon_vast() {
        return telefoon_vast;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public String getBusnummer() {
        return busnummer;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getStad() {
        return stad;
    }

    public Land getLand() {
        return land;
    }

    public String getRijksregisternummer() {
        return rijksregisternummer;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail_ouders() {
        return email_ouders;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public LocalDate getInschrijvingsdatum() {
        return inschrijvingsdatum;
    }

    public List<LocalDate> getAanwezigheden() {
        return aanwezigheden;
    }

    public LesType getLessen() {
        return lessen;
    }

    public Geslacht getGeslacht() {
        return geslacht;
    }

    public Graad getGraad() {
        return graad;
    }

    public RolType getType() {
        return type;
    }

    @Override
    public SimpleStringProperty getVoornaamProperty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SimpleStringProperty getFamilienaamProperty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SimpleStringProperty getGraadProperty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SimpleStringProperty getTypeProperty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SimpleStringProperty getLessenProperty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SimpleStringProperty getInschrijvingsDatumProperty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
