package domein;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Comparator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
//import persistentie.IPersistentieController;
import persistentie.LidDao;
import persistentie.LidDaoJpa;

public class Dojo {

    private List<Lid> lijstLeden;
    private final Type type;
    private final ObservableList<Lid> leden;
    private final Comparator<Lid> opVoornaam = (lid1, lid2) -> lid1.getVoornaam().compareToIgnoreCase(lid2.getVoornaam());
    private final Comparator<Lid> opType = (lid1, lid2) -> lid1.getType().compareTo(lid2.getType());
    private final Comparator<Lid> opGraad = (lid1, lid2) -> lid1.getGraad().compareTo(lid2.getGraad());
    private final Comparator<Lid> sortOrder = opVoornaam.thenComparing(opGraad).thenComparing(opType);
    private final FilteredList<Lid> filtered;
    private final SortedList<Lid> sorted;
    private PropertyChangeSupport subject;
    private LidDao lidRepo;

    public Dojo(LidDao lidRepo) {
        setLidRepo(lidRepo);
        lijstLeden = this.lidRepo.findAll();
        this.type = Type.BEHEERDER;
        leden = FXCollections.observableArrayList(lijstLeden);
        filtered = new FilteredList<>(leden, (p) -> true);
        sorted = new SortedList<>(filtered, sortOrder);
        subject = new PropertyChangeSupport(this);
    }

    /**
     *
     * @param lid
     */
    public boolean verwijderLid(Lid lid) {
     
        this.lidRepo.delete(lid);
        return this.lijstLeden.remove(lid);
    }

    /**
     *
     * @param lid
     */
    public boolean wijzigLid(Lid lid) {
     
        Lid temp = lidRepo.update(lid);
        
        lijstLeden = lidRepo.findAll();
        subject.firePropertyChange("lijstleden", null, lijstLeden);
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
        return lijstLeden.stream().filter(l -> l.getId() == id).findFirst().orElse(null);
    }

    /**
     *
     * @param lid
     */
    public boolean voegLidToe(Lid lid) {
      
        if (!lijstLeden.contains(lid)) {
            if (lidRepo.get(lid.getId()) == null) {
               
                lidRepo.insert(lid);
                
                lijstLeden.add(lid);
                subject.firePropertyChange("lijstleden", null, lijstLeden);
                return true;
            }
        }
        return false;
    }

    public List<Lid> getLijstLeden() {
        return lijstLeden;
    }

    public Type getType() {
        return type;
    }

    public ObservableList<Lid> getSortedLeden() {
        fillSimplePropertiesForGui();
        return FXCollections.observableArrayList(lijstLeden);
    }

    public FilteredList getFilteredLeden() {
        return filtered;
    }

    private void fillSimplePropertiesForGui() {
        lijstLeden.forEach(lid -> lid.fillSimpleProperties());
    }

    public void addPropertyChangeListener(PropertyChangeListener pc1) {
        subject.addPropertyChangeListener(pc1);
        pc1.propertyChange(new PropertyChangeEvent(pc1, "lijstleden", null, lijstLeden));

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
    public void filter(SorteerType optie, String start, String einde) {
        filtered.setPredicate(lid -> {
            switch (optie) {
                case VOORNAAM:
                    if (start == null || start.isEmpty()) {
                        return lid.getVoornaam().compareToIgnoreCase(einde) <= 0 || lid.getVoornaam().toLowerCase().startsWith(einde.toLowerCase());
                    }
                    if (einde == null || einde.isEmpty()) {
                        return lid.getVoornaam().compareToIgnoreCase(start) >= 0;
                    } else {
                        return (lid.getVoornaam().compareToIgnoreCase(start) >= 0
                                && lid.getVoornaam().compareToIgnoreCase(einde) <= 0 || lid.getVoornaam().toLowerCase().startsWith(einde.toLowerCase()));
                    }
                case ACHTERNAAM:
                    if (start == null || start.isEmpty()) {
                        return lid.getAchternaam().compareToIgnoreCase(einde) <= 0 || lid.getAchternaam().toLowerCase().startsWith(einde.toLowerCase());
                    }
                    if (einde == null || einde.isEmpty()) {
                        return lid.getAchternaam().compareToIgnoreCase(start) >= 0;
                    } else {
                        return (lid.getAchternaam().compareToIgnoreCase(start) >= 0
                                && lid.getAchternaam().compareToIgnoreCase(einde) <= 0 || lid.getAchternaam().toLowerCase().startsWith(einde.toLowerCase()));
                    }
                case GRAAD:
                    return start.toLowerCase().equals(lid.getGraad().toString().toLowerCase());
                case TYPE:
                    return start.toLowerCase().equals(lid.getType().toString().toLowerCase());
                default:
                    return true;
            }
        });
    }
}
