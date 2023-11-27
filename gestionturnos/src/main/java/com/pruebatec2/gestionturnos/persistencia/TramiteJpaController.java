/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatec2.gestionturnos.persistencia;

import com.pruebatec2.gestionturnos.logica.Tramite;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.pruebatec2.gestionturnos.logica.Turno;
import com.pruebatec2.gestionturnos.persistencia.exceptions.NonexistentEntityException;
import com.pruebatec2.gestionturnos.utilidades.Recursos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Carlos Jaquez
 */
public class TramiteJpaController implements Serializable {

    public TramiteJpaController() {
        this.emf = Persistence.createEntityManagerFactory(Recursos.PERSISTENCENAME);
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tramite tramite) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Turno turno = tramite.getTurno();
            if (turno != null) {
                turno = em.getReference(turno.getClass(), turno.getId());
                tramite.setTurno(turno);
            }
            em.persist(tramite);
            if (turno != null) {
                Tramite oldTramiteOfTurno = turno.getTramite();
                if (oldTramiteOfTurno != null) {
                    oldTramiteOfTurno.setTurno(null);
                    oldTramiteOfTurno = em.merge(oldTramiteOfTurno);
                }
                turno.setTramite(tramite);
                turno = em.merge(turno);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tramite tramite) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tramite persistentTramite = em.find(Tramite.class, tramite.getId());
            Turno turnoOld = persistentTramite.getTurno();
            Turno turnoNew = tramite.getTurno();
            if (turnoNew != null) {
                turnoNew = em.getReference(turnoNew.getClass(), turnoNew.getId());
                tramite.setTurno(turnoNew);
            }
            tramite = em.merge(tramite);
            if (turnoOld != null && !turnoOld.equals(turnoNew)) {
                turnoOld.setTramite(null);
                turnoOld = em.merge(turnoOld);
            }
            if (turnoNew != null && !turnoNew.equals(turnoOld)) {
                Tramite oldTramiteOfTurno = turnoNew.getTramite();
                if (oldTramiteOfTurno != null) {
                    oldTramiteOfTurno.setTurno(null);
                    oldTramiteOfTurno = em.merge(oldTramiteOfTurno);
                }
                turnoNew.setTramite(tramite);
                turnoNew = em.merge(turnoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tramite.getId();
                if (findTramite(id) == null) {
                    throw new NonexistentEntityException("The tramite with id " + id + " no longer exists.");
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
            Tramite tramite;
            try {
                tramite = em.getReference(Tramite.class, id);
                tramite.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tramite with id " + id + " no longer exists.", enfe);
            }
            Turno turno = tramite.getTurno();
            if (turno != null) {
                turno.setTramite(null);
                turno = em.merge(turno);
            }
            em.remove(tramite);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tramite> findTramiteEntities() {
        return findTramiteEntities(true, -1, -1);
    }

    public List<Tramite> findTramiteEntities(int maxResults, int firstResult) {
        return findTramiteEntities(false, maxResults, firstResult);
    }

    private List<Tramite> findTramiteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tramite.class));
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

    public Tramite findTramite(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tramite.class, id);
        } finally {
            em.close();
        }
    }

    public int getTramiteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tramite> rt = cq.from(Tramite.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
