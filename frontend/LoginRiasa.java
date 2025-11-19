package frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginRiasa extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIngresar, btnCancelar;

    public LoginRiasa() {
        setTitle("Login - RIASA");
        setBounds(500, 200, 400, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel();
        setVisible(true);
    }

    private void panel() {
        JPanel panel = new JPanel();
        this.getContentPane().add(panel);

        // Fondo de un solo color (puedes cambiarlo aquí)
        panel.setBackground(new Color(230, 230, 230)); // Gris claro
        panel.setLayout(null);
        panel.setOpaque(true);

        // Título
        panel.add(ventana(90, 10, 220, 40, "RIASA", 28));

        // Etiqueta Usuario
        panel.add(ventana(50, 80, 120, 25, "Usuario:", 14));

        txtUsuario = new JTextField();
        txtUsuario.setBounds(170, 80, 150, 25);
        panel.add(txtUsuario);

        // Etiqueta Contraseña
        panel.add(ventana(50, 130, 120, 25, "Contraseña:", 14));

        txtPassword = new JPasswordField();
        txtPassword.setBounds(170, 130, 150, 25);
        panel.add(txtPassword);

        // Botón INGRESAR
        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(70, 200, 110, 30);
        panel.add(btnIngresar);

        // Botón CANCELAR
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(210, 200, 110, 30);
        panel.add(btnCancelar);

        // ---- LISTENERS (sin lambdas) ----
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = txtUsuario.getText();
                String pass = new String(txtPassword.getPassword());

                if (user.equals("admin") && pass.equals("1234")) {
                    JOptionPane.showMessageDialog(LoginRiasa.this, "Acceso permitido");
                } else {
                    JOptionPane.showMessageDialog(LoginRiasa.this,
                            "Usuario o contraseña incorrectos",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    // Método ventana: genera etiquetas al estilo de tu formato
    public JLabel ventana(int x, int y, int w, int h, String txt, int tm) {
        JLabel etiqueta = new JLabel();
        etiqueta.setBounds(x, y, w, h);
        etiqueta.setText(txt);
        etiqueta.setHorizontalAlignment(SwingConstants.CENTER);

        etiqueta.setOpaque(true);
        etiqueta.setBackground(Color.white);
        etiqueta.setFont(new Font("Arial", Font.BOLD, tm));

        return etiqueta;
    }

    public static void main(String[] args) {
        new LoginRiasa();
    }
}

