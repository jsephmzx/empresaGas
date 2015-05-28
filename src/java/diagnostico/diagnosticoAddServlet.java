/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diagnostico;

import departamento.departamentoDAO;
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
@WebServlet(name = "diagnosticoAddServlet", urlPatterns = {"/diagnosticoAddServlet"})
public class diagnosticoAddServlet extends HttpServlet {
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
           diagnosticoDAO diagDAO = new diagnosticoDAO();
           diagDAO.setConexion(conexion);
           departamentoDAO deptoDAO = new departamentoDAO();
           deptoDAO.setConexion(conexion);
           diagnostico diag = new diagnostico();
           
           /*obtener datos del formulario*/
           String id = request.getParameter("id_depto");
           String detalle = request.getParameter("detalle");
           String obsDetalle = request.getParameter("observacion_detalle");
           String sello = request.getParameter("ducto");
           int error = 0;
           int idDepartamento = 0;
           try{
               idDepartamento = Integer.parseInt(id);
           }catch(Exception ex){
               error++;
               request.setAttribute("ErrorIdDepto", "Error en id de edificio, contiene caracteres alfabeticos");
           }
           
           if(detalle.equals("") || detalle == null){
               error++;
               request.setAttribute("ErrorDetalle", "Error, el campo detalle se encuentra vacio.");
           }
           
           if(obsDetalle.equals("") || obsDetalle == null){
               error++;
               request.setAttribute("ErrorObsDetalle", "Error, el campo Detalle se encuentra vacio.");
           }
           
           if(error>0){
               /*do nothing*/
           }else{
              diag.setIdDepartamento(idDepartamento);
              diag.setDetalle(detalle);
              diag.setObsDetalle(obsDetalle);
              diagDAO.insert(diag);
              deptoDAO.updateSello(idDepartamento, sello);
              request.setAttribute("msgOk", "Diagnostico ingresado exitosamente!");
           }
        } catch (Exception connectionException) {
            connectionException.printStackTrace();
        } finally {
            /* cerrar conexion */
            try {
                conexion.close();
            } catch (Exception noGestionar) {
            }
            request.getRequestDispatcher("/agregarDiagnostico.jsp").forward(request, response);
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
