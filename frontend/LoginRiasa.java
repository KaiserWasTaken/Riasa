package frontend;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import backend.RiasaDAO; 

public class LoginRiasa extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIngresar, btnCancelar;
    
    // Colores de diseño
    private Color colorPrimario = new Color(44, 62, 80);   // Azul Oscuro
    private Color colorSecundario = new Color(236, 240, 241); // Gris
    private Color colorAcento = new Color(52, 152, 219);   // Azul Brillante
    private Color colorTexto = new Color(51, 51, 51);      // Gris oscuro

    public LoginRiasa() {
        setTitle("Acceso al Sistema - RIASA");
        setBounds(400, 150, 700, 450); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        this.getContentPane().add(mainPanel);

        // --- 1. PANEL IZQUIERDO ---
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(colorPrimario);
        sidePanel.setBounds(0, 0, 300, 450);
        sidePanel.setLayout(null);
        mainPanel.add(sidePanel);

        JLabel lblLogo = new JLabel("RIASA");
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 40));
        lblLogo.setBounds(0, 130, 300, 50);
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        sidePanel.add(lblLogo);

        JLabel lblSlogan = new JLabel("Sistemas Automotrices");
        lblSlogan.setForeground(new Color(189, 195, 199));
        lblSlogan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSlogan.setBounds(0, 180, 300, 20);
        lblSlogan.setHorizontalAlignment(SwingConstants.CENTER);
        sidePanel.add(lblSlogan);
        
        JSeparator separador = new JSeparator();
        separador.setForeground(new Color(255, 255, 255, 100));
        separador.setBounds(50, 220, 200, 10);
        sidePanel.add(separador);

        // --- 2. PANEL DERECHO ---
        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setBounds(300, 0, 400, 450);
        formPanel.setLayout(null);
        mainPanel.add(formPanel);

        JLabel lblTitulo = new JLabel("INICIAR SESIÓN");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(colorPrimario);
        lblTitulo.setBounds(50, 50, 300, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(lblTitulo);

        JLabel lblUser = new JLabel("USUARIO");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblUser.setForeground(colorTexto);
        lblUser.setBounds(50, 110, 300, 20);
        formPanel.add(lblUser);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(50, 135, 300, 30);
        styleTextField(txtUsuario);
        formPanel.add(txtUsuario);

        JLabel lblPass = new JLabel("CONTRASEÑA");
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblPass.setForeground(colorTexto);
        lblPass.setBounds(50, 190, 300, 20);
        formPanel.add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(50, 215, 300, 30);
        styleTextField(txtPassword);
        formPanel.add(txtPassword);

        // BOTÓN INGRESAR
        btnIngresar = new JButton("INGRESAR");
        btnIngresar.setBounds(50, 280, 140, 40);
        btnIngresar.setBackground(colorPrimario);
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnIngresar.setFocusPainted(false);
        btnIngresar.setBorderPainted(false);
        btnIngresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnIngresar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) { btnIngresar.setBackground(colorAcento); }
            public void mouseExited(MouseEvent evt) { btnIngresar.setBackground(colorPrimario); }
        });
        
        // --- AQUÍ ESTÁ LA LÓGICA MODIFICADA ---
        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = txtUsuario.getText().trim();
                String pass = new String(txtPassword.getPassword());

                if(user.isEmpty() || pass.isEmpty()){
                    JOptionPane.showMessageDialog(LoginRiasa.this, "Complete todos los campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                RiasaDAO dao = new RiasaDAO();
                // Usamos el nuevo método "loginInteligente"
                String respuesta = dao.loginInteligente(user, pass);

                // Analizamos la respuesta
                if (respuesta.startsWith("BLOQUEADO")) {
                    // Extraemos los minutos del mensaje
                    String minutos = respuesta.split(":")[1];
                    JOptionPane.showMessageDialog(LoginRiasa.this, 
                        " CUENTA BLOQUEADA TEMPORALMENTE \n\n" +
                        "Ha excedido el número de intentos fallidos.\n" +
                        "Intente de nuevo en: " + minutos + " minutos.", 
                        "Seguridad", JOptionPane.ERROR_MESSAGE);

                } else if (respuesta.equals("ERROR_PASS")) {
                    JOptionPane.showMessageDialog(LoginRiasa.this, 
                        " Contraseña incorrecta.\nCuidado: Al tercer fallo se bloqueará la cuenta.", 
                        "Error de Credenciales", JOptionPane.WARNING_MESSAGE);
                        
                } else if (respuesta.equals("NO_EXISTE")) {
                    JOptionPane.showMessageDialog(LoginRiasa.this, 
                        "El usuario no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                        
                } else if (respuesta.equals("ERROR_DB")) {
                    JOptionPane.showMessageDialog(LoginRiasa.this, 
                        "Error de conexión con la base de datos.", "Error Crítico", JOptionPane.ERROR_MESSAGE);

                } else {
                    // Si no es ninguno de los errores anteriores, es que ES EL ROL (Login Exitoso)
                    JOptionPane.showMessageDialog(LoginRiasa.this, "✅ Bienvenido " + user + " (" + respuesta + ")");
                    try {
                        HomeRiasa home = new HomeRiasa();
                        home.setVisible(true);
                        dispose(); 
                    } catch (Exception ex) { ex.printStackTrace(); }
                }
            }
        });
        formPanel.add(btnIngresar);

        // BOTÓN SALIR
        btnCancelar = new JButton("Salir");
        btnCancelar.setBounds(210, 280, 140, 40);
        btnCancelar.setBackground(Color.WHITE);
        btnCancelar.setForeground(new Color(150, 150, 150));
        btnCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btnCancelar.setForeground(Color.RED);
                btnCancelar.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
            public void mouseExited(MouseEvent evt) {
                btnCancelar.setForeground(new Color(150, 150, 150));
                btnCancelar.setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));
            }
        });
        btnCancelar.addActionListener(e -> System.exit(0));
        formPanel.add(btnCancelar);
    }

    private void styleTextField(JTextField field) {
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, colorAcento),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        field.setBackground(Color.WHITE);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(colorTexto);
    }
    
    public static void main(String[] args) {
        new LoginRiasa().setVisible(true);
    }
}