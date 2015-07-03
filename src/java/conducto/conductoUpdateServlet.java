/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conducto;

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
@WebServlet(name = "conductoUpdateServlet", urlPatterns = {"/conductoUpdateServlet"})
public class conductoUpdateServlet extends HttpServlet {

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

            conductoDAO conDAO = new conductoDAO();
            conDAO.setConexion(conexion);
            String userSession = (String) request.getSession().getAttribute("tipo");
            try {
                if (userSession.equals("admin")) {
                    /*obtener datos*/
                    String id = request.getParameter("id");
                    int idConducto = Integer.parseInt(id);
                    String condSombrete = request.getParameter("cond_sombrete");
                    String secciones = request.getParameter("secciones");
                    String conInterior = request.getParameter("con_interior");
                    String relacionLados = request.getParameter("relacion_lados");
                    String pruebaTiro = request.getParameter("prueba_tiro");
                    String tomaAire = request.getParameter("toma_aire");
                    String materialidad = request.getParameter("materialidad");
                    String observaciones = request.getParameter("observaciones");
                    String sello = request.getParameter("sello");
                    String artefactoConducto = request.getParameter("artefacto");
                    String potenciaArtefacto = request.getParameter("potencia");
                    int potencia = 0;
                    boolean error = false;
                    try {
                        potencia = Integer.parseInt(potenciaArtefacto);
                    } catch (Exception ex) {
                        request.setAttribute("msgErrorPotencia", "Error, La potencia ingresada posee caracteres alfabeticos.");
                        error = true;
                    }

                    if (error == false) {
                        conducto conductoMod = new conducto();
                        conductoMod.setCondSombrete(condSombrete);
                        conductoMod.setSecciones(secciones);
                        conductoMod.setConInterior(conInterior);
                        conductoMod.setRelacionLados(relacionLados);
                        conductoMod.setPruebaTiro(pruebaTiro);
                        conductoMod.setTomaAire(tomaAire);
                        conductoMod.setMaterialidad(materialidad);
                        conductoMod.setObservaciones(observaciones);
                        conductoMod.setSello(sello);
                        conductoMod.setArtefactoConducto(artefactoConducto);
                        conductoMod.setPotenciaArtefacto(potencia);
                        conductoMod.setIdConducto(idConducto);
                        request.setAttribute("conducto", conductoMod);
                        conDAO.updateCondiciones(conductoMod);
                        request.setAttribute("msgOk", "Se realizaron las modificaciones Exitosamente.");
                    }else{
                       conducto conductoBD = new conducto();
                       conductoBD = conDAO.findById(idConducto);
                       request.setAttribute("conducto", conductoBD);
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
        } catch (Exception connectionException) {
            connectionException.printStackTrace();
        } finally {
            /* cerrar conexion */
            try {
                conexion.close();
            } catch (Exception noGestionar) {
            }
            request.getRequestDispatcher("/condicionesConducto.jsp").forward(request, response);
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
