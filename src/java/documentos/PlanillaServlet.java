/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documentos;

import administrador.administrador;
import administrador.administradorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import detalleEdificio.DetalleEdificioDAO;
import detalleEdificio.detalleEdificio;
import edificio.edificio;
import edificio.edificioDAO;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import usuario.usuario;
import usuario.usuarioDAO;

/**
 *
 * @author Joseph
 */
@WebServlet(name = "PlanillaServlet", urlPatterns = {"/PlanillaServlet"})
public class PlanillaServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("application/pdf");
        Connection conexion = null;
        HttpSession session = request.getSession(true);
        try {
            conexion = ds.getConnection();
            
            edificioDAO edifDAO = new edificioDAO();
            edifDAO.setConexion(conexion);
            
            usuarioDAO usuDAO = new usuarioDAO();
            usuDAO.setConexion(conexion);
            
            //obtener variable
            boolean error = false;
            String idString = (String)request.getParameter("id_edificio");
            int idEdificio = 0;
            try{
                idEdificio = Integer.parseInt(idString);
                
            }catch(Exception ex){
                System.out.println("Error al convertir variables :"+ex);
                error = true;
            }
            //obtener datos de edificio segun el id
            edificio edif = new edificio();
            edif = edifDAO.findbyIdEdificio(idEdificio);
            
            DetalleEdificioDAO detEdifDAO = new DetalleEdificioDAO();
            detEdifDAO.setConexion(conexion);
            
            administradorDAO adminDAO = new administradorDAO();
            adminDAO.setConexion(conexion);
            
            //obtener admin Edificio
            administrador admin = new administrador();
            admin = adminDAO.findbyIdEdificio(idEdificio);
            //obtener datos de detalleEdif
            detalleEdificio detalleEdif = new detalleEdificio();
            detalleEdif = detEdifDAO.findbyIdEdificio(idEdificio);
            
            //obtener datos de usuario segun id edificio
            usuario usuario = new usuario();
            usuario = usuDAO.findbyIdUsuarios(edif.getIdUsuario());
            //guardar datos de edif en variables separadas
            
            String nombreVendedor = usuario.getNombre();
            String apellidoVendedor = usuario.getApellido();
            int annoNorma = edif.getAnoEdificio();
            String ubicacionMed = detalleEdif.getUbicacionMedidores();
            String ciige = detalleEdif.getCiigeAnterior();
            String testDePruebas = detalleEdif.getDespiche();
            String nombreEdif = edif.getNombreEdificio();
            String correo = admin.getEmailAdmin();
            String fonoAdmin = admin.getTelefonoAdmin();
            String fonoEdif = edif.getTelefonoEdificio();
            String rutEdif = edif.getRutEdificio();
            String dirEdif = edif.getDireccionEdificio();
            String nomAdmin = admin.getNombreAdmin();
            String rutAdmin = admin.getRutAdmin();
            int cantidadDe = edif.getCantCasas() + edif.getCantDepartamentos() + edif.getCantLocales() +edif.getCantLavanderias();
            int cantPisos = edif.getCantPisos();
            int idEmpresaGas = edif.getIdGas();
            //determinar el nombre de la empresa de gas segun su id
            String empresagas = "";
            if(idEmpresaGas == 1){
                empresagas = "GASVALPO";
            }
            if(idEmpresaGas == 2){
               empresagas = "LIPIGAS";
            }
            if(idEmpresaGas == 3){
                empresagas = "ABASTIBLE";
            }
            if(idEmpresaGas == 4){
                empresagas = "GASCO";
            }
            if(idEmpresaGas == 5){
                empresagas = "OTROS";
            }
            int annoEdif = edif.getAnoEdificio();
            int potencia = edif.getPotenciaReal();
            int cantidaConductos = edif.getCantConductos();
            int cantidadCalderas = edif.getCantCalderas();
            
            //Crear documento
            Document document = new Document();
            Image logo = Image.getInstance(getClass().getResource("logo.png"));
            PdfWriter.getInstance(document, response.getOutputStream());
            Font fuenteNegrita = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
            Font fuenteNormal = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
            Font fuenteEPiePagina = new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD); 
            Font fuentePiePagina = new Font (Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);

            // step 3
            document.open();

            // step 4
            logo.setAbsolutePosition(270f, 750f);
            logo.scalePercent(65f);

            PdfPTable tablaEncabezado = new PdfPTable(2);
            PdfPTable tablaContenido = new PdfPTable(2);

            tablaEncabezado.addCell(new Paragraph("PLANILLA CIIG-e INSPECCIÓN PERIODICA"));
            tablaEncabezado.addCell(logo);

            tablaContenido.setSpacingBefore(15);
            tablaContenido.setSpacingAfter(20);
            tablaContenido.addCell(new Paragraph("VENDEDORA", fuenteNormal));
            tablaContenido.addCell(new Paragraph(nombreVendedor+" "+apellidoVendedor ,fuenteNormal));

            tablaContenido.addCell(new Paragraph("AÑO DEL EDIFICIO Y NORMA APLICAR", fuenteNormal));
            tablaContenido.addCell(new Paragraph(Integer.toString(annoEdif), fuenteNormal));
            
            tablaContenido.addCell(new Paragraph("UBICACIÓN MEDIDORES: EN LOS PISOS, FUERA DEL EDIFICO" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(ubicacionMed,fuenteNormal));
            
            tablaContenido.addCell(new Paragraph("NRO CIIGE DE LA INSPECCION ANTERIOR (VER PAGINA SEC)" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(ciige, fuenteNormal));
            
            tablaContenido.addCell(new Paragraph("T DE PRUEBAS, DESPICHE – OTRO (COCINA)" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(testDePruebas , fuenteNormal));
            
            tablaContenido.addCell(new Paragraph("NOMBRE: EDIFICIO, CONDOMINIO, PROPIETARIO, ETC." , fuenteNormal));
            tablaContenido.addCell(new Paragraph(nombreEdif , fuenteNormal));
            
            tablaContenido.addCell(new Paragraph("CORREO ELECTRONICO" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(correo , fuenteNormal));
            
            tablaContenido.addCell(new Paragraph("FONO ADMINISTRADOR /FONO EDIFICIO" , fuenteNormal));
            tablaContenido.addCell(new Paragraph("Fono Administrador :"+fonoAdmin+" Fono Edificio :"+fonoEdif , fuenteNormal));
            
            tablaContenido.addCell(new Paragraph("R.U.T EDIFICIO, CONDOMINIO, PROPIETARIOS, ETC." , fuenteNormal));
            tablaContenido.addCell(new Paragraph(rutEdif , fuenteNormal));
            
            tablaContenido.addCell(new Paragraph("DIRECCIÓN  DEL EDIFICO" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(dirEdif , fuenteNormal));
            
            tablaContenido.addCell(new Paragraph("NOMBRE ADMINISTRADOR, PROPIETARIO, ETC." , fuenteNormal));
            tablaContenido.addCell(new Paragraph(nomAdmin , fuenteNormal));
            
            tablaContenido.addCell(new Paragraph("RUT: ADMINISTRADOR, PROPIETARIO, ETC." , fuenteNormal));
            tablaContenido.addCell(new Paragraph(rutAdmin , fuenteNormal));
            
            tablaContenido.addCell(new Paragraph("CANTIDAD DE: DEPTOS, CASAS, LOCALES, ETC." , fuenteNormal));
            tablaContenido.addCell(new Paragraph(Integer.toString(cantidadDe) , fuenteNormal));
            
            tablaContenido.addCell(new Paragraph("CANTIDAD DE PISOS INMUEBLE" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(Integer.toString(cantPisos), fuenteNormal));
            
            tablaContenido.addCell(new Paragraph("EMPRESA DISTRIBUIDORA DE GAS" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(empresagas, fuenteNormal));
            
            tablaContenido.addCell(new Paragraph("CANTIDAD DE INSTALACIONES" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(Integer.toString(cantidadDe), fuenteNormal));
            
            tablaContenido.addCell(new Paragraph("TIPO DE ARTEFACTOS INSTALADOS" , fuenteNormal));
            tablaContenido.addCell(new Paragraph("", fuenteNormal));
            
            tablaContenido.addCell(new Paragraph("POTENCIA TOTAL INSTALADA" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(Integer.toString(potencia), fuenteNormal));
            
            tablaContenido.addCell(new Paragraph("CANTIDAD DE CONDUCTOS COLECTIVOS" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(Integer.toString(cantidaConductos), fuenteNormal));
            
            tablaContenido.addCell(new Paragraph("CANTIDAD DE CALDERAS CENTRALES" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(Integer.toString(cantidadCalderas) , fuenteNormal));
            
            
            document.add(tablaEncabezado);
            document.add(tablaContenido);
            Paragraph textoIng = new Paragraph("IngeníeriaRSLimitada", fuenteEPiePagina);
            textoIng.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph textPie = new Paragraph("Calle Montaña 754 Oficina 37-A Viña Del Mar / ingenieria.sr@gmail.com - Fono 032-2697175 / 92564059", fuentePiePagina);
            textPie.setAlignment(Element.ALIGN_CENTER);
            document.add(textoIng);
            document.add(textPie);
            document.close();
        } catch (DocumentException de) {
            throw new IOException(de.getMessage());
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
        } catch (SQLException ex) {
            Logger.getLogger(PlanillaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(PlanillaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
