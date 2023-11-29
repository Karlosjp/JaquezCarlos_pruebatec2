/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatec2.gestionturnos.persistencia;

import com.pruebatec2.gestionturnos.logica.Ciudadano;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.pruebatec2.gestionturnos.logica.Direccion;
import com.pruebatec2.gestionturnos.logica.Turno;
import com.pruebatec2.gestionturnos.persistencia.exceptions.NonexistentEntityException;
import com.pruebatec2.gestionturnos.utilidades.Recursos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;

/**
 *
 * @author Carlos Jaquez
 */
public class CiudadanoJpaController implements Serializable {

    public CiudadanoJpaController() {
        this.emf = Persistence.createEntityManagerFactory(Recursos.PERSISTENCENAME);
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ciudadano ciudadano) {
        if (ciudadano.getTurnos() == null) {
            ciudadano.setTurnos(new ArrayList<Turno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Direccion direccion = ciudadano.getDireccion();
            if (direccion != null) {
                direccion = em.getReference(direccion.getClass(), direccion.getId());
                ciudadano.setDireccion(direccion);
            }
            List<Turno> attachedTurnos = new ArrayList<Turno>();
            for (Turno turnosTurnoToAttach : ciudadano.getTurnos()) {
                turnosTurnoToAttach = em.getReference(turnosTurnoToAttach.getClass(), turnosTurnoToAttach.getId());
                attachedTurnos.add(turnosTurnoToAttach);
            }
            ciudadano.setTurnos(attachedTurnos);
            em.persist(ciudadano);
            if (direccion != null) {
                direccion.getCiudadanos().add(ciudadano);
                direccion = em.merge(direccion);
            }
            for (Turno turnosTurno : ciudadano.getTurnos()) {
                Ciudadano oldCiudadanoOfTurnosTurno = turnosTurno.getCiudadano();
                turnosTurno.setCiudadano(ciudadano);
                turnosTurno = em.merge(turnosTurno);
                if (oldCiudadanoOfTurnosTurno != null) {
                    oldCiudadanoOfTurnosTurno.getTurnos().remove(turnosTurno);
                    oldCiudadanoOfTurnosTurno = em.merge(oldCiudadanoOfTurnosTurno);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ciudadano ciudadano) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ciudadano persistentCiudadano = em.find(Ciudadano.class, ciudadano.getId());
            Direccion direccionOld = persistentCiudadano.getDireccion();
            Direccion direccionNew = ciudadano.getDireccion();
            List<Turno> turnosOld = persistentCiudadano.getTurnos();
            List<Turno> turnosNew = ciudadano.getTurnos();
            if (direccionNew != null) {
                direccionNew = em.getReference(direccionNew.getClass(), direccionNew.getId());
                ciudadano.setDireccion(direccionNew);
            }
            List<Turno> attachedTurnosNew = new ArrayList<Turno>();
            for (Turno turnosNewTurnoToAttach : turnosNew) {
                turnosNewTurnoToAttach = em.getReference(turnosNewTurnoToAttach.getClass(), turnosNewTurnoToAttach.getId());
                attachedTurnosNew.add(turnosNewTurnoToAttach);
            }
            turnosNew = attachedTurnosNew;
            ciudadano.setTurnos(turnosNew);
            ciudadano = em.merge(ciudadano);
            if (direccionOld != null && !direccionOld.equals(direccionNew)) {
                direccionOld.getCiudadanos().remove(ciudadano);
                direccionOld = em.merge(direccionOld);
            }
            if (direccionNew != null && !direccionNew.equals(direccionOld)) {
                direccionNew.getCiudadanos().add(ciudadano);
                direccionNew = em.merge(direccionNew);
            }
            for (Turno turnosOldTurno : turnosOld) {
                if (!turnosNew.contains(turnosOldTurno)) {
                    turnosOldTurno.setCiudadano(null);
                    turnosOldTurno = em.merge(turnosOldTurno);
                }
            }
            for (Turno turnosNewTurno : turnosNew) {
                if (!turnosOld.contains(turnosNewTurno)) {
                    Ciudadano oldCiudadanoOfTurnosNewTurno = turnosNewTurno.getCiudadano();
                    turnosNewTurno.setCiudadano(ciudadano);
                    turnosNewTurno = em.merge(turnosNewTurno);
                    if (oldCiudadanoOfTurnosNewTurno != null && !oldCiudadanoOfTurnosNewTurno.equals(ciudadano)) {
                        oldCiudadanoOfTurnosNewTurno.getTurnos().remove(turnosNewTurno);
                        oldCiudadanoOfTurnosNewTurno = em.merge(oldCiudadanoOfTurnosNewTurno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = ciudadano.getId();
                if (findCiudadano(id) == null) {
                    throw new NonexistentEntityException("The ciudadano with id " + id + " no longer exists.");
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
            Ciudadano ciudadano;
            try {
                ciudadano = em.getReference(Ciudadano.class, id);
                ciudadano.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ciudadano with id " + id + " no longer exists.", enfe);
            }
            Direccion direccion = ciudadano.getDireccion();
            if (direccion != null) {
                direccion.getCiudadanos().remove(ciudadano);
                direccion = em.merge(direccion);
            }
            List<Turno> turnos = ciudadano.getTurnos();
            for (Turno turnosTurno : turnos) {
                turnosTurno.setCiudadano(null);
                turnosTurno = em.merge(turnosTurno);
            }
            em.remove(ciudadano);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ciudadano> findCiudadanoEntities() {
        return findCiudadanoEntities(true, -1, -1);
    }

    public List<Ciudadano> findCiudadanoEntities(int maxResults, int firstResult) {
        return findCiudadanoEntities(false, maxResults, firstResult);
    }

    private List<Ciudadano> findCiudadanoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ciudadano.class));
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

    public Ciudadano findCiudadano(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ciudadano.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Busca registros de Ciudadano con el nombre pasado por marametro
     *
     * @param nombre
     * @return List<Ciudadano>
     */
    public List<Ciudadano> findCiudadanoEntitiesName(String nombre) {
        EntityManager em = getEntityManager();

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Ciudadano> query = cb.createQuery(Ciudadano.class);

            Root<Ciudadano> ciudadano = query.from(Ciudadano.class);
            Query q = em.createQuery(query.where(cb.equal(ciudadano.get("nombre"), nombre)));

            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int getCiudadanoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ciudadano> rt = cq.from(Ciudadano.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
