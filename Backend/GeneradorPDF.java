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
    // --- NUEVO MÉTODO PARA FACTURAS ---
    public void crearTicketFactura(int idFactura, int idCotizacion, String cliente, double total, String metodoPago) {
        Document documento = new Document();
        String nombreArchivo = "Factura_Folio_" + idFactura + ".pdf";

        try {
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();

            // Encabezado
            Font fontTitulo = FontFactory.getFont(FontFactory.COURIER_BOLD, 18, BaseColor.BLACK);
            Paragraph titulo = new Paragraph("FACTURA ELECTRÓNICA - RIASA", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            
            documento.add(new Paragraph("RFC Emisor: RIA-901010-KG4")); // RFC Ficticio del taller
            documento.add(new Paragraph("Fecha de Emisión: " + new java.util.Date()));
            documento.add(new Paragraph("--------------------------------------------------"));

            // Datos del Cliente y Servicio
            documento.add(new Paragraph("FACTURA FOLIO: " + idFactura));
            documento.add(new Paragraph("Referencia Cotización: #" + idCotizacion));
            documento.add(new Paragraph("Cliente: " + cliente));
            documento.add(new Paragraph("--------------------------------------------------"));

            // Cuerpo del pago
            Paragraph pEstado = new Paragraph("ESTADO: PAGADO", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.GREEN));
            pEstado.setAlignment(Element.ALIGN_CENTER);
            documento.add(pEstado);
            
            documento.add(new Paragraph(" ")); // Espacio
            
            // Tabla de totales
            PdfPTable tabla = new PdfPTable(2);
            tabla.addCell("Concepto");
            tabla.addCell("Monto");
            
            tabla.addCell("Importe Total");
            tabla.addCell("$ " + total);
            
            tabla.addCell("Método de Pago");
            tabla.addCell(metodoPago);
            
            documento.add(tabla);
            
            documento.add(new Paragraph(" "));
            Paragraph pie = new Paragraph("¡Gracias por su preferencia!", FontFactory.getFont(FontFactory.TIMES_ITALIC, 10));
            pie.setAlignment(Element.ALIGN_CENTER);
            documento.add(pie);

            documento.close();
            
            // Abrir PDF
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File(nombreArchivo));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}