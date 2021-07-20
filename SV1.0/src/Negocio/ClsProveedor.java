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

public class ClsProveedor {
private Connection connection=new ClsConexion().getConection();
    //--------------------------------------------------------------------------------------------------
    //-----------------------------------------METODOS--------------------------------------------------
    //-------------------------------------------------------------------------------------------------- 
    public void agregarProveedor(ClsEntidadProveedor Proveedor){
        try{
            CallableStatement statement=connection.prepareCall("{call SP_I_Proveedor(?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pnombre",Proveedor.getStrNombreProveedor());
            statement.setString("prol",Proveedor.getStrRolProveedor());
            statement.setString("pRut",Proveedor.getStrRutProveedor());
            statement.setString("pdireccion",Proveedor.getStrDireccionProveedor());
            statement.setString("ptelefono",Proveedor.getStrTelefonoProveedor());
            statement.setString("pcelular",Proveedor.getStrCelularProveedor());
            statement.setString("pemail",Proveedor.getStrEmailProveedor());
            statement.setString("pcuenta1",Proveedor.getStrCuenta1Proveedor());
            statement.setString("pcuenta2",Proveedor.getStrCuenta2Proveedor());
            statement.setString("pestado",Proveedor.getStrEstadoProveedor());
            statement.setString("pobsv",Proveedor.getStrObsvProveedor());
            statement.execute();

            JOptionPane.showMessageDialog(null,"¡Proveedor Agregado con éxito!","Mensaje del Sistema",1);           

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }    
    public void modificarProveedor(String codigo,ClsEntidadProveedor Proveedor){
        try{
            CallableStatement statement=connection.prepareCall("{call SP_U_Proveedor(?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pidproveedor",codigo);
            statement.setString("pnombre",Proveedor.getStrNombreProveedor());
            statement.setString("prol",Proveedor.getStrRolProveedor());
            statement.setString("prut",Proveedor.getStrRutProveedor());
            statement.setString("pdireccion",Proveedor.getStrDireccionProveedor());
            statement.setString("ptelefono",Proveedor.getStrTelefonoProveedor());
            statement.setString("pcelular",Proveedor.getStrCelularProveedor());
            statement.setString("pemail",Proveedor.getStrEmailProveedor());
            statement.setString("pcuenta1",Proveedor.getStrCuenta1Proveedor());
            statement.setString("pcuenta2",Proveedor.getStrCuenta2Proveedor());
            statement.setString("pestado",Proveedor.getStrEstadoProveedor());
            statement.setString("pobsv",Proveedor.getStrObsvProveedor());
            statement.executeUpdate();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        JOptionPane.showMessageDialog(null,"¡Proveedor Actualizado!","Mensaje del Sistema",1);
    }
    public ArrayList<ClsEntidadProveedor> listarProveedor(){
        ArrayList<ClsEntidadProveedor> proveedores=new ArrayList<ClsEntidadProveedor>();
        try{
            CallableStatement statement=connection.prepareCall("{call SP_S_Proveedor}");
            ResultSet resultSet=statement.executeQuery();
            
            while (resultSet.next()){
                ClsEntidadProveedor proveedor=new ClsEntidadProveedor();
                proveedor.setStrIdProveedor(resultSet.getString("IdProveedor"));
                proveedor.setStrNombreProveedor(resultSet.getString("nombre"));
                proveedor.setStrRolProveedor(resultSet.getString("rol"));
                proveedor.setStrRutProveedor(resultSet.getString("rut"));
                proveedor.setStrDireccionProveedor(resultSet.getString("direccion"));
                proveedor.setStrTelefonoProveedor(resultSet.getString("telefono"));
                proveedor.setStrCelularProveedor(resultSet.getString("celular"));
                proveedor.setStrEmailProveedor(resultSet.getString("email"));
                proveedor.setStrCuenta1Proveedor(resultSet.getString("cuenta1"));
                proveedor.setStrCuenta2Proveedor(resultSet.getString("cuenta2"));
                proveedor.setStrEstadoProveedor(resultSet.getString("estado"));
                proveedor.setStrObsvProveedor(resultSet.getString("obsv"));
                proveedores.add(proveedor);
            }
            return proveedores;
         }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    public ResultSet listarProveedorPorParametro(String criterio, String busqueda) throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = connection.prepareCall("{call SP_S_ProveedorPorParametro(?,?)}");
            statement.setString("pcriterio", criterio);
            statement.setString("pbusqueda", busqueda);
            rs = statement.executeQuery();
            return rs;
        }catch(SQLException SQLex){
            throw SQLex;            
        }        
    }
}
