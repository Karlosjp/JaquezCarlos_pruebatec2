package com.pruebatec2.gestionturnos.persistencia;

import com.pruebatec2.gestionturnos.logica.Ciudadano;
import com.pruebatec2.gestionturnos.logica.Direccion;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author Carlos Jaquez
 */
public class ControladoraPersistencia {

    CiudadanoJpaController ciudadanoJPA = new CiudadanoJpaController();
    DireccionJpaController direccionJPA = new DireccionJpaController();

    // <editor-fold defaultstate="collapsed" desc="Persistencia Creacion methods. Click on the + sign on the left to edit the code.">
    /**
     * Crea un nuevo usuario
     *
     * @param ciudadano
     * @param direccion
     */
    public void crearCiudadano(Ciudadano ciudadano) {
        ciudadanoJPA.create(ciudadano);
    }

    /**
     *
     * @param direccion
     */
    public void crearDireccion(Direccion direccion) {
        direccionJPA.create(direccion);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Persistencia Buscar methods. Click on the + sign on the left to edit the code.">
    /**
     * Si no existe registro de la direccion crea un nuevo registro. Devuelve el
     * registro encontrado o el recien creado
     *
     * @param direccion
     * @return Object Direccion
     */
    public Direccion existeSinoCrea(Direccion direccion) {
        Direccion d = existe(direccion);

        if (d == null) {
            crearDireccion(direccion);
            d = direccion;
        }

        return d;
    }

    /**
     * Si no existe registro del ciudadano crea un nuevo registro
     *
     * @param ciudadano
     */
    public void existeSinoCrea(Ciudadano ciudadano) {
        Ciudadano c = existe(ciudadano);

        if (c == null) {
            crearCiudadano(ciudadano);
        }
    }

    /**
     * Comprueba si la direccion ya esta en un registro. Si no lo esta retorna
     * null
     *
     * @param direccion
     * @return Object Direccion / null
     */
    private Direccion existe(Direccion direccion) {
        Direccion dir = null;
        List<Direccion> direcciones = direccionJPA.findDireccionEntitiesCountry(direccion.getCiudad());

        if (!direcciones.isEmpty()) {
            dir = direcciones.stream()
                    .filter(d -> d.equals(direccion))
                    .findFirst()
                    .orElse(null);
        }

        return dir;
    }

    /**
     * Comprueba si el ciudadano ya esta en un registro. Si no lo esta retorna
     * null
     *
     * @param direccion
     * @return Object Direccion / null
     */
    private Ciudadano existe(Ciudadano ciudadano) {
        Ciudadano ciu = null;
        List<Ciudadano> ciudadanos = ciudadanoJPA.findCiudadanoEntitiesName(ciudadano.getNombre());

        if (!ciudadanos.isEmpty()) {
            ciu = ciudadanos.stream()
                    .filter(c -> c.equals(ciudadano))
                    .findFirst()
                    .orElse(null);
        }

        return ciu;
    }

    public List<Ciudadano> listarCiudadanos() {
        return ciudadanoJPA.findCiudadanoEntities();
    }

    public Ciudadano buscarCiudadano(Long id) {
        return ciudadanoJPA.findCiudadano(id);
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Persistencia Turno methods. Click on the + sign on the left to edit the code.">
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Persistencia Tramite methods. Click on the + sign on the left to edit the code.">
    // </editor-fold>
}
