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


public class FrmCambiarContraseña extends javax.swing.JInternalFrame {

    String strCodigo;
    public String IdEmpleado;
        //algoritmos
    public static String MD2 = "MD2";
    public static String MD5 = "MD5";
    public static String SHA1 = "SHA-1";
    public static String SHA256 = "SHA-256";
    public static String SHA384 = "SHA-384";
    public static String SHA512 = "SHA-512";
    
    public FrmCambiarContraseña() {
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
        txtContraseña = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setTitle("Cambiar Contraseña");
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingrese nueva contraseña"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Nueva contraseña:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 35, 110, 30));

        txtContraseña.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContraseñaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContraseñaKeyTyped(evt);
            }
        });
        jPanel1.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 35, 130, 30));

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
//----------------------VALIDACIÓN DE DATOS-------------------------------------
    public boolean validardatos(){
        if (txtContraseña.getText().equals("")){
            JOptionPane.showMessageDialog(null, "¡Ingrese una contraseña!");
            txtContraseña.requestFocus();
            txtContraseña.setBackground(Color.YELLOW);
            return false;

        }else{
            return true;
        }

    }
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
    String algorithm="SHA-512";  
    String resultado,contraseña=null;
        
    if(validardatos()==true){ 
        ClsEmpleado empleados=new ClsEmpleado();
        ClsEntidadEmpleado empleado=new ClsEntidadEmpleado();
        contraseña=txtContraseña.getText();
        resultado=getStringMessageDigest(contraseña,algorithm);   
        empleado.setStrContraseñaEmpleado(resultado);
        empleados.cambiarContraseña(IdEmpleado, empleado);
        txtContraseña.setText("");
    }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtContraseñaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseñaKeyReleased
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) btnGuardar.requestFocus();
    }//GEN-LAST:event_txtContraseñaKeyReleased

    private void txtContraseñaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseñaKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
        txtContraseña.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtContraseñaKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtContraseña;
    // End of variables declaration//GEN-END:variables
}
