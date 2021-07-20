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
import java.util.Date;
import javax.swing.JOptionPane;

public class ClsVenta {
private Connection connection=new ClsConexion().getConection();
    //--------------------------------------------------------------------------------------------------
    //-----------------------------------------METODOS--------------------------------------------------
    //-------------------------------------------------------------------------------------------------- 
    public void agregarVenta(ClsEntidadVenta Venta){
        try{
            CallableStatement statement=connection.prepareCall("{call SP_I_Venta(?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pidtipodocumento",Venta.getStrIdTipoDocumento());
            statement.setString("pidcliente",Venta.getStrIdCliente());
            statement.setString("pidempleado",Venta.getStrIdEmpleado());
            statement.setString("pserie",Venta.getStrSerieVenta());
            statement.setString("pnumero",Venta.getStrNumeroVenta());
            statement.setDate ("pfecha", new java.sql.Date(Venta.getStrFechaVenta().getTime()));
            statement.setString("ptotalventa",Venta.getStrTotalVenta());
            statement.setString("pdescuento",Venta.getStrDescuentoVenta());
            statement.setString("psubtotal",Venta.getStrSubTotalVenta());
            //statement.setString("piva",Venta.getStrIvaVenta());
            statement.setString("ptotalpagar",Venta.getStrTotalPagarVenta());
            statement.setString("pestado",Venta.getStrEstadoVenta());
            
            System.out.println("id tipo documento "+ Venta.getStrIdTipoDocumento());
            System.out.println("id cliente "+ Venta.getStrIdCliente());
            System.out.println("id empleado "+ Venta.getStrIdEmpleado());
            System.out.println("serie venta "+ Venta.getStrSerieVenta());
            System.out.println("nuemro venta "+ Venta.getStrNumeroVenta());
            System.out.println("fecha "+ new java.sql.Date(Venta.getStrFechaVenta().getTime()));
            System.out.println("total venta "+Venta.getStrTotalVenta());
            System.out.println("descuento "+Venta.getStrDescuentoVenta());
            System.out.println("sub-total "+Venta.getStrSubTotalVenta());
            System.out.println("total a pagar "+Venta.getStrTotalPagarVenta());
            System.out.println("estado "+Venta.getStrEstadoVenta());
            statement.execute();

            JOptionPane.showMessageDialog(null,"¡Venta Realizada con éxito!","Mensaje del Sistema",1);

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }    
    public void modificarVenta(String codigo,ClsEntidadVenta Venta){
        try{
            CallableStatement statement=connection.prepareCall("{call SP_U_Venta(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pidventa",codigo);
            statement.setString("pidtipodocumento",Venta.getStrIdTipoDocumento());
            statement.setString("pidcliente",Venta.getStrIdCliente());
            statement.setString("pidempleado",Venta.getStrIdEmpleado());
            statement.setString("pserie",Venta.getStrSerieVenta());
            statement.setString("pnumero",Venta.getStrNumeroVenta());
            statement.setDate ("pfecha", new java.sql.Date(Venta.getStrFechaVenta().getTime()));
            statement.setString("ptotalventa",Venta.getStrTotalVenta());
            statement.setString("pdescuento",Venta.getStrDescuentoVenta());
            statement.setString("psubtotal",Venta.getStrSubTotalVenta());
            statement.setString("piva",Venta.getStrIvaVenta());
            statement.setString("ptotalpagar",Venta.getStrTotalPagarVenta());
            statement.setString("pestado",Venta.getStrEstadoVenta());
            statement.executeUpdate();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        JOptionPane.showMessageDialog(null,"¡Venta Actualizada!","Mensaje del Sistema",1);
    }
    public ArrayList<ClsEntidadVenta> listarVenta(){
        ArrayList<ClsEntidadVenta> ventas=new ArrayList<ClsEntidadVenta>();
        try{
            CallableStatement statement=connection.prepareCall("{call SP_S_Venta}");
            ResultSet resultSet=statement.executeQuery();
            
            while (resultSet.next()){
                ClsEntidadVenta venta=new ClsEntidadVenta();
                venta.setStrIdVenta(resultSet.getString("IdVenta"));
                venta.setStrTipoDocumento(resultSet.getString("TipoDocumento"));
                venta.setStrCliente(resultSet.getString("Cliente"));
                venta.setStrEmpleado(resultSet.getString("Empleado"));
                venta.setStrSerieVenta(resultSet.getString("Serie"));
                venta.setStrNumeroVenta(resultSet.getString("Numero"));
                venta.setStrFechaVenta(resultSet.getDate("Fecha"));
                venta.setStrTotalVenta(resultSet.getString("TotalVenta"));
                venta.setStrDescuentoVenta(resultSet.getString("Descuento"));
                venta.setStrSubTotalVenta(resultSet.getString("SubTotal"));
                venta.setStrIvaVenta(resultSet.getString("Iva"));
                venta.setStrTotalPagarVenta(resultSet.getString("TotalPagar"));
                venta.setStrEstadoVenta(resultSet.getString("Estado"));

                ventas.add(venta);
            }
            return ventas;
         }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    public ResultSet listarVentaPorParametro(String criterio, String busqueda) throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = connection.prepareCall("{call SP_S_VentaPorParametro(?,?)}");
            statement.setString("pcriterio", criterio);
            statement.setString("pbusqueda", busqueda);
            rs = statement.executeQuery();
            return rs;
        }catch(SQLException SQLex){
            throw SQLex;            
        }        
    }    
    public ResultSet obtenerUltimoIdVenta() throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = connection.prepareCall("{call SP_S_UltimoIdVenta()}");
            rs = statement.executeQuery();
            return rs;
        }catch(SQLException SQLex){
            throw SQLex;            
        }        
    }
    public ResultSet listarVentaPorFecha(String criterio,Date fechaini, Date fechafin, String doc) throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = connection.prepareCall("{call SP_S_VentaPorFecha(?,?,?,?)}");
            statement.setString ("pcriterio", criterio);
            statement.setDate ("pfechaini", new java.sql.Date(fechaini.getTime()));
            statement.setDate ("pfechafin", new java.sql.Date(fechafin.getTime()));
            statement.setString("pdocumento", doc);
            rs = statement.executeQuery();
            return rs;
        }catch(SQLException SQLex){
            throw SQLex;            
        }        
    } 
    public void actualizarVentaEstado(String codigo,ClsEntidadVenta Venta){
        try{
            CallableStatement statement=connection.prepareCall("{call SP_U_ActualizarVentaEstado(?,?)}");
            statement.setString("pidventa",codigo);
            statement.setString("pestado",Venta.getStrEstadoVenta());        
            statement.executeUpdate();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        JOptionPane.showMessageDialog(null,"¡Venta Anulada!","Mensaje del Sistema",1);
    }
    public ResultSet listarVentaPorDetalle(String criterio,Date fechaini, Date fechafin) throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = connection.prepareCall("{call SP_S_VentaPorDetalle(?,?,?)}");
            statement.setString ("pcriterio", criterio);
            statement.setDate ("pfechaini", new java.sql.Date(fechaini.getTime()));
            statement.setDate ("pfechafin", new java.sql.Date(fechafin.getTime()));
            rs = statement.executeQuery();
            return rs;
        }catch(SQLException SQLex){
            throw SQLex;            
        }        
    }
    public ResultSet listarVentaMensual(String criterio,String fecha_ini,String fecha_fin) throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = connection.prepareCall("{call SP_S_VentaMensual(?,?,?)}");
            statement.setString ("pcriterio", criterio);
            statement.setString ("pfecha_ini", fecha_ini);
            statement.setString ("pfecha_fin", fecha_fin);
            rs = statement.executeQuery();
            return rs;
        }catch(SQLException SQLex){
            throw SQLex;            
        }        
    }
}
