/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departamento;

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
 * @author Joseph
 */
@WebServlet(name = "departamentoUpdateServlet", urlPatterns = {"/departamentoUpdateServlet"})
public class departamentoUpdateServlet extends HttpServlet {

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
        try {
            conexion = ds.getConnection();
            String userSession = (String) request.getSession().getAttribute("tipo");
            try {
                if (userSession.equals("admin")) {
                    String id = request.getParameter("id");
                    String descripcion = request.getParameter("descripcion");
                    String num = request.getParameter("numero");
                    int idDepto = Integer.parseInt(id);
                    int numDepto = 0;
                    boolean error = false;

                    if (descripcion.equals("") || descripcion == null) {
                        error = true;
                        request.setAttribute("msgErrorDescripcion", "Error, la descripción no tiene valor");
                    }

                    if (num.equals("") || num == null) {
                        error = true;
                        request.setAttribute("msgErrorNumero", "Error, el Numero de Departamento no tiene un valor valido");
                    }

                    if (error == true) {
                        /*do nothing*/
                    } else {
                        try {
                            numDepto = Integer.parseInt(num);
                        } catch (Exception ex) {
                        }

                        departamentoDAO deptoDAO = new departamentoDAO();
                        deptoDAO.setConexion(conexion);
                        try {
                            deptoDAO.updateNumberDescription(descripcion, numDepto, idDepto);
                            request.setAttribute("msgOk", "Se han realizado las modificaciones exitosamente.");
                            departamento depto = new departamento();
                            depto = deptoDAO.getByIdDepto(idDepto);
                            request.setAttribute("depto", depto);
                        } catch (Exception ex) {
                            request.setAttribute("msgErrorUpdate", "Se a producido un error al momento de modificar los datos.");
                        }
                    }
                } else {
                    request.getRequestDispatcher("/accesodenegado.jsp").forward(request, response);
                }
                //
            } catch (Exception sessionException) {
                /* enviar a la vista de login */
                System.out.println("no ha iniciado session");
                /*enviar al login*/
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (Exception connectionException) {
            connectionException.printStackTrace();
        } finally {
            /* cerrar conexion */
            try {
                conexion.close();
            } catch (Exception noGestionar) {
            }
            request.getRequestDispatcher("/modificarDepto.jsp").forward(request, response);
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
