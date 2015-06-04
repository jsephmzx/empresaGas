<%-- 
    Document   : user
    Created on : 15-11-2014, 11:36:14 PM
    Author     : Natalia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
        <!-- Latest compiled and minified CSS -->
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/bootstrap-theme.min.css" rel="stylesheet">
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
        <!-- Add custom CSS here -->
        <link href="css/empresa-css.css" rel="stylesheet">
        <link href="css/dcdrilldown.css" rel="stylesheet">
        <link href="css/simple-sidebar.css" rel="stylesheet">
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <title>| INICIO </title> 
        <link rel="icon" type="image/png" href="/Proyectoempresa/iconos/Agregaredificio.png">
        <link rel="stylesheet" href="css/simple-sidebar.css">
        <link rel="stylesheet" type="text/css" href="css/jquery-ui-1.7.2.custom.css" />
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script>
        <script type='text/javascript' src='js/jquery.cookie.js'></script>
        <script type='text/javascript' src='js/jquery.dcdrilldown.1.2.min.js'></script>
        <script type="text/javascript">
        </script>
        <script type="text/javascript">
            jQuery(document).ready(function($) {
                jQuery('#drilldown').dcDrilldown();
            });
        </script>
    </head>
    <body>

        <div  id="wrapper" class="container">
                <div id="sidebar-wrapper">                  
                        <ul class="sidebar-nav" >
                            <div>
                                <h3 style="color: #ffffff !important;white-space: pre!important;"> Hola  <c:out value="${nombre}"/></h3>                   
                            </div>
                            <li class="sidebar-brand"><a href="user.jsp"><span class="glyphicon glyphicon-home" ></span> Inicio</a></li>
                            <li>
                                <form action="/Proyectoempresa/edificioListaServlet" role="form" method="post">
                                    <div class=" glyphicon glyphicon-pencil">
                                        <input type="text"  name="nombre_sesion" value="<c:out value="${nombre}"/>" hidden="true"/>
                                        <input type="text"  name="listarEdificio" value="TODAS" hidden="true"/>                                    
                                        <input type="submit"  style="background:  #000000!important;width: 150px !important;color: #999999!important; border:   black!important;" value="Mantenedor Clientes"/>    
                                    </div>
                                </form>
                            </li>
                            <c:if test="${noadmin == 0}">
                            <li>                                
                                    <form action="/Proyectoempresa/usuarioListaServlet" role="form" method="post">
                                        <div class="glyphicon glyphicon-user">
                                            <input type="text"  name="nombre_sesion" value="<c:out value="${nombre}"/>" hidden="true"/>
                                            <input type="text"  name="listarUsuarios" value="TODOS" hidden="true"/>                                    
                                            <input type="submit"  style="background:  #000000!important;width: 160px !important;color: #999999!important; border:   black!important;" value="Mantenedor Usuarios"/>    
                                        </div>
                                    </form>
                                </li>
                            </c:if>
                                <br>
                            <li class="sidebar-brand"><form action="/Proyectoempresa/fillContrasenaServlet" role="form" method="post" > 
                                    <div class="form-group sidebar-brand btn-block" >
                                        <input type="text"  name="nombre_sesion" value="<c:out value="${nombre}"/>" hidden="true"/>
                                        <input type="submit" class="btn boton-perfil btn-info btn-radius" style="background: #1a6ecc !important;width: 200px !important;" value="Cambiar Contraseña"/>    
                                    </div>                 
                                </form></li>                            
                            <li class="sidebar-brand"><form action="/Proyectoempresa/fillModificarUserServlet" role="form" method="post" > 
                                    <div class="form-group sidebar-brand btn-block" >
                                        <input type="text"  name="nombre_sesion" value="<c:out value="${nombre}"/>" hidden="true"/>
                                        <input type="submit" class="btn boton-perfil btn-info btn-radius" style="background: #1a6ecc !important;width: 200px !important;" value="Cambiar Email"/>    
                                    </div>                                     
                                </form></li>
                            <li class="sidebar-brand"><div class="form-group sidebar-brand btn-block">
                                    <input type="button" onclick=window.location.assign("/Proyectoempresa/finServlet") style="background: #1a6ecc !important;width: 200px !important;" class="btn btn-primary btn-sm btn-block" value="Cerrar Sesion"/>
                                </div></li>

                        </ul>
                </div>
            
                <div id="page-content-wrapper">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-12">
                                <a href="#menu-toggle" class="btn btn-default left-boton" id="menu-toggle"> Menu</a>
                                <div class="img-logo">
                                    <img class="img-usuario" src="iconos/logo.png"/> 
                                    <h1>Acceso denegado.</h1>
                                </div>
                                <div class="form-group">
                                    <c:if test="${agregar == 1}">
                                        <h5>Usuario Ingresado Satisfactoriamente</h5>
                                    </c:if>
                                    <c:if test="${agregar1 == 1}">
                                        <h5>Contraseña Modificada Correctamente</h5>                     
                                    </c:if>
                                    <c:if test="${agregar2 == 2}">
                                        <h5>Email Modificado Correctamente</h5>                     
                                    </c:if>
                                </div>     

                            </div>
                        </div>
                    </div>
                </div>           
        </div>
        <!-- JavaScript -->
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/bootstrap.js"></script>            
        <script>
            $("#menu-toggle").click(function(e) {
                e.preventDefault();
                $("#wrapper").toggleClass("active");
            });
        </script> 
        <!-- -----
        Se agregan
       edificio
       fecha
       administrador
       detalle edificio
       ------
       No se agregan
       ------
       gas
       empresaGas -->

    </body>
</html>

