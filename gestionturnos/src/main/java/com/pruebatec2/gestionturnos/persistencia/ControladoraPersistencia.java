package com.pruebatec2.gestionturnos.persistencia;

import com.pruebatec2.gestionturnos.logica.Ciudadano;
import com.pruebatec2.gestionturnos.logica.Direccion;
import com.pruebatec2.gestionturnos.logica.Tramite;
import com.pruebatec2.gestionturnos.logica.Turno;
import com.pruebatec2.gestionturnos.utilidades.Estado;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos Jaquez
 */
public class ControladoraPersistencia {

    CiudadanoJpaController ciudadanoJPA = new CiudadanoJpaController();
    DireccionJpaController direccionJPA = new DireccionJpaController();
    TramiteJpaController tramiteJPA = new TramiteJpaController();
    TurnoJpaController turnoJPA = new TurnoJpaController();

    // <editor-fold defaultstate="collapsed" desc="Persistencia Creacion methods. ">
    /**
     * Crea un nuevo usuario
     *
     * @param ciudadano
     */
    public void crearCiudadano(Ciudadano ciudadano) {
        ciudadanoJPA.create(ciudadano);
    }

    /**
     * Crea una nueva direccion
     *
     * @param direccion
     */
    public void crearDireccion(Direccion direccion) {
        direccionJPA.create(direccion);
    }

    /**
     * Crea un nuevo turno
     *
     * @param turno
     */
    public void crearTurno(Turno turno) {
        turnoJPA.create(turno);
    }

    /**
     * Crea un nuevo tramite
     *
     * @param tramite
     */
    public void crearTramite(Tramite tramite) {
        tramiteJPA.create(tramite);
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Persistencia Buscar methods.">
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

    public List<Turno> listarTurnos() {
        return turnoJPA.findTurnoEntities();
    }

    public Turno buscarTurno(Long id) {
        return turnoJPA.findTurno(id);
    }

    /**
     * Hace una consulta para buscar turnos en una determinada fecha
     *
     * @param ld LocalDate
     * @return List<Turno>
     */
    public List<Turno> buscarTurnoBy(LocalDate ld) {
        return turnoJPA.findTurnoByDate(ld);
    }

    /**
     * Filtra la lista de turnos por le estado indicado
     *
     * @param ld LocalDate
     * @param std Estado enum
     * @return List<Turno>
     */
    public List<Turno> buscarTurnoBy(LocalDate ld, Estado std) {
        return buscarTurnoBy(ld).stream()
                .filter(t -> t.getEstado().equals(std))
                .toList();
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Persistencia Editar methods.">
    public void editarTurno(Turno turno) {
        try {
            turnoJPA.edit(turno);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // </editor-fold>
}
