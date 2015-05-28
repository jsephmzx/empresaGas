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
import javax.swing.JOptionPane;

/**
 *
 * @author Natalia
 */
@WebServlet(name = "usuarioDeleteServlet", urlPatterns = {"/usuarioDeleteServlet"})
public class usuarioDeleteServlet extends HttpServlet {
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
        /*
         *Establecer conexion
         */
        try {
            conexion = ds.getConnection();
            // Conexion 
            // Conexion edificio
            usuarioDAO usDAO = new usuarioDAO();
            usDAO.setConexion(conexion);
            // Conexion administrador


            // Recepcion de parametros
            int idUsuario = Integer.parseInt(request.getParameter("id_usuario"));

            if (JOptionPane.showConfirmDialog(null, "¿Seguro que Desea Eliminar?", "Precaucion", 0) == 0) {

                //eliminacion edificio
                usDAO.delete(idUsuario);
                JOptionPane.showMessageDialog(null, "Usuario Eliminado");
            }

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
        request.getRequestDispatcher("/usuarioListaServlet?listarUsuarios=TODOS").forward(request, response);
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
