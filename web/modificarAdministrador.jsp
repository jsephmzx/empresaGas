<%-- 
    Document   : modificarAdministrador
    Created on : 08-03-2015, 12:30:25 PM
    Author     : Natalia
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
        <title>Administrador | MODIFICAR USUARIO </title> 
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
                                    <input type="submit"  style="background:  #000000!important;width: 150px !important;color: #999999!important; border:   black!important;" value="Mantenedor Usuarios"/>    
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
                                <h4><b>MODIFICAR TIPO USUARIO</b></h4>
                                <div class="form-group">
                                    <c:if test="${modificar == 1}">
                                        <div class="alert alert-success color-negro">
                                            <h5>Usuario Modificado Satisfactoriamente</h5>
                                        </div>
                                    </c:if>
                                    <c:if test="${agregar5 == 5}">
                                        <div class="alert alert-danger">
                                            <label style="color: #e45c00 !important;"><h5><b>*  Seleccione tipo de usuario</b></h5></label>
                                        </div> 
                                    </c:if>
                                </div> 
                                <form action="adminChangeServlet" role="form" method="post"> <%--fillModificarAdminServlet en el boton de ingreso --%>
                                    <div class="col-lg-7">
                                        <div hidden="true">
                                            <input  type="text" class="form-control" name="listarUsuarios" required="true" value="<c:out value="${listarUsuarios}"/>"/>
                                        </div>
                                        <div hidden="true">
                                            <input  type="text" class="form-control" name="id_usuario" required="true" value="<c:out value="${us.idUsuario}"/>"/>
                                        </div>
                                        <div class="form-group" hidden="true">
                                            <label >Nombre usuario <label style="text-decoration-color: #aaa !important;"><h6>* Nombre utilizado para acceder al sistema.</h6></label></label>
                                            <input  type="text" class="form-control" name="nombre_usuario" required="true" value="<c:out value="${us.nombreUsuario}"/>"/>
                                        </div>
                                        <div class="form-group" >                               
                                            <label>Tipo de Usuario <c:out value="${us.tipoUsuario}"/></label>
                                            <div class="padding-radio"> 
                                                <div class="radio">
                                                    <input  type="radio" name="tipo_usuario" value="admin" <c:if test="${us.tipoUsuario == 'admin'}">checked</c:if>>Administrador
                                                        <div class="radio">
                                                            <input  type="radio" name="tipo_usuario" value="vende"<c:if test="${us.tipoUsuario == 'vende'}">checked</c:if>>Vendedor 
                                                        </div>
                                                    </div>
                                                </div>
                                            </div> 
                                            <div hidden="true">
                                                <input  type="text" class="form-control" name="email_usuario" required="true" value="<c:out value="${us.emailUsuario}"/>"/>
                                        </div>
                                        <div hidden="true">
                                            <input type="password" class="form-control" name="contrasena" value="<c:out value="${us.contrasena}"/>"/>
                                        </div>
                                        <div class="form-group">
                                            <input type="text"  name="nombre_sesion" value="<c:out value="${nombre}"/>" hidden="true"/> 
                                            <input type="submit" class="btn btn-default btn-md" value="Modificar Tipo Usuario" />

                                        </div>
                                    </div>
                                </form>
                                <div class="col-lg-7">
                                    <form action="/Proyectoempresa/usuarioListaServlet" role="form" method="post">
                                        <div class="form-group">
                                            <input type="text"  name="nombre_sesion" value="<c:out value="${nombre}"/>" hidden="true"/>
                                            <input type="text"  name="listarUsuarios" value="TODOS" hidden="true"/>                                    
                                            <input type="submit" class="btn btn-danger btn-md" value="Volver al listado usuario"/>    
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
