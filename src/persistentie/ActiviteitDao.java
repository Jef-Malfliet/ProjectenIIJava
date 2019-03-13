/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Activiteit;
import java.time.LocalDate;

/**
 *
 * @author Jef
 */
public interface ActiviteitDao extends GenericDao<Activiteit> {

    public Activiteit getByNaamAndBeginAndEindDate(String activiteitNaam, LocalDate beginDatum, LocalDate eindDatum);

    @Override
    public void delete(Activiteit currentActiviteit);
}
