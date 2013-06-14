/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pgxp.systika.dao;

import br.com.pgxp.systika.dao.exceptions.NonexistentEntityException;
import br.com.pgxp.systika.dao.exceptions.PreexistingEntityException;
import br.com.pgxp.systika.domain.Metadados;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author escritorio
 */
public class MetadadosJpaController implements Serializable {

    public MetadadosJpaController() {
        
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("systika-client");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Metadados metadados) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(metadados);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMetadados(metadados.getId()) != null) {
                throw new PreexistingEntityException("Metadados " + metadados + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Metadados metadados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            metadados = em.merge(metadados);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = metadados.getId();
                if (findMetadados(id) == null) {
                    throw new NonexistentEntityException("The metadados with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Metadados metadados;
            try {
                metadados = em.getReference(Metadados.class, id);
                metadados.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The metadados with id " + id + " no longer exists.", enfe);
            }
            em.remove(metadados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Metadados> findMetadadosEntities() {
        return findMetadadosEntities(true, -1, -1);
    }

    public List<Metadados> findMetadadosEntities(int maxResults, int firstResult) {
        return findMetadadosEntities(false, maxResults, firstResult);
    }

    private List<Metadados> findMetadadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Metadados.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Metadados findMetadados(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Metadados.class, id);
        } finally {
            em.close();
        }
    }

    public int getMetadadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Metadados> rt = cq.from(Metadados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
