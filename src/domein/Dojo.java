package domein;

import java.util.List;
import persistentie.PersistentieController;

public class Dojo {
    private PersistentieController persistentieController;
    private final List<Lid> lijstLeden;
    private final Type type;

    public Dojo(PersistentieController persistentieController) {
        setPersistentieController(persistentieController);
        lijstLeden = this.persistentieController.geefLijstLeden();

        this.type = Type.BEHEERDER;
    }

    public Dojo() {
        this(new PersistentieController());
    }

    /**
     *
     * @param lid
     */
    public boolean verwijderLid(Lid lid) {
        return lijstLeden.remove(lid);
    }

    /**
     *
     * @param lid
     */
    public boolean wijzigLid(Lid lid) {
        return persistentieController.wijzigLid(lid);
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
            return lijstLeden.add(lid);
        }
        return false;
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