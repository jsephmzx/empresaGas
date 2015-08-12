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
import javax.sql.DataSource;
import javax.swing.JOptionPane;
import usuario.usuario;
import usuario.usuarioDAO;
import java.util.Calendar;

/**
 *
 * @author Natalia
 */
@WebServlet(name = "edificioChangeServlet", urlPatterns = {"/edificioChangeServlet"})
public class edificioChangeServlet extends HttpServlet {

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
        Connection conexion = null;
        int exito = 0;
        /*
         *Establecer conexion
         */
        try {
            conexion = ds.getConnection();
            /**
             * ************************
             */
            /*    Conexion a DAOS      */
            /**
             * ************************
             */
            Calendar fechaMod = Calendar.getInstance();
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
            //Validar cuenta de ususario
            String userSession = (String) request.getSession().getAttribute("tipo");
            try {
                if (userSession.equals("admin")) {
                    /**
                     * ************************
                     */
                    /*    llamada helpers      */
                    /**
                     * ************************
                     */
                    Rut validarRut = new Rut();
                    validarEmail mail = new validarEmail();
                    numero num = new numero();

                    /**
                     * ************************
                     */
                    /*  Inizializar Variables  */
                    /**
                     * ************************
                     */
                    boolean rutAdminCorrecto = false;

                    boolean error = false;
                    boolean emailCorrecto = false;
                    boolean rutCorrecto = false;
                    int Numdepto = 0;
                    int Numpiso = 0;
                    int Numconducto = 0;
                    int Numcaldera = 0;
                    int Numpotencia = 0;
                    int annioConvertido = 0;
                    int annioConvertidos = 0;
                    int idEd = 0;
                    int tipoInst = 0;
                    int tipoGas = 0;
                    int empresaGas = 0;
                    int idAdmins = 0;
                    int idEds = 0;
                    int tipoInsts = 0;
                    int tipoGass = 0;
                    int empresaGass = 0;
                    int idAdminss = 0;
                    String normaAplicada = null;
                    int rutnumericoEd = 0;
                    int casas = 0;
                    int lavanderias = 0;
                    int locales = 0;
                    int areas = 0;
                    int depto = 0;
                    boolean rutCorrectoA = false;
                    int piso = 0;
                    int conducto = 0;
                    int caldera = 0;
                    int potenciaR = 0;
                    int potenciaA = 0;
                    String existencia = "s";
                    String ano = Integer.toString(fechaMod.get(Calendar.YEAR));
                    String mes = Integer.toString(fechaMod.get(Calendar.MONTH));
                    String dia = Integer.toString(fechaMod.get(Calendar.DAY_OF_MONTH));
                    /**
                     * ************************
                     */
                    /* Recepcion parametros    */
                    /**
                     * ************************
                     */
                    System.out.println("Comienzo de resivir parametros");
                    String idEdificio = request.getParameter("id_edificio");
                    /**
                     * *** NUMERICO ****
                     */
                    String idAdmin = request.getParameter("id_admin");
                    String nombreEjecutivo = request.getParameter("nombre_ejecutivo");
                    String tipoConstruccion = request.getParameter("tipo_construccion");
                    String nombreEdificio = request.getParameter("nombre_edificio");
                    String tipoInstalacion = request.getParameter("tipo_instalacion");
                    /**
                     * *** NUMERICO ****
                     */
                    String idGas = request.getParameter("id_gas");
                    /**
                     * *** NUMERICO ****
                     */
                    String idEmpresa = request.getParameter("id_empresa");
                    /**
                     * *** NUMERICO ****
                     */
                    String ciigeAnterior = request.getParameter("ciige_anterior");
                    String direccionEdificio = request.getParameter("direccion_edificio");
                    String nombreAdmin = request.getParameter("nombre_admin");
                    String telefonoAdmin = request.getParameter("telefono_admin");
                    String anoEdificio = request.getParameter("ano_edificio");
                    /**
                     * *** NUMERICO ****
                     */
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
                    String gasLocal = request.getParameter("gas_local");
                    String fechaVencimiento = request.getParameter("fecha_vencimiento");
                    String selloEdificio = request.getParameter("sello_edificio");
                    String tipoCliente = request.getParameter("tipo_cliente");
                    /**
                     * *** NUMERICO ****
                     */
                    String cantCasas = request.getParameter("cant_casas");
                    String cantLocales = request.getParameter("cant_locales");
                    String cantAreas = request.getParameter("cant_areas");
                    String cantLavanderias = request.getParameter("cant_lavanderias");
                    String cantPisos = request.getParameter("cant_pisos");
                    /**
                     * *** NUMERICO ****
                     */
                    String cantConductos = request.getParameter("cant_conductos");
                    /**
                     * *** NUMERICO ****
                     */
                    String cantCalderas = request.getParameter("cant_calderas");
                    /**
                     * *** NUMERICO ****
                     */
                    String despiche = request.getParameter("despiche");
                    String potenciaReal = request.getParameter("potencia_real");

                    /**
                     * *** NUMERICO ****
                     */
                    String nombrebd = request.getParameter("nombrebd");
                    /**
                     * ***edificio****
                     */
                    String rutbd = request.getParameter("rutbd");
                    /**
                     * ***edificio****
                     */
                    /**
                     * *** parse int ****
                     */
                    idEd = Integer.parseInt(idEdificio);
                    empresaGas = Integer.parseInt(idEmpresa);
                    tipoGas = Integer.parseInt(idGas);
                    tipoInst = Integer.parseInt(tipoInstalacion);
                    idAdmins = Integer.parseInt(idAdmin);

                    /**
                     * ************************
                     */
                    /*    Crear instancias     */
                    /**
                     * ************************
                     */
                    edificio ed = new edificio();
                    administrador ad = new administrador();
                    fecha fe = new fecha();
                    detalleEdificio deed = new detalleEdificio();
                    System.out.println("fecha modificacion"+ano+mes+dia);

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
                    if (tipoCliente.equals("edificio") || tipoCliente.equals("otros")) {
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

                        // validar mail administrador
                        emailCorrecto = validarEmail.validateEmail(emailAdmin);
                        //valida mail
                        if (emailCorrecto == false) {
                            request.setAttribute("msgErrorMail", "Error, Porfavor ingrese email correcto");
                            request.setAttribute("edificioAgregar12", 12);
                            System.out.println("error email");
                            emailCorrecto = true;
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
                        try {
                            potenciaR = Integer.parseInt(potenciaReal);
                            depto = Integer.parseInt(cantDepartamentos);
                            casas = Integer.parseInt(cantCasas);
                            areas = Integer.parseInt(cantAreas);
                            lavanderias = Integer.parseInt(cantLavanderias);
                            locales = Integer.parseInt(cantLocales);
                            piso = Integer.parseInt(cantPisos);
                            caldera = Integer.parseInt(cantCalderas);
                            conducto = Integer.parseInt(cantConductos);
                        } catch (NumberFormatException ex) {
                            error = true;
                            System.out.println("Error :" + ex);
                        }
                    } else {
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
                        try {
                            annioConvertido = Integer.parseInt(anoEdificio);
                        } catch (Exception ex) {
                            if (num.CadenaIsNumeric(anoEdificio)) {

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
                        }
                        // validar mail administrador
                        emailCorrecto = validarEmail.validateEmail(emailAdmin);
                        //valida mail
                        if (emailCorrecto == false) {
                            request.setAttribute("msgErrorMail", "Error, Porfavor ingrese email correcto");
                            request.setAttribute("edificioAgregar12", 12);
                            System.out.println("error email");
                            emailCorrecto = true;
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

                    }

                    System.out.println("canr dpto " + cantDepartamentos);

                    ed.setIdEdificio(idEd);
                    ad.setIdAdmin(idAdmins);
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
                    ed.setRutEdificio(rutEdificio);//funca
                    ed.setTelefonoEdificio(telefonoEdificio);//funca
                    ad.setRutAdmin(rutAdmin);
                    ad.setEmailAdmin(emailAdmin);
                    /**/
                    /**/
                    deed.setUbicacionMedidores(ubicacionMedidores);
                    fe.setFechaInspeccion(fechaInspeccion);
                    fe.setFechaPrimera(fechaPrimera);
                    fe.setFechaSegunda(fechaSegunda);
                    fe.setFechaCierre(fechaCierre);
                    ed.setFechaVencimiento(fechaVencimiento);
                    ed.setTipoCliente(tipoCliente);
                    ed.setSelloEdificio(selloEdificio);
                    ed.setExistenciaConductos(existencia);
                    deed.setDespiche(despiche);
                    ed.setPotenciaReal(Numpotencia);//funca
                    ed.setCantDepartamentos(Numdepto);//funca
                    ed.setCantCasas(casas);
                    ed.setCantAreas(areas);
                    ed.setCantLavanderias(lavanderias);
                    ed.setCantLocales(locales);
                    ed.setCantPisos(Numpiso);//funca
                    ed.setCantConductos(Numconducto);//funca
                    ed.setCantCalderas(Numcaldera);//funca
                    request.setAttribute("fe", fe);
                    request.setAttribute("ed", ed);
                    request.setAttribute("ad", ad);
                    request.setAttribute("deed", deed);

                    /**
                     * ************************
                     */
                    /* Validar nombre edificio */ /**
                     * IMPORTANTE*
                     */
                    /**
                     * ************************
                     */
                    ArrayList<String> listaNombres = edDAO.getNombreEdificio();
                    if (!nombrebd.equals(nombreEdificio)) {
                        if ((listaNombres.contains(nombreEdificio))) {
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
                    /*   Validar rut edificio  */
                    /**
                     * ************************
                     */
                    rutnumericoEd = validarRut.getRut(rutEdificio);
                    System.out.println("rutn numerico " + rutnumericoEd);
                    if (rutnumericoEd < 1000000) {
                        request.setAttribute("edificioAgregar9", 9);
                        error = true;
                    }

                    if (rutEdificio.trim().equals("") || rutEdificio == null) {
                        request.setAttribute("msgErrorEdificio", "Error, el Rut de edificio posee espacios o esta vacio");
                        request.setAttribute("edificioAgregar9", 9);
                        error = true;
                        System.out.println(" error rut edificio");
                    }
                    rutCorrecto = validarRut.validateRut(rutEdificio);
                    if (rutCorrecto == false) {
                        request.setAttribute("msgErrorNoValido", "Error, el rut del edificio ingresado no es correcto");
                        request.setAttribute("edificioAgregar9", 9);
                        error = true;
                    } else {
                        if (!rutbd.equals(rutEdificio)) {
                            ArrayList<String> listaRutEd = edDAO.getRutEdificio();
                            if ((listaRutEd.contains(rutEdificio))) {
                                request.setAttribute("edificioAgregar9", 8);
                                error = true;
                            }
                        }
                    }

                    /**
                     * ************************
                     */
                    /*   Validar rut admin     */
                    /**
                     * ************************
                     */
                    // valida rut administrador
                    if (rutAdmin.trim().equals("") || rutAdmin == null) {
                        request.setAttribute("msgErrorAdministrador", "Error, el Rut del administrador posee espacios o esta vacio");
                        request.setAttribute("edificioAgregar11", 11);
                        error = true;
                    } else {
                        rutAdminCorrecto = validarRut.validateRut(rutAdmin);
                        if (rutAdminCorrecto == false) {
                            request.setAttribute("msnErrorRutAdminNoValido", "Error, el rut administrador no es correcto");
                            request.setAttribute("edificioAgregar11", 11);
                            error = true;
                        }
                    }

                    /**
                     * ************************
                     */
                    /*   Validar  tipo inst    */
                    /**
                     * ************************
                     */
                    if (tipoInstalacion.equals("") || tipoInstalacion == null) {
                        request.setAttribute("OpcionNoSelecionadaTipoInst", "no se selecciono ninguna de las opciones disponibles");
                        System.out.println("error tipo instalacion");
                        error = true;
                    }
                    /**
                     * ****************
                     */
                    try {
                    } catch (Exception ex) {
                        request.setAttribute("errorTipoInstalacion", "error en tipo de instalacion");
                    }
                    if (tipoInst < 0 || tipoInst > 21) {
                        request.setAttribute("errorRango", " el tipo de instalacion esta fuera del rango establecido");
                        System.out.println(" error ripo instalacion");
                        error = true;
                    }

                    if (!error) {
                        System.out.println("fecha modificacion" + fechaMod);
                        /**
                         * ************************
                         */
                        /*    Crear instancias     */
                        /**
                         * ************************
                         */
                        edificio edi = new edificio();
                        detalleEdificio deedi = new detalleEdificio();
                        administrador adi = new administrador();
                        fecha fei = new fecha();
                        adi.setIdEdificio(idEd);
                        adi.setIdAdmin(idAdmins);
                        edi.setIdEdificio(idEd);
                        deedi.setIdEdificio(idEd);
                        fei.setIdEdificio(idEd);
                        edi.setNombreEjecutivo(nombreEjecutivo);//funca
                        deedi.setTipoConstruccion(tipoConstruccion);
                        edi.setNombreEdificio(nombreEdificio);//funca
                        deedi.setTipoInstalacion(tipoInst);
                        edi.setIdGas(tipoGas);
                        edi.setIdEmpresa(empresaGas);
                        deedi.setCiigeAnterior(ciigeAnterior);
                        edi.setDireccionEdificio(direccionEdificio);//funca
                        adi.setNombreAdmin(nombreAdmin);
                        adi.setTelefonoAdmin(telefonoAdmin);
                        edi.setAnoEdificio(annioConvertido);//funca yyyyyyy
                        edi.setRutEdificio(rutEdificio);//funca
                        edi.setTelefonoEdificio(telefonoEdificio);//funca
                        edi.setNormaAplicada(normaAplicada);
                        adi.setRutAdmin(rutAdmin);
                        adi.setEmailAdmin(emailAdmin);
                        deedi.setUbicacionMedidores(ubicacionMedidores);
                        fei.setFechaInspeccion(fechaInspeccion);
                        fei.setFechaPrimera(fechaPrimera);
                        fei.setFechaSegunda(fechaSegunda);
                        fei.setFechaCierre(fechaCierre);
                        edi.setCantDepartamentos(Numdepto);
                        ed.setCantCasas(casas);
                        ed.setCantAreas(areas);
                        ed.setCantLavanderias(lavanderias);
                        ed.setCantLocales(locales);
                        edi.setCantPisos(Numpiso);
                        edi.setCantConductos(Numconducto);
                        edi.setCantCalderas(Numcaldera);
                        deedi.setDespiche(despiche);
                        edi.setPotenciaReal(Numpotencia);
                        ed.setExistenciaConductos(existencia);

                        /*   request.setAttribute("edi", edi);
                         request.setAttribute("adi", adi);
                         request.setAttribute("deedi",deedi);
                         request.setAttribute("fei", fei); */
                        System.out.println("antes de update");
                        edDAO.update(edi);
                        deedDAO.update(deedi);
                        adDAO.update(adi);
                        feDAO.update(fei);
                        exito = 1;
                        request.setAttribute("actualizar", 1);
                        /**
                         * ************************
                         */
                        /* limpiar variables       */
                        /**
                         * ************************
                         */

                        nombreEjecutivo = null;
                        tipoConstruccion = null;
                        nombreEdificio = null;
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
                        Numpotencia = 0;
                        Numdepto = 0;
                        casas = 0;
                        areas = 0;
                        locales = 0;
                        lavanderias = 0;
                        Numpiso = 0;
                        Numconducto = 0;
                        Numcaldera = 0;
                        ed.setNombreEjecutivo(nombreEjecutivo);
                        deed.setTipoConstruccion(tipoConstruccion);
                        ed.setNombreEdificio(nombreEdificio);
                        deed.setTipoInstalacion(tipoInst);
                        deed.setCiigeAnterior(ciigeAnterior);
                        ed.setDireccionEdificio(direccionEdificio);
                        ad.setNombreAdmin(nombreAdmin);
                        ad.setTelefonoAdmin(telefonoAdmin);
                        ed.setAnoEdificio(annioConvertido);
                        ed.setRutEdificio(rutEdificio);
                        ed.setTelefonoEdificio(telefonoEdificio);
                        ad.setRutAdmin(rutAdmin);
                        ed.setTelefonoEdificio(telefonoEdificio);
                        ad.setEmailAdmin(emailAdmin);
                        deed.setUbicacionMedidores(ubicacionMedidores);
                        fe.setFechaInspeccion(fechaInspeccion);
                        fe.setFechaPrimera(fechaPrimera);
                        fe.setFechaSegunda(fechaSegunda);
                        fe.setFechaCierre(fechaCierre);
                        deed.setDespiche(despiche);
                        ed.setPotenciaReal(Numpotencia);
                        ed.setCantDepartamentos(Numdepto);
                        ed.setCantCasas(casas);
                        ed.setCantAreas(areas);
                        ed.setCantLavanderias(lavanderias);
                        ed.setCantLocales(locales);
                        ed.setCantPisos(Numpiso);
                        ed.setCantConductos(Numconducto);
                        ed.setCantCalderas(Numcaldera);
                        ed.setExistenciaConductos(existencia);
                    }
                } else {
                    request.getRequestDispatcher("/accesodenegado.jsp").forward(request, response);
                }
                //fin if anidado de tipo de usuario
            } catch (Exception sessionException) {
                /* enviar a la vista de login */
                System.out.println("no ha iniciado session");
                /*enviar al login*/
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
            //fin catch de sesion
        } catch (Exception connectionException) {
            connectionException.printStackTrace();
            System.out.println("Conexion Fallida");
        } finally {
            try {
                conexion.close();
            } catch (Exception noGestionar) {
            }
        }
        if (exito == 1) {
            request.getRequestDispatcher("/modificar-edificio.jsp").forward(request, response);
        } else {

            request.getRequestDispatcher("/modificar-edificio.jsp").forward(request, response);
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
