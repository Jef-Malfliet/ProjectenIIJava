/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Oefening;
import java.util.List;
import javafx.scene.image.Image;
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
    public Oefening getOefeningById(long id) throws EntityNotFoundException {
         System.out.println(id);
        try{
            return em.createNamedQuery("Oefening.GetOefeningById", Oefening.class)
                    .setParameter("oefId", id).getSingleResult();
            
        }catch(NoResultException ex){
            throw new EntityNotFoundException();
        }
    }

    
    
    
}
