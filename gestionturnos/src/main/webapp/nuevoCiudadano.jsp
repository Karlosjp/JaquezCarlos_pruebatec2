<%-- 
    Document   : nuevoCiudadano
    Created on : 24 nov 2023, 3:45:40
    Author     : Carlos Jaquez
--%>

<%@page import="com.pruebatec2.gestionturnos.utilidades.Recursos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar ciudadano</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-4">
            <div>
                <a class="btn btn-primary" href=<%=Recursos.HOME%> role="button"><- Atras</a>
            </div>
            <h2>Formulario registro</h2>
            <div class="row justify-content-md-center">
                <form action="SvCiudadano" method="post">
                    <div class="form-group">
                        <label for="nombre">Nombre: </label>
                        <input type="text" class="form-control" id="inputNombre" name="nombre" placeholder="Nombre">
                    </div>
                    <div class="form-group">
                        <label for="nombre">Apellidos: </label>
                        <input type="text" class="form-control" id="inputApellido" name="apellido" placeholder="Apellidos">
                    </div>
                    <div class="form-group">
                        <label for="nombre">DNI: </label>
                        <input type="text" class="form-control" id="inputDNI" name="dni" placeholder="12345678A">
                    </div>
                    <div class="form-group">
                        <label for="nombre">Telefono: </label>
                        <input type="text" class="form-control" id="inputTelefono" name="telefono" placeholder="+34 612 34 56 78">
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="inputCity">Calle:</label>
                            <input type="text" class="form-control" id="inputCalle" name="calle" placeholder="Calle">
                        </div>
                        <div class="form-group col-md-2">
                            <label for="inputState">Portal:</label>
                            <input type="text" class="form-control" id="inputPortal" name="portal" placeholder="Nº portal">
                        </div>
                        <div class="form-group col-md-2">
                            <label for="inputZip">Piso:</label>
                            <input type="text" class="form-control" id="inputPiso" name="piso" placeholder="Nº piso">
                        </div>
                        <div class="form-group col-md-2">
                            <label for="inputZip">Puerta:</label>
                            <input type="text" class="form-control" id="inputPuerta" name="puerta" placeholder="Nº puerta">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="inputCity">Ciudad:</label>
                            <input type="text" class="form-control" id="inputCiudad" name="ciudad" placeholder="Ciudad">
                        </div>
                        <div class="form-group col-md-4">
                            <label for="inputState">Provincia:</label>
                            <input type="text" class="form-control" id="inputProvincia" name="provincia" placeholder="Provincia">
                        </div>
                        <div class="form-group col-md-2">
                            <label for="inputZip">CP:</label>
                            <input type="text" class="form-control" id="inputCP" name="cp" placeholder="Nº cp">
                        </div>
                    </div>
                    <div class="row justify-content-md-center">
                        <button type="submit" class="btn btn-primary">Registrar</button>  
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
