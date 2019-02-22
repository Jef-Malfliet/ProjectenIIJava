package domein;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;
import persistentie.LidDao;
import persistentie.LidDaoJpa;

public class DomeinController {

    private Dojo dojo;
    private LidDaoJpa lidRepository;

    public DomeinController() {
        setLidRepository(new LidDaoJpa());
        dojo = new Dojo(this.lidRepository);
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

    public void filter(SorteerType type, String start, String einde) {
        dojo.filter(type, start, einde);
    }

    public ObservableList<Lid> getLeden() {
        return dojo.getSortedLeden();
    }

    public void setLidRepository(LidDaoJpa lidRepository) {
        this.lidRepository = lidRepository;
    }

    public void addPropertyChangeListener(PropertyChangeListener pc1) {
        dojo.addPropertyChangeListener(pc1);

    }

    public void removePropertyChangeListener(PropertyChangeListener pc1) {
        dojo.removePropertyChangeListener(pc1);

    }

}
