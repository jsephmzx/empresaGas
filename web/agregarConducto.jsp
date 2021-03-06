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
        <title>Administrador | AGREGAR CONDUCTO </title> 
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
                            <h3>Agregar Conducto</h3>
                            <div class="col-lg-4"> 
                                <c:if test="${ErrorCantDepto != null}">
                                <div class="alert alert-danger">
                                        <c:out value="${ErrorCantDepto}"></c:out>
                                </div>
                                </c:if>
                                <c:if test="${ErrorSello != null}">
                                <div class="alert alert-danger">
                                        <c:out value="${ErrorSello}"></c:out>
                                </div>
                                </c:if>
                                <c:if test="${msgOk != null}">
                                <div class="alert alert-success">
                                        <c:out value="${msgOk}"></c:out>
                                </div>
                                </c:if>
                                <form method="post" role="form" action="conductoAddServlet">

                                    <%--       *****************        --%>
                                    <div class="form-group">  
                                        <label>Conducto circular  </label>
                                        <div class="radio">
                                            <input type="checkbox" id="" name="conducto_circular"  value="1<c:if test="${conductoCircular == '1'}">checked</c:if>"> si
                                            <input type="checkbox"  id="" name="conducto_circular" value="2 <c:if test="${conductoCircular == '2'}">checked</c:if>"> no                                        
                                        </div>
                                    </div>
                                    <c:if test="${conducto_circular==1}">
                                    <div class="form-group">
                                        <label for="area_conducto"> largo - ancho conducto  </label>
                                        <input type="text" name="largo"  class="form-control">  
                                        <input type="text" name="ancho"  class="form-control"> 
                                    </div>
                                    </c:if>
                                    <div class="form-group">
                                        <div class="form-group">
                                            <label for="area_conducto"> Area conducto  </label>
                                            <input type="text" name="area_conducto"  class="form-control">  
                                        </div>
                                        <div class="form-group">
                                            <label for="cant_depto"> Cantidad Departamentos  </label>
                                            <input type="text" name="cant_depto" class="form-control">  
                                        </div>
                                        <div class="form-group">
                                            <label for="potencia_"> Potencia total  </label>
                                            <input type="text" name="potencia_"  class="form-control">  
                                        </div>
                                        <div class="form-group">  
                                            <label>Estado conducto  </label>
                                            <div class="radio">
                                                <input type="checkbox" id="" name="estado_conducto"  value="aprovado<c:if test="${estadoConducto == 'aprovado'}">checked</c:if>"> Aprovado
                                                <input type="checkbox"  id="" name="estado_conducto" value="pendiente<c:if test="${estadoConducto == 'pendiente'}">checked</c:if>"> Pendiente   
                                                <input type="checkbox"  id="" name="estado_conducto" value="rechazado<c:if test="${estadoConducto == 'rechazado'}">checked</c:if>"> Rechazado
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="potencia_"> Potencia total  </label>
                                            <input type="text" name="potencia_"  class="form-control">  
                                        </div>
                                        <div class="form-group">
                                            <label for="obs_conducto"> Observaciones </label>
                                            <input type="text" name="obs_conducto"  class="form-control">  
                                        </div>


                                    <%-- <div class="form-group">
                                                <label for="sello"> Sello Conducto  </label>
                                                <select name="sello" class="form-control-textos">
                                                    <option value=verde> VERDE</option>
                                                    <option value=rojo> ROJO</option>  
                                                    <option value=amarillo> AMARILLO</option>
                                                </select></div> --%>

                                        <div class="form-group">
                                            <button type="submit" class="btn btn-primary"><font size="1"><strong>AGREGAR CONDUCTO</strong></font></button>
                                            <button class="btn btn-primary btn-warning" name="btnAdd" type="button" onclick="location.href = 'conductoMainServlet?id_edificio=<c:out value="${idEdificio}"/>';"><font size="1"><strong>Volver a lista conducto</strong></font></button>
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
