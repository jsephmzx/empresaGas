/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import helpers.Rut;
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

/**
 *
 * @author Natalia
 */
@WebServlet(name = "registrarUsuarioServlet", urlPatterns = {"/registrarUsuarioServlet"})
public class registrarUsuarioServlet extends HttpServlet {

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
        try {
            conexion = ds.getConnection();
            // Conexion usuario
            usuarioDAO userDAO = new usuarioDAO();
            validarEmail mail = new validarEmail();
            userDAO.setConexion(conexion);
            //comprobar sesion
            String userSession = (String) request.getSession().getAttribute("tipo");
            try {
                if (userSession.equals("admin")) {
                    // Recepcion de parametros
                    String nombreUsuario = request.getParameter("nombre_usuario");
                    String contrasena = request.getParameter("contrasena");
                    String contrasenaV = request.getParameter("contrasenaV");
                    String emailUsuario = request.getParameter("email_usuario");
                    String tipoUsuario = request.getParameter("tipo_usuario");
                    String rutPersona = request.getParameter("rut_persona");
                    String nombrePersona = request.getParameter("nombre_persona");
                    String apellidoPersona = request.getParameter("apellido_persona");
                    boolean error = false;
                    request.setAttribute("nombreUsuario", nombreUsuario);
                    request.setAttribute("emailUsuario", emailUsuario);
                    request.setAttribute("tipoUsuario", tipoUsuario);
                    request.setAttribute("rutP", rutPersona);
                    request.setAttribute("nombreP", nombrePersona);
                    request.setAttribute("apellidoP", apellidoPersona);
                    ArrayList<String> listaNombres = userDAO.getNombre();
                    /*  
                     Validaciones 
                     */
                    // validar mail administrador
                    boolean rutCorrecto = false;
                    Rut validarRut = new Rut(); 
                    boolean emailCorrecto = mail.validateEmail(emailUsuario);
                    
                    
                    //valida mail
                    if (emailCorrecto == false) {

                        request.setAttribute("agregar4", 4);
                        error = true;
                        emailCorrecto = true;
                    }
                    if (!contrasena.equals(contrasenaV)) {
                        request.setAttribute("agregar2", 2);
                        error = true;
                    }
                    if (listaNombres.contains(nombreUsuario)) {

                        request.setAttribute("agregar3", 3);
                        error = true;
                    }
                    if (tipoUsuario == null) {
                        request.setAttribute("agregar5", 5);
                        error = true;
                    }
                    //validar rut
                    rutCorrecto = validarRut.validateRut(rutPersona);
                    if(rutCorrecto == false){
                        request.setAttribute("errorRut", "Error, el Rut ingresado no es correcto.");
                        error = true;
                    }
                    //validar nomre y apellido
                    if(nombrePersona.equals("") || nombrePersona == null){
                        request.setAttribute("errorNombrePersona", "Error, el Nombre de la Persona esta Vacio");
                        error = true;
                    }
                    
                    if(apellidoPersona.equals("") || apellidoPersona == null){
                        request.setAttribute("errorApellidoPersona", "Error, el Apellido de la Persona esta Vacio");
                        error = true;
                    }
                    //ingreso de usuario en base de datos
                    if (!error) {
                        usuario us = new usuario();
                        us.setIdUsuario(userDAO.nuevoId());
                        us.setTipoUsuario(tipoUsuario);
                        us.setNombreUsuario(nombreUsuario);
                        us.setEmailUsuario(emailUsuario);
                        us.setContrasena(contrasena);
                        us.setRutUsuario(rutPersona);
                        us.setNombre(nombrePersona);
                        us.setApellido(apellidoPersona);
                        userDAO.insert(us);
                        //mensaje exito
                        exito = 1;
                        request.setAttribute("agregar", 1);
                        nombreUsuario = null;
                        emailUsuario = null;
                        tipoUsuario = null;
                        request.setAttribute("nombreUsuario", nombreUsuario);
                        request.setAttribute("emailUsuario", emailUsuario);
                        request.setAttribute("tipoUsuario", tipoUsuario);

                    }
                    if (exito == 1) {

                        request.getRequestDispatcher("/agregarUsuario.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("/agregarUsuario.jsp").forward(request, response);
                    }
                } else {
                    request.getRequestDispatcher("/accesodenegado.jsp").forward(request, response);
                }
            } catch (Exception sessionException) {
                /* enviar a la vista de login */
                System.out.println("no ha iniciado session");
                /*enviar al login*/
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
            //fin catch de sesion
        } catch (Exception connectionException) {
            connectionException.printStackTrace();
            JOptionPane.showMessageDialog(null, connectionException.getMessage());
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
