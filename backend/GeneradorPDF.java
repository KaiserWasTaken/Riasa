package backend;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.List;
import java.awt.Desktop;
import java.io.File;

public class GeneradorPDF {

    public void crearDocumento(int folio, String nombreCliente, String auto, List<servicioItem> servicios) {
        Document documento = new Document();
        String nombreArchivo = "Cotizacion_" + folio + ".pdf";

        try {
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();

            // 1. Título
            Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLUE);
            Paragraph titulo = new Paragraph("TALLER MECÁNICO RIASA", fuenteTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(new Paragraph(" ")); // Espacio vacío

            // 2. Datos Generales
            documento.add(new Paragraph("Folio Cotización: #" + folio));
            documento.add(new Paragraph("Cliente: " + nombreCliente));
            documento.add(new Paragraph("Vehículo: " + auto));
            documento.add(new Paragraph("Fecha: " + new java.util.Date()));
            documento.add(new Paragraph("-----------------------------------------------------------"));
            documento.add(new Paragraph(" "));

            // 3. Tabla de Servicios
            PdfPTable tabla = new PdfPTable(4); // 4 columnas
            // Encabezados
            tabla.addCell("Descripción");
            tabla.addCell("Cant.");
            tabla.addCell("P. Unitario");
            tabla.addCell("Subtotal");

            double totalFinal = 0;

            // Llenar con los datos de la lista
            for (servicioItem item : servicios) {
                tabla.addCell(item.descripcion);
                tabla.addCell(String.valueOf(item.cantidad));
                tabla.addCell("$" + item.precio);
                tabla.addCell("$" + item.getSubtotal());
                totalFinal += item.getSubtotal();
            }
            documento.add(tabla);

            // 4. Total
            Paragraph pTotal = new Paragraph("TOTAL A PAGAR: $" + totalFinal);
            pTotal.setAlignment(Element.ALIGN_RIGHT);
            documento.add(pTotal);

            documento.close();
            System.out.println("PDF Generado: " + nombreArchivo);

            // 5. Abrir el PDF automáticamente
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File(nombreArchivo));
            }

        } catch (Exception e) {
            System.out.println("Error al crear PDF: " + e.getMessage());
        }
    }
    // --- MÉTODO ACTUALIZADO: TICKET CON DETALLES ---
    // Agregamos el parámetro: List<ServicioItem> servicios
public void crearTicketFactura(int idFactura, int idCotizacion, String nombreReal, String rfc, double total, String metodoPago, java.util.List<servicioItem> servicios) {
        
        // Tamaño ticket 80mm
        com.itextpdf.text.Rectangle tamanioTicket = new com.itextpdf.text.Rectangle(226, 1000); // Largo variable
        Document documento = new Document(tamanioTicket, 10, 10, 10, 10); 
        String nombreArchivo = "Ticket_" + idFactura + ".pdf";

        try {
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();

            // --- LOGO Y ENCABEZADO ---
            // (Igual que antes...)
            Font fontChica = FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK);
            Font fontMedia = FontFactory.getFont(FontFactory.COURIER_BOLD, 10, BaseColor.BLACK);
            Font fontGrande = FontFactory.getFont(FontFactory.COURIER_BOLD, 14, BaseColor.BLACK);

            Paragraph titulo = new Paragraph("TALLER RIASA", fontGrande);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            Paragraph subtitulo = new Paragraph("RFC: RIA-901010-KG4\nOaxaca, Mexico\nTel: 555-000-0000", fontChica);
            subtitulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(subtitulo);
            
            documento.add(new Paragraph("---------------------------------", fontChica));

            // Datos Venta
            Paragraph datos = new Paragraph(
                "Ticket #: " + idFactura + "\n" +
                "Ref Cot: #" + idCotizacion + "\n" +
                "Fecha: " + new java.util.Date().toString().substring(0, 16) + "\n" + 
                "Cliente: " + nombreReal + "\n" +  // <--- NOMBRE
                "RFC: " + rfc,                     // <--- RFC
                fontChica);
            documento.add(datos);

            documento.add(new Paragraph("---------------------------------", fontChica));

            // --- TABLA DE PRODUCTOS (DINÁMICA) ---
            PdfPTable tabla = new PdfPTable(2);
            tabla.setWidthPercentage(100); 
            tabla.setWidths(new float[]{3f, 1.2f}); // Columna ancha para descripción, angosta para precio

            tabla.addCell(new Paragraph("Concepto", fontMedia));
            tabla.addCell(new Paragraph("Importe", fontMedia));

            // *** AQUÍ ESTÁ LA MAGIA: RECORREMOS LA LISTA ***
            for (servicioItem item : servicios) {
                // Formato: "2x Aceite de Motor"
                String desc = item.cantidad + "x " + item.descripcion;
                double importe = item.cantidad * item.precio;

                // Agregamos a la tabla del PDF
                tabla.addCell(new Paragraph(desc, fontChica));
                tabla.addCell(new Paragraph("$" + String.format("%.2f", importe), fontChica));
            }

            documento.add(tabla);

            documento.add(new Paragraph("---------------------------------", fontChica));

            // --- TOTALES ---
            Paragraph pTotal = new Paragraph("TOTAL: $" + String.format("%.2f", total), fontGrande);
            pTotal.setAlignment(Element.ALIGN_RIGHT);
            documento.add(pTotal);

            Paragraph pMetodo = new Paragraph("Pago: " + metodoPago, fontChica);
            pMetodo.setAlignment(Element.ALIGN_RIGHT);
            documento.add(pMetodo);
            
            documento.add(new Paragraph("\n")); 
            
            Paragraph pie = new Paragraph("¡GRACIAS POR SU COMPRA!\nNO SE ACEPTAN DEVOLUCIONES\nDESPUÉS DE 30 DÍAS", fontChica);
            pie.setAlignment(Element.ALIGN_CENTER);
            documento.add(pie);

            documento.close();

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File(nombreArchivo));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}