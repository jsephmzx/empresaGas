/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edificio;

import administrador.administrador;
import administrador.administradorDAO;
import artefacto.artefacto;
import artefacto.artefactoDAO;
import conducto.conducto;
import conducto.conductoDAO;
import departamento.departamento;
import departamento.departamentoDAO;
import detalleEdificio.DetalleEdificioDAO;
import detalleEdificio.detalleEdificio;
import empresagas.empresagas;
import empresagas.empresagasDAO;
import fecha.fecha;
import fecha.fechaDAO;
import gas.gas;
import gas.gasDAO;
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
import usuario.usuario;
import usuario.usuarioDAO;

/**
 *
 * @author Natalia
 */
@WebServlet(name = "fillModificarServlet", urlPatterns = {"/fillModificarServlet"})
public class fillModificarServlet extends HttpServlet {

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
            // Conexion edificio
            edificioDAO edDAO = new edificioDAO();
            edDAO.setConexion(conexion);
            
            // Conexion administrador
            administradorDAO adDAO = new administradorDAO();
            adDAO.setConexion(conexion);

            // Conexion detalle edificio
            DetalleEdificioDAO deedDAO = new DetalleEdificioDAO();
            deedDAO.setConexion(conexion);
            
            // Conexion fecha
            fechaDAO feDAO = new fechaDAO();
            feDAO.setConexion(conexion);
            
            edificio ed = new edificio();
            administrador ad = new administrador();
            detalleEdificio deed = new detalleEdificio();
            fecha fe = new fecha();
            //variables
            int idEdConvertido = 0;
            //
            String idEdificio = request.getParameter("id_edificio");
            idEdConvertido = Integer.parseInt(idEdificio);
            
            
            ed =edDAO.findbyIdEdificio(idEdConvertido);
            System.out.println("nombre edificio : "+ ed.getNombreEdificio());
            request.setAttribute("ed",ed);
            
            ad = adDAO.findbyIdEdificio(idEdConvertido);
            request.setAttribute("ad",ad);
                       
            deed = deedDAO.findbyIdEdificio(idEdConvertido);
            
            request.setAttribute("deed",deed);        
                        
            fe = feDAO.findbyIdEdificio(idEdConvertido);
            request.setAttribute("fe",fe);
            
        } catch (Exception connectionException) {
            connectionException.printStackTrace();
            JOptionPane.showMessageDialog(null, connectionException.getMessage());

        } finally {

            try {
                conexion.close();
            } catch (Exception noGestionar) {
            }
        }
        request.getRequestDispatcher("/modificar-edificio.jsp").forward(request, response);
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
