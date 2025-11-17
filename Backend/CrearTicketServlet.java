import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// La anotación @WebServlet mapea la URL /crearTicket a esta clase
@WebServlet("/crearTicket")
public class CrearTicketServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Establecer el tipo de respuesta
        response.setContentType("text/html;charset=UTF-8");
        
        // 2. Obtener los datos del formulario HTML
        // Los nombres ("cliente", "vehiculo", etc.) deben coincidir
        // con los atributos 'name' del formulario.
        request.setCharacterEncoding("UTF-8");
        String cliente = request.getParameter("cliente");
        String vehiculo = request.getParameter("vehiculo");
        String placas = request.getParameter("placas");
        String servicio = request.getParameter("servicio");
        String costo = request.getParameter("costo");

        // 3. Obtener la fecha y hora actual
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaHora = dtf.format(LocalDateTime.now());

        // 4. Generar la página HTML del Ticket (la "vista previa")
        PrintWriter out = response.getWriter();
        
        // Aquí es donde construyes el HTML que el usuario verá
        out.println("<!DOCTYPE html>");
        out.println("<html lang='es'>");
        out.println("<head>");
        out.println("<title>Ticket de Servicio</title>");
        // ¡Importante! Los estilos para la impresora
        out.println(getCssEstilos()); 
        out.println("</head>");
        out.println("<body>");

        // Contenedor del ticket
        out.println("<div class='ticket-container'>");
        out.println("<h2>Taller A/C 'El Frío'</h2>");
        out.println("<p>Reparación de Climas Automotrices</p>");
        out.println("<hr>");
        out.println("<p><strong>Fecha:</strong> " + fechaHora + "</p>");
        out.println("<p><strong>Cliente:</strong> " + escapeHtml(cliente) + "</p>");
        out.println("<p><strong>Vehículo:</strong> " + escapeHtml(vehiculo) + "</p>");
        out.println("<p><strong>Placas:</strong> " + escapeHtml(placas) + "</p>");
        out.println("<hr>");
        out.println("<h3>Servicio Realizado:</h3>");
        // Reemplazar saltos de línea por <br> para HTML
        out.println("<p>" + escapeHtml(servicio).replaceAll("\n", "<br>") + "</p>"); 
        out.println("<hr>");
        out.println("<p class='total'><strong>TOTAL: $" + escapeHtml(costo) + "</strong></p>");
        out.println("<hr>");
        out.println("<p class='footer'>¡Gracias por su preferencia!</p>");
        out.println("</div>");

        // Botón de impresión (que se oculta al imprimir)
        out.println("<div class='controles-impresion'>");
        out.println("<button onclick='window.print()'>Imprimir Ticket</button>");
        out.println("</div>");
        
        out.println("</body>");
        out.println("</html>");
    }

    // Método privado para insertar el bloque de CSS
    private String getCssEstilos() {
        return "<style>" +
                "body { background: #f0f0f0; font-family: 'Courier New', Courier, monospace; }" +
                ".ticket-container { " +
                "   width: 300px; /* Ancho típico de ticket */" +
                "   margin: 20px auto; " +
                "   padding: 15px; " +
                "   background: #fff; " +
                "   border: 1px dashed #888; " +
                "   text-align: left; " +
                "}" +
                "h2, h3, p { margin: 5px 0; }" +
                "h2 { text-align: center; margin-bottom: 10px; }" +
                ".total { font-size: 1.2em; text-align: right; }" +
                ".footer { text-align: center; font-size: 0.8em; }" +
                "hr { border: none; border-top: 1px dashed #000; }" +
                
                // Controles del botón
                ".controles-impresion { text-align: center; margin-top: 20px; }" +
                ".controles-impresion button { padding: 10px; font-size: 16px; }" +

                // --- ¡LA PARTE MÁS IMPORTANTE! ---
                // Estilos que SÓLO se aplican al imprimir
                "@media print {" +
                "   /* Ocultar todo lo que no sea el ticket */" +
                "   body { background: #fff; margin: 0; }" +
                "   .controles-impresion { display: none; }" +
                "   .ticket-container { " +
                "       width: 100%; /* El ticket usa todo el ancho del papel */" +
                "       margin: 0; " +
                "       padding: 0; " +
                "       border: none; " +
                "   }" +
                "}" +
                "</style>";
    }

    // Pequeña utilidad para evitar problemas de seguridad (Inyección XSS)
    private String escapeHtml(String data) {
        if (data == null) return "";
        return data.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&#39;");
    }
}