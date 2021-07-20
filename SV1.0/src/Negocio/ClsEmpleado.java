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


public class ClsEmpleado {
    private Connection connection=new ClsConexion().getConection();
    
    public void agregarEmpleado (ClsEntidadEmpleado empleado){
        try{
            CallableStatement statement=connection.prepareCall("{call SP_I_Empleado(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pnombre", empleado.getStrNombreEmpleado());
            statement.setString("papellido", empleado.getStrApellidoEmpleado());
            statement.setString("psexo", empleado.getStrSexoEmpleado());               
            statement.setDate("pfechanac", new java.sql.Date(empleado.getStrFechaNacEmpleado().getTime()));
            statement.setString("pdireccion", empleado.getStrDireccionEmpleado());
            statement.setString("ptelefono", empleado.getStrTelefonoEmpleado());
            statement.setString("pcelular", empleado.getStrCelularEmpleado());
            statement.setString("pemail", empleado.getStrEmailEmpleado());
            statement.setString("prut", empleado.getStrRutEmpleado());
            statement.setDate("pfechaing", new java.sql.Date(empleado.getStrFechaIngEmpleado().getTime()));
            statement.setString("psueldo", empleado.getStrSueldoEmpleado());
            statement.setString("pestado", empleado.getStrEstadoEmpleado());
            statement.setString("pusuario", empleado.getStrUsuarioEmpleado());
            statement.setString("pcontraseña", empleado.getStrContraseñaEmpleado());
            statement.setString("pidtipousuario", empleado.getStrIdTipoUsuario());
            statement.execute();
            JOptionPane.showMessageDialog(null,"¡Empleado Agregado con éxito!","Mensaje del Sistema",1);
        }catch(SQLException ex){
            ex.printStackTrace();
        }  
    }
    
    public void modificarEmpleado (String codigo,ClsEntidadEmpleado empleado){
        try{
            CallableStatement statement=connection.prepareCall("{call SP_U_Empleado(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString("pnombre", empleado.getStrNombreEmpleado());
            statement.setString("papellido", empleado.getStrApellidoEmpleado());
            statement.setString("psexo", empleado.getStrSexoEmpleado());
            statement.setDate("pfechanac", new java.sql.Date(empleado.getStrFechaNacEmpleado().getTime()));
            statement.setString("pdireccion", empleado.getStrDireccionEmpleado());
            statement.setString("ptelefono", empleado.getStrTelefonoEmpleado());
            statement.setString("pcelular", empleado.getStrCelularEmpleado());
            statement.setString("pemail", empleado.getStrEmailEmpleado());
            statement.setString("prut", empleado.getStrRutEmpleado());
            statement.setDate("pfechaing", new java.sql.Date(empleado.getStrFechaIngEmpleado().getTime()));
            statement.setString("psueldo", empleado.getStrSueldoEmpleado());
            statement.setString("pestado", empleado.getStrEstadoEmpleado());
            statement.setString("pusuario", empleado.getStrUsuarioEmpleado());
            statement.setString("pcontraseña", empleado.getStrContraseñaEmpleado());
            statement.setString("pidtipousuario", empleado.getStrIdTipoUsuario());
            statement.setString("pidempleado",codigo);
            statement.execute();
        }catch(SQLException ex){
            ex.printStackTrace();
        }  
        JOptionPane.showMessageDialog(null,"¡Empleado Actualizado!","Mensaje del Sistema",1);
    }
    
    public ResultSet LoginEmpleados(String usu, String contra, String desc) throws Exception{
    
        ResultSet rs=null;
        try{
            CallableStatement statement=connection.prepareCall("{call SP_S_Login(?,?,?)}");
            statement.setString("pusuario", usu);
            statement.setString("pcontraseña", contra);
            statement.setString("pdescripcion", desc);
            rs=statement.executeQuery();
            return rs;
        }catch(SQLException SQLex){
            throw SQLex;
        }    
    }
    
    public ArrayList<ClsEntidadEmpleado> listarEmpleado(){
        ArrayList<ClsEntidadEmpleado> empleados=new ArrayList<ClsEntidadEmpleado>();
        try{
            CallableStatement statement=connection.prepareCall("{call SP_S_Empleado}");
            ResultSet resultSet=statement.executeQuery();
            
            while (resultSet.next()){
                ClsEntidadEmpleado empleado=new ClsEntidadEmpleado();
                empleado.setStrIdEmpleado(resultSet.getString("IdEmpleado"));
                empleado.setStrNombreEmpleado(resultSet.getString("Nombre"));
                empleado.setStrApellidoEmpleado(resultSet.getString("Apellido"));
                empleado.setStrSexoEmpleado(resultSet.getString("Sexo"));
                empleado.setStrFechaNacEmpleado(resultSet.getDate("FechaNac"));   
                empleado.setStrDireccionEmpleado(resultSet.getString("Direccion"));
                empleado.setStrTelefonoEmpleado(resultSet.getString("Telefono"));
                empleado.setStrCelularEmpleado(resultSet.getString("Celular"));
                empleado.setStrEmailEmpleado(resultSet.getString("Email"));
                empleado.setStrRutEmpleado(resultSet.getString("Rut"));
                empleado.setStrFechaIngEmpleado(resultSet.getDate("FechaIng"));
                empleado.setStrSueldoEmpleado(resultSet.getString("Sueldo"));
                empleado.setStrEstadoEmpleado(resultSet.getString("Estado"));
                empleado.setStrUsuarioEmpleado(resultSet.getString("Usuario"));
                empleado.setStrContraseñaEmpleado(resultSet.getString("Contraseña"));
                empleado.setStrTipoUsuario(resultSet.getString("TipoUsuario"));
                
                
                empleados.add(empleado);
            }
            return empleados;
         }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }  
    public ResultSet listarEmpleadoPorParametro(String criterio, String busqueda) throws Exception{
        ResultSet rs = null;
        try{
            CallableStatement statement = connection.prepareCall("{call SP_S_EmpleadoPorParametro(?,?)}");
            statement.setString("pcriterio", criterio);
            statement.setString("pbusqueda", busqueda);
            rs = statement.executeQuery();
            return rs;
        }catch(SQLException SQLex){
            throw SQLex;            
        }        
    } 
    public void cambiarContraseña(String codigo,ClsEntidadEmpleado Empleado){
        try{
            CallableStatement statement=connection.prepareCall("{call SP_U_CambiarPass(?,?)}");
            statement.setString("pidempleado",codigo);
            statement.setString("pcontraseña",Empleado.getStrContraseñaEmpleado());        
            statement.executeUpdate();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        JOptionPane.showMessageDialog(null,"¡Se cambio contraseña satisfactoriamente!","Mensaje del Sistema",1);
    }    

}
