package domein;

import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import persistentie.GenericDaoJpa;
import persistentie.LidDao;
import persistentie.LidDaoJpa;
import persistentie.OefeningDao;
import persistentie.OefeningDaoJpa;

public class DomeinController {

    private Dojo dojo;
    private LidDao lidRepository;
    private OefeningDao oefeningRepository;

    public DomeinController() {
        setLidRepository(new LidDaoJpa());
        setOefeningRepository(new OefeningDaoJpa());
        dojo = new Dojo(this.lidRepository, oefeningRepository);
    }

    public List<String> toonLeden() {
        return dojo.getLijstLeden().stream().map(ILid::toString).collect(Collectors.toList());
    }

    /**
     *
     * @param lid
     */
    public String toonLid(long id) {
        GenericDaoJpa.startTransaction();
        String lid1 = dojo.toonLid(id).toString();
        GenericDaoJpa.commitTransaction();
        return lid1;
    }

    /**
     *
     * @param lid
     */
    public boolean voegLidToe(ILid lid) {
        GenericDaoJpa.startTransaction();
        boolean lid1 = dojo.voegLidToe(new Lid(lid));
        GenericDaoJpa.commitTransaction();
        return lid1;
    }

    public boolean wijzigLid(long id, String voornaam, String familienaam, String wachtwoord, String gsm, String telefoon_vast, String straatnaam, String huisnummer, String busnummer,
            String postcode, String stad, String land, String email, String email_ouders, LocalDate geboortedatum, LocalDate inschrijvingsdatum, List<LocalDate> aanwezigheden,
            Geslacht geslacht, Graad graad, RolType type) {

        GenericDaoJpa.startTransaction();

        boolean lid1 = dojo.wijzigLid(id, voornaam, familienaam, wachtwoord, gsm, telefoon_vast, straatnaam, huisnummer, busnummer, postcode, stad, land, email, email_ouders, geboortedatum, inschrijvingsdatum,
                aanwezigheden, geslacht, graad, type);

        GenericDaoJpa.commitTransaction();
        return lid1;

    }

    /**
     *
     * @param lid
     */
    public boolean verwijderLid(long id) {
        GenericDaoJpa.startTransaction();
        boolean verwijdert = dojo.verwijderLid(id);
        GenericDaoJpa.commitTransaction();
        return verwijdert;
    }

    public ObservableList<ILid> getLeden() {
        return dojo.getSortedLeden();
    }

    private void setLidRepository(LidDao lidRepository) {
        this.lidRepository = lidRepository;
    }

    public void addPropertyChangeListener(PropertyChangeListener pc1) {
        dojo.addPropertyChangeListener(pc1);

    }

    public void removePropertyChangeListener(PropertyChangeListener pc1) {
        dojo.removePropertyChangeListener(pc1);

    }

    public void maakOverzicht(OverzichtType type, String path) {
        dojo.maakOverzicht(type, path);
    }

    public List<Overzicht> getOverzicht() {
        return dojo.getOverzichtList();
    }

    public List<Oefening> getLesmateriaal() {
        return dojo.getOefeningen();
    }

    /**
     *
     * @param oefening
     */
    public void addLesMateriaal(Oefening oefening) {
        dojo.addOefening(oefening);
    }

    public Oefening getOefening(Long id) {
        return dojo.getOefeningById(id);
    }

    private void setOefeningRepository(OefeningDaoJpa oefeningDao) {
        this.oefeningRepository = oefeningDao;
    }

    public void filter(String voornaamFilter, String familienaamFilter, String graadFilter, String typeFilter) {
        dojo.filter(voornaamFilter, familienaamFilter, graadFilter, typeFilter);
    }
}
