/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domein.Graad;
import domein.Lid;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import util.JPA_Utility;

/**
 *
 * @author Nante
 */
public class InitializeData {

    public InitializeData() {
        initialize();
    }

    private void initialize() {
        Lid lid0 = new Lid("Nante", "Vermeulen", Graad.GROEN, "12/34567890", "nante.vermeulen@student.hogent.be", "weet ik niet", 9300, "Landeghem");
        Lid lid1 = new Lid("Indy Van Canegem", Graad.BRUIN);
        Lid lid2 = new Lid("Jef Malfliet", Graad.ORANJE);
        Lid lid3 = new Lid("Mout Pessemier", Graad.WIT);
        EntityManagerFactory emf = JPA_Utility.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();      
        em.persist(lid0);
        em.persist(lid1);
        em.persist(lid2);
        em.persist(lid3);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
   

}
