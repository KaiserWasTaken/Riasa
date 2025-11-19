import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RiasaDAO {

    // --- MÉTODO 1: REGISTRAR CLIENTE ---
    public boolean registrarCliente(String rfc, String nombre, String telefono, String direccion, String email) {
        String sql = "INSERT INTO clientes (rfc, nombre, telefono, direccion, email) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            if (conn == null) return false;

            pstmt.setString(1, rfc);
            pstmt.setString(2, nombre);
            pstmt.setString(3, telefono);
            pstmt.setString(4, direccion);
            pstmt.setString(5, email);

            int filas = pstmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar cliente: " + e.getMessage());
            return false;
        }
    }

    // --- MÉTODO 2: REGISTRAR AUTO ---
    public boolean registrarAuto(String placa, String marca, String modelo, int anio, String clienteRfc) {
        String sql = "INSERT INTO autos (placa, marca, modelo, anio, cliente_rfc) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (conn == null) return false;

            pstmt.setString(1, placa);
            pstmt.setString(2, marca);
            pstmt.setString(3, modelo);
            // OJO AQUÍ: Como 'anio' es INT en la base de datos, usamos setInt
            pstmt.setInt(4, anio); 
            // Este RFC debe existir previamente en la tabla clientes
            pstmt.setString(5, clienteRfc); 

            int filas = pstmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar auto: " + e.getMessage());
            // Esto imprimirá un error específico si intentas poner un RFC que no existe
            return false;
        }
    }
}