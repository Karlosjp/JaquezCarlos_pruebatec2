<%-- 
    Document   : crearTurno
    Created on : 27 nov 2023, 19:31:42
    Author     : Carlos Jaquez
--%>

<%@page import="java.time.LocalDate"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="com.pruebatec2.gestionturnos.utilidades.Estado"%>
<%@page import="java.util.List"%>
<%@page import="com.pruebatec2.gestionturnos.logica.Ciudadano"%>
<%@page import="com.pruebatec2.gestionturnos.utilidades.Recursos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Asignar nuevo turno</title>
    </head>
    <body>
        <jsp:include page="nav-bar.jsp"/>
        <div class="container mt-4">
            <div class="row">
                <div class="col-12 mb-3 mb-lg-5">
                    <div class="position-relative card table-nowrap table-card">
                        <% if (request.getAttribute("ciudadanos") != null) {%>
                        <div class="card-header align-items-center">
                            <h5 class="mb-0">Ciudadanos registrados</h5>
                        </div>
                        <div class="table-responsive">
                            <table class="table mb-0">
                                <thead class="small text-uppercase bg-body text-muted">
                                    <tr>
                                        <th>ID</th>
                                        <th>Nombre</th>
                                        <th>Apellido</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for (Ciudadano ciudadano : (List<Ciudadano>) request.getAttribute("ciudadanos")) {%>
                                    <tr class="align-middle">
                                        <td>
                                            <%=ciudadano.getId()%>
                                        </td>
                                        <td>
                                            <%=ciudadano.getNombre()%>
                                        </td>
                                        <td>
                                            <%=ciudadano.getApellido()%>
                                        </td>
                                    </tr>
                                    <% }%>
                                </tbody>
                            </table>
                        </div>
                        <div class="card-footer text-end">
                            <div>
                                <a class="btn btn-primary" href="<%=Recursos.NUEVOCIUDADANO%>" role="button">Registrar ciudadano</a>
                            </div>
                        </div>
                        <% } else {%>
                        <div class="card-header align-items-center">
                            <h5 class="mb-0">No hay ciudadanos registrados</h5>
                        </div>
                        <% }%>
                    </div>
                </div>
            </div>
            <div class="row justify-content-md-center">
                <div class="card-header align-items-center">
                    <h5 class="mb-0">Registrar nuevo turno</h5>
                </div>
                <div class="container mt-4">
                    <form action="SvTurnos" method="post">
                        <div class="form-group col-md">
                            <label for="inputIDCiudadano">Ciudadano: </label>
                            <select class="form-control" size="3" required
                                    aria-label="size 3 select example" 
                                    id="inputIDCiudadano" name="idCiudadano" >
                                <% for (Ciudadano ciudadano : (List<Ciudadano>) request.getAttribute("ciudadanos")) {%>
                                <option value=<%=ciudadano.getId()%>>
                                    <%=ciudadano.getId() + " - " + ciudadano.getNombre() + " " + ciudadano.getApellido()%>
                                </option>
                                <% }%>
                            </select>
                        </div>
                        <div class="form-group col-md">
                            <label for="inputTramite">Descripcion tramite:</label>
                            <textarea class="form-control" id="inputTramite" rows="3" 
                                      name="descripcionTramite" placeholder="breve descripcion del tramite a realizar" required></textarea>
                        </div>
                        <div class="form-group col-md align-items-center">   
                            <label for="inputAnno">Fecha:</label>
                            <input type="date" id="inputFecha" name="inputFecha" min="<%=LocalDate.now()%>" required>
                        </div> 
                        <div class="row justify-content-md-center">
                            <button type="submit" class="btn btn-primary">Registrar</button>  
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
