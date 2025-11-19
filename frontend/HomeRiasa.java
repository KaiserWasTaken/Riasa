package frontend;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HomeRiasa extends JFrame {

    // Paleta de colores (Misma del Login)
    private Color colorPrimario = new Color(44, 62, 80);    // Azul Oscuro
    private Color colorFondo    = new Color(236, 240, 241); // Gris Claro
    private Color colorAcento   = new Color(52, 152, 219);  // Azul Brillante
    private Color colorBlanco   = Color.WHITE;

    public HomeRiasa() {
        // Aumentamos un poco el ancho para que quepan las tarjetas cómodamente
        setBounds(450, 100, 850, 600);
        setTitle("Panel de Control - RIASA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(colorFondo);
        this.getContentPane().add(mainPanel);

        // --- 1. HEADER (Barra Superior) ---
        JPanel header = new JPanel();
        header.setBounds(0, 0, 850, 80);
        header.setBackground(colorPrimario);
        header.setLayout(null);
        mainPanel.add(header);

        JLabel lblTitulo = new JLabel("RIASA | Panel de Control");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setBounds(30, 20, 400, 40);
        header.add(lblTitulo);

        // Botón Cerrar Sesión (Esquina superior derecha)
        JButton btnLogout = new JButton("Cerrar Sesión");
        btnLogout.setBounds(680, 25, 130, 30);
        btnLogout.setBackground(new Color(192, 57, 43)); // Rojo suave
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFocusPainted(false);
        btnLogout.setBorderPainted(false);
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnLogout.addActionListener(e -> {
            // Volver al Login
            LoginRiasa login = new LoginRiasa();
            login.setVisible(true);
            dispose();
        });
        header.add(btnLogout);

        // --- 2. CONTENEDOR DE TARJETAS ---
        // Aquí llamamos al método auxiliar para crear cada opción
        // (X, Y, Título, Icono, Descripción, Acción)

        // TARJETA 1: CLIENTES
        crearTarjeta(mainPanel, 50, 150, "Clientes", "👤", "Registrar nuevos clientes", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AgregarCliente ac = new AgregarCliente();
                ac.setVisible(true);
                dispose();
            }
        });

        // TARJETA 2: AUTOMÓVILES
        crearTarjeta(mainPanel, 300, 150, "Vehículos", "🚗", "Asignar autos a clientes", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AgregarAuto aa = new AgregarAuto();
                aa.setVisible(true);
                dispose();
            }
        });

        // TARJETA 3: FACTURACIÓN
        crearTarjeta(mainPanel, 550, 150, "Caja / Factura", "📄", "Cobrar y generar PDF", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Cotizacion cot = new Cotizacion();
                cot.setVisible(true);
                dispose();
            }
        });
        
        // FOOTER (Pie de página decorativo)
        JLabel lblFooter = new JLabel("Sistema de Gestión v1.0 - 2025");
        lblFooter.setForeground(new Color(127, 140, 141));
        lblFooter.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblFooter.setBounds(0, 530, 850, 20);
        lblFooter.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(lblFooter);
    }

    /**
     * MÉTODO MÁGICO: Crea una tarjeta de diseño automáticamente.
     * Evita repetir código y mantiene el estilo uniforme.
     */
    /**
     * MÉTODO MÁGICO: Crea una tarjeta de diseño automáticamente.
     * Evita repetir código y mantiene el estilo uniforme.
     */
    private void crearTarjeta(JPanel parent, int x, int y, String titulo, String icono, String desc, ActionListener accion) {
        
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBounds(x, y, 220, 280); // Tamaño de la tarjeta
        card.setBackground(colorBlanco); // <-- ¡IMPORTANTE! Aseguramos que la tarjeta sea blanca
        
        // Borde inferior azul para darle estilo
        card.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, colorAcento));
        
        // Sombra simulada (Un panel gris oscuro detrás)
        // Por ahora, para arreglar el problema visual, vamos a SIMPLIFICAR la sombra.
        // La forma en que estaba implementada, la sombra se quedaba por encima o no se veía bien.
        // Si quieres una sombra más avanzada, necesitaríamos una librería o más complejidad.
        // Por ahora, simplemente agregamos la tarjeta con su fondo blanco.
        parent.add(card);   // Agregamos la tarjeta directamente

        // 1. Icono (Usamos fuente grande para simular imagen)
        JLabel lblIcono = new JLabel(icono);
        lblIcono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 60)); // Emoji font para los iconos
        lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
        lblIcono.setBounds(0, 30, 220, 70); // Centrado en la tarjeta
        card.add(lblIcono);

        // 2. Título
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(colorPrimario);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(0, 110, 220, 30); // Centrado en la tarjeta
        card.add(lblTitulo);

        // 3. Descripción
        JLabel lblDesc = new JLabel("<html><center>" + desc + "</center></html>"); // HTML para centrar texto
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDesc.setForeground(Color.GRAY);
        lblDesc.setHorizontalAlignment(SwingConstants.CENTER);
        lblDesc.setBounds(10, 140, 200, 40); // Pequeños márgenes laterales
        card.add(lblDesc);

        // 4. Botón de Acción
        JButton btn = new JButton("ACCEDER");
        btn.setBounds(40, 200, 140, 40); // Ajustar posición para que quede centrado en la tarjeta
        btn.setBackground(colorPrimario);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Efecto Hover en el botón
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btn.setBackground(colorAcento);
            }
            public void mouseExited(MouseEvent evt) {
                btn.setBackground(colorPrimario);
            }
        });
        
        btn.addActionListener(accion);
        card.add(btn);
    }
    
    // Main para probar visualmente
    public static void main(String[] args) {
        new HomeRiasa().setVisible(true);
    }
}