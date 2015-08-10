/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documentos;

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
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.net.URL;
import java.util.Date;

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
            throws ServletException, IOException {
        response.setContentType("application/pdf");

        try {

            Document document = new Document();
            Image logo = Image.getInstance(getClass().getResource("logo.png"));
            PdfWriter.getInstance(document, response.getOutputStream());
            Font fuenteNegrita = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
            Font fuenteNormal = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);

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
            tablaContenido.addCell(new Paragraph("VENDEDORA", fuenteNormal));
            tablaContenido.addCell(new Paragraph(""));

            tablaContenido.addCell(new Paragraph("AÑO DEL EDIFICIO Y NORMA APLICAR", fuenteNormal));
            tablaContenido.addCell(new Paragraph(""));
            
            tablaContenido.addCell(new Paragraph("UBICACIÓN MEDIDORES: EN LOS PISOS, FUERA DEL EDIFICO" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(""));
            
            tablaContenido.addCell(new Paragraph("NRO CIIGE DE LA INSPECCION ANTERIOR (VER PAGINA SEC)" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(""));
            
            tablaContenido.addCell(new Paragraph("T DE PRUEBAS, DESPICHE – OTRO (COCINA)" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(""));
            
            tablaContenido.addCell(new Paragraph("NOMBRE: EDIFICIO, CONDOMINIO, PROPIETARIO, ETC." , fuenteNormal));
            tablaContenido.addCell(new Paragraph(""));
            
            tablaContenido.addCell(new Paragraph("CORREO ELECTRONICO" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(""));
            
            tablaContenido.addCell(new Paragraph("FONO ADMINISTRADOR /FONO EDIFICIO" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(""));
            
            tablaContenido.addCell(new Paragraph("R.U.T EDIFICIO, CONDOMINIO, PROPIETARIOS, ETC." , fuenteNormal));
            tablaContenido.addCell(new Paragraph(""));
            
            tablaContenido.addCell(new Paragraph("DIRECCIÓN  DEL EDIFICO" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(""));
            
            tablaContenido.addCell(new Paragraph("NOMBRE ADMINISTRADOR, PROPIETARIO, ETC." , fuenteNormal));
            tablaContenido.addCell(new Paragraph(""));
            
            tablaContenido.addCell(new Paragraph("RUT: ADMINISTRADOR, PROPIETARIO, ETC." , fuenteNormal));
            tablaContenido.addCell(new Paragraph(""));
            
            tablaContenido.addCell(new Paragraph("CANTIDAD DE: DEPTOS, CASAS, LOCALES, ETC." , fuenteNormal));
            tablaContenido.addCell(new Paragraph(""));
            
            tablaContenido.addCell(new Paragraph("CANTIDAD DE PISOS INMUEBLE" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(""));
            
            tablaContenido.addCell(new Paragraph("EMPRESA DISTRIBUIDORA DE GAS" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(""));
            
            tablaContenido.addCell(new Paragraph("CANTIDAD DE INSTALACIONES" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(""));
            
            tablaContenido.addCell(new Paragraph("TIPO DE ARTEFACTOS INSTALADOS" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(""));
            
            tablaContenido.addCell(new Paragraph("POTENCIA TOTAL INSTALADA" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(""));
            
            tablaContenido.addCell(new Paragraph("CANTIDAD DE CONDUCTOS COLECTIVOS" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(""));
            
            tablaContenido.addCell(new Paragraph("CANTIDAD DE CALDERAS CENTRALES" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(""));
            
            tablaContenido.addCell(new Paragraph("HORARIO DE INGRESO AL EDIFICIO" , fuenteNormal));
            tablaContenido.addCell(new Paragraph(""));
            
            tablaContenido.addCell(new Paragraph("HORARIO DE SALIDA DEL EDIFICIO " , fuenteNormal));
            tablaContenido.addCell(new Paragraph(""));
            
            
            document.add(tablaEncabezado);

            document.add(tablaContenido);

            // step 5
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
