/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conducto;

import departamento.departamento;
import departamento.departamentoDAO;
import edificio.edificio;
import edificio.edificioDAO;
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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author Joseph
 */
@WebServlet(name = "conductoMainServlet", urlPatterns = {"/conductoMainServlet"})
public class conductoMainServlet extends HttpServlet {

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
            //////////////////////////////////////////
            // ESTABLECER CONEXION
            /////////////////////////////////////////

            conexion = ds.getConnection();
            HttpSession session = request.getSession(true);
            conductoDAO conDAO = new conductoDAO();
            conDAO.setConexion(conexion);
            departamentoDAO deptoDAO = new departamentoDAO();
            deptoDAO.setConexion(conexion);
            edificioDAO ediDAO = new edificioDAO();
            ediDAO.setConexion(conexion);
            //////////////////////////////////////////
            //    crear comprobar sesion            //
            //////////////////////////////////////////
            String userSession = (String) request.getSession().getAttribute("tipo");
            System.out.println("Tipo de usuario que ingreso al sistema :" + userSession);
            try {
                /*obtener lista de conductos*/
                if (userSession.equals("admin")) {
                    try {
                        /*obtener id del conducto*/
                        String id = "";
                        id = request.getParameter("id");
                        request.setAttribute("idConducto", id);
                        if (id != null) {
                            int idNum = Integer.parseInt(id);
                            Collection<departamento> listDepto = new ArrayList<departamento>();
                            listDepto = deptoDAO.getAllById(idNum);
                            System.out.println("Obtuvo la lista de departamentos");
                            request.setAttribute("listDepto", listDepto);
                        }
                        /*fin obtener id del conducto*/

                        String idEdif = request.getParameter("id_edificio");
                        System.out.println("id edificio :" + idEdif);
                        int cantConductos = 0;
                        int idEdifSession = 0;
                        Collection<conducto> list = null;
                        if (idEdif == null) {
                            System.out.println("entro a if de idEdif == null");
                            idEdifSession = (Integer) session.getAttribute("idEdificio");
                            System.out.println("id edificio en if :" + idEdifSession);
                        }
                        if (idEdifSession != 0) {
                            list = conDAO.getAllById(idEdifSession);
                            cantConductos = conDAO.cantidadConductos(idEdifSession);
                            session.setAttribute("idEdificio", idEdifSession);
                            edificio edif = new edificio();
                            edif = ediDAO.findbyIdEdificio(idEdifSession);
                            request.setAttribute("edifName", edif.getNombreEdificio());
                        } else {
                            int idEdificio = Integer.parseInt(idEdif); //mientras para realizar pruebas directamente sin obtener el id del edificio
                            list = conDAO.getAllById(idEdificio);
                            cantConductos = conDAO.cantidadConductos(idEdificio);
                            session.setAttribute("idEdificio", idEdificio);
                            edificio edif = new edificio();
                            edif = ediDAO.findbyIdEdificio(idEdificio);
                            request.setAttribute("edifName", edif.getNombreEdificio());
                        }

                        if (list.size() > 0) {
                            request.setAttribute("cantConductos", cantConductos);
                            request.setAttribute("list", list);
                        }
                        request.getRequestDispatcher("/ductos.jsp").forward(request, response);
                    } catch (Exception ex) {

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
