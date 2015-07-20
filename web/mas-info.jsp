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
        <title>Usuario| INFORMACION EDIFICIO </title> 
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
        <div id="wrapper" class="container"> <%--  sirve para dividir menu lateral del formulario--%>
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
                            <div class="col-lg-12">
                                <h3>INFORMACION EDIFICIO</h3>
                                <div class="col-lg-5 letra-minimizar">
                                    <div class="form-inline letra-minimizar margen-dere">
                                        <form action="/Proyectoempresa/edificioListaServlet" role="form" method="post">
                                            <div class="margen-dere">
                                                <input type="text"  name="nombre_sesion" value="<c:out value="${nombre}"/>" hidden="true"/>
                                                <input type="text"  name="listarEdificio" value="TODAS" hidden="true"/>                                    
                                                <input type="submit" class="btn btn-danger btn-md" value="Volver al listado edificio"/>    
                                            </div>
                                        </form>
                                    </div>
                                    <table class="table-bordered table-striped table-condensed letra-minimizar ">
                                        <tr>
                                            <td>
                                                <div class="letra-minimizar ancho-col1">
                                                    <label>Nombre Ejecutivo</label>
                                                </div>  
                                            </td>
                                            <td>
                                                <div class="letra-minimizar ancho-col2">
                                                    <label><c:out value="${ed.nombreEjecutivo}"/></label>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label>Tipo Construcción</label>
                                                </div>  
                                            </td>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label><c:out value="${deed.tipoConstruccion}" /></label>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label>Nombre del Edificio</label>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label><c:out value="${ed.nombreEdificio}" /></label>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label>Tipo de instalación</label>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label><c:out value="${inst.nombreInstalacion}"/></label>
                                                </div>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label>Empresa distribuidora de gas</label>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label><c:out value="${em.empresaGas}" /></label>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label>Tipo de gas</label>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label><c:out value="${gas.tipoGas}" /></label>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label>CIIGE anterior</label>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label><c:out value="${deed.ciigeAnterior}" /></label>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label>Dirección</label>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label><c:out value="${ed.direccionEdificio}" /></label>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label>Nombre Administrador</label>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label><c:out value="${ad.nombreAdmin}" /></label>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label>Teléfono Administrador</label>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label><c:out value="${ad.telefonoAdmin}" /></label>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label>Telefono Edificio</label>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label><c:out value="${ed.telefonoEdificio}" /></label>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label>Rut Administrador</label>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label><c:out value="${ad.rutAdmin}" /></label>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="letra-minimizar ancho-col1">
                                                    <label>Año construcción</label>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="letra-minimizar ancho-col2">
                                                    <label><c:out value="${ed.anoEdificio}" /></label>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label>Rut Edificio</label>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label><c:out value="${ed.rutEdificio}" /></label>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label>Norma aplicable</label>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label><c:out value="${ed.normaAplicada}" /></label>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label>Sello edificio</label>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label><c:out value="${ed.selloEdificio}" /></label>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label>Cantidad de pisos</label>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label><c:out value="${ed.cantPisos}" /></label>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label>Cantidad de departamentos</label>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label><c:out value="${ed.cantDepartamentos}" /></label>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label>Cantidad de conductos colectivos</label>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label><c:out value="${ed.cantConductos}" /></label>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label>Potencia Real</label>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label><c:out value="${ed.potenciaReal}" />(kW)</label>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label>Fecha inspección</label>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label><c:out value="${fe.fechaInspeccion}" /></label>
                                                </div>
                                            </td>

                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label>Fecha 1° re inspección</label>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label><c:out value="${fe.fechaPrimera}" /></label>
                                                </div>
                                            </td>

                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label>Fecha 2° re inspección</label>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="letra-minimizar">
                                                    <label><c:out value="${fe.fechaSegunda}" /></label>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>

                                    <%--
                                        <div class="form-group">
                                        <input type="button" class="btn btn-primary btn-lg btn-ductos" value="Ductos"/>
                                        </div> 
                                    --%>
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


