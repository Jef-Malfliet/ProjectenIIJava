//package persistentie;
//
//import domein.*;
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import util.JPA_Utility;
//
//public class PersistentieController<E> implements IPersistentieController {
//
//    private EntityManagerFactory emf;
//    private EntityManager em;
//
//    public PersistentieController() {
//    }
//
//    @Override
//    public List<Object> geefAlles(Stuff type) {
//        openConnection();
//        switch (type) {
//            case ACTIVITEIT:
//                return null;
//
//            case KAMPIOENSCHAP:
//                return null;
//
//            case LID:
//                List<Object> leden = em.createNamedQuery("Lid.GetAll").getResultList();
//                closeConnection();
//                return leden;
//        }
//
//        return null;
//    }
//
//    @Override
//    public boolean wijzig(Object item) {
//        openConnection();
//        Lid lid = (Lid) item;
//        try {
//            Lid gevonden_lid = em.find(Lid.class, lid.getId());
//            gevonden_lid.wijzigLid(lid.getVoornaam(), lid.getGraad());
//            em.merge(gevonden_lid);
//            closeConnection();
//
//            return true;
//        } catch (Exception e) {
//
//            return false;
//        }
//    }
//
//    @Override
//    public boolean delete(Object item) {
//        openConnection();
//        switch (item.getClass().getSimpleName()) {
//            case "Activiteit":
//                return false;
//            case "Kampioenschap":
//                return false;
//            case "Lid":
//                Lid lid = (Lid) item;
//                Lid gevonden_lid = em.find(Lid.class, lid.getId());
//                em.remove(gevonden_lid);
//                closeConnection();
//                return true;
//
//        }
//
//        return false;
//    }
//
//    @Override
//    public Object geefById(Stuff type, Object item) {
//        openConnection();
//        switch (type) {
//            case LID:
//                Lid lid = (Lid) item;
//                Lid gevonden_lid = em.find(Lid.class, lid.getId());
//                closeConnection();
//                return gevonden_lid;
//        }
//        return null;
//    }
//
//    @Override
//    public void add(Object item) {
//        openConnection();
//        em.persist(item);
//        closeConnection();
//
//    }
//
//    private void openConnection() {
//        emf = JPA_Utility.getEntityManagerFactory();
//        em = emf.createEntityManager();
//        em.getTransaction().begin();
//    }
//
//    private void closeConnection() {
//        em.getTransaction().commit();
//        em.close();
//    }
//
//}
