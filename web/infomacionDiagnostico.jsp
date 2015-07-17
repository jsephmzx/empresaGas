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

                            <!-- pintar los datos del depto obtenido desde la bd-->
                            <table class="table table-bordered">
                                <tr>
                                    <td>Numero Departamento</td>
                                    <td><c:out value="${depto.numDepartamento}"></c:out></td>
                                    </tr>
                                    <tr>
                                        <td>Sello</td>
                                        <td>
                                        <c:if test="${depto.selloDepartamento == 1}">
                                            VERDE
                                        </c:if>
                                        <c:if test="${depto.selloDepartamento == 2}">
                                            ROJO
                                        </c:if>
                                        <c:if test="${depto.selloDepartamento == 3}">
                                            AMARILLO
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Numero</td>
                                    <td><c:out value="${depto.numDepartamento}"></c:out></td>
                                    </tr>
                                    <tr>
                                        <td>Propietario</td>
                                        <td><c:out value="${depto.getPropietario()}"></c:out></td>
                                    </tr>
                                    <tr>
                                        <td>Observación</td>
                                        <td><c:out value="${depto.getObservacion()}"></c:out></td>
                                    </tr>
                                </table>
                                <table class="table table-bordered">
                                    <tr>
                                        <th>Artefacto</th>
                                        <th>Potencia (kW)</th>
                                    </tr>
                                <c:forEach var="list" items="${list}">

                                    <tr>
                                        <td><c:out value="${list.tipoArtefacto}"></c:out></td>
                                        <td><c:out value="${list.potenciaArtefacto}"></c:out></td>
                                        </tr>

                                </c:forEach>
                            </table>
                            <table class="table table-bordered">
                                <tr>
                                    <td><label>Defectos</label></td>   
                                </tr>
                                
                                <c:forEach var="listDefecto" items="${listDefecto}">
                                <tr>
                                    <td>
                                        <c:if test="${listDefecto.defectoDepto == 1}">
                                           Fuga de gas en artefacto
                                        </c:if>
                                        <c:if test="${listDefecto.defectoDepto == 2}">
                                           Fuga de gas en red
                                        </c:if>
                                        <c:if test="${listDefecto.defectoDepto == 3}">
                                           Artefacto tipo B C sin conducto de evacuación de gases de la combustión instalados en recintos interiores
                                        </c:if>
                                        <c:if test="${listDefecto.defectoDepto == 4}">
                                           Existencia de concentración de monóxido de carbono (CO) superior a 50 ppm
                                        </c:if>   
                                        <c:if test="${listDefecto.defectoDepto == 5}">
                                           Dormitorio con artefactos a gas tipo A
                                        </c:if> 
                                        <c:if test="${listDefecto.defectoDepto == 6}">
                                           Lectura de tiro igual o superior a 0, en conducto individual o colectivo
                                        </c:if>
                                           <c:if test="${listDefecto.defectoDepto == 7}">
                                           Recinto sin ventilaciones que cuente con calefactores tipo A
                                        </c:if>
                                           <c:if test="${listDefecto.defectoDepto == 8}">
                                           Conexión al abastecimiento de gas por medio de un tubo flexible no metálico (elastómero) en contacto consuperficie caliente
                                        </c:if>
                                           <c:if test="${listDefecto.defectoDepto == 9}">
                                           Arranque sin artefacto a gas conectado y que no se encuentra debidamente sellado
                                        </c:if>
                                           <c:if test="${listDefecto.defectoDepto == 10}">
                                           Flexible de conexión visiblemente dañado
                                        </c:if>
                                           <c:if test="${listDefecto.defectoDepto == 11}">
                                           Sin tapón arranque
                                        </c:if>
                                           <c:if test="${listDefecto.defectoDepto == 12}">
                                           Sin ventilación inferior
                                        </c:if>
                                           <c:if test="${listDefecto.defectoDepto == 13}">
                                           Ducto del calefont no cumple
                                        </c:if>
                                           <c:if test="${listDefecto.defectoDepto == 14}">
                                           Ducto del calefont no sale a los cuatro vientos
                                        </c:if>
                                           <c:if test="${listDefecto.defectoDepto == 15}">
                                           Artefacto no enciende
                                        </c:if>
                                           
                                           <c:if test="${listDefecto.defectoDepto == 17}">
                                           Artefacto cocina plato Q1 se apaga en minino o no enciende o llama envolvente lo mismo para los 4 platos
                                        </c:if>
                                    </td>
                                </tr>
                                </c:forEach>
                                         
                            </table>    
                            <!-- -->
                            <button class="btn btn-primary btn-warning" name="btnAdd" type="button" onclick="location.href = 'conductoMainServlet';"><font size="1"><strong>Volver</strong></font></button>
                             
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

