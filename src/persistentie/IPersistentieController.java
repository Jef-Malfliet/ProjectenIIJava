package persistentie;

import domein.*;
import java.util.List;

public interface IPersistentieController<E> {

    List<E> geefAlles(Stuff type);
    
    E geefById(Stuff type,E item);

    boolean wijzig(E item);
    
    boolean delete(E item);
    
    void add(E item);

}
