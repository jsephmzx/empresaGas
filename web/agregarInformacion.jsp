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
        <title>Administrador | AGREGAR INFORMACION DEPARTAMENTO </title> 
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
                            <a href="#menu-toggle" class="btn btn-default left-boton" id="menu-toggle"> Menu</a>
                            <h3>Agregar Información</h3>
                            <div class="col-lg-4"> 
                                <c:if test="${errorArtefactos != null}">
                                    <div class="alert alert-danger">
                                        <c:out value="${errorArtefactos}"></c:out>
                                        </div>
                                </c:if>
                                <c:if test="${ErrorPotenciaCalefont != null}">
                                    <div class="alert alert-danger">
                                        <c:out value="${ErrorPotenciaCalefont}"></c:out>
                                        </div>
                                </c:if>
                                <c:if test="${ ErrorPotenciaCocina!= null}">
                                    <div class="alert alert-danger">
                                        <c:out value="${ErrorPotenciaCocina}"></c:out>
                                        </div>
                                </c:if>
                                <c:if test="${ErrorPotenciaCaldera != null}">
                                    <div class="alert alert-danger">
                                        <c:out value="${ErrorPotenciaCaldera}"></c:out>
                                        </div>
                                </c:if>
                                <c:if test="${ErrorPotenciaCalefactor != null}">
                                    <div class="alert alert-danger">
                                        <c:out value="${ErrorPotenciaCalefactor}"></c:out>
                                        </div>
                                </c:if>
                                <c:if test="${ErrorPotenciaCalefactor != null}">
                                    <div class="alert alert-danger">
                                        <c:out value="${ErrorPotenciaCalefactor}"></c:out>
                                        </div>
                                </c:if>
                                <c:if test="${ErrorPotenciaCentral != null}">
                                    <div class="alert alert-danger">
                                        <c:out value="${ErrorPotenciaCentral}"></c:out>
                                        </div>
                                </c:if>
                                <c:if test="${ErrorObservacion != null}">
                                    <div class="alert alert-danger">
                                        <c:out value="${ErrorObservacion}"></c:out>
                                        </div>
                                </c:if>
                                <c:if test="${ErrorTipoArtefacto != null}">
                                    <div class="alert alert-danger">
                                        <c:out value="${ErrorTipoArtefacto}"></c:out>
                                        </div>
                                </c:if>
                                <c:if test="${ErrorNombreDueno}">
                                    <div class="alert alert-danger">
                                        <c:out value="${ErrorNombreDueno}"></c:out>
                                        </div>
                                </c:if>
                                <c:if test="${msgOk != null}">
                                    <div class="alert alert-success">
                                        <c:out value="${msgOk}"></c:out>
                                        </div>
                                </c:if>
                                <form method="post" role="form" action="informacionAddServlet">
                                    <input type="hidden" name="id_depto" value="<c:out value="${idDepto}"></c:out>"/>
                                    <div class="form-group">
                                        <label for="cant_depto"> Observación  </label>
                                        <input type="text" name="observacion" size="4" class="form-control">  
                                    </div>
                                    <div class="form-group">

                                        <!--<input type="hidden" name="tipo_artefacto" size="4" class="form-control" value="cosina">-->
                                        <label for="Artefacto">Tipo Artefactos Instalados</label><br>
                                        <table>
                                            <tr>
                                                <td><input type="checkbox" id="1" name="tipo_artefacto" value="calefont"> Calefont<BR></td>
                                                <td><input type="text" id="checkArtefacto" class="form-control-textos" name="TextCalefont"/></td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" id="2" name="tipo_artefacto" value="cocina"> Cocina<BR></td>
                                                <td><input type="text" id="checkCosina" class="form-control-textos" name="TextCocina"/></td>
                                            </tr>
                                            <tr>
                                                <td> <input type="checkbox" id="3" name="tipo_artefacto" value="caldera"> Caldera<BR></td>
                                                <td><input type="text" id="checkCaldera" class="form-control-textos" name="textCaldera"/></td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" id="4" name="tipo_artefacto" value="calefactor"> Calefactor<BR></td>
                                                <td><input type="text" id="checkCalefactor" class="form-control-textos" name="textCalefactor"/></td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" id="5" name="tipo_artefacto" value="termo"> Termo<BR></td>
                                                <td><input type="text" id="checkArtefacto" class="form-control-textos" name="textTermo"/></td>
                                            </tr>
                                    <%---        <tr>
                                                <td><input type="checkbox" id="5" name="tipo_artefacto" value="central"> Caldera Central<BR></td>
                                                <td><input type="text" id="checkArtefacto" class="form-control-textos" name="textcentral"/></td>
                                            </tr>--%>
                                        </table>  
                                    </div>
                                    <div class="form-group">
                                        <label> Nombre Dueño</label>
                                        <input type="text" name="nombre_dueno" size="25" class="form-control"/>
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-primary"><font size="1"><strong>AGREGAR INFORMACIÓN</strong></font></button>
                                        <button class="btn btn-primary btn-warning" name="btnAdd" type="button" onclick="location.href = 'conductoMainServlet';"><font size="1"><strong>Volver</strong></font></button>
                                    </div>
                                </form>
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
