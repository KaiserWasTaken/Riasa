package backend;

import java.sql.*;
import java.util.List;

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

    public int crearCotizacion(String rfc, String placa, List<servicioItem> servicios) {
        String sqlCabecera = "INSERT INTO cotizaciones (cliente_rfc, auto_placa, total) VALUES (?, ?, ?)";
        String sqlDetalle = "INSERT INTO detalles_cotizacion (cotizacion_id, descripcion_servicio, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        
        Connection conn = null;
        int idCotizacionGenerado = -1;

        try {
            conn = Conexion.getConexion();
            // 1. IMPORTANTE: Apagamos el guardado automático para manejar la transacción manualmente
            conn.setAutoCommit(false);

            // --- PASO A: Calcular el total general ---
            double totalGeneral = 0;
            for (servicioItem item : servicios) {
                totalGeneral += item.getSubtotal();
            }

            // --- PASO B: Insertar la Cabecera ---
            // RETURN_GENERATED_KEYS es vital para obtener el ID (folio) que Postgres creó automáticamente
            PreparedStatement psCabecera = conn.prepareStatement(sqlCabecera, Statement.RETURN_GENERATED_KEYS);
            psCabecera.setString(1, rfc);
            psCabecera.setString(2, placa);
            psCabecera.setDouble(3, totalGeneral);
            psCabecera.executeUpdate();

            // Obtener el ID generado
            ResultSet rsKeys = psCabecera.getGeneratedKeys();
            if (rsKeys.next()) {
                idCotizacionGenerado = rsKeys.getInt(1);
            } else {
                throw new SQLException("No se pudo obtener el ID de la cotización.");
            }

            // --- PASO C: Insertar los Detalles ---
            PreparedStatement psDetalle = conn.prepareStatement(sqlDetalle);
            for (servicioItem item : servicios) {
                psDetalle.setInt(1, idCotizacionGenerado); // Usamos el ID que acabamos de obtener
                psDetalle.setString(2, item.descripcion);
                psDetalle.setInt(3, item.cantidad);
                psDetalle.setDouble(4, item.precio);
                psDetalle.setDouble(5, item.getSubtotal());
                psDetalle.addBatch(); // Agregamos al "paquete"
            }
            psDetalle.executeBatch(); // Ejecutamos todo el paquete junto

            // --- PASO D: Confirmar todo (Commit) ---
            conn.commit();
            return idCotizacionGenerado; // Devolvemos el folio para el PDF

        } catch (SQLException e) {
            System.out.println("Error en transacción cotización: " + e.getMessage());
            try {
                if (conn != null) conn.rollback(); // Si algo falló, deshacemos todo
            } catch (SQLException ex) { ex.printStackTrace(); }
            return -1;
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true); // Restauramos el estado normal
                    conn.close();
                }
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    // --- MÉTODO 4: PAGAR Y GENERAR FACTURA ---
    public int generarFactura(int idCotizacion, String metodoPago, double montoEntregado, String rfcCliente) {
        Connection conn = null;
        try {
            conn = Conexion.getConexion();
            
            // 1. VALIDACIÓN: Verificar cuánto costaba la cotización original
            String sqlVerificar = "SELECT total FROM cotizaciones WHERE id = ?";
            PreparedStatement psCheck = conn.prepareStatement(sqlVerificar);
            psCheck.setInt(1, idCotizacion);
            ResultSet rs = psCheck.executeQuery();

            if (rs.next()) {
                double totalReal = rs.getDouble("total");
                
                // Regla de Negocio: No permitir pago si el monto es menor
                if (montoEntregado < totalReal) {
                    System.out.println("Error: El pago es insuficiente. Total a pagar: " + totalReal);
                    return -1;
                }
            } else {
                System.out.println("Error: La cotización no existe.");
                return -1;
            }

            // 2. INSERTAR LA FACTURA
            String sqlInsert = "INSERT INTO facturas (cotizacion_id, metodo_pago, monto_pagado, rfc_receptor) VALUES (?, ?, ?, ?)";
            PreparedStatement psInsert = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            psInsert.setInt(1, idCotizacion);
            psInsert.setString(2, metodoPago);
            psInsert.setDouble(3, montoEntregado);
            psInsert.setString(4, rfcCliente);
            
            psInsert.executeUpdate();

            ResultSet rsKeys = psInsert.getGeneratedKeys();
            if (rsKeys.next()) {
                return rsKeys.getInt(1); // Retornamos el ID de la factura nueva
            }

        } catch (SQLException e) {
            // Si falla (ej. intentas pagar una cotización ya pagada), caerá aquí por la restricción UNIQUE
            System.out.println("Error al facturar: " + e.getMessage());
        }
        return -1;
    }

    // --- MÉTODO 5: LOGIN SEGURO ---
    public String login(String usuario, String contraPlana) {
        String rolEncontrado = null;
        String sql = "SELECT rol FROM usuarios_sistema WHERE username = ? AND password = ?";
        
        try (Connection conn = Conexion.getConexion();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // 1. Encriptamos la contraseña que escribió el usuario
            String contraEncriptada = Seguridad.encriptar(contraPlana);

            pstmt.setString(1, usuario);
            pstmt.setString(2, contraEncriptada); // <--- Enviamos el HASH, no el texto plano
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                rolEncontrado = rs.getString("rol");
            }
            
        } catch (SQLException e) {
            System.out.println("Error en login: " + e.getMessage());
        }
        
        return rolEncontrado;
    }
}