package frontend;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import backend.RiasaDAO; 

public class AgregarCliente extends JFrame  {
    private JPanel panel;
    private JLabel et1,et5, et6, et7,et8 ,et9,et10;
    private JButton btAceptar, btLimpiar, btAtras;
    private JTextField txtRFC, txtNOMBRE, txtTELEFONO, txtDIRECCION, txtEMAIL;
    
    public AgregarCliente(){
        setBounds(500,90,500,650);
        setTitle("Registro cliente");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel();
    }

    private void panel(){
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);
        etiqueta();
        campoTexto(); // Aquí aplicamos las restricciones de longitud
        boton();
        eventos();
    }

    private void etiqueta(){
        et1= new JLabel();
        et1.setText("Registro de cliente");
        et1.setBounds(0,0,500,50);
        et1.setFont(new Font("Arial",1,20));
        et1.setBackground(new Color(39, 142, 245));
        et1.setForeground(Color.white);
        et1.setHorizontalAlignment(SwingConstants.CENTER);
        et1.setOpaque(true);
        panel.add(et1);
        
        et5 = new JLabel();
        et5.setBounds(135,100,200,25);
        et5.setText("Datos del cliente");
        et5.setFont(new Font("Arial",1,16));
        et5.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(et5);
        
        et6 = new JLabel(); et6.setBounds(50,150, 100, 20);
        et6.setFont(new Font("arial",2,15)); et6.setText("RFC: "); panel.add(et6);
        
        et7 = new JLabel(); et7.setBounds(50,190, 100, 20);
        et7.setFont(new Font("arial",2,15)); et7.setText("Nombre: "); panel.add(et7);
        
        et8 = new JLabel(); et8.setBounds(50,230, 100, 20);
        et8.setFont(new Font("arial",2,15)); et8.setText("Telefono: "); panel.add(et8);
        
        et9 = new JLabel(); et9.setBounds(50,270, 100, 20);
        et9.setFont(new Font("arial",2,15)); et9.setText("Direccion "); panel.add(et9);
        
        et10 = new JLabel(); et10.setBounds(50,310, 100, 20);
        et10.setFont(new Font("arial",2,15)); et10.setText("Email"); panel.add(et10);
    }
    
    private void campoTexto(){
        // --- RFC (VARCHAR 13) ---
        txtRFC = new JTextField();
        txtRFC.setBounds(130,150,280,20);
        // Aquí limitamos a 13 caracteres y forzamos MAYÚSCULAS automáticamente
        limitarInput(txtRFC, 13, true); 
        panel.add(txtRFC);
        
        // --- NOMBRE (VARCHAR 100) ---
        txtNOMBRE = new JTextField();
        txtNOMBRE.setBounds(130,190,280,20);
        limitarInput(txtNOMBRE, 100, false);
        panel.add(txtNOMBRE);
        
        // --- TELEFONO (VARCHAR 20) ---
        txtTELEFONO = new JTextField();
        txtTELEFONO.setBounds(130,230,280,20);
        limitarInput(txtTELEFONO, 20, false); 
        // Opcional: Podrías agregar validación solo números aquí si quisieras
        panel.add(txtTELEFONO);
        
        // --- DIRECCION (VARCHAR 255) ---
        txtDIRECCION = new JTextField();
        txtDIRECCION.setBounds(130,270,280,20);
        limitarInput(txtDIRECCION, 255, false);
        panel.add(txtDIRECCION);
        
        // --- EMAIL (VARCHAR 100) ---
        txtEMAIL = new JTextField();
        txtEMAIL.setBounds(130,310,280,20);
        limitarInput(txtEMAIL, 100, false);
        panel.add(txtEMAIL);
    }

    // --- NUEVO MÉTODO AUXILIAR PARA RESTRINGIR ESCRITURA ---
    private void limitarInput(JTextField campo, int longitudMaxima, boolean soloMayusculas) {
        campo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                
                // 1. Si quiere escribir más del límite, lo bloqueamos (consume)
                if (campo.getText().length() >= longitudMaxima) {
                    e.consume(); 
                    // Un "beep" para avisar que llegó al límite
                    Toolkit.getDefaultToolkit().beep(); 
                }
                
                // 2. Si pedimos mayúsculas (para el RFC), convertimos la letra
                if (soloMayusculas) {
                    if (Character.isLowerCase(c)) {
                        e.setKeyChar(Character.toUpperCase(c));
                    }
                }
            }
        });
    }
    
    private void boton(){
        btAceptar= new JButton();
        btAceptar.setBounds(70,400,80,20);
        btAceptar.setText("Aceptar");
        panel.add(btAceptar);
        
        btLimpiar= new JButton();
        btLimpiar.setBounds(200,400,80,20);
        btLimpiar.setText("Limpiar");
        panel.add(btLimpiar);
        
        btAtras= new JButton();
        btAtras.setBounds(330,400,80,20);
        btAtras.setText("Atrás");
        panel.add(btAtras);
    }
    
    private void eventos(){
        ActionListener act = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                String rfc = txtRFC.getText().trim(); // .trim() quita espacios accidentales al final
                String nombre = txtNOMBRE.getText();
                String telefono = txtTELEFONO.getText();
                String direccion = txtDIRECCION.getText();
                String email = txtEMAIL.getText();

                // 1. Validación de Campos Vacíos
                if(rfc.isEmpty() || nombre.isEmpty() || direccion.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Faltan datos obligatorios.");
                    return;
                }
                
                // 2. VALIDACIÓN DB: CHECK (LENGTH(rfc) >= 12)
                // Aunque limitamos el máximo a 13 arriba, el usuario podría escribir solo 5 letras.
                // Aquí validamos el MÍNIMO.
                if (rfc.length() < 12) {
                    JOptionPane.showMessageDialog(null, "El RFC es inválido.\nDebe tener al menos 12 caracteres.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                    return; // Detenemos el guardado
                }

                RiasaDAO dao = new RiasaDAO(); 
                boolean exito = dao.registrarCliente(rfc, nombre, telefono, direccion, email);

                if(exito) {
                    JOptionPane.showMessageDialog(null, "¡Cliente registrado exitosamente!");
                    limpiarCampos(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar.\nVerifica si el RFC o Email ya existen.", "Error BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        btAceptar.addActionListener(act);
        
        ActionListener act1 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        };
        btLimpiar.addActionListener(act1);
        
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
        btAtras.addActionListener(act2);
    }

    private void limpiarCampos() {
        txtRFC.setText("");
        txtNOMBRE.setText("");
        txtTELEFONO.setText("");
        txtDIRECCION.setText("");
        txtEMAIL.setText("");
    }
}