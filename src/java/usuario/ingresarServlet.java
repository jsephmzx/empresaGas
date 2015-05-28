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
@WebServlet(name = "ingresarServlet", urlPatterns = {"/ingresarServlet"})
public class ingresarServlet extends HttpServlet {

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
            conexion = ds.getConnection();
             // Conexion usuario
            usuarioDAO usDAO = new usuarioDAO();
            usDAO.setConexion(conexion);
            // Recepcion de parametros
            String nombreUsuario = request.getParameter("usuario");
            String pass = request.getParameter("contrasena");
            System.out.println("nombre "+nombreUsuario);
            System.out.println("contraseña "+pass);

            //variables
            usuario usuario = new usuario();
            boolean error = false;
            //Busqueda usuario en db 
            usuario = usDAO.findbyIdUsuario(nombreUsuario);
            
            if (usuario != null) {
                //comparacion con contraseña almacenada
                if (usuario.getContrasena().equals(pass)) {
                    exito = 1;
                } else {
                    request.setAttribute("agregar", 4);
                    error = true;
                }
            } else {
                request.setAttribute("agregar", 3);
                error = true;
            }
            //exito
            if (!error) {                       
                request.getSession().setAttribute("nombre", usuario.getNombreUsuario());
                request.getSession().setAttribute("correo", usuario.getEmailUsuario());
                request.getSession().setAttribute("tipo",usuario.getTipoUsuario());
                request.getSession().setAttribute("noadmin", 1);
                if (usuario.getTipoUsuario().equals("admin")) {
                    request.getSession().removeAttribute("noadmin");
                    request.getSession().setAttribute("noadmin", 0);
                }

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
        if (exito == 1) {
            request.getRequestDispatcher("/user.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
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
