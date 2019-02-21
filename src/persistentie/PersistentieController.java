package persistentie;

import domein.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import util.JPA_Utility;

public class PersistentieController<E> implements IPersistentieController {

    private List<Lid> leden;
    private EntityManagerFactory emf;
    private EntityManager em;

    public PersistentieController() {
        
        emf = JPA_Utility.getEntityManagerFactory();
        em = emf.createEntityManager();
        em.getTransaction().begin();
        this.leden = (List<Lid>) geefAlles(Stuff.LID);
        closeConnection();
    }

    @Override
    public List<E> geefAlles(Stuff type) {

        switch (type) {
            case ACTIVITEIT:
                List<E> activiteiten = em.createQuery("SELECT * FROM Activiteit").getResultList();
                closeConnection();
                return activiteiten;

            case KAMPIOENSCHAP:
                List<E> kampioenschappen = em.createQuery("SELECT * FROM Kampioenschap").getResultList();
                closeConnection();
                return kampioenschappen;

            case LID:
                List<E> leden = em.createQuery("SELECT * FROM Lid").getResultList();
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
                //Activiteit gevonden_activiteit = em.find(Activiteit.class, gevonden_activiteit)
                closeConnection();
                return true;
            case "Kampioenschap":
                //Kampioenschap gevonden_kampioenschap = em.find(Activiteit.class, gevonden_kampioenschap )
                closeConnection();
                return true;
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

    private void closeConnection() {
        em.getTransaction().commit();
        em.close();
        emf.close();
    }


}
