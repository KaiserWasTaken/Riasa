package backend;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RiasaDAO dao = new RiasaDAO();
        GeneradorPDF pdf = new GeneradorPDF();

        /*  --- DATOS DE PRUEBA ---
        // Cliente
        String rfcPrueba = "XAXX010101000"; // Debe cumplir el CHECK (largo >= 12)
        String nombre = "Maria Gonzales";
        String tel = "555-1234";
        String dir = "Av. Siempre Viva 123";
        String email = "maria@correo.com";

        // Auto (Nota que usaremos el rfcPrueba para vincularlo)
        String placa = "ABC-123";
        String marca = "Nissan";
        String modelo = "Versa";
        int anio = 2020;

        System.out.println("--- INICIANDO REGISTRO EN RIASA ---");

        // 1. PRIMERO registramos al cliente
        boolean clienteRegistrado = dao.registrarCliente(rfcPrueba, nombre, tel, dir, email);

        if (clienteRegistrado) {
            System.out.println("✅ Cliente registrado: " + nombre);

            // 2. SOLO SI el cliente se registró, intentamos registrar su auto
            boolean autoRegistrado = dao.registrarAuto(placa, marca, modelo, anio, rfcPrueba);
            
            if (autoRegistrado) {
                System.out.println("✅ Auto registrado y vinculado al RFC: " + rfcPrueba);
            } else {
                System.out.println("❌ Error al registrar el auto.");
            }

        } else {
            System.out.println("❌ No se pudo registrar el cliente (quizás el RFC ya existe o el email está duplicado).");
            System.out.println("   Como falló el cliente, no intentamos registrar el auto.");
        }
        */

        
        // --- SIMULACIÓN DE CREAR COTIZACIÓN ---
        String rfcCliente = "XAXX010101000"; // Asegúrate que este RFC exista en tu BD
        String placaAuto = "ABC-123";       // Asegúrate que esta placa exista en tu BD
        
        // 2. Simulamos la lista de servicios que el usuario agregó en la tabla visual
        List<servicioItem> listaServicios = new ArrayList<>();
        listaServicios.add(new servicioItem("Cambio de Aceite Sintético", 1, 1200.50));
        listaServicios.add(new servicioItem("Filtro de Aire", 1, 350.00));
        listaServicios.add(new servicioItem("Balatas Delanteras", 2, 800.00)); // 2 juegos

        System.out.println("--- GENERANDO COTIZACIÓN ---");

        // 3. Guardamos en Base de Datos
        int folioGenerado = dao.crearCotizacion(rfcCliente, placaAuto, listaServicios);

        if (folioGenerado != -1) {
            System.out.println("✅ Guardado en BD con Folio: " + folioGenerado);

            // 4. Si se guardó bien, generamos el PDF
            // Nota: Aquí podrías hacer una consulta extra para traer el nombre real del cliente,
            // por ahora lo pondré manual para el ejemplo.
            pdf.crearDocumento(folioGenerado, "Maria Gonzales", "Nissan Versa", listaServicios);
            
        } else {
            System.out.println("❌ Error al guardar la cotización.");
        }
        

        // --- DATOS SIMULADOS (Como si vinieran de tu interfaz) ---
        // IMPORTANTE: Cambia este ID por una cotización que SI EXISTA en tu BD y que NO ESTÉ PAGADA aún.
        int idCotizacionAPagar = 2; 
        
        String nombreCliente = "Maria Gonzales"; // Esto normalmente lo sacarías con una consulta extra al DAO
        String metodoPago = "Efectivo";
        double montoTotal = 3150.50; // Debe coincidir con el total de la cotización

        System.out.println("--- PROCESANDO PAGO Y TICKET ---");

        // 2. Registrar el pago en la BD
        int idFacturaGenerada = dao.generarFactura(idCotizacionAPagar, metodoPago, montoTotal, rfcCliente);

        if (idFacturaGenerada != -1) {
            System.out.println("✅ Pago registrado en BD. ID Factura: " + idFacturaGenerada);

            // 3. Generar el TICKET (Usando el nuevo método que hicimos)
            pdf.crearTicketFactura(
                idFacturaGenerada,  // Folio del ticket
                idCotizacionAPagar, // Referencia
                nombreCliente,      // Nombre para mostrar
                montoTotal,         // Dinero
                metodoPago          // 'Efectivo', etc.
            );
            
            System.out.println("✅ Ticket generado y abierto correctamente.");

        } else {
            System.out.println("❌ Error: No se pudo cobrar.");
            System.out.println("   Posibles causas: La cotización no existe, ya fue pagada antes, o el monto es incorrecto.");
        }
    }
}