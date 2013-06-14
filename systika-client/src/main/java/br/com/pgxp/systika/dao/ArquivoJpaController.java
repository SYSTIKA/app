/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pgxp.systika.dao;

import br.com.pgxp.systika.dao.exceptions.NonexistentEntityException;
import br.com.pgxp.systika.domain.Arquivo;
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
public class ArquivoJpaController implements Serializable {

    public ArquivoJpaController() {
        
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("systika-client");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Arquivo arquivo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(arquivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Arquivo arquivo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            arquivo = em.merge(arquivo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = arquivo.getId();
                if (findArquivo(id) == null) {
                    throw new NonexistentEntityException("The arquivo with id " + id + " no longer exists.");
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
            Arquivo arquivo;
            try {
                arquivo = em.getReference(Arquivo.class, id);
                arquivo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The arquivo with id " + id + " no longer exists.", enfe);
            }
            em.remove(arquivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Arquivo> findArquivoEntities() {
        return findArquivoEntities(true, -1, -1);
    }

    public List<Arquivo> findArquivoEntities(int maxResults, int firstResult) {
        return findArquivoEntities(false, maxResults, firstResult);
    }

    private List<Arquivo> findArquivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Arquivo.class));
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

    public Arquivo findArquivo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Arquivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getArquivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Arquivo> rt = cq.from(Arquivo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
