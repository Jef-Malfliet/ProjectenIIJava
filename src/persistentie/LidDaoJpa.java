/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Lid;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

/**
 *
 * @author Mout
 */
public class LidDaoJpa extends GenericDaoJpa<Lid> implements LidDao{
    
    public LidDaoJpa() {
        super(Lid.class);
    }

    @Override
    public List<Lid> getLedenByName(String name) throws EntityNotFoundException {
        try{
            return em.createNamedQuery("Lid.GetLedenByName", Lid.class)
                    .setParameter("lidVoornaam", name)
                    .getResultList();
        }catch(NoResultException ex){
            throw new EntityNotFoundException();
        }
    }
    
}
