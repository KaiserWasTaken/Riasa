package backend;

import java.security.MessageDigest;

public class Seguridad {

    // Este método recibe "1234" y devuelve el código largo encriptado
    public static String encriptar(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            
            // Convertir los bytes a formato Hexadecimal (String legible)
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
            
        } catch (Exception e) {
            throw new RuntimeException("Error al encriptar: " + e);
        }
    }
}