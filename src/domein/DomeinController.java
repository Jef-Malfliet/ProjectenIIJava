package domein;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;
import persistentie.LidDao;
import persistentie.LidDaoJpa;
import persistentie.OefeningDao;
import persistentie.OefeningDaoJpa;

public class DomeinController {
    
    private Dojo dojo;
    private LidDao lidRepository;
    private OefeningDao oefeningRepository;
    
    public DomeinController() {
        setLidRepository(new LidDaoJpa());
        setOefeningRepository(new OefeningDaoJpa());
        dojo = new Dojo(this.lidRepository,oefeningRepository);
    }
    
    public List<String> toonLeden() {
        return dojo.getLijstLeden().stream().map(Lid::toString).collect(Collectors.toList());
    }

    /**
     *
     * @param lid
     */
    public String toonLid(long id) {
        GenericDaoJpa.startTransaction();
        String lid1 = dojo.toonLid(id).toString();
        GenericDaoJpa.commitTransaction();
        return lid1;
    }

    /**
     *
     * @param lid
     */
    public boolean voegLidToe(Lid lid) {
        GenericDaoJpa.startTransaction();
        boolean lid1 = dojo.voegLidToe(lid);
        GenericDaoJpa.commitTransaction();
        return lid1;
    }
    
    public boolean wijzigLid(Lid lid) {
        GenericDaoJpa.startTransaction();
        boolean lid1 = dojo.wijzigLid(lid);
        GenericDaoJpa.commitTransaction();
        return lid1;
        
    }

    /**
     *
     * @param lid
     */
    public boolean verwijderLid(Lid lid) {
        GenericDaoJpa.startTransaction();
        boolean verwijdert = dojo.verwijderLid(lid);
        GenericDaoJpa.commitTransaction();
        return verwijdert;
    }
    
    public void filter(SorteerType type, String start) {
        dojo.filter(type, start);
    }
    
    public ObservableList<Lid> getLeden() {
        return dojo.getSortedLeden();
    }
    
    public void setLidRepository(LidDao lidRepository) {
        this.lidRepository = lidRepository;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener pc1) {
        dojo.addPropertyChangeListener(pc1);
        
    }
    
    public void removePropertyChangeListener(PropertyChangeListener pc1) {
        dojo.removePropertyChangeListener(pc1);
        
    }
    
    public void maakOverzicht(OverzichtType type, String path) {
        dojo.maakOverzicht(type, path);
    }
    
    public List<Overzicht> getOverzicht() {
        return dojo.getOverzichtList();
    }
    
    public List<Oefening> getLesmateriaal() {
        return dojo.getOefeningen();
    }

    /**
     *
     * @param oefening
     */
    public void addLesMateriaal(Oefening oefening) {
        dojo.addOefening(oefening);
    }

    public Oefening getOefening(Long id){
        return dojo.getOefeningById(id);
    }
    private void setOefeningRepository(OefeningDaoJpa oefeningDao) {
        this.oefeningRepository=oefeningDao;
    }
}
