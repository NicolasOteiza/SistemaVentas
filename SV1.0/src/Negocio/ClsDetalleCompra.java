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

public class ClsDetalleCompra {
private Connection connection=new ClsConexion().getConection();
    //--------------------------------------------------------------------------------------------------
    //-----------------------------------------METODOS--------------------------------------------------
    //-------------------------------------------------------------------------------------------------- 
    public void agregarDetalleCompra(ClsEntidadDetalleCompra DetalleCompra){
        try{
            CallableStatement statement=connection.prepareCall("{call SP_I_DetalleCompra(?,?,?,?,?)}");
            statement.setString("pidcompra",DetalleCompra.getStrIdCompra());
            statement.setString("pidproducto",DetalleCompra.getStrIdProducto());
            statement.setString("pcantidad",DetalleCompra.getStrCantidadDet());
            statement.setString("pprecio",DetalleCompra.getStrPrecioDet());
            statement.setString("ptotal",DetalleCompra.getStrTotalDet());
            statement.execute();

            //JOptionPane.showMessageDialog(null,"¡Compra Realizada con éxito!","Mensaje del Sistema",1);

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }    
    public void modificarDetalleCompra(String codigo,ClsEntidadDetalleCompra DetalleCompra){
        try{
            CallableStatement statement=connection.prepareCall("{call SP_U_DetalleCompra(?,?,?,?,?)}");
            statement.setString("pidcompra",codigo);
            statement.setString("pidproducto",DetalleCompra.getStrIdProducto());
            statement.setString("pcantidad",DetalleCompra.getStrCantidadDet());
            statement.setString("pprecio",DetalleCompra.getStrPrecioDet());
            statement.setString("ptotal",DetalleCompra.getStrTotalDet());
            statement.executeUpdate();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        JOptionPane.showMessageDialog(null,"¡Compra Actualizada!","Mensaje del Sistema",1);
    }
    
    public ResultSet listarDetalleCompraPorParametro(String criterio, String busqueda) throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = connection.prepareCall("{call SP_S_DetalleCompraPorParametro(?,?)}");
            statement.setString("pcriterio", criterio);
            statement.setString("pbusqueda", busqueda);
            rs = statement.executeQuery();
            return rs;
        }catch(SQLException SQLex){
            throw SQLex;            
        }        
    }     

       
}
