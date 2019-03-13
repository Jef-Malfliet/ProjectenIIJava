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
public class KampioenschapDaoJpa extends GenericDaoJpa<Kampioenschap> implements KampioenschapDao {
    
    public KampioenschapDaoJpa() {
        super(Kampioenschap.class);
    }
    
    @Override
    public Kampioenschap getByNaamAndDate(String kampioenschapNaam, LocalDate datum) {
        return findAll().stream().filter(k -> k.getNaam().equals(kampioenschapNaam) && k.getDatum().equals(datum)).findFirst().orElse(null);
    }
    
}
