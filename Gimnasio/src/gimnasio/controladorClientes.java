
package gimnasio;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class controladorClientes {
    
    private final String url = "jdbc:mysql://localhost:3306/gimnasio";
    private final String usuario = "root";
    private final String password = "luis";
    
    Connection connection;
    Statement statement;
    ResultSet rs;
    private boolean ejecucion;
    
    
    public void conectar() {
   
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, usuario, password);
            statement = connection.createStatement();
        }catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){
            System.out.println("No se puede conectar");
        }
    }
    
    public void cerrar(){
        
        try{
            rs.close();
            statement.close();
            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void verDatosClientes(){
        
        try{
            conectar();
            rs = statement.executeQuery("SELECT * FROM clientes");
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String dni = rs.getString("DNI");
                String telefono = rs.getString("telefono");
                Date fecha = rs.getDate("fecha_alta");
                String cuota = rs.getString("cuota");

                System.out.println(String.format("%d, %s, %s, %s, %s, %s", id, nombre, dni, telefono, fecha, cuota));
            }
            cerrar();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public void insertCliente(Cliente c){
        try{
            conectar();
            ejecucion = statement.execute("insert into clientes (nombre,DNI,telefono,fecha_alta,cuota) "
            + "values ('"+c.getNombre()+"','"+c.getDni()+"', '"+c.getTelefono()+"','"+c.getFechaAlta()+"',"+c.getCuota()+");");
            cerrar();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public void updateCliente(int id, String n, String dni, String t, String f, String cuota){
        try{
            conectar();
            ejecucion = statement.execute("update clientes set nombre='"+n+"',DNI='"+dni+"',telefono='"+t+"',fecha_alta='"+f+"',cuota="+cuota+" where id="+id);
            cerrar();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public void deleteCliente(int id){
        try{
            conectar();
            ejecucion = statement.execute("delete from clientes where id="+id+";");
            cerrar();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
}
