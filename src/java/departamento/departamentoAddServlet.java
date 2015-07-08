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
@WebServlet(name = "departamentoAddServlet", urlPatterns = {"/departamentoAddServlet"})
public class departamentoAddServlet extends HttpServlet {

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
            HttpSession session = request.getSession(true);

            artefactoDAO arteDAO = new artefactoDAO();
            arteDAO.setConexion(conexion);

            departamentoDAO deptDAO = new departamentoDAO();
            deptDAO.setConexion(conexion);

            defectoDeptoDAO defectoDAO = new defectoDeptoDAO();
            defectoDAO.setConexion(conexion);

            String userSession = (String) request.getSession().getAttribute("tipo");
            try {
                if (userSession.equals("admin")) {
                    departamento depto = new departamento();
                    /*Obtener datos del formulario*/
                    String idConducto = request.getParameter("idConducto");
                    System.out.println("ID del conducto : " + idConducto);
                    //String cantDuctos = request.getParameter("cantDuctos");
                    String observacion = request.getParameter("observacion");
                    String propietario = request.getParameter("propietario");
                    String sello = request.getParameter("sello");
                    String numero = request.getParameter("numero");

                    String tipoArtefacto = request.getParameter("tipo_artefacto");
                    String[] Artefactos = request.getParameterValues("tipo_artefacto");
                    String[] defectos = request.getParameterValues("defectos");

                    /*obtener potencias de los artefactos*/
                    String TextCalefont = request.getParameter("TextCalefont");
                    String TextCocina = request.getParameter("TextCocina");
                    String textCaldera = request.getParameter("textCaldera");
                    String textCalefactor = request.getParameter("textCalefactor");
                    String textTermo = request.getParameter("textTermo");
                    String textcentral = request.getParameter("textcentral");
                    
                    /*Obtener el id de edificio*/
                    int idEdifSession = (Integer) session.getAttribute("idEdificio");
                    System.out.println("id edificio en if :" + idEdifSession);

                    int poderCalefont = 0;
                    int poderCosina = 0;
                    int poderCaldera = 0;
                    int poderCalefactor = 0;
                    int poderTermo = 0;
                    int poderCentral = 0;

                    //int cantidadDuctos = 0;
                    int selloI = 0;
                    int numeroDepto = 0;
                    int error = 0;
                    int id = Integer.parseInt(idConducto);

                    if (Artefactos == null) {
                        error++;
                        request.setAttribute("errorArtefactos", "Error, no se han ingresado artefactos.");
                    } else {
                        for (int i = 0; i < Artefactos.length; i++) {
                            System.out.println("Artefaco numero :" + i + " Nombre :" + Artefactos[i]);
                            if (Artefactos[i].equals("calefont")) {
                                try {
                                    poderCalefont = Integer.parseInt(TextCalefont);
                                } catch (Exception ex) {
                                    error++;
                                    request.setAttribute("ErrorPotenciaCalefont", "Error, se ha ingresado caracteres alfabeticos en la Potencia del Calefont");
                                }

                            }

                            if (Artefactos[i].equals("cocina")) {
                                try {
                                    poderCosina = Integer.parseInt(TextCocina);
                                } catch (Exception ex) {
                                    error++;
                                    request.setAttribute("ErrorPotenciaCocina", "Error, se ha ingresado caracteres alfeticos en la Potencia de la Cosina");
                                }
                            }

                            if (Artefactos[i].equals("caldera")) {
                                try {
                                    poderCaldera = Integer.parseInt(textCaldera);
                                } catch (Exception ex) {
                                    error++;
                                    request.setAttribute("ErrorPotenciaCaldera", "Error, se ha ingresado caracteres alfabeticos en la Potencia de la Caldera");
                                }
                            }

                            if (Artefactos[i].equals("calefactor")) {
                                try {
                                    poderCalefactor = Integer.parseInt(textCalefactor);
                                } catch (Exception ex) {
                                    error++;
                                    request.setAttribute("ErrorPotenciaCalefactor", "Error, se ha ingresado caracteres alfabeticos en la Potencia del Calefactor");
                                }
                            }

                            if (Artefactos[i].equals("termo")) {
                                try {
                                    poderTermo = Integer.parseInt(textTermo);
                                } catch (Exception ex) {
                                    error++;
                                    request.setAttribute("ErrorPotenciaCalefactor", "Error, se ha ingresado caracteres alfabeticos en la Potencia de Termo");
                                }
                            }

                            if (Artefactos[i].equals("central")) {
                                try {
                                    poderCentral = Integer.parseInt(textcentral);
                                } catch (Exception ex) {
                                    error++;
                                    request.setAttribute("ErrorPotenciaCentral", "Error, se ha ingresado caracteres alfabeticos en la Potencia Central.");
                                }
                            }
                        }
                    }

                    selloI = Integer.parseInt(sello);
                    try {
                        numeroDepto = Integer.parseInt(numero);
                    } catch (Exception e) {
                        request.setAttribute("errorNumDepto", "Error, El numero del Departamento posee caracteres alfabéticos.");
                        error++;
                    }
                    try {
                        if (error == 0) {
                            depto.setIdConductos(id);
                            //depto.setCantConductos(cantidadDuctos);
                            depto.setDescripcion("");
                            depto.setObservacion(observacion);
                            depto.setSelloDepartamento(sello);
                            depto.setPropietario(propietario);
                            depto.setNumDepartamento(numeroDepto);
                            depto.setInfo(0);
                            depto.setIdEdificio(idEdifSession);
                            deptDAO.insert(depto);

                            int idDepto = 0;
                            idDepto = deptDAO.getLastId();
                            

                            /*Insertar en base de datos los artefactos con sus respectivas potencias*/
                            for(int x = 0; x < Artefactos.length; x++) {
                                System.out.println("Entra al proceso para comenzar a agregar artefactos");
                                artefacto reg = new artefacto();
                                if (Artefactos[x].equals("calefont")) {
                                    reg.setIdDepartamento(idDepto);
                                    reg.setTipoArtefacto(Artefactos[x]);
                                    reg.setPotenciaArtefacto(poderCalefont);
                                    arteDAO.insert(reg);
                                }

                                if (Artefactos[x].equals("cocina")) {
                                    reg.setIdDepartamento(idDepto);
                                    reg.setTipoArtefacto(Artefactos[x]);
                                    reg.setPotenciaArtefacto(poderCosina);
                                    arteDAO.insert(reg);
                                }

                                if (Artefactos[x].equals("caldera")) {
                                    reg.setIdDepartamento(idDepto);
                                    reg.setTipoArtefacto(Artefactos[x]);
                                    reg.setPotenciaArtefacto(poderCaldera);
                                    arteDAO.insert(reg);
                                }

                                if (Artefactos[x].equals("calefactor")) {
                                    reg.setIdDepartamento(idDepto);
                                    reg.setTipoArtefacto(Artefactos[x]);
                                    reg.setPotenciaArtefacto(poderCalefactor);
                                    arteDAO.insert(reg);
                                }

                                if (Artefactos[x].equals("termo")) {
                                    reg.setIdDepartamento(idDepto);
                                    reg.setTipoArtefacto(Artefactos[x]);
                                    reg.setPotenciaArtefacto(poderTermo);
                                    arteDAO.insert(reg);
                                }

                                if (Artefactos[x].equals("central")) {
                                    reg.setIdDepartamento(idDepto);
                                    reg.setTipoArtefacto(Artefactos[x]);
                                    reg.setPotenciaArtefacto(poderCentral);
                                    arteDAO.insert(reg);
                                }
                                
                            }
                            request.setAttribute("msgOk", "Se ha ingresado un nuevo Departamento exitosamente!");
                            /*Insertar en la base de datos los defectos correspondientes*/
                            if (defectos.length > 0) {
                                for(int y = 0; y < defectos.length; y++) {
                                    defectoDepto regDef = new defectoDepto ();
                                    regDef.setIdDepto(idDepto);
                                    regDef.setDefectoDepto(defectos[y]);
                                    defectoDAO.insert(regDef);
                                }
                            }

                            
                        }
                    } catch (Exception parameterException) {
                    } finally {
                        request.getRequestDispatcher("/agregarDepto.jsp").forward(request, response);
                    }

                } else {
                    request.getRequestDispatcher("/accesodenegado.jsp").forward(request, response);
                }
            } catch (Exception sessionException) {
                /* enviar a la vista de login */
                System.out.println("no ha iniciado session");
                System.out.println("Error :" + sessionException);
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
