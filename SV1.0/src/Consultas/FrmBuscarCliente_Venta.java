/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Consultas;

import Entidad.ClsEntidadCliente;
import Negocio.ClsCliente;
import java.awt.Color;
import java.awt.Component;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 */
public class FrmBuscarCliente_Venta extends javax.swing.JInternalFrame {

    static ResultSet rs=null;
    DefaultTableModel dtm=new DefaultTableModel();

    String criterio,busqueda,Total;
    public FrmBuscarCliente_Venta() {
        initComponents();
        buttonGroup1.add(rbtnCodigo);
        buttonGroup1.add(rbtnNombre);
        buttonGroup1.add(rbtnRol);
        buttonGroup1.add(rbtnRut);
        //--------------------PANEL - PRODUCTO----------------------------
        
        actualizarTablaCliente();
        CrearTablaCliente();
        //---------------------ANCHO Y ALTO DEL FORM----------------------
        this.setSize(536, 300);
        CantidadTotal();
    }

//-----------------------------------------------------------------------------------------------
//----------------------------------PANEL - CLIENTE----------------------------------------------
//-----------------------------------------------------------------------------------------------
    void actualizarTablaCliente(){
      String titulos[]={"ID","Nombre o Razón Social","ROL","RUT","Dirección","Teléfono","Observación"};
              
       ClsCliente clientes=new ClsCliente();
       ArrayList<ClsEntidadCliente> cliente=clientes.listarCliente();
       Iterator iterator=cliente.iterator();
       DefaultTableModel defaultTableModel=new DefaultTableModel(null,titulos);
       
       String fila[]=new String[7];
       while(iterator.hasNext()){
           ClsEntidadCliente Cliente=new ClsEntidadCliente();
           Cliente=(ClsEntidadCliente) iterator.next();
           fila[0]=Cliente.getStrIdCliente();
           fila[1]=Cliente.getStrNombreCliente();       
           fila[2]=Cliente.getStrRolCliente();
           fila[3]=Cliente.getStrRutCliente();
           fila[4]=Cliente.getStrDireccionCliente();
           fila[5]=Cliente.getStrTelefonoCliente();
           fila[6]=Cliente.getStrObsvCliente();
           defaultTableModel.addRow(fila);               
       }
       tblCliente.setModel(defaultTableModel);
    }
    void CrearTablaCliente(){
   //--------------------PRESENTACION DE JTABLE----------------------
      
        TableCellRenderer render = new DefaultTableCellRenderer() { 

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) { 
                //aqui obtengo el render de la calse superior 
                JLabel l = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
                //Determinar Alineaciones   
                    if(column==0 || column==2 || column==3 || column==5){
                        l.setHorizontalAlignment(SwingConstants.CENTER); 
                    }else{
                        l.setHorizontalAlignment(SwingConstants.LEFT);
                    }

                //Colores en Jtable        
                if (isSelected) {
                    l.setBackground(new Color(203, 159, 41));
                    //l.setBackground(new Color(168, 198, 238));
                    l.setForeground(Color.WHITE); 
                }else{
                    l.setForeground(Color.BLACK);
                    if (row % 2 == 0) {
                        l.setBackground(Color.WHITE);
                    } else {
                        //l.setBackground(new Color(232, 232, 232));
                        l.setBackground(new Color(254, 227, 152));
                    }
                }     
                return l; 
            } 
        }; 
        
        //Agregar Render
        for (int i=0;i<tblCliente.getColumnCount();i++){
            tblCliente.getColumnModel().getColumn(i).setCellRenderer(render);
        }
      
        //Activar ScrollBar
        tblCliente.setAutoResizeMode(tblCliente.AUTO_RESIZE_OFF);

        //Anchos de cada columna
        int[] anchos = {50,200,80,80,150,80,200};
        for(int i = 0; i < tblCliente.getColumnCount(); i++) {
            tblCliente.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
    }
    void BuscarClientePanel(){
        String titulos[]={"ID","Nombre o Razón Social","ROL","RUT","Dirección","Teléfono","Observación"};
        dtm.setColumnIdentifiers(titulos);
        
        ClsCliente cliente=new ClsCliente();
        busqueda=txtBusqueda.getText();
        if(rbtnCodigo.isSelected()){
            criterio="id";
        }else if(rbtnNombre.isSelected()){
            criterio="nombre";
        }else if(rbtnRol.isSelected()){
            criterio="rol";
        }else if(rbtnRut.isSelected()){
            criterio="rut";
        }
        try{
            rs=cliente.listarClientePorParametro(criterio,busqueda);
            boolean encuentra=false;
            String Datos[]=new String[7];
            int f,i;
            f=dtm.getRowCount();
            if(f>0){
                for(i=0;i<f;i++){
                    dtm.removeRow(0);
                }
            }
            while(rs.next()){
                Datos[0]=rs.getString(1);
                Datos[1]=rs.getString(2);
                Datos[2]=rs.getString(3);
                Datos[3]=rs.getString(4);
                Datos[4]=rs.getString(5);
                Datos[5]=rs.getString(6);
                Datos[6]=rs.getString(7);

                dtm.addRow(Datos);
                encuentra=true;

            }
            if(encuentra=false){
                JOptionPane.showMessageDialog(null, "¡No se encuentra!");
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        tblCliente.setModel(dtm);
    }
    void CantidadTotal(){
        Total= String.valueOf(tblCliente.getRowCount());   
        lblEstado.setText("Se cargaron " + Total + " registros");      
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        txtBusqueda = new javax.swing.JTextField();
        rbtnRut = new javax.swing.JRadioButton();
        rbtnRol = new javax.swing.JRadioButton();
        rbtnNombre = new javax.swing.JRadioButton();
        rbtnCodigo = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        lblEstado = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setTitle("Consultar Clientes");
        getContentPane().setLayout(null);

        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblCliente.setRowHeight(22);
        tblCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClienteMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblCliente);

        getContentPane().add(jScrollPane5);
        jScrollPane5.setBounds(10, 100, 500, 150);

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });
        getContentPane().add(txtBusqueda);
        txtBusqueda.setBounds(30, 61, 380, 20);

        rbtnRut.setText("RUT");
        rbtnRut.setOpaque(false);
        rbtnRut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnRutActionPerformed(evt);
            }
        });
        getContentPane().add(rbtnRut);
        rbtnRut.setBounds(350, 31, 60, 23);

        rbtnRol.setText("ROL");
        rbtnRol.setOpaque(false);
        rbtnRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnRolActionPerformed(evt);
            }
        });
        getContentPane().add(rbtnRol);
        rbtnRol.setBounds(280, 31, 45, 23);

        rbtnNombre.setText("Nombre o Razón Social");
        rbtnNombre.setOpaque(false);
        rbtnNombre.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnNombreStateChanged(evt);
            }
        });
        getContentPane().add(rbtnNombre);
        rbtnNombre.setBounds(120, 31, 150, 23);

        rbtnCodigo.setText("ID Cliente");
        rbtnCodigo.setOpaque(false);
        rbtnCodigo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnCodigoStateChanged(evt);
            }
        });
        getContentPane().add(rbtnCodigo);
        rbtnCodigo.setBounds(30, 31, 90, 23);

        jLabel10.setBackground(new java.awt.Color(255, 204, 102));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Criterios de Búsqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jLabel10.setOpaque(true);
        getContentPane().add(jLabel10);
        jLabel10.setBounds(10, 11, 410, 80);

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/door_in.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir);
        btnSalir.setBounds(430, 10, 80, 80);
        getContentPane().add(lblEstado);
        lblEstado.setBounds(10, 250, 180, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClienteMouseClicked
        int fila;
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        fila = tblCliente.getSelectedRow();

        if (fila == -1){
            JOptionPane.showMessageDialog(null, "Se debe seleccionar un registro");
        }else{
            defaultTableModel = (DefaultTableModel)tblCliente.getModel();
            
            Presentacion.FrmVenta.lblIdCliente.setText((String) defaultTableModel.getValueAt(fila, 0));
            Presentacion.FrmVenta.txtNombreCliente.setText((String) defaultTableModel.getValueAt(fila, 1));

        }

    }//GEN-LAST:event_tblClienteMouseClicked

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        BuscarClientePanel();
        CrearTablaCliente();
        CantidadTotal();
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void rbtnNombreStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnNombreStateChanged
        txtBusqueda.setText("");
    }//GEN-LAST:event_rbtnNombreStateChanged

    private void rbtnCodigoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnCodigoStateChanged
        txtBusqueda.setText("");
    }//GEN-LAST:event_rbtnCodigoStateChanged

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void rbtnRutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnRutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnRutActionPerformed

    private void rbtnRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnRolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnRolActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JRadioButton rbtnCodigo;
    private javax.swing.JRadioButton rbtnNombre;
    private javax.swing.JRadioButton rbtnRol;
    private javax.swing.JRadioButton rbtnRut;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
