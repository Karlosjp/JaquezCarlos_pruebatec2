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
import com.pruebatec2.gestionturnos.logica.Ciudadano;
import com.pruebatec2.gestionturnos.logica.Direccion;
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
public class DireccionJpaController implements Serializable {

    public DireccionJpaController() {
        this.emf = Persistence.createEntityManagerFactory(Recursos.PERSISTENCENAME);
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Direccion direccion) {
        if (direccion.getCiudadanos() == null) {
            direccion.setCiudadanos(new ArrayList<Ciudadano>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ciudadano> attachedCiudadanos = new ArrayList<Ciudadano>();
            for (Ciudadano ciudadanosCiudadanoToAttach : direccion.getCiudadanos()) {
                ciudadanosCiudadanoToAttach = em.getReference(ciudadanosCiudadanoToAttach.getClass(), ciudadanosCiudadanoToAttach.getId());
                attachedCiudadanos.add(ciudadanosCiudadanoToAttach);
            }
            direccion.setCiudadanos(attachedCiudadanos);
            em.persist(direccion);
            for (Ciudadano ciudadanosCiudadano : direccion.getCiudadanos()) {
                Direccion oldDireccionOfCiudadanosCiudadano = ciudadanosCiudadano.getDireccion();
                ciudadanosCiudadano.setDireccion(direccion);
                ciudadanosCiudadano = em.merge(ciudadanosCiudadano);
                if (oldDireccionOfCiudadanosCiudadano != null) {
                    oldDireccionOfCiudadanosCiudadano.getCiudadanos().remove(ciudadanosCiudadano);
                    oldDireccionOfCiudadanosCiudadano = em.merge(oldDireccionOfCiudadanosCiudadano);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Direccion direccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Direccion persistentDireccion = em.find(Direccion.class, direccion.getId());
            List<Ciudadano> ciudadanosOld = persistentDireccion.getCiudadanos();
            List<Ciudadano> ciudadanosNew = direccion.getCiudadanos();
            List<Ciudadano> attachedCiudadanosNew = new ArrayList<Ciudadano>();
            for (Ciudadano ciudadanosNewCiudadanoToAttach : ciudadanosNew) {
                ciudadanosNewCiudadanoToAttach = em.getReference(ciudadanosNewCiudadanoToAttach.getClass(), ciudadanosNewCiudadanoToAttach.getId());
                attachedCiudadanosNew.add(ciudadanosNewCiudadanoToAttach);
            }
            ciudadanosNew = attachedCiudadanosNew;
            direccion.setCiudadanos(ciudadanosNew);
            direccion = em.merge(direccion);
            for (Ciudadano ciudadanosOldCiudadano : ciudadanosOld) {
                if (!ciudadanosNew.contains(ciudadanosOldCiudadano)) {
                    ciudadanosOldCiudadano.setDireccion(null);
                    ciudadanosOldCiudadano = em.merge(ciudadanosOldCiudadano);
                }
            }
            for (Ciudadano ciudadanosNewCiudadano : ciudadanosNew) {
                if (!ciudadanosOld.contains(ciudadanosNewCiudadano)) {
                    Direccion oldDireccionOfCiudadanosNewCiudadano = ciudadanosNewCiudadano.getDireccion();
                    ciudadanosNewCiudadano.setDireccion(direccion);
                    ciudadanosNewCiudadano = em.merge(ciudadanosNewCiudadano);
                    if (oldDireccionOfCiudadanosNewCiudadano != null && !oldDireccionOfCiudadanosNewCiudadano.equals(direccion)) {
                        oldDireccionOfCiudadanosNewCiudadano.getCiudadanos().remove(ciudadanosNewCiudadano);
                        oldDireccionOfCiudadanosNewCiudadano = em.merge(oldDireccionOfCiudadanosNewCiudadano);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = direccion.getId();
                if (findDireccion(id) == null) {
                    throw new NonexistentEntityException("The direccion with id " + id + " no longer exists.");
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
            Direccion direccion;
            try {
                direccion = em.getReference(Direccion.class, id);
                direccion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The direccion with id " + id + " no longer exists.", enfe);
            }
            List<Ciudadano> ciudadanos = direccion.getCiudadanos();
            for (Ciudadano ciudadanosCiudadano : ciudadanos) {
                ciudadanosCiudadano.setDireccion(null);
                ciudadanosCiudadano = em.merge(ciudadanosCiudadano);
            }
            em.remove(direccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Direccion> findDireccionEntities() {
        return findDireccionEntities(true, -1, -1);
    }

    public List<Direccion> findDireccionEntities(int maxResults, int firstResult) {
        return findDireccionEntities(false, maxResults, firstResult);
    }

    private List<Direccion> findDireccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Direccion.class));
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

    public List<Direccion> findDireccionEntitiesCountry(String ciudad) {
        EntityManager em = getEntityManager();

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Direccion> query = cb.createQuery(Direccion.class);

            Root<Direccion> direccion = query.from(Direccion.class);
            Query q = em.createQuery(query.where(cb.equal(direccion.get("ciudad"), ciudad)));

            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Direccion findDireccion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Direccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getDireccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Direccion> rt = cq.from(Direccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
