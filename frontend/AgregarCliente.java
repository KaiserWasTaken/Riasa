package frontend;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import backend.RiasaDAO; // Asegúrate que coincida con tu paquete real (Backend o backend)

public class AgregarCliente extends JFrame {

    // Paleta de colores corporativa
    private Color colorPrimario = new Color(44, 62, 80);    // Azul Oscuro
    private Color colorFondo    = new Color(236, 240, 241); // Gris Claro
    private Color colorAcento   = new Color(52, 152, 219);  // Azul Brillante
    private Color colorTexto    = new Color(51, 51, 51);    // Gris Oscuro

    // Componentes
    private JTextField txtRFC, txtNOMBRE, txtTELEFONO, txtDIRECCION, txtEMAIL;
    private JButton btAceptar, btLimpiar, btAtras;

    public AgregarCliente() {
        setTitle("Registro de Nuevo Cliente");
        setBounds(500, 50, 500, 680); // Un poco más alto para que respire el diseño
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(colorFondo);
        this.getContentPane().add(mainPanel);

        // --- 1. HEADER (Encabezado) ---
        JPanel header = new JPanel();
        header.setBounds(0, 0, 500, 70);
        header.setBackground(colorPrimario);
        header.setLayout(null);
        mainPanel.add(header);

        JLabel lblTitulo = new JLabel("REGISTRO DE CLIENTE");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(0, 0, 500, 70);
        header.add(lblTitulo);

        // --- 2. FORMULARIO (Panel central blanco) ---
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBounds(30, 90, 425, 430);
        formPanel.setBackground(Color.WHITE);
        // Sombra sutil (Borde gris suave)
        formPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        mainPanel.add(formPanel);

        JLabel lblSubtitulo = new JLabel("Datos Generales");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSubtitulo.setForeground(colorAcento);
        lblSubtitulo.setBounds(30, 20, 200, 20);
        formPanel.add(lblSubtitulo);

        // Separador decorativo
        JSeparator sep = new JSeparator();
        sep.setForeground(colorAcento);
        sep.setBounds(30, 45, 365, 2);
        formPanel.add(sep);

        // --- CAMPOS DE TEXTO ---
        // Usamos un método auxiliar 'crearCampo' para no repetir código de diseño
        
        // RFC
        crearEtiqueta(formPanel, "RFC *", 30, 60);
        txtRFC = crearCampoTexto(formPanel, 30, 85);
        limitarInput(txtRFC, 13, true); // Restricción: 13 chars, Mayúsculas

        // Nombre
        crearEtiqueta(formPanel, "Nombre Completo *", 30, 130);
        txtNOMBRE = crearCampoTexto(formPanel, 30, 155);
        limitarInput(txtNOMBRE, 100, false);

        // Teléfono
        crearEtiqueta(formPanel, "Teléfono", 30, 200);
        txtTELEFONO = crearCampoTexto(formPanel, 30, 225);
        limitarInput(txtTELEFONO, 20, false);

        // Dirección
        crearEtiqueta(formPanel, "Dirección *", 30, 270);
        txtDIRECCION = crearCampoTexto(formPanel, 30, 295);
        limitarInput(txtDIRECCION, 255, false);

        // Email
        crearEtiqueta(formPanel, "Correo Electrónico", 30, 340);
        txtEMAIL = crearCampoTexto(formPanel, 30, 365);
        limitarInput(txtEMAIL, 100, false);


        // --- 3. BOTONES DE ACCIÓN (Footer) ---
        
        // Botón ACEPTAR (Verde/Azul Brillante)
        btAceptar = new JButton("GUARDAR");
        btAceptar.setBounds(50, 540, 180, 45);
        btAceptar.setBackground(colorPrimario);
        btAceptar.setForeground(Color.WHITE);
        btAceptar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btAceptar.setFocusPainted(false);
        btAceptar.setBorderPainted(false);
        btAceptar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Efecto Hover
        btAceptar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) { btAceptar.setBackground(colorAcento); }
            public void mouseExited(MouseEvent evt) { btAceptar.setBackground(colorPrimario); }
        });
        mainPanel.add(btAceptar);

        // Botón LIMPIAR (Gris)
        btLimpiar = new JButton("Limpiar");
        btLimpiar.setBounds(250, 540, 100, 45);
        btLimpiar.setBackground(Color.WHITE);
        btLimpiar.setForeground(colorTexto);
        btLimpiar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btLimpiar.setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));
        btLimpiar.setFocusPainted(false);
        btLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainPanel.add(btLimpiar);

        // Botón ATRÁS (Rojo sutil o icono)
        btAtras = new JButton("Salir");
        btAtras.setBounds(360, 540, 80, 45);
        btAtras.setBackground(new Color(231, 76, 60)); // Rojo
        btAtras.setForeground(Color.WHITE);
        btAtras.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btAtras.setBorderPainted(false);
        btAtras.setFocusPainted(false);
        btAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainPanel.add(btAtras);

        // Inicializar lógica de eventos
        eventos();
    }

    // --- MÉTODOS DE DISEÑO AUXILIARES ---
    
    private void crearEtiqueta(JPanel panel, String texto, int x, int y) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lbl.setForeground(colorTexto);
        lbl.setBounds(x, y, 300, 20);
        panel.add(lbl);
    }

    private JTextField crearCampoTexto(JPanel panel, int x, int y) {
        JTextField txt = new JTextField();
        txt.setBounds(x, y, 365, 30);
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txt.setForeground(colorTexto);
        txt.setBackground(Color.WHITE);
        // Borde inferior moderno
        txt.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(150,150,150)),
            BorderFactory.createEmptyBorder(2, 5, 2, 5)
        ));
        panel.add(txt);
        return txt;
    }

    // --- LÓGICA DE NEGOCIO (Igual que antes, solo adaptada) ---

    private void eventos() {
        // EVENTO GUARDAR
        btAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String rfc = txtRFC.getText().trim();
                String nombre = txtNOMBRE.getText();
                String telefono = txtTELEFONO.getText();
                String direccion = txtDIRECCION.getText();
                String email = txtEMAIL.getText();

                if(rfc.isEmpty() || nombre.isEmpty() || direccion.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Los campos marcados con (*) son obligatorios.", "Faltan Datos", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (rfc.length() < 12) {
                    JOptionPane.showMessageDialog(null, "El RFC debe tener al menos 12 caracteres.", "RFC Inválido", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                RiasaDAO dao = new RiasaDAO();
                boolean exito = dao.registrarCliente(rfc, nombre, telefono, direccion, email);

                if(exito) {
                    JOptionPane.showMessageDialog(null, "✅ Cliente registrado correctamente.");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(null, "❌ Error al registrar.\nPosiblemente el RFC o Email ya existen.", "Error BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // EVENTO LIMPIAR
        btLimpiar.addActionListener(e -> limpiarCampos());

        // EVENTO ATRÁS
        btAtras.addActionListener(e -> {
            try {
                HomeRiasa hr = new HomeRiasa();
                hr.setVisible(true);
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void limpiarCampos() {
        txtRFC.setText("");
        txtNOMBRE.setText("");
        txtTELEFONO.setText("");
        txtDIRECCION.setText("");
        txtEMAIL.setText("");
    }

    // Restricciones de teclado (Igual que tu código anterior)
    private void limitarInput(JTextField campo, int longitudMaxima, boolean soloMayusculas) {
        campo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (campo.getText().length() >= longitudMaxima) {
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
                if (soloMayusculas) {
                    if (Character.isLowerCase(c)) {
                        e.setKeyChar(Character.toUpperCase(c));
                    }
                }
            }
        });
    }
    
    // Main solo para probar vista
    public static void main(String[] args) {
        new AgregarCliente().setVisible(true);
    }
}