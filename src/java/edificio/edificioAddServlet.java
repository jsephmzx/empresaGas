/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edificio;

import administrador.administrador;
import administrador.administradorDAO;
import artefacto.artefacto;
import artefacto.artefactoDAO;
import conducto.conducto;
import conducto.conductoDAO;
import departamento.departamento;
import departamento.departamentoDAO;
import detalleEdificio.DetalleEdificioDAO;
import detalleEdificio.detalleEdificio;
import empresagas.empresagas;
import empresagas.empresagasDAO;
import fecha.fecha;
import fecha.fechaDAO;
import gas.gas;
import gas.gasDAO;
import helpers.Rut;
import helpers.numero;
import helpers.validarEmail;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import usuario.usuario;
import usuario.usuarioDAO;

/**
 *
 * @author Natalia
 */
@WebServlet(name = "edificioAddServlet", urlPatterns = {"/edificioAddServlet"})
public class edificioAddServlet extends HttpServlet {

    @Resource(name = "jdbc/Proyectoempresa")
    private DataSource ds;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Connection conexion = null;
        int exito = 0;
        /*
         *Establecer conexion
         */

        try {
            HttpSession session = request.getSession(true);
            conexion = ds.getConnection();

            // Conexion edificio
            edificioDAO edDAO = new edificioDAO();
            edDAO.setConexion(conexion);
            // Conexion administrador
            administradorDAO adDAO = new administradorDAO();
            adDAO.setConexion(conexion);

            // Conexion departamento
            departamentoDAO deDAO = new departamentoDAO();
            deDAO.setConexion(conexion);
            // Conexion detalle edificio
            DetalleEdificioDAO deedDAO = new DetalleEdificioDAO();
            deedDAO.setConexion(conexion);
            // Conexion fecha
            fechaDAO feDAO = new fechaDAO();
            feDAO.setConexion(conexion);
            
            //Conexion conducto
            conductoDAO condDAO = new conductoDAO();
            condDAO.setConexion(conexion);
            //Comprovar usuario
            String userSession = (String) request.getSession().getAttribute("tipo");
            try {
                if (userSession.equals("admin") || userSession.equals("vende")) {
                    // Variables
                    Rut validarRut = new Rut();
                    validarEmail mail = new validarEmail();
                    numero num = new numero();
                    boolean emailCorrecto = false;
                    boolean rutCorrecto = false;
                    boolean rutCorrectoA = false;
                    boolean error = false;
                    int annioConvertido = 0;
                    boolean rutAdminCorrecto = false;
                    int rutNumericoedificio = 0;
                    int rutNumericoAdmin = 0;
                    int potenciaE = 0;
                    String dvEdificio;
                    String dvAdmin;
                    String normaAplicada = null;
                    int depto = 0;
                    int casas = 0;
                    int lavanderias = 0;
                    int locales = 0;
                    int areas = 0;
                    int piso = 0;
                    int conducto = 0;
                    int caldera = 0;
                    int potenciaR = 0;
                    int potenciaA = 0;
                    int tipoInst = 0;
                    int tipoGas = 0;
                    int empresaGas = 0;
                    String existencia = "s";
                    
                    /*
                     *Recibir parametros
                     */
                    int idUsuOnline = (Integer) session.getAttribute("idUsuariOnline");
                    System.out.println("id usuario online :"+idUsuOnline);
                    
                    
                    System.out.println("Comienzo de resivir parametros");
                    String nombreEjecutivo = request.getParameter("nombre_ejecutivo");;
                    String tipoConstruccion = request.getParameter("tipo_construccion");
                    String nombreEdificio = request.getParameter("nombre_edificio");
                    String tipoInstalacion = request.getParameter("tipo_instalacion");
                    String idGas = request.getParameter("id_gas");
                    String idEmpresa = request.getParameter("id_empresa");
                    String ciigeAnterior = request.getParameter("ciige_anterior");
                    String direccionEdificio = request.getParameter("direccion_edificio");
                    String nombreAdmin = request.getParameter("nombre_admin");
                    String telefonoAdmin = request.getParameter("telefono_admin");
                    String anoEdificio = request.getParameter("ano_edificio");
                    String rutEdificio = request.getParameter("rut_edificio");
                    String telefonoEdificio = request.getParameter("telefono_edificio");
                    String rutAdmin = request.getParameter("rut_admin");
                    String emailAdmin = request.getParameter("email_admin");
                    String ubicacionMedidores = request.getParameter("ubicacion_medidores");
                    String fechaInspeccion = request.getParameter("fecha_inspeccion");
                    String fechaPrimera = request.getParameter("fecha_primera");
                    String fechaSegunda = request.getParameter("fecha_segunda");
                    String fechaCierre = request.getParameter("fecha_cierre");
                    String cantDepartamentos = request.getParameter("cant_departamentos");
                    String cantCasas = request.getParameter("cant_casas");
                    String cantLocales = request.getParameter("cant_locales");
                    String cantAreas = request.getParameter("cant_areas");
                    String cantLavanderias = request.getParameter("cant_lavanderias");
                    String cantPisos = request.getParameter("cant_pisos");
                    String cantConductos = request.getParameter("cant_conductos");
                    String cantCalderas = request.getParameter("cant_calderas");
                    String despiche = request.getParameter("despiche");
                    String potenciaReal = request.getParameter("potencia_real");
                    String gasLocal = request.getParameter("gas_local");
                    String fechaVencimiento = request.getParameter("fecha_vencimiento");
                    String selloEdificio =request.getParameter("sello_edificio");
                    String tipoCliente =request.getParameter("tipo_cliente");
                    //
                    request.setAttribute("nombreEjecutivo", nombreEjecutivo);
                    request.setAttribute("tipoConstruccion", tipoConstruccion);
                    request.setAttribute("nombreEdificio", nombreEdificio);
                    request.setAttribute("tipoInstalacion", tipoInstalacion);
                    request.setAttribute("idGas", idGas);
                    request.setAttribute("idEmpresa", idEmpresa);
                    request.setAttribute("ciigeAnterior", ciigeAnterior);
                    request.setAttribute("direccionEdificio", direccionEdificio);
                    request.setAttribute("nombreAdmin", nombreAdmin);
                    request.setAttribute("telefonoAdmin", telefonoAdmin);
                    request.setAttribute("anoEdificio", anoEdificio);
                    request.setAttribute("rutEdificio", rutEdificio);
                    request.setAttribute("telefonoEdificio", telefonoEdificio);
                    request.setAttribute("rutAdmin", rutAdmin);
                    request.setAttribute("emailAdmin", emailAdmin);
                    request.setAttribute("ubicacionMedidores", ubicacionMedidores);
                    request.setAttribute("fechaInspeccion", fechaInspeccion);
                    request.setAttribute("fechaPrimera", fechaPrimera);
                    request.setAttribute("fechaSegunda", fechaSegunda);
                    request.setAttribute("fechaCierre", fechaCierre);
                    request.setAttribute("cantDepartamentos", cantDepartamentos);
                    request.setAttribute("cantCasas", cantCasas);
                    request.setAttribute("cantLocales", cantLocales);
                    request.setAttribute("cantAreas", cantAreas);
                    request.setAttribute("cantLavanderias", cantLavanderias);
                    request.setAttribute("cantPisos", cantPisos);
                    request.setAttribute("cantCalderas", cantCalderas);
                    request.setAttribute("cantConductos", cantConductos);
                    request.setAttribute("despiche", despiche);
                    request.setAttribute("potenciaReal", potenciaReal);
                    request.setAttribute("gasLocal",gasLocal);
                    request.setAttribute("fechaVencimiento",fechaVencimiento);
                    request.setAttribute("selloEdificio",selloEdificio);
                    request.setAttribute("tipoCliente",tipoCliente);

                    // parse id
                    empresaGas = Integer.parseInt(idEmpresa);
                    tipoGas = Integer.parseInt(idGas);
                    tipoInst = Integer.parseInt(tipoInstalacion);

                    //Validaciones
                    /**
                     * ********************************
                     */
                    /**
                     * ********************************
                     */
                    /**
                     * * Validar campos String **
                     */
                    /**
                     * ********************************
                     */
                    /**
                     * ********************************
                     */

                    /**
                     * ************************
                     */
                    /* Validar nombre edificio */ /**
                     * IMPORTANTE*
                     */
                    /**
                     * ************************
                     */

                    if (nombreEdificio == null) {
                        request.setAttribute("msgErrorNomEdificio", "Error, Porfavor Ingrese el nombre del Edificio");
                        System.out.println(" error nombre edificio");
                        request.setAttribute("edificioAgregar3", 3);
                        error = true;
                    } else {
                        ArrayList<String> listaNombres = edDAO.getNombreEdificio();
                        if (listaNombres.contains(nombreEdificio)) {
                            request.setAttribute("edificioAgregar3", 3);
                            error = true;
                        }
                    }

                    /**
                     * ************************
                     */
                    /*   Validar email admin   */ /**
                     * IMPORTANTE*
                     */
                    /**
                     * ************************
                     */
                    emailCorrecto = validarEmail.validateEmail(emailAdmin);
                    if (emailCorrecto == false) {
                        request.setAttribute("msgErrorMail", "Error, Porfavor ingrese email correcto");
                        request.setAttribute("edificioAgregar12", 12);
                        System.out.println("error email");
                        error = true;
                    }

                    /**
                     * ************************
                     */
                    /*Validar nombre ejecutivo */
                    /**
                     * ************************
                     */
                    if (nombreEjecutivo == null) {
                        request.setAttribute("msgErrorNomEjecutivo", "Error, Porfavor Ingrese el nombre del ejecutivo");
                        System.out.println(" error nombre ejecutivo");
                        request.setAttribute("edificioAgregar1", 1);
                        error = true;
                    }

                    /**
                     * ************************
                     */
                    /* Validar nombre Admin    */
                    /**
                     * ************************
                     */
                    if (nombreAdmin == null) {
                        request.setAttribute("msgErrorNomAdmin", "Error, Porfavor Ingrese el nombre del administrador");
                        request.setAttribute("edificioAgregar6", 6);
                        error = true;
                    }

                    /**
                     * ************************
                     */
                    /* Validar tipo constru    */
                    /**
                     * ************************
                     */
                    if (tipoConstruccion == null) {
                        request.setAttribute("msgErrorTipoConstruccion", "Error, Porfavor Ingrese tipo de construccion");
                        request.setAttribute("edificioAgregar2", 2);
                        error = true;
                    }

                    /**
                     * ************************
                     */
                    /*     Validar ciige       */
                    /**
                     * ************************
                     */
                    if (ciigeAnterior == null) {
                        request.setAttribute("msgErrorCiigeAnterior", "Error, Porfavor Ingrese el ciige anterior");
                        request.setAttribute("edificioAgregar4", 4);
                        error = true;
                    }

                    /**
                     * ************************
                     */
                    /*   Validar direccion     */
                    /**
                     * ************************
                     */
                    if (direccionEdificio == null) {
                        request.setAttribute("msgErrorDireccionEdificio", "Error, Porfavor Ingrese una dirección edificio");
                        request.setAttribute("edificioAgregar5", 5);
                        error = true;
                    }

                    /**
                     * ************************
                     */
                    /*   Validar fono Admin    */
                    /**
                     * ************************
                     */
                    if (telefonoAdmin == null) {
                        request.setAttribute("msgErrorTelefonoAdmin", "Error, Porfavor Ingrese telefono administrador ");
                        request.setAttribute("edificioAgregar7", 7);
                        error = true;
                    }

                    /**
                     * ************************
                     */
                    /*   Validar fono Edificio */
                    /**
                     * ************************
                     */
                    if (telefonoEdificio == null) {
                        request.setAttribute("msgErrorfonoEdificio", "Error, Porfavor Ingrese fono edificio ");
                        request.setAttribute("edificioAgregar10", 10);
                        error = true;
                    }

                    /**
                     * ************************
                     */
                    /*   Validar Ub medidores  */
                    /**
                     * ************************
                     */
                    if (ubicacionMedidores == null) {
                        request.setAttribute("msgErrorUbicacionMedidores", "Error, Porfavor Ingrese ubicacion medidores ");
                        request.setAttribute("edificioAgregar13", 13);
                        error = true;
                    }

                    /**
                     * ************************
                     */
                    /*   validar potencia real */
                    /**
                     * ************************
                     */
                    if (num.CadenaIsNumeric(potenciaReal)) {
                        potenciaR = Integer.parseInt(potenciaReal);
                        if (potenciaR < 0) {
                            request.setAttribute("msgErrorPotenciaReal", "Error, Porfavor Ingrese potencia real ");
                            request.setAttribute("edificioAgregar15", 15);
                            System.out.println("error potencia real");
                            error = true;
                        }
                    } else {
                        request.setAttribute("edificioAgregar15", 15);
                        error = true;
                    }
                    /**
                     * ************************
                     */
                    /*   Validar  cant deptos  */
                    /**
                     * ************************
                     */
                    if (num.CadenaIsNumeric(cantDepartamentos)) {
                        depto = Integer.parseInt(cantDepartamentos);
                        if (depto < 0) {
                            request.setAttribute("msgErrorCantDeptos", "Error, Porfavor Ingrese cantidad de departamentos ");
                            request.setAttribute("edificioAgregar16", 16);
                            error = true;
                        }
                    } else {
                        request.setAttribute("edificioAgregar16", 16);
                        error = true;
                    }
                    /**
                     * ************************
                     */
                    /*   Validar  cant casas   */
                    /**
                     * ************************
                     */
                    if (num.CadenaIsNumeric(cantCasas)) {
                        casas = Integer.parseInt(cantCasas);
                        if (casas < 0) {
                            request.setAttribute("msgErrorCantDeptos", "Error, Porfavor Ingrese cantidad de departamentos ");
                            request.setAttribute("edificioAgregar16", 16);
                            error = true;
                        }
                    } else {
                        request.setAttribute("edificioAgregar16", 16);
                        error = true;
                    }
                    /**
                     * ************************
                     */
                    /*   Validar  cant areas   */
                    /**
                     * ************************
                     */
                    if (num.CadenaIsNumeric(cantAreas)) {
                        areas = Integer.parseInt(cantAreas);
                        if (areas < 0) {
                            request.setAttribute("msgErrorCantDeptos", "Error, Porfavor Ingrese cantidad de departamentos ");
                            request.setAttribute("edificioAgregar16", 16);
                            error = true;
                        }
                    } else {
                        request.setAttribute("edificioAgregar16", 16);
                        error = true;
                    }
                    /**
                     * ************************
                     */
                    /*   Validar  cant locales */
                    /**
                     * ************************
                     */
                    if (num.CadenaIsNumeric(cantLocales)) {
                        locales = Integer.parseInt(cantLocales);
                        if (locales < 0) {
                            request.setAttribute("msgErrorCantDeptos", "Error, Porfavor Ingrese cantidad de departamentos ");
                            request.setAttribute("edificioAgregar16", 16);
                            existencia = "n";
                            error = true;
                        }
                    } else {
                        request.setAttribute("edificioAgregar16", 16);
                        existencia = "n";
                        error = true;
                    }
                    /**
                     * ************************
                     */
                    /*Validar  cant lavanderias*/
                    /**
                     * ************************
                     */
                    if (num.CadenaIsNumeric(cantLavanderias)) {
                        lavanderias = Integer.parseInt(cantLavanderias);
                        if (lavanderias < 0) {
                            request.setAttribute("msgErrorCantDeptos", "Error, Porfavor Ingrese cantidad de departamentos ");
                            request.setAttribute("edificioAgregar16", 16);
                            
                            error = true;
                            
                        }
                    } else {
                        request.setAttribute("edificioAgregar16", 16);
                        error = true;
                    }
                    /**
                     * ************************
                     */
                    /*  validar cantidad piso  */
                    /**
                     * ************************
                     */
                    System.out.println("validar numerico " + num.CadenaIsNumeric(cantPisos));
                    if (num.CadenaIsNumeric(cantPisos)) {
                        piso = Integer.parseInt(cantPisos);
                        System.out.println("piso " + piso);
                        if (piso < 0) {
                            request.setAttribute("msgErrorCantPisos", "Error, Porfavor Ingrese cantidad de pisos ");
                            request.setAttribute("edificioAgregar17", 17);
                            error = true;
                        }
                    } else {
                        request.setAttribute("edificioAgregar17", 17);
                        error = true;
                    }

                    /**
                     * ************************
                     */
                    /*validar cantidad conducto*/
                    /**
                     * ************************
                     */
                    if (num.CadenaIsNumeric(cantConductos)) {
                        conducto = Integer.parseInt(cantConductos);
                        if (conducto < 0) {
                            request.setAttribute("msgErrorCantConducto", "Error, Porfavor Ingrese cantidad de conductos ");
                            request.setAttribute("edificioAgregar18", 18);
                            error = true;
                        }
                    } else {
                        request.setAttribute("edificioAgregar18", 18);
                        error = true;
                    }
                    /**
                     * ************************
                     */
                    /*validar cantidad caldera */
                    /**
                     * ************************
                     */
                    if (num.CadenaIsNumeric(cantCalderas)) {
                        caldera = Integer.parseInt(cantCalderas);
                        if (caldera < 0) {
                            request.setAttribute("msgErrorCantCalderas", "Error, Porfavor Ingrese cantidad de calderas ");
                            request.setAttribute("edificioAgregar19", 19);
                            error = true;
                        }
                    } else {
                        request.setAttribute("edificioAgregar19", 19);
                        error = true;
                    }
                    /**
                     * ************************
                     */
                    /* validar tipo instalacion*/
                    /**
                     * ************************
                     */
                    try {
                        tipoInst = Integer.parseInt(tipoInstalacion);
                    } catch (Exception ex) {
                        request.setAttribute("errorTipoInstalacion", "error en tipo de instalacion");
                    }
                    if (tipoInst < 0 || tipoInst > 21) {
                        request.setAttribute("errorRango", " el tipo de instalacion esta fuera del rango establecido");
                        System.out.println(" error ripo instalacion");
                        error = true;
                    }
                    /**
                     * *********************************
                     */
                    /*Validar rangos de años de edificio*/
                    /**
                     * *********************************
                     */
                    if (num.CadenaIsNumeric(anoEdificio)) {
                        annioConvertido = Integer.parseInt(anoEdificio);
                        if (annioConvertido < 1955) {
                            System.out.println("entro a validar año");
                            request.setAttribute("msgErrorAnnioFueraRango", "El año ingresado es menor que la primera norma existente 1955");
                            request.setAttribute("edificioAgregar8", 8);
                            error = true;
                        } else {
                            if (annioConvertido >= 1955 && annioConvertido <= 1990) {
                                normaAplicada = "DI3952";
                            }
                            if (annioConvertido >= 1991 && annioConvertido <= 1996) {
                                normaAplicada = "DS182";
                            }
                            if (annioConvertido >= 1997 && annioConvertido <= 2007) {
                                normaAplicada = "DS222";
                            }
                            if (annioConvertido > 2007) {
                                normaAplicada = "DS66";
                            }
                        }

                    } else {
                        request.setAttribute("edificioAgregar8", 8);
                        error = true;
                    }

                    /**
                     * ************************
                     */
                    /*   Validar tipo prueba   */
                    /**
                     * ************************
                     */
                    System.out.println("despiche contiene " + despiche);
                    if (despiche == null) {
                        request.setAttribute("OpcionNoSelecionadaTipoInst", "no se selecciono ninguna de las opciones disponibles");
                        request.setAttribute("edificioAgregar22", 22);
                        System.out.println("error null despiche");
                        error = true;

                    }
                    /**
                     * ************************
                     */
                    /*   Validar tipo cliente  */
                    /**
                     * ************************
                     */
                    System.out.println("tipo cliente contiene " + tipoCliente);
                    if (tipoCliente == null) {
                        request.setAttribute("OpcionNoSelecionadaTipoInst", "no se selecciono ninguna de las opciones disponibles");
                        request.setAttribute("edificioAgregar22", 22);
                        System.out.println("error null tipoCliente");
                        error = true;

                    }
                    /**
                     * ************************
                     */
                    /*   Validar rut edificio  */
                    /**
                     * ************************
                     */
                    if (rutEdificio.trim().equals("") || rutEdificio == null) {
                        request.setAttribute("msgErrorEdificio", "Error, el Rut de edificio posee espacios o esta vacio");
                        request.setAttribute("edificioAgregar9", 9);
                        error = true;
                        System.out.println(" error rut edificio");
                    } else {
                        rutCorrecto = validarRut.validateRut(rutEdificio);
                        if (rutCorrecto == false) {
                            request.setAttribute("msgErrorNoValido", "Error, el rut del edificio ingresado no es correcto");
                            request.setAttribute("edificioAgregar9", 9);
                            error = true;
                        } else {
                            ArrayList<String> listaRutEd = edDAO.getRutEdificio();
                            if (listaRutEd.contains(rutEdificio)) {
                                request.setAttribute("edificioAgregar9", 8);
                                error = true;
                            }
                        }
                    }

                    /**
                     * ************************
                     */
                    /*   Validar rut Admin     */
                    /**
                     * ************************
                     */
                    if (rutAdmin.trim().equals("") || rutAdmin == null) {
                        request.setAttribute("msgErrorAdmin", "Error, el Rut de admin posee espacios o esta vacio");
                        request.setAttribute("edificioAgregar11", 11);
                        error = true;
                        System.out.println(" error rut admin");
                    } else {
                        rutCorrectoA = validarRut.validateRut(rutAdmin);
                        if (rutCorrectoA == false) {
                            request.setAttribute("msgErrorNoValido", "Error, el rut del admin ingresado no es correcto");
                            request.setAttribute("edificioAgregar11", 11);
                            error = true;
                        } else {
                            ArrayList<String> listaRutEd = edDAO.getRutEdificio();
                            if (listaRutEd.contains(rutEdificio)) {
                                request.setAttribute("edificioAgregar11", 11);
                                error = true;
                            }
                        }
                    }
                    System.out.println("rutAdminCorrecto " + rutAdmin);

                    // validar mail administrador
                    emailCorrecto = validarEmail.validateEmail(emailAdmin);
                    //valida mail
                    if (emailCorrecto == false) {
                        request.setAttribute("msgErrorMail", "Error, Porfavor ingrese email correcto");
                        request.setAttribute("edificioAgregar12", 12);
                        System.out.println("error email");
                        emailCorrecto = true;
                    }
                    potenciaR = Integer.parseInt(potenciaReal);
                    depto = Integer.parseInt(cantDepartamentos);
                    casas = Integer.parseInt(cantCasas);
                    areas = Integer.parseInt(cantAreas);
                    lavanderias = Integer.parseInt(cantLavanderias);
                    locales = Integer.parseInt(cantLocales);
                    piso = Integer.parseInt(cantPisos);
                    caldera = Integer.parseInt(cantCalderas);
                    conducto = Integer.parseInt(cantConductos);

                    if (!error) {
                        edificio ed = new edificio();
                        administrador ad = new administrador();
                        fecha fe = new fecha();
                        detalleEdificio deed = new detalleEdificio();

                        ed.setNombreEjecutivo(nombreEjecutivo);//funca
                        deed.setTipoConstruccion(tipoConstruccion);
                        ed.setNombreEdificio(nombreEdificio);//funca
                        deed.setTipoInstalacion(tipoInst);
                        ed.setIdGas(tipoGas);
                        ed.setIdEmpresa(empresaGas);
                        deed.setCiigeAnterior(ciigeAnterior);
                        ed.setDireccionEdificio(direccionEdificio);//funca
                        ad.setNombreAdmin(nombreAdmin);
                        ad.setTelefonoAdmin(telefonoAdmin);
                        ed.setAnoEdificio(annioConvertido);//funca
                        ed.setNormaAplicada(normaAplicada);
                        ed.setRutEdificio(rutEdificio);//funca
                        ed.setTelefonoEdificio(telefonoEdificio);//funca
                        ad.setRutAdmin(rutAdmin);
                        ad.setEmailAdmin(emailAdmin);
                        ed.setIdUsuario(idUsuOnline);
                        ed.setGasLocal(gasLocal);
                        ed.setExistenciaConductos(existencia);
                        ed.setSelloEdificio(selloEdificio);
                        ed.setFechaVencimiento(fechaVencimiento);
                        

                        /**/
                        /**/
                        deed.setUbicacionMedidores(ubicacionMedidores);
                        fe.setFechaInspeccion(fechaInspeccion);
                        fe.setFechaPrimera(fechaPrimera);
                        fe.setFechaSegunda(fechaSegunda);
                        fe.setFechaCierre(fechaCierre);
                        ed.setPotenciaEstimada(potenciaE);
                        deed.setDespiche(despiche);
                        ed.setPotenciaReal(potenciaR);//funca
                        ed.setCantDepartamentos(depto);//funca
                        ed.setCantCasas(casas);
                        ed.setCantAreas(areas);
                        ed.setCantLavanderias(lavanderias);
                        ed.setCantLocales(locales);
                        ed.setCantPisos(piso);//funca
                        ed.setCantConductos(conducto);//funca
                        ed.setCantCalderas(caldera);//funca
                        ed.setTipoCliente(tipoCliente);

                        edDAO.insert(ed);
                        /*Obtener id de edificio  detalle*/

                        int idEdificio = edDAO.getLastId();
                        System.out.println("id edificio :" + idEdificio);

                        /*Creación de los conductos automaticamente*/
                        
                        for (int cont = 0; cont < conducto; cont++) {
                            conducto conduc = new conducto();
                            conduc.setCantDeptoConducto(0);
                            conduc.setCondSombrete("n");
                            conduc.setSecciones("n");
                            conduc.setConInterior("n");
                            conduc.setRelacionLados("n");
                            conduc.setPruebaTiro("n");
                            conduc.setTomaAire("n");
                            conduc.setMaterialidad("n");
                            conduc.setObservaciones("");
                            conduc.setSello("1");
                            conduc.setSelloConducto("1");
                            conduc.setIdEdificio(idEdificio);
                            conduc.setArtefactoConducto("calefont");
                            conduc.setPotenciaArtefacto(1);
                            conduc.setSeccionConducto(0);
                            conduc.setMaterialConducto("");
                            conduc.setEspesorMaterial(0);
                            conduc.setConductoNormalizado("n");
                            conduc.setSombreteExpuesto("n");
                            conduc.setConductoQuiebre("n");
                            conduc.setSecundarioNormalizados("n");
                            condDAO.insert(conduc);
                            System.out.println("ingresando conductos ");
                        }
                        /*Fin de la creacion de los conductos*/
                        /*Insertar id en los campos necesarios*/

                        fe.setIdEdificio(idEdificio);
                        ad.setIdEdificio(idEdificio);
                        deed.setIdEdificio(idEdificio);
                        /*Insertar fecha y administrador en bd*/
                        feDAO.insert(fe);
                        adDAO.insert(ad);
                        deedDAO.insert(deed);
                        exito = 1;
                        request.setAttribute("agregarEd", 1);
                        nombreEjecutivo = null;
                        tipoConstruccion = null;
                        nombreEdificio = null;
                        tipoInst = 0;
                        tipoGas = 0;
                        empresaGas = 0;
                        ciigeAnterior = null;
                        direccionEdificio = null;
                        nombreAdmin = null;
                        telefonoAdmin = null;
                        annioConvertido = 0;
                        rutEdificio = null;
                        telefonoEdificio = null;
                        rutAdmin = null;
                        emailAdmin = null;
                        ubicacionMedidores = null;
                        fechaInspeccion = null;
                        fechaPrimera = null;
                        fechaSegunda = null;
                        fechaCierre = null;
                        despiche = null;
                        potenciaR = 0;
                        existencia="s";
                        depto = 0;
                        casas = 0;
                        areas = 0;
                        locales = 0;
                        lavanderias = 0;
                        piso = 0;
                        conducto = 0;
                        caldera = 0;
                        fechaVencimiento="";
                        selloEdificio="amarillo";
                        request.setAttribute("nombreEjecutivo", nombreEjecutivo);
                        request.setAttribute("tipoConstruccion", tipoConstruccion);
                        request.setAttribute("nombreEdificio", nombreEdificio);
                        request.setAttribute("tipoInstalacion", tipoInstalacion);
                        request.setAttribute("idGas", idGas);
                        request.setAttribute("idEmpresa", idEmpresa);
                        request.setAttribute("ciigeAnterior", ciigeAnterior);
                        request.setAttribute("direccionEdificio", direccionEdificio);
                        request.setAttribute("nombreAdmin", nombreAdmin);
                        request.setAttribute("telefonoAdmin", telefonoAdmin);
                        request.setAttribute("anoEdificio", anoEdificio);
                        request.setAttribute("rutEdificio", rutEdificio);
                        request.setAttribute("telefonoEdificio", telefonoEdificio);
                        request.setAttribute("rutAdmin", rutAdmin);
                        request.setAttribute("emailAdmin", emailAdmin);
                        request.setAttribute("ubicacionMedidores", ubicacionMedidores);
                        request.setAttribute("fechaInspeccion", fechaInspeccion);
                        request.setAttribute("fechaPrimera", fechaPrimera);
                        request.setAttribute("fechaSegunda", fechaSegunda);
                        request.setAttribute("fechaCierre", fechaCierre);
                        request.setAttribute("cantDepartamentos", cantDepartamentos);
                        request.setAttribute("cantAreas", cantAreas);
                        request.setAttribute("cantCasas", cantCasas);
                        request.setAttribute("canLocales", cantLocales);
                        request.setAttribute("cantLavanderias", cantLavanderias);
                        request.setAttribute("cantPisos", cantPisos);
                        request.setAttribute("cantCalderas", cantCalderas);
                        request.setAttribute("cantConductos", cantConductos);
                        request.setAttribute("despiche", despiche);
                        request.setAttribute("potenciaReal", potenciaReal);
                        request.setAttribute("gasLocal",gasLocal);
                        request.setAttribute("fechaVencimiento",fechaVencimiento);
                        request.setAttribute("selloEdificio",selloEdificio);

                        if (exito == 1) {
                            request.getRequestDispatcher("/index.jsp").forward(request, response);
                        } else {
                            request.getRequestDispatcher("/index.jsp").forward(request, response);
                        }
                    }
                } else {
                    request.getRequestDispatcher("/accesodenegado.jsp").forward(request, response);
                }
            } catch (Exception sessionException) {
                /* enviar a la vista de login */
                System.out.println("no ha iniciado session");
                /*enviar al login*/
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } catch (Exception connectionException) {
            connectionException.printStackTrace();
            System.out.println("Conexion Fallida");
        } finally {
            try {
                conexion.close();
            } catch (Exception noGestionar) {
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
