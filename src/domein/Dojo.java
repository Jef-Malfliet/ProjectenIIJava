package domein;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import persistentie.ActiviteitDao;
import persistentie.ExportFiles;
import persistentie.GenericDaoJpa;
import persistentie.KampioenschapDao;
import persistentie.LidDao;
import persistentie.OefeningDao;

public class Dojo {

    private final ObservableList<Lid> leden;
    private final FilteredList<Lid> filteredLeden;
    private final SortedList<Lid> sortedLeden;
    private final Comparator<Lid> opVoornaam = (lid1, lid2) -> lid1.getVoornaam().compareToIgnoreCase(lid2.getVoornaam());
    private final Comparator<Lid> opType = (lid1, lid2) -> lid1.getType().compareTo(lid2.getType());
    private final Comparator<Lid> opGraad = (lid1, lid2) -> lid1.getGraad().compareTo(lid2.getGraad());
    private final Comparator<Lid> sortOrderLeden = opVoornaam.thenComparing(opGraad).thenComparing(opType);

    private final ObservableList<Activiteit> activiteiten;
    private final FilteredList<Activiteit> filteredActiviteiten;
    private final SortedList<Activiteit> sortedActiviteiten;

    private final ObservableList<Oefening> oefeningen;
    private final FilteredList<Oefening> filteredOefeningen;
    private final SortedList<Oefening> sortedOefeningen;
    private final Comparator<Oefening> opOefNaam = (oef1, oef2) -> oef1.getNaam().compareToIgnoreCase(oef2.getNaam());
    private final Comparator<Oefening> opOefGraad = (oef1, oef2) -> oef1.getGraad().compareTo(oef2.getGraad());
    private final Comparator<Oefening> sortOefeningOrder = opOefNaam.thenComparing(opOefGraad);

    private final ObservableList<Kampioenschap> kampioenschappen;
    private final FilteredList<Kampioenschap> filteredKampioenschappen;
    private final SortedList<Kampioenschap> sortedKampioenschappen;

    private final PropertyChangeSupport subject;
    private final PropertyChangeSupport subjectAct;
    private final PropertyChangeSupport subjectOef;
    private LidDao lidRepo;
    private OefeningDao oefeningRepo;
    private ActiviteitDao activiteitRepo;
    private KampioenschapDao kampioenschapRepo;
    private final List<Overzicht<Object>> overzichtList;
    private Lid currentLid;
    private IOefening current_oefening;
    private final RolType type;

    private List<String> headers = new ArrayList<>();

    private Activiteit currentActiviteit;

    public Dojo(LidDao lidRepo, OefeningDao oefeningRepo, ActiviteitDao actRepo, KampioenschapDao kampioenschapDao) {
        setLidRepo(lidRepo);
        setOefeningRepo(oefeningRepo);
        setActiviteitDao(actRepo);
        setKampioenschapRepo(kampioenschapDao);
        this.type = RolType.BEHEERDER;
        subject = new PropertyChangeSupport(this);
        subjectOef = new PropertyChangeSupport(this);
        subjectAct = new PropertyChangeSupport(this);
        overzichtList = new ArrayList();

        leden = FXCollections.observableArrayList(this.lidRepo.findAll());
        filteredLeden = new FilteredList<>(leden, (p) -> true);
        sortedLeden = new SortedList<>(filteredLeden, sortOrderLeden);

        oefeningen = FXCollections.observableArrayList(oefeningRepo.findAll());
        filteredOefeningen = new FilteredList<>(oefeningen, (p) -> true);
        sortedOefeningen = new SortedList<>(filteredOefeningen, sortOefeningOrder);

        kampioenschappen = FXCollections.observableArrayList();
        filteredKampioenschappen = new FilteredList<>(kampioenschappen, p -> true);
        sortedKampioenschappen = new SortedList<>(filteredKampioenschappen);

        activiteiten = FXCollections.observableArrayList(this.activiteitRepo.findAll());
        filteredActiviteiten = new FilteredList<>(activiteiten, p -> true);
        sortedActiviteiten = new SortedList<>(filteredActiviteiten);
    }

    /**
     *
     * @param lid
     */
    public boolean verwijderCurrentLid() {
        GenericDaoJpa.startTransaction();
        this.lidRepo.delete(currentLid);
        boolean remove = this.leden.remove(currentLid);
        GenericDaoJpa.commitTransaction();
        return remove;
    }

    /**
     *
     * @param lid
     */
    public boolean wijzigLid(String voornaam, String familienaam, String wachtwoord, String gsm, String telefoon_vast, String straatnaam, String huisnummer, String busnummer, String postcode, String stad, Land land, String rijksregisternummer, String email, String email_ouders, LocalDate geboortedatum, LocalDate inschrijvingsdatum, List<LocalDate> aanwezigheden, Geslacht geslacht, Graad graad, RolType type, LesType lessen) {
        GenericDaoJpa.startTransaction();
        currentLid.wijzigLid(voornaam, familienaam, wachtwoord, gsm, telefoon_vast, straatnaam, huisnummer, busnummer, postcode, stad, land, rijksregisternummer, email, email_ouders, geboortedatum, inschrijvingsdatum, aanwezigheden, geslacht, graad, type, lessen);
        lidRepo.update(currentLid);
        GenericDaoJpa.commitTransaction();
        return true;
    }

    /**
     *
     * @param lid
     */
    public ILid toonLid(long id) {
        return leden.stream().filter(l -> l.getId() == id).findFirst().orElse(null);
    }

    /**
     *
     * @param lid
     */
    public boolean voegLidToe(Lid lid) {

        if (!leden.contains(lid)) {
            if (lidRepo.get(lid.getId()) == null) {
                GenericDaoJpa.startTransaction();
                lidRepo.insert(lid);
                GenericDaoJpa.commitTransaction();
                leden.add(lid);
                return true;
            }
        }
        return false;
    }

    public boolean voegKampioenschapToe(Kampioenschap k) {
        if (!kampioenschappen.contains(k)) {
            if (kampioenschapRepo.get(k.getId()) == null) {
                GenericDaoJpa.startTransaction();
                kampioenschapRepo.insert(k);
                GenericDaoJpa.commitTransaction();
                kampioenschappen.add(k);
                return true;
            }
        }
        return false;
    }

    public boolean voegActiviteitToe(Activiteit activiteit) {

        if (!activiteiten.contains(activiteit)) {
            if (activiteitRepo.get(activiteit.getId()) == null) {
                GenericDaoJpa.startTransaction();
                activiteitRepo.insert(activiteit);
                GenericDaoJpa.commitTransaction();
                activiteiten.add(activiteit);
                return true;
            }
        }
        return false;
    }

    public List<Lid> getLijstLeden() {
        return leden;
    }

    public RolType getType() {
        return type;
    }

    public ObservableList<Lid> getSortedLeden() {
        return sortedLeden;
    }

    public FilteredList getFilteredLeden() {
        return filteredLeden;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        subject.addPropertyChangeListener(pcl);
        pcl.propertyChange(new PropertyChangeEvent(pcl, "currentLid", null, currentLid));
    }

    public void removePropertyChangeListener(PropertyChangeListener pc1) {
        subject.removePropertyChangeListener(pc1);

    }

    public void setLidRepo(LidDao mock) {
        lidRepo = mock;
    }

    public void setKampioenschapRepo(KampioenschapDao mock) {
        kampioenschapRepo = mock;
    }

    public <T extends Exportable> void maakOverzicht(List<T> overzicht, String path) {
        ExportFiles.toExcel(overzicht, 25, 20, path);
    }

    public List<Overzicht<Object>> getOverzichtList() {
        return overzichtList;
    }

    public ObservableList<Oefening> getSortedOefeningen() {
        return sortedOefeningen;
    }

    public Oefening getOefeningById(Long id) {
        return oefeningRepo.get(id);
    }

    public ObservableList<Activiteit> getActiviteitenList() {
        return activiteiten;
    }

    public void lidInschrijven(String activiteitNaam, LocalDate beginDatum, LocalDate eindDatum, String lidEmail) {
        Activiteit actTemp = activiteitRepo.getByNaamAndBeginAndEindDate(activiteitNaam, beginDatum, eindDatum);
        Lid lidTemp = lidRepo.getLidByEmail(lidEmail);
        actTemp.lidInschrijven(lidTemp);
    }

    public void lidUitschrijven(String activiteitNaam, LocalDate beginDatum, LocalDate eindDatum, String lidEmail) {
        Activiteit tempAct = activiteitRepo.getByNaamAndBeginAndEindDate(activiteitNaam, beginDatum, eindDatum);
        Lid tempLid = lidRepo.getLidByEmail(lidEmail);
        tempAct.lidUitschrijven(tempLid);
    }

    /**
     *
     * @param oefening
     */
    public void addLesmateriaal(Oefening oefening) {
        GenericDaoJpa.startTransaction();
        if (!oefeningen.contains(oefening)) {
            if (oefeningRepo.get(oefening.getId()) == null) {
                this.oefeningen.add(oefening);
                oefeningRepo.insert(oefening);
            }
        }
        GenericDaoJpa.commitTransaction();
    }

    public void wijzigLesmateriaal(Oefening newValue, Oefening oldValue) {
        Oefening origin = oldValue;
        GenericDaoJpa.startTransaction();
        origin.mergeOefening(newValue);
        GenericDaoJpa.commitTransaction();
    }

    private void setOefeningRepo(OefeningDao oefeningRepo) {
        this.oefeningRepo = oefeningRepo;
    }

    private void setActiviteitDao(ActiviteitDao actRepo) {
        this.activiteitRepo = actRepo;
    }

    public void verwijderLesMateriaal(long id) {
        GenericDaoJpa.startTransaction();
        Oefening oef = oefeningRepo.get(id);
        this.oefeningRepo.delete(oef);
        this.oefeningen.remove(oef);
        GenericDaoJpa.commitTransaction();
    }

    Activiteit getActiviteit(long id) {
        return activiteitRepo.get(id);
    }

//    public void filter(String voornaamFilter, String familienaamFilter, String graadFilter, String typeFilter) {
//        Predicate predicate = PredicateFactory.makePredicate(SorteerType.LID, Arrays.asList(voornaamFilter, familienaamFilter, graadFilter, typeFilter));
//        filteredLeden.setPredicate(predicate);
//    }
    public <T extends Exportable> List<T> maakOverzichtList(SorteerType type, List<String> extraParameters) {
        Predicate predicate;
        switch (type) {
            case AANWEZIGHEID:
                predicate = PredicateFactory.makePredicate(SorteerType.AANWEZIGHEID, extraParameters);
                filteredLeden.setPredicate(predicate);
                return (List<T>) filteredLeden;
            case INSCHRIJVING:
                predicate = PredicateFactory.makePredicate(SorteerType.INSCHRIJVING, extraParameters);
                filteredLeden.setPredicate(predicate);
                return (List<T>) filteredLeden;
            case ACTIVITEIT:
                predicate = PredicateFactory.makePredicate(SorteerType.ACTIVITEIT, extraParameters);
                filteredActiviteiten.setPredicate(predicate);
                return (List<T>) filteredActiviteiten;
            case CLUBKAMPIOENSCHAP:
                predicate = PredicateFactory.makePredicate(SorteerType.CLUBKAMPIOENSCHAP, extraParameters);
                filteredKampioenschappen.setPredicate(predicate);
                return (List<T>) filteredKampioenschappen;
            case LESMATERIAAL:
                predicate = PredicateFactory.makePredicate(SorteerType.LESMATERIAAL, extraParameters);
                filteredOefeningen.setPredicate(predicate);
                return (List<T>) filteredOefeningen;
            case LID:
                predicate = PredicateFactory.makePredicate(SorteerType.LID, extraParameters);
                filteredLeden.setPredicate(predicate);
                //return (List<T>) filteredLeden;
                return null;
            default:
                return null;
        }
    }

    public void setCurrentLid(ILid lid) {
        int plaats = leden.indexOf(lid);
        Lid nieuwLid = null;

        if (plaats != -1) {
            nieuwLid = leden.get(plaats);
        }
        subject.firePropertyChange("currentLid", currentLid, nieuwLid);
        // currentLid = (Lid) lid;
        currentLid = nieuwLid;

    }

    public Lid getCurrentLid() {
        return currentLid;
    }

    public void setCurrentActiviteit(IActiviteit activiteit) {
        int plaats = activiteiten.indexOf(activiteit);
        Activiteit nieuweActiviteit = null;

        if (plaats != -1) {
            nieuweActiviteit = activiteiten.get(plaats);
        }
        subjectAct.firePropertyChange("currentActiviteit", currentActiviteit, nieuweActiviteit);
        // currentLid = (Lid) lid;
        currentActiviteit = nieuweActiviteit;
    }

    public IActiviteit getCurrentActiviteit() {
        return currentActiviteit;
    }

    public boolean verwijderCurrentActiviteit() {
        GenericDaoJpa.startTransaction();
        this.activiteitRepo.delete(currentActiviteit);
        GenericDaoJpa.commitTransaction();
        return this.activiteiten.remove(currentActiviteit);
    }

    public boolean wijzigActiviteit(String naam, LocalDate beginDatum, LocalDate eindDatum, int maxAanwezigen, ActiviteitType type) {
        GenericDaoJpa.startTransaction();
        currentActiviteit.wijzigActiviteit(naam, beginDatum, eindDatum, maxAanwezigen, type);
        activiteitRepo.update(currentActiviteit);
        GenericDaoJpa.commitTransaction();
        return true;
    }

    public IActiviteit getActiviteitByNaamAndBeginAndEinddate(String activiteitNaam, LocalDate beginDatum, LocalDate eindDatum) {
        return activiteitRepo.getByNaamAndBeginAndEindDate(activiteitNaam, beginDatum, eindDatum);
    }

    public void lidInschrijvenClubkampioenschap(String activiteitNaam, LocalDate date, String lidEmail) {
        Kampioenschap actKamp = kampioenschapRepo.getByNaamAndDate(activiteitNaam, date);
        Lid lidTemp = lidRepo.getLidByEmail(lidEmail);
        actKamp.lidInschrijven(lidTemp);
        kampioenschapRepo.update(actKamp);
    }

    public void lidUitschrijvenClubkampioenschap(String activiteitNaam, LocalDate date, String lidEmail) {
        Kampioenschap tempKamp = kampioenschapRepo.getByNaamAndDate(activiteitNaam, date);
        Lid tempLid = lidRepo.getLidByEmail(lidEmail);
        tempKamp.lidUitschrijven(tempLid);
    }

    public IOefening getCurrent_oefening() {
        return current_oefening;
    }

    public void setCurrent_oefening(IOefening current_oefening) {
        this.current_oefening = current_oefening;
        subjectOef.firePropertyChange("currentOefening", null, current_oefening);
    }

    public void addPropertyChangeListenerOefening(PropertyChangeListener pcl) {
        subjectOef.addPropertyChangeListener(pcl);
        pcl.propertyChange(new PropertyChangeEvent(pcl, "currentOefening", null, current_oefening));
    }

    public void addPropertyChangeListenerActiviteit(PropertyChangeListener pcl) {
        subjectAct.addPropertyChangeListener(pcl);
        pcl.propertyChange(new PropertyChangeEvent(pcl, "currentActiviteit", null, currentActiviteit));
    }
    
}
