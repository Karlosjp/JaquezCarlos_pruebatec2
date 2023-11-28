<%-- 
    Document   : nav-bar
    Created on : 28 nov 2023, 3:55:57
    Author     : Carlos Jaquez
--%>

<%@page import="com.pruebatec2.gestionturnos.utilidades.Recursos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href=<%=Recursos.HOME%>>Home</a>
                <button class="navbar-toggler" type="button" 
                        data-bs-toggle="collapse" 
                        data-bs-target="#navbarSupportedContent" 
                        aria-controls="navbarSupportedContent" 
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link btn btn-primary" aria-current="page" href="<%=Recursos.HOME%>">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link btn btn-primary" href="<%=Recursos.NUEVOCIUDADANO%>" role="button">Registrar ciudadano</a>
                        </li>
                        <li class="nav-item">
                            <form action="SvCiudadano" method="get">
                                <button type="submit" class="nav-link btn btn-primary">Asignar nuevo turno</button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <form action="SvTurnos" method="get">
                                <button type="submit" class="nav-link btn btn-primary">Gestionar turno</button>
                            </form>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </body>
</html>
