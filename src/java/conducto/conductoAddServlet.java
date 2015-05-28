/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conducto;

import departamento.departamento;
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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author Joseph
 */
@WebServlet(name = "conductoAddServlet", urlPatterns = {"/conductoAddServlet"})
public class conductoAddServlet extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        try {
            conexion = ds.getConnection();
            
            System.out.println("entro a conductoAddServlet");
            int idEdificio = 0;
            idEdificio = (Integer) session.getAttribute("idEdificio");
            System.out.println("Id del edicio :" + idEdificio);
            conductoDAO conDAO = new conductoDAO();
            conDAO.setConexion(conexion);
            /////////////////////////////////////////
            // RECIBIR PARAMETROS
            ////////////////////////////////////////
            try {
                String cantDepto = request.getParameter("cant_depto");
                //String sello = request.getParameter("sello");
                int cantdept = 0;
                int error = 0;
                if (cantDepto.equals("")) {
                    error++;
                    request.setAttribute("ErrorCantDepto", "Error, el campo Cantidad de Departementos esta vacio.");
                }
                /*if (sello.equals("")) {
                    error++;
                    request.setAttribute("ErrorSello", "Error, el campo Sello esta vacio.");}*/
                
                try {
                    cantdept = Integer.parseInt(cantDepto);
                } catch (Exception e) {
                    request.setAttribute("ErrorCantDepto", "El numero ingresado en Cantidad de departamentos contiene caracteres alfabéticos.");
                    error++;
                }

                if (error == 0) {
                    conducto cond = new conducto();
                    cond.setCantDeptoConducto(cantdept);
                  //  cond.setSelloConducto(sello);
                    cond.setIdEdificio(idEdificio);

                    conDAO.insert(cond);
                    request.setAttribute("msgOk", "Se a ingresado un nuevo conducto Exitosamente!");
                    //Crear la cantidad de departamentos ingresados en la base de datos
                    departamentoDAO deptoDAO = new departamentoDAO();
                    deptoDAO.setConexion(conexion);
                    int idConducto = conDAO.getLastId();
                    System.out.println("id conducto :" + idConducto);
                    departamento depto = new departamento();
                    depto.setIdConductos(idConducto);
                    depto.setCantConductos(0);
                    depto.setDescripcion("Ingrese descripción");
                    depto.setSelloDepartamento("1");
                    depto.setNumDepartamento(0);
                    for (int cont = 1; cont <= cantdept; cont++) {
                        deptoDAO.insert(depto);
                        System.out.println("cont :" + cont);
                    }
                    //Fin de la creación de departamentos
                }
            } catch (Exception parameterException) {
            } finally {
                request.getRequestDispatcher("/agregarConducto.jsp").forward(request, response);
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
