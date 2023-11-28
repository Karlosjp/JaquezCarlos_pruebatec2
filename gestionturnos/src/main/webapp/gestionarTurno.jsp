<%-- 
    Document   : gestionarTurno
    Created on : 28 nov 2023, 5:16:26
    Author     : Carlos Jaquez
--%>

<%@page import="com.pruebatec2.gestionturnos.logica.Turno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Informacion turno</title>
    </head>
    <body>
        <jsp:include page="nav-bar.jsp"/>
        <% if (request.getAttribute("turno") != null) {
                Turno turno = (Turno) request.getAttribute("turno");%>
        <div class="container mt-4">
            <div class="card border-info" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title"><%=turno.getCiudadano().nombreCompleto()%></h5>
                    <h6 class="card-subtitle mb-2 text-muted"><%=turno.getCiudadano().getDni()%></h6>
                    <p class="card-text"><%=turno.getCiudadano().getDireccion()%></p>
                </div>
            </div>
            <h2>Turno asignado</h2>
            <form action="SvGestionTurno" method="post">
                <input type="text" class="form-control" id="inputCalle" 
                       name="id" value="<%=turno.getId()%>"
                       aria-label="Disabled input" hidden>
                <div class="form-group row">
                    <label for="inputNumero" class="col-sm-2 col-form-label">Numero: </label>
                    <div class="col-sm-10">
                        <input type="text" readonly class="form-control-plaintext"  id="inputNumero" name="numero" value="<%=turno.getNumero()%>">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputFecha" class="col-sm-2 col-form-label">Fecha: </label>
                    <div class="col-sm-10">
                        <input type="text" readonly class="form-control-plaintext"  id="inputFecha" name="fecha" value="<%=turno.getFecha().toString()%>">
                    </div>
                </div>            
                <div class="form-group row">
                    <label for="inputEstado" class="col-sm-2 col-form-label">Estado </label>
                    <div class="col-sm-10">
                        <input type="text" readonly class="form-control-plaintext"  id="inputEstado" name="estado" value="<%=turno.getEstado().getMensaje()%>">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputTramite">Descripcion tramite:</label>
                    <textarea class="form-control" id="inputTramite" rows="3" 
                              name="descripcionTramite" readonly><%=turno.getTramite().getDescripcion()%></textarea>
                </div>
                <div class="row justify-content-md-center">
                    <button type="submit" class="btn btn-primary">Completar</button>  
                </div>
            </form>
        </div>
        <% } else {%>
        <div class="card-header align-items-center">
            <h5 class="mb-0">No hay datos registrados</h5>
        </div>
        <% }%>
    </body>
</html>
