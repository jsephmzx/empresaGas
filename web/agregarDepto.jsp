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
        <title>Administrador | AGREGAR DEPARTAMENTO </title> 
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
                            <h3>Agregar Depto</h3>
                            <div class="col-lg-6"> 
                                <c:if test="${msgOk != null}">
                                    <div class="alert alert-success">
                                        <c:out value="${msgOk}"></c:out>
                                        </div>
                                </c:if>
                                <c:if test="${errorDuctos != null}">
                                    <div class="alert alert-danger">
                                        <c:out value="${errorDuctos}"></c:out>
                                        </div>
                                </c:if>
                                <c:if test="${errorNumDepto != null}">
                                    <div class="alert alert-danger">
                                        <c:out value="${errorNumDepto}"></c:out>
                                        </div>
                                </c:if>
                                <form method="post" role="form" action="departamentoAddServlet">
                                    <div class="form-group">

                                        <input type="hidden" name="idConducto" size="7" class="form-control" value="<c:out value="${idConducto}"></c:out>" hidden>  
                                        </div>
                                        <!--<div class="form-group">
                                            <label for="cantDuctos">Cantidad de ductos</label>
                                            <input type="text" name="cantDuctos" size="4" class="form-control"/>
                                            
                                        </div>-->
                                        
                                        <div class="form-group">
                                            <label for="sello"> Sello Conducto : </label>
                                            <select name="sello" class="form-control-textos">
                                                <option value=1> VERDE</option>
                                                <option value=2> ROJO</option>  
                                                <option value=3> AMARILLO</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="numero">Numero Depto</label>
                                            <input type="text" name="numero" size="45" class="form-control"/>
                                        </div>
                                        <div class="form-group">

                                            <!--<input type="hidden" name="tipo_artefacto" size="4" class="form-control" value="cosina">-->
                                            <label for="Artefacto">Tipo Artefactos Instalados</label><br>
                                            <table>
                                                <tr>
                                                    <td><input type="checkbox" id="1" name="tipo_artefacto" value="calefont"> Calefont<BR></td>
                                                    <td><input type="text" id="checkArtefacto" class="form-control-textos" name="TextCalefont"/></td>
                                                    <td><label>KW</label></td>
                                                </tr>
                                                <tr>
                                                    <td><input type="checkbox" id="2" name="tipo_artefacto" value="cocina"> Cocina<BR></td>
                                                    <td><input type="text" id="checkCosina" class="form-control-textos" name="TextCocina"/></td>
                                                    <td><label>KW</label></td>
                                                </tr>
                                                <tr>
                                                    <td> <input type="checkbox" id="3" name="tipo_artefacto" value="caldera"> Caldera<BR></td>
                                                    <td><input type="text" id="checkCaldera" class="form-control-textos" name="textCaldera"/></td>
                                                    <td><label>KW</label></td>
                                                </tr>
                                                <tr>
                                                    <td><input type="checkbox" id="4" name="tipo_artefacto" value="calefactor"> Calefactor<BR></td>
                                                    <td><input type="text" id="checkCalefactor" class="form-control-textos" name="textCalefactor"/></td>
                                                    <td><label>KW</label></td>
                                                </tr>
                                                <tr>
                                                    <td><input type="checkbox" id="5" name="tipo_artefacto" value="termo"> Termo<BR></td>
                                                    <td><input type="text" id="checkArtefacto" class="form-control-textos" name="textTermo"/></td>
                                                    <td><label>KW</label></td>
                                                </tr>
                                            <%---        <tr>
                                                        <td><input type="checkbox" id="5" name="tipo_artefacto" value="central"> Caldera Central<BR></td>
                                                        <td><input type="text" id="checkArtefacto" class="form-control-textos" name="textcentral"/></td>
                                                    </tr>--%>
                                        </table>  
                                    </div>
                                    <div class="form-group">
                                        <label>Posibles Defectos</label>
                                        <table class="table table-bordered">
                                            <tr>
                                                <td><input type="checkbox" id="1" name="defectos" value="1"></td>
                                                <td>Fuga de gas en artefacto<BR></td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" id="2" name="defectos" value="2"> </td>
                                                <td>Fuga de gas en red<BR></td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" id="3" name="defectos" value="3"> </td>
                                                <td>Artefacto tipo B C sin conducto de evacuación de gases de la combustión instalados en recintos interiores<BR></td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" id="4" name="defectos" value="4"> </td>
                                                <td>Existencia de concentración de monóxido de carbono (CO) superior a 50 ppm<BR></td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" id="5" name="defectos" value="5"> </td>
                                                <td>Dormitorio con artefactos a gas tipo A<BR></td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" id="6" name="defectos" value="6"> </td>
                                                <td>Lectura de tiro igual o superior a 0, en conducto individual o colectivo<BR></td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" id="7" name="defectos" value="7"> </td>
                                                <td>Recinto sin ventilaciones que cuente con calefactores tipo A<BR></td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" id="8" name="defectos" value="8"></td>
                                                    
                                                <td>Conexión al abastecimiento de gas por medio de un tubo flexible no metálico (elastómero) en contacto consuperficie caliente.<BR></td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" id="9" name="defectos" value="9"></td>
                                                <td>Arranque sin artefacto a gas conectado y que no se encuentra debidamente sellado.<BR></td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" id="10" name="defectos" value="10"> </td>
                                                <td>Flexible de conexión visiblemente dañado.<BR></td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" id="11" name="defectos" value="11"> </td>
                                                <td>Sin tapón arranque<BR></td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" id="12" name="defectos" value="12"> </td>
                                                <td>Sin ventilación inferior<BR></td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" id="13" name="defectos" value="13"> </td>
                                                <td>Ducto del calefont no cumple<BR></td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" id="14" name="defectos" value="14"> </td>
                                                <td>Ducto del calefont no sale a los cuatro vientos<BR></td>
                                            </tr>
                                            <tr>
                                                <td><input type="checkbox" id="15" name="defectos" value="15"> </td>
                                                <td>Artefacto no enciende<BR></td>
                                            </tr>
                                            
                                            <tr>
                                                <td><input type="checkbox" id="17" name="defectos" value="17"></td>
                                                <td>Artefacto cocina plato Q1 se apaga en minino o no enciende o llama envolvente lo mismo para los 4 platos<br></td></td>
                                            </tr>
                                        </table>
                                    </div>    
                                    <div class="form-group">
                                        <label>Observación</label>
                                        <textarea name="observacion" rows="7" cols="50" ></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label>Propietario</label>
                                        <input type="text" name="propietario" class="form-control"/>
                                    </div>    
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-primary"><font size="1"><strong>AGREGAR DEPTO</strong></font></button>
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

