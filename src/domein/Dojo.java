package domein;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import persistentie.ActiviteitDao;
import persistentie.ExportFiles;
//import persistentie.IPersistentieController;
import persistentie.LidDao;
import persistentie.GenericDaoJpa;
import persistentie.OefeningDao;

public class Dojo {

    private final RolType type;
    private final ObservableList<ILid> leden;
    private final ObservableList<IActiviteit> activiteiten;
    private final ObservableList<IOefening> oefeningen;
    private final Comparator<ILid> opVoornaam = (lid1, lid2) -> lid1.getVoornaam().compareToIgnoreCase(lid2.getVoornaam());
    private final Comparator<ILid> opType = (lid1, lid2) -> lid1.getType().compareTo(lid2.getType());
    private final Comparator<ILid> opGraad = (lid1, lid2) -> lid1.getGraad().compareTo(lid2.getGraad());
    private final Comparator<ILid> sortOrder = opVoornaam.thenComparing(opGraad).thenComparing(opType);
    private final FilteredList<ILid> filtered;
    private final SortedList<ILid> sorted;
    private PropertyChangeSupport subject;
    private LidDao lidRepo;
    private OefeningDao oefeningRepo;
    private ActiviteitDao activiteitRepo;
    private final List<Overzicht> overzichtList;
    private List<Kampioenschap> kampioenschappen;

    public Dojo(LidDao lidRepo, OefeningDao oefeningRepo, ActiviteitDao actRepo) {
        setLidRepo(lidRepo);
        setOefeningRepo(oefeningRepo);
        setActiviteitDao(actRepo);
        this.type = RolType.BEHEERDER;
        leden = FXCollections.observableArrayList(this.lidRepo.findAll());
        activiteiten = FXCollections.observableArrayList(this.activiteitRepo.findAll());
        filtered = new FilteredList<>(leden, (p) -> true);
        sorted = new SortedList<>(filtered, sortOrder);
        subject = new PropertyChangeSupport(this);
        overzichtList = new ArrayList();
        oefeningen = FXCollections.observableArrayList(oefeningRepo.findAll());
    }

    /**
     *
     * @param lid
     */
    public boolean verwijderLid(long id) {
        Lid lid = lidRepo.get(id);
        this.lidRepo.delete(lid);
        return this.leden.remove(lid);
    }

    /**
     *
     * @param lid
     */
    public boolean wijzigLid(long id, String voornaam, String familienaam, String wachtwoord, String gsm, String telefoon_vast, String straatnaam, String huisnummer, String busnummer, String postcode, String stad, String land, String rijksregisternummer, String email, String email_ouders, LocalDate geboortedatum, LocalDate inschrijvingsdatum, List<LocalDate> aanwezigheden, Geslacht geslacht, Graad graad, RolType type, LesType lessen) {
        Lid temp = lidRepo.get(id);
        temp.wijzigLid(voornaam, familienaam, wachtwoord, gsm, telefoon_vast, straatnaam, huisnummer, busnummer, postcode, stad, land, rijksregisternummer, email, email_ouders, geboortedatum, inschrijvingsdatum, aanwezigheden, geslacht, graad, type, lessen);
        //ILid temp = lidRepo.update(lid);
        lidRepo.update(temp);
        subject.firePropertyChange("lijstleden", null, leden);
        if (temp == null) {
            return false;
        }
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

                lidRepo.insert(lid);

                leden.add(lid);
                subject.firePropertyChange("lijstleden", null, leden);
                return true;
            }
        }
        return false;
    }

    public boolean voegActiviteitToe(Activiteit activiteit) {

        if (!activiteiten.contains(activiteit)) {
            if (activiteitRepo.get(activiteit.getId()) == null) {

                activiteitRepo.insert(activiteit);

                activiteiten.add(activiteit);
                subject.firePropertyChange("lijstactiviteiten", null, activiteiten);
                return true;
            }
        }
        return false;
    }

    public List<ILid> getLijstLeden() {
        return leden;
    }

    public RolType getType() {
        return type;
    }

    public ObservableList<ILid> getSortedLeden() {
        fillSimplePropertiesLidForGui();
        return sorted;
    }

    public FilteredList getFilteredLeden() {
        return filtered;
    }

    private void fillSimplePropertiesLidForGui() {
        leden.forEach(lid -> ((Lid) lid).fillSimpleProperties());
    }

    private void fillSimplePropertiesActiviteitForGui() {
        activiteiten.forEach(act -> ((Activiteit) act).fillSimpleProperties());
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        subject.addPropertyChangeListener(pcl);
        pcl.propertyChange(new PropertyChangeEvent(pcl, "lijstleden", null, leden));
        pcl.propertyChange(new PropertyChangeEvent(pcl, "lijstOefeningen", null, oefeningen));

    }

    public void removePropertyChangeListener(PropertyChangeListener pc1) {
        subject.removePropertyChangeListener(pc1);

    }

    public void setLidRepo(LidDao mock) {
        lidRepo = mock;
    }

    public void maakOverzicht(OverzichtType type, String besNaam, String path, List<Object> extraParameters) {
        ExportFiles.toExcel(leden, 25, 20, path);
    }

    public List<Overzicht> getOverzichtList() {
        return overzichtList;
    }

    public ObservableList<IOefening> getOefeningen() {
        oefeningen.forEach(oef -> ((Oefening) oef).fillSimpleString());
        return oefeningen;
    }

    public Oefening getOefeningById(Long id) {
        return oefeningRepo.get(id);
    }

    public ObservableList<IActiviteit> getActiviteitenList() {
        fillSimplePropertiesActiviteitForGui();
        return activiteiten;
    }

    public void lidInschrijven(Activiteit activiteit, Lid lid) {
        activiteit.lidInschrijven(lid);
    }

    public void lidUitschrijven(long activiteitId, long lidId) {
        Activiteit tempAct = activiteitRepo.get(lidId);
        ILid tempLid = leden.stream().filter(l -> l.getId() == lidId).findFirst().orElse(null);
        tempAct.lidUitschrijven((Lid) tempLid);
    }

    /**
     *
     * @param oefening
     */
    public void addOefening(Oefening oefening) {
        if (!oefeningen.contains(oefening)) {
            if (oefeningRepo.get(oefening.getId()) == null) {
                this.oefeningen.add(oefening);
                oefeningRepo.insert(oefening);
                subject.firePropertyChange("lijstOefeningen", null, oefeningen);
            }
        }
    }

    public void wijzigOefening(Oefening oefening, long id) {
        System.out.println(id+"DOJO");
        Oefening temp = oefeningRepo.getOefeningById(id);
        System.out.println(temp.getId()+"TEMP ZIJN ID");
        System.out.println("temp ontvangen");
        temp.wijzigOefening(oefening);
        System.out.println("temp.wijzigoef");
        oefeningRepo.update(temp);
        System.out.println("gemerged");
        subject.firePropertyChange("lijstOefeningen", null, oefeningen);
    }

    private void setOefeningRepo(OefeningDao oefeningRepo) {
        this.oefeningRepo = oefeningRepo;
    }

    /**
     *
     * @param lidRepo
     */
    public Dojo(LidDao lidRepo) {
        // TODO - implement Dojo.Dojo
        throw new UnsupportedOperationException();
    }

    public void filter(String voornaamFilter, String familienaamFilter, String graadFilter, String typeFilter) {
        filtered.setPredicate(lid -> {
            //4 van de 4 null
            if ((voornaamFilter == null || voornaamFilter.isEmpty()) && (familienaamFilter == null || familienaamFilter.isEmpty())
                    && (graadFilter == null || graadFilter.isEmpty()) && (typeFilter == null || typeFilter.isEmpty())) {
                return true;
            }

            //1 van de 1, rest null
            if (!(voornaamFilter == null || voornaamFilter.isEmpty()) && (familienaamFilter == null || familienaamFilter.isEmpty())
                    && (graadFilter == null || graadFilter.isEmpty()) && (typeFilter == null || typeFilter.isEmpty())) {
                return lid.getVoornaam().compareToIgnoreCase(voornaamFilter) >= 0;
            }

            if ((voornaamFilter == null || voornaamFilter.isEmpty()) && !(familienaamFilter == null || familienaamFilter.isEmpty())
                    && (graadFilter == null || graadFilter.isEmpty()) && (typeFilter == null || typeFilter.isEmpty())) {
                return lid.getFamilienaam().compareToIgnoreCase(familienaamFilter) >= 0;
            }

            if ((voornaamFilter == null || voornaamFilter.isEmpty()) && (familienaamFilter == null || familienaamFilter.isEmpty())
                    && !(graadFilter == null || graadFilter.isEmpty()) && (typeFilter == null || typeFilter.isEmpty())) {
                //return lid.getGraad().toString().compareToIgnoreCase(graadFilter) >= 0;
                return lid.getGraad().toString().toLowerCase().startsWith(graadFilter.toLowerCase());
            }

            if ((voornaamFilter == null || voornaamFilter.isEmpty()) && (familienaamFilter == null || familienaamFilter.isEmpty())
                    && (graadFilter == null || graadFilter.isEmpty()) && !(typeFilter == null || typeFilter.isEmpty())) {
                //return lid.getType().toString().compareToIgnoreCase(typeFilter) >= 0;
                return lid.getType().toString().toLowerCase().startsWith(typeFilter.toLowerCase());
            }

            //2 van de 4, de rest null
            if (!(voornaamFilter == null || voornaamFilter.isEmpty()) && !(familienaamFilter == null || familienaamFilter.isEmpty())
                    && (graadFilter == null || graadFilter.isEmpty()) && (typeFilter == null || typeFilter.isEmpty())) {
                return lid.getVoornaam().compareToIgnoreCase(voornaamFilter) >= 0 && lid.getFamilienaam().compareToIgnoreCase(familienaamFilter) >= 0;
            }

            if (!(voornaamFilter == null || voornaamFilter.isEmpty()) && (familienaamFilter == null || familienaamFilter.isEmpty())
                    && !(graadFilter == null || graadFilter.isEmpty()) && (typeFilter == null || typeFilter.isEmpty())) {
                return lid.getVoornaam().compareToIgnoreCase(voornaamFilter) >= 0 && lid.getGraad().toString().toLowerCase().startsWith(graadFilter.toLowerCase());
            }

            if (!(voornaamFilter == null || voornaamFilter.isEmpty()) && (familienaamFilter == null || familienaamFilter.isEmpty())
                    && (graadFilter == null || graadFilter.isEmpty()) && !(typeFilter == null || typeFilter.isEmpty())) {
                return lid.getVoornaam().compareToIgnoreCase(voornaamFilter) >= 0 && lid.getType().toString().toLowerCase().startsWith(typeFilter.toLowerCase());
            }

            if ((voornaamFilter == null || voornaamFilter.isEmpty()) && !(familienaamFilter == null || familienaamFilter.isEmpty())
                    && !(graadFilter == null || graadFilter.isEmpty()) && (typeFilter == null || typeFilter.isEmpty())) {
                return lid.getFamilienaam().compareToIgnoreCase(familienaamFilter) >= 0 && lid.getGraad().toString().toLowerCase().startsWith(graadFilter.toLowerCase());
            }

            if ((voornaamFilter == null || voornaamFilter.isEmpty()) && !(familienaamFilter == null || familienaamFilter.isEmpty())
                    && (graadFilter == null || graadFilter.isEmpty()) && !(typeFilter == null || typeFilter.isEmpty())) {
                return lid.getFamilienaam().compareToIgnoreCase(familienaamFilter) >= 0 && lid.getType().toString().toLowerCase().startsWith(typeFilter.toLowerCase());
            }

            if ((voornaamFilter == null || voornaamFilter.isEmpty()) && (familienaamFilter == null || familienaamFilter.isEmpty())
                    && !(graadFilter == null || graadFilter.isEmpty()) && !(typeFilter == null || typeFilter.isEmpty())) {
                return lid.getGraad().toString().toLowerCase().startsWith(graadFilter.toLowerCase()) && lid.getType().toString().toLowerCase().startsWith(typeFilter.toLowerCase());
            }

            //3 van de 4, de rest null
            if (!(voornaamFilter == null || voornaamFilter.isEmpty()) && !(familienaamFilter == null || familienaamFilter.isEmpty())
                    && !(graadFilter == null || graadFilter.isEmpty()) && (typeFilter == null || typeFilter.isEmpty())) {
                return lid.getVoornaam().compareToIgnoreCase(voornaamFilter) >= 0 && lid.getFamilienaam().compareToIgnoreCase(familienaamFilter) >= 0
                        && lid.getGraad().toString().toLowerCase().startsWith(graadFilter.toLowerCase());
            }

            if (!(voornaamFilter == null || voornaamFilter.isEmpty()) && !(familienaamFilter == null || familienaamFilter.isEmpty())
                    && (graadFilter == null || graadFilter.isEmpty()) && !(typeFilter == null || typeFilter.isEmpty())) {
                return lid.getVoornaam().compareToIgnoreCase(voornaamFilter) >= 0 && lid.getFamilienaam().compareToIgnoreCase(familienaamFilter) >= 0
                        && lid.getType().toString().toLowerCase().startsWith(typeFilter.toLowerCase());
            }

            if (!(voornaamFilter == null || voornaamFilter.isEmpty()) && (familienaamFilter == null || familienaamFilter.isEmpty())
                    && !(graadFilter == null || graadFilter.isEmpty()) && !(typeFilter == null || typeFilter.isEmpty())) {
                return lid.getVoornaam().compareToIgnoreCase(voornaamFilter) >= 0 && lid.getGraad().toString().toLowerCase().startsWith(graadFilter.toLowerCase())
                        && lid.getType().toString().toLowerCase().startsWith(typeFilter.toLowerCase());
            }

            if ((voornaamFilter == null || voornaamFilter.isEmpty()) && !(familienaamFilter == null || familienaamFilter.isEmpty())
                    && !(graadFilter == null || graadFilter.isEmpty()) && !(typeFilter == null || typeFilter.isEmpty())) {
                return lid.getFamilienaam().compareToIgnoreCase(familienaamFilter) >= 0 && lid.getGraad().toString().toLowerCase().startsWith(graadFilter.toLowerCase())
                        && lid.getType().toString().toLowerCase().startsWith(typeFilter.toLowerCase());
            } //allemaal
            else {
                return lid.getVoornaam().compareToIgnoreCase(voornaamFilter) >= 0 && lid.getFamilienaam().compareToIgnoreCase(familienaamFilter) >= 0
                        && lid.getGraad().toString().toLowerCase().startsWith(graadFilter.toLowerCase()) && lid.getType().toString().toLowerCase().startsWith(typeFilter.toLowerCase());
            }
        });
    }

    private void setActiviteitDao(ActiviteitDao actRepo) {
        this.activiteitRepo = actRepo;
    }

    public void verwijderLesMateriaal(long id) {
        Oefening oef = oefeningRepo.get(id);
        System.out.println(id+"GAT VERWIJDEREN");
        System.out.println(oef.getId() + " - "+id);
        this.oefeningRepo.delete(oef);
        this.oefeningen.remove(oef);
        System.out.println(id+"DELETED");
        subject.firePropertyChange("lijstOefeningen", null, oefeningen);
    }

    Activiteit getActiviteit(long id) {
        return activiteitRepo.get(id);
    }
}
