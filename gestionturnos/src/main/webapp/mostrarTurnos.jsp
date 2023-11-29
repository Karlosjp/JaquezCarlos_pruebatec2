<%-- 
    Document   : cambiarEstado
    Created on : 28 nov 2023, 3:40:26
    Author     : Carlos Jaquez
--%>

<%@page import="java.time.LocalDate"%>
<%@page import="com.pruebatec2.gestionturnos.utilidades.Estado"%>
<%@page import="java.util.List"%>
<%@page import="com.pruebatec2.gestionturnos.logica.Turno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Turnos</title>
    </head>
    <body>
        <jsp:include page="nav-bar.jsp"/>
        <div class="container mt-4">
            <form action="SvTurnos" method="get">
                <div class="form-row">
                    <div class="form-group col-md-3">  
                        <label for="inputAnno">Fecha:</label>
                        <input type="date" id="inputFecha" name="fecha" value="<%=LocalDate.now()%>" required>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="estado">Estado: </label>
                        <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" name="estado"> 
                            id="estado" name="estado" >
                            <option value="-1" selected>None</option>
                            <% for (Estado e : Estado.values()) {%>
                            <option value=<%=e%>>
                                <%=e%>
                            </option>
                            <% }%>
                        </select>
                    </div>
                    <div class="form-group col-md-2">
                        <button type="submit" class="btn btn-primary">Buscar</button>  
                    </div>
                </div>
            </form>
            <div class="card-header align-items-center">
                <h5 class="mb-0">Lista de turnos</h5>
            </div>
            <% if (request.getAttribute("turnos") != null) {
                    List<Turno> turnos = (List<Turno>) request.getAttribute("turnos");
                    if (turnos.isEmpty()) {%>
            <div class="card-header align-items-center">
                <h5 class="mb-0">No hay datos registrados</h5>
            </div>            
            <%} else {%>

            <div class="card-group">
                <% for (Turno turno : (List<Turno>) request.getAttribute("turnos")) {%>

                <div class="col">
                    <div
                        <% switch (turno.getEstado()) {
                                case EN_ESPERA:%>
                        class="card text-dark bg-warning mb-3"
                        <% break;
                            case EN_PROGRESO: %>
                        class="card text-white bg-success mb-3"
                        <% break;
                            default: %>
                        class="card text-white bg-dark mb-3"
                        <%}%>
                        style="max-width: 18rem; min-width: 13rem;">
                        <div class="card-header"><%=turno.getFecha().toString()%></div>
                        <div class="card-body">
                            <div>            
                                <h5 class="card-title"><%=turno.getNumero()%></h5>
                                <p class="card-text"><%=turno.getTramite().getDescripcion()%></p>
                            </div>
                            <div class="align-items-center">
                                <form action="SvGestionTurno" method="get">
                                    <input type="text" class="form-control" id="inputCalle" 
                                           name="id" value="<%=turno.getId()%>"
                                           aria-label="Disabled input" hidden>
                                    <button type="submit" class="nav-link btn btn-info"> Gestionar</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <% }
                    }%>
            </div>
            <% } else {%>
            <div class="card-header align-items-center">
                <h5 class="mb-0">No hay datos registrados</h5>
            </div>
            <% }%>
        </div>
    </body>
</html>
