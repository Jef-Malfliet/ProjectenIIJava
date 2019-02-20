package domein;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import persistentie.*;

public class Beheerder {

    private PersistentieController persistentieController;
    private final Set<Lid> lijstLeden;
    private final Type type;

    public Beheerder(PersistentieController persistentieController) {
        setPersistentieController(persistentieController);
        lijstLeden = this.persistentieController.geefLijstLeden();
        
        this.type = Type.BEHEERDER;
    }
    public Beheerder(){
        this(new PersistentieController());
    }


    /**
     *
     * @param lid
     */
    public boolean verwijderLid(Lid lid) {
        return lijstLeden.remove(lid);
        
//        Iterator it = lijstLeden.iterator();
//        while(it.hasNext()){
//            Lid temp = (Lid)it.next();
//            if(temp.equals(lid)){
//                it.remove();
//                return true;
//            }
//        }
//        return false;
    }

    /**
     *
     * @param lid
     */
    public boolean wijzigLid(Lid lid) {//deze methode moet hermaakt worden, slecht opgesteld
        // TODO - implement Beheerder.wijzigLid
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param lid
     */
    public Lid toonLid(Lid lid) {//methode moet hermaakt worden, slecht opgesteld
        // TODO - implement Beheerder.toonLid
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param lid
     */
    public boolean voegLidToe(Lid lid) {
       return lijstLeden.add(lid);
       
//        for (Lid temp : lijstLeden) {
//            if (temp.equals(lid)) {
//                return false;
//            }
//        }
//        
//        lijstLeden.add(lid);
//        return true;
    }
    
    public Set<Lid> getLijstLeden() {
        return lijstLeden;
    }

    private void setPersistentieController(PersistentieController persistentieController) {
        this.persistentieController = persistentieController;
    }

    public Type getType() {
        return type;
    }

}
