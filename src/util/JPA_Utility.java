package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Heidi
 */
public class JPA_Utility {
    private final static EntityManagerFactory entityManagerFactory =
                            Persistence.createEntityManagerFactory("G19_PU");
    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
    private JPA_Utility() {
    }
}