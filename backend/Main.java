package backend;
import frontend.LoginRiasa; // Ahora importamos LoginRiasa

public class Main {
    public static void main(String[] args) {
        // Arrancamos la aplicación con TU diseño de login
        LoginRiasa login = new LoginRiasa();
        login.setVisible(true);
    }
}