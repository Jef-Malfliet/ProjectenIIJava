package persistentie;

import domein.*;
import java.util.List;

public class PersistentieController {

    private List<Lid> leden;
    private LidMapper lidmapper;

    public PersistentieController() {
        lidmapper = new LidMapper();
        this.leden = lidmapper.geefLijstLeden();
    }

    
    public List<Lid> geefLijstLeden() {
        return leden;
    }

    public boolean wijzigLid(Lid lid) {
        return lidmapper.wijzigLid(lid);
        
    }

}
