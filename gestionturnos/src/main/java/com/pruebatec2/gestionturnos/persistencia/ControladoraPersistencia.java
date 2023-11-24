package com.pruebatec2.gestionturnos.persistencia;

import com.pruebatec2.gestionturnos.logica.Ciudadano;
import java.util.List;

/**
 *
 * @author Carlos Jaquez
 */
public class ControladoraPersistencia {

    CiudadanoJpaController ciudadanoJPA = new CiudadanoJpaController();

    // <editor-fold defaultstate="collapsed" desc="Persistencia Ciudadano methods. Click on the + sign on the left to edit the code.">
    public void crearCiudadano(Ciudadano ciudadano) {
        ciudadanoJPA.create(ciudadano);
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
