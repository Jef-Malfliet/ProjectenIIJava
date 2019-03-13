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
public class ActiviteitDaoJpa extends GenericDaoJpa<Activiteit> implements ActiviteitDao {

    public ActiviteitDaoJpa() {
        super(Activiteit.class);
    }

    @Override
    public Activiteit getByNaamAndBeginAndEindDate(String activiteitNaam, LocalDate beginDatum, LocalDate eindDatum) {
        return findAll().stream().filter(a -> a.getNaam().equals(activiteitNaam) && a.getBeginDatum().equals(beginDatum) && a.getEindDatum().equals(eindDatum)).findFirst().orElse(null);
    }

    @Override
    public void delete(Activiteit currentActiviteit) {
        currentActiviteit.getAanwezigen().clear();
        em.remove(currentActiviteit);
    }
}
