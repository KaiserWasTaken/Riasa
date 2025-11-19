/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package paquete;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author ezuri
 */
public class paginaAdministrador extends javax.swing.JFrame {

  private JMenuItem Productos;
  private JMenuItem mniClientes;
 private JMenuItem mniVehiculos;
  private JMenuItem mniInventario;
  private JMenuItem mniVentas;
  private JMenuItem mniReportes;
  private JMenuItem mniConfig;
  private JMenuItem mniSalir;
  private CardLayout vista;
 

  
  //Imagenes
    public String logproduc = "/imagenes/caja.png";
    public String logo_salir = "/imagenes/salir.png";
    public String logo_config = "/imagenes/config.png";
    public String logo_cliente = "/imagenes/clientes.png";
    public String logo_reporte = "/imagenes/reportes.png";
    public String logo_ventas = "/imagenes/ventas.png";
    public String logo_inventario = "/imagenes/inventario.png";
    public String logo_vehiculos="/imagenes/coche.png";
    //public String logo_busqueda = "/imagenes/busqueda.png";
    
    
   
  Color MCOlor  = new Color(24,127,220);
          
    public paginaAdministrador() {
        
        initComponents();
        vista = new CardLayout();
        PanelPrincipal.setLayout(vista);

        PanelPrincipal.add(PanelProductos, "productos");
        PanelPrincipal.add(PanelClientes, "clientes");
        PanelPrincipal.add(PanelVentas, "ventas");
        PanelPrincipal.add(PanelInvetario, "inventario");
        PanelPrincipal.add(PanelReportes, "reportes");
        PanelPrincipal.add(PanelConfiguracion, "configuracion");
        Productos = new JMenuItem("Productos",getIcono(logproduc));
        mniClientes = new JMenuItem(" Clientes ", getIcono(logo_cliente));
        mniVentas = new JMenuItem(" Ventas ", getIcono(logo_ventas));
        mniInventario = new JMenuItem(" Inventario ", getIcono(logo_inventario));
        mniReportes = new JMenuItem(" Reportes ", getIcono(logo_reporte));
        mniConfig = new JMenuItem(" Configuración ", getIcono(logo_config));
        mniSalir = new JMenuItem(" Salir ", getIcono(logo_salir));
        mniVehiculos = new JMenuItem("Vehiculos",getIcono(logo_vehiculos));
        
        
        mniConfig.setOpaque(true);
        mniConfig.setBackground(MCOlor);
        mniConfig.setForeground(Color.white);

        mniVentas.setOpaque(true);
        mniVentas.setBackground(MCOlor);
        mniVentas.setForeground(Color.white);

        mniInventario.setOpaque(true);
        mniInventario.setBackground(MCOlor);
        mniInventario.setForeground(Color.white);

        mniClientes.setOpaque(true);
        mniClientes.setBackground(MCOlor);
        mniClientes.setForeground(Color.white);

        mniReportes.setOpaque(true);
        mniReportes.setBackground(MCOlor);
        mniReportes.setForeground(Color.white);

        mniSalir.setOpaque(true);
        mniSalir.setBackground(MCOlor);
        mniSalir.setForeground(Color.white);
        
        
        mniVehiculos.setOpaque(true);
        mniVehiculos.setBackground(MCOlor);
        mniVehiculos.setForeground(Color.white);
        
        Productos.addActionListener((ActionEvent e) -> {
    vista.show(PanelPrincipal, "productos");
});

mniClientes.addActionListener((ActionEvent e) -> {
    vista.show(PanelPrincipal, "clientes");
});

mniInventario.addActionListener((ActionEvent e) -> {
    vista.show(PanelPrincipal, "inventario");
});

mniVentas.addActionListener((ActionEvent e) -> {
    vista.show(PanelPrincipal, "ventas");
});

mniReportes.addActionListener((ActionEvent e) -> {
    vista.show(PanelPrincipal, "reportes");
});

mniConfig.addActionListener((ActionEvent e) -> {
    vista.show(PanelPrincipal, "configuracion");
});


        
        Productos.setOpaque(true);
        Productos.setBackground(MCOlor);
        Productos.setForeground(Color.WHITE);
        Mbmenu.add(Productos);
        Mbmenu.add(mniClientes);
        Mbmenu.add(mniVehiculos);
        Mbmenu.add(mniVentas);
        Mbmenu.add(mniInventario);
        Mbmenu.add(mniReportes);
        Mbmenu.add(mniConfig);
        Mbmenu.add(mniSalir);
    }
    
    
    private Icon getIcono(String ruta){
        return new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(30, 30, 0));
    }
    
    private void CambiarVIsta(JPanel jpanel){
        jpanel.setSize(PanelPrincipal.getWidth(),PanelPrincipal.getHeight());
        PanelPrincipal.removeAll();
        PanelPrincipal.add(jpanel);
        PanelPrincipal.repaint();
        
    }
    
    private void Activarvista(JPanel JPanel){
        PanelProductos.setEnabled(false);
        PanelProductos.setVisible(false);
        
        PanelClientes.setEnabled(false);
        PanelClientes.setVisible(false);
        
        PanelVentas.setEnabled(false);
        PanelVentas.setVisible(false);
        
        PanelInvetario.setEnabled(false);
        PanelInvetario.setVisible(false);
        
        PanelReportes.setEnabled(false);
        PanelReportes.setVisible(false);
        
        PanelConfiguracion.setEnabled(false);
        PanelConfiguracion.setVisible(false);
        
        
        JPanel.setVisible(true);
        JPanel.setEnabled(true);
       
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelPrincipal = new javax.swing.JPanel();
        PanelProductos = new javax.swing.JPanel();
        Produc = new javax.swing.JLabel();
        PanelInvetario = new javax.swing.JPanel();
        Inven = new javax.swing.JLabel();
        PanelReportes = new javax.swing.JPanel();
        Repor = new javax.swing.JLabel();
        PanelConfiguracion = new javax.swing.JPanel();
        COnfig = new javax.swing.JLabel();
        PanelVentas = new javax.swing.JPanel();
        Vent = new javax.swing.JLabel();
        PanelClientes = new javax.swing.JPanel();
        Clien = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        PanelVehiculos = new javax.swing.JPanel();
        Clien1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        Mbmenu = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelPrincipal.setBackground(new java.awt.Color(204, 255, 255));

        Produc.setText("Productos");

        Inven.setText("Invetarios");

        Repor.setText("Reportes");

        COnfig.setText("Configuracion");

        javax.swing.GroupLayout PanelConfiguracionLayout = new javax.swing.GroupLayout(PanelConfiguracion);
        PanelConfiguracion.setLayout(PanelConfiguracionLayout);
        PanelConfiguracionLayout.setHorizontalGroup(
            PanelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelConfiguracionLayout.createSequentialGroup()
                .addContainerGap(643, Short.MAX_VALUE)
                .addComponent(COnfig)
                .addGap(536, 536, 536))
        );
        PanelConfiguracionLayout.setVerticalGroup(
            PanelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelConfiguracionLayout.createSequentialGroup()
                .addGap(364, 364, 364)
                .addComponent(COnfig)
                .addContainerGap(256, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelReportesLayout = new javax.swing.GroupLayout(PanelReportes);
        PanelReportes.setLayout(PanelReportesLayout);
        PanelReportesLayout.setHorizontalGroup(
            PanelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelReportesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanelConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(PanelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelReportesLayout.createSequentialGroup()
                    .addGap(587, 587, 587)
                    .addComponent(Repor)
                    .addContainerGap(587, Short.MAX_VALUE)))
        );
        PanelReportesLayout.setVerticalGroup(
            PanelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelReportesLayout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(PanelConfiguracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(PanelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelReportesLayout.createSequentialGroup()
                    .addGap(413, 413, 413)
                    .addComponent(Repor)
                    .addContainerGap(414, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout PanelInvetarioLayout = new javax.swing.GroupLayout(PanelInvetario);
        PanelInvetario.setLayout(PanelInvetarioLayout);
        PanelInvetarioLayout.setHorizontalGroup(
            PanelInvetarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInvetarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelReportes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(PanelInvetarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelInvetarioLayout.createSequentialGroup()
                    .addGap(591, 591, 591)
                    .addComponent(Inven)
                    .addContainerGap(591, Short.MAX_VALUE)))
        );
        PanelInvetarioLayout.setVerticalGroup(
            PanelInvetarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInvetarioLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(PanelReportes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(PanelInvetarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelInvetarioLayout.createSequentialGroup()
                    .addGap(416, 416, 416)
                    .addComponent(Inven)
                    .addContainerGap(417, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout PanelProductosLayout = new javax.swing.GroupLayout(PanelProductos);
        PanelProductos.setLayout(PanelProductosLayout);
        PanelProductosLayout.setHorizontalGroup(
            PanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelProductosLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(PanelInvetario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(PanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelProductosLayout.createSequentialGroup()
                    .addGap(609, 609, 609)
                    .addComponent(Produc)
                    .addContainerGap(609, Short.MAX_VALUE)))
        );
        PanelProductosLayout.setVerticalGroup(
            PanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelProductosLayout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(PanelInvetario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(PanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelProductosLayout.createSequentialGroup()
                    .addGap(416, 416, 416)
                    .addComponent(Produc)
                    .addContainerGap(417, Short.MAX_VALUE)))
        );

        Vent.setText("Ventas");

        javax.swing.GroupLayout PanelVentasLayout = new javax.swing.GroupLayout(PanelVentas);
        PanelVentas.setLayout(PanelVentasLayout);
        PanelVentasLayout.setHorizontalGroup(
            PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelVentasLayout.createSequentialGroup()
                    .addGap(609, 609, 609)
                    .addComponent(Vent)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        PanelVentasLayout.setVerticalGroup(
            PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 843, Short.MAX_VALUE)
            .addGroup(PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelVentasLayout.createSequentialGroup()
                    .addGap(413, 413, 413)
                    .addComponent(Vent)
                    .addContainerGap(414, Short.MAX_VALUE)))
        );

        Clien.setText("Clientes registrados");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombres", "Apellido Materno", "Apellido Paterno", "Teléfono", "Correo", "RFC"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Modificar cliente");

        jButton2.setText("Eliminar cliente");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Buscar cliente");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Agregar cliente");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelClientesLayout = new javax.swing.GroupLayout(PanelClientes);
        PanelClientes.setLayout(PanelClientesLayout);
        PanelClientesLayout.setHorizontalGroup(
            PanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelClientesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(340, 340, 340))
            .addGroup(PanelClientesLayout.createSequentialGroup()
                .addGroup(PanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelClientesLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(Clien)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelClientesLayout.createSequentialGroup()
                        .addContainerGap(59, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1349, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        PanelClientesLayout.setVerticalGroup(
            PanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelClientesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Clien)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addGroup(PanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(1276, Short.MAX_VALUE))
        );

        Clien1.setText("Vehiculos registrados");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombres", "Apellido Materno", "Apellido Paterno", "Teléfono", "Correo", "RFC"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jButton5.setText("Modificar cliente");

        jButton6.setText("Eliminar cliente");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Buscar cliente");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Agregar cliente");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelVehiculosLayout = new javax.swing.GroupLayout(PanelVehiculos);
        PanelVehiculos.setLayout(PanelVehiculosLayout);
        PanelVehiculosLayout.setHorizontalGroup(
            PanelVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVehiculosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(340, 340, 340))
            .addGroup(PanelVehiculosLayout.createSequentialGroup()
                .addGroup(PanelVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelVehiculosLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(Clien1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelVehiculosLayout.createSequentialGroup()
                        .addContainerGap(59, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1349, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        PanelVehiculosLayout.setVerticalGroup(
            PanelVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVehiculosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PanelVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Clien1)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addGroup(PanelVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addContainerGap(299, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelPrincipalLayout = new javax.swing.GroupLayout(PanelPrincipal);
        PanelPrincipal.setLayout(PanelPrincipalLayout);
        PanelPrincipalLayout.setHorizontalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(PanelProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(PanelVentas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PanelClientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanelVehiculos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelPrincipalLayout.setVerticalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(PanelProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelPrincipalLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PanelClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(PanelVehiculos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        Mbmenu.add(jMenu1);
        Mbmenu.add(jMenu2);

        setJMenuBar(Mbmenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(paginaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(paginaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(paginaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(paginaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new paginaAdministrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel COnfig;
    private javax.swing.JLabel Clien;
    private javax.swing.JLabel Clien1;
    private javax.swing.JLabel Inven;
    private javax.swing.JMenuBar Mbmenu;
    private javax.swing.JPanel PanelClientes;
    private javax.swing.JPanel PanelConfiguracion;
    private javax.swing.JPanel PanelInvetario;
    private javax.swing.JPanel PanelPrincipal;
    private javax.swing.JPanel PanelProductos;
    private javax.swing.JPanel PanelReportes;
    private javax.swing.JPanel PanelVehiculos;
    private javax.swing.JPanel PanelVentas;
    private javax.swing.JLabel Produc;
    private javax.swing.JLabel Repor;
    private javax.swing.JLabel Vent;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
