package persistentie;

import domein.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import util.JPA_Utility;

public class LidMapper {

    public List<Lid> geefLijstLeden() {
        EntityManagerFactory emf = JPA_Utility.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        return null;
    }

    public boolean wijzigLid(Lid lid) {
        try{
          EntityManagerFactory emf = JPA_Utility.getEntityManagerFactory();
          EntityManager em = emf.createEntityManager();
          em.getTransaction().begin();
          Lid gevonden_lid = em.find(Lid.class, lid.getId());
          gevonden_lid.wijzigLid(lid.getNaam(),lid.getGraad());
          em.getTransaction().commit();
          em.close();
          emf.close();
           return true;
        }catch(Exception e){
            return false;
        }
         
    }

}
