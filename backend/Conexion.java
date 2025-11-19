package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // Tus datos configurados:
    // Nota el '?currentSchema=riasa' al final para que apunte a tu esquema correcto
    private static final String URL = "jdbc:postgresql://localhost:5432/Riasa?currentSchema=riasa";
    private static final String USER = "postgres";
    private static final String PASS = "admin";

    public static Connection getConexion() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Error crítico de conexión: " + e.getMessage());
            return null;
        }
    }
}