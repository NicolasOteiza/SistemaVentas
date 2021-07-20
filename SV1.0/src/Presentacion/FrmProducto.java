/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Conexion.ClsConexion;
import Entidad.*;
import Negocio.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

//--------------CODIGO DE BARRAS------------
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.*;   
import net.sourceforge.barbecue.BarcodeFactory;   
import net.sourceforge.barbecue.Barcode;   
import net.sourceforge.barbecue.BarcodeException;   
import net.sourceforge.barbecue.BarcodeImageHandler;   
import java.awt.image.BufferedImage;   
import java.awt.*;   
import java.awt.event.*;   
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;   
import java.io.IOException;   
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import net.sourceforge.barbecue.output.OutputException;

public class FrmProducto extends javax.swing.JInternalFrame {
    private Connection connection=new ClsConexion().getConection();
    String Total;
    String strCodigo;
    String accion;
    String imagen="";
    int registros;
    String id[]=new String[50];
    static int intContador;
    public String codigo;
    static Connection conn=null;
    static ResultSet rs=null;
    DefaultTableModel dtm=new DefaultTableModel();
    String criterio,busqueda;
    
    public FrmProducto() {
        initComponents();
        tabProducto.setIconAt(tabProducto.indexOfComponent(pBuscar), new ImageIcon("src/iconos/busca_p1.png"));
        tabProducto.setIconAt(tabProducto.indexOfComponent(pNuevo), new ImageIcon("src/iconos/nuevo1.png"));
        cargarComboCategoria();
        buttonGroup1.add(rbtnCodigo);
        buttonGroup1.add(rbtnNombre);
        buttonGroup1.add(rbtnDescripcion);
        buttonGroup1.add(rbtnCategoria);
        buttonGroup2.add(rbtnActivo);
        buttonGroup2.add(rbtnInactivo);
        
        mirar();
        actualizarTabla();
        //---------------------ANCHO Y ALTO DEL FORM----------------------
        this.setSize(888, 558);
        CrearTabla();
        CantidadTotal();
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
        
                    if(column==0 || column==1 || column==4 || column==5 || column==6 || column==7 || column==8 || column==9 || column==10){
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
        for (int i=0;i<tblProducto.getColumnCount();i++){
            tblProducto.getColumnModel().getColumn(i).setCellRenderer(render);
        }
      
        //Activar ScrollBar
        tblProducto.setAutoResizeMode(tblProducto.AUTO_RESIZE_OFF);

        //Anchos de cada columna
        int[] anchos = {40,100,150,200,60,60,60,60,60,80,100,80};
        for(int i = 0; i < tblProducto.getColumnCount(); i++) {
            tblProducto.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
   }
   void CantidadTotal(){
        Total= String.valueOf(tblProducto.getRowCount());   
        lblEstado.setText("Se cargaron " + Total + " registros");      
   }
   void limpiarCampos(){
       txtCodigo.setText("");
       txtCodigoBar.setText("");
       txtNombre.setText("");
       txtDescripcion.setText("");
       txtStock.setText("");
       txtStockMin.setText("");
       txtPrecioCosto.setText("");
       txtPrecioVenta.setText("");
       lblImagen.setIcon(null);
       txtUtilidad.setText("");
       txtCodigoBar.requestFocus();
       rbtnActivo.setSelected(true);
       rbtnInactivo.setSelected(false);
//       txtStock.setText("0");
//       txtStockMin.setText("0");
       txtPrecioCosto.setText("0");
       txtPrecioVenta.setText("0");
       txtUtilidad.setText("0");
       
       rbtnCodigo.setSelected(false);
       rbtnNombre.setSelected(false);
       rbtnDescripcion.setSelected(false);
       rbtnCategoria.setSelected(false);
       txtBusqueda.setText("");
       limpiarList();
   }
       
   void mirar(){
       tblProducto.setEnabled(true);
       btnNuevo.setEnabled(true);
       btnModificar.setEnabled(true);
       btnGuardar.setEnabled(false);
       btnCancelar.setEnabled(false);
       btnSalir1.setEnabled(true);
       btnSeleccionarImagen.setEnabled(false);
        
       txtCodigoBar.setEnabled(false);
       txtNombre.setEnabled(false);
       txtDescripcion.setEnabled(false);
       txtStock.setEnabled(false);
       txtStockMin.setEnabled(false);
       txtPrecioCosto.setEnabled(false);
       txtPrecioVenta.setEnabled(false);
       cboCategoria.setEnabled(false);
       rbtnActivo.setEnabled(false);
       rbtnInactivo.setEnabled(false);
       
       btnActualizar.setEnabled(false);
       lstCodigos.setEnabled(false);
       cboTipoCodificacion.setEnabled(false);
       btnGenerar.setEnabled(false);
       imagen="";
   
   }
   
   void modificar(){
       tblProducto.setEnabled(false);
       btnNuevo.setEnabled(false);
       btnModificar.setEnabled(false);
       btnGuardar.setEnabled(true);
       btnCancelar.setEnabled(true);
       btnSeleccionarImagen.setEnabled(true);
       btnSalir1.setEnabled(false);
        
       txtCodigoBar.setEnabled(true);
       txtNombre.setEnabled(true);
       txtDescripcion.setEnabled(true);
       txtStock.setEnabled(true);
       txtStockMin.setEnabled(true);
       txtPrecioCosto.setEnabled(true);
       txtPrecioVenta.setEnabled(true);
       cboCategoria.setEnabled(true);
       rbtnActivo.setEnabled(true);
       rbtnInactivo.setEnabled(true);
       
       btnActualizar.setEnabled(true);
       lstCodigos.setEnabled(true);
       cboTipoCodificacion.setEnabled(true);
       btnGenerar.setEnabled(true);
       txtCodigoBar.requestFocus();
   }
  void cargarComboCategoria(){
  ClsCategoria tipodocumento=new ClsCategoria();
       ArrayList<ClsEntidadCategoria> categorias=tipodocumento.listarCategoria();
       Iterator iterator=categorias.iterator();
       DefaultComboBoxModel DefaultComboBoxModel=new DefaultComboBoxModel();
       DefaultComboBoxModel.removeAllElements();
       
       cboCategoria.removeAll();
       String fila[]=new String[2];
       intContador=0;
       
       while(iterator.hasNext()){
           ClsEntidadCategoria Categoria = new ClsEntidadCategoria();
           Categoria=(ClsEntidadCategoria) iterator.next();
           id[intContador]=Categoria.getStrIdCategoria();
           fila[0]=Categoria.getStrIdCategoria();
           fila[1]=Categoria.getStrDescripcionCategoria();
           DefaultComboBoxModel.addElement(Categoria.getStrDescripcionCategoria());
           intContador++;              
       }
       cboCategoria.setModel(DefaultComboBoxModel);
    }
    void actualizarTabla(){
       String titulos[]={"ID","Cód. de Barras","Nombre","Descripción","Stock","Stock Min.","Costo","Precio","Utilidad","Estado","Categoría"/*,"Imagen"*/};
              
       ClsProducto productos=new ClsProducto();
       ArrayList<ClsEntidadProducto> producto=productos.listarProducto();
       Iterator iterator=producto.iterator();
       DefaultTableModel defaultTableModel=new DefaultTableModel(null,titulos);
       
       String fila[]=new String[12];
       while(iterator.hasNext()){
           ClsEntidadProducto Producto=new ClsEntidadProducto();
           Producto=(ClsEntidadProducto) iterator.next();
           fila[0]=Producto.getStrIdProducto();
           fila[1]=Producto.getStrCodigoProducto();       
           fila[2]=Producto.getStrNombreProducto();
           fila[3]=Producto.getStrDescripcionProducto();
           fila[4]=Producto.getStrStockProducto();
           fila[5]=Producto.getStrStockMinProducto();
           fila[6]=Producto.getStrPrecioCostoProducto();
           fila[7]=Producto.getStrPrecioVentaProducto();
           fila[8]=Producto.getStrUtilidadProducto();
           fila[9]=Producto.getStrEstadoProducto();
           fila[10]=Producto.getStrDescripcionCategoria();
          // fila[11]=Producto.getStrImagen();
           defaultTableModel.addRow(fila);               
       }
       tblProducto.setModel(defaultTableModel);
   }
   void BuscarProducto(){
        String titulos[]={"ID","Cód. de Barras","Nombre","Descripción","Stock","Stock Min.","Costo","Precio","Utilidad","Estado","Categoría"/*,"Imagen"*/};
        dtm.setColumnIdentifiers(titulos);
        
        ClsProducto categoria=new ClsProducto();
        busqueda=txtBusqueda.getText();
        if(rbtnCodigo.isSelected()){
            criterio="codigo";
        }else if(rbtnNombre.isSelected()){
            criterio="nombre";
        }else if(rbtnDescripcion.isSelected()){
            criterio="descripcion";
        }else if(rbtnCategoria.isSelected()){
            criterio="categoria";
        }
        try{
            rs=categoria.listarProductoPorParametro(criterio,busqueda);
            boolean encuentra=false;
            String Datos[]=new String[11];
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
                Datos[7]=rs.getString(8);
                Datos[8]=rs.getString(9);
                Datos[9]=rs.getString(10);
                Datos[10]=rs.getString(11);
                //Datos[11]=rs.getString(12);
                dtm.addRow(Datos);
                encuentra=true;

            }
            if(encuentra=false){
                JOptionPane.showMessageDialog(null, "¡No se encuentra!");
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        tblProducto.setModel(dtm);
    }

    void listardatos(){
        String estado;
        DefaultTableModel defaultTableModel=new DefaultTableModel();
        if(registros==-1){
            JOptionPane.showMessageDialog(null,"Se debe seleccionar un registro");
        }else{
            defaultTableModel=(DefaultTableModel) tblProducto.getModel();
            strCodigo=((String) defaultTableModel.getValueAt(registros,0));
            txtCodigo.setText((String)defaultTableModel.getValueAt(registros,0));
            txtCodigoBar.setText((String)defaultTableModel.getValueAt(registros,1));
            txtNombre.setText((String)defaultTableModel.getValueAt(registros,2));
            txtDescripcion.setText((String)defaultTableModel.getValueAt(registros,3));
            txtStock.setText((String)defaultTableModel.getValueAt(registros,4));
            txtStockMin.setText((String)defaultTableModel.getValueAt(registros,5));
            txtPrecioCosto.setText((String)defaultTableModel.getValueAt(registros,6));
            txtPrecioVenta.setText((String)defaultTableModel.getValueAt(registros,7));
            txtUtilidad.setText((String)defaultTableModel.getValueAt(registros,8));
            if ("ACTIVO".equals(defaultTableModel.getValueAt(registros,9))){
               rbtnActivo.setSelected(true);
            }else if ("INACTIVO".equals(defaultTableModel.getValueAt(registros,9))){
               rbtnInactivo.setSelected(true);
            }
            cboCategoria.setSelectedItem(defaultTableModel.getValueAt(registros,10));
            /*imagen=(String)defaultTableModel.getValueAt(registros,11);
            String ruta="src/Images/"+imagen;
            ImageIcon fot = new ImageIcon(ruta);
            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_DEFAULT));
            lblImagen.setIcon(icono);
            lblImagen.repaint();*/
            
            tblProducto.setRowSelectionInterval(registros,registros);
        }
    
    }
    void CalcularUtilidad(){
        int pre_costo=0,pre_venta=0,utilidad=0,t_utilidad;
        pre_costo=Integer.parseInt(txtPrecioCosto.getText());
        pre_venta=Integer.parseInt(txtPrecioVenta.getText());
        utilidad=pre_venta-pre_costo;
        
        txtUtilidad.setText(String.valueOf(utilidad));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        btnModificar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tabProducto = new javax.swing.JTabbedPane();
        pBuscar = new javax.swing.JPanel();
        lblEstado = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        txtBusqueda = new javax.swing.JTextField();
        rbtnCodigo = new javax.swing.JRadioButton();
        rbtnNombre = new javax.swing.JRadioButton();
        rbtnCategoria = new javax.swing.JRadioButton();
        rbtnDescripcion = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducto = new javax.swing.JTable();
        pNuevo = new javax.swing.JPanel();
        txtCodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCodigoBar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtStockMin = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPrecioVenta = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtPrecioCosto = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        rbtnActivo = new javax.swing.JRadioButton();
        rbtnInactivo = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtUtilidad = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        cboCategoria = new javax.swing.JComboBox();
        btnGenerar = new javax.swing.JButton();
        panel = new javax.swing.JPanel();
        lblprueba = new javax.swing.JLabel();
        cboTipoCodificacion = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstCodigos = new javax.swing.JList();
        btnActualizar = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();
        lblImagen = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        btnSeleccionarImagen = new javax.swing.JButton();
        btnSalir1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setTitle("Productos");
        getContentPane().setLayout(null);

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/editar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnModificar.setIconTextGap(0);
        btnModificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar);
        btnModificar.setBounds(780, 190, 81, 70);

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
        getContentPane().add(btnCancelar);
        btnCancelar.setBounds(780, 260, 81, 70);

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
        getContentPane().add(btnNuevo);
        btnNuevo.setBounds(780, 50, 81, 70);

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setIconTextGap(0);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar);
        btnGuardar.setBounds(780, 120, 81, 70);

        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Mantenimiento"));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(770, 30, 100, 480);

        pBuscar.setBackground(new java.awt.Color(255, 255, 255));
        pBuscar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pBuscar.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 200, 20));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/report.png"))); // NOI18N
        jButton3.setText("Reporte");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        pBuscar.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 120, 50));

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });
        pBuscar.add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 440, -1));

        rbtnCodigo.setText("Cód. Producto");
        rbtnCodigo.setOpaque(false);
        rbtnCodigo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnCodigoStateChanged(evt);
            }
        });
        pBuscar.add(rbtnCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 110, -1));

        rbtnNombre.setText("Nombre");
        rbtnNombre.setOpaque(false);
        rbtnNombre.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnNombreStateChanged(evt);
            }
        });
        pBuscar.add(rbtnNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 30, 80, -1));

        rbtnCategoria.setText("Categoría");
        rbtnCategoria.setOpaque(false);
        pBuscar.add(rbtnCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, -1, -1));

        rbtnDescripcion.setText("Descripción");
        rbtnDescripcion.setOpaque(false);
        pBuscar.add(rbtnDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, -1));

        jLabel13.setBackground(new java.awt.Color(255, 204, 102));
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Criterios de Búsqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jLabel13.setOpaque(true);
        pBuscar.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 720, 80));

        tblProducto.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProducto.setRowHeight(22);
        tblProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProducto);

        pBuscar.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 720, 340));

        tabProducto.addTab("Buscar", pBuscar);

        pNuevo.setBackground(new java.awt.Color(255, 255, 255));
        pNuevo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCodigo.setEnabled(false);
        pNuevo.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 70, 20));

        jLabel3.setText("ID Producto:");
        pNuevo.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 70, 20));

        jLabel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        pNuevo.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 16, 430, 140));

        jLabel2.setText("Código de Barras:");
        pNuevo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 100, 20));

        txtCodigoBar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoBarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoBarKeyTyped(evt);
            }
        });
        pNuevo.add(txtCodigoBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 130, -1));

        jLabel5.setText("Nombre:");
        pNuevo.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 50, 20));

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        pNuevo.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 310, -1));

        jLabel6.setText("Stock:");
        pNuevo.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 40, 30));

        txtStockMin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtStockMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStockMinKeyReleased(evt);
            }
        });
        pNuevo.add(txtStockMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, 80, 30));

        jLabel7.setText("Stock Mínimo:");
        pNuevo.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 80, 30));

        txtPrecioVenta.setBackground(new java.awt.Color(254, 254, 241));
        txtPrecioVenta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPrecioVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioVentaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioVentaKeyTyped(evt);
            }
        });
        pNuevo.add(txtPrecioVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 400, 80, 30));

        jLabel8.setText("Descripción:");
        pNuevo.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 70, 20));

        jLabel10.setText("Precio Costo:");
        pNuevo.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 80, 30));

        txtPrecioCosto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPrecioCosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioCostoKeyReleased(evt);
            }
        });
        pNuevo.add(txtPrecioCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 360, 80, 30));

        jLabel12.setText("Precio Venta:");
        pNuevo.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 80, 30));

        rbtnActivo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rbtnActivo.setText("ACTIVO");
        rbtnActivo.setOpaque(false);
        pNuevo.add(rbtnActivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 80, -1));

        rbtnInactivo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rbtnInactivo.setText("INACTIVO");
        rbtnInactivo.setOpaque(false);
        pNuevo.add(rbtnInactivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 90, -1));

        jLabel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Estado"));
        pNuevo.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 190, 50));

        txtStock.setBackground(new java.awt.Color(242, 253, 253));
        txtStock.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStockKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockKeyTyped(evt);
            }
        });
        pNuevo.add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 80, 30));

        jLabel15.setText("Utilidad:");
        pNuevo.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 50, 30));

        txtUtilidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtUtilidad.setEnabled(false);
        pNuevo.add(txtUtilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 440, 80, 30));

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane3.setViewportView(txtDescripcion);

        pNuevo.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 310, 50));

        jLabel4.setText("Categoría:");
        pNuevo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 60, 30));

        cboCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pNuevo.add(cboCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 120, 30));

        btnGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Generar.png"))); // NOI18N
        btnGenerar.setText("Generar");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });
        pNuevo.add(btnGenerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 330, 110, 40));

        panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panel.add(lblprueba, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 160, 90));

        pNuevo.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 220, 180, 90));

        cboTipoCodificacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Code 128" }));
        pNuevo.add(cboTipoCodificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 180, 160, 30));

        jLabel9.setText("Tipo de Codificación:");
        pNuevo.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 160, -1, -1));

        jLabel16.setText("Código Alternativos:");
        pNuevo.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, 110, 30));

        lstCodigos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lstCodigos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstCodigosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lstCodigos);

        pNuevo.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 200, 70));

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Refresh.png"))); // NOI18N
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        pNuevo.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 30, 40, 30));

        jLabel18.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pNuevo.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, 200, 170));

        jLabel19.setForeground(new java.awt.Color(0, 51, 153));
        jLabel19.setText("asterísco (*) son obligatorios");
        pNuevo.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 430, 180, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 51, 153));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("*");
        pNuevo.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 20, 20));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 51, 153));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("*");
        pNuevo.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 20, 20));

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/printer.png"))); // NOI18N
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        pNuevo.add(btnImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 330, 60, 40));

        lblImagen.setBackground(new java.awt.Color(255, 255, 153));
        lblImagen.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pNuevo.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 300, 300));

        jLabel22.setForeground(new java.awt.Color(0, 51, 153));
        jLabel22.setText("Los campos marcado con un");
        pNuevo.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 410, 170, -1));

        btnSeleccionarImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Agregar_p1.png"))); // NOI18N
        btnSeleccionarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarImagenActionPerformed(evt);
            }
        });
        pNuevo.add(btnSeleccionarImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 50, 40));

        tabProducto.addTab("Nuevo / Modificar", pNuevo);

        getContentPane().add(tabProducto);
        tabProducto.setBounds(10, 10, 750, 510);

        btnSalir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/principal.png"))); // NOI18N
        btnSalir1.setText("Salir");
        btnSalir1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir1.setIconTextGap(0);
        btnSalir1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir1);
        btnSalir1.setBounds(780, 340, 81, 70);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if(tblProducto.getSelectedRows().length > 0 ) {
            accion="Modificar";
            modificar();
            tabProducto.setSelectedIndex(tabProducto.indexOfComponent(pNuevo));
        }else{
            JOptionPane.showMessageDialog(null, "¡Se debe seleccionar un registro!");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        mirar();
        tabProducto.setSelectedIndex(tabProducto.indexOfComponent(pBuscar));
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        accion="Nuevo";
        modificar();
        limpiarCampos();
        tblProducto.setEnabled(false);
        tabProducto.setSelectedIndex(tabProducto.indexOfComponent(pNuevo));
    }//GEN-LAST:event_btnNuevoActionPerformed
//----------------------VALIDACIÓN DE DATOS-------------------------------------
    public boolean validardatos(){
        if(txtCodigoBar.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Especifique un código de barras para el Producto");
            txtCodigoBar.requestFocus();
            txtCodigoBar.setBackground(Color.YELLOW);
            return false;
        }else if(txtNombre.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Ingrese un nombre para el Producto");
            txtNombre.requestFocus();
            txtNombre.setBackground(Color.YELLOW);
            return false;
        }else{
            return true;
        }

    }
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
    if(validardatos()==true){       
        if(accion.equals("Nuevo")){
            ClsProducto productos=new ClsProducto();
            ClsEntidadProducto producto=new ClsEntidadProducto();
            producto.setStrCodigoProducto(txtCodigoBar.getText());
            producto.setStrNombreProducto(txtNombre.getText());
            producto.setStrDescripcionProducto(txtDescripcion.getText());
            if(txtStock.getText().equals("")){
                producto.setStrStockProducto("0");
            }else{
                producto.setStrStockProducto(txtStock.getText());
            }
            if(txtStockMin.getText().equals("")){
                producto.setStrStockMinProducto("0");
            }else{
                producto.setStrStockMinProducto(txtStockMin.getText());
            }
            if(txtPrecioCosto.getText().equals("")){
                producto.setStrPrecioCostoProducto("0");
            }else{
                producto.setStrPrecioCostoProducto(txtPrecioCosto.getText());
            }
            if(txtPrecioVenta.getText().equals("")){
                producto.setStrPrecioVentaProducto("0");
            }else{
                producto.setStrPrecioVentaProducto(txtPrecioVenta.getText());
            }
            if(txtUtilidad.getText().equals("")){
                producto.setStrUtilidadProducto("0");
            }else{
                producto.setStrUtilidadProducto(txtUtilidad.getText());
            }           
            if (rbtnActivo.isSelected()){
                producto.setStrEstadoProducto("ACTIVO");
            }else if (rbtnInactivo.isSelected()){
                producto.setStrEstadoProducto("INACTIVO");
            }
            producto.setStrIdCategoria(id[cboCategoria.getSelectedIndex()]);
            /*if (imagen.equals(""))
            {
                producto.setStrImagen("imagen.png");
            }
            else
            {
               producto.setStrImagen(imagen); 
            }*/
            productos.agregarProducto(producto);
            actualizarTabla();
            CantidadTotal();
        }
        if(accion.equals("Modificar")){
            ClsProducto productos=new ClsProducto();
            ClsEntidadProducto producto=new ClsEntidadProducto();
            producto.setStrCodigoProducto(txtCodigoBar.getText());
            producto.setStrNombreProducto(txtNombre.getText());
            producto.setStrDescripcionProducto(txtDescripcion.getText());
            if(txtStock.getText().equals("")){
                producto.setStrStockProducto("0");
            }else{
                producto.setStrStockProducto(txtStock.getText());
            }
            if(txtStockMin.getText().equals("")){
                producto.setStrStockMinProducto("0");
            }else{
                producto.setStrStockMinProducto(txtStockMin.getText());
            }
            if(txtPrecioCosto.getText().equals("")){
                producto.setStrPrecioCostoProducto("0");
            }else{
                System.out.println(txtPrecioCosto.getText());

                producto.setStrPrecioCostoProducto(txtPrecioCosto.getText());
            }
            if(txtPrecioVenta.getText().equals("")){
                producto.setStrPrecioVentaProducto("0");
            }else{
                producto.setStrPrecioVentaProducto(txtPrecioVenta.getText());
            }
            if(txtUtilidad.getText().equals("")){
                producto.setStrUtilidadProducto("0");
            }else{
                producto.setStrUtilidadProducto(txtUtilidad.getText());
            } 
            if (rbtnActivo.isSelected()){
                producto.setStrEstadoProducto("ACTIVO");
            }else if (rbtnInactivo.isSelected()){
                producto.setStrEstadoProducto("INACTIVO");
            }
            producto.setStrIdCategoria(id[cboCategoria.getSelectedIndex()]);
            /*if (imagen.equals(""))
            {
                producto.setStrImagen("imagen.png");
            }
            else
            {
               producto.setStrImagen(imagen); 
            }*/
            productos.modificarProducto(strCodigo, producto);
            actualizarTabla();
            modificar();
            limpiarCampos();
            CantidadTotal();
        }
        CrearTabla();
        mirar();
        tabProducto.setSelectedIndex(tabProducto.indexOfComponent(pBuscar));
    
    }  
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        BuscarProducto();
        CrearTabla();
        CantidadTotal();
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void rbtnCodigoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnCodigoStateChanged
        txtBusqueda.setText("");
    }//GEN-LAST:event_rbtnCodigoStateChanged

    private void rbtnNombreStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnNombreStateChanged
        txtBusqueda.setText("");
    }//GEN-LAST:event_rbtnNombreStateChanged

    private void tblProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductoMouseClicked
        int fila;
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        fila = tblProducto.getSelectedRow();

        if (fila == -1){
            JOptionPane.showMessageDialog(null, "Se debe seleccionar un registro");
        }else{
            defaultTableModel = (DefaultTableModel)tblProducto.getModel();
            strCodigo =  ((String) defaultTableModel.getValueAt(fila, 0));
            txtCodigo.setText((String)defaultTableModel.getValueAt(fila,0));
            txtCodigoBar.setText((String)defaultTableModel.getValueAt(fila,1));
            txtNombre.setText((String)defaultTableModel.getValueAt(fila,2));
            txtDescripcion.setText((String)defaultTableModel.getValueAt(fila,3));
            txtStock.setText((String)defaultTableModel.getValueAt(fila,4));
            txtStockMin.setText((String)defaultTableModel.getValueAt(fila,5));
            txtPrecioCosto.setText((String)defaultTableModel.getValueAt(fila,6));
            txtPrecioVenta.setText((String)defaultTableModel.getValueAt(fila,7));
            txtUtilidad.setText((String)defaultTableModel.getValueAt(fila,8));
            if ("ACTIVO".equals(defaultTableModel.getValueAt(fila,9))){
               rbtnActivo.setSelected(true);
            }else if ("INACTIVO".equals(defaultTableModel.getValueAt(fila,9))){
               rbtnInactivo.setSelected(true);
            }
            cboCategoria.setSelectedItem(defaultTableModel.getValueAt(fila,10));
            /*imagen=(String)defaultTableModel.getValueAt(fila,11);
            String ruta="src/Images/"+imagen;
            ImageIcon fot = new ImageIcon(ruta);
            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_DEFAULT));
            lblImagen.setIcon(icono);
            lblImagen.repaint();*/

        }

        mirar();
    }//GEN-LAST:event_tblProductoMouseClicked

    private void txtCodigoBarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBarKeyTyped
        txtCodigoBar.setBackground(Color.WHITE);
        //        char car = evt.getKeyChar();
        //        if((car<'a' || car>'z') && (car<'A' || car>'Z')) evt.consume();
    }//GEN-LAST:event_txtCodigoBarKeyTyped

    private void txtPrecioCostoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioCostoKeyReleased
        CalcularUtilidad();
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) txtPrecioVenta.requestFocus();  
    }//GEN-LAST:event_txtPrecioCostoKeyReleased

    private void txtPrecioVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaKeyReleased
        CalcularUtilidad();
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) btnGuardar.requestFocus();  
    }//GEN-LAST:event_txtPrecioVentaKeyReleased
       void verificarCodigoBar(){
        String busqueda=null;
        int sen = 2;
        busqueda=txtCodigoBar.getText(); 
        
            try{
                ClsProducto oProducto=new ClsProducto();
                
                rs= oProducto.verificarCodigoBar(busqueda);
                while (rs.next()) {
                    if(!rs.getString(2).equals("")) {
                               
                       sen=1;
                    }else{

                       sen=2;
                    }
                   break;
                }
 
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,ex.getMessage());
                System.out.println(ex.getMessage());
            }
            
            if(sen==1){
                JOptionPane.showMessageDialog(null, "Codigo No Disponible");
            }else if (sen==2){
                JOptionPane.showMessageDialog(null, "Codigo Disponible");
            }else if(rs==null){
                JOptionPane.showMessageDialog(null, "no hay");
            }
    
    }
    void GeneraAleatorio(){

        String codbar;
        int sen = 2;
        DefaultListModel modelo = new DefaultListModel();
      
        for(int i = 1; i<=4; i++){
            codbar=String.valueOf((int)(Math.random()*(500000-100000+1)+100000));
            //codbar=String.valueOf((int)(Math.random()*(9-1+1)+1));    
            try{
                ClsProducto oProducto=new ClsProducto();
                
                rs= oProducto.verificarCodigoBar(codbar);
                while (rs.next()) {
                    if(!rs.getString(2).equals("")) {
                               
                       sen=1;
                    }else{

                       sen=2;
                    }
                   break;
                }
 
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,ex.getMessage());
                System.out.println(ex.getMessage());
            }
            
            if(sen==1){
                //JOptionPane.showMessageDialog(null, "Codigo No Disponible");
                i=i-1;
                sen=2;
            }else if (sen==2){
                //JOptionPane.showMessageDialog(null, "Codigo Disponible");
                modelo.addElement(codbar);
                //sen=2;
                
            }
        
            

        }
      
        lstCodigos.setModel(modelo);
    }
    void limpiarList(){
        DefaultListModel model = new DefaultListModel();
        lstCodigos.setModel(model);
    }

    
    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
    String codigobar;
    ImageIcon imagen=null;
    codigobar=txtCodigoBar.getText();
        File file=new File("C:\\Users\\Manu\\Desktop\\sistema de ventas\\SV1.0\\sisventas\\versiones sistema de ventas\\SV1.0\\src\\codeBarra\\001.jpg");
        
        Barcode barcode = null;   
        try {   
            if(cboTipoCodificacion.getSelectedItem().equals("Code 128")){
                barcode = BarcodeFactory.createCode128(codigobar);  
            }else if(cboTipoCodificacion.getSelectedItem().equals("Code 128A")){
                barcode = BarcodeFactory.createCode128A(codigobar);
            }else if(cboTipoCodificacion.getSelectedItem().equals("Code 128B")){
                barcode = BarcodeFactory.createCode128B(codigobar); 
            }
             
            barcode.setBarHeight(60);   
            barcode.setBarWidth(1);
            barcode.setDrawingText(false);


            BarcodeImageHandler.saveJPEG(barcode,file);

        } catch(BarcodeException e) {  
            e.printStackTrace();
        } catch(OutputException e) {  
            e.printStackTrace();
        }
        imagen = new ImageIcon("C:\\Users\\Manu\\Desktop\\sistema de ventas\\SV1.0\\sisventas\\versiones sistema de ventas\\SV1.0\\src\\codeBarra\\001.jpg");
        imagen.getImage().flush();
        lblprueba.setIcon(imagen);

        
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
      GeneraAleatorio();


    }//GEN-LAST:event_btnActualizarActionPerformed

    private void lstCodigosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstCodigosMouseClicked
        //int seleccion = lstCodigos.getSelectedIndex();
        //txtCodigoBar.setText(String.valueOf(seleccion));
        txtCodigoBar.setText(lstCodigos.getSelectedValue().toString());
        
    }//GEN-LAST:event_lstCodigosMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Map p=new HashMap();
        p.put("busqueda",txtBusqueda.getText());
        if(rbtnCodigo.isSelected()){
            p.put("criterio", "codigo");
        }
        else if(rbtnNombre.isSelected()){
            p.put("criterio", "nombre");
        }else if(rbtnDescripcion.isSelected()){
            p.put("criterio", "descripcion");
        }else if(rbtnCategoria.isSelected()){
            p.put("criterio", "categoria");
        }else{
            p.put("criterio", "");
        }
        JasperReport report;
        JasperPrint print;
        try{
            report=JasperCompileManager.compileReport(new File("").getAbsolutePath()+ "/src/Reportes/RptProducto.jrxml");
            print=JasperFillManager.fillReport(report, p,connection);
            JasperViewer view=new JasperViewer(print,false);
            view.setTitle("Reporte General de Productos");
            view.setVisible(true);
        }catch(JRException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        txtNombre.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtStockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockKeyTyped
        txtStock.setBackground(Color.WHITE);        
    }//GEN-LAST:event_txtStockKeyTyped

    private void txtPrecioVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaKeyTyped
        txtPrecioVenta.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtPrecioVentaKeyTyped

    private void txtCodigoBarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBarKeyReleased
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) txtNombre.requestFocus();        
    }//GEN-LAST:event_txtCodigoBarKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) txtDescripcion.requestFocus();         
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtStockKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockKeyReleased
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) txtStockMin.requestFocus();        
    }//GEN-LAST:event_txtStockKeyReleased

    private void txtStockMinKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockMinKeyReleased
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) txtPrecioCosto.requestFocus();     
    }//GEN-LAST:event_txtStockMinKeyReleased

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        Map p=new HashMap();
        String cod=txtCodigoBar.getText();
        p.put("codigo",cod);

        JasperReport report;
        JasperPrint print;
        if(cboTipoCodificacion.getSelectedItem().equals("Code 128")){
            try{
                report=JasperCompileManager.compileReport(new File("").getAbsolutePath()+ "/src/Reportes/RptProducto_Code128.jrxml");
                print=JasperFillManager.fillReport(report, p,new JREmptyDataSource());
                JasperViewer view=new JasperViewer(print,false);
                view.setTitle("Código de Barras - CODE128");
                view.setVisible(true);
            }catch(JRException e){
                e.printStackTrace();
            }
         }else if(cboTipoCodificacion.getSelectedItem().equals("Code 128A")){
            try{
                report=JasperCompileManager.compileReport(new File("").getAbsolutePath()+ "/src/Reportes/RptProducto_Code128A.jrxml");
                print=JasperFillManager.fillReport(report, p,new JREmptyDataSource());
                JasperViewer view=new JasperViewer(print,false);
                view.setTitle("Código de Barras - CODE128A");
                view.setVisible(true);
            }catch(JRException e){
                e.printStackTrace();
            }
         }else if(cboTipoCodificacion.getSelectedItem().equals("Code 128B")){
            try{
                report=JasperCompileManager.compileReport(new File("").getAbsolutePath()+ "/src/Reportes/RptProducto_Code128B.jrxml");
                print=JasperFillManager.fillReport(report, p,new JREmptyDataSource());
                JasperViewer view=new JasperViewer(print,false);
                view.setTitle("Código de Barras - CODE128B");
                view.setVisible(true);
            }catch(JRException e){
                e.printStackTrace();
            }
         }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnSeleccionarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarImagenActionPerformed
        // TODO add your handling code here:
        String ruta = "";
        String pathOrigen;
        String pathDestino;
        
        JFileChooser file = new JFileChooser();
        int estado=file.showOpenDialog(this);
        if(estado==JFileChooser.APPROVE_OPTION);
        {
            imagen=file.getSelectedFile().getName();
            //-----------------------------------------------
            pathOrigen=file.getSelectedFile().getAbsolutePath();
            pathDestino="src/Images/"+imagen;
            
            File origen = new File(pathOrigen);
            File destino = new File(pathDestino);
            try {
                InputStream in = new FileInputStream(origen);
                OutputStream out = new FileOutputStream(destino);
                byte[] buf = new byte[1024];
                int len;

                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    in.close();
                    out.close();    
                } catch (IOException ex) {
                    Logger.getLogger(FrmProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                 
            
            
            //-----------------------------------------------
            
            
            ruta="src/Images/"+imagen;
            ImageIcon fot = new ImageIcon(ruta);
            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_DEFAULT));
            lblImagen.setIcon(icono);
            lblImagen.repaint();
//            ImageIcon img = new ImageIcon(ruta);
//            lblImagen.setIcon(img);
//            lblImagen.repaint();
            //System.out.println(ruta);
            
        }
        
    }//GEN-LAST:event_btnSeleccionarImagenActionPerformed

    private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalir1ActionPerformed
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir1;
    private javax.swing.JButton btnSeleccionarImagen;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox cboCategoria;
    private javax.swing.JComboBox cboTipoCodificacion;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblprueba;
    private javax.swing.JList lstCodigos;
    private javax.swing.JPanel pBuscar;
    private javax.swing.JPanel pNuevo;
    private javax.swing.JPanel panel;
    private javax.swing.JRadioButton rbtnActivo;
    private javax.swing.JRadioButton rbtnCategoria;
    private javax.swing.JRadioButton rbtnCodigo;
    private javax.swing.JRadioButton rbtnDescripcion;
    private javax.swing.JRadioButton rbtnInactivo;
    private javax.swing.JRadioButton rbtnNombre;
    private javax.swing.JTabbedPane tabProducto;
    private javax.swing.JTable tblProducto;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCodigoBar;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecioCosto;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtStockMin;
    private javax.swing.JTextField txtUtilidad;
    // End of variables declaration//GEN-END:variables
}
