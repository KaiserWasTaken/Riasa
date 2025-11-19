package frontend;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HomeRiasa extends JFrame {

    private JPanel panel;
    private JLabel titulo, opTitulo;
    private JLabel img1, img2, img3;
    private JLabel txt1, txt2, txt3;
    private JButton bt1, bt2, bt3;

    public HomeRiasa() {
        setBounds(500, 90, 500, 650);
        setTitle("Inicio - RIASA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel();
    }

    private void panel() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(230, 230, 230));
        this.getContentPane().add(panel);

        titulo();
        contenedorOpciones();
        botones();
    }

    private void titulo() {
        titulo = new JLabel();
        titulo.setText("Bienvenido al inicio, elija una opción.");
        titulo.setBounds(0, 10, 500, 40);
        titulo.setFont(new Font("Arial", 1, 20));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titulo);
    }

    private void contenedorOpciones() {

        // Marco principal similar a la imagen
        JPanel marco = new JPanel();
        marco.setLayout(null);
        marco.setBounds(30, 80, 430, 500);
        marco.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        marco.setOpaque(true);
        panel.add(marco);

        opTitulo = new JLabel();
        opTitulo.setText("OPCIONES");
        opTitulo.setFont(new Font("Arial", 1, 14));
        opTitulo.setBounds(15, 5, 200, 20);
        marco.add(opTitulo);

        // -------------------------------------------
        // Opción 1: Añadir Cliente
        // -------------------------------------------
        txt1 = new JLabel();
        txt1.setText("Añadir Cliente");
        txt1.setFont(new Font("Arial", 1, 16));
        txt1.setBounds(20, 60, 200, 20);
        marco.add(txt1);

        img1 = new JLabel();
        img1.setText("Imagen");
        img1.setOpaque(true);
        img1.setBackground(Color.WHITE);
        img1.setHorizontalAlignment(SwingConstants.CENTER);
        img1.setBounds(260, 40, 120, 80);
        marco.add(img1);

        bt1 = new JButton();
        bt1.setText("Seleccionar");
        bt1.setBounds(70, 90, 150, 35);
        marco.add(bt1);

        // -------------------------------------------
        // Opción 2: Añadir Coche
        // -------------------------------------------
        txt2 = new JLabel();
        txt2.setText("Añadir Coche");
        txt2.setFont(new Font("Arial", 1, 16));
        txt2.setBounds(20, 180, 200, 20);
        marco.add(txt2);

        img2 = new JLabel();
        img2.setText("Imagen");
        img2.setOpaque(true);
        img2.setBackground(Color.WHITE);
        img2.setHorizontalAlignment(SwingConstants.CENTER);
        img2.setBounds(260, 160, 120, 80);
        marco.add(img2);

        bt2 = new JButton();
        bt2.setText("Seleccionar");
        bt2.setBounds(70, 210, 150, 35);
        marco.add(bt2);

        // -------------------------------------------
        // Opción 3: Generar Factura
        // -------------------------------------------
        txt3 = new JLabel();
        txt3.setText("Generar Factura");
        txt3.setFont(new Font("Arial", 1, 16));
        txt3.setBounds(20, 300, 200, 20);
        marco.add(txt3);

        img3 = new JLabel();
        img3.setText("Imagen");
        img3.setOpaque(true);
        img3.setBackground(Color.WHITE);
        img3.setHorizontalAlignment(SwingConstants.CENTER);
        img3.setBounds(260, 280, 120, 80);
        marco.add(img3);

        bt3 = new JButton();
        bt3.setText("Seleccionar");
        bt3.setBounds(70, 330, 150, 35);
        marco.add(bt3);
    }

    private void botones() {
        // Aquí puedes agregar los listeners si los necesitas
        ActionListener act1 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AgregarCliente ac = new AgregarCliente();
                ac.setVisible(true);
                dispose();
            }
        };
        ActionListener act2 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Botón presionado");
            }
        };
        ActionListener act3 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Botón presionado");
            }
        };

        bt1.addActionListener(act1);
        bt2.addActionListener(act2);
        bt3.addActionListener(act3);
    }
}
