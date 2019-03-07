/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Oefening;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

/**
 *
 * @author IndyV
 */
public class OefeningDaoJpa extends GenericDaoJpa<Oefening> implements OefeningDao{
    
    public OefeningDaoJpa() {
        super(Oefening.class);
    }
    
     @Override
    public Oefening getOefeningByName(String name) throws EntityNotFoundException {
        try{
            return em.createNamedQuery("Oefening.GetOefeningByName", Oefening.class)
                    .setParameter("oefNaam", name).getSingleResult();
        }catch(NoResultException ex){
            throw new EntityNotFoundException();
        }
    }
    
}
