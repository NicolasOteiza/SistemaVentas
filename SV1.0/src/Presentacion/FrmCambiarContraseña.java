/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Entidad.ClsEntidadEmpleado;
import Negocio.ClsEmpleado;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;


public class FrmCambiarContrase├▒a extends javax.swing.JInternalFrame {

    String strCodigo;
    public String IdEmpleado;
        //algoritmos
    public static String MD2 = "MD2";
    public static String MD5 = "MD5";
    public static String SHA1 = "SHA-1";
    public static String SHA256 = "SHA-256";
    public static String SHA384 = "SHA-384";
    public static String SHA512 = "SHA-512";
    
    public FrmCambiarContrase├▒a() {
        initComponents();
        //---------------------ANCHO Y ALTO DEL FORM----------------------
        this.setSize(464, 147);
    }

   //-----------------------ENCRIPTACION - SHA----------------------------------------
    private static String toHexadecimal(byte[] digest)
    {
        String hash = "";
        for(byte aux : digest) 
        {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) hash += "0";
            hash += Integer.toHexString(b);
        }
        return hash;
    }
    
    public static String getStringMessageDigest(String message, String algorithm)
    {
        byte[] digest = null;
        byte[] buffer = message.getBytes();
        try 
        {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.reset();
            messageDigest.update(buffer);
            digest = messageDigest.digest();
        }
        catch (NoSuchAlgorithmException ex) 
        {
            System.out.println("Error creando Digest");
        }
        return toHexadecimal(digest);
    } 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtContrase├▒a = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setTitle("Cambiar Contrase├▒a");
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingrese nueva contrase├▒a"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Nueva contrase├▒a:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 35, 110, 30));

        txtContrase├▒a.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtContrase├▒a.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContrase├▒aKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContrase├▒aKeyTyped(evt);
            }
        });
        jPanel1.add(txtContrase├▒a, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 35, 130, 30));

        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/guardar_compra.png"))); // NOI18N
        btnGuardar.setText("Cambiar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 140, 60));

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 10, 430, 100);

        pack();
    }// </editor-fold>//GEN-END:initComponents
//----------------------VALIDACI├ôN DE DATOS-------------------------------------
    public boolean validardatos(){
        if (txtContrase├▒a.getText().equals("")){
            JOptionPane.showMessageDialog(null, "┬íIngrese una contrase├▒a!");
            txtContrase├▒a.requestFocus();
            txtContrase├▒a.setBackground(Color.YELLOW);
            return false;

        }else{
            return true;
        }

    }
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
    String algorithm="SHA-512";  
    String resultado,contrase├▒a=null;
        
    if(validardatos()==true){ 
        ClsEmpleado empleados=new ClsEmpleado();
        ClsEntidadEmpleado empleado=new ClsEntidadEmpleado();
        contrase├▒a=txtContrase├▒a.getText();
        resultado=getStringMessageDigest(contrase├▒a,algorithm);   
        empleado.setStrContrase├▒aEmpleado(resultado);
        empleados.cambiarContrase├▒a(IdEmpleado, empleado);
        txtContrase├▒a.setText("");
    }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtContrase├▒aKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContrase├▒aKeyReleased
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) btnGuardar.requestFocus();
    }//GEN-LAST:event_txtContrase├▒aKeyReleased

    private void txtContrase├▒aKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContrase├▒aKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
        txtContrase├▒a.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtContrase├▒aKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtContrase├▒a;
    // End of variables declaration//GEN-END:variables
}
