/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import helpers.validarEmail;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Natalia
 */
@WebServlet(name = "usuarioChangeServlet", urlPatterns = {"/usuarioChangeServlet"})
public class usuarioChangeServlet extends HttpServlet {

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
        int exito=0;
        try {
            conexion = ds.getConnection();

            // Conexion usuario
            usuarioDAO usDAO = new usuarioDAO();
            usDAO.setConexion(conexion);

            //variables
            validarEmail mail = new validarEmail();
            usuario us = new usuario();
            boolean emailCorrecto = false;
            boolean error = false;
            /*
             *Recibir parametros
             */
            String idUsuario = request.getParameter("id_usuario");
            String nombreUsuario = request.getParameter("nombre_usuario");
            String contrasena = request.getParameter("contrasena");
            String emailUsuario = request.getParameter("email_usuario");
            String tipoUsuario = request.getParameter("tipo_usuario");
            int idUser = Integer.parseInt(idUsuario);
            us.setIdUsuario(idUser);
            System.out.println("id user :"+us.getIdUsuario());
            
            us.setNombreUsuario(nombreUsuario);
            us.setContrasena(contrasena);
            us.setEmailUsuario(emailUsuario);
            us.setTipoUsuario(tipoUsuario);
            request.setAttribute("us", us);
            // validar mail administrador
            emailCorrecto = validarEmail.validateEmail(emailUsuario);
            //valida mail
            if (emailCorrecto == false) {
                request.setAttribute("agregar4", 4);
                emailCorrecto = true;
                error = true;
            }
            
            if (!error) {
                usuario user = new usuario();
                user.setIdUsuario(idUser);
                user.setNombreUsuario(nombreUsuario);
                user.setContrasena(contrasena);
                user.setEmailUsuario(emailUsuario);
                user.setTipoUsuario(tipoUsuario);
                usDAO.update(user);
                //mensaje exito
                exito = 1;
                request.setAttribute("email", 1);
                emailUsuario =null;
                us.setEmailUsuario(emailUsuario);
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
        if(exito==1){
        request.getRequestDispatcher("/modificarUsuario.jsp").forward(request, response);
        }
        else{
            request.getRequestDispatcher("/modificarUsuario.jsp").forward(request, response);
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
