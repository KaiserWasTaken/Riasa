package frontend;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AgregarAuto extends JFrame  {
    private JPanel panel;
    private JLabel et1,et5, et6, et7,et8 ,et9,et10;
    private JButton bt1, bt2, bt3;
    private JTextField txtF1, txtF2, txtF3, txtF4,txtF5;
    
    public AgregarAuto(){
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
        
        et6 = new JLabel();
        et6.setBounds(50,150, 100, 20);
        et6.setFont(new Font("arial",2,15));
        et6.setText("Placa: ");
        panel.add(et6);
        
        et7 = new JLabel();
        et7.setBounds(50,190, 100, 20);
        et7.setFont(new Font("arial",2,15));
        et7.setText("Marca: ");
        panel.add(et7);
        
        et8 = new JLabel();
        et8.setBounds(50,230, 100, 20);
        et8.setFont(new Font("arial",2,15));
        et8.setText("Modelo: ");
        panel.add(et8);
        
        et9 = new JLabel();
        et9.setBounds(50,270, 100, 20);
        et9.setFont(new Font("arial",2,15));
        et9.setText("Año: ");
        panel.add(et9);
        
        et10 = new JLabel();
        et10.setBounds(50,310, 150, 20);
        et10.setFont(new Font("arial",2,15));
        et10.setText("RFC del cliente:");
        panel.add(et10);
        
        
    }
    
    private void campoTexto(){
        txtF1 = new JTextField();
        txtF1.setBounds(150,150,280,20);
        panel.add(txtF1);
        
        txtF2 = new JTextField();
        txtF2.setBounds(130,190,280,20);
        panel.add(txtF2);
        
        txtF3 = new JTextField();
        txtF3.setBounds(130,230,280,20);
        panel.add(txtF3);
        
        txtF4 = new JTextField();
        txtF4.setBounds(130,270,280,20);
        panel.add(txtF4);
        
        txtF5 = new JTextField();
        txtF5.setBounds(170,310,240,20);
        panel.add(txtF5);
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
                
                dispose();
                }
        };
        bt1.addActionListener(act);
        
        //Evento para boton limipar
        ActionListener act1 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtF1.setText(null);
                txtF2.setText(null);
                txtF3.setText(null);
                txtF4.setText(null);
                txtF5.setText(null);
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

