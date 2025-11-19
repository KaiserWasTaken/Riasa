public class Main {
    public static void main(String[] args) {
        RiasaDAO dao = new RiasaDAO();

        // --- DATOS DE PRUEBA ---
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
    }
}