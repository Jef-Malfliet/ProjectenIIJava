package domein;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class DomeinController {

    private final ObservableList<Lid> leden;

    private final FilteredList<Lid> filtered;
    private final SortedList<Lid> sorted;

    private final Comparator<Lid> opNaam = (lid1, lid2) -> lid1.getNaam().compareToIgnoreCase(lid2.getNaam());
    private final Comparator<Lid> opGraad = (lid1, lid2) -> lid1.getGraad().compareTo(lid2.getGraad());
    private final Comparator<Lid> opType = (lid1, lid2) -> lid1.getType().compareTo(lid2.getType());
    private final Comparator<Lid> sortOrder = opNaam.thenComparing(opGraad).thenComparing(opType);
    private Dojo dojo;

    public DomeinController() {
        dojo = new Dojo();
        leden = FXCollections.observableArrayList(dojo.getLijstLeden());
        filtered = new FilteredList<>(leden, p -> true);
        sorted = new SortedList<>(filtered, sortOrder);
    }

    public List<String> toonLeden() {
        return dojo.getLijstLeden().stream().map(Lid::toString).collect(Collectors.toList());
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
    public boolean voegLidToe(Lid lid) {
        return dojo.voegLidToe(lid);
    }

    /**
     *
     * @param lid
     */
    public boolean verwijderLid(Lid lid) {
        return dojo.verwijderLid(lid);
    }

    /**
     * @param start
     * @param end
     * @param optie
     */
    public void filterLijst(String optie, String start, String end) {
        filtered.setPredicate(lid -> {
            if (optie == null || optie.isEmpty()) {
                return true;
            }
            switch (optie) {
                case "naam":
                    return false;
                case "graad":
                    return false;
                case "type":
                    return false;
                default:
                    return true;
            }
        });
    }

}
