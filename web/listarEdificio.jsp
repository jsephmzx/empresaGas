<%-- 
    Document   : listarEdificio
    Created on : 26-01-2015, 11:31:42 PM
    Author     : Natalia
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>Usuario | LISTAR EDIFICIOS </title> 
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
        <div class="container" id="wrapper"> <%--  sirve para dividir menu lateral del formulario--%>
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
                            <a href="#menu-toggle" class="btn btn-default left-boton" id="menu-toggle"> Menu</a>
                            <h3>Mantenedor edificio</h3>

                            <form class="form-inline" role="form" action="edificioListaServlet">
                                <div class="form-group" >
                                    <label class="padding-list-filtro" for="listarEdificio"> Edificios</label>
                                    <select class="padding-list-filtro form-control" name="listarEdificio" style="height: 35px!important;">
                                        <option value="TODAS" >Todas</option>
                                        <option value="VERDE" >Sello verde</option>
                                        <option value="ROJO" >Sello rojo</option>
                                        <option value="AMARI" >Sello amarillo</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <input type="submit" class="btn btn-default btn-md" value="Filtrar"/>
                                </div>
                            </form> 

                            <a href="index.jsp" >
                                <div class="form-group sidebar-brand btn-block" style="padding-left: 515px!important;" >                 
                                    <input type="button" class="btn boton-perfil btn-info btn-radius" style="background: #1a6ecc !important;width: 200px !important; " value="Agrega Nuevo Cliente"/>    
                                </div>  
                            </a>



                            <table class="table-bordered table-striped table-condensed">
                                <tr>
                                    <%--  <td style="width: 10px!important;"></td>--%>
                                    <td style="width: 150px!important;text-align: center!important;">Nombre edificio</td>
                                    <td style="width: 150px!important;text-align: center!important;">Nombre ejecutivo</td>
                                    <td style="width: 90px!important;text-align: center!important; ">Modificar Cliente</td>
                                    <td style="width: 100px!important;text-align: center!important;">Informacion Cliente</td>
                                    <c:if test="${noadmin == 0}"> <td style="width: 80px!important;text-align: center!important;">Eliminar Cliente</td></c:if>
                                    <td style="width: 80px!important;text-align: center!important;">Conductos</td>
                                </tr>


                                <c:forEach var="listas" items="${lista}">
                                    <form>

                                        <tr>
                                            <%--  <td><span class="badge text-center"> </span></td>--%>
                                        <input name="id_edificio" value="${listas.idEdificio}" hidden="true">
                                        <td><label> <c:out value="${listas.nombreEdificio}" /></label></td>
                                        <td><label> <c:out value="${listas.nombreEjecutivo}" /></label></td>

                                        <td><input type="submit" class="btn btn-danger " value="Modificar"  formaction="/Proyectoempresa/fillModificarServlet?id=<c:out value="${idEdificio}"/>;"><font size="1"></td>
                                        <td><input type="submit" class="btn btn-danger btn-info" value="Mas informacion"  formaction="MasInfoServlet?id=<c:out value="${idEdificio}"></c:out>;"><font size="1"></td>
                                        <c:if test="${noadmin == 0}"><td><input type="submit" class="btn btn-danger btn-md" value="Eliminar"  formaction="edificioDeleteServlet?id=<c:out value="${idEdificio}"></c:out>;"><font size="1"></td></c:if>
                                        <td><input type="submit" class="btn btn-danger btn-md" value="Conductos"  formaction="conductoMainServlet?id_edificio=<c:out value="${idEdificio}"></c:out>;"><font size="1"></td>
                                             
                                    </tr>
                                        </form>
                                </c:forEach>
                                <!-- -->
                            </table>
                            <br><br>                        
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
