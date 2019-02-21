/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Mout
 */
public class GenericDaoJpa<E> implements GenericDao<E> {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("G19_PU");
    protected static final EntityManager em = emf.createEntityManager();
    private final Class<E> type;

    public GenericDaoJpa(Class<E> type) {
        this.type = type;
    }

    public void closePersistency() {
        em.close();
        emf.close();
    }

    public void startTransaction() {
        em.getTransaction().begin();
    }

    public void commitTransaction() {
        em.getTransaction().commit();
    }

    public void rollbackTransaction() {
        em.getTransaction().rollback();
    }

    @Override
    public List<E> findAll() {
        return em.createQuery("SELECT entity FROM" + type.getName() + " entity", type).getResultList();
    }

    @Override
    public E get(Long id) {
        return em.find(type, id);
    }

    @Override
    public E update(E object) {
        return em.merge(object);
    }

    @Override
    public void delete(E object) {
        em.remove(em.merge(object));
    }

    @Override
    public void insert(E object) {
        em.persist(object);
    }

    @Override
    public boolean exists(Long id) {
        E entity = em.find(type, id);
        return entity != null;
    }

}
