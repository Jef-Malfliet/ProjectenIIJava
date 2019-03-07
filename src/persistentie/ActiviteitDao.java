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
public interface ActiviteitDao extends GenericDao<Activiteit> {

    public void lidInschrijven(Activiteit activiteit);

}
