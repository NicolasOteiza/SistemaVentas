/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Backup;

import java.io.File;
import javax.swing.JFileChooser;

public class FrmRestaurarDB extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmRestaurarDB
     */
    public FrmRestaurarDB() {
        initComponents();
        this.setSize(527, 358);
    }

class MyFilter extends javax.swing.filechooser.FileFilter {

        public boolean accept(File file) {
            String filename = file.getName();
            return filename.endsWith(".sql");
        }

        public String getDescription() {
            return "*.sql";
        }
  }   
  public boolean restoreDB(String dbUserName, String dbPassword, String source) {

        String[] executeCmd = new String[]{"mysql", "--user=" + dbUserName, "--password=" + dbPassword, "-e", "source " + source};

        Process runtimeProcess;
        try {

            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                lblMessage.setText("<html><font color='green'>!Backup restaurado con éxito!</font></html>");
                return true;
            } else {
                lblMessage.setText("<html><font color='red'>No se pudo restaurar la copia de seguridad!</font></html>");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMessage = new javax.swing.JLabel();
        btnRestaurar = new javax.swing.JButton();
        txtRuta = new javax.swing.JTextField();
        btnSeleccionar = new javax.swing.JButton();
        txtUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setTitle(".:: Restarurar Base de Datos ::.");
        setToolTipText("");
        getContentPane().setLayout(null);
        getContentPane().add(lblMessage);
        lblMessage.setBounds(30, 270, 450, 30);

        btnRestaurar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRestaurar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/database_refresh.png"))); // NOI18N
        btnRestaurar.setText("Restaurar");
        btnRestaurar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRestaurar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestaurarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRestaurar);
        btnRestaurar.setBounds(150, 170, 100, 90);

        txtRuta.setEditable(false);
        getContentPane().add(txtRuta);
        txtRuta.setBounds(140, 120, 220, 30);

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSeleccionar);
        btnSeleccionar.setBounds(370, 120, 110, 30);

        txtUsuario.setText("root");
        getContentPane().add(txtUsuario);
        txtUsuario.setBounds(140, 40, 150, 30);

        jLabel2.setText("Usuario:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(80, 40, 60, 30);

        jLabel3.setText("Contraseña:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(60, 80, 80, 30);

        jLabel5.setText("Seleccionar archivo:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(26, 120, 110, 30);

        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Información de Restauración"));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 490, 310);
        getContentPane().add(txtContraseña);
        txtContraseña.setBounds(140, 80, 150, 30);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancel.png"))); // NOI18N
        jButton1.setText("Cancelar");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(260, 170, 100, 90);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestaurarActionPerformed

        String user;
        String pass;

        user=txtUsuario.getText();
        pass=txtContraseña.getText();

        if (txtRuta.getText().equals("")) {
            lblMessage.setText("<html><font color='red'>¡Porfavor seleccione el archivo a restaurar!</font></html>");
        } else {
            restoreDB(user,pass, txtRuta.getText().toString());
        }
    }//GEN-LAST:event_btnRestaurarActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed

        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new MyFilter());
        //fc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);

        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            txtRuta.setText(file.toString());
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRestaurar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtRuta;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
