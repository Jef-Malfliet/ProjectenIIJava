/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Lid;
import java.util.List;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Mout
 */
public interface LidDao extends GenericDao<Lid> {
//voor extra sql operaties, als er geen extra nodig zijn mag deze klasse weg
    List<Lid> getLedenByName(String name) throws EntityNotFoundException;
}
