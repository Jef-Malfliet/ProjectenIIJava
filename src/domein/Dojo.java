package domein;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import persistentie.ExportFiles;
//import persistentie.IPersistentieController;
import persistentie.LidDao;
import java.util.*;

public class Dojo {

    private final RolType type;
    private final ObservableList<Lid> leden;
    private final Comparator<Lid> opVoornaam = (lid1, lid2) -> lid1.getVoornaam().compareToIgnoreCase(lid2.getVoornaam());
    private final Comparator<Lid> opType = (lid1, lid2) -> lid1.getType().compareTo(lid2.getType());
    private final Comparator<Lid> opGraad = (lid1, lid2) -> lid1.getGraad().compareTo(lid2.getGraad());
    private final Comparator<Lid> sortOrder = opVoornaam.thenComparing(opGraad).thenComparing(opType);
    private final FilteredList<Lid> filtered;
    private final SortedList<Lid> sorted;
    private PropertyChangeSupport subject;
    private LidDao lidRepo;
    private final List<Overzicht> overzichtList;
    private List<Kampioenschap> kampioenschappen;
    private List<Activiteit> activiteiten;
	private Collection<Oefening> oefeningen;

    public Dojo(LidDao lidRepo) {
        setLidRepo(lidRepo);
        this.type = RolType.BEHEERDER;
        leden = FXCollections.observableArrayList(this.lidRepo.findAll());
        filtered = new FilteredList<>(leden, (p) -> true);
        sorted = new SortedList<>(filtered, sortOrder);
        subject = new PropertyChangeSupport(this);
        overzichtList = new ArrayList();
    }

    /**
     *
     * @param lid
     */
    public boolean verwijderLid(Lid lid) {

        this.lidRepo.delete(lid);
        return this.leden.remove(lid);
    }

    /**
     *
     * @param lid
     */
    public boolean wijzigLid(Lid lid) {

        Lid temp = lidRepo.update(lid);
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
    public Lid toonLid(long id) {
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

    public List<Lid> getLijstLeden() {
        return leden;
    }

    public RolType getType() {
        return type;
    }

    public ObservableList<Lid> getSortedLeden() {
        fillSimplePropertiesForGui();
        return sorted;
    }

    public FilteredList getFilteredLeden() {
        return filtered;
    }

    private void fillSimplePropertiesForGui() {
        leden.forEach(lid -> lid.fillSimpleProperties());
    }

    public void addPropertyChangeListener(PropertyChangeListener pc1) {
        subject.addPropertyChangeListener(pc1);
        pc1.propertyChange(new PropertyChangeEvent(pc1, "lijstleden", null, leden));

    }

    public void removePropertyChangeListener(PropertyChangeListener pc1) {
        subject.removePropertyChangeListener(pc1);

    }

    public void setLidRepo(LidDao mock) {
        lidRepo = mock;
    }

    /**
     * @param start
     * @param einde
     * @param optie
     */
    public void filter(SorteerType optie, String start) {
        filtered.setPredicate(lid -> {
            if (optie == null) {
                return true;
            }

            switch (optie) {
                case VOORNAAM:
                    return lid.getVoornaam().compareToIgnoreCase(start) >= 0;
                case ACHTERNAAM:
                    return lid.getFamilienaam().compareToIgnoreCase(start) >= 0;
                case GRAAD:
                    return lid.getGraad().toString().toLowerCase().startsWith(start.toLowerCase());
                case TYPE:
                    return lid.getType().toString().toLowerCase().startsWith(start.toLowerCase());
                default:
                    return true;
            }
        });
    }

    public void maakOverzicht(OverzichtType type, String path) {
        ExportFiles.toExcel(leden, 25, 20, path);
    }

    public List<Overzicht> getOverzichtList() {
        return overzichtList;
    }

	public List<Oefening> getOefeningen() {
		// TODO - implement Dojo.toonOefeningen
		throw new UnsupportedOperationException();
	}

}
