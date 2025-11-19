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
    public void crearTicketFactura(int idFactura, int idCotizacion, String cliente, double total, String metodoPago) {
        // 1. DEFINIR TAMAÑO TICKET (aprox 80mm ancho x largo variable)
        // 226 puntos = ~80mm. 800 puntos de largo (ajustable)
        Rectangle tamanioTicket = new Rectangle(226, 800); 
        
        // Márgenes muy pequeños (izq, der, arriba, abajo)
        Document documento = new Document(tamanioTicket, 10, 10, 10, 10); 
        String nombreArchivo = "Ticket_" + idFactura + ".pdf";

        try {
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();

            // --- A. LOGO (Buscando en carpeta hermana frontend) ---
            try {
                // ".." significa subir un nivel, luego entrar a frontend
                String rutaLogo = "../frontend/logo.png"; // ¡Asegúrate que se llame logo.png o cambia el nombre aquí!
                Image logo = Image.getInstance(rutaLogo);
                
                // Escalar imagen para que quepa en el ticket
                logo.scaleToFit(100, 100); 
                logo.setAlignment(Element.ALIGN_CENTER);
                documento.add(logo);
            } catch (Exception e) {
                System.out.println("Aviso: No se encontró el logo en " + "../frontend/logo.png");
                // Si no encuentra el logo, sigue sin él para no romper el programa
            }

            // --- B. TEXTOS DEL TICKET ---
            // Usamos COURIER para que parezca ticket retro
            Font fontChica = FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK);
            Font fontMedia = FontFactory.getFont(FontFactory.COURIER_BOLD, 10, BaseColor.BLACK);
            Font fontGrande = FontFactory.getFont(FontFactory.COURIER_BOLD, 14, BaseColor.BLACK);

            Paragraph titulo = new Paragraph("TALLER RIASA", fontGrande);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            Paragraph subtitulo = new Paragraph("RFC: RIA-901010-KG4\nOaxaca, Mexico", fontChica);
            subtitulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(subtitulo);
            
            documento.add(new Paragraph("---------------------------------", fontChica));

            // Datos Venta
            Paragraph datos = new Paragraph(
                "Ticket #: " + idFactura + "\n" +
                "Ref Cot: #" + idCotizacion + "\n" +
                "Fecha: " + new java.util.Date().toString().substring(0, 10) + "\n" + // Solo fecha corta
                "Cliente: " + cliente, 
                fontChica);
            documento.add(datos);

            documento.add(new Paragraph("---------------------------------", fontChica));

            // --- C. TABLA COMPACTA ---
            // Solo 2 columnas para ahorrar espacio
            PdfPTable tabla = new PdfPTable(2);
            tabla.setWidthPercentage(100); 
            // Ajustamos ancho de columnas (70% descripción, 30% precio)
            tabla.setWidths(new float[]{3f, 1.5f}); 

            tabla.addCell(new Paragraph("Concepto", fontMedia));
            tabla.addCell(new Paragraph("Total", fontMedia));

            // Aquí podrías iterar los productos si los pasaras como lista, 
            // por ahora ponemos el resumen
            tabla.addCell(new Paragraph("Servicio General", fontChica));
            tabla.addCell(new Paragraph("$" + total, fontChica));

            documento.add(tabla);

            documento.add(new Paragraph("---------------------------------", fontChica));

            // --- D. TOTALES Y PIE ---
            Paragraph pTotal = new Paragraph("TOTAL: $" + total, fontGrande);
            pTotal.setAlignment(Element.ALIGN_RIGHT);
            documento.add(pTotal);

            Paragraph pMetodo = new Paragraph("Pago: " + metodoPago, fontChica);
            pMetodo.setAlignment(Element.ALIGN_RIGHT);
            documento.add(pMetodo);
            
            documento.add(new Paragraph("\n")); // Espacio
            
            Paragraph pie = new Paragraph("¡GRACIAS POR SU VISITA!\nConserve este ticket", fontChica);
            pie.setAlignment(Element.ALIGN_CENTER);
            documento.add(pie);

            documento.close();
            System.out.println("Ticket generado: " + nombreArchivo);

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File(nombreArchivo));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}