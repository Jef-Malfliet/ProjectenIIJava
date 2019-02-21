package domein;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

public class DomeinController {

    private Dojo dojo;

    public DomeinController() {
        dojo = new Dojo();

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
        dojo.getFilteredLeden().setPredicate(lid -> {
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

    public ObservableList<Lid> getLeden() {
        return dojo.getFilteredLeden();
    }

}
