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
@WebServlet(name = "contrasenaChangeServlet", urlPatterns = {"/contrasenaChangeServlet"})
public class contrasenaChangeServlet extends HttpServlet {

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
            //Comprobar sesion
            String userSession = (String) request.getSession().getAttribute("tipo");
            try {
                if (userSession.equals("admin") || userSession.equals("vende")) {
                    //variables

                    boolean error = false;
                    usuario us = new usuario();
                    /*
                     *Recibir parametros
                     */

                    String idUsuario = request.getParameter("id_usuario");
                    String nombreUsuario = request.getParameter("nombre_usuario");
                    String contrasenaBD = request.getParameter("contrasenaBD");
                    String contrasena = request.getParameter("contrasena");//nueva
                    String contrasenaV = request.getParameter("contrasenaV");//repetir nueva
                    String contrasenaA = request.getParameter("contrasenaA");//ingreso antigua para validar
                    String emailUsuario = request.getParameter("email_usuario");
                    String tipoUsuario = request.getParameter("tipo_usuario");
                    int idUser = Integer.parseInt(idUsuario);
                    us.setIdUsuario(idUser);
                    us.setNombreUsuario(nombreUsuario);
                    us.setContrasena(contrasena);
                    us.setEmailUsuario(emailUsuario);
                    us.setTipoUsuario(tipoUsuario);
                    request.setAttribute("us", us);
                    // validar si contraseña antigua es igual a la anterior
                    System.out.println("nombreUsuario" + nombreUsuario);
                    if (!contrasenaBD.equals(contrasenaA)) {
                        request.setAttribute("agregar5", 5);
                        error = true;
                    }
                    //valida si contraseñas son iguales
                    if (!contrasena.equals(contrasenaV)) {
                        request.setAttribute("agregar2", 2);

                        error = true;
                    }

                    if (!error) {

                        //   usuario user = new usuario();
                        usuario user = new usuario();
                        user.setNombreUsuario(nombreUsuario);
                        user.setContrasena(contrasena);
                        user.setEmailUsuario(emailUsuario);
                        user.setTipoUsuario(tipoUsuario);
                        user.setIdUsuario(idUser);
                        System.out.println("id " + idUser);
                        System.out.println("name " + nombreUsuario);
                        System.out.println("mail " + emailUsuario);
                        System.out.println("tipo " + tipoUsuario);
                        System.out.println("pass " + contrasena);
                        System.out.println("contraseña getContraseña :" + user.getContrasena());
                        usDAO.update(user);
                        //mensaje exito
                        exito = 1;
                        request.setAttribute("password", 1);
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
            //termino catch session
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
            request.getRequestDispatcher("/modificarContrasena.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/modificarContrasena.jsp").forward(request, response);
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
