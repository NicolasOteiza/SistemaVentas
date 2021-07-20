/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.*;
import Entidad.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClsTipoUsuario 
{
    private Connection connection=new ClsConexion().getConection();
    //----------------------------METODOS---------------------------------------------------------
    //--------------Son VERBOS, si tiene algo de texto en singular y en español-------------------       
    public void agregarTipoUsuario(ClsEntidadTipoUsuario TipoUsuario){
        try{
            CallableStatement statement=connection.prepareCall("{call SP_I_TipoUsuario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pdescripcion",TipoUsuario.getStrDescripcionTipoUsuario());
            statement.setString("pp_venta",TipoUsuario.getStrP_Venta());
            statement.setString("pp_compra",TipoUsuario.getStrP_Compra());
            statement.setString("pp_producto",TipoUsuario.getStrP_Producto());
            statement.setString("pp_proveedor",TipoUsuario.getStrP_Proveedor());
            statement.setString("pp_empleado",TipoUsuario.getStrP_Empleado());
            statement.setString("pp_cliente",TipoUsuario.getStrP_Cliente());
            statement.setString("pp_categoria",TipoUsuario.getStrP_Categoria());
            statement.setString("pp_tipodoc",TipoUsuario.getStrP_Tipodoc());
            statement.setString("pp_tipouser",TipoUsuario.getStrP_Tipouser());
            statement.setString("pp_anularv",TipoUsuario.getStrP_Anularv());
            statement.setString("pp_anularc",TipoUsuario.getStrP_Anularc());
            statement.setString("pp_estadoprod",TipoUsuario.getStrP_Estadoprod());
            statement.setString("pp_ventare",TipoUsuario.getStrP_Ventare());
            statement.setString("pp_ventade",TipoUsuario.getStrP_Ventade());
            statement.setString("pp_estadistica",TipoUsuario.getStrP_Estadistica());
            statement.setString("pp_comprare",TipoUsuario.getStrP_Comprare());
            statement.setString("pp_comprade",TipoUsuario.getStrP_Comprade());
            statement.setString("pp_pass",TipoUsuario.getStrP_Pass());
            statement.setString("pp_respaldar",TipoUsuario.getStrP_Respaldar());
            statement.setString("pp_restaurar",TipoUsuario.getStrP_Restaurar());
            statement.setString("pp_caja",TipoUsuario.getStrP_Caja());
            
            statement.execute();
            JOptionPane.showMessageDialog(null,"¡Tipo de Usuario Agregado con éxito!","Mensaje del Sistema",1);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }    
    public void modificarTipoUsuario(String codigo,ClsEntidadTipoUsuario TipoUsuario){
        try{
            CallableStatement statement=connection.prepareCall("{call SP_U_TipoUsuario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pidtipousuario",codigo);
            statement.setString("pdescripcion",TipoUsuario.getStrDescripcionTipoUsuario());
            statement.setString("pp_venta",TipoUsuario.getStrP_Venta());
            statement.setString("pp_compra",TipoUsuario.getStrP_Compra());
            statement.setString("pp_producto",TipoUsuario.getStrP_Producto());
            statement.setString("pp_proveedor",TipoUsuario.getStrP_Proveedor());
            statement.setString("pp_empleado",TipoUsuario.getStrP_Empleado());
            statement.setString("pp_cliente",TipoUsuario.getStrP_Cliente());
            statement.setString("pp_categoria",TipoUsuario.getStrP_Categoria());
            statement.setString("pp_tipodoc",TipoUsuario.getStrP_Tipodoc());
            statement.setString("pp_tipouser",TipoUsuario.getStrP_Tipouser());
            statement.setString("pp_anularv",TipoUsuario.getStrP_Anularv());
            statement.setString("pp_anularc",TipoUsuario.getStrP_Anularc()); 
            statement.setString("pp_estadoprod",TipoUsuario.getStrP_Estadoprod());
            statement.setString("pp_ventare",TipoUsuario.getStrP_Ventare());
            statement.setString("pp_ventade",TipoUsuario.getStrP_Ventade());
            statement.setString("pp_estadistica",TipoUsuario.getStrP_Estadistica());
            statement.setString("pp_comprare",TipoUsuario.getStrP_Comprare());
            statement.setString("pp_comprade",TipoUsuario.getStrP_Comprade());
            statement.setString("pp_pass",TipoUsuario.getStrP_Pass());
            statement.setString("pp_respaldar",TipoUsuario.getStrP_Respaldar());
            statement.setString("pp_restaurar",TipoUsuario.getStrP_Restaurar());
            statement.setString("pp_caja",TipoUsuario.getStrP_Caja());
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        JOptionPane.showMessageDialog(null,"¡Tipo de Usuario Actualizado!","Mensaje del Sistema",1);
    }
    public ArrayList<ClsEntidadTipoUsuario> listarTipoUsuario(){
        ArrayList<ClsEntidadTipoUsuario> tipousuariousuarios=new ArrayList<ClsEntidadTipoUsuario>();
        try{
            CallableStatement statement=connection.prepareCall("{call SP_S_TipoUsuario}");
            ResultSet resultSet=statement.executeQuery();
            
            while (resultSet.next()){
                ClsEntidadTipoUsuario tipousuario=new ClsEntidadTipoUsuario();
                tipousuario.setStrIdTipoUsuario(resultSet.getString("IdTipoUsuario"));
                tipousuario.setStrDescripcionTipoUsuario(resultSet.getString("Descripcion"));
                tipousuario.setStrP_Venta(resultSet.getString("p_venta"));
                tipousuario.setStrP_Compra(resultSet.getString("p_compra"));
                tipousuario.setStrP_Producto(resultSet.getString("p_producto"));
                tipousuario.setStrP_Proveedor(resultSet.getString("p_proveedor"));
                tipousuario.setStrP_Empleado(resultSet.getString("p_empleado"));
                tipousuario.setStrP_Cliente(resultSet.getString("p_cliente"));
                tipousuario.setStrP_Categoria(resultSet.getString("p_categoria"));
                tipousuario.setStrP_Tipodoc(resultSet.getString("p_tipodoc"));
                tipousuario.setStrP_Tipouser(resultSet.getString("p_tipouser"));
                tipousuario.setStrP_Anularv(resultSet.getString("p_anularv"));
                tipousuario.setStrP_Anularc(resultSet.getString("p_anularc"));
                tipousuario.setStrP_Estadoprod(resultSet.getString("p_estadoprod"));
                tipousuario.setStrP_Ventare(resultSet.getString("p_ventare"));
                tipousuario.setStrP_Ventade(resultSet.getString("p_ventade"));
                tipousuario.setStrP_Estadistica(resultSet.getString("p_estadistica"));
                tipousuario.setStrP_Comprare(resultSet.getString("p_comprare"));
                tipousuario.setStrP_Comprade(resultSet.getString("p_comprade"));
                tipousuario.setStrP_Pass(resultSet.getString("p_pass"));
                tipousuario.setStrP_Respaldar(resultSet.getString("p_respaldar"));
                tipousuario.setStrP_Restaurar(resultSet.getString("p_restaurar"));
                tipousuario.setStrP_Caja(resultSet.getString("p_caja"));

                tipousuariousuarios.add(tipousuario);
            }
            return tipousuariousuarios;
         }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
        public ResultSet listarTipoUsuarioPorParametro(String criterio, String busqueda) throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = connection.prepareCall("{call SP_S_TipoUsuarioPorParametro(?,?)}");
            statement.setString("pcriterio", criterio);
            statement.setString("pbusqueda", busqueda);
            rs = statement.executeQuery();
            return rs;
        }catch(SQLException SQLex){
            throw SQLex;            
        }        
    } 
    public ResultSet consultarLoginPermisos(String nomuser, String tipouser) throws Exception{
    
        ResultSet rs=null;
        try{
            CallableStatement statement=connection.prepareCall("{call SP_S_LoginPermisos(?,?)}");
            statement.setString("pnombre_usuario", nomuser);
            statement.setString("pdescripcion_tipousuario", tipouser);
            rs=statement.executeQuery();
            return rs;
        }catch(SQLException SQLex){
            throw SQLex;
        }
    
    }     


   
}
