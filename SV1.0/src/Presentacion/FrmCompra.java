/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Entidad.*;
import Negocio.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class FrmCompra extends javax.swing.JInternalFrame {

String Total;
    String strCodigo;
    String accion;
    String numCompra;
    int registros;
    String id[]=new String[50];
    //String Datos[]=new String[50];

    static int intContador;
    public String IdEmpleado,NombreEmpleado;
    int idventa,nidventa;
    //-----------------------------------------------
    public String codigo;
    static Connection conn=null;
    
    static ResultSet rs=null;
    DefaultTableModel dtm=new DefaultTableModel();
    DefaultTableModel dtmDetalle = new DefaultTableModel();

    String criterio,busqueda;
    
    public FrmCompra() {
        initComponents();
        //---------------------FECHA ACTUAL-------------------------------
        Date date=new Date();
        String format=new String("dd/MM/yyyy");
        SimpleDateFormat formato=new SimpleDateFormat(format);

        txtFecha.setDate(date);
        //---------------------GENERA NUMERO DE VENTA---------------------
        numCompra=generaNumCompra();
        txtNumero.setText(numCompra);
        //---------------------ANCHO Y ALTO DEL FORM----------------------
        this.setSize(939, 506);
        cargarComboTipoDocumento();
       
        lblIdProducto.setVisible(false);
        lblIdProveedor.setVisible(false);
        txtDescripcionProducto.setVisible(false);
        mirar();
        //--------------------JTABLE - DETALLEPRODUCTO--------------------
        
        String titulos[] = {"ID","CODIGO", "PRODUCTO","DESCRIPCION", "CANT.", "PRECIO", "TOTAL"};
        dtmDetalle.setColumnIdentifiers(titulos);
        tblDetalleProducto.setModel(dtmDetalle);
        CrearTablaDetalleProducto();
    }

//-----------------------------------------------------------------------------------------------
//--------------------------------------METODOS--------------------------------------------------
//-----------------------------------------------------------------------------------------------
  public String generaNumCompra(){

        ClsCompra oCompra=new ClsCompra(); 
        try {

            rs= oCompra.obtenerUltimoIdCompra();
            while (rs.next()) {
            if (rs.getString(1) != null) {
                Scanner s = new Scanner(rs.getString(1));
                int c = s.useDelimiter("C").nextInt() + 1;

                if (c < 10) {
                    return "C0000" + c;
                }
                if (c < 100) {
                    return "C000" + c;
                }
                if (c < 1000) {
                    return "C00" + c;
                }
                if (c < 10000) {
                    return "C0" + c;
                } else {
                    return "C" + c;
                }
            }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return "C00001";
              
  }
  
   void CrearTablaDetalleProducto(){
   //--------------------PRESENTACION DE JTABLE----------------------
      
        TableCellRenderer render = new DefaultTableCellRenderer() { 

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) { 
                //aqui obtengo el render de la calse superior 
                JLabel l = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
                //Determinar Alineaciones   
                    if(column==0 || column==1 || column==4 || column==5 || column==6){
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
        for (int i=0;i<tblDetalleProducto.getColumnCount();i++){
            tblDetalleProducto.getColumnModel().getColumn(i).setCellRenderer(render);
        }
      
        //Activar ScrollBar
        tblDetalleProducto.setAutoResizeMode(tblDetalleProducto.AUTO_RESIZE_OFF);

        //Anchos de cada columna
        int[] anchos = {50,100,180,247,70,70,70};
        for(int i = 0; i < tblDetalleProducto.getColumnCount(); i++) {
            tblDetalleProducto.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
   }

   void limpiarCampos(){

       txtSubTotal.setText("0");
       txtIVA.setText("0");
       txtTotalCompra.setText("0");
       
       lblIdProducto.setText("");
       txtCodigoProducto.setText("");
       txtNombreProducto.setText("");
       txtStockProducto.setText("");
       txtPrecioProducto.setText("");
       txtCantidadProducto.setText("");
       txtTotalProducto.setText("");
       txtCodigoProducto.requestFocus();
   }
       
   void mirar(){
       btnNuevo.setEnabled(true);
       btnGuardar.setEnabled(false);
       btnCancelar.setEnabled(false);
       btnSalir.setEnabled(true);
        

       cboTipoDocumento.setEnabled(false);
       txtCodigoProducto.setEnabled(false);
       txtCantidadProducto.setEnabled(false);
       txtFecha.setEnabled(false);
       txtNumero.setEnabled(false);
       
       btnBuscarProveedor.setEnabled(false);
       btnBuscarProducto.setEnabled(false);
       btnAgregarProducto.setEnabled(false);
       btnEliminarProducto.setEnabled(false);
       btnLimpiarTabla.setEnabled(false);
       chkCambiarNumero.setEnabled(false);
       chkCambiarNumero.setSelected(false);
       
       txtSubTotal.setText("0");
       txtIVA.setText("0");
       txtTotalCompra.setText("0");
       lblIdProducto.setText("");
       txtCodigoProducto.setText("");
       txtNombreProducto.setText("");
       txtStockProducto.setText("");
       txtPrecioProducto.setText("");
       txtCantidadProducto.setText("");
       txtTotalProducto.setText("");
       txtCodigoProducto.requestFocus();
       

   }
   
   void modificar(){

       btnNuevo.setEnabled(false);

       btnGuardar.setEnabled(true);
       btnCancelar.setEnabled(true);
       btnSalir.setEnabled(false);
        
       cboTipoDocumento.setEnabled(true);
       txtCodigoProducto.setEnabled(true);
       txtCantidadProducto.setEnabled(true);
       txtFecha.setEnabled(true);
       
       btnBuscarProveedor.setEnabled(true);
       btnBuscarProducto.setEnabled(true);
       btnAgregarProducto.setEnabled(true);
       btnEliminarProducto.setEnabled(true);
       btnLimpiarTabla.setEnabled(true);
       chkCambiarNumero.setEnabled(true);
       
       txtCodigoProducto.requestFocus();
   }
//   void cargarComboTipoDocumento(){
//       ClsTipoDocumento documentos=new ClsTipoDocumento();
//       DefaultComboBoxModel DefaultComboBoxModel=new DefaultComboBoxModel();
//       DefaultComboBoxModel.removeAllElements();
//             
//        try{
//            rs = documentos.listarTipoDocumentoPorParametro("descripcion", "");
//            boolean encuentra = false;
//            String Datos[] = new String [2];
//            
//
//            while (rs.next()){
//                id[intContador]=(String) rs.getString(1);
//                lblIdDoc.setText(rs.getString(1));
//                Datos[0] = (String) rs.getString(1);
//                Datos[1] = (String) rs.getString(2);
//                DefaultComboBoxModel.addElement((String) rs.getString(2));
//                encuentra = true;
//                intContador++;
//            }      
//           cboTipoDocumento.setModel(DefaultComboBoxModel);
//
//            if(encuentra=false){
//                JOptionPane.showMessageDialog(null, "No se encuentra");
//            }      
//            
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }  
//    }
    void cargarComboTipoDocumento(){
       ClsTipoDocumento tipodocumento=new ClsTipoDocumento();
       ArrayList<ClsEntidadTipoDocumento> tipodocumentos=tipodocumento.listarTipoDocumento();
       Iterator iterator=tipodocumentos.iterator();
       DefaultComboBoxModel DefaultComboBoxModel=new DefaultComboBoxModel();
       DefaultComboBoxModel.removeAllElements();
       
       cboTipoDocumento.removeAll();
       String fila[]=new String[2];
       intContador=0;
       
       while(iterator.hasNext()){
           ClsEntidadTipoDocumento TipoDocumento = new ClsEntidadTipoDocumento();
           TipoDocumento=(ClsEntidadTipoDocumento) iterator.next();
           id[intContador]=TipoDocumento.getStrIdTipoDocumento();
           fila[0]=TipoDocumento.getStrIdTipoDocumento();
           fila[1]=TipoDocumento.getStrDescripcionTipoDocumento();
           DefaultComboBoxModel.addElement(TipoDocumento.getStrDescripcionTipoDocumento());
           intContador++;              
       }
       cboTipoDocumento.setModel(DefaultComboBoxModel);
    }
    void BuscarProductoPorCodigo(){
        String busqueda=null;
        busqueda=txtCodigoProducto.getText(); 
            try{
                ClsProducto oProducto=new ClsProducto();
                
                rs= oProducto.listarProductoActivoPorParametro("codigo",busqueda);
                while (rs.next()) {
                    if(rs.getString(2).equals(busqueda)) {
        
                            lblIdProducto.setText(rs.getString(1));
                            txtNombreProducto.setText(rs.getString(3));
                            txtDescripcionProducto.setText(rs.getString(4));
                            //DescripcionProducto=rs.getString(4);
                            txtStockProducto.setText(rs.getString(5));
                            txtPrecioProducto.setText(rs.getString(7));             
                    }
                   break;
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,ex.getMessage());
                System.out.println(ex.getMessage());
            }
    
    }
void BuscarProveedorPorDefecto(){
        try{
            ClsProveedor oProveedor=new ClsProveedor();
            rs= oProveedor.listarProveedorPorParametro("id","1");
            while (rs.next()) {
                lblIdProveedor.setText(rs.getString(1));
                txtNombreProveedor.setText(rs.getString(2));             
            break;
            }

         }catch(Exception ex){
            JOptionPane.showMessageDialog(this,ex.getMessage());
            System.out.println(ex.getMessage());
         }
    
    }
    
void CalcularTotal(){
            
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("####.##",simbolos);
        double precio_prod=0,cant_prod=0,total_prod=0;
        precio_prod=Double.parseDouble(txtPrecioProducto.getText());
        cant_prod=Double.parseDouble(txtCantidadProducto.getText());
        total_prod=precio_prod*cant_prod;
        txtTotalProducto.setText(String.valueOf(formateador.format(total_prod)));
}




    

    public int recorrer(int id){
        int fila = 0,valor=-1;
        
        fila = tblDetalleProducto.getRowCount();
        
        for (int f = 0; f < fila;f++) {
            if(Integer.parseInt(String.valueOf(dtmDetalle.getValueAt(f, 0)))==id){

                valor=f;
                //JOptionPane.showMessageDialog(null, "te encontre!");
                break;
                
                
            }else{
                //JOptionPane.showMessageDialog(null, "no estas!");
                valor= -1;
            }          
              
        }
        return valor;
    } 

    void agregardatos(int item, String cod, String nom,String descrip,int cant,int pre,int tot){
        
        int p=recorrer(item);
        int n_cant,n_total;
        if (p>-1){
                               
            n_cant=Integer.parseInt(String.valueOf(tblDetalleProducto.getModel().getValueAt(p,4)))+cant;
            tblDetalleProducto.setValueAt(n_cant,p,4);
                       
            n_total=Integer.parseInt(String.valueOf(tblDetalleProducto.getModel().getValueAt(p,4)))*Integer.parseInt(String.valueOf(tblDetalleProducto.getModel().getValueAt(p,5)));
            tblDetalleProducto.setValueAt(n_total,p,6);
            
                            
        }else{
            String Datos[] = {String.valueOf(item),cod,nom,descrip,String.valueOf(cant),String.valueOf(pre),String.valueOf(tot)};
            dtmDetalle.addRow(Datos);
        }
        tblDetalleProducto.setModel(dtmDetalle);
    }
    void CalcularSubTotal(){
        double subtotal=0;
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("####.##",simbolos);        
        subtotal=Double.parseDouble(txtTotalCompra.getText())/1.18;
        txtSubTotal.setText(String.valueOf(formateador.format(subtotal)));
    }
    void CalcularIVA(){
        double iva=0;
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("####.##",simbolos);       
        iva=Double.parseDouble(txtSubTotal.getText())*0.18;
        txtIVA.setText(String.valueOf(formateador.format(iva)));
    }
    void CalcularTotal_Compra(){
        int fila = 0;
        int valorCompra = 0;
        fila = dtmDetalle.getRowCount();
        for (int f=0; f<fila; f++){
            valorCompra += Integer.parseInt(String.valueOf(tblDetalleProducto.getModel().getValueAt(f, 6)));            
        }
        txtTotalCompra.setText(String.valueOf(valorCompra));
    }
    void limpiarTabla(){
        try{      
	int filas=tblDetalleProducto.getRowCount();
            for (int i = 0;filas>i; i++) {
                dtmDetalle.removeRow(0);
            }
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
    void obtenerUltimoIdCompra(){
        try{
        ClsCompra oCompra=new ClsCompra(); 
        rs= oCompra.obtenerUltimoIdCompra();
            while (rs.next()) {
                idventa = rs.getInt(1);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }

    void guardarDetalle(){
        obtenerUltimoIdCompra();
        ClsDetalleCompra detallecompras=new ClsDetalleCompra();
        ClsEntidadDetalleCompra detallecompra=new ClsEntidadDetalleCompra();
        ClsProducto productos=new ClsProducto();
        String codigo,strId;
        ClsEntidadProducto producto=new ClsEntidadProducto();
        int fila=0;
        int cant = 0,ncant,stock;   
        fila =tblDetalleProducto.getRowCount();
        for (int f=0; f<fila; f++){
            detallecompra.setStrIdCompra(String.valueOf(idventa));
            detallecompra.setStrIdProducto(String.valueOf(tblDetalleProducto.getModel().getValueAt(f, 0)));
            detallecompra.setStrCantidadDet(String.valueOf(tblDetalleProducto.getModel().getValueAt(f, 4)));
            detallecompra.setStrPrecioDet(String.valueOf(tblDetalleProducto.getModel().getValueAt(f, 5)));
            detallecompra.setStrTotalDet(String.valueOf(tblDetalleProducto.getModel().getValueAt(f, 6)));  
            detallecompras.agregarDetalleCompra(detallecompra);
            


            try{
                ClsProducto oProducto=new ClsProducto();
                
                rs= oProducto.listarProductoActivoPorParametro("id",((String) tblDetalleProducto.getValueAt(f, 0)));
                while (rs.next()) {

                            cant=Integer.parseInt(rs.getString(5));
                }
                

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,ex.getMessage());
                System.out.println(ex.getMessage());
            }                   
            
        strId =  ((String) tblDetalleProducto.getValueAt(f, 0));
        ncant=Integer.parseInt(String.valueOf(tblDetalleProducto.getModel().getValueAt(f, 4)));
        stock=cant+ncant;
        producto.setStrStockProducto(String.valueOf(stock));
        productos.actualizarProductoStock(strId, producto);

    }
    }    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        tblDetalleProducto = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txtCantidadProducto = new javax.swing.JTextField();
        btnAgregarProducto = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        txtTotalProducto = new javax.swing.JTextField();
        btnEliminarProducto = new javax.swing.JButton();
        btnLimpiarTabla = new javax.swing.JButton();
        txtNumero = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txtCodigoProducto = new javax.swing.JTextField();
        btnBuscarProducto = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtDescripcionProducto = new javax.swing.JLabel();
        txtStockProducto = new javax.swing.JTextField();
        txtPrecioProducto = new javax.swing.JTextField();
        lblIdProducto = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnBuscarProveedor = new javax.swing.JButton();
        txtNombreProveedor = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cboTipoDocumento = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtFecha = new com.toedter.calendar.JDateChooser();
        lblIdProveedor = new javax.swing.JLabel();
        chkCambiarNumero = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        txtIVA = new javax.swing.JTextField();
        txtTotalCompra = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setTitle("Compras");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(null);

        tblDetalleProducto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblDetalleProducto.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDetalleProducto.setRowHeight(22);
        jScrollPane3.setViewportView(tblDetalleProducto);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(10, 260, 790, 140);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/agregar.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevo.setIconTextGap(0);
        btnNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/guardar_compra.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setIconTextGap(0);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setIconTextGap(0);
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/principal.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setIconTextGap(0);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSalir, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel4);
        jPanel4.setBounds(810, 10, 105, 460);

        jPanel5.setBackground(new java.awt.Color(255, 255, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(249, 205, 87));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setText("CANTIDAD:");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 70, 30));

        txtCantidadProducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCantidadProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidadProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadProductoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadProductoKeyTyped(evt);
            }
        });
        jPanel3.add(txtCantidadProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 60, 30));

        btnAgregarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Agregar_p1.png"))); // NOI18N
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });
        jPanel3.add(btnAgregarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 5, 50, 40));

        jLabel24.setText("TOTAL:");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 50, 30));

        txtTotalProducto.setBackground(new java.awt.Color(204, 255, 204));
        txtTotalProducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTotalProducto.setForeground(new java.awt.Color(0, 102, 204));
        txtTotalProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalProducto.setDisabledTextColor(new java.awt.Color(0, 102, 204));
        txtTotalProducto.setEnabled(false);
        jPanel3.add(txtTotalProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 80, 30));

        btnEliminarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Remove.png"))); // NOI18N
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });
        jPanel3.add(btnEliminarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 5, 50, 40));

        btnLimpiarTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/nuevo1.png"))); // NOI18N
        btnLimpiarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarTablaActionPerformed(evt);
            }
        });
        jPanel3.add(btnLimpiarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 5, 50, 40));

        jPanel5.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 530, 50));

        txtNumero.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNumero.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel5.add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 200, 110, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Nº DE COMPRA");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 180, 110, 20));

        jPanel2.setBackground(new java.awt.Color(249, 205, 87));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Producto"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setText("CÓDIGO:");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 70, -1));

        txtCodigoProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoProductoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoProductoKeyTyped(evt);
            }
        });
        jPanel2.add(txtCodigoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 190, -1));

        btnBuscarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Buscar_p.png"))); // NOI18N
        btnBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductoActionPerformed(evt);
            }
        });
        jPanel2.add(btnBuscarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 40, 35));

        jLabel17.setText("NOMBRE:");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 55, 70, 20));

        txtNombreProducto.setEnabled(false);
        jPanel2.add(txtNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 75, 240, -1));

        jLabel19.setText("STOCK:");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, -1, -1));
        jPanel2.add(txtDescripcionProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 30, 20));

        txtStockProducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtStockProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtStockProducto.setEnabled(false);
        jPanel2.add(txtStockProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 90, -1));

        txtPrecioProducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPrecioProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioProducto.setEnabled(false);
        jPanel2.add(txtPrecioProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 90, -1));
        jPanel2.add(lblIdProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 20, 20));

        jLabel23.setText("COSTO:");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 50, -1));

        jPanel5.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 85, 530, 100));

        jPanel1.setBackground(new java.awt.Color(249, 205, 87));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la Compra"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("PROVEEDOR:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 15, 80, 20));

        btnBuscarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Buscar_p.png"))); // NOI18N
        btnBuscarProveedor.setAlignmentY(1.0F);
        btnBuscarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProveedorActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 25, 40, 35));

        txtNombreProveedor.setEnabled(false);
        jPanel1.add(txtNombreProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 35, 190, -1));

        jLabel13.setText("DOCUMENTO:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 15, 100, -1));

        cboTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cboTipoDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 35, 130, 20));

        jLabel2.setText("FECHA:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 15, 80, 20));
        jPanel1.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 35, 100, -1));
        jPanel1.add(lblIdProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 20, 20));

        jPanel5.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 530, 70));

        chkCambiarNumero.setText("Cambiar Número");
        chkCambiarNumero.setOpaque(false);
        chkCambiarNumero.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkCambiarNumeroStateChanged(evt);
            }
        });
        jPanel5.add(chkCambiarNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 220, 110, -1));

        getContentPane().add(jPanel5);
        jPanel5.setBounds(10, 10, 790, 250);

        jPanel6.setBackground(new java.awt.Color(255, 246, 227));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("SUB TOTAL");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 5, 100, 20));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("IVA");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 5, 100, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("TOTAL A PAGAR");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 5, 150, 20));

        txtSubTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSubTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSubTotal.setEnabled(false);
        jPanel6.add(txtSubTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 28, 100, 30));

        txtIVA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtIVA.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtIVA.setEnabled(false);
        jPanel6.add(txtIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 28, 100, 30));

        txtTotalCompra.setBackground(new java.awt.Color(0, 0, 0));
        txtTotalCompra.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTotalCompra.setForeground(new java.awt.Color(0, 255, 102));
        txtTotalCompra.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalCompra.setDisabledTextColor(new java.awt.Color(0, 255, 102));
        txtTotalCompra.setEnabled(false);
        jPanel6.add(txtTotalCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 28, 150, 30));

        getContentPane().add(jPanel6);
        jPanel6.setBounds(10, 405, 790, 65);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        accion="Nuevo";
        modificar();
        limpiarCampos();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        mirar();
        limpiarTabla();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtCantidadProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadProductoKeyReleased
        CalcularTotal();
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) btnAgregarProducto.requestFocus(); 
    }//GEN-LAST:event_txtCantidadProductoKeyReleased

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
    double stock,cant;
  
    if (!txtCantidadProducto.getText().equals("")){
        if(txtCantidadProducto.getText().equals("")){
            txtCantidadProducto.setText("0");
            cant=Integer.parseInt(txtCantidadProducto.getText());
        }else{
        cant=Integer.parseInt(txtCantidadProducto.getText());
        }
            
        if(cant>0){

            int d1=Integer.parseInt(lblIdProducto.getText());
            String d2=txtCodigoProducto.getText();
            String d3=txtNombreProducto.getText();
            String d4=txtDescripcionProducto.getText();
            int d5=Integer.parseInt(txtCantidadProducto.getText());
            int d6=Integer.parseInt(txtPrecioProducto.getText());
            int d7=Integer.parseInt(txtTotalProducto.getText());
            agregardatos(d1,d2,d3,d4,d5,d6,d7);

            CalcularTotal_Compra();
            CalcularSubTotal();
            CalcularIVA();

            txtCantidadProducto.setText("");
            txtTotalProducto.setText("");
            txtCodigoProducto.requestFocus();  
            
        }else{
            JOptionPane.showMessageDialog(null, "Ingrese Cantidad mayor a 0");
            txtCantidadProducto.requestFocus();   
        }
     
    }else{
        JOptionPane.showMessageDialog(null, "Ingrese cantidad");
        txtCantidadProducto.requestFocus();   
    }

       
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        int fila = tblDetalleProducto.getSelectedRow();
        if (fila > 0) {
            dtmDetalle.removeRow(fila);
            CalcularTotal_Compra();
            CalcularSubTotal();
            CalcularIVA();
        } else if (fila == 0) {
            dtmDetalle.removeRow(fila);
            txtSubTotal.setText("0.0");
            txtIVA.setText("0.0");
            txtTotalCompra.setText("0.0");

            CalcularTotal_Compra();
            CalcularSubTotal();
            CalcularIVA();
        }

        CalcularTotal_Compra();
        CalcularSubTotal();
        CalcularIVA();
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnLimpiarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarTablaActionPerformed
        limpiarTabla();
        txtSubTotal.setText("0");
        txtIVA.setText("0");
        txtTotalCompra.setText("0");
    }//GEN-LAST:event_btnLimpiarTablaActionPerformed

    private void txtCodigoProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProductoKeyReleased
        BuscarProductoPorCodigo(); 
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) txtCantidadProducto.requestFocus(); 
    }//GEN-LAST:event_txtCodigoProductoKeyReleased

    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
    Consultas.FrmBuscarProducto_Compra producto= new Consultas.FrmBuscarProducto_Compra();
    Presentacion.FrmPrincipal.Escritorio.add(producto);
    producto.toFront();
    producto.setVisible(true);
    }//GEN-LAST:event_btnBuscarProductoActionPerformed

    private void btnBuscarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProveedorActionPerformed
    Consultas.FrmBuscarProveedor_Compra proveedor= new Consultas.FrmBuscarProveedor_Compra();
    Presentacion.FrmPrincipal.Escritorio.add(proveedor);
    proveedor.toFront();
    proveedor.setVisible(true);
    }//GEN-LAST:event_btnBuscarProveedorActionPerformed

    private void chkCambiarNumeroStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkCambiarNumeroStateChanged
        if (chkCambiarNumero.isSelected()){
            txtNumero.setText("");
            txtNumero.setEnabled(true);
        }else{
            txtNumero.setEnabled(false);
            numCompra=generaNumCompra();
            txtNumero.setText(numCompra);
        }
    }//GEN-LAST:event_chkCambiarNumeroStateChanged

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        int result = JOptionPane.showConfirmDialog(this, "¿Desea Generar la Compra?", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            if(accion.equals("Nuevo")){
                ClsCompra compras=new ClsCompra();
                ClsEntidadCompra venta=new ClsEntidadCompra();
                venta.setStrIdTipoDocumento(id[cboTipoDocumento.getSelectedIndex()]);
                venta.setStrIdProveedor(lblIdProveedor.getText());
                venta.setStrIdEmpleado(IdEmpleado);
                venta.setStrNumeroCompra(txtNumero.getText());
                venta.setStrFechaCompra(txtFecha.getDate());
                venta.setStrSubTotalCompra(txtSubTotal.getText());
                venta.setStrIvaCompra(txtIVA.getText());
                venta.setStrTotalCompra(txtTotalCompra.getText());
                venta.setStrEstadoCompra("NORMAL");
                compras.agregarCompra(venta);
                guardarDetalle();
            }
            mirar();
            limpiarTabla();
            numCompra=generaNumCompra();
            txtNumero.setText(numCompra);
            BuscarProveedorPorDefecto();
        }
        if (result == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Compra Anulada!");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        BuscarProveedorPorDefecto();
        cargarComboTipoDocumento();

    }//GEN-LAST:event_formComponentShown

    private void txtCodigoProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProductoKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_txtCodigoProductoKeyTyped

    private void txtCantidadProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadProductoKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_txtCantidadProductoKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnBuscarProveedor;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiarTabla;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cboTipoDocumento;
    private javax.swing.JCheckBox chkCambiarNumero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JLabel lblIdProducto;
    public static javax.swing.JLabel lblIdProveedor;
    private javax.swing.JTable tblDetalleProducto;
    private javax.swing.JTextField txtCantidadProducto;
    public static javax.swing.JTextField txtCodigoProducto;
    public static javax.swing.JLabel txtDescripcionProducto;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtIVA;
    public static javax.swing.JTextField txtNombreProducto;
    public static javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JTextField txtNumero;
    public static javax.swing.JTextField txtPrecioProducto;
    public static javax.swing.JTextField txtStockProducto;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotalCompra;
    private javax.swing.JTextField txtTotalProducto;
    // End of variables declaration//GEN-END:variables
}
