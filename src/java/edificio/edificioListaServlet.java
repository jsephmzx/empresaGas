/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edificio;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "edificioListaServlet", urlPatterns = {"/edificioListaServlet"})
public class edificioListaServlet extends HttpServlet {

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
    // BORRRAAAAAAAAAAAAAAARR EXCEPTIOOOON
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        request.setCharacterEncoding("UTF-8");
        Connection conexion = null;
        boolean salir = false;
        /*
         *Establecer conexion
         */
        try {
            conexion = ds.getConnection();
            // Conexion edificio
            edificioDAO edDAO = new edificioDAO();
            edDAO.setConexion(conexion);
            //Comprobar sesion
            String userSession = (String) request.getSession().getAttribute("tipo");
            try {
                if (userSession.equals("admin") || userSession.equals("vende")) {
                ArrayList<edificio> listaEdificio = new ArrayList<edificio>();
                String listarEdificio = request.getParameter("listarEdificio");
                System.out.println("filtrado :" + listarEdificio);
                if (listarEdificio.equals("TODAS")) {
                    System.out.println("entra en if");
                    listaEdificio = (ArrayList<edificio>) edDAO.getAll();
                    salir = true;
                }
                request.setAttribute("lista", listaEdificio);
                request.setAttribute("salir", salir);
                request.getRequestDispatcher("/listarEdificio.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/accesodenegado.jsp").forward(request, response);
                }
            } catch (Exception sessionException) {
                /* enviar a la vista de login */
                System.out.println("no ha iniciado session");
                /*enviar al login*/
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (Exception connectionException) {
            connectionException.printStackTrace();

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
        try {
            processRequest(request, response);

        } catch (Exception ex) {
            Logger.getLogger(edificioListaServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);

        } catch (Exception ex) {
            Logger.getLogger(edificioListaServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
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
