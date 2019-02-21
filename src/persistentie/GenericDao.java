/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import java.util.List;

/**
 *
 * @author Mout
 */
public interface GenericDao<E> {

    List<E> findAll();

    E get(Long id);

    E update(E object);

    void delete(E object);

    void insert(E object);

    boolean exists(Long id);
}
