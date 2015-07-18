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
@WebServlet(name = "edifConductoGetServlet", urlPatterns = {"/edifConductoGetServlet"})
public class edifConductoGetServlet extends HttpServlet {

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
                   String id = request.getParameter("id");
                   int idEdif = 0;
                   try{
                       idEdif = Integer.parseInt(id);
                   }catch(Exception ex){
                       System.out.println("Error en formato de id, contiene caracteres alfanumericos.");
                       request.setAttribute("ErrorIdEdif", "Error en formato de id, contiene caracteres alfanumericos.");
                   }
                   conducto reg = new conducto();
                   System.out.println("reg id antes de obtener objeto desde base de datos :"+reg.getIdConducto());
                   reg = conDAO.getById(idEdif);
                    System.out.println("reg id ="+reg.getIdConducto());
                   if(reg.getIdConducto() == 0){
                    //se debe volver a obtener el registro ingresado.
                       conducto conduc = new conducto();
                            conduc.setCantDeptoConducto(0);
                            conduc.setCondSombrete("n");
                            conduc.setSecciones("n");
                            conduc.setConInterior("n");
                            conduc.setRelacionLados("n");
                            conduc.setPruebaTiro("n");
                            conduc.setTomaAire("n");
                            conduc.setMaterialidad("n");
                            conduc.setObservaciones("");
                            conduc.setSello("1");
                            conduc.setSelloConducto("1");
                            conduc.setIdEdificio(idEdif);
                            conduc.setArtefactoConducto("calefont");
                            conduc.setPotenciaArtefacto(1);
                            conduc.setSeccionConducto(0);
                            conduc.setMaterialConducto("");
                            conduc.setEspesorMaterial(0);
                            conduc.setConductoNormalizado("n");
                            conduc.setSombreteExpuesto("n");
                            conduc.setConductoQuiebre("n");
                            conduc.setSecundarioNormalizados("n");
                            conDAO.insert(conduc);
                            
                            int lastId = conDAO.getLastId();
                            reg = conDAO.findById(lastId);
                   }
                   
                   request.setAttribute("conducto", reg);
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
