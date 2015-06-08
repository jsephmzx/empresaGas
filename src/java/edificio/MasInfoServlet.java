/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edificio;

import administrador.administrador;
import administrador.administradorDAO;
import detalleEdificio.DetalleEdificioDAO;
import detalleEdificio.detalleEdificio;
import empresagas.empresagas;
import empresagas.empresagasDAO;
import fecha.fecha;
import fecha.fechaDAO;
import gas.gas;
import gas.gasDAO;
import instalacion.instalacion;
import instalacion.instalacionDAO;
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

/**
 *
 * @author Natalia
 */
@WebServlet(name = "MasInfoServlet", urlPatterns = {"/MasInfoServlet"})
public class MasInfoServlet extends HttpServlet {

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
        int tipoInst = 0;
        int tipo = 0;
        int idEmp = 0;
        int tipoEmp = 0;
        /*
         *Establecer conexion
         */
        try {
            conexion = ds.getConnection();
            // Conexion edificio
            edificioDAO edDAO = new edificioDAO();
            edDAO.setConexion(conexion);
            edificio ed = new edificio();
            // Conexion administrador
            administradorDAO adDAO = new administradorDAO();
            adDAO.setConexion(conexion);
            administrador ad = new administrador();
            // Conexion detalle edificio
            DetalleEdificioDAO deedDAO = new DetalleEdificioDAO();
            deedDAO.setConexion(conexion);
            detalleEdificio deed = new detalleEdificio();
            // Conexion gas
            gasDAO gasDAO = new gasDAO();
            gasDAO.setConexion(conexion);
            gas gas = new gas();
            // Conexion fecha
            fechaDAO feDAO = new fechaDAO();
            feDAO.setConexion(conexion);
            fecha fe = new fecha();
            //Conexion empresagas
            empresagasDAO emDAO = new empresagasDAO();
            emDAO.setConexion(conexion);
            empresagas em = new empresagas();
            //Conexion
            instalacionDAO instDAO = new instalacionDAO();
            instDAO.setConexion(conexion);
            instalacion inst = new instalacion();

            String userSession = (String) request.getSession().getAttribute("tipo");
            System.out.println("tipo de usuario : "+userSession);
            try {
                if (userSession.equals("admin") || userSession.equals("vende")) {
                    //variables
                    int idEdConvertido = 0;
                    //
                    String idEdificio = request.getParameter("id_edificio");
                    idEdConvertido = Integer.parseInt(idEdificio);
                    ed = edDAO.findbyIdEdificio(idEdConvertido);

                    request.setAttribute("ed", ed);
                    ad = adDAO.findbyIdEdificio(idEdConvertido);
                    request.setAttribute("ad", ad);
                    fe = feDAO.findbyIdEdificio(idEdConvertido);
                    System.out.println("nose " + fe.fechaInspeccion);
                    request.setAttribute("fe", fe);
                    deed = deedDAO.findbyIdEdificio(idEdConvertido);
                    request.setAttribute("deed", deed);

                    tipo = ed.getIdGas();
                    gas = gasDAO.findbyIdGas(tipo);

                    request.setAttribute("gas", gas);

                    idEmp = ed.getIdEmpresa();
                    em = emDAO.findbyIdEmpresagas(idEmp);

                    request.setAttribute("em", em);

                    tipoInst = deed.getTipoInstalacion();
                    inst = instDAO.findbyTipoInst(tipoInst);
                    request.setAttribute("inst", inst);
                    request.getRequestDispatcher("/mas-info.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/accesodenegado.jsp").forward(request, response);
                }
            } catch (Exception sessionException) {
                /* enviar a la vista de login */
                System.out.println("no ha iniciado session");
                /*enviar al login*/
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
            //fin catch de session
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
