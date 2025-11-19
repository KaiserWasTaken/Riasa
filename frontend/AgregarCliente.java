package frontend;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AgregarCliente extends JFrame  {
    private JPanel panel;
    private JLabel et1,et5, et6, et7,et8 ,et9,et10;
    private JButton bt1, bt2, bt3;
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
        bt1= new JButton();
        bt1.setBounds(70,400,80,20);
        bt1.setText("Aceptar");
        panel.add(bt1);
        
        bt2= new JButton();
        bt2.setBounds(200,400,80,20);
        bt2.setText("Limpiar");
        panel.add(bt2);
        
        bt3= new JButton();
        bt3.setBounds(330,400,80,20);
        bt3.setText("Atrás");
        panel.add(bt3);
    }
    
    private void eventos(){
        //Evento para boton de registro
        ActionListener act = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                JOptionPane.showMessageDialog(null, "Kaiser super gei");
                }
        };
        bt1.addActionListener(act);
        
        //Evento para boton limipar
        ActionListener act1 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtRFC.setText(null);
                txtNOMBRE.setText(null);
                txtTELEFONO.setText(null);
                txtDIRECCION.setText(null);
                txtEMAIL.setText(null);
                }
        };
        bt2.addActionListener(act1);
        
        //Evento para salir del programa
        ActionListener act2 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomeRiasa hr = new HomeRiasa();
                hr.setVisible(true);
                dispose();
                }
        };
        bt3.addActionListener(act2);
    }
    
}
