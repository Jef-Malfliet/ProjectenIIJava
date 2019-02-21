package persistentie;

import domein.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import util.JPA_Utility;

public class PersistentieController<E> implements IPersistentieController {
    
    private EntityManagerFactory emf;
    private EntityManager em;

    public PersistentieController() {
    }

    @Override
    public List<Lid> geefAlles(Stuff type) {

        switch (type) {
            case ACTIVITEIT:
                return null;

            case KAMPIOENSCHAP:
                return null;

            case LID:
                openConnection();
                List<Lid> leden = em.createNamedQuery("Lid.GetAll",Lid.class).getResultList();
                closeConnection();
                return leden;
        }

        return null;
    }

    @Override
    public boolean wijzig(Object item) {
        Lid lid = (Lid) item;
        try {
            Lid gevonden_lid = em.find(Lid.class, lid.getId());
            gevonden_lid.wijzigLid(lid.getNaam(), lid.getGraad());
            em.merge(gevonden_lid);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(Object item) {
        switch (item.getClass().toString()) {
            case "Activiteit":
                return false;
            case "Kampioenschap":
                return false;
            case "Lid":
                Lid lid = (Lid) item;
                Lid gevonden_lid = em.find(Lid.class, lid.getId());
                em.remove(gevonden_lid);
                closeConnection();
                return true;

        }

        return false;
    }

    @Override
    public Object geefById(Stuff type, Object item) {        
        switch (type) {
            case LID:
                Lid lid = (Lid)item;
                Lid gevonden_lid = em.find(Lid.class,lid.getId());
                return gevonden_lid;
        }
        return null;
    }

    @Override
    public void add(Object item) {
        em.persist(item);
        closeConnection();

    }

    private void openConnection(){
                
        emf = JPA_Utility.getEntityManagerFactory();
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    private void closeConnection() {
        em.getTransaction().commit();
        em.close();
    }


}
