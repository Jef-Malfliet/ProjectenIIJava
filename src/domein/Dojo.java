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
import static util.Validatie.isNull;

public class Dojo {

    private final RolType type;
    private final ObservableList<Lid> leden;
    private final ObservableList<Activiteit> activiteiten;
    private final ObservableList<Oefening> oefeningen;
    private final ObservableList<Kampioenschap> kampioenschappen;
    private final Comparator<Lid> opVoornaam = (lid1, lid2) -> lid1.getVoornaam().compareToIgnoreCase(lid2.getVoornaam());
    private final Comparator<Lid> opType = (lid1, lid2) -> lid1.getType().compareTo(lid2.getType());
    private final Comparator<Lid> opGraad = (lid1, lid2) -> lid1.getGraad().compareTo(lid2.getGraad());
    private final Comparator<Lid> sortOrder = opVoornaam.thenComparing(opGraad).thenComparing(opType);
    private final Comparator<Oefening> opOefNaam = (oef1,oef2)->oef1.getNaam().compareToIgnoreCase(oef2.getNaam());
    private final Comparator<Oefening> sortOefeningOrder = opOefNaam;
    private final FilteredList<Lid> filtered;
    private final SortedList<Lid> sorted;
    private final SortedList<Oefening> sortedOefeningen;
    private PropertyChangeSupport subject;
    private LidDao lidRepo;
    private OefeningDao oefeningRepo;
    private ActiviteitDao activiteitRepo;
    private KampioenschapDao kampioenschapRepo;
    private final List<Overzicht<Object>> overzichtList;
    private int current_Lid = -1;

    private List<String> headers = new ArrayList<>();

    private int current_Activiteit = -1;
    private final FilteredList<Oefening> filteredOefeningen;

    public Dojo(LidDao lidRepo, OefeningDao oefeningRepo, ActiviteitDao actRepo, KampioenschapDao kampioenschapDao) {
        setLidRepo(lidRepo);
        setOefeningRepo(oefeningRepo);
        setActiviteitDao(actRepo);
        setKampioenschapRepo(kampioenschapDao);
        this.type = RolType.BEHEERDER;
        leden = FXCollections.observableArrayList(this.lidRepo.findAll());
        activiteiten = FXCollections.observableArrayList(this.activiteitRepo.findAll());
        filtered = new FilteredList<>(leden, (p) -> true);
        sorted = new SortedList<>(filtered, sortOrder);
        subject = new PropertyChangeSupport(this);
        overzichtList = new ArrayList();
        oefeningen = FXCollections.observableArrayList(oefeningRepo.findAll());
        filteredOefeningen = new FilteredList<>(oefeningen, (p)->true);
        sortedOefeningen = new SortedList<>(filteredOefeningen, sortOefeningOrder);
        kampioenschappen = FXCollections.observableArrayList();
    }

    /**
     *
     * @param lid
     */
    public boolean verwijderCurrentLid() {
        Lid currentLid = current_Lid != -1 ? leden.get(current_Lid) : null;
        GenericDaoJpa.startTransaction();
        this.lidRepo.delete(currentLid);
        GenericDaoJpa.commitTransaction();
        return this.leden.remove(currentLid);
    }

    /**
     *
     * @param lid
     */
    public boolean wijzigLid(String voornaam, String familienaam, String wachtwoord, String gsm, String telefoon_vast, String straatnaam, String huisnummer, String busnummer, String postcode, String stad, Land land, String rijksregisternummer, String email, String email_ouders, LocalDate geboortedatum, LocalDate inschrijvingsdatum, List<LocalDate> aanwezigheden, Geslacht geslacht, Graad graad, RolType type, LesType lessen) {
        GenericDaoJpa.startTransaction();
        //Lid temp = lidRepo.get(id);
        Lid currentLid = current_Lid != -1 ? leden.get(current_Lid) : null;

        currentLid.wijzigLid(voornaam, familienaam, wachtwoord, gsm, telefoon_vast, straatnaam, huisnummer, busnummer, postcode, stad, land, rijksregisternummer, email, email_ouders, geboortedatum, inschrijvingsdatum, aanwezigheden, geslacht, graad, type, lessen);
        leden.set(current_Lid, currentLid);
//ILid temp = lidRepo.update(lid);
        lidRepo.update(currentLid);
        GenericDaoJpa.commitTransaction();
        // subject.firePropertyChange("lijstleden", null, leden);
//        if (temp == null) {
//            return false;
//        }
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
                subject.firePropertyChange("lijstactiviteiten", null, activiteiten);
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
        fillSimplePropertiesLidForGui();
        return sorted;
    }

    public FilteredList getFilteredLeden() {
        return filtered;
    }

    private void fillSimplePropertiesLidForGui() {
        leden.forEach(Lid::fillSimpleProperties);
    }

    private void fillSimplePropertiesActiviteitForGui() {
        activiteiten.forEach(Activiteit::fillSimpleProperties);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        subject.addPropertyChangeListener(pcl);
        pcl.propertyChange(new PropertyChangeEvent(pcl, "lijstOefeningen", null, oefeningen));
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
        oefeningen.forEach(Oefening::fillSimpleString);
        return sortedOefeningen;
    }

    public Oefening getOefeningById(Long id) {
        return oefeningRepo.get(id);
    }

    public ObservableList<Activiteit> getActiviteitenList() {
        fillSimplePropertiesActiviteitForGui();
        return activiteiten;
    }

    public void lidInschrijven(String activiteitNaam, LocalDate beginDatum, LocalDate eindDatum, String lidEmail) {
        Activiteit actTemp = activiteitRepo.getByNaamAndBeginAndEindDate(activiteitNaam, beginDatum, eindDatum);
        Lid lidTemp = lidRepo.getLidByEmail(lidEmail);
        actTemp.lidInschrijven(lidTemp);
        activiteitRepo.update(actTemp);
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
                subject.firePropertyChange("lijstOefeningen", null, oefeningen);
            }
        }
        GenericDaoJpa.commitTransaction();
    }

    public void wijzigLesmateriaal(Oefening newValue, Oefening oldValue) {
        Oefening origin = oldValue;
        GenericDaoJpa.startTransaction();
        origin.mergeOefening(newValue);
        GenericDaoJpa.commitTransaction();
        subject.firePropertyChange("lijstOefeningen", null, oefeningen);
    }

    private void setOefeningRepo(OefeningDao oefeningRepo) {
        this.oefeningRepo = oefeningRepo;
    }

    public void filter(String voornaamFilter, String familienaamFilter, String graadFilter, String typeFilter) {
        Predicate<Lid> result = lid -> true;
        Predicate<Lid> voornaam = lid -> lid.getVoornaam().toLowerCase().startsWith(voornaamFilter.toLowerCase());
        Predicate<Lid> familienaam = lid -> lid.getFamilienaam().toLowerCase().startsWith(familienaamFilter.toLowerCase());
        Predicate<Lid> graad = lid -> lid.getGraad().toString().toLowerCase().startsWith(graadFilter.toLowerCase());
        Predicate<Lid> lidType = lid -> lid.getType().toString().toLowerCase().startsWith(typeFilter.toLowerCase());

        if (notEmpty(voornaamFilter)) {
            result = result.and(voornaam);
        }

        if (notEmpty(familienaamFilter)) {
            result = result.and(familienaam);
        }

        if (notEmpty(graadFilter)) {
            result = result.and(graad);
        }

        if (notEmpty(typeFilter)) {
            result = result.and(lidType);
        }

        filtered.setPredicate(result);
    }

    public void filterOefening(String naamFilter, String graadFilter) {
        Predicate<Oefening> result = oefening -> true;
        Predicate<Oefening> naam = oefening -> oefening.getNaam().toLowerCase().startsWith(naamFilter.toLowerCase());
        Predicate<Oefening> graad = oefening -> oefening.getGraad().toString().toLowerCase().startsWith(graadFilter.toLowerCase());
        
        if(notEmpty(naamFilter)){
            result = result.and(naam);
        }
        if(notEmpty(graadFilter)){
            result = result.and(graad);
        }
        
        filteredOefeningen.setPredicate(result);
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
        subject.firePropertyChange("lijstOefeningen", null, oefeningen);
    }

    Activiteit getActiviteit(long id) {
        return activiteitRepo.get(id);
    }

    private boolean notEmpty(String value) {
        return !(value == null || value.isEmpty());
    }

    public <T extends Exportable> List<T> maakOverzichtList(OverzichtType type, List<Object> extraParameters) {
        FilteredList overzicht;

        switch (type) {
            case AANWEZIGHEID:
                overzicht = new FilteredList<>(leden, (p) -> true);
                LocalDate aanwezigheidDatum = (LocalDate) extraParameters.get(0);
                String lidVoornaam = extraParameters.get(1).toString();
                LesType lidFormule = (LesType) extraParameters.get(2);
                Predicate<Lid> aanwResult = lid -> true;
                Predicate<Lid> onAanwezigheid = lid -> lid.getAanwezigheden().contains(aanwezigheidDatum);
                Predicate<Lid> onNameA = lid -> lid.getVoornaam().toLowerCase().startsWith(lidVoornaam.toLowerCase());
                Predicate<Lid> onFormuleA = lid -> lid.getLessen().equals(lidFormule);

                if (aanwezigheidDatum != null) {
                    aanwResult = aanwResult.and(onAanwezigheid);
                }

                if (notEmpty(lidVoornaam)) {
                    aanwResult = aanwResult.and(onNameA);
                }

                if (!isNull(onFormuleA)) {
                    if (lidFormule != LesType.ALLES) {
                        aanwResult = aanwResult.and(onFormuleA);
                    }
                }
                overzicht.setPredicate(aanwResult);
                return overzicht;
            case INSCHRIJVING:
                overzicht = new FilteredList<>(leden, (p) -> true);
                LocalDate inschrijvingdatum = (LocalDate) extraParameters.get(0);
                String voornaam = extraParameters.get(1).toString();
                LesType formule = (LesType) extraParameters.get(2);
                Predicate<Lid> inschResult = lid -> true;
                Predicate<Lid> onInschrijving = lid -> lid.getInschrijvingsdatum().equals(inschrijvingdatum);
                Predicate<Lid> onName = lid -> lid.getVoornaam().toLowerCase().startsWith(voornaam.toLowerCase());
                Predicate<Lid> onFormule = lid -> lid.getLessen().equals(formule);

                if (!isNull(inschrijvingdatum)) {
                    inschResult = inschResult.and(onInschrijving);
                }

                if (notEmpty(voornaam)) {
                    inschResult = inschResult.and(onName);
                }

                if (!isNull(formule)) {
                    if (formule != LesType.ALLES) {
                        inschResult = inschResult.and(onFormule);
                    }
                }
                overzicht.setPredicate(inschResult);
                return overzicht;
            case ACTIVITEIT:
                overzicht = new FilteredList(activiteiten, p -> true);
                boolean stage = (boolean) extraParameters.get(0);
                String aNaam = extraParameters.get(1).toString();
                Predicate<Activiteit> actResult = a -> true;
                Predicate<Activiteit> isStage = a -> a.isStage();
                Predicate<Activiteit> onAName = a -> a.getNaam().toLowerCase().startsWith(aNaam.toLowerCase());

                if (stage) {
                    actResult = actResult.and(isStage);
                }
                if (notEmpty(aNaam)) {
                    actResult = actResult.and(onAName);
                }
                overzicht.setPredicate(actResult);
                return overzicht;
            case CLUBKAMPIOENSCHAP:
                overzicht = new FilteredList(kampioenschappen, p -> true);
                return overzicht;
            case LESMATERIAAL:
                overzicht = new FilteredList(oefeningen, p -> true);
                Graad graad = (Graad) extraParameters.get(0);
                Predicate<Oefening> oefResult = o -> true;
                Predicate<Oefening> onGraad = o -> o.getGraad().equals(graad);

                if (graad != null) {
                    oefResult = oefResult.and(onGraad);
                }
                overzicht.setPredicate(oefResult);
                return overzicht;
            default:
                return null;
        }
    }

    public void setCurrentLid(ILid lid) {
        current_Lid = leden.indexOf(lid);
    }

    public ILid getCurrentLid() {
        if (current_Lid != -1) {
            return leden.get(current_Lid);
        }
        return null;
    }

    public void setCurrentActiviteit(IActiviteit activiteit) {
        current_Activiteit = activiteiten.indexOf(activiteit);
    }

    public IActiviteit getCurrentActiviteit() {
        if (current_Activiteit != -1) {
            return activiteiten.get(current_Activiteit);
        }
        return null;
    }

    public boolean verwijderCurrentActiviteit() {
        Activiteit currentActiviteit = current_Activiteit != -1 ? activiteiten.get(current_Activiteit) : null;
        GenericDaoJpa.startTransaction();
        this.activiteitRepo.delete(currentActiviteit);
        GenericDaoJpa.commitTransaction();
        return this.activiteiten.remove(currentActiviteit);
    }

    public boolean wijzigActiviteit(String naam, LocalDate beginDatum, LocalDate eindDatum, boolean isStage, int maxAanwezigen) {
        GenericDaoJpa.startTransaction();
        Activiteit currentActiviteit = current_Activiteit != -1 ? activiteiten.get(current_Activiteit) : null;
        currentActiviteit.wijzigActiviteit(naam, beginDatum, eindDatum, isStage, maxAanwezigen);
        this.activiteiten.set(current_Activiteit, currentActiviteit);
        this.activiteitRepo.update(currentActiviteit);
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

}
