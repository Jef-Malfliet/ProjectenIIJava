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

}