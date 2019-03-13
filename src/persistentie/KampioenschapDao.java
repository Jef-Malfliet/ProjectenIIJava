/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Kampioenschap;
import java.time.LocalDate;

/**
 *
 * @author Jef
 */
public interface KampioenschapDao extends GenericDao<Kampioenschap> {

    public Kampioenschap getByNaamAndDate(String kampioenschapNaam, LocalDate datum);

}
