<%-- 
    Document   : modificarUsuario
    Created on : 08-03-2015, 02:17:18 PM
    Author     : Natalia
    Funcion    : jsp para modificar email del usuario que esta activo en la sesion
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
        <title>Usuario| MODIFICAR EMAIL </title> 
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
        <div class="container" id="wrapper">
            <div id="sidebar-wrapper">
                <ul class="sidebar-nav" >
                    <div>
                        <h3 style="color: #ffffff !important;white-space: pre!important;"> Hola  <c:out value="${nombrePersona}"/> <c:out value="${apellido}"/></h3>                   
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
                            <a href="#menu-toggle" class="btn btn-default left-boton" id="menu-toggle">Menu</a>
                            <h3>FORMULARIO</h3>
                            <div class="col-lg-12 agregar-admin-vendedor centrar2 color-blanco">
                                <h4><b>MODIFICAR EMAIL</b></h4>
                                <div class="form-group">
                                    <c:if test="${agregar4 == 4}">
                                        <div class="alert alert-danger">
                                            <label style="color: #e45c00 !important;"><h5>*  Email no cumple con el formato requerido</h5></label>
                                        </div>
                                    </c:if>
                                    <c:if test="${email == 1}">
                                        <div class="alert alert-success color-negro">
                                            <h5>Email Modificado Satisfactoriamente</h5>
                                        </div>
                                    </c:if> 
                                </div>
                                <form action="usuarioChangeServlet" role="form" method="post"> <%--fillModificarUserServlet en el boton de ingreso --%>
                                    <div class="col-lg-7">
                                        <div class="form-group" hidden="true">      
                                            <input  type="text" class="form-control" name="nombre_usuario" required="true" value="<c:out value="${us.nombreUsuario}"/>" />
                                        </div>
                                        <div class="form-group">
                                            <label>Email usuario</label>
                                            <input  type="text" class="form-control" name="email_usuario" required="true" value="<c:out value="${us.emailUsuario}"/>"/>
                                        </div>
                                        <div  hidden="true" >
                                            <input  type="text" name="tipo_usuario" value="<c:out  value="${us.tipoUsuario}"/>">                                
                                        </div> 
                                        <div hidden="true">
                                            <input type="password" class="form-control" name="contrasena" value="<c:out value="${us.contrasena}"/>"/>
                                        </div>
                                        <div hidden="true">
                                            <input type="text" class="form-control" name="id_usuario" value="<c:out value="${us.idUsuario}"/>"/>
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" class="btn btn-default btn-md" value="Modificar Email" />
                                        </div>
                                    </div>
                                </form>
                                <div class="col-lg-7">
                                    <form action="user.jsp" role="form" method="post">
                                        <div class="form-group">
                                            <input type="text"  name="nombre_sesion" value="<c:out value="${nombre}"/>" hidden="true"/>                                 
                                            <input type="submit" class="btn btn-danger btn-md" value="Volver al inicio"/>    
                                        </div>
                                    </form>
                                </div>
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
    </body>
</html>

