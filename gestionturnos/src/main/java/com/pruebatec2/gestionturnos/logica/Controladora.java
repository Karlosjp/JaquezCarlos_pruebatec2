/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatec2.gestionturnos.logica;

import com.pruebatec2.gestionturnos.persistencia.ControladoraPersistencia;
import com.pruebatec2.gestionturnos.utilidades.Estado;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos Jaquez
 */
public class Controladora {

    ControladoraPersistencia controlP = new ControladoraPersistencia();

    // <editor-fold defaultstate="collapsed" desc="Persistencia Creacion methods. ">
    public void crearCiudadano(Ciudadano ciudadano) {
        controlP.crearCiudadano(ciudadano);
    }

    public void crearDireccion(Direccion direccion) {
        controlP.crearDireccion(direccion);
    }

    public void crearTurno(Turno turno) {
        controlP.crearTurno(turno);
    }

    public void crearTramite(Tramite tramite) {
        controlP.crearTramite(tramite);
    }

    /**
     * Si no existe registro de la direccion crea un nuevo registro. Devuelve el
     * registro encontrado o el recien creado
     *
     * @param direccion
     * @return Object Direccion
     */
    public Direccion existeSinoCrea(Direccion direccion) {
        Direccion d = controlP.existe(direccion);

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
        Ciudadano c = controlP.existe(ciudadano);

        if (c == null) {
            crearCiudadano(ciudadano);
        }
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Persistencia Buscar methods.">
    public List<Ciudadano> listarCiudadanos() {
        return controlP.listarCiudadanos();
    }

    public Ciudadano buscarCiudadano(Long id) {
        return controlP.buscarCiudadano(id);
    }

    public List<Turno> listarTurnos() {
        return controlP.listarTurnos();
    }

    public Turno buscarTurno(Long id) {
        return controlP.buscarTurno(id);
    }

    /**
     * Hace una consulta para buscar turnos en una determinada fecha
     *
     * @param ld LocalDate
     * @return List<Turno>
     */
    public List<Turno> buscarTurnoBy(LocalDate ld) {
        return controlP.buscarTurnoBy(ld);
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
        controlP.editarTurno(turno);
    }
    // </editor-fold>

}
