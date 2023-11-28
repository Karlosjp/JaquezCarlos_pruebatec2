/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.pruebatec2.gestionturnos.servlets;

import com.pruebatec2.gestionturnos.logica.Turno;
import com.pruebatec2.gestionturnos.persistencia.ControladoraPersistencia;
import com.pruebatec2.gestionturnos.utilidades.Estado;
import com.pruebatec2.gestionturnos.utilidades.Recursos;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "SvGestionTurno", urlPatterns = {"/SvGestionTurno"})
public class SvGestionTurno extends HttpServlet {

    private ControladoraPersistencia controladora = new ControladoraPersistencia();

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
        String idTurno = request.getParameter("id");

        Turno turno = controladora.buscarTurno(Long.valueOf(idTurno));

        turno.setEstado(Estado.EN_PROGRESO);
        controladora.editarTurno(turno);

        request.setAttribute("turno", turno);

        request.getRequestDispatcher(Recursos.GESTIONARTURNO).forward(request, response);
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
        String idTurno = request.getParameter("id");

        Turno turno = controladora.buscarTurno(Long.valueOf(idTurno));

        turno.setEstado(Estado.YA_ATENDIDO);
        controladora.editarTurno(turno);

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
