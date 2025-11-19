
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
        campoTexto();
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
        
        et6 = new JLabel();
        et6.setBounds(50,150, 100, 20);
        et6.setFont(new Font("arial",2,15));
        et6.setText("RFC: ");
        panel.add(et6);
        
        et7 = new JLabel();
        et7.setBounds(50,190, 100, 20);
        et7.setFont(new Font("arial",2,15));
        et7.setText("Nombre: ");
        panel.add(et7);
        
        et8 = new JLabel();
        et8.setBounds(50,230, 100, 20);
        et8.setFont(new Font("arial",2,15));
        et8.setText("Telefono: ");
        panel.add(et8);
        
        et9 = new JLabel();
        et9.setBounds(50,270, 100, 20);
        et9.setFont(new Font("arial",2,15));
        et9.setText("Direccion ");
        panel.add(et9);
        
        et10 = new JLabel();
        et10.setBounds(50,310, 100, 20);
        et10.setFont(new Font("arial",2,15));
        et10.setText("Email");
        panel.add(et10);
        
        
    }
    
    private void campoTexto(){
        txtRFC = new JTextField();
        txtRFC.setBounds(130,150,280,20);
        panel.add(txtRFC);
        
        txtNOMBRE = new JTextField();
        txtNOMBRE.setBounds(130,190,280,20);
        panel.add(txtNOMBRE);
        
        txtTELEFONO = new JTextField();
        txtTELEFONO.setBounds(130,230,280,20);
        panel.add(txtTELEFONO);
        
        txtDIRECCION = new JTextField();
        txtDIRECCION.setBounds(130,270,280,20);
        panel.add(txtDIRECCION);
        
        txtEMAIL = new JTextField();
        txtEMAIL.setBounds(130,310,280,20);
        panel.add(txtEMAIL);
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
        // --- EVENTO BOTÓN ACEPTAR (Guardar en BD) ---
        ActionListener act = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // 1. Obtener lo que el usuario escribió en las cajas de texto
                String rfc = txtRFC.getText();
                String nombre = txtNOMBRE.getText();
                String telefono = txtTELEFONO.getText();
                String direccion = txtDIRECCION.getText();
                String email = txtEMAIL.getText();

                // 2. Validación básica: Que no envíen campos vacíos obligatorios
                if(rfc.isEmpty() || nombre.isEmpty() || direccion.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor llena los campos obligatorios (RFC, Nombre, Dirección).");
                    return; // Detiene la ejecución aquí
                }

                // 3. Conectar con el DAO
                // Asegúrate de tener acceso a tu clase RiasaDAO aquí
                RiasaDAO dao = new RiasaDAO(); 

                // 4. Intentar guardar
                boolean exito = dao.registrarCliente(rfc, nombre, telefono, direccion, email);

                // 5. Dar respuesta al usuario
                if(exito) {
                    JOptionPane.showMessageDialog(null, "¡Cliente registrado exitosamente!");
                    
                    // Opcional: Limpiar las cajas después de guardar
                    limpiarCampos(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar.\nVerifica que el RFC no esté duplicado o sea muy corto.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        btAceptar.addActionListener(act);
        
        // --- EVENTO BOTÓN LIMPIAR ---
        ActionListener act1 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarCampos(); // Llamamos al método auxiliar
            }
        };
        btLimpiar.addActionListener(act1);
        
        // --- EVENTO BOTÓN ATRÁS ---
        ActionListener act2 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Asegúrate de que HomeRiasa exista y esté importado
                try {
                    HomeRiasa hr = new HomeRiasa(); // Descomenta cuando tengas la clase HomeRiasa lista
                    hr.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    System.out.println("Error al regresar: " + ex.getMessage());
                }
            }
        };
        btAtras.addActionListener(act2);
    }

    // Método auxiliar para no repetir código de limpiar
    private void limpiarCampos() {
        txtRFC.setText("");
        txtNOMBRE.setText("");
        txtTELEFONO.setText("");
        txtDIRECCION.setText("");
        txtEMAIL.setText("");
    }
    
}
