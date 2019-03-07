/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Oefening;
import java.util.List;

/**
 *
 * @author IndyV
 */
public interface OefeningDao extends GenericDao<Oefening>{
    Oefening getOefeningById(long id);
}
