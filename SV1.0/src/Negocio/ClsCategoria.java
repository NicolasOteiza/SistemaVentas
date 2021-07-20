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

public class ClsCategoria {
 private Connection connection=new ClsConexion().getConection();
    //--------------------------------------------------------------------------------------------------
    //-----------------------------------------METODOS--------------------------------------------------
    //-------------------------------------------------------------------------------------------------- 
    public void agregarCategoria(ClsEntidadCategoria Categoria){
        try{
            CallableStatement statement=connection.prepareCall("{call SP_I_Categoria(?)}");
            statement.setString("pdescripcion",Categoria.getStrDescripcionCategoria());
            statement.execute();

            JOptionPane.showMessageDialog(null,"¡Categoría Agregada con éxito!","Mensaje del Sistema",1);

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }    
    public void modificarCategoria(String codigo,ClsEntidadCategoria Categoria){
        try{
            CallableStatement statement=connection.prepareCall("{call SP_U_Categoria(?,?)}");
            statement.setString("pidcategoria",codigo);
            statement.setString("pdescripcion",Categoria.getStrDescripcionCategoria());           
            statement.executeUpdate();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        JOptionPane.showMessageDialog(null,"¡Categoría Actualizada!","Mensaje del Sistema",1);
    }
    public ArrayList<ClsEntidadCategoria> listarCategoria(){
        ArrayList<ClsEntidadCategoria> categorias=new ArrayList<ClsEntidadCategoria>();
        try{
            CallableStatement statement=connection.prepareCall("{call SP_S_Categoria}");
            ResultSet resultSet=statement.executeQuery();
            
            while (resultSet.next()){
                ClsEntidadCategoria categoria=new ClsEntidadCategoria();
                categoria.setStrIdCategoria(resultSet.getString("IdCategoria"));
                categoria.setStrDescripcionCategoria(resultSet.getString("Descripcion"));
                categorias.add(categoria);
            }
            return categorias;
         }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }    
    public ResultSet listarCategoriaPorParametro(String criterio, String busqueda) throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = connection.prepareCall("{call SP_S_CategoriaPorParametro(?,?)}");
            statement.setString("pcriterio", criterio);
            statement.setString("pbusqueda", busqueda);
            rs = statement.executeQuery();
            return rs;
        }catch(SQLException SQLex){
            throw SQLex;            
        }        
    }
}
