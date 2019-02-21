package domein;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import persistentie.IPersistentieController;
import persistentie.PersistentieController;

public class Dojo {

    private IPersistentieController persistentieController;
    private List<Lid> lijstLeden;
    private final Type type;
    private final ObservableList<Lid> leden;
    private final Comparator<Lid> opNaam = (lid1, lid2) -> lid1.getNaam().compareToIgnoreCase(lid2.getNaam());
    private final Comparator<Lid> opType = (lid1, lid2) -> lid1.getType().compareTo(lid2.getType());
    private final Comparator<Lid> opGraad = (lid1, lid2) -> lid1.getGraad().compareTo(lid2.getGraad());
    private final Comparator<Lid> sortOrder = opNaam.thenComparing(opGraad).thenComparing(opType);
    private final FilteredList<Lid> filtered;
    private final SortedList<Lid> sorted;

    public Dojo(PersistentieController persistentieController) {
        setPersistentieController(persistentieController);
        lijstLeden = this.persistentieController.geefAlles(Stuff.LID);
        this.type = Type.BEHEERDER;
        leden = FXCollections.observableArrayList(lijstLeden);
        filtered = new FilteredList<>(leden);
        sorted = new SortedList<>(filtered,sortOrder);
    }

    public Dojo() {
        this(new PersistentieController());
    }

    /**
     *
     * @param lid
     */
    public boolean verwijderLid(Lid lid) {
        this.persistentieController.delete(lid);
        return this.lijstLeden.remove(lid);
    }

    /**
     *
     * @param lid
     */
    public boolean wijzigLid(Lid lid) {
        boolean succes = persistentieController.wijzig(lid);
        lijstLeden = persistentieController.geefAlles(Stuff.LID);
        return succes;
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
            if(persistentieController.geefById(Stuff.LID, lid)==null){
                persistentieController.add(lid);
                lijstLeden.add(lid);
                return true;
            }
        }
        return false;
    }

    public List<Lid> getLijstLeden() {
        return lijstLeden;
    }

    private void setPersistentieController(IPersistentieController persistentieController) {
        this.persistentieController = persistentieController;
    }

    public Type getType() {
        return type;
    }

    public ObservableList<Lid> getLeden() {
            return sorted;
    }
    
    public FilteredList getFilteredLeden(){
        return filtered;
    }

}
