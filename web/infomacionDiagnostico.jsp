<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>Usuario | INFORMACION DEPARTAMENTO </title> 
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
                            <h3>Información y Diagnostico del Departamento Numero : <c:out value="${depto.getNumDepartamento()}"/></h3>
                            <br/>
                            <div class="col-lg-6 ">

                                <div class="form-horizontal">

                                </div>
                                <div class="form-group">
                                    <label>Observación</label>
                                </div>
                                <div class="form-group">
                                    <label class="font-normal-display"><c:out value="${info.observacion}"></c:out></label>
                                    </div>  
                                    <div class="form-group">
                                        <label>Artefactos</label>
                                    </div>
                                    <div class="form-group">
                                        <table class="table table-striped">
                                            <tr>
                                                <th class="encabezado-listado">Artefacto</th>
                                                <th class="encabezado-listado">Potencia (kW)</th>
                                            </tr>
                                        <c:forEach var="list" items="${list}">

                                            <tr>
                                                <td><c:out value="${list.tipoArtefacto}"></c:out></td>
                                                <td><c:out value="${list.potenciaArtefacto}"></c:out></td>
                                                </tr>

                                        </c:forEach>
                                    </table>
                                </div>
                                <div class="form-group">
                                    <label>Nombre Dueño</label>
                                </div>
                                <div class="form-group">
                                    <label class="font-normal-display"><c:out value="${info.nombreDueno}"></c:out></label>
                                    </div>    
                                </div>
                                <div class="form-group">
                                    <label>Detalle</label>
                                </div>    
                                <div class="form-group">
                                    <label class="font-normal-display"><c:out value="${diag.detalle}"></c:out></label>
                                </div>
                                <div class="form-group">
                                    <label>Observación Detalle</label> 
                                </div>
                                <div class="form-group">
                                    <label class="font-normal-display"><c:out value="${diag.obsDetalle}"></c:out></label>
                                </div>
                                <div class="form-group">
                                    <label class="font-normal-display">Sello</label>
                                </div>
                                <div class="form-group">
                                    <label class="font-normal-display">
                                    <c:if test="${depto.selloDepartamento == 1}">
                                        VERDE
                                    </c:if>
                                    <c:if test="${depto.selloDepartamento == 2}">
                                        ROJO
                                    </c:if>
                                    <c:if test="${depto.selloDepartamento == 1}">
                                        AMARILLO
                                    </c:if>
                                </label>
                            </div>
                        </div>
                        <!-- pintar los datos del depto obtenido desde la bd-->


                        <!-- -->


                        <c:if test="${idConducto !=null}">
                            <button class="btn btn-primary" name="btnAdd" type="button" onclick="location.href = 'departamentoGetAddServlet?id=<c:out value="${idConducto}"></c:out>';"><font size="1"><strong>AGREGAR DEPARTAMENTO</strong></font></button>
                            </c:if>  
                    </div>
                    <div class="col-lg-5">
                        <div class="form-group">
                            <br><br>
                            <!--<img class="img-login" src="iconos/imagen.jpg">-->
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

