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

        <script type="text/javascript" src="paging.js"></script>
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
                            <h3><c:if test="${edifExistenciaConduc == 'n'}">Departamentos</c:if><c:if test="${edifExistenciaConduc != 'n'}">Conductos</c:if> Edificio :<c:out value="${edifName}"></c:out></h3>
                            <c:if test="${msgErrorExistencia != null}">
                                <div class="alert alert-danger">
                                    <c:out value="${msgErrorExistencia}"></c:out>
                                    </div>
                            </c:if>

                            <br/>
                            <div class="col-lg-7 ">
                                <!--<button class="btn btn-primary" name="btnAdd" type="button" onclick="location.href = 'agregarConducto.jsp';"><font size="1"><strong>AGREGAR CONDUCTO</strong></font></button>-->
                                <div class="form-horizontal div-conductos">
                                    <ul class="nav nav-tabs">
                                       <c:if test="${edifExistenciaConduc != 'n'}">
                                        <c:set var="sum" value="${1}" />
                                        <c:forEach var="list" items="${list}">
                                            <li class="li-conductos"><a style="margin-right: 2px !important;
                                                                        line-height: 1.42857 !important;
                                                                        border: 1px solid !important;
                                                                        border-radius: 0px !important;
                                                                        color : white !important;
                                                                        background: #1A6ECC !important" href="conductoMainServlet?id=<c:out value="${list.idConducto}" />"><c:out value="${sum}" /></a></li>
                                                <c:set var="sum" value="${sum + 1}" />
                                            </c:forEach>
                                       </c:if> 
                                    </ul>
                                </div>
                                <table class="table table-condensed table-responsive" id="results">
                                    <tr>
                                        <td><label>N°</label></td>
                                        <td><label>N° Depto</label></td>
                                        <td><label>Modificar</label></td>
                                        <td><label>Eliminar</label></td>
                                    </tr>
                                    <!-- pintar los datos de deptos obtenidos de la bd-->
                                    <c:set var="sumDepto" value="${1}" />
                                    <c:forEach var="listDepto" items="${listDepto}">
                                        <input type="text" hidden="true" name="id_edificio" value="<c:out value="${idEdificio}"></c:out>"/>
                                            <tr>
                                                <td><label><c:out value="${sumDepto}"></c:out></label></td>
                                            <td><a href="listInfoDiagServlet?id=<c:out value="${listDepto.idDepartamento}"></c:out>"><c:out value="${listDepto.numDepartamento}"/></a></td>
                                                <!--<td><select name="ducto" class="form-control-textos">
                                                        <option value=1 <c:if test="${listDepto.selloDepartamento == 1}" > selected </c:if>> VERDE</option>
                                                        <option value=2 <c:if test="${listDepto.selloDepartamento == 2}" > selected </c:if>> ROJO</option>  
                                                        <option value=3 <c:if test="${listDepto.selloDepartamento == 3}" > selected </c:if> > AMARILLO</option>
                                                        </select></td>-->



                                                   <!-- <button class="btn btn-primary" name="btnAdd" type="button" onclick="location.href = 'informacionGetAddServlet?id=<c:out value="${listDepto.idDepartamento}"></c:out>';"><font size="1"><strong>AGREGAR INFORMACIÓN</strong></font></button></td>
                                            <td><c:if test="${listDepto.info == 0}">
                                                    <button class="btn btn-primary btn-success" name="btnAdd" type="button" disabled="true" onclick="location.href = 'diagnosticoGetAddServlet?id=<c:out value="${listDepto.idDepartamento}"></c:out>';"><font size="1"><strong>AGREGAR DIAGNOSTICO</strong></font></button>
                                            </c:if>
                                            <c:if test="${listDepto.info != 0}">
                                            <button class="btn btn-primary btn-success" name="btnAdd" type="button" onclick="location.href = 'diagnosticoGetAddServlet?id=<c:out value="${listDepto.idDepartamento}"></c:out>';"><font size="1"><strong>AGREGAR DIAGNOSTICO</strong></font></button></td>   
                                            </c:if>  -->

                                            <td><button class="btn btn-primary btn-warning" name="btnAdd" type="button"  onclick="location.href = 'informacionGetUpdateServlet?id=<c:out value="${listDepto.idDepartamento}"></c:out>';"><font size="1"><strong>MODIFICAR</strong></font></button></td>    
                                            <td><button class="btn btn-primary btn-danger" name="btnAdd" type="button"  onclick="location.href = 'departamentoDeleteServlet?idDepto=<c:out value="${listDepto.idDepartamento}"></c:out>';"><font size="1"><strong>ELIMINAR</strong></font></button></td>      
                                            </tr> 

                                        <c:set var="sumDepto" value="${sumDepto + 1}" />
                                        <div style="display:none; border: 0px;" class="pagination" id="NavPosicion"></div>


                                    </c:forEach>

                                    <!-- -->

                                </table>
                                <c:if test="${listDepto != null}">
                                    <div class="row">
                                        <div class="col-md-offset-5">
                                            <div id="pageNavPosition"></div>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${idConducto !=null}">
                                        <br/>
                                        <button class="btn btn-primary" name="btnAdd" type="button" onclick="location.href = 'departamentoGetAddServlet?id=<c:out value="${idConducto}"></c:out>';"><font size="1"><strong>AGREGAR DEPARTAMENTO</strong></font></button>
                                        <button class="btn btn-warning" name="btnmodcon"  onclick="location.href = 'conductoGetServlet?id=<c:out value="${idConducto}"></c:out>';"><font size="1"><strong>Condiciones</strong></font></button>
                                    
                                </c:if>  
                                <c:if test="${edifExistenciaConduc == 'n'}">
                                    <button class="btn btn-primary" name="btnAdd" type="button" onclick="location.href = 'departamentoGetAddServlet?id=<c:out value="${idEdificio}"></c:out>';"><font size="1"><strong>AGREGAR DEPARTAMENTO</strong></font></button>
                                    <button class="btn btn-warning" name="btnmodcon"  onclick="location.href = 'edifConductoGetServlet?id=<c:out value="${idEdificio}"></c:out>';"><font size="1"><strong>Condiciones</strong></font></button>
                                </c:if>        
                            </div>
                            <div class="col-lg-5">
                                <div class="form-group">
                                    <br><br>
                                    <img class="img-login" src="iconos/imagen.jpg">
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
        <script type="text/javascript"><!--
        var pager = new Pager('results', 6);
            pager.init();
            pager.showPageNav('pager', 'pageNavPosition');
            pager.showPage(1);
//--></script>
    </body>
</html>
