package frontend;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import backend.RiasaDAO; 

public class AgregarAuto extends JFrame  {
    private JPanel panel;
    private JLabel et1,et5, et6, et7,et8 ,et9,et10;
    private JButton bt1, bt2, bt3;
    private JTextField txtF1, txtF2, txtF3, txtF4,txtF5;
    
    public AgregarAuto(){
        setBounds(500,90,500,650);
        setTitle("Registro Auto");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel();
    }

    private void panel(){
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);
        etiqueta();
        campoTexto();
        boton();
        eventos();
    }

    private void etiqueta(){
        et1= new JLabel();
        et1.setText("Registro de auto");
        et1.setBounds(0,0,500,50);
        et1.setFont(new Font("Arial",1,20));
        et1.setBackground(new Color(39, 142, 245));
        et1.setForeground(Color.white);
        et1.setHorizontalAlignment(SwingConstants.CENTER);
        et1.setOpaque(true);
        panel.add(et1);
        
        et5 = new JLabel();
        et5.setBounds(135,100,200,25);
        et5.setText("Datos del auto");
        et5.setFont(new Font("Arial",1,16));
        et5.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(et5);
        
        et6 = new JLabel(); et6.setBounds(50,150, 100, 20);
        et6.setFont(new Font("arial",2,15)); et6.setText("Placa: "); panel.add(et6);
        
        et7 = new JLabel(); et7.setBounds(50,190, 100, 20);
        et7.setFont(new Font("arial",2,15)); et7.setText("Marca: "); panel.add(et7);
        
        et8 = new JLabel(); et8.setBounds(50,230, 100, 20);
        et8.setFont(new Font("arial",2,15)); et8.setText("Modelo: "); panel.add(et8);
        
        et9 = new JLabel(); et9.setBounds(50,270, 100, 20);
        et9.setFont(new Font("arial",2,15)); et9.setText("Año: "); panel.add(et9);
        
        et10 = new JLabel(); et10.setBounds(50,310, 150, 20);
        et10.setFont(new Font("arial",2,15)); et10.setText("RFC del cliente:"); panel.add(et10);
    }
    
    private void campoTexto(){
        // --- PLACA (VARCHAR 10) ---
        txtF1 = new JTextField();
        txtF1.setBounds(170,150,240,20);
        limitarInput(txtF1, 10, true); // Mayúsculas forzadas
        panel.add(txtF1);
        
        // --- MARCA (VARCHAR 50) ---
        txtF2 = new JTextField();
        txtF2.setBounds(170,190,240,20);
        limitarInput(txtF2, 50, true);
        panel.add(txtF2);
        
        // --- MODELO (VARCHAR 50) ---
        txtF3 = new JTextField();
        txtF3.setBounds(170,230,240,20);
        limitarInput(txtF3, 50, true);
        panel.add(txtF3);
        
        // --- AÑO (INT) ---
        txtF4 = new JTextField();
        txtF4.setBounds(170,270,240,20);
        limitarSoloNumeros(txtF4, 4); // Solo dígitos, max 4
        panel.add(txtF4);
        
        // --- RFC CLIENTE (VARCHAR 13) ---
        txtF5 = new JTextField();
        txtF5.setBounds(170,310,240,20);
        limitarInput(txtF5, 13, true); // Mayúsculas forzadas
        panel.add(txtF5);
    }
    
    // Método auxiliar para texto normal (Placa, Marca, Modelo, RFC)
    private void limitarInput(JTextField campo, int longitudMaxima, boolean soloMayusculas) {
        campo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (campo.getText().length() >= longitudMaxima) {
                    e.consume(); Toolkit.getDefaultToolkit().beep();
                }
                if (soloMayusculas && Character.isLowerCase(c)) {
                    e.setKeyChar(Character.toUpperCase(c));
                }
            }
        });
    }

    // Método auxiliar exclusivo para el AÑO (Solo números)
    private void limitarSoloNumeros(JTextField campo, int longitudMaxima) {
        campo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // Si no es dígito, lo bloqueamos
                if (!Character.isDigit(c)) {
                    e.consume();
                    return;
                }
                if (campo.getText().length() >= longitudMaxima) {
                    e.consume(); Toolkit.getDefaultToolkit().beep();
                }
            }
        });
    }
    
    private void boton(){
        bt1= new JButton(); bt1.setBounds(70,400,80,20); bt1.setText("Aceptar"); panel.add(bt1);
        bt2= new JButton(); bt2.setBounds(200,400,80,20); bt2.setText("Limpiar"); panel.add(bt2);
        bt3= new JButton(); bt3.setBounds(330,400,80,20); bt3.setText("Atrás"); panel.add(bt3);
    }
    
    private void eventos(){
        // --- BOTÓN ACEPTAR ---
        ActionListener act = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 1. Obtener datos
                String placa = txtF1.getText().trim();
                String marca = txtF2.getText();
                String modelo = txtF3.getText();
                String anioStr = txtF4.getText();
                String rfcCliente = txtF5.getText().trim();

                // 2. Validar vacíos
                if(placa.isEmpty() || marca.isEmpty() || modelo.isEmpty() || anioStr.isEmpty() || rfcCliente.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
                    return;
                }

                // 3. Validar Año (Lógica)
                int anio = 0;
                try {
                    anio = Integer.parseInt(anioStr);
                    if (anio <= 1900 || anio > 2026) {
                        JOptionPane.showMessageDialog(null, "El año debe ser mayor a 1900 y menor a 2027.");
                        return;
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "El año no es válido.");
                    return;
                }

                // 4. Guardar en BD
                RiasaDAO dao = new RiasaDAO();
                boolean exito = dao.registrarAuto(placa, marca, modelo, anio, rfcCliente);

                // 5. Resultado
                if(exito) {
                    JOptionPane.showMessageDialog(null, "✅ Auto registrado correctamente.");
                    limpiarCampos();
                } else {
                    // Mensaje específico por si falla la llave foránea
                    JOptionPane.showMessageDialog(null, 
                        "Error al registrar.\n" +
                        "Posibles causas:\n" +
                        "1. La placa ya existe.\n" +
                        "2. El RFC del cliente NO existe (Registra al cliente primero).", 
                        "Error BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        bt1.addActionListener(act);
        
        // --- BOTÓN LIMPIAR ---
        ActionListener act1 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        };
        bt2.addActionListener(act1);
        
        // --- BOTÓN ATRÁS ---
        ActionListener act2 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    HomeRiasa hr = new HomeRiasa();
                    hr.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    System.out.println("Error al regresar: " + ex.getMessage());
                }
            }
        };
        bt3.addActionListener(act2);
    }

    private void limpiarCampos() {
        txtF1.setText("");
        txtF2.setText("");
        txtF3.setText("");
        txtF4.setText("");
        txtF5.setText("");
    }
}