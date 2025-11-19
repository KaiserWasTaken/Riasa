package frontend;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import backend.RiasaDAO; 

public class Cotizacion extends JFrame {

    // Paleta de colores (La misma de todo el sistema)
    private Color colorPrimario = new Color(44, 62, 80);    // Azul Oscuro
    private Color colorFondo    = new Color(236, 240, 241); // Gris Claro
    private Color colorAcento   = new Color(52, 152, 219);  // Azul Brillante
    private Color colorTexto    = new Color(51, 51, 51);    // Gris Oscuro

    // Componentes
    private JTextField txtCobro, txtConcepto, txtRFC, txtPlaca;
    private JButton btGenerar, btPago, btAtras;

    public Cotizacion() {
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

        JLabel lblTitulo = new JLabel("GENERAR COTIZACION");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(0, 0, 500, 70);
        header.add(lblTitulo);

        // --- 2. FORMULARIO ---
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBounds(30, 90, 425, 400);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        mainPanel.add(formPanel);

        JLabel lblSubtitulo = new JLabel("Datos a ingresar");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSubtitulo.setForeground(colorAcento);
        lblSubtitulo.setBounds(30, 20, 200, 20);
        formPanel.add(lblSubtitulo);

        JSeparator sep = new JSeparator();
        sep.setForeground(colorAcento);
        sep.setBounds(30, 45, 365, 2);
        formPanel.add(sep);

        // --- CAMPOS DE TEXTO ---
        
        // Cobro
        crearEtiqueta(formPanel, "Cobro", 30, 60);
        txtCobro = crearCampoTexto(formPanel, 30, 85);
        limitarInput(txtPlaca, 50, true); // Mayúsculas

        // Concepto
        crearEtiqueta(formPanel, "Concepto", 30, 130);
        txtConcepto = crearCampoTexto(formPanel, 30, 155);
        limitarInput(txtConcepto, 250, true);

        // RFC
        crearEtiqueta(formPanel, "RFC del cliente", 30, 200);
        txtRFC = crearCampoTexto(formPanel, 30, 225);
        limitarInput(txtRFC, 50, true);

        // Año
        crearEtiqueta(formPanel, "Placa del vehiculo", 30, 270);
        txtPlaca = crearCampoTexto(formPanel, 30, 295);
        limitarInput(txtPlaca, 20, true);

        // Separación visual para el Dueño
        JSeparator sep2 = new JSeparator();
        sep2.setForeground(new Color(200,200,200));
        sep2.setBounds(30, 340, 365, 2);
        formPanel.add(sep2);



        // --- 3. BOTONES (Footer) ---
        
        // Botón GENERAR
        btGenerar = new JButton("Generar cotizacion");
        btGenerar.setBounds(50, 540, 180, 45);
        btGenerar.setBackground(colorPrimario);
        btGenerar.setForeground(Color.WHITE);
        btGenerar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btGenerar.setFocusPainted(false);
        btGenerar.setBorderPainted(false);
        btGenerar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btGenerar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) { btGenerar.setBackground(colorAcento); }
            public void mouseExited(MouseEvent evt) { btGenerar.setBackground(colorPrimario); }
        });
        mainPanel.add(btGenerar);

        // Botón PAGO
        btPago = new JButton("Pago");
        btPago.setBounds(250, 540, 100, 45);
        btPago.setBackground(Color.WHITE);
        btPago.setForeground(colorTexto);
        btPago.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btPago.setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));
        btPago.setFocusPainted(false);
        btPago.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainPanel.add(btPago);

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
        btGenerar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cobro = txtCobro.getText().trim();
                String concepto = txtConcepto.getText();
                String rfc = txtRFC.getText().trim();
                String placa = txtPlaca.getText();

                if(cobro.isEmpty() || concepto.isEmpty() || rfc.isEmpty() || placa.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                
                //MODIFICAR EN EL DAO
                /*
                RiasaDAO dao = new RiasaDAO();
                boolean exito = dao.registrarAuto(placa, marca, modelo, anio, rfcCliente);

                if(exito) {
                    JOptionPane.showMessageDialog(null, "✅ Proceso de pago en tramite.");
                    Pago();
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "❌ Error al registrar.\nVerifica que la Placa no exista y que el RFC del Cliente sea correcto.", 
                        "Error BD", JOptionPane.ERROR_MESSAGE);
                } */
            }
        });

        // LIMPIAR
        btPago.addActionListener(e -> Pago());

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

    private void Pago() {
        //Ingreasar metodo para pago
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

    
    
    public static void main(String[] args) {
        new Cotizacion().setVisible(true);
    }
}