/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Conexion.ClsConexion;
import Entidad.ClsEntidadProducto;
import Negocio.ClsDetalleVenta;
import Negocio.ClsProducto;
import Negocio.ClsVenta;
import java.awt.Color;
import java.awt.Component;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Edgar
 */
public class FrmCaja extends javax.swing.JInternalFrame {
    static ResultSet rs=null;
    DefaultTableModel dtm=new DefaultTableModel();
    Date fecha_ini,fecha_fin;
    
    public FrmCaja() {
        initComponents();
        //---------------------ANCHO Y ALTO DEL FORM----------------------
        this.setSize(805, 299);

        //---------------------FECHA ACTUAL-------------------------------
        Date date=new Date();
        String format=new String("dd/MM/yyyy");
        SimpleDateFormat formato=new SimpleDateFormat(format);
        dcFecha.setDate(date);

        
        BuscarIngresos();
        CrearTabla(); 
        VentasTotal();
        CantidadVenta();
        GananciaVenta();
    }

//-----------------------------------------------------------------------------------------------
//--------------------------------------METODOS--------------------------------------------------
//-----------------------------------------------------------------------------------------------

    void CrearTabla(){
   //--------------------PRESENTACION DE JTABLE----------------------
      
        TableCellRenderer render = new DefaultTableCellRenderer() { 

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) { 
                //aqui obtengo el render de la calse superior 
                JLabel l = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
                //Determinar Alineaciones   
                    if(column==0 || column==2 || column==3 || column==4 || column==5){
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
        for (int i=0;i<tblVenta.getColumnCount();i++){
            tblVenta.getColumnModel().getColumn(i).setCellRenderer(render);
        }
      
        //Activar ScrollBar
        tblVenta.setAutoResizeMode(tblVenta.AUTO_RESIZE_OFF);

        //Anchos de cada columna
        int[] anchos = {60,185,70,70,70,70};
        for(int i = 0; i < tblVenta.getColumnCount(); i++) {
            tblVenta.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

    }
  void BuscarIngresos(){
        String titulos[]={"Cantidad","Producto","Precio","Importe","Ganancia","Fecha"};
        dtm.setColumnIdentifiers(titulos);
        
        ClsVenta venta=new ClsVenta();

        fecha_ini=dcFecha.getDate();
      

        try{
            rs=venta.listarVentaPorFecha("caja",fecha_ini,fecha_ini,"");
            boolean encuentra=false;
            String Datos[]=new String[6];
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
                dtm.addRow(Datos);
                encuentra=true;

            }
            if(encuentra=false){
                JOptionPane.showMessageDialog(null, "¡No se encuentra!");
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        tblVenta.setModel(dtm);
    }
    void VentasTotal(){
        Double Total_venta=0.0;    
        int fila = 0;
        fila = dtm.getRowCount();
        for (int f=0; f<fila; f++){
            Total_venta += Double.parseDouble(String.valueOf(tblVenta.getModel().getValueAt(f, 3)));            
        }
        txtIngreso.setText(String.valueOf(Total_venta));
        txtTotal.setText(String.valueOf(Total_venta));
    }
    void CantidadVenta(){
        Double cantidad=0.0;    
        int fila = 0;
        fila = dtm.getRowCount();
        for (int f=0; f<fila; f++){
            cantidad += Double.parseDouble(String.valueOf(tblVenta.getModel().getValueAt(f, 0)));            
        }
        txtCantidad.setText(String.valueOf(cantidad));
    }
    void GananciaVenta(){
        Double ganancia=0.0;    
        int fila = 0;
        fila = dtm.getRowCount();
        for (int f=0; f<fila; f++){
            ganancia += Double.parseDouble(String.valueOf(tblVenta.getModel().getValueAt(f, 4)));            
        }

        txtGanancia.setText(String.valueOf(Truncar(ganancia,2)));
    }
    public double Truncar(double nD, int nDec)
    {
      if(nD > 0)
        nD = Math.floor(nD * Math.pow(10,nDec))/Math.pow(10,nDec);
      else
        nD = Math.ceil(nD * Math.pow(10,nDec))/Math.pow(10,nDec);

      return nD;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        dcFecha = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblVenta = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtIngreso = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtGanancia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setTitle("Resumen de caja");
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("FECHA:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(60, 27, 60, 15);

        dcFecha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(dcFecha);
        dcFecha.setBounds(120, 25, 120, 22);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/calcular_32.png"))); // NOI18N
        jButton1.setText("Calcular Ingresos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(250, 10, 160, 50);

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));

        tblVenta.setModel(new javax.swing.table.DefaultTableModel(
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
        tblVenta.setRowHeight(22);
        tblVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVentaMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblVenta);

        getContentPane().add(jScrollPane5);
        jScrollPane5.setBounds(250, 70, 530, 190);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estadísticas del día", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Ingreso por ventas:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(16, 27, 120, 20);

        txtIngreso.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtIngreso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIngreso.setText("0.0");
        jPanel1.add(txtIngreso);
        txtIngreso.setBounds(140, 20, 70, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Cant. de Productos:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 70, 120, 14);

        txtCantidad.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidad.setText("0");
        jPanel1.add(txtCantidad);
        txtCantidad.setBounds(140, 60, 70, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Ganancia:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(70, 100, 70, 30);

        txtGanancia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtGanancia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGanancia.setText("0.0");
        jPanel1.add(txtGanancia);
        txtGanancia.setBounds(140, 100, 70, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 70, 230, 150);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("TOTAL EN CAJA:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(10, 235, 110, 20);

        txtTotal.setBackground(new java.awt.Color(255, 255, 237));
        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(0, 102, 204));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.setText("0.0");
        getContentPane().add(txtTotal);
        txtTotal.setBounds(120, 230, 110, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        BuscarIngresos();
        CrearTabla();
        VentasTotal();
        CantidadVenta();
        GananciaVenta();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVentaMouseClicked

    }//GEN-LAST:event_tblVentaMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dcFecha;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tblVenta;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtGanancia;
    private javax.swing.JTextField txtIngreso;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
