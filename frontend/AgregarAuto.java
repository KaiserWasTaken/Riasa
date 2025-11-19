package frontend;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import backend.RiasaDAO; 

public class AgregarAuto extends JFrame {

    // Paleta de colores (La misma de todo el sistema)
    private Color colorPrimario = new Color(44, 62, 80);    // Azul Oscuro
    private Color colorFondo    = new Color(236, 240, 241); // Gris Claro
    private Color colorAcento   = new Color(52, 152, 219);  // Azul Brillante
    private Color colorTexto    = new Color(51, 51, 51);    // Gris Oscuro

    // Componentes
    private JTextField txtPlaca, txtMarca, txtModelo, txtAnio, txtRFCCliente;
    private JButton btGuardar, btLimpiar, btAtras;

    public AgregarAuto() {
        setTitle("Registro de Vehículo");
        setBounds(500, 50, 500, 680);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(colorFondo);
        this.getContentPane().add(mainPanel);

        // --- 1. HEADER ---
        JPanel header = new JPanel();
        header.setBounds(0, 0, 500, 70);
        header.setBackground(colorPrimario);
        header.setLayout(null);
        mainPanel.add(header);

        JLabel lblTitulo = new JLabel("REGISTRO DE VEHÍCULO");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(0, 0, 500, 70);
        header.add(lblTitulo);

        // --- 2. FORMULARIO ---
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBounds(30, 90, 425, 430);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        mainPanel.add(formPanel);

        JLabel lblSubtitulo = new JLabel("Datos del Automóvil");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSubtitulo.setForeground(colorAcento);
        lblSubtitulo.setBounds(30, 20, 200, 20);
        formPanel.add(lblSubtitulo);

        JSeparator sep = new JSeparator();
        sep.setForeground(colorAcento);
        sep.setBounds(30, 45, 365, 2);
        formPanel.add(sep);

        // --- CAMPOS DE TEXTO ---
        
        // Placa
        crearEtiqueta(formPanel, "Placa *", 30, 60);
        txtPlaca = crearCampoTexto(formPanel, 30, 85);
        limitarInput(txtPlaca, 10, true); // Mayúsculas

        // Marca
        crearEtiqueta(formPanel, "Marca", 30, 130);
        txtMarca = crearCampoTexto(formPanel, 30, 155);
        limitarInput(txtMarca, 50, true);

        // Modelo
        crearEtiqueta(formPanel, "Modelo", 30, 200);
        txtModelo = crearCampoTexto(formPanel, 30, 225);
        limitarInput(txtModelo, 50, true);

        // Año
        crearEtiqueta(formPanel, "Año (Ej. 2020) *", 30, 270);
        txtAnio = crearCampoTexto(formPanel, 30, 295);
        limitarSoloNumeros(txtAnio, 4);

        // Separación visual para el Dueño
        JSeparator sep2 = new JSeparator();
        sep2.setForeground(new Color(200,200,200));
        sep2.setBounds(30, 340, 365, 2);
        formPanel.add(sep2);

        // RFC Cliente
        crearEtiqueta(formPanel, "Asignar al Cliente (RFC) *", 30, 355);
        txtRFCCliente = crearCampoTexto(formPanel, 30, 380);
        limitarInput(txtRFCCliente, 13, true); // Mayúsculas


        // --- 3. BOTONES (Footer) ---
        
        // Botón GUARDAR
        btGuardar = new JButton("GUARDAR");
        btGuardar.setBounds(50, 540, 180, 45);
        btGuardar.setBackground(colorPrimario);
        btGuardar.setForeground(Color.WHITE);
        btGuardar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btGuardar.setFocusPainted(false);
        btGuardar.setBorderPainted(false);
        btGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btGuardar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) { btGuardar.setBackground(colorAcento); }
            public void mouseExited(MouseEvent evt) { btGuardar.setBackground(colorPrimario); }
        });
        mainPanel.add(btGuardar);

        // Botón LIMPIAR
        btLimpiar = new JButton("Limpiar");
        btLimpiar.setBounds(250, 540, 100, 45);
        btLimpiar.setBackground(Color.WHITE);
        btLimpiar.setForeground(colorTexto);
        btLimpiar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btLimpiar.setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));
        btLimpiar.setFocusPainted(false);
        btLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainPanel.add(btLimpiar);

        // Botón SALIR
        btAtras = new JButton("Salir");
        btAtras.setBounds(360, 540, 80, 45);
        btAtras.setBackground(new Color(231, 76, 60)); // Rojo
        btAtras.setForeground(Color.WHITE);
        btAtras.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btAtras.setBorderPainted(false);
        btAtras.setFocusPainted(false);
        btAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainPanel.add(btAtras);

        // Iniciar eventos
        eventos();
    }

    // --- MÉTODOS DE DISEÑO (Reutilizados) ---
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
        txt.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(150,150,150)),
            BorderFactory.createEmptyBorder(2, 5, 2, 5)
        ));
        panel.add(txt);
        return txt;
    }

    // --- LÓGICA ---

    private void eventos() {
        // GUARDAR
        btGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String placa = txtPlaca.getText().trim();
                String marca = txtMarca.getText();
                String modelo = txtModelo.getText();
                String anioStr = txtAnio.getText();
                String rfcCliente = txtRFCCliente.getText().trim();

                if(placa.isEmpty() || marca.isEmpty() || modelo.isEmpty() || anioStr.isEmpty() || rfcCliente.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int anio = 0;
                try {
                    anio = Integer.parseInt(anioStr);
                    if (anio <= 1900 || anio > 2026) {
                        JOptionPane.showMessageDialog(null, "El año debe ser coherente (1900-2026).", "Año Inválido", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "El año debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                RiasaDAO dao = new RiasaDAO();
                boolean exito = dao.registrarAuto(placa, marca, modelo, anio, rfcCliente);

                if(exito) {
                    JOptionPane.showMessageDialog(null, "✅ Vehículo registrado correctamente.");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "❌ Error al registrar.\nVerifica que la Placa no exista y que el RFC del Cliente sea correcto.", 
                        "Error BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // LIMPIAR
        btLimpiar.addActionListener(e -> limpiarCampos());

        // ATRÁS
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
        txtPlaca.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtAnio.setText("");
        txtRFCCliente.setText("");
    }

    // Validaciones de teclado
    private void limitarInput(JTextField campo, int longitudMaxima, boolean soloMayusculas) {
        campo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (campo.getText().length() >= longitudMaxima) {
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
                if (soloMayusculas && Character.isLowerCase(c)) {
                    e.setKeyChar(Character.toUpperCase(c));
                }
            }
        });
    }

    private void limitarSoloNumeros(JTextField campo, int longitudMaxima) {
        campo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                    return;
                }
                if (campo.getText().length() >= longitudMaxima) {
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
    }
    
    public static void main(String[] args) {
        new AgregarAuto().setVisible(true);
    }
}