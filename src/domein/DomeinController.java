package domein;

import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistentie.ActiviteitDao;
import persistentie.ActiviteitDaoJpa;
import persistentie.GenericDaoJpa;
import persistentie.LidDao;
import persistentie.LidDaoJpa;
import persistentie.OefeningDao;
import persistentie.OefeningDaoJpa;

public class DomeinController {

    private Dojo dojo;
    private LidDao lidRepository;
    private OefeningDao oefeningRepository;
    private ActiviteitDao activiteitRepository;

    public DomeinController() {
        setLidRepository(new LidDaoJpa());
        setOefeningRepository(new OefeningDaoJpa());
        setActiviteitRepository(new ActiviteitDaoJpa());
        dojo = new Dojo(lidRepository, oefeningRepository, activiteitRepository);
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
            String postcode, String stad, String land, String rijksregisternummer, String email, String email_ouders, LocalDate geboortedatum, LocalDate inschrijvingsdatum, List<LocalDate> aanwezigheden,
            Geslacht geslacht, Graad graad, RolType type, LesType lessen) {

        GenericDaoJpa.startTransaction();

        boolean lid1 = dojo.wijzigLid(id, voornaam, familienaam, wachtwoord, gsm, telefoon_vast, straatnaam, huisnummer, busnummer, postcode, stad, land, rijksregisternummer, email, email_ouders, geboortedatum, inschrijvingsdatum,
                aanwezigheden, geslacht, graad, type, lessen);

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

    public void maakOverzicht(OverzichtType type, String besNaam, String path, List<Object> extraParameters) {
        dojo.maakOverzicht(type, besNaam, path, extraParameters);
    }

    public List<Overzicht> getOverzicht() {
        return dojo.getOverzichtList();
    }

    public ObservableList<IOefening> getLesmateriaal() {
        return FXCollections.unmodifiableObservableList(dojo.getOefeningen());
    }

    /**
     *
     * @param oefening
     */
    public void addLesMateriaal(Oefening oefening) {
        GenericDaoJpa.startTransaction();
        dojo.addOefening(oefening);
        GenericDaoJpa.commitTransaction();
    }

    public void wijzigLesMateriaal(Oefening oefening) {
        GenericDaoJpa.startTransaction();
        dojo.wijzigOefening(oefening);
        GenericDaoJpa.commitTransaction();

    }

    public void verwijderLesMateriaal(long id) {
        GenericDaoJpa.startTransaction();
        dojo.verwijderLesMateriaal(id);
        GenericDaoJpa.commitTransaction();
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

    public ObservableList<IActiviteit> getActiviteiten() {
        return dojo.getActiviteitenList();
    }

    private void setActiviteitRepository(ActiviteitDaoJpa activiteitDaoJpa) {
        this.activiteitRepository = activiteitDaoJpa;
    }

    public boolean voegActiviteitToe(IActiviteit act1) {
        GenericDaoJpa.startTransaction();
        boolean act = dojo.voegActiviteitToe(new Activiteit(act1));
        GenericDaoJpa.commitTransaction();
        return act;
    }

    public List<ILid> geefIngeschrevenLeden(long activiteitId) {
        List<ILid> iLeden = new ArrayList<>();
        activiteitRepository.get(activiteitId).getAanwezigen().stream().forEach(l -> {
            iLeden.add(l);
        });
        return iLeden;
    }

    public void schrijfLidIn(Activiteit activiteit, Lid lid) {
        GenericDaoJpa.startTransaction();
        dojo.lidInschrijven(activiteit, lid);
        GenericDaoJpa.commitTransaction();
    }

    public Activiteit getActiviteit(long id) {
        return dojo.getActiviteit(id);
    }
}
