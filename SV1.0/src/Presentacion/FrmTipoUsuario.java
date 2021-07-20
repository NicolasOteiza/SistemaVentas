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
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class FrmTipoUsuario extends javax.swing.JInternalFrame {

    String Total;
    String strCodigo;
    String accion;
    //---------------------Privilegios--------------------
    String p_venta="0",p_compra="0",p_producto="0",p_proveedor="0",p_empleado="0";
    String p_cliente="0",p_categoria="0",p_tipodoc="0",p_tipouser="0",p_anularv="0",p_anularc="0";
    String p_estadoprod="0",p_ventare="0",p_ventade="0",p_estadistica="0",p_comprare="0",p_comprade="0",p_pass="0",p_respaldar="0",p_restaurar="0",p_caja="0";
    //----------------------------------------------------
    int registros;
    String id[]=new String[50];
    static int intContador;
    
    //-----------------------------------------------
    public String codigo;
    static Connection conn=null;
    static ResultSet rs=null;
    DefaultTableModel dtm=new DefaultTableModel();
    String criterio,busqueda;
    
    public FrmTipoUsuario() {
        initComponents();
        tabTipoUsuario.setIconAt(tabTipoUsuario.indexOfComponent(pBuscar), new ImageIcon("src/iconos/busca_p1.png"));
        tabTipoUsuario.setIconAt(tabTipoUsuario.indexOfComponent(pNuevo), new ImageIcon("src/iconos/nuevo1.png"));
        buttonGroup1.add(rbtnCodigo);
        buttonGroup1.add(rbtnDescripcion);
        mirar();
        actualizarTabla();
        //---------------------ANCHO Y ALTO DEL FORM----------------------
        this.setSize(654, 426);
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
                    if(column==0){
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
        for (int i=0;i<tblTipoUsuario.getColumnCount();i++){
            tblTipoUsuario.getColumnModel().getColumn(i).setCellRenderer(render);
        }
      
        //Activar ScrollBar
        tblTipoUsuario.setAutoResizeMode(tblTipoUsuario.AUTO_RESIZE_OFF);

        //Anchos de cada columna
        int[] anchos = {50,180,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80};
        for(int i = 0; i < tblTipoUsuario.getColumnCount(); i++) {
            tblTipoUsuario.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
    }
   void CantidadTotal(){
        Total= String.valueOf(tblTipoUsuario.getRowCount());   
        lblEstado.setText("Se cargaron " + Total + " registros");      
   }
   void limpiarCampos(){
       txtCodigo.setText("");
       txtDescripcion.setText(""); 
       chkRProducto.isSelected();
       
       rbtnCodigo.setSelected(false);
       rbtnDescripcion.setSelected(false);
       chkRVenta.setSelected(false);
       chkRCompra.setSelected(false);
       chkRProducto.setSelected(false);
       chkRProveedor.setSelected(false);
       chkREmpleado.setSelected(false);
       chkRCliente.setSelected(false);
       chkRCategoria.setSelected(false);
       chkRTipodoc.setSelected(false);
       chkRTipouser.setSelected(false);
       chkAnularv.setSelected(false);
       chkAnularc.setSelected(false);
       chkEstado.setSelected(false);
       chkVentare.setSelected(false);
       chkVentade.setSelected(false);
       chkEstadistica.setSelected(false);
       chkComprare.setSelected(false);
       chkComprade.setSelected(false);
       chkPass.setSelected(false);
       chkRespaldar.setSelected(false);
       chkRestaurar.setSelected(false);
       chkCaja.setSelected(false);
       
       txtBusqueda.setText("");
   }
    
        
   void mirar(){
        tblTipoUsuario.setEnabled(true);
        btnNuevo.setEnabled(true);
        btnModificar.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnSalir.setEnabled(true);
        
        txtDescripcion.setEnabled(false);
   
    }
   
    void modificar(){
        tblTipoUsuario.setEnabled(false);
        btnNuevo.setEnabled(false);
        btnModificar.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnSalir.setEnabled(false);
        
        txtDescripcion.setEnabled(true);
        txtDescripcion.requestFocus();
    }
   
   
    void actualizarTabla(){
       String titulos[]={"ID","Descripción","P. venta","P. compra","P. producto","P. proveedor","P. empleado","P. cliente","P. categoria","P. tipodoc","P. tipouser","P. anularv","P. anularc",
           "P. estadoprod","P. ventare ","P. ventade","P. estadist.","P. comprare","P. comprade","P. pass","P. respaldar","P. restaurar","P. caja"};
      
       ClsTipoUsuario tipousuarios=new ClsTipoUsuario();
       ArrayList<ClsEntidadTipoUsuario> tipousuario=tipousuarios.listarTipoUsuario();
       Iterator iterator=tipousuario.iterator();
       DefaultTableModel defaultTableModel=new DefaultTableModel(null,titulos);
       
       String fila[]=new String[23];
       while(iterator.hasNext()){
           ClsEntidadTipoUsuario TipoUsuario=new ClsEntidadTipoUsuario();
           TipoUsuario=(ClsEntidadTipoUsuario) iterator.next();
           fila[0]=TipoUsuario.getStrIdTipoUsuario();
           fila[1]=TipoUsuario.getStrDescripcionTipoUsuario();
           fila[2]=TipoUsuario.getStrP_Venta();  
           fila[3]=TipoUsuario.getStrP_Compra();  
           fila[4]=TipoUsuario.getStrP_Producto();  
           fila[5]=TipoUsuario.getStrP_Proveedor();  
           fila[6]=TipoUsuario.getStrP_Empleado();  
           fila[7]=TipoUsuario.getStrP_Cliente();  
           fila[8]=TipoUsuario.getStrP_Categoria();  
           fila[9]=TipoUsuario.getStrP_Tipodoc();  
           fila[10]=TipoUsuario.getStrP_Tipouser();  
           fila[11]=TipoUsuario.getStrP_Anularv();  
           fila[12]=TipoUsuario.getStrP_Anularc();
           fila[13]=TipoUsuario.getStrP_Estadoprod(); 
           fila[14]=TipoUsuario.getStrP_Ventare(); 
           fila[15]=TipoUsuario.getStrP_Ventade(); 
           fila[16]=TipoUsuario.getStrP_Estadistica(); 
           fila[17]=TipoUsuario.getStrP_Comprare(); 
           fila[18]=TipoUsuario.getStrP_Comprade(); 
           fila[19]=TipoUsuario.getStrP_Pass(); 
           fila[20]=TipoUsuario.getStrP_Respaldar(); 
           fila[21]=TipoUsuario.getStrP_Restaurar(); 
           fila[22]=TipoUsuario.getStrP_Caja();
           
           defaultTableModel.addRow(fila);
                
       }
       tblTipoUsuario.setModel(defaultTableModel);
   }
   void BuscarTipoUsuario(){
        String titulos[]={"ID","Descripción","P. venta","P. compra","P. producto","P. proveedor","P. empleado","P. cliente","P. categoria","P. tipodoc","P. tipouser","P. anularv","P. anularc",
            "P. estadoprod","P. ventare ","P. ventade","P. estadist.","P. comprare","P. comprade","P. pass","P. respaldar","P. restaurar","P. caja"};
        
        dtm.setColumnIdentifiers(titulos);
        
        ClsTipoUsuario categoria=new ClsTipoUsuario();
        busqueda=txtBusqueda.getText();
        if(rbtnCodigo.isSelected()){
            criterio="id";
        }else if(rbtnDescripcion.isSelected()){
            criterio="descripcion";
        }
        try{
            rs=categoria.listarTipoUsuarioPorParametro(criterio,busqueda);
            boolean encuentra=false;
            String Datos[]=new String[23];
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
                Datos[11]=rs.getString(12);
                Datos[12]=rs.getString(13);
                Datos[13]=rs.getString(14);
                Datos[14]=rs.getString(15);
                Datos[15]=rs.getString(16);
                Datos[16]=rs.getString(17);
                Datos[17]=rs.getString(18);
                Datos[18]=rs.getString(19);
                Datos[19]=rs.getString(20);
                Datos[20]=rs.getString(21);
                Datos[21]=rs.getString(22);
                Datos[22]=rs.getString(23);

                dtm.addRow(Datos);
                encuentra=true;

            }
            if(encuentra=false){
                JOptionPane.showMessageDialog(null, "¡No se encuentra!");
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        tblTipoUsuario.setModel(dtm);
    }
    void listardatos(){
        String estado;
        DefaultTableModel defaultTableModel=new DefaultTableModel();
        if(registros==-1){
            JOptionPane.showMessageDialog(null,"Se debe seleccionar un registro");
        }else{
            defaultTableModel=(DefaultTableModel) tblTipoUsuario.getModel();
            strCodigo=((String) defaultTableModel.getValueAt(registros,0));
            txtCodigo.setText((String)defaultTableModel.getValueAt(registros,0));
            txtDescripcion.setText((String)defaultTableModel.getValueAt(registros,1));
            if ("1".equals(defaultTableModel.getValueAt(registros,2))){
                chkRVenta.setSelected(true);
            }else{
                chkRVenta.setSelected(false);
            }
            if ("1".equals(defaultTableModel.getValueAt(registros,3))){
                chkRCompra.setSelected(true);
            }else{
                chkRCompra.setSelected(false);
            }  
            if ("1".equals(defaultTableModel.getValueAt(registros,4))){
                chkRProducto.setSelected(true);
            }else{
                chkRProducto.setSelected(false);
            }             
            if ("1".equals(defaultTableModel.getValueAt(registros,5))){
                chkRProveedor.setSelected(true);
            }else{
                chkRProveedor.setSelected(false);
            }  
            if ("1".equals(defaultTableModel.getValueAt(registros,6))){
                chkREmpleado.setSelected(true);
            }else{
                chkREmpleado.setSelected(false);
            }  
            if ("1".equals(defaultTableModel.getValueAt(registros,7))){
                chkRCliente.setSelected(true);
            }else{
                chkRCliente.setSelected(false);
            }
            if ("1".equals(defaultTableModel.getValueAt(registros,8))){
                chkRCategoria.setSelected(true);
            }else{
                chkRCategoria.setSelected(false);
            }
            if ("1".equals(defaultTableModel.getValueAt(registros,9))){
                chkRTipodoc.setSelected(true);
            }else{
                chkRTipodoc.setSelected(false);
            }
            if ("1".equals(defaultTableModel.getValueAt(registros,10))){
                chkRTipouser.setSelected(true);
            }else{
                chkRTipouser.setSelected(false);
            }
            if ("1".equals(defaultTableModel.getValueAt(registros,11))){
                chkAnularv.setSelected(true);
            }else{
                chkAnularv.setSelected(false);
            }
            if ("1".equals(defaultTableModel.getValueAt(registros,12))){
                chkAnularc.setSelected(true);
            }else{
                chkAnularc.setSelected(false);
            }
            if ("1".equals(defaultTableModel.getValueAt(registros,13))){
                chkEstado.setSelected(true);
            }else{
                chkEstado.setSelected(false);
            }
            if ("1".equals(defaultTableModel.getValueAt(registros,14))){
                chkVentare.setSelected(true);
            }else{
                chkVentare.setSelected(false);
            }
            if ("1".equals(defaultTableModel.getValueAt(registros,15))){
                chkVentade.setSelected(true);
            }else{
                chkVentade.setSelected(false);
            }             
            if ("1".equals(defaultTableModel.getValueAt(registros,16))){
                chkEstadistica.setSelected(true);
            }else{
                chkEstadistica.setSelected(false);
            }            
            if ("1".equals(defaultTableModel.getValueAt(registros,17))){
                chkComprare.setSelected(true);
            }else{
                chkComprare.setSelected(false);
            }         
            if ("1".equals(defaultTableModel.getValueAt(registros,18))){
                chkComprade.setSelected(true);
            }else{
                chkComprade.setSelected(false);
            }            
            if ("1".equals(defaultTableModel.getValueAt(registros,19))){
                chkPass.setSelected(true);
            }else{
                chkPass.setSelected(false);
            }
            if ("1".equals(defaultTableModel.getValueAt(registros,20))){
                chkRespaldar.setSelected(true);
            }else{
                chkRespaldar.setSelected(false);
            }
            if ("1".equals(defaultTableModel.getValueAt(registros,21))){
                chkRestaurar.setSelected(true);
            }else{
                chkRestaurar.setSelected(false);
            }  
            if ("1".equals(defaultTableModel.getValueAt(registros,22))){
                chkCaja.setSelected(true);
            }else{
                chkCaja.setSelected(false);
            }              
  
            tblTipoUsuario.setRowSelectionInterval(registros,registros);
        }
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        btnModificar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tabTipoUsuario = new javax.swing.JTabbedPane();
        pBuscar = new javax.swing.JPanel();
        lblEstado = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        rbtnCodigo = new javax.swing.JRadioButton();
        rbtnDescripcion = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTipoUsuario = new javax.swing.JTable();
        pNuevo = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        chkEstadistica = new javax.swing.JCheckBox();
        chkComprare = new javax.swing.JCheckBox();
        chkComprade = new javax.swing.JCheckBox();
        chkPass = new javax.swing.JCheckBox();
        chkRespaldar = new javax.swing.JCheckBox();
        chkRestaurar = new javax.swing.JCheckBox();
        chkRTipodoc = new javax.swing.JCheckBox();
        chkRTipouser = new javax.swing.JCheckBox();
        chkAnularv = new javax.swing.JCheckBox();
        chkAnularc = new javax.swing.JCheckBox();
        chkEstado = new javax.swing.JCheckBox();
        chkVentare = new javax.swing.JCheckBox();
        chkVentade = new javax.swing.JCheckBox();
        chkRVenta = new javax.swing.JCheckBox();
        chkRCompra = new javax.swing.JCheckBox();
        chkRProducto = new javax.swing.JCheckBox();
        chkRProveedor = new javax.swing.JCheckBox();
        chkREmpleado = new javax.swing.JCheckBox();
        chkRCliente = new javax.swing.JCheckBox();
        chkRCategoria = new javax.swing.JCheckBox();
        chkCaja = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setTitle("Registro del Tipo de Usuario");
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
        btnModificar.setBounds(540, 170, 81, 70);

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
        btnCancelar.setBounds(540, 240, 81, 70);

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
        btnNuevo.setBounds(540, 30, 81, 70);

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
        getContentPane().add(btnSalir);
        btnSalir.setBounds(540, 310, 81, 70);

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
        btnGuardar.setBounds(540, 100, 81, 70);

        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Mantenimiento"));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(530, 10, 100, 380);

        pBuscar.setBackground(new java.awt.Color(255, 255, 255));
        pBuscar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pBuscar.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 200, 20));

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });
        pBuscar.add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 310, -1));

        rbtnCodigo.setText("ID Tipo de Usuario");
        rbtnCodigo.setOpaque(false);
        rbtnCodigo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnCodigoStateChanged(evt);
            }
        });
        pBuscar.add(rbtnCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 130, -1));

        rbtnDescripcion.setText("Descripción");
        rbtnDescripcion.setOpaque(false);
        rbtnDescripcion.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnDescripcionStateChanged(evt);
            }
        });
        pBuscar.add(rbtnDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 100, -1));

        jLabel5.setBackground(new java.awt.Color(255, 204, 102));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Criterios de Búsqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jLabel5.setOpaque(true);
        pBuscar.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 490, 80));

        tblTipoUsuario.setModel(new javax.swing.table.DefaultTableModel(
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
        tblTipoUsuario.setRowHeight(22);
        tblTipoUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTipoUsuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTipoUsuario);

        pBuscar.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 490, 230));

        tabTipoUsuario.addTab("Buscar", pBuscar);

        pNuevo.setBackground(new java.awt.Color(255, 255, 255));
        pNuevo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Tipo de Usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        pNuevo.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 11, 490, 50));

        txtCodigo.setEnabled(false);
        pNuevo.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 50, -1));

        jLabel3.setText("ID Tipo de Usuario:");
        pNuevo.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 100, 20));

        jLabel2.setText("Descripción:");
        pNuevo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 70, 20));

        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });
        pNuevo.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 200, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Roles del Empleado [Operaciones que puede realizar el empleado en el sistema]", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chkEstadistica.setText("Info. Estadística");
        chkEstadistica.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkEstadisticaStateChanged(evt);
            }
        });
        jPanel1.add(chkEstadistica, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, -1, -1));

        chkComprare.setText("Info. Compra realizada");
        chkComprare.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkComprareStateChanged(evt);
            }
        });
        jPanel1.add(chkComprare, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, -1, -1));

        chkComprade.setText("Info. Compra detallada");
        chkComprade.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkCompradeStateChanged(evt);
            }
        });
        jPanel1.add(chkComprade, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, -1, -1));

        chkPass.setText("Cambiar Contraseña");
        chkPass.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkPassStateChanged(evt);
            }
        });
        jPanel1.add(chkPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, -1, -1));

        chkRespaldar.setText("Respaldar DB");
        chkRespaldar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkRespaldarStateChanged(evt);
            }
        });
        jPanel1.add(chkRespaldar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, -1, -1));

        chkRestaurar.setText("Restaurar DB");
        chkRestaurar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkRestaurarStateChanged(evt);
            }
        });
        jPanel1.add(chkRestaurar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, -1, -1));

        chkRTipodoc.setText("Reg. Tipo de Documento");
        chkRTipodoc.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkRTipodocStateChanged(evt);
            }
        });
        jPanel1.add(chkRTipodoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, -1, -1));

        chkRTipouser.setText("Reg. Tipo de Usuario");
        chkRTipouser.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkRTipouserStateChanged(evt);
            }
        });
        jPanel1.add(chkRTipouser, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, -1, -1));

        chkAnularv.setText("Anular Venta");
        chkAnularv.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkAnularvStateChanged(evt);
            }
        });
        jPanel1.add(chkAnularv, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, -1, -1));

        chkAnularc.setText("Anular Compra");
        chkAnularc.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkAnularcStateChanged(evt);
            }
        });
        jPanel1.add(chkAnularc, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, -1, -1));

        chkEstado.setText("Info. Estado");
        chkEstado.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkEstadoStateChanged(evt);
            }
        });
        jPanel1.add(chkEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, -1, -1));

        chkVentare.setText("Info. Venta realizada");
        chkVentare.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkVentareStateChanged(evt);
            }
        });
        jPanel1.add(chkVentare, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, -1, -1));

        chkVentade.setText("Info. Venta detallada");
        chkVentade.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkVentadeStateChanged(evt);
            }
        });
        jPanel1.add(chkVentade, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, -1, -1));

        chkRVenta.setText("Reg. Venta");
        chkRVenta.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkRVentaStateChanged(evt);
            }
        });
        jPanel1.add(chkRVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        chkRCompra.setText("Reg. Compra");
        chkRCompra.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkRCompraStateChanged(evt);
            }
        });
        jPanel1.add(chkRCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        chkRProducto.setText("Reg. Producto");
        chkRProducto.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkRProductoStateChanged(evt);
            }
        });
        jPanel1.add(chkRProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        chkRProveedor.setText("Reg. Proveedor");
        chkRProveedor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkRProveedorStateChanged(evt);
            }
        });
        jPanel1.add(chkRProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        chkREmpleado.setText("Reg. Empleado");
        chkREmpleado.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkREmpleadoStateChanged(evt);
            }
        });
        jPanel1.add(chkREmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        chkRCliente.setText("Reg. Cliente");
        chkRCliente.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkRClienteStateChanged(evt);
            }
        });
        jPanel1.add(chkRCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        chkRCategoria.setText("Reg. Categoria");
        chkRCategoria.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkRCategoriaStateChanged(evt);
            }
        });
        jPanel1.add(chkRCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        chkCaja.setText("Info. Caja");
        chkCaja.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkCajaStateChanged(evt);
            }
        });
        jPanel1.add(chkCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, -1, -1));

        pNuevo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, 490, 280));

        tabTipoUsuario.addTab("Nuevo / Modificar", pNuevo);

        getContentPane().add(tabTipoUsuario);
        tabTipoUsuario.setBounds(10, 10, 520, 380);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblTipoUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTipoUsuarioMouseClicked
        int fila;
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        fila = tblTipoUsuario.getSelectedRow();

        if (fila == -1){
            JOptionPane.showMessageDialog(null, "Se debe seleccionar un registro");
        }else{
            defaultTableModel = (DefaultTableModel)tblTipoUsuario.getModel();
            strCodigo =  ((String) defaultTableModel.getValueAt(fila, 0));
            txtCodigo.setText((String) defaultTableModel.getValueAt(fila, 0));
            txtDescripcion.setText((String) defaultTableModel.getValueAt(fila, 1));
            if ("1".equals(defaultTableModel.getValueAt(fila,2))){
                chkRVenta.setSelected(true);
            }else{
                chkRVenta.setSelected(false);
            }
            if ("1".equals(defaultTableModel.getValueAt(fila,3))){
                chkRCompra.setSelected(true);
            }else{
                chkRCompra.setSelected(false);
            }  
            if ("1".equals(defaultTableModel.getValueAt(fila,4))){
                chkRProducto.setSelected(true);
            }else{
                chkRProducto.setSelected(false);
            }             
            if ("1".equals(defaultTableModel.getValueAt(fila,5))){
                chkRProveedor.setSelected(true);
            }else{
                chkRProveedor.setSelected(false);
            }  
            if ("1".equals(defaultTableModel.getValueAt(fila,6))){
                chkREmpleado.setSelected(true);
            }else{
                chkREmpleado.setSelected(false);
            }  
            if ("1".equals(defaultTableModel.getValueAt(fila,7))){
                chkRCliente.setSelected(true);
            }else{
                chkRCliente.setSelected(false);
            }
            if ("1".equals(defaultTableModel.getValueAt(fila,8))){
                chkRCategoria.setSelected(true);
            }else{
                chkRCategoria.setSelected(false);
            }
            if ("1".equals(defaultTableModel.getValueAt(fila,9))){
                chkRTipodoc.setSelected(true);
            }else{
                chkRTipodoc.setSelected(false);
            }
            if ("1".equals(defaultTableModel.getValueAt(fila,10))){
                chkRTipouser.setSelected(true);
            }else{
                chkRTipouser.setSelected(false);
            }
            if ("1".equals(defaultTableModel.getValueAt(fila,11))){
                chkAnularv.setSelected(true);
            }else{
                chkAnularv.setSelected(false);
            }
            if ("1".equals(defaultTableModel.getValueAt(fila,12))){
                chkAnularc.setSelected(true);
            }else{
                chkAnularc.setSelected(false);
            }
            if ("1".equals(defaultTableModel.getValueAt(fila,13))){
                chkEstado.setSelected(true);
            }else{
                chkEstado.setSelected(false);
            }
            if ("1".equals(defaultTableModel.getValueAt(fila,14))){
                chkVentare.setSelected(true);
            }else{
                chkVentare.setSelected(false);
            }
            if ("1".equals(defaultTableModel.getValueAt(fila,15))){
                chkVentade.setSelected(true);
            }else{
                chkVentade.setSelected(false);
            }             
            if ("1".equals(defaultTableModel.getValueAt(fila,16))){
                chkEstadistica.setSelected(true);
            }else{
                chkEstadistica.setSelected(false);
            }            
            if ("1".equals(defaultTableModel.getValueAt(fila,17))){
                chkComprare.setSelected(true);
            }else{
                chkComprare.setSelected(false);
            }         
            if ("1".equals(defaultTableModel.getValueAt(fila,18))){
                chkComprade.setSelected(true);
            }else{
                chkComprade.setSelected(false);
            }            
            if ("1".equals(defaultTableModel.getValueAt(fila,19))){
                chkPass.setSelected(true);
            }else{
                chkPass.setSelected(false);
            }
            if ("1".equals(defaultTableModel.getValueAt(fila,20))){
                chkRespaldar.setSelected(true);
            }else{
                chkRespaldar.setSelected(false);
            }
            if ("1".equals(defaultTableModel.getValueAt(fila,21))){
                chkRestaurar.setSelected(true);
            }else{
                chkRestaurar.setSelected(false);
            }
            if ("1".equals(defaultTableModel.getValueAt(fila,22))){
                chkCaja.setSelected(true);
            }else{
                chkCaja.setSelected(false);
            }                 
        }

        mirar();
    }//GEN-LAST:event_tblTipoUsuarioMouseClicked

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        char car = evt.getKeyChar();
        if((car<'a' || car>'z') && (car<'A' || car>'Z')) evt.consume();
        txtDescripcion.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtDescripcionKeyTyped

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
    if(tblTipoUsuario.getSelectedRows().length > 0 ) { 
        accion="Modificar";
        modificar();
        tabTipoUsuario.setSelectedIndex(tabTipoUsuario.indexOfComponent(pNuevo));
    }else{
        JOptionPane.showMessageDialog(null, "¡Se debe seleccionar un registro!");
    }
          
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        mirar();
        tabTipoUsuario.setSelectedIndex(tabTipoUsuario.indexOfComponent(pBuscar));

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        accion="Nuevo";
        modificar();
        limpiarCampos();
        tabTipoUsuario.setSelectedIndex(tabTipoUsuario.indexOfComponent(pNuevo));
        
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
//----------------------VALIDACIÓN DE DATOS-------------------------------------
    public boolean validardatos(){
        if (txtDescripcion.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Ingrese una descripción");
            txtDescripcion.requestFocus();
            txtDescripcion.setBackground(Color.YELLOW);
            return false;
        }else{
            return true;
        }

    }
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
    if(validardatos()==true){   
        if(accion.equals("Nuevo")){
            ClsTipoUsuario tipousuarios=new ClsTipoUsuario();
            ClsEntidadTipoUsuario tipousuario=new ClsEntidadTipoUsuario();
            tipousuario.setStrDescripcionTipoUsuario(txtDescripcion.getText());
            tipousuario.setStrP_Venta(p_venta);
            tipousuario.setStrP_Compra(p_compra);
            tipousuario.setStrP_Producto(p_producto);
            tipousuario.setStrP_Proveedor(p_proveedor);
            tipousuario.setStrP_Empleado(p_empleado);
            tipousuario.setStrP_Cliente(p_cliente);
            tipousuario.setStrP_Categoria(p_categoria);
            tipousuario.setStrP_Tipodoc(p_tipodoc);
            tipousuario.setStrP_Tipouser(p_tipouser);
            tipousuario.setStrP_Anularv(p_anularv);
            tipousuario.setStrP_Anularc(p_anularc);
            tipousuario.setStrP_Estadoprod(p_estadoprod);
            tipousuario.setStrP_Ventare(p_ventare);
            tipousuario.setStrP_Ventade(p_ventade);
            tipousuario.setStrP_Estadistica(p_estadistica);
            tipousuario.setStrP_Comprare(p_comprare);
            tipousuario.setStrP_Comprade(p_comprade);
            tipousuario.setStrP_Pass(p_pass);
            tipousuario.setStrP_Respaldar(p_respaldar);
            tipousuario.setStrP_Restaurar(p_restaurar);
            tipousuario.setStrP_Caja(p_caja);
            tipousuarios.agregarTipoUsuario(tipousuario);
            actualizarTabla();
            CantidadTotal();
        }
        if(accion.equals("Modificar")){
            ClsTipoUsuario tipousuarios=new ClsTipoUsuario();
            ClsEntidadTipoUsuario tipousuario=new ClsEntidadTipoUsuario();
            tipousuario.setStrDescripcionTipoUsuario(txtDescripcion.getText());
            tipousuario.setStrP_Venta(p_venta);
            tipousuario.setStrP_Compra(p_compra);
            tipousuario.setStrP_Producto(p_producto);
            tipousuario.setStrP_Proveedor(p_proveedor);
            tipousuario.setStrP_Empleado(p_empleado);
            tipousuario.setStrP_Cliente(p_cliente);
            tipousuario.setStrP_Categoria(p_categoria);
            tipousuario.setStrP_Tipodoc(p_tipodoc);
            tipousuario.setStrP_Tipouser(p_tipouser);
            tipousuario.setStrP_Anularv(p_anularv);
            tipousuario.setStrP_Anularc(p_anularc);
            tipousuario.setStrP_Estadoprod(p_estadoprod);
            tipousuario.setStrP_Ventare(p_ventare);
            tipousuario.setStrP_Ventade(p_ventade);
            tipousuario.setStrP_Estadistica(p_estadistica);
            tipousuario.setStrP_Comprare(p_comprare);
            tipousuario.setStrP_Comprade(p_comprade);
            tipousuario.setStrP_Pass(p_pass);
            tipousuario.setStrP_Respaldar(p_respaldar);
            tipousuario.setStrP_Restaurar(p_restaurar);
            tipousuario.setStrP_Caja(p_caja);
            tipousuarios.modificarTipoUsuario(strCodigo, tipousuario);
            actualizarTabla();
            limpiarCampos();
            modificar();
            CantidadTotal();
        }
        CrearTabla();
        mirar();
        tabTipoUsuario.setSelectedIndex(tabTipoUsuario.indexOfComponent(pBuscar)); 
    }    
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        BuscarTipoUsuario();
        CrearTabla();
        CantidadTotal();
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void rbtnCodigoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnCodigoStateChanged
        txtBusqueda.setText("");
    }//GEN-LAST:event_rbtnCodigoStateChanged

    private void rbtnDescripcionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnDescripcionStateChanged
        txtBusqueda.setText("");
    }//GEN-LAST:event_rbtnDescripcionStateChanged

    private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
        String cadena= (txtDescripcion.getText()).toUpperCase();
        txtDescripcion.setText(cadena);
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) btnGuardar.requestFocus();
    }//GEN-LAST:event_txtDescripcionKeyReleased

    private void chkRVentaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkRVentaStateChanged
        if (chkRVenta.isSelected()){
            p_venta="1";
        }else{
            p_venta="0";
        }
    }//GEN-LAST:event_chkRVentaStateChanged

    private void chkRCompraStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkRCompraStateChanged
        if (chkRCompra.isSelected()){
            p_compra="1";
        }else{
            p_compra="0";
        }
    }//GEN-LAST:event_chkRCompraStateChanged

    private void chkRProductoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkRProductoStateChanged
        if (chkRProducto.isSelected()){
            p_producto="1";
        }else{
            p_producto="0";
        }
    }//GEN-LAST:event_chkRProductoStateChanged

    private void chkRProveedorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkRProveedorStateChanged
        if (chkRProveedor.isSelected()){
            p_proveedor="1";
        }else{
            p_proveedor="0";
        }
    }//GEN-LAST:event_chkRProveedorStateChanged

    private void chkREmpleadoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkREmpleadoStateChanged
        if (chkREmpleado.isSelected()){
            p_empleado="1";
        }else{
            p_empleado="0";
        }
    }//GEN-LAST:event_chkREmpleadoStateChanged

    private void chkRClienteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkRClienteStateChanged
        if (chkRCliente.isSelected()){
            p_cliente="1";
        }else{
            p_cliente="0";
        }
    }//GEN-LAST:event_chkRClienteStateChanged

    private void chkRCategoriaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkRCategoriaStateChanged
        if (chkRCategoria.isSelected()){
            p_categoria="1";
        }else{
            p_categoria="0";
        }
    }//GEN-LAST:event_chkRCategoriaStateChanged

    private void chkRTipodocStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkRTipodocStateChanged
        if (chkRTipodoc.isSelected()){
            p_tipodoc="1";
        }else{
            p_tipodoc="0";
        }
    }//GEN-LAST:event_chkRTipodocStateChanged

    private void chkRTipouserStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkRTipouserStateChanged
        if (chkRTipouser.isSelected()){
            p_tipouser="1";
        }else{
            p_tipouser="0";
        }
    }//GEN-LAST:event_chkRTipouserStateChanged

    private void chkAnularvStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkAnularvStateChanged
        if (chkAnularv.isSelected()){
            p_anularv="1";
        }else{
            p_anularv="0";
        }
    }//GEN-LAST:event_chkAnularvStateChanged

    private void chkAnularcStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkAnularcStateChanged
        if (chkAnularc.isSelected()){
            p_anularc="1";
        }else{
            p_anularc="0";
        }
    }//GEN-LAST:event_chkAnularcStateChanged

    private void chkEstadoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkEstadoStateChanged
        if (chkEstado.isSelected()){
            p_estadoprod="1";
        }else{
            p_estadoprod="0";
        }
    }//GEN-LAST:event_chkEstadoStateChanged

    private void chkVentareStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkVentareStateChanged
        if (chkVentare.isSelected()){
            p_ventare="1";
        }else{
            p_ventare="0";
        }
    }//GEN-LAST:event_chkVentareStateChanged

    private void chkVentadeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkVentadeStateChanged
        if (chkVentade.isSelected()){
            p_ventade="1";
        }else{
            p_ventade="0";
        }
    }//GEN-LAST:event_chkVentadeStateChanged

    private void chkEstadisticaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkEstadisticaStateChanged
        if (chkEstadistica.isSelected()){
            p_estadistica="1";
        }else{
            p_estadistica="0";
        }
    }//GEN-LAST:event_chkEstadisticaStateChanged

    private void chkComprareStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkComprareStateChanged
        if (chkComprare.isSelected()){
            p_comprare="1";
        }else{
            p_comprare="0";
        }
    }//GEN-LAST:event_chkComprareStateChanged

    private void chkCompradeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkCompradeStateChanged
        if (chkComprade.isSelected()){
            p_comprade="1";
        }else{
            p_comprade="0";
        }
    }//GEN-LAST:event_chkCompradeStateChanged

    private void chkPassStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkPassStateChanged
        if (chkPass.isSelected()){
            p_pass="1";
        }else{
            p_pass="0";
        }
    }//GEN-LAST:event_chkPassStateChanged

    private void chkRespaldarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkRespaldarStateChanged
        if (chkRespaldar.isSelected()){
            p_respaldar="1";
        }else{
            p_respaldar="0";
        }
    }//GEN-LAST:event_chkRespaldarStateChanged

    private void chkRestaurarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkRestaurarStateChanged
        if (chkRestaurar.isSelected()){
            p_restaurar="1";
        }else{
            p_restaurar="0";
        }
    }//GEN-LAST:event_chkRestaurarStateChanged

    private void chkCajaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkCajaStateChanged
        if (chkCaja.isSelected()){
            p_caja="1";
        }else{
            p_caja="0";
        }
    }//GEN-LAST:event_chkCajaStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox chkAnularc;
    private javax.swing.JCheckBox chkAnularv;
    private javax.swing.JCheckBox chkCaja;
    private javax.swing.JCheckBox chkComprade;
    private javax.swing.JCheckBox chkComprare;
    private javax.swing.JCheckBox chkEstadistica;
    private javax.swing.JCheckBox chkEstado;
    private javax.swing.JCheckBox chkPass;
    private javax.swing.JCheckBox chkRCategoria;
    private javax.swing.JCheckBox chkRCliente;
    private javax.swing.JCheckBox chkRCompra;
    private javax.swing.JCheckBox chkREmpleado;
    private javax.swing.JCheckBox chkRProducto;
    private javax.swing.JCheckBox chkRProveedor;
    private javax.swing.JCheckBox chkRTipodoc;
    private javax.swing.JCheckBox chkRTipouser;
    private javax.swing.JCheckBox chkRVenta;
    private javax.swing.JCheckBox chkRespaldar;
    private javax.swing.JCheckBox chkRestaurar;
    private javax.swing.JCheckBox chkVentade;
    private javax.swing.JCheckBox chkVentare;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JPanel pBuscar;
    private javax.swing.JPanel pNuevo;
    private javax.swing.JRadioButton rbtnCodigo;
    private javax.swing.JRadioButton rbtnDescripcion;
    private javax.swing.JTabbedPane tabTipoUsuario;
    private javax.swing.JTable tblTipoUsuario;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables
}
