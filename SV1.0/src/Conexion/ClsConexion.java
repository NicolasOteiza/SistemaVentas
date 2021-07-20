/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;
import java.sql.Connection;
import com.mysql.jdbc.jdbc2.optional.*;

public class ClsConexion {
    
    private static Connection conection=null;
    public Connection getConection(){
        try{
            MysqlConnectionPoolDataSource ds=new MysqlConnectionPoolDataSource();
            ds.setServerName("localhost");
            ds.setPort(3306);
            ds.setDatabaseName("dbventas");
            conection=ds.getConnection("root","");
        }catch(Exception ex){
           ex.printStackTrace();
        }
        return conection;
    }
    
}
