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
import persistentie.GenericDaoJpa;
import persistentie.LidDao;
import persistentie.OefeningDao;

public class Dojo {

    private final RolType type;
    private final ObservableList<Lid> leden;
    private final ObservableList<Activiteit> activiteiten;
    private final ObservableList<Oefening> oefeningen;
    private final Comparator<Lid> opVoornaam = (lid1, lid2) -> lid1.getVoornaam().compareToIgnoreCase(lid2.getVoornaam());
    private final Comparator<Lid> opType = (lid1, lid2) -> lid1.getType().compareTo(lid2.getType());
    private final Comparator<Lid> opGraad = (lid1, lid2) -> lid1.getGraad().compareTo(lid2.getGraad());
    private final Comparator<Lid> sortOrder = opVoornaam.thenComparing(opGraad).thenComparing(opType);
    private final FilteredList<Lid> filtered;
    private final SortedList<Lid> sorted;
    private PropertyChangeSupport subject;
    private LidDao lidRepo;
    private OefeningDao oefeningRepo;
    private ActiviteitDao activiteitRepo;
    private final List<Overzicht> overzichtList;
    private List<Kampioenschap> kampioenschappen;

    private int current_Lid = -1;

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
    public boolean verwijderCurrentLid() {
        Lid currentLid = current_Lid != -1?leden.get(current_Lid):null;
        GenericDaoJpa.startTransaction();
        this.lidRepo.delete(currentLid);
        GenericDaoJpa.commitTransaction();
        return this.leden.remove(currentLid);
    }

    /**
     *
     * @param lid
     */
    public boolean wijzigLid(String voornaam, String familienaam, String wachtwoord, String gsm, String telefoon_vast, String straatnaam, String huisnummer, String busnummer, String postcode, String stad, String land, String rijksregisternummer, String email, String email_ouders, LocalDate geboortedatum, LocalDate inschrijvingsdatum, List<LocalDate> aanwezigheden, Geslacht geslacht, Graad graad, RolType type, LesType lessen) {
        GenericDaoJpa.startTransaction();
        //Lid temp = lidRepo.get(id);
        Lid currentLid = current_Lid != -1?leden.get(current_Lid):null;

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

    public void maakOverzicht(List<String> overzicht, String headers, String path) {
        ExportFiles.toExcel(overzicht, headers, 25, 20, path);
    }

    public List<Overzicht> getOverzichtList() {
        return overzichtList;
    }

    public ObservableList<Oefening> getOefeningen() {
        oefeningen.forEach(oef -> oef.fillSimpleString());
        return oefeningen;
    }

    public Oefening getOefeningById(Long id) {
        return oefeningRepo.get(id);
    }

    public ObservableList<Activiteit> getActiviteitenList() {
        fillSimplePropertiesActiviteitForGui();
        return activiteiten;
    }

    public void lidInschrijven(Activiteit activiteit, Lid lid) {
        GenericDaoJpa.startTransaction();
        activiteit.lidInschrijven(lid);
        activiteitRepo.update(activiteit);
        GenericDaoJpa.commitTransaction();

    }

    public void lidUitschrijven(long activiteitId, long lidId) {
        Activiteit tempAct = activiteitRepo.get(lidId);
        Lid tempLid = leden.stream().filter(l -> l.getId() == lidId).findFirst().orElse(null);
        tempAct.lidUitschrijven(tempLid);
    }

    /**
     *
     * @param oefening
     */
    public void addOefening(Oefening oefening) {
        if (!oefeningen.contains(oefening)) {
            if (oefeningRepo.get(oefening.getId()) == null) {
                GenericDaoJpa.startTransaction();
                this.oefeningen.add(oefening);
                oefeningRepo.insert(oefening);
                GenericDaoJpa.commitTransaction();
                subject.firePropertyChange("lijstOefeningen", null, oefeningen);
            }
        }
    }

    public void wijzigOefening(Oefening nieuweWaarden, long id) {
        GenericDaoJpa.startTransaction();
        Oefening origin = oefeningRepo.getOefeningById(id);
        origin.mergeOefening(nieuweWaarden);
        oefeningRepo.update(origin);
        GenericDaoJpa.commitTransaction();

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
        GenericDaoJpa.startTransaction();
        Oefening oef = oefeningRepo.get(id);
        System.out.println(id + "GAT VERWIJDEREN");
        System.out.println(oef.getId() + " - " + id);
        this.oefeningRepo.delete(oef);
        this.oefeningen.remove(oef);
        System.out.println(id + "DELETED");
        subject.firePropertyChange("lijstOefeningen", null, oefeningen);
        GenericDaoJpa.commitTransaction();
    }

    Activiteit getActiviteit(long id) {
        return activiteitRepo.get(id);
    }

    public List<String> maakOverzichtList(OverzichtType type, List<Object> extraParameters) {
        switch (type) {
            case AANWEZIGHEID:
            case ACTIVITEIT:
            case INSCHRIJVING:
            case CLUBKAMPIOENSCHAP:
        }
        return null;
    }

    public void setCurrentLid(ILid lid) {
       
        current_Lid = leden.indexOf(lid);
        
    }

    public ILid getCurrentLid() {
        if(current_Lid != -1)
            return leden.get(current_Lid);
        return null;
    }
}
