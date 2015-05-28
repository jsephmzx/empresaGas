/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "adminChangeServlet", urlPatterns = {"/adminChangeServlet"})
public class adminChangeServlet extends HttpServlet {

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
            usuarioDAO usDAO = new usuarioDAO();
            usDAO.setConexion(conexion);
            usuario hi = new usuario();
            //variables
            boolean error = false;
            /*
             *Recibir parametros
             */
            String idUsuario = request.getParameter("id_usuario");
            String nombreUsuario = request.getParameter("nombre_usuario");
            String contrasena = request.getParameter("contrasena");
            String emailUsuario = request.getParameter("email_usuario");
            String tipoUsuario = request.getParameter("tipo_usuario");
            String listarUsuarios = request.getParameter("listarUsuarios");            
            request.setAttribute("listarUsuarios", listarUsuarios);
            
            int idUser = Integer.parseInt(idUsuario);
            if (tipoUsuario == null) {
                request.setAttribute("agregar5", 5);
                error = true;
            }
            System.out.println("waaa");
            if (!error) {
                request.setAttribute("error", error);
                usuario us = new usuario();
                us.setIdUsuario(idUser);
                us.setNombreUsuario(nombreUsuario);
                us.setContrasena(contrasena);
                us.setEmailUsuario(emailUsuario);
                us.setTipoUsuario(tipoUsuario);
                usDAO.update(us);
                //mensaje exito
                exito = 1;
                request.setAttribute("modificar", 1);  
                
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
        if (exito == 1) {
            request.getRequestDispatcher("/modificarAdministrador.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/modificarAdministrador.jsp").forward(request, response);
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
