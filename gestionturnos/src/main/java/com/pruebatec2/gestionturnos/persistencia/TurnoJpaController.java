/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatec2.gestionturnos.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.pruebatec2.gestionturnos.logica.Tramite;
import com.pruebatec2.gestionturnos.logica.Ciudadano;
import com.pruebatec2.gestionturnos.logica.Turno;
import com.pruebatec2.gestionturnos.persistencia.exceptions.NonexistentEntityException;
import com.pruebatec2.gestionturnos.utilidades.Recursos;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;

/**
 *
 * @author Carlos Jaquez
 */
public class TurnoJpaController implements Serializable {

    public TurnoJpaController() {
        this.emf = Persistence.createEntityManagerFactory(Recursos.PERSISTENCENAME);
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Turno turno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tramite tramite = turno.getTramite();
            if (tramite != null) {
                tramite = em.getReference(tramite.getClass(), tramite.getId());
                turno.setTramite(tramite);
            }
            Ciudadano ciudadano = turno.getCiudadano();
            if (ciudadano != null) {
                ciudadano = em.getReference(ciudadano.getClass(), ciudadano.getId());
                turno.setCiudadano(ciudadano);
            }
            em.persist(turno);
            if (tramite != null) {
                Turno oldTurnoOfTramite = tramite.getTurno();
                if (oldTurnoOfTramite != null) {
                    oldTurnoOfTramite.setTramite(null);
                    oldTurnoOfTramite = em.merge(oldTurnoOfTramite);
                }
                tramite.setTurno(turno);
                tramite = em.merge(tramite);
            }
            if (ciudadano != null) {
                ciudadano.getTurnos().add(turno);
                ciudadano = em.merge(ciudadano);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Turno turno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Turno persistentTurno = em.find(Turno.class, turno.getId());
            Tramite tramiteOld = persistentTurno.getTramite();
            Tramite tramiteNew = turno.getTramite();
            Ciudadano ciudadanoOld = persistentTurno.getCiudadano();
            Ciudadano ciudadanoNew = turno.getCiudadano();
            if (tramiteNew != null) {
                tramiteNew = em.getReference(tramiteNew.getClass(), tramiteNew.getId());
                turno.setTramite(tramiteNew);
            }
            if (ciudadanoNew != null) {
                ciudadanoNew = em.getReference(ciudadanoNew.getClass(), ciudadanoNew.getId());
                turno.setCiudadano(ciudadanoNew);
            }
            turno = em.merge(turno);
            if (tramiteOld != null && !tramiteOld.equals(tramiteNew)) {
                tramiteOld.setTurno(null);
                tramiteOld = em.merge(tramiteOld);
            }
            if (tramiteNew != null && !tramiteNew.equals(tramiteOld)) {
                Turno oldTurnoOfTramite = tramiteNew.getTurno();
                if (oldTurnoOfTramite != null) {
                    oldTurnoOfTramite.setTramite(null);
                    oldTurnoOfTramite = em.merge(oldTurnoOfTramite);
                }
                tramiteNew.setTurno(turno);
                tramiteNew = em.merge(tramiteNew);
            }
            if (ciudadanoOld != null && !ciudadanoOld.equals(ciudadanoNew)) {
                ciudadanoOld.getTurnos().remove(turno);
                ciudadanoOld = em.merge(ciudadanoOld);
            }
            if (ciudadanoNew != null && !ciudadanoNew.equals(ciudadanoOld)) {
                ciudadanoNew.getTurnos().add(turno);
                ciudadanoNew = em.merge(ciudadanoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = turno.getId();
                if (findTurno(id) == null) {
                    throw new NonexistentEntityException("The turno with id " + id + " no longer exists.");
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
            Turno turno;
            try {
                turno = em.getReference(Turno.class, id);
                turno.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The turno with id " + id + " no longer exists.", enfe);
            }
            Tramite tramite = turno.getTramite();
            if (tramite != null) {
                tramite.setTurno(null);
                tramite = em.merge(tramite);
            }
            Ciudadano ciudadano = turno.getCiudadano();
            if (ciudadano != null) {
                ciudadano.getTurnos().remove(turno);
                ciudadano = em.merge(ciudadano);
            }
            em.remove(turno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Turno> findTurnoEntities() {
        return findTurnoEntities(true, -1, -1);
    }

    public List<Turno> findTurnoEntities(int maxResults, int firstResult) {
        return findTurnoEntities(false, maxResults, firstResult);
    }

    private List<Turno> findTurnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Turno.class));
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

    public Turno findTurno(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Turno.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Busca registros de turno en la bbdd que tengan la misma fecha pasada por
     * parametro
     *
     * @param ld
     * @return List<Turno>
     */
    public List<Turno> findTurnoByDate(LocalDate ld) {
        EntityManager em = getEntityManager();

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Turno> query = cb.createQuery(Turno.class);

            Root<Turno> turno = query.from(Turno.class);
            Query q = em.createQuery(query.where(cb.equal(turno.get("fecha"), ld)));

            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int getTurnoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Turno> rt = cq.from(Turno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
