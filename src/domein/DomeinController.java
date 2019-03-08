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
        return dojo.toonLid(id).toString();
    }

    /**
     *
     * @param lid
     */
    public boolean voegLidToe(ILid lid) {
        return dojo.voegLidToe(new Lid(lid));
    }

    public boolean wijzigLid(String voornaam, String familienaam, String wachtwoord, String gsm, String telefoon_vast, String straatnaam, String huisnummer, String busnummer,
            String postcode, String stad, String land, String rijksregisternummer, String email, String email_ouders, LocalDate geboortedatum, LocalDate inschrijvingsdatum, List<LocalDate> aanwezigheden,
            Geslacht geslacht, Graad graad, RolType type, LesType lessen) {
        boolean lid1 = dojo.wijzigLid(voornaam, familienaam, wachtwoord, gsm, telefoon_vast, straatnaam, huisnummer, busnummer, postcode, stad, land, rijksregisternummer, email, email_ouders, geboortedatum, inschrijvingsdatum,
                aanwezigheden, geslacht, graad, type, lessen);
        return lid1;

    }

    /**
     *
     * @param lid
     */
    public boolean verwijderCurrentLid() {
        return dojo.verwijderCurrentLid();
    }

    public ObservableList<ILid> getLeden() {
        return (ObservableList<ILid>) (Object) dojo.getSortedLeden();
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

    public void maakOverzicht(List<String> overzicht, String headers, String path) {
        dojo.maakOverzicht(overzicht, headers, path);
    }

    public List<Overzicht> getOverzicht() {
        return dojo.getOverzichtList();
    }

    public ObservableList<IOefening> getLesmateriaal() {
        return FXCollections.unmodifiableObservableList((ObservableList<IOefening>) (Object) dojo.getOefeningen());
    }

    /**
     *
     * @param oefening
     */
    public void addLesMateriaal(Oefening oefening) {
        dojo.addLesmateriaal(oefening);
    }

    public void wijzigLesMateriaal(Oefening newValue, IOefening oldValue) {
        dojo.wijzigLesmateriaal(newValue, (Oefening) oldValue);

    }

    public void verwijderLesMateriaal(long id) {
        dojo.verwijderLesMateriaal(id);
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
        return (ObservableList<IActiviteit>) (Object) dojo.getActiviteitenList();
    }

    private void setActiviteitRepository(ActiviteitDaoJpa activiteitDaoJpa) {
        this.activiteitRepository = activiteitDaoJpa;
    }

    public boolean voegActiviteitToe(IActiviteit act1) {
        return dojo.voegActiviteitToe(new Activiteit(act1));
    }

    public List<ILid> geefIngeschrevenLeden(long activiteitId) {
        List<ILid> iLeden = new ArrayList<>();
        System.out.println(activiteitRepository.get(activiteitId));
        System.out.println(activiteitRepository.get(activiteitId).getAanwezigen());
        activiteitRepository.get(activiteitId).getAanwezigen().stream().forEach(l -> {
            iLeden.add(l);
        });
        return iLeden;
    }

    public void schrijfLidIn(Activiteit activiteit, Lid lid) {
        dojo.lidInschrijven(activiteit, lid);
    }

    public Activiteit getActiviteit(long id) {
        return dojo.getActiviteit(id);
    }

    public List<String> maakOverzichtList(OverzichtType type, List<Object> extraParameters) {
        return dojo.maakOverzichtList(type, extraParameters);
    }

    public String maakHeaders() {
        return null;
    }
    
        public void setCurrentLid(ILid lid) {
        dojo.setCurrentLid(lid);
    }
    public ILid getCurrentLid() {
        return dojo.getCurrentLid();
    }
    public boolean geenLidGeslecteerd(){
        return getCurrentLid() == null;
    }
    public void verwijderSelectieLid(){
        setCurrentLid(null);
    }
}
