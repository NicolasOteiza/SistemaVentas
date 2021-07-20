/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Consultas;

import Conexion.ClsConexion;
import Entidad.*;
import Negocio.*;
import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class FrmVentasMensuales extends javax.swing.JInternalFrame {

private Connection connection=new ClsConexion().getConection();
    
static ResultSet rs=null;
    DefaultTableModel dtm=new DefaultTableModel();
    DefaultTableModel dtm1=new DefaultTableModel();
    String id[]=new String[50];
    static int intContador;
    String documento,criterio,busqueda,Total;
    boolean valor=true;
    int n=0;
    
    public FrmVentasMensuales() {
        initComponents();


        //---------------------ANCHO Y ALTO DEL FORM----------------------
        this.setSize(744, 399);

        //---------------------FECHA ACTUAL-------------------------------
//        Date date=new Date();
//        String format=new String("dd/MM/yyyy");
//        SimpleDateFormat formato=new SimpleDateFormat(format);
//        dcFechaini.setDate(date);
//        dcFechafin.setDate(date);
        
        BuscarVenta();
        CrearTabla(); 
        CantidadTotal();
        VentasTotal();

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
                    if(column==1 ||column==2){
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
        int[] anchos = {120,120,120};
        for(int i = 0; i < tblVenta.getColumnCount(); i++) {
            tblVenta.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        //Ocultar columnas
        //ocultarColumnas(tblVenta,new int[]{3});

    }
  void BuscarVenta(){
        String titulos[]={"Mes","Total","Porcentaje"};
        String fecha_ini,fecha_fin;
        dtm.setColumnIdentifiers(titulos);
        
        ClsVenta venta=new ClsVenta();

        int mes_ini=(mcMes_ini.getMonth()+1);
        int año_ini=(ycAño_ini.getYear());
        int mes_fin=(mcMes_fin.getMonth()+1);
        int año_fin=(ycAño_fin.getYear());

        if(mes_ini<10){
            fecha_ini=año_ini+"-"+"0"+mes_ini;
        }else{
            fecha_ini=año_ini+"-"+mes_ini;
        }
        if(mes_fin<10){
            fecha_fin=año_fin+"-"+"0"+mes_fin;
        }else{
            fecha_fin=año_fin+"-"+mes_fin;
        }
        
        try{
            rs=venta.listarVentaMensual("consultar",fecha_ini,fecha_fin);
            boolean encuentra=false;
            String Datos[]=new String[3];
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
      
   
  
    void CantidadTotal(){
        Total= String.valueOf(tblVenta.getRowCount());   
        lblEstado.setText("Se cargaron " + Total + " registros");      
    }

    private void ocultarColumnas(JTable tbl, int columna[]){
        for(int i=0;i<columna.length;i++)
        {
             tbl.getColumnModel().getColumn(columna[i]).setMaxWidth(0);
             tbl.getColumnModel().getColumn(columna[i]).setMinWidth(0);
             tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMaxWidth(0);
             tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMinWidth(0);
        }
    }
    void VentasTotal(){
        Integer Total_venta=0;    
        int fila = 0;
        fila = dtm.getRowCount();
        for (int f=0; f<fila; f++){
            Total_venta += Integer.parseInt(String.valueOf(tblVenta.getModel().getValueAt(f, 1)));            
        }
        txtTotalVenta.setText(String.valueOf(Truncar(Total_venta,0)));
    }
    
    public int Truncar(int nD, int nDec)
    {
      if(nD > 0)
        nD = Integer.parseInt(Double.toString(Math.floor(nD * Math.pow(10,nDec))/Math.pow(10,nDec)));
      else
        nD = Integer.parseInt(Double.toString(Math.ceil(nD * Math.pow(10,nDec))/Math.pow(10,nDec)));

      return nD;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane5 = new javax.swing.JScrollPane();
        tblVenta = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        mcMes_ini = new com.toedter.calendar.JMonthChooser();
        ycAño_ini = new com.toedter.calendar.JYearChooser();
        mcMes_fin = new com.toedter.calendar.JMonthChooser();
        ycAño_fin = new com.toedter.calendar.JYearChooser();
        lblEstado = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTotalVenta = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setTitle("Estadística Mensual");
        getContentPane().setLayout(null);

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
        jScrollPane5.setBounds(10, 120, 710, 200);

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione un rango de fechas para realizar la busqueda"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("DESDE:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 70, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("HASTA:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 70, 20));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Buscar_32.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 28, 110, 50));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/printer.png"))); // NOI18N
        jButton2.setText("Imprimir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 28, 110, 50));

        mcMes_ini.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.add(mcMes_ini, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 110, 30));
        jPanel1.add(ycAño_ini, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 60, 30));

        mcMes_fin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.add(mcMes_fin, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 110, 30));
        jPanel1.add(ycAño_fin, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 60, 30));

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 10, 710, 100);
        getContentPane().add(lblEstado);
        lblEstado.setBounds(10, 320, 230, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("TOTAL VENTAS:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(530, 330, 100, 30);

        txtTotalVenta.setBackground(new java.awt.Color(254, 254, 224));
        txtTotalVenta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTotalVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtTotalVenta);
        txtTotalVenta.setBounds(630, 330, 90, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVentaMouseClicked

    }//GEN-LAST:event_tblVentaMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        BuscarVenta();
        CrearTabla();
        CantidadTotal();
        VentasTotal();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Map p=new HashMap();
        String fecha_ini,fecha_fin;
        int mes_ini=(mcMes_ini.getMonth()+1);
        int año_ini=(ycAño_ini.getYear());
        int mes_fin=(mcMes_fin.getMonth()+1);
        int año_fin=(ycAño_fin.getYear());
        
        if(mes_ini<10){
            fecha_ini=año_ini+"-"+"0"+mes_ini;
        }else{
            fecha_ini=año_ini+"-"+mes_ini;
        }
        if(mes_fin<10){
            fecha_fin=año_fin+"-"+"0"+mes_fin;
        }else{
            fecha_fin=año_fin+"-"+mes_fin;
        }
        p.put("criterio" ,"consultar");    
        p.put("fecha_ini" ,fecha_ini);        
        p.put("fecha_fin" ,fecha_fin);
       
        //JOptionPane.showMessageDialog(rootPane, fecha_ini+" "+fecha_fin);
        JasperReport report;
        JasperPrint print;
        try{

            report=JasperCompileManager.compileReport(new File("").getAbsolutePath()+ "/src/Reportes/RptVentasMensual2.jrxml");
            print=JasperFillManager.fillReport(report, p,connection);
            JasperViewer view=new JasperViewer(print,false);
            view.setTitle("Reporte General de Ventas Mensuales");
            view.setVisible(true);
        }catch(JRException e){
            e.printStackTrace();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblEstado;
    private com.toedter.calendar.JMonthChooser mcMes_fin;
    private com.toedter.calendar.JMonthChooser mcMes_ini;
    private javax.swing.JTable tblVenta;
    private javax.swing.JTextField txtTotalVenta;
    private com.toedter.calendar.JYearChooser ycAño_fin;
    private com.toedter.calendar.JYearChooser ycAño_ini;
    // End of variables declaration//GEN-END:variables
}
