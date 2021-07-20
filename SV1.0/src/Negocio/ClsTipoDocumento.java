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

public class ClsTipoDocumento {
private Connection connection=new ClsConexion().getConection();
    //--------------------------------------------------------------------------------------------------
    //-----------------------------------------METODOS--------------------------------------------------
    //-------------------------------------------------------------------------------------------------- 
    public void agregarTipoDocumento(ClsEntidadTipoDocumento TipoDocumento){
        try{
            CallableStatement statement=connection.prepareCall("{call SP_I_TipoDocumento(?)}");
            statement.setString("pdescripcion",TipoDocumento.getStrDescripcionTipoDocumento());
            statement.execute();

            JOptionPane.showMessageDialog(null,"¡Tipo de Documento Agregado con éxito!","Mensaje del Sistema",1);

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }    
    public void modificarTipoDocumento(String codigo,ClsEntidadTipoDocumento TipoDocumento){
        try{
            CallableStatement statement=connection.prepareCall("{call SP_U_TipoDocumento(?,?)}");
            statement.setString("pidtipodocumento",codigo);
            statement.setString("pdescripcion",TipoDocumento.getStrDescripcionTipoDocumento());           
            statement.executeUpdate();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        JOptionPane.showMessageDialog(null,"¡Tipo de Documento Actualizado!","Mensaje del Sistema",1);
    }
    
    public ArrayList<ClsEntidadTipoDocumento> listarTipoDocumento(){
        ArrayList<ClsEntidadTipoDocumento> tipodocumentos=new ArrayList<ClsEntidadTipoDocumento>();
        try{
            CallableStatement statement=connection.prepareCall("{call SP_S_TipoDocumento}");
            ResultSet resultSet=statement.executeQuery();
            
            while (resultSet.next()){
                ClsEntidadTipoDocumento categoria=new ClsEntidadTipoDocumento();
                categoria.setStrIdTipoDocumento(resultSet.getString("IdTipoDocumento"));
                categoria.setStrDescripcionTipoDocumento(resultSet.getString("Descripcion"));
                tipodocumentos.add(categoria);
            }
            return tipodocumentos;
         }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
         
    public ResultSet listarTipoDocumentoPorParametro(String criterio, String busqueda) throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = connection.prepareCall("{call SP_S_TipoDocumentoPorParametro(?,?)}");
            statement.setString("pcriterio", criterio);
            statement.setString("pbusqueda", busqueda);
            rs = statement.executeQuery();
            return rs;
        }catch(SQLException SQLex){
            throw SQLex;            
        }        
    }

}
