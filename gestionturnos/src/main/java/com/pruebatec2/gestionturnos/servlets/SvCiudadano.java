/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.pruebatec2.gestionturnos.servlets;

import com.pruebatec2.gestionturnos.logica.Ciudadano;
import com.pruebatec2.gestionturnos.persistencia.ControladoraPersistencia;
import com.pruebatec2.gestionturnos.utilidades.CUtils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Carlos Jaquez
 */
@WebServlet(name = "SvCiudadano", urlPatterns = {"/SvCiudadano"})
public class SvCiudadano extends HttpServlet {

    ControladoraPersistencia controladora = new ControladoraPersistencia();

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
        System.out.println("-------------------- Estoy en post ciudadano --------------");
        
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String dni = request.getParameter("dni");
        String telefono = request.getParameter("telefono");
        String calle = request.getParameter("calle");
        String portal = request.getParameter("portal");
        String piso = request.getParameter("piso");
        String puerta = request.getParameter("puerta");
        String ciudad = request.getParameter("ciudad");
        String provincia = request.getParameter("provincia");
        String cp = request.getParameter("cp");

        String direccionCompleta = calle
                + ", " + portal
                + ", " + piso
                + ", " + puerta
                + ", " + ciudad
                + ", " + provincia
                + ", " + cp;

        Ciudadano ciudadano = new Ciudadano(nombre, apellido, dni, telefono, direccionCompleta);
        
        controladora.crearCiudadano(ciudadano);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
