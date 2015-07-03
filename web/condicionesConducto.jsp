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
        <title>Administrador | LISTADO LINEAS - DEPARTAMENTOS </title> 
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
        <script src='js/paging.js'></script>
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
                            <c:if test="${msgOk !=null}">
                                <div class="alert alert-success">
                                    <c:out value="${msgOk}"></c:out>
                                    </div>
                            </c:if>
                            <c:if test="${msgErrorPotencia !=null}">
                                <div class="alert alert-danger">
                                    <c:out value="${msgErrorPotencia}"></c:out>
                                </div>
                            </c:if>
                            <h3>Cumple las siguientes condiciones</h3>
                            <form action="conductoUpdateServlet" role="form" method="POST">
                                <input type="hidden" name="id" value="<c:out value="${conducto.idConducto}"></c:out>"/>
                                    <table class="table table-bordered">
                                        <tr>
                                            <td></td>
                                            <td>Si</td>
                                            <td>No</td>
                                        </tr>
                                        <tr>
                                            <td>Condición Sombrete</td>
                                        <c:if test="${conducto.condSombrete == 'n'}">
                                            <td><input type="radio" name="cond_sombrete" value="s"/></td>
                                            <td><input type="radio" name="cond_sombrete" value="n" checked/></td>
                                            </c:if>
                                            <c:if test="${conducto.condSombrete == 's'}">
                                            <td><input type="radio" name="cond_sombrete" value="s" checked/></td>
                                            <td><input type="radio" name="cond_sombrete" value="n" /></td>
                                            </c:if>
                                    </tr>
                                    <tr>
                                        <td>Secciones</td>
                                        <c:if test="${conducto.secciones == 'n'}">
                                            <td><input type="radio" name="secciones" value="s"/></td>
                                            <td><input type="radio" name="secciones" value="n" checked/></td>
                                            </c:if>
                                            <c:if test="${conducto.secciones == 's'}">
                                            <td><input type="radio" name="secciones" value="s" checked/></td>
                                            <td><input type="radio" name="secciones" value="n"/></td>
                                            </c:if>       
                                    </tr>
                                    <tr>
                                        <td>Condición Interior</td>
                                        <c:if test="${conducto.conInterior == 'n'}">
                                            <td><input type="radio" name="con_interior" value="s"/></td>
                                            <td><input type="radio" name="con_interior" value="n" checked/></td>
                                            </c:if>
                                            <c:if test="${conducto.conInterior == 's'}">
                                            <td><input type="radio" name="con_interior" value="s" checked/></td>
                                            <td><input type="radio" name="con_interior" value="n"/></td>
                                            </c:if>
                                    </tr>
                                    <tr>
                                        <td>Relación Lados</td>
                                        <c:if test="${conducto.relacionLados == 's'}">
                                            <td><input type="radio" name="relacion_lados" value="s" checked/></td>
                                            <td><input type="radio" name="relacion_lados" value="n"/></td> 
                                            </c:if>
                                            <c:if test="${conducto.relacionLados == 'n'}">
                                            <td><input type="radio" name="relacion_lados" value="s"/></td>
                                            <td><input type="radio" name="relacion_lados" value="n" checked/></td>
                                            </c:if>

                                    </tr>
                                    <tr>
                                        <td>Prueba Tiro</td>
                                        <c:if test="${conducto.pruebaTiro == 's'}">
                                            <td><input type="radio" name="prueba_tiro" value="s" checked/></td>
                                            <td><input type="radio" name="prueba_tiro" value="n"/></td>
                                            </c:if>
                                            <c:if test="${conducto.pruebaTiro == 'n'}">
                                            <td><input type="radio" name="prueba_tiro" value="s"/></td>
                                            <td><input type="radio" name="prueba_tiro" value="n" checked/></td>
                                            </c:if>

                                    </tr>
                                    <tr>
                                        <td>Toma de Aire</td>
                                        <c:if test="${conducto.tomaAire == 's'}">
                                            <td><input type="radio" name="toma_aire" value="s" checked/></td>
                                            <td><input type="radio" name="toma_aire" value="n"/></td>
                                            </c:if>
                                            <c:if test="${conducto.tomaAire == 'n'}">
                                            <td><input type="radio" name="toma_aire" value="s"/></td>
                                            <td><input type="radio" name="toma_aire" value="n" checked/></td>
                                            </c:if>

                                    </tr>
                                    <tr>
                                        <td>Materialidad</td>
                                        <c:if test="${conducto.materialidad == 's'}">
                                            <td><input type="radio" name="materialidad" value="s" checked/></td>
                                            <td><input type="radio" name="materialidad" value="n"/></td>
                                            </c:if>
                                            <c:if test="${conducto.materialidad == 'n'}">
                                            <td><input type="radio" name="materialidad" value="s"/></td>
                                            <td><input type="radio" name="materialidad" value="n" checked/></td>
                                            </c:if>

                                    </tr>
                                </table>
                                <table class="table table-bordered">
                                    <tr>
                                        <td>Observaciones</td>
                                        <td><input type="text" name="observaciones" value="<c:out value="${conducto.observaciones}"></c:out>"/></td>
                                        </tr>
                                        <tr>
                                            <td>Sello</td>
                                            <td> 
                                                <select name="sello" class="form-control-textos">
                                                    <option value=1 <c:if test="${conducto.sello == 1}">selected</c:if>> VERDE</option>
                                                <option value=2 <c:if test="${conducto.sello == 2}">selected</c:if>> ROJO</option>  
                                                <option value=3 <c:if test="${conducto.sello == 3}">selected</c:if>> AMARILLO</option>
                                                </select></td>
                                        </tr>
                                    </table>
                                    <table class="table table-bordered">
                                        <tr>
                                            <td></td>
                                            <td>Caldera</td>
                                            <td>Calefont</td>
                                            <td>Potencia Artefacto</td>
                                        </tr> 
                                        <tr>
                                            <td>Artefactos</td>
                                        <c:if test="${conducto.artefactoConducto == 'caldera'}">
                                            <td><input type="radio" name="artefacto" value="caldera" checked/></td>
                                            <td><input type="radio" name="artefacto" value="calefont"/></td>
                                            </c:if>
                                            <c:if test="${conducto.artefactoConducto == 'calefont'}">
                                            <td><input type="radio" name="artefacto" value="caldera" /></td>
                                            <td><input type="radio" name="artefacto" value="calefont" checked/></td>
                                            </c:if>
                                            <td><input type="text" name="potencia" value="<c:out value="${conducto.potenciaArtefacto}"></c:out>"/></td>
                                    </tr>
                                </table>            
                                <div class="form-group">
                                    <input type="submit" class="btn btn-primary" value="Guardar Cambios"/>
                                    <button type="button" class="btn btn-warning" onclick="location.href = 'conductoMainServlet';">Volver</button>
                                </div> 
                            </form>

                            <div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>                                   
            <!-- JavaScript -->

            <script src="js/jquery-1.10.2.js"></script>
            <script src="js/bootstrap.js"></script> 
            <script type=”text/javascript”>
                                        var pager = new Pager(‘resultados’, 5);
                                                pager.init();
                                                pager.showPageNav(‘pager’, ‘NavPosicion‘);
                                                pager.showPage(1);
            </script>
            <script>
                $("#menu-toggle").click(function(e) {
                    e.preventDefault();
                    $("#wrapper").toggleClass("active");
                });
            </script> 
    </body>
</html>
