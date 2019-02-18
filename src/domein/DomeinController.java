package domein;

import java.util.Comparator;
import java.util.List;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class DomeinController {

    private Beheerder beheerder;
    private List<String> leden;

    
  //  private final FilteredList<Lid> filtered;
//    private final SortedList<Lid> sorted;

    private final Comparator<Lid> opNaam = (lid1, lid2) -> lid1.getNaam().compareToIgnoreCase(lid2.getNaam());
    private final Comparator<Lid> opGraad = (lid1, lid2) -> lid1.getGraad().compareTo(lid2.getGraad());
    private final Comparator<Lid> opType = (lid1, lid2) -> lid1.getType().compareTo(lid2.getType());
    private final Comparator<Lid> sortOrder = opNaam.thenComparing(opGraad).thenComparing(opType);
    public DomeinController() {

    }
    
    

    public List<String> toonLeden() {
        // TODO - implement DomeinController.geefLijstLeden
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param lid
     */
    public List<String> toonLid(Lid lid) {
        // TODO - implement DomeinController.toonLid
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param lid
     */
    public boolean voegLidToe(Lid lid) {
        // TODO - implement DomeinController.voegLidToe
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param lid
     */
    public boolean verwijderLid(Lid lid) {
        // TODO - implement DomeinController.verwijderLid
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param optie
     */
    public void filterLijst(String optie) {
        // TODO - implement DomeinController.filterLijst
        throw new UnsupportedOperationException();
    }

}
