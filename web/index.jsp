<%-- 
Document   : index
Created on : 15-05-2014, 05:31:24 PM
Author     : Natalia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <!-- Calendario-->
    <script src="js/jscal2.js"></script>
    <script src="js/lang/en.js"></script>
    <link rel="stylesheet" type="text/css" href="css/jscal2.css" />
    <link rel="stylesheet" type="text/css" href="css/border-radius.css" />
    <link rel="stylesheet" type="text/css" href="css/steel/steel.css" />
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
    <script src="funciones.js" type="text/javascript" charset="utf-8"></script>
    <title>Usuario | AGREGAR EDIFICIO </title> 
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

        $(document).ready(function () {
        $("#datepicker").datepicker({
        showOn: 'button',
                buttonText: "Seleccionar"});
        }); * / / / calendario datapicker jquery

                $(function(){

                $('#login').toggle(function(){

                $('#login-form').slideDown();
                }, function(){

                $('#login-form').slideUp();
                });
                        $('#usuario').focus(function(){
                $(this).val('');
                });
                        $('#password').focus(function(){
                $(this).val('');
                });
                });</script>
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
                        <input type="button" onclick="window.location.assign('/Proyectoempresa/finServlet')" style="background: #1a6ecc !important;width: 200px !important;" class="btn btn-primary btn-sm btn-block" value="Cerrar Sesion"/>
                    </div></li>


            </ul>
        </div>
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <a href="#menu-toggle" class="btn btn-default left-boton" id="menu-toggle"> Menu</a>
                        <h3>FORMULARIO</h3>



                        <div class="col-lg-12 agregar-admin-vendedor"  > 
                            <h4>ADMINISTRADOR</h4>
                            <div class="form-group">
                                <b>Casa</b>
                                <input type="checkbox" name="check" id="check" value="1" onchange="javascript:showContent()" />
                                <b>Edificio</b>
                                <input type="checkbox" name="checke" id="checke" value="1" onchange="javascript:showDepto()" />
                                <b>Otros</b>
                                <input type="checkbox" name="checko" id="checko" value="1" onchange="javascript:showOtros()" />
                            </div>
                            <div class="form-group">
                                <c:if test="${edificioAgregar1 == 1}">
                                    <div class="alert alert-danger">
                                        <label style="color : #e45c00 !important;"><h5><b>*  Nombre ejecutivo vacio</b></h5></label>   
                                    </div>              
                                </c:if>
                                <c:if test="${edificioAgregar2 == 2}">
                                    <div class="alert alert-danger">
                                        <label style="color : #e45c00 !important;"><h5><b>*  Tipo construccion vacia</b></h5></label>
                                    </div>        
                                </c:if>
                                <c:if test="${edificioAgregar3 == 3}">
                                    <div class="alert alert-danger">
                                        <label style="color : #e45c00 !important;"><h5><b>*  Nombre edificio vacio o ya existe</b></h5></label>
                                    </div>   
                                </c:if>

                                <c:if test="${edificioAgregar4 == 4}">
                                    <div class="alert alert-danger">
                                        <label style="color : #e45c00 !important;"><h5><b>*  CIIGe anterior vacio</b></h5></label>
                                    </div>       
                                </c:if>
                                <c:if test="${edificioAgregar5 == 5}">
                                    <div class="alert alert-danger">
                                        <label style="color : #e45c00 !important;"><h5><b>*  Direccion edificio vacia</b></h5></label>
                                    </div>   
                                </c:if>
                                <c:if test="${edificioAgregar6 == 6}">
                                    <div class="alert alert-danger">
                                        <label style="color : #e45c00 !important;"><h5><b>*  Nombre administrador vacio</b></h5></label>
                                    </div>   
                                </c:if>
                                <c:if test="${edificioAgregar7 == 7}">
                                    <div class="alert alert-danger">
                                        <label style="color : #e45c00 !important;"><h5><b>*  Telefono administrador vacio</b></h5></label>
                                    </div>   
                                </c:if>
                                <c:if test="${edificioAgregar8 == 8}">
                                    <div class="alert alert-danger">
                                        <label style="color : #e45c00 !important;"><h5><b>*  Año edificio vacio o fuera de rango</b></h5></label>
                                    </div>  
                                </c:if>
                                <c:if test="${edificioAgregar9 == 9}">
                                    <div class="alert alert-danger">
                                        <label style="color : #e45c00 !important;"><h5><b>*  Rut edificio incorrecto o vacio</b></h5></label>
                                    </div>  
                                </c:if>
                                <c:if test="${edificioAgregar9 == 8}">
                                    <div class="alert alert-danger">
                                        <label style="color : #e45c00 !important;"><h5><b>*  Rut edificio ya existe</b></h5></label>
                                    </div>  
                                </c:if>
                                <c:if test="${edificioAgregar10 == 10}">
                                    <div class="alert alert-danger">
                                        <label style="color : #e45c00 !important;"><h5><b>*  Telefono administrador vacio</b></h5></label>
                                    </div>   
                                </c:if>
                                <c:if test="${edificioAgregar11 == 11}">
                                    <div class="alert alert-danger">
                                        <label style="color : #e45c00 !important;"><h5><b>*  Rut administrador incorrecto o vacio</b></h5></label>
                                    </div>   
                                </c:if>
                                <c:if test="${edificioAgregar12 == 12}">
                                    <div class="alert alert-danger">
                                        <label style="color : #e45c00 !important;"><h5><b>*  Email administrador incorrecto o vacio</b></h5></label>
                                    </div>  
                                </c:if>
                                <c:if test="${edificioAgregar13 == 13}">
                                    <div class="alert alert-danger">
                                        <label style="color : #e45c00 !important;"><h5><b>*  Ubicacion medidores vacia</b></h5></label>
                                    </div>  
                                </c:if>                                    
                                <c:if test="${edificioAgregar15 == 15}">
                                    <div class="alert alert-danger">
                                        <label style="color : #e45c00 !important;"><h5><b>*  Potencia real se encuentra vacia o fuera de rango</b></h5></label>
                                    </div>   
                                </c:if>
                                <c:if test="${edificioAgregar16 == 16}">
                                    <div class="alert alert-danger">
                                        <label style="color : #e45c00 !important;"><h5><b>*  Cantidad de departamentos se encuentra vacia o fuera de rango</b></h5></label>
                                    </div>    
                                </c:if>
                                <c:if test="${edificioAgregar17 == 17}">
                                    <div class="alert alert-danger">
                                        <label style="color : #e45c00 !important;"><h5><b>*  Cantidad de pisos se encuentra vacia o fuera de rango</b></h5></label>
                                    </div>   
                                </c:if>                                

                                <c:if test="${edificioAgregar18 == 18}">
                                    <div class="alert alert-danger">
                                        <label style="color : #e45c00 !important;"><h5><b>*  Cantidad de conductos se encuentra vacia o fuera de rango</b></h5></label>
                                    </div>    
                                </c:if>
                                <c:if test="${edificioAgregar19 == 19}">
                                    <div class="alert alert-danger">
                                        <label style="color : #e45c00 !important;"><h5><b>*  Cantidad de calderas se encuentra vacia o fuera de rango</b></h5></label>
                                    </div>    
                                </c:if>
                                <c:if test="${edificioAgregar22 == 22}">
                                    <div class="alert alert-danger">
                                        <label style="color : #e45c00 !important;"><h5><b>*  Seleccione t-de prueba u otra opcion</b></h5></label>
                                    </div>    
                                </c:if>
                                <c:if test="${agregarEd == 1}">
                                    <div class="alert alert-success color-negro">
                                        <h5>Cliente Ingresado Satisfactoriamente</h5>
                                    </div>
                                </c:if> 
                            </div>
                            <form action="edificioAddServlet" method="post" role="form">
                                <div id="content" style="display: none;">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="nombre_ejecutivo" style="height: 10px!important;">Nombre Ejecutivo(a)</label>
                                            <input type="text" name="nombre_ejecutivo"   class="form-control" required="true" value="<c:out value="${nombreEjecutivo}"/>"
                                                   onChange="javascript:while ('' + this.value.charAt(0) == ' ')
                                                                       this.value = this.value.substring(1, this.value.length);"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="tipo_construccion" style="height: 10px!important;">Tipo construcción</label>
                                            <input type="text" name="tipo_construccion" class="form-control" required="true" value="<c:out value="${tipoConstruccion}"/>"
                                                   onChange="javascript:while ('' + this.value.charAt(0) == ' ')
                                                                       this.value = this.value.substring(1, this.value.length);"/>                                     
                                        </div>
                                        <div class="form-group">
                                            <label for="nombre_edificio" style="height: 10px!important;">Nombre Edificio </label>
                                            <input type="text" name="nombre_edificio"  class="form-control" required="true" value="<c:out value="${nombreEdificio}"/>"
                                                   onChange="javascript:while ('' + this.value.charAt(0) == ' ')
                                                                       this.value = this.value.substring(1, this.value.length);"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="direccion_edificio" style="height: 10px!important;">Dirección del Edificio</label>
                                            <input type="text" name="direccion_edificio"  class="form-control" required="true" value="<c:out value="${direccionEdificio}"/>"
                                                   onChange="javascript:while ('' + this.value.charAt(0) == ' ')
                                                                       this.value = this.value.substring(1, this.value.length);"/>
                                        </div>
                                        <div>
                                            <label form="ciige_anterior" style="height: 10px!important;">CIIG-e Anterior</label>
                                            <input type="text" name="ciige_anterior" required="true" class="form-control" value="<c:out value="${ciigeAnterior}"/>"
                                                   onChange="javascript:while ('' + this.value.charAt(0) == ' ')
                                                                       this.value = this.value.substring(1, this.value.length);"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="ano_edificio"  style="height: 10px!important;">Año construcción <label style="text-decoration-color: #aaa !important;"><h6><i>* Debe ser mayor a 1954.</i></h6></label> </label>
                                            <input type="text" name="ano_edificio"  maxlength="4" class="form-control" required="true" value="<c:out value="${anoEdificio}"/>"
                                                   onChange="javascript:while ('' + this.value.charAt(0) == ' ')
                                                                       this.value = this.value.substring(1, this.value.length);"/>
                                        </div> 
                                        <div class="form-group" >
                                            <label for="rut_edificio"  style="height: 8px!important;" >R.U.T construcción <label style="text-decoration-color: #aaa !important;"><h6><i>* Formato 11111111-1.</i></h6></label></label >
                                            <input type="text" name="rut_edificio" maxlength="10"  class="form-control" required="true"  value="<c:out value="${rutEdificio}"/>"
                                                   onChange="javascript:while ('' + this.value.charAt(0) == ' ')this.value = this.value.substring(1, this.value.length);"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="nombre_admin" style="height: 10px!important;">Nombre Administrador</label>
                                            <input type="text" name="nombre_admin"  class="form-control" required="true" value="<c:out value="${nombreAdmin}"/>"
                                                   onChange="javascript:while ('' + this.value.charAt(0) == ' ')
                                                                       this.value = this.value.substring(1, this.value.length);"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="telefono_admin" style="height: 10px!important;">Teléfono Administrador</label>
                                            <input type="text" name="telefono_admin" class="form-control"required="true" value="<c:out value="${telefonoAdmin}"/>"
                                                   onChange="javascript:while ('' + this.value.charAt(0) == ' ')
                                                                       this.value = this.value.substring(1, this.value.length);"/>                                     
                                        </div>
                                    </div>
                                    <div class="col-lg-6">   
                                        <div class="form-group">
                                            <label for="telefono_edificio" style="height: 10px!important;">Teléfono Edificio</label>
                                            <input type="text" name="telefono_edificio"   class="form-control" required="true" value="<c:out value="${telefonoEdificio}"/>"
                                                   onChange="javascript:while ('' + this.value.charAt(0) == ' ')this.value = this.value.substring(1, this.value.length);"/>
                                        </div>   
                                        <div class="form-group">
                                            <label for="email_admin" style="height: 10px!important;">Correo Electrónico</label>
                                            <input type="text" name="email_admin"  class="form-control" required="true" value="<c:out value="${emailAdmin}"/>"
                                                   onChange="javascript:while ('' + this.value.charAt(0) == ' ')this.value = this.value.substring(1, this.value.length);"/>
                                        </div>
                                        <div class="form-group">
                                            <label style="height: 10px!important;">Tipo instalación</label> 
                                            <select name="tipo_instalacion" class="porte" >
                                                <option value="1" <c:if test="${tipoInstalacion == 1}">selected</c:if>>ADMINISTRACIÓN PUBLICA Y DEFENSA - CASA PATRONAL</option>
                                                <option value="2" <c:if test="${tipoInstalacion == 2}">selected</c:if>>AGRICOLA</option>
                                                <option value="3" <c:if test="${tipoInstalacion == 3}">selected</c:if>>AGRICOLA POR ASIMILACION</option>
                                                <option value="4" <c:if test="${tipoInstalacion == 4}">selected</c:if>>BIENES COMUNES</option>
                                                <option value="5" <c:if test="${tipoInstalacion == 5}">selected</c:if>>BODEGA</option>
                                                <option value="6" <c:if test="${tipoInstalacion == 6}">selected</c:if>>COMERCIO</option>
                                                <option value="7" <c:if test="${tipoInstalacion == 7}">selected</c:if>>CULTO</option>
                                                <option value="8" <c:if test="${tipoInstalacion == 8}">selected</c:if>>DEPORTE Y RECREACIÓN</option>
                                                <option value="9" <c:if test="${tipoInstalacion == 9}">selected</c:if>>DESTINO NULO</option>
                                                <option value="10" <c:if test="${tipoInstalacion == 10}">selected</c:if>>EDUCACIÓN Y CULTURA</option>
                                                <option value="11" <c:if test="${tipoInstalacion == 11}">selected</c:if>>ESTACIONAMIENTO</option>
                                                <option value="12" <c:if test="${tipoInstalacion == 12}">selected</c:if>>FORESTAL</option>
                                                <option value="13" <c:if test="${tipoInstalacion == 13}">selected</c:if>>HABITACIONAL</option>
                                                <option value="14" <c:if test="${tipoInstalacion == 14}">selected</c:if>>HOTEL - MOTEL</option>
                                                <option value="15" <c:if test="${tipoInstalacion == 15}">selected</c:if>>INDUSTRIA</option>
                                                <option value="16" <c:if test="${tipoInstalacion == 16}">selected</c:if>>MINERIA</option>
                                                <option value="17" <c:if test="${tipoInstalacion == 17}">selected</c:if>>OFICINA</option>
                                                <option value="18" <c:if test="${tipoInstalacion == 18}">selected</c:if>>OTROS NO CONSIDERADOS</option>
                                                <option value="19" <c:if test="${tipoInstalacion == 19}">selected</c:if>>SALUD</option>
                                                <option value="20" <c:if test="${tipoInstalacion == 20}">selected</c:if>>SITIO ERIAZO</option>
                                                <option value="21" <c:if test="${tipoInstalacion == 21}">selected</c:if>>TRANSPORTE Y TELECOMUNICACIONES</option>
                                                </select>
                                            </div>                                               
                                            <div class="form-group">
                                                <label style="height: 10px!important;">Tipo de  Gas</label>
                                                <select name="id_gas" class="porte">
                                                    <option value="1"<c:if test="${idGas == 1}">selected</c:if>> NATURAL</option>
                                                <option value="2"<c:if test="${idGas == 2}">selected</c:if>> LICUADO</option>                
                                                </select>
                                            </div> 
                                            <div class="form-group" style="margin-bottom: 0px!important;margin-top: 0px!important;">
                                                <label style="height: 10px!important;" >Empresa Distribuidora de Gas</label>
                                                <select name="id_empresa" class="porte">
                                                    <option value="1"  <c:if test="${idEmpresa == 1}">selected</c:if>> GASVALPO</option>
                                                <option value="2" <c:if test="${idEmpresa == 2}">selected</c:if>> LIPIGAS</option>
                                                <option value="3" <c:if test="${idEmpresa == 3}">selected</c:if>> ABASTIBLE</option>
                                                <option value="4" <c:if test="${idEmpresa == 4}">selected</c:if>> GASCO</option>
                                                <option value="5" <c:if test="${idEmpresa == 5}">selected</c:if>> OTROS</option>                    
                                                </select>
                                            </div>
                                            <br><br><br><br><br><br>
                                            <div class="form-group" style="padding-top: 10px!important;">
                                                <label for="rut_admin" style="height: 10px!important;">R.U.T Administrador o propietario <label style="text-decoration-color: #aaa !important;"><h6><i>* Formato 11111111-1.</i></h6></label></label>
                                                <input type="text" name="rut_admin" maxlength="10"  class="form-control" required="true" value="<c:out value="${rutAdmin}"/>"
                                                   onChange="javascript:while ('' + this.value.charAt(0) == ' ')this.value = this.value.substring(1, this.value.length);"/> 
                                        </div>
                                        <div class="form-group" style="margin-bottom: 0px!important;margin-top: 0px!important;">
                                            <label for="fecha_vencimiento">Fecha vencimiento</label>                                                                                                   
                                            <input type="text" name="fecha_vencimiento"  id="fecha_vencimiento"  class="form-control-fecha" value="<c:out value="${fechaVencimiento}"/>">                                              
                                            <button id="fecha_vencimiento">...</button>                   
                                        </div>
                                        <div class="form-group" style="margin-bottom: 0px!important;margin-top: 0px!important;">
                                            <label style="height: 10px!important;" >Sello </label>
                                            <select name="sello_edificio" class="porte">
                                                <option value="amarillo"  <c:if test="${idEmpresa == 'amarillo'}">selected</c:if>> AMARILLO</option>
                                                <option value="rojo" <c:if test="${idEmpresa == 'rojo'}">selected</c:if>> ROJO</option>
                                                <option value="verde" <c:if test="${idEmpresa == 'verde'}">selected</c:if>> VERDE</option>
                   
                                                </select>
                                            </div>


                                        </div>
                                    </div>

                                    <div class="col-sm-12 agregar-edificio" id="edificio" style="display: none;"> 
                                        <h4>EDIFICIO</h4>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label for="ubicacion_medidores">Ubicacion de los medidores</label>
                                                <input type="text" name="ubicacion_medidores"   class="form-control" required="true" value="<c:out value="${ubicacionMedidores}"/>"
                                                   onChange="javascript:while ('' + this.value.charAt(0) == ' ')this.value = this.value.substring(1, this.value.length);"/>
                                        </div> 
                                        <div class="form-group">
                                            <label for="potencia_real">Potencia (kW)</label>
                                            <input type="text" name="potencia_real" maxlength="3" class="form-control" required="true" value="<c:out value="${potenciaReal}"/>"
                                                   onChange="javascript:while ('' + this.value.charAt(0) == ' ')
                                                                       this.value = this.value.substring(1, this.value.length);"/>
                                        </div>
                                        <div class="form-group">          
                                            <table table-bordered table-striped table-condensed>
                                                <tr style="">
                                                    <td>
                                                        <label for="fecha_inspeccion">Fecha inspección</label>
                                                    </td>
                                                    <td style="padding-left: 30px!important; width: 200px!important;">
                                                        <input type="text" name="fecha_inspeccion" id="f_date1"  class="form-control-fecha"  value="<c:out value="${fechaInspeccion}"/> ">
                                                    </td><td>
                                                        <button id="f_btn1">...</button>                           
                                                    </td>                                            
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <label for="fecha_primera">Fecha 1° re-inspección</label>
                                                    </td>
                                                    <td style="padding-left: 30px!important;">
                                                        <input type="text" name="fecha_primera" id="f_date2" class="form-control-fecha"  value="<c:out value="${fechaPrimera}"/>">
                                                    </td><br><td>
                                                    <button id="f_btn1">...</button>
                                                </td>                                            
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <label for="fecha_segunda">Fecha 2° re-inspección</label>
                                                    </td>
                                                    <td style="padding-left: 30px!important;">
                                                        <input type="text" name="fecha_segunda" id="f_date3"   class="form-control-fecha"  value="<c:out value="${fechaSegunda}"/>">
                                                    </td><br><td>
                                                    <button id="f_btn1">...</button>
                                                </td>                                            
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <label for="fecha_cierre">Fecha cierre</label>
                                                    </td>
                                                    <td style="padding-left: 30px!important;">
                                                        <input type="text" name="fecha_cierre"  id="f_date4"  class="form-control-fecha" value="<c:out value="${fechaCierre}"/>">                                          
                                                    </td><td>
                                                        <button id="f_btn1">...</button>
                                                    </td>  
                                                </tr>
                                            </table>  
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <label>T de prueba,Despiche,otro(Cocina)</label>
                                            <div class="padding-radio">  
                                                <div class="radio">                             
                                                    <input  type="radio" name="despiche" value="T_de prueba" <c:if test="${despiche == 'T_de prueba'}">checked</c:if>>T- de prueba 
                                                        <div class="radio">
                                                            <input  type="radio" name="despiche" value="Despiche" <c:if test="${despiche == 'Despiche'}">checked</c:if>>      Despiche 
                                                            <div class="radio">
                                                                <input  type="radio" name="despiche" value="Otros" <c:if test="${despiche == 'Otros'}">checked</c:if>>            Otros  
                                                            </div>
                                                        </div>
                                                    </div>         
                                                </div>
                                            </div>
                                            <div class="form-group">          
                                                <table table-bordered table-striped table-condensed>
                                                    <tr>
                                                        <td>
                                                            <label>N° de Departamentos </label>
                                                        </td>
                                                        <td style="padding-left: 30px!important; width: 150px!important;">
                                                            <input type="text" name="cant_departamentos" maxlength="3" required="true" class="form-control" value="<c:out value="${cantDepartamentos}"/>"
                                                               onChange="javascript:while ('' + this.value.charAt(0) == ' ')
                                                                                   this.value = this.value.substring(1, this.value.length);"/>
                                                    </td>                                            
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <label>N° de Casas </label>
                                                    </td>
                                                    <td style="padding-left: 30px!important;">
                                                        <input type="text" name="cant_casas" maxlength="3" required="true" class="form-control" value="<c:out value="${cantCasas}"/>"
                                                               onChange="javascript:while ('' + this.value.charAt(0) == ' ')
                                                                                   this.value = this.value.substring(1, this.value.length);"/>
                                                    </td>                                            
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <label>N° de Locales comerciales</label>
                                                    </td>
                                                    <td style="padding-left: 30px!important;">
                                                        <input type="text" name="cant_locales" maxlength="3" required="true" class="form-control" value="<c:out value="${cantLocales}"/>"
                                                               onChange="javascript:while ('' + this.value.charAt(0) == ' ')
                                                                                   this.value = this.value.substring(1, this.value.length);"/>
                                                    </td>                                            
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <div class="form-group">
                                                            <h6>* Solo en el caso de haber locales comerciales</h6>
                                                            <div class="padding-radio">  
                                                                <div class="radio">                             
                                                                    <input  type="radio" name="gas_local" value="s" <c:if test="${despiche == 'gas_local'}">checked</c:if>>Con gas 
                                                                        <div class="radio">
                                                                            <input  type="radio" name="gas_local" value="n" <c:if test="${despiche == 'gas_local'}">checked</c:if>>Sin gas
                                                                        </div>
                                                                    </div>         
                                                                </div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <label>N° de areas comunes </label>
                                                        </td>
                                                        <td style="padding-left: 30px!important;">
                                                            <input type="text" name="cant_areas" maxlength="3" required="true" class="form-control" value="<c:out value="${cantAreas}"/>"
                                                               onChange="javascript:while ('' + this.value.charAt(0) == ' ')
                                                                                   this.value = this.value.substring(1, this.value.length);"/>
                                                    </td>                                            
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <label>N° de lavanderias </label>
                                                    </td>
                                                    <td style="padding-left: 30px!important;">
                                                        <input type="text" name="cant_lavanderias" maxlength="3" required="true" class="form-control" value="<c:out value="${cantLavanderias}"/>"
                                                               onChange="javascript:while ('' + this.value.charAt(0) == ' ')
                                                                                   this.value = this.value.substring(1, this.value.length);"/>
                                                    </td>                                            
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <label>N° de Pisos Inmueble</label>
                                                    </td>
                                                    <td style="padding-left: 30px!important;">
                                                        <input type="text" name="cant_pisos" maxlength="3" required="true" class="form-control" value="<c:out value="${cantPisos}"/>"
                                                               onChange="javascript:while ('' + this.value.charAt(0) == ' ')
                                                                                   this.value = this.value.substring(1, this.value.length);"/>
                                                    </td>                                            
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <label>N° de Conductos Colectivos</label>
                                                    </td>
                                                    <td style="padding-left: 30px!important;">
                                                        <input type="text" name="cant_conductos" maxlength="3" required="true" class="form-control" 
                                                               onChange="javascript:while ('' + this.value.charAt(0) == ' ')
                                                                                   this.value = this.value.substring(1, this.value.length);"
                                                               value="<c:out value="${cantConductos}"/>"/>
                                                    </td>                                            
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <label>N° de Calderas Centrales</label>
                                                    </td>
                                                    <td style="padding-left: 30px!important;">
                                                        <input type="text" name="cant_calderas" maxlength="3" required="true" class="form-control" value="<c:out value="${cantCalderas}"/>"
                                                               onChange="javascript:while ('' + this.value.charAt(0) == ' ')
                                                                                   this.value = this.value.substring(1, this.value.length);"/>
                                                    </td>                                            
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="col-lg-7 img-edificio">
                                        <br>
                                        <div class="form-group">
                                            <img class="img-login" src="iconos/edificio.jpg">
                                        </div>

                                    </div>
                                </div>

                                <div class="col-lg-7">
                                    <div class="form-group" id="agregar" style="display: none;">
                                        <br><br>
                                        <button class="btn btn-primary btn-lg" type="submit" >Agregar</button>  
                                    </div>
                            </form> 
                            <form action="/Proyectoempresa/edificioListaServlet" role="form" method="post">
                                <div class="form-group">
                                    <input type="text"  name="nombre_sesion" value="<c:out value="${nombre}"/>" hidden="true"/>
                                    <input type="text"  name="listarEdificio" value="TODAS" hidden="true"/>                                    
                                    <input type="submit" class="btn btn-danger btn-md" value="Volver al listado edificio"/>    
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
<script type="text/javascript">//<![CDATA[

                                                                           var cal = Calendar.setup({
                                                                           onSelect: function (cal) {
                                                                           cal.hide()
                                                                           },
                                                                                   showTime: true
                                                                           });
                                                                           cal.manageFields("f_btn1", "f_date1", "%Y-%m-%d %I:%M %p");
                                                                           cal.manageFields("f_btn2", "f_date2", "%Y-%m-%d %I:%M %p");
                                                                           cal.manageFields("f_btn3", "f_date3", "%Y-%m-%d %I:%M %p");
                                                                           cal.manageFields("f_btn4", "f_date4", "%Y-%m-%d %I:%M %p");
                                                                           //]]></script>
<script>
            $("#menu-toggle").click(function (e) {
    e.preventDefault();
            $("#wrapper").toggleClass("active");
    });</script>
<script type="text/javascript">
            function showContent() {
            element = document.getElementById("content");
                    edificio = document.getElementById("edificio");
                    agregar = document.getElementById("agregar");
                    check = document.getElementById("check");
                    if (check.checked) {
            element.style.display = 'block';
                    agregar.style.display = 'block';
            }
            else {
            element.style.display = 'none';
                    agregar.style.display = 'none';
            }
            }
    function showDepto(){
    element = document.getElementById("content");
            edificio = document.getElementById("edificio");
            agregar = document.getElementById("agregar");
            checkE = document.getElementById("checke");
            if (checkE.checked){
    edificio.style.display = 'block';
            element.style.display = 'block';
            agregar.style.display = 'block';
    } else{
    edificio.style.display = 'none';
            element.style.display = 'none';
            agregar.style.display = 'none';
    }

    }

    function showOtros(){
    element = document.getElementById("content");
            edificio = document.getElementById("edificio");
            agregar = document.getElementById("agregar");
            checkO = document.getElementById("checko");
            if (checkO.checked){
    edificio.style.display = 'block';
            element.style.display = 'block';
            agregar.style.display = 'block';
    } else{
    edificio.style.display = 'none';
            element.style.display = 'none';
            agregar.style.display = 'none';
    }
    }
</script>
</body>
</html>