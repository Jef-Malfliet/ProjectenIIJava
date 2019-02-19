package domein;

import java.util.List;
import persistentie.*;

public class Beheerder {

    private PersistentieController persistentieController;
    private final List<Lid> lijstLeden;
    private final Type type;

    public Beheerder(PersistentieController persistentieController) {
        setPersistentieController(persistentieController);
        lijstLeden = this.persistentieController.geefLijstLeden();
        this.type = Type.BEHEERDER;
    }

    /**
     *
     * @param lid
     */
    public boolean verwijderLid(Lid lid) {
        // TODO - implement Beheerder.verwijderLid
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param lid
     */
    public boolean wijzigLid(Lid lid) {
        // TODO - implement Beheerder.wijzigLid
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param lid
     */
    public Lid toonLid(Lid lid) {
        // TODO - implement Beheerder.toonLid
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param lid
     */
    public boolean voegLidToe(Lid lid) {
        // TODO - implement Beheerder.voegLidToe
        throw new UnsupportedOperationException();
    }

    public List<Lid> getLijstLeden() {
        return lijstLeden;
    }

    private void setPersistentieController(PersistentieController persistentieController) {
        this.persistentieController = persistentieController;
    }

    public Type getType() {
        return type;
    }

}
