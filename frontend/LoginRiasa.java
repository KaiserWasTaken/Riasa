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
    private Color colorPrimario = new Color(44, 62, 80);   // Azul Oscuro (Sidebar)
    private Color colorSecundario = new Color(236, 240, 241); // Gris muy claro (Fondo)
    private Color colorAcento = new Color(52, 152, 219);   // Azul Brillante (Botones/Bordes)
    private Color colorTexto = new Color(51, 51, 51);      // Gris oscuro (Texto)

    public LoginRiasa() {
        setTitle("Acceso al Sistema - RIASA");
        // Hacemos la ventana un poco más ancha para el diseño dividido
        setBounds(400, 150, 700, 450); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false); // Diseño fijo para evitar deformaciones
        initUI();
    }

    private void initUI() {
        // Panel Principal que contiene todo
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        this.getContentPane().add(mainPanel);

        // --- 1. PANEL IZQUIERDO (BRANDING) ---
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(colorPrimario);
        sidePanel.setBounds(0, 0, 300, 450);
        sidePanel.setLayout(null);
        mainPanel.add(sidePanel);

        // Logo / Texto Empresa
        JLabel lblLogo = new JLabel("RIASA");
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 40));
        lblLogo.setBounds(0, 130, 300, 50);
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        sidePanel.add(lblLogo);

        JLabel lblSlogan = new JLabel("Sistemas Automotrices");
        lblSlogan.setForeground(new Color(189, 195, 199)); // Gris plata
        lblSlogan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSlogan.setBounds(0, 180, 300, 20);
        lblSlogan.setHorizontalAlignment(SwingConstants.CENTER);
        sidePanel.add(lblSlogan);
        
        // Decoración (una línea simple)
        JSeparator separador = new JSeparator();
        separador.setForeground(new Color(255, 255, 255, 100)); // Blanco transparente
        separador.setBounds(50, 220, 200, 10);
        sidePanel.add(separador);

        // --- 2. PANEL DERECHO (FORMULARIO) ---
        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setBounds(300, 0, 400, 450);
        formPanel.setLayout(null);
        mainPanel.add(formPanel);

        // Título "Bienvenido"
        JLabel lblTitulo = new JLabel("INICIAR SESIÓN");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(colorPrimario);
        lblTitulo.setBounds(50, 50, 300, 30);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(lblTitulo);

        // -- CAMPO USUARIO --
        JLabel lblUser = new JLabel("USUARIO");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblUser.setForeground(colorTexto);
        lblUser.setBounds(50, 110, 300, 20);
        formPanel.add(lblUser);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(50, 135, 300, 30);
        styleTextField(txtUsuario); // Aplicamos estilo personalizado
        formPanel.add(txtUsuario);

        // -- CAMPO CONTRASEÑA --
        JLabel lblPass = new JLabel("CONTRASEÑA");
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblPass.setForeground(colorTexto);
        lblPass.setBounds(50, 190, 300, 20);
        formPanel.add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(50, 215, 300, 30);
        styleTextField(txtPassword); // Aplicamos estilo personalizado
        formPanel.add(txtPassword);

        // -- BOTÓN INGRESAR --
        btnIngresar = new JButton("INGRESAR");
        btnIngresar.setBounds(50, 280, 140, 40);
        btnIngresar.setBackground(colorPrimario);
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnIngresar.setFocusPainted(false);
        btnIngresar.setBorderPainted(false);
        btnIngresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Efecto Hover simple
        btnIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIngresar.setBackground(colorAcento);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIngresar.setBackground(colorPrimario);
            }
        });
        formPanel.add(btnIngresar);

        // -- BOTÓN CANCELAR (Estilo plano/texto) --
        btnCancelar = new JButton("Salir");
        btnCancelar.setBounds(210, 280, 140, 40);
        btnCancelar.setBackground(Color.WHITE);
        btnCancelar.setForeground(new Color(150, 150, 150)); // Gris suave
        btnCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorder(BorderFactory.createLineBorder(new Color(200,200,200))); // Borde sutil
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelar.setForeground(Color.RED);
                btnCancelar.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelar.setForeground(new Color(150, 150, 150));
                btnCancelar.setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));
            }
        });
        formPanel.add(btnCancelar);

        // --- LÓGICA DE LOS BOTONES (TU CÓDIGO ORIGINAL) ---
        
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = txtUsuario.getText();
                String pass = new String(txtPassword.getPassword());

                if(user.isEmpty() || pass.isEmpty()){
                    JOptionPane.showMessageDialog(LoginRiasa.this, "Por favor, complete todos los campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                RiasaDAO dao = new RiasaDAO();
                String rol = dao.login(user, pass);

                if (rol != null) {
                    JOptionPane.showMessageDialog(LoginRiasa.this, "Acceso concedido.\nBienvenido " + user + " (" + rol + ")");
                    
                    try {
                        HomeRiasa home = new HomeRiasa();
                        home.setVisible(true);
                        dispose(); 
                    } catch (Exception ex) {
                        System.out.println("Error al abrir HomeRiasa: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(LoginRiasa.this,
                            "Credenciales incorrectas.", "Error de Seguridad", JOptionPane.ERROR_MESSAGE);
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

    // --- MÉTODO PARA ESTILIZAR CAJAS DE TEXTO (Moderno) ---
    private void styleTextField(JTextField field) {
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, colorAcento), // Línea inferior azul
            BorderFactory.createEmptyBorder(5, 5, 5, 5))); // Padding interno
        field.setBackground(Color.WHITE);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(colorTexto);
    }
    
    public static void main(String[] args) {
        // Activar Antialiasing para fuentes suaves (Opcional pero recomendado)
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        
        LoginRiasa lr = new LoginRiasa();
        lr.setVisible(true);
    }
}