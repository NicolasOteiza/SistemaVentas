/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Entidad.*;
import Negocio.*;
import java.awt.Image;
import java.sql.ResultSet;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


/**
 *
 * @author Edgar
 */
public class FrmProductoEstado extends javax.swing.JInternalFrame {

static ResultSet rs=null;
    public FrmProductoEstado() {
        initComponents();
        //---------------------ANCHO Y ALTO DEL FORM----------------------
        this.setSize(711, 335);
    }
//-----------------------------------------------------------------------------------------------
//--------------------------------------METODOS--------------------------------------------------
//-----------------------------------------------------------------------------------------------
 void BuscarProductoPorCodigo(){
        String busqueda=null;
        busqueda=txtCodigoProducto.getText(); 
            try{
                ClsProducto oProducto=new ClsProducto();
                
                rs= oProducto.listarProductoActivoPorParametro("codigo",busqueda);
                while (rs.next()) {
                    if(rs.getString(2).equals(busqueda)) {
        
                            lblCodigo.setText(rs.getString(2));
                            lblNombre.setText(rs.getString(3));
                            lblDescripcion.setText(rs.getString(4));
                            lblStock.setText(rs.getString(5));
                            lblStockMin.setText(rs.getString(6));
                            lblPrecioCosto.setText(rs.getString(7));
                            lblPrecioVenta.setText(rs.getString(8));
                            lblUtilidad.setText(rs.getString(9));
                            lblEstado.setText(rs.getString(10));
                            lblCategoria.setText(rs.getString(11));
                            String imagen=rs.getString(12);
                            String ruta="src/Images/"+imagen;
                            ImageIcon fot = new ImageIcon(ruta);
                            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_DEFAULT));
                            lblImagen.setIcon(icono);
                            lblImagen.repaint();
          
                    }
                   break;
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,ex.getMessage());
                System.out.println(ex.getMessage());
            }
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtCodigoProducto = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lblDatos = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        lblStock = new javax.swing.JLabel();
        lblStockMin = new javax.swing.JLabel();
        lblPrecioCosto = new javax.swing.JLabel();
        lblPrecioVenta = new javax.swing.JLabel();
        lblUtilidad = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        lblCategoria = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setTitle("Verificar Producto");
        getContentPane().setLayout(null);

        jLabel1.setText("Código de Barras:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 11, 150, 16);

        txtCodigoProducto.setBackground(new java.awt.Color(255, 255, 204));
        txtCodigoProducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCodigoProducto.setForeground(new java.awt.Color(0, 102, 204));
        txtCodigoProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoProductoKeyReleased(evt);
            }
        });
        getContentPane().add(txtCodigoProducto);
        txtCodigoProducto.setBounds(10, 30, 170, 30);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(null);

        lblDatos.setBackground(new java.awt.Color(0, 102, 204));
        lblDatos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblDatos.setText("Código:");
        jPanel1.add(lblDatos);
        lblDatos.setBounds(20, 10, 80, 20);

        jLabel2.setBackground(new java.awt.Color(0, 102, 204));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Nombre:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 30, 80, 20);

        jLabel3.setBackground(new java.awt.Color(0, 102, 204));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Descripción:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 50, 80, 20);

        jLabel4.setBackground(new java.awt.Color(0, 102, 204));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Stock:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 70, 80, 20);

        jLabel5.setBackground(new java.awt.Color(0, 102, 204));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Stock Mínimo:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(20, 90, 80, 20);

        jLabel6.setBackground(new java.awt.Color(0, 102, 204));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Precio Costo:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(20, 110, 80, 20);

        jLabel7.setBackground(new java.awt.Color(0, 102, 204));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Precio Venta:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(20, 130, 80, 20);

        jLabel8.setBackground(new java.awt.Color(0, 102, 204));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Utilidad:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(20, 150, 80, 20);

        jLabel9.setBackground(new java.awt.Color(0, 102, 204));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Estado:");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(20, 170, 80, 20);

        jLabel10.setBackground(new java.awt.Color(0, 102, 204));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Categoría:");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(20, 190, 80, 20);

        lblCodigo.setBackground(new java.awt.Color(209, 249, 249));
        jPanel1.add(lblCodigo);
        lblCodigo.setBounds(110, 10, 120, 20);

        lblNombre.setBackground(new java.awt.Color(209, 249, 249));
        jPanel1.add(lblNombre);
        lblNombre.setBounds(110, 30, 270, 20);

        lblDescripcion.setBackground(new java.awt.Color(209, 249, 249));
        jPanel1.add(lblDescripcion);
        lblDescripcion.setBounds(110, 50, 380, 20);

        lblStock.setBackground(new java.awt.Color(209, 249, 249));
        jPanel1.add(lblStock);
        lblStock.setBounds(110, 70, 80, 20);

        lblStockMin.setBackground(new java.awt.Color(209, 249, 249));
        jPanel1.add(lblStockMin);
        lblStockMin.setBounds(110, 90, 80, 20);

        lblPrecioCosto.setBackground(new java.awt.Color(209, 249, 249));
        jPanel1.add(lblPrecioCosto);
        lblPrecioCosto.setBounds(110, 110, 80, 20);

        lblPrecioVenta.setBackground(new java.awt.Color(209, 249, 249));
        jPanel1.add(lblPrecioVenta);
        lblPrecioVenta.setBounds(110, 130, 80, 20);

        lblUtilidad.setBackground(new java.awt.Color(209, 249, 249));
        jPanel1.add(lblUtilidad);
        lblUtilidad.setBounds(110, 150, 80, 20);

        lblEstado.setBackground(new java.awt.Color(209, 249, 249));
        jPanel1.add(lblEstado);
        lblEstado.setBounds(110, 170, 160, 20);

        lblCategoria.setBackground(new java.awt.Color(209, 249, 249));
        jPanel1.add(lblCategoria);
        lblCategoria.setBounds(110, 190, 160, 20);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 70, 380, 230);

        lblImagen.setBackground(new java.awt.Color(255, 255, 153));
        lblImagen.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(lblImagen);
        lblImagen.setBounds(410, 10, 270, 280);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProductoKeyReleased
BuscarProductoPorCodigo();
    }//GEN-LAST:event_txtCodigoProductoKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDatos;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecioCosto;
    private javax.swing.JLabel lblPrecioVenta;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblStockMin;
    private javax.swing.JLabel lblUtilidad;
    private javax.swing.JTextField txtCodigoProducto;
    // End of variables declaration//GEN-END:variables
}
