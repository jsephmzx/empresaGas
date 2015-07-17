/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package departamento;

import artefacto.artefacto;
import artefacto.artefactoDAO;
import defectoDepto.defectoDepto;
import defectoDepto.defectoDeptoDAO;
import diagnostico.diagnostico;
import diagnostico.diagnosticoDAO;
import informacion.informacion;
import informacion.informacionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
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
@WebServlet(name = "listInfoDiagServlet", urlPatterns = {"/listInfoDiagServlet"})
public class listInfoDiagServlet extends HttpServlet {
     
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
             informacionDAO infDAO = new informacionDAO();
             diagnosticoDAO diagDAO = new diagnosticoDAO();
             departamentoDAO deptoDAO = new departamentoDAO();
             defectoDeptoDAO defectoDAO = new defectoDeptoDAO();
             artefactoDAO arteDAO = new artefactoDAO();
             informacion info = new informacion();
             diagnostico diag = new diagnostico();
             departamento depto = new departamento();
             defectoDepto defDepto = new defectoDepto();
             Collection<artefacto> list = new ArrayList<artefacto>();
             Collection<defectoDepto> listDefecto = new ArrayList<defectoDepto>();
             
             infDAO.setConexion(conexion);
             diagDAO.setConexion(conexion);
             deptoDAO.setConexion(conexion);
             arteDAO.setConexion(conexion);
             defectoDAO.setConexion(conexion);
             try{
              /*obtener id departamento*/
                 String id = request.getParameter("id");
                 int idDepto = 0;
                 try{
                   idDepto = Integer.parseInt(id);
                 }catch(Exception ex){
                     request.setAttribute("errorIdDepto", "El id del departamento ha sido cambiado por valores que no corresponden");
                 }
                 System.out.println("En listInfoDiagServlet Id Departamento :"+idDepto);
                 info = infDAO.getByidDepto(idDepto);
                 System.out.println("despues de info");
                 
                 diag = diagDAO.getByidDepto(idDepto);
                 depto = deptoDAO.getByIdDepto(idDepto);
                 list =arteDAO.getByidDepto(idDepto);
                 listDefecto =defectoDAO.getByidDepto(idDepto);
                 
                 request.setAttribute("info", info);
                 request.setAttribute("diag", diag);
                 request.setAttribute("depto", depto);
                 request.setAttribute("idDepto", idDepto);
                 request.setAttribute("list", list);
                 request.setAttribute("listDefecto", listDefecto);
             } catch (Exception ex) {

            }finally {
                request.getRequestDispatcher("/infomacionDiagnostico.jsp").forward(request, response);
            }
        } catch (Exception connectionException) {
            connectionException.printStackTrace();
        } finally {
            /* cerrar conexion */
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
