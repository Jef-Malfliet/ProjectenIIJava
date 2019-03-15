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
import persistentie.KampioenschapDao;
import persistentie.KampioenschapDaoJpa;
import persistentie.LidDao;
import persistentie.LidDaoJpa;
import persistentie.OefeningDao;
import persistentie.OefeningDaoJpa;

public class DomeinController {

    private Dojo dojo;
    private LidDao lidRepository;
    private OefeningDao oefeningRepository;
    private ActiviteitDao activiteitRepository;
    private KampioenschapDao kampioenschapRepository;

    public DomeinController() {
        setLidRepository(new LidDaoJpa());
        setOefeningRepository(new OefeningDaoJpa());
        setActiviteitRepository(new ActiviteitDaoJpa());
        setKampioenschapRepository(new KampioenschapDaoJpa());
        dojo = new Dojo(lidRepository, oefeningRepository, activiteitRepository, kampioenschapRepository);
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
            String postcode, String stad, Land land, String rijksregisternummer, String email, String email_ouders, LocalDate geboortedatum, LocalDate inschrijvingsdatum, List<LocalDate> aanwezigheden,
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

    public <T extends Exportable> void maakOverzicht(List<T> overzicht, String path) {
        dojo.maakOverzicht(overzicht, path);
    }

    public List<Overzicht<Object>> getOverzicht() {
        return dojo.getOverzichtList();
    }

    public ObservableList<IOefening> getLesmateriaal() {
        return FXCollections.unmodifiableObservableList((ObservableList<IOefening>) (Object) dojo.getSortedOefeningen());
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
    
    public void filterOefening(String naam, String graad){
        dojo.filterOefening(naam,graad);
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
        activiteitRepository.get(activiteitId).getAanwezigen().stream().forEach(l -> {
            iLeden.add(l);
        });
        return iLeden;
    }

    public void schrijfLidIn(String activiteitNaam, LocalDate beginDatum, LocalDate eindDatum, String lidEmail) {
        GenericDaoJpa.startTransaction();
        dojo.lidInschrijven(activiteitNaam, beginDatum, eindDatum, lidEmail);
        GenericDaoJpa.commitTransaction();
    }

    public void schrijfLidInVoorActiviteit(String activiteitNaam, LocalDate date, String lidEmail) {
        GenericDaoJpa.startTransaction();
        dojo.lidInschrijvenClubkampioenschap(activiteitNaam, date, lidEmail);
        GenericDaoJpa.commitTransaction();
    }

    public Activiteit getActiviteit(long id) {
        return dojo.getActiviteit(id);
    }

    public <T extends Exportable> List<T> maakOverzichtList(OverzichtType type, List<Object> extraParameters) {
        return dojo.maakOverzichtList(type, extraParameters);
    }

    public void setCurrentLid(ILid lid) {
        dojo.setCurrentLid(lid);
    }

    public ILid getCurrentLid() {
        return dojo.getCurrentLid();
    }

    public boolean geenLidGeslecteerd() {
        return getCurrentLid() == null;
    }

    public void verwijderSelectieLid() {
        setCurrentLid(null);
    }

    public boolean verwijderCurrentActiviteit() {
        return dojo.verwijderCurrentActiviteit();
    }

    public void verwijderSelectieActiviteit() {
        setCurrentActiviteit(null);
    }

    public void setCurrentActiviteit(IActiviteit activiteit) {
        dojo.setCurrentActiviteit(activiteit);
    }

    public boolean geenActiviteitGeselecteerd() {
        return getcurrentActiviteit() == null;
    }

    private IActiviteit getcurrentActiviteit() {
        return dojo.getCurrentActiviteit();
    }

    public IActiviteit getCurrentActiviteit() {
        return dojo.getCurrentActiviteit();
    }

    public boolean wijzigActiviteit(String naam, LocalDate beginDatum, LocalDate eindDatum, int maxAanwezigen, ActiviteitType type) {
        boolean activiteit = dojo.wijzigActiviteit(naam, beginDatum, eindDatum, maxAanwezigen, type);
        return activiteit;
    }

    public IActiviteit getActiviteitByNaamAndBeginAndEinddate(String activiteitNaam, LocalDate beginDatum, LocalDate eindDatum) {
        return dojo.getActiviteitByNaamAndBeginAndEinddate(activiteitNaam, beginDatum, eindDatum);
    }

    public void schrijfLidUit(String activiteitNaam, LocalDate beginDatum, LocalDate eindDatum, String lidEmail) {
        GenericDaoJpa.startTransaction();
        dojo.lidUitschrijven(activiteitNaam, beginDatum, eindDatum, lidEmail);
        GenericDaoJpa.commitTransaction();
    }

    public void addKampioenschap(Kampioenschap kampioenschap) {
        dojo.voegKampioenschapToe(kampioenschap);
    }

    private void setKampioenschapRepository(KampioenschapDaoJpa kampioenschapDao) {
        this.kampioenschapRepository = kampioenschapDao;
    }

    public void addPropertyChangeListenerOefening(PropertyChangeListener pcl){
        dojo.addPropertyChangeListenerOefening(pcl);
    }
    public void setCurrent_oefening(IOefening newOef) {
        dojo.setCurrent_oefening(newOef);
    }
    
    public IOefening getCurrent_oefening(){
        return dojo.getCurrent_oefening();
    }
}
