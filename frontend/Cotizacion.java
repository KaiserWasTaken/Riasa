package frontend;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import backend.RiasaDAO; 
import backend.servicioItem; // Asegúrate de que coincida con tu clase (Mayúscula/Minúscula)
import backend.servicioItem;

public class Cotizacion extends JFrame {

    private Color colorPrimario = new Color(44, 62, 80);    // Azul Oscuro
    private Color colorFondo    = new Color(236, 240, 241); // Gris Claro
    private Color colorAcento   = new Color(52, 152, 219);  // Azul Brillante
    private Color colorTexto    = new Color(51, 51, 51);    // Gris Oscuro
    private Color colorVerde    = new Color(39, 174, 96);   // Verde Éxito
    private Color colorNaranja  = new Color(230, 126, 34);  // Naranja Ticket

    // Componentes
    private JTextField txtCobro, txtConcepto, txtCantidad, txtRFC, txtPlaca;
    private JButton btAgregar, btGenerarPDF, btTicket, btLimpiar, btAtras;
    
    // Componentes de la tabla
    private JTable tablaDetalles;
    private DefaultTableModel modeloTabla;
    private JLabel lblTotal;
    
    // Lógica
    private List<servicioItem> listaServicios;
    private double totalAcumulado = 0.0;

    public Cotizacion() {
        setTitle("Nueva Venta / Cotización");
        setBounds(500, 20, 600, 750); // Un poco más ancho para los 3 botones
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        listaServicios = new ArrayList<>();
        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(colorFondo);
        this.getContentPane().add(mainPanel);

        // --- 1. HEADER ---
        JPanel header = new JPanel();
        header.setBounds(0, 0, 600, 70);
        header.setBackground(colorPrimario);
        header.setLayout(null);
        mainPanel.add(header);

        JLabel lblTitulo = new JLabel("COTIZACIÓN Y VENTA");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(0, 0, 600, 70);
        header.add(lblTitulo);

        // --- 2. FORMULARIO ---
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBounds(25, 90, 535, 520);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        mainPanel.add(formPanel);

        // SECCIÓN A: DATOS DEL CLIENTE
        JLabel lblCliente = new JLabel("1. Datos del Cliente");
        lblCliente.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblCliente.setForeground(colorPrimario);
        lblCliente.setBounds(20, 15, 300, 20);
        formPanel.add(lblCliente);

        crearEtiqueta(formPanel, "RFC Cliente *", 20, 45);
        txtRFC = crearCampoTexto(formPanel, 20, 65, 230);
        limitarInput(txtRFC, 13, true);

        crearEtiqueta(formPanel, "Placa Auto *", 270, 45);
        txtPlaca = crearCampoTexto(formPanel, 270, 65, 230);
        limitarInput(txtPlaca, 10, true);

        JSeparator sep1 = new JSeparator();
        sep1.setForeground(Color.LIGHT_GRAY);
        sep1.setBounds(20, 110, 495, 2);
        formPanel.add(sep1);

        // SECCIÓN B: AGREGAR CONCEPTOS (Refacciones / Servicios)
        JLabel lblConceptos = new JLabel("2. Agregar Refacciones o Servicios");
        lblConceptos.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblConceptos.setForeground(colorPrimario);
        lblConceptos.setBounds(20, 125, 300, 20);
        formPanel.add(lblConceptos);

        // Fila de campos: Descripción (Grande) - Cantidad (Chico) - Precio (Mediano)
        
        // 1. Descripción
        crearEtiqueta(formPanel, "Descripción", 20, 155);
        txtConcepto = crearCampoTexto(formPanel, 20, 175, 240); // Ancho 240
        limitarInput(txtConcepto, 250, false);

        // 2. Cantidad (NUEVO)
        crearEtiqueta(formPanel, "Cant.", 270, 155);
        txtCantidad = crearCampoTexto(formPanel, 270, 175, 60); // Ancho 60
        limitarSoloNumeros(txtCantidad, 4);
        txtCantidad.setHorizontalAlignment(JTextField.CENTER); // Centrar número

        // 3. Precio Unitario
        crearEtiqueta(formPanel, "P. Unitario ($)", 340, 155);
        txtCobro = crearCampoTexto(formPanel, 340, 175, 100); // Ancho 100
        limitarSoloNumeros(txtCobro, 10);

        // Botón (+)
        btAgregar = new JButton("Añadir");
        btAgregar.setBounds(450, 175, 70, 30);
        btAgregar.setBackground(colorAcento);
        btAgregar.setForeground(Color.WHITE);
        btAgregar.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btAgregar.setFocusPainted(false);
        btAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        formPanel.add(btAgregar);

        // SECCIÓN C: TABLA
        // Columnas actualizadas
        String[] columnas = {"Cant.", "Descripción", "P. Unit.", "Importe"};
        modeloTabla = new DefaultTableModel(null, columnas);
        tablaDetalles = new JTable(modeloTabla);
        
        // Ajustar anchos de columna visualmente
        tablaDetalles.getColumnModel().getColumn(0).setPreferredWidth(40);  // Cant
        tablaDetalles.getColumnModel().getColumn(1).setPreferredWidth(250); // Desc
        tablaDetalles.getColumnModel().getColumn(2).setPreferredWidth(80);  // Precio
        tablaDetalles.getColumnModel().getColumn(3).setPreferredWidth(80);  // Importe

        JScrollPane scrollTabla = new JScrollPane(tablaDetalles);
        scrollTabla.setBounds(20, 230, 495, 180);
        formPanel.add(scrollTabla);

        // TOTAL
        lblTotal = new JLabel("Total: $0.00");
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTotal.setForeground(colorPrimario);
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotal.setBounds(295, 420, 220, 40);
        formPanel.add(lblTotal);

        // --- 3. BOTONES INFERIORES ---
        
        // Botón 1: Solo Cotizar (PDF Carta)
        btGenerarPDF = new JButton("COTIZAR (PDF)");
        btGenerarPDF.setBounds(30, 630, 150, 45);
        btGenerarPDF.setBackground(colorPrimario);
        btGenerarPDF.setForeground(Color.WHITE);
        btGenerarPDF.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btGenerarPDF.setFocusPainted(false);
        btGenerarPDF.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainPanel.add(btGenerarPDF);

        // Botón 2: Vender y Ticket (NUEVO)
        btTicket = new JButton("TICKET (PAGAR)");
        btTicket.setBounds(190, 630, 150, 45);
        btTicket.setBackground(colorNaranja); // Color distintivo
        btTicket.setForeground(Color.WHITE);
        btTicket.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btTicket.setFocusPainted(false);
        btTicket.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainPanel.add(btTicket);

        // Botón Limpiar
        btLimpiar = new JButton("Limpiar");
        btLimpiar.setBounds(350, 630, 100, 45);
        btLimpiar.setBackground(Color.WHITE);
        btLimpiar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        btLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainPanel.add(btLimpiar);

        // Botón Salir
        btAtras = new JButton("Salir");
        btAtras.setBounds(460, 630, 100, 45);
        btAtras.setBackground(new Color(231, 76, 60));
        btAtras.setForeground(Color.WHITE);
        btAtras.setBorderPainted(false);
        btAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainPanel.add(btAtras);

        eventos();
    }

    // --- MÉTODOS DE DISEÑO ---
    private void crearEtiqueta(JPanel panel, String texto, int x, int y) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lbl.setForeground(colorTexto);
        lbl.setBounds(x, y, 200, 20);
        panel.add(lbl);
    }

    private JTextField crearCampoTexto(JPanel panel, int x, int y, int ancho) {
        JTextField txt = new JTextField();
        txt.setBounds(x, y, ancho, 30);
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

    // --- LÓGICA PRINCIPAL ---

    private void eventos() {
        
        // 1. AGREGAR ÍTEM
        btAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String concepto = txtConcepto.getText().trim();
                String precioStr = txtCobro.getText().trim();
                String cantStr = txtCantidad.getText().trim();

                if(concepto.isEmpty() || precioStr.isEmpty() || cantStr.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Llene descripción, cantidad y precio.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                double precio = 0;
                int cantidad = 0;
                try {
                    precio = Double.parseDouble(precioStr);
                    cantidad = Integer.parseInt(cantStr);
                    if(cantidad <= 0) throw new Exception();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Valores numéricos inválidos.");
                    return;
                }

                // Calculamos subtotal
                double subtotal = precio * cantidad;

                // A. Agregar a la lista lógica
                servicioItem item = new servicioItem(concepto, cantidad, precio);
                listaServicios.add(item);

                // B. Agregar a la tabla visual
                modeloTabla.addRow(new Object[]{cantidad, concepto, "$" + precio, "$" + subtotal});

                // C. Actualizar Total
                totalAcumulado += subtotal;
                lblTotal.setText("Total: $" + totalAcumulado);

                // D. Resetear campos
                txtConcepto.setText("");
                txtCantidad.setText(""); // Limpia cantidad
                txtCobro.setText("");
                txtConcepto.requestFocus(); 
            }
        });

        // 2. GENERAR COTIZACIÓN (SOLO PDF, SIN PAGAR)
        btGenerarPDF.addActionListener(e -> procesarVenta(false));

        // 3. GENERAR TICKET (GUARDAR, PAGAR E IMPRIMIR TICKET)
        btTicket.addActionListener(e -> procesarVenta(true));

        // LIMPIAR
        btLimpiar.addActionListener(e -> limpiarTodo());

        // SALIR
        btAtras.addActionListener(e -> {
            try {
                HomeRiasa hr = new HomeRiasa();
                hr.setVisible(true);
                dispose();
            } catch (Exception ex) { ex.printStackTrace(); }
        });
    }

    // --- MÉTODO CENTRAL PARA PROCESAR ---
    // Si esTicket = true, registra el pago y saca ticket térmico.
    // Si esTicket = false, solo guarda cotización y saca PDF carta.
    private void procesarVenta(boolean esTicket) {
        String rfc = txtRFC.getText().trim();
        String placa = txtPlaca.getText().trim();

        if(rfc.isEmpty() || placa.isEmpty() || listaServicios.isEmpty()){
            JOptionPane.showMessageDialog(null, "Faltan datos o lista vacía.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        RiasaDAO dao = new RiasaDAO();
        
        // --- NUEVO: BUSCAMOS EL NOMBRE REAL EN LA BD ---
        String nombreReal = dao.obtenerNombreCliente(rfc); 
        // Si el rfc no existe, devolverá "Cliente General", pero idealmente ya validaste que exista.

        backend.GeneradorPDF pdf = new backend.GeneradorPDF();

        // 1. Guardar Cotización
        int folioCotizacion = dao.crearCotizacion(rfc, placa, listaServicios);

        if(folioCotizacion != -1) {
            
            if (esTicket) {
                // TICKET
                String[] opciones = {"Efectivo", "Tarjeta", "Transferencia"};
                String metodo = (String) JOptionPane.showInputDialog(null, "Método de Pago:", "Caja", 
                        JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                
                if(metodo == null) return;

                int folioFactura = dao.generarFactura(folioCotizacion, metodo, totalAcumulado, rfc);

                if (folioFactura != -1) {
                    // Enviamos 7 cosas: ..., nombreReal, rfc, ...
                    pdf.crearTicketFactura(folioFactura, folioCotizacion, nombreReal, rfc, totalAcumulado, metodo, listaServicios);
                    JOptionPane.showMessageDialog(null, "✅ Venta Registrada a nombre de: " + nombreReal);
                    limpiarTodo();
                } 

            } else {
                // COTIZACIÓN (PDF CARTA)
                // Pasamos Nombre Real y Placa
                pdf.crearDocumento(folioCotizacion, nombreReal + " (" + rfc + ")", placa, listaServicios);
                
                JOptionPane.showMessageDialog(null, "✅ Cotización generada para: " + nombreReal);
            }

        } else {
            JOptionPane.showMessageDialog(null, "❌ Error. Verifica que el RFC exista en la BD.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarTodo() {
        txtRFC.setText(""); txtPlaca.setText(""); 
        txtConcepto.setText(""); txtCantidad.setText(""); txtCobro.setText("");
        listaServicios.clear();
        modeloTabla.setRowCount(0);
        totalAcumulado = 0.0;
        lblTotal.setText("Total: $0.00");
    }

    private void limitarInput(JTextField campo, int max, boolean mayus) {
        campo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if(campo.getText().length() >= max) e.consume();
                if(mayus && Character.isLowerCase(e.getKeyChar())) e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
            }
        });
    }

    private void limitarSoloNumeros(JTextField campo, int max) {
        campo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!Character.isDigit(c) && c != '.') e.consume();
                if(campo.getText().length() >= max) e.consume();
            }
        });
    }
    
    public static void main(String[] args) {
        new Cotizacion().setVisible(true);
    }
}