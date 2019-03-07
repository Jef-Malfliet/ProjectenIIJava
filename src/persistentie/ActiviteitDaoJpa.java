/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Activiteit;

/**
 *
 * @author Jef
 */
public class ActiviteitDaoJpa extends GenericDaoJpa<Activiteit> implements ActiviteitDao {
    
    public ActiviteitDaoJpa() {
        super(Activiteit.class);
    }
    
    @Override
    public void lidInschrijven(Activiteit activiteit) {
        insert(activiteit);
    }
    
}
