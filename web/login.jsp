<%-- 
    Document   : login
    Created on : 29-05-2014, 05:16:28 PM
    Author     : Natalia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Latest compiled and minified CSS -->
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/bootstrap-theme.min.css" rel="stylesheet">
        <!-- Add custom CSS here -->
        <link href="css/empresa-css.css" rel="stylesheet">
        <link href="css/simple-sidebar.css" rel="stylesheet">
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <title>Bienvenido </title> 
        <link rel="icon" type="image/png" href="/Proyectoempresa/iconos/Agregaredificio.png">
        <link rel="stylesheet" href="css/simple-sidebar.css">
        <link rel="stylesheet" type="text/css" href="css/jquery-ui-1.7.2.custom.css" />
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script>
    </head>
    <body background="/Proyectoempresa/iconos/Fondos.jpg" >
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-lg-offset-4">
                    <div class="col-lg-12 contenedor-login"> 
                        <form action="/Proyectoempresa/ingresarServlet" method="post" role="form">
                            <div class="form-group">
                                <div class="img-responsive">
                                    <img class="img-login" src="iconos/logo.png"/> 
                                </div>  
                                <span style="color: darkgray">Ambos datos son obligatorios</span>
                                <label for="usuario"></label>
                                <input type="text" name="usuario" size="15" placeholder="Ingrese nombre de usuario" class="form-control input-sm" required autofocus>
                                <br>
                                <input type="password" name="contrasena" placeholder="Ingrese su Contraseña" class="form-control input-sm" required>

                                <!-- Mensajes de error en el ingreso -->
                                <div class="form-group">
                                    <c:if test="${agregar == 3 }">
                                        <div class="alert alert-danger">
                                            <h5>Usuario no valido</h5>
                                        </div>
                                    </c:if>
                                    <c:if test="${agregar == 4 }">
                                        <div class="alert alert-danger">
                                            <h5>Contraseña no valida</h5>
                                        </div>
                                    </c:if>
                                </div>
                                <button class="btn btn-danger btn-lg" type="submit" id="errores">Ingresar</button>
                                <input type="button" class="btn btn-warning btn-lg" value="Contactarse" onclick="window.location.href = ''">
                            </div>
                        </form>
                    </div>
                </div>    
            </div>
            <!-- JavaScript -->
            <script src="js/jquery-1.10.2.js"></script>
            <script src="js/bootstrap.js"></script>   

    </body>
</html>
