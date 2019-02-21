package domein;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;
import persistentie.LidDao;
import persistentie.LidDaoJpa;

public class DomeinController {

    private Dojo dojo;
    private GenericDao<Dojo> dojoRepository;
    private LidDao lidRepository;

    public DomeinController() {
        dojo = new Dojo();
        setDojoRepository(new GenericDaoJpa<>(Dojo.class));
        setLidRepository(new LidDaoJpa());
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

    public boolean wijzigLid(Lid lid) {
        return dojo.wijzigLid(lid);

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
        return dojo.getSortedLeden();
    }

    public void setDojoRepository(GenericDao<Dojo> dojoRepository) {
        this.dojoRepository = dojoRepository;
    }

    public void setLidRepository(LidDao lidRepository) {
        this.lidRepository = lidRepository;
    }

    
     public void addPropertyChangeListener(PropertyChangeListener pc1){
       dojo.addPropertyChangeListener(pc1);
        
    }
     public void removePropertyChangeListener(PropertyChangeListener pc1){
        dojo.removePropertyChangeListener(pc1);
       
        
    }

}
