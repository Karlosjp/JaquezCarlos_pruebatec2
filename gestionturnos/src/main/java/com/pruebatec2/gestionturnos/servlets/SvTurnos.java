/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.pruebatec2.gestionturnos.servlets;

import com.pruebatec2.gestionturnos.logica.Ciudadano;
import com.pruebatec2.gestionturnos.logica.Controladora;
import com.pruebatec2.gestionturnos.logica.Tramite;
import com.pruebatec2.gestionturnos.logica.Turno;
import com.pruebatec2.gestionturnos.persistencia.ControladoraPersistencia;
import com.pruebatec2.gestionturnos.utilidades.CUtils;
import com.pruebatec2.gestionturnos.utilidades.Estado;
import com.pruebatec2.gestionturnos.utilidades.Recursos;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Charly Cimino Aprendé más Java en mi canal:
 * https://www.youtube.com/c/CharlyCimino Encontrá más código en mi repo de
 * GitHub: https://github.com/CharlyCimino
 */
@WebServlet(name = "SvTurnos", urlPatterns = {"/SvTurnos"})
public class SvTurnos extends HttpServlet {

    private Controladora controladora = new Controladora();

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fecha = request.getParameter("fecha");
        String estado = request.getParameter("estado");

        List<Turno> lista = null;

        switch (estado) {
            case "EN_ESPERA":
                lista = controladora.buscarTurnoBy(CUtils.stringToDate(fecha), Estado.EN_ESPERA);
                break;
            case "EN_PROGRESO":
                lista = controladora.buscarTurnoBy(CUtils.stringToDate(fecha), Estado.EN_PROGRESO);
                break;
            case "YA_ATENDIDO":
                lista = controladora.buscarTurnoBy(CUtils.stringToDate(fecha), Estado.YA_ATENDIDO);
                break;
            default:
                lista = controladora.buscarTurnoBy(CUtils.stringToDate(fecha));
        }

        request.setAttribute("turnos", lista);

        request.getRequestDispatcher(Recursos.MOSTRARTURNOS).forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idCiudadano = request.getParameter("idCiudadano");
        String descripcion = request.getParameter("descripcionTramite");
        String fecha = request.getParameter("inputFecha");
        LocalDate localDate = CUtils.stringToDate(fecha);

        Ciudadano ciudadano = controladora.buscarCiudadano(Long.valueOf(idCiudadano));

        Turno turno = new Turno(CUtils.asignarTurno(), localDate, Estado.EN_ESPERA);
        turno.setCiudadano(ciudadano);
        controladora.crearTurno(turno);

        Tramite tramite = new Tramite(descripcion);
        tramite.setTurno(turno);
        controladora.crearTramite(tramite);

        response.sendRedirect(Recursos.INDEXJSP);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
