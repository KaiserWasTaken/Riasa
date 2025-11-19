import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GestionBD {

    public static void main(String[] args) {
        
        // 1. Datos de conexión (Ajusta esto a tu configuración)
String url = "jdbc:postgresql://localhost:5432/Riasa?currentSchema=riasa";
        String usuario = "postgres"; // Por defecto suele ser 'postgres'
        String contrasenia = "admin";

        // 2. La consulta SQL (Usamos ? como marcadores de posición)
        String sql = "INSERT INTO usuarios (nombre, email) VALUES (?, ?)";

        // 3. Bloque try-with-resources para asegurar que la conexión se cierre
        try (Connection conn = DriverManager.getConnection(url, usuario, contrasenia);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("¡Conexión exitosa!");

            // 4. Asignar valores a los ? del SQL
            pstmt.setString(1, "Juan Perez");       // Primer ?
            pstmt.setString(2, "juan@ejemplo.com"); // Segundo ?

            // 5. Ejecutar la inserción
            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Se agregó un nuevo usuario correctamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar o insertar datos:");
            e.printStackTrace();
        }
    }
}