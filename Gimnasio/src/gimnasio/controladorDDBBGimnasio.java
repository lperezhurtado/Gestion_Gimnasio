
package gimnasio;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class controladorDDBBGimnasio {
    
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
        }
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){
            System.out.println("No se puede conectar");
        }
    }
    
    public void cerrar(){
        
        try{
            rs.close();
            statement.close();
            connection.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    //Metodos controladores para la tabla CLIENTES
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
        catch(Exception e){
            System.out.println("DNI ya existente, no se puede introducir cliente");
        }
    }
    
    public void updateCliente(int id, Cliente c){
        
        try{
            conectar();
            ejecucion = statement.execute("update clientes set nombre='"+c.getNombre()
            + "',DNI='"+c.getDni()+"',telefono='"+c.getTelefono()+"',fecha_alta='"+c.getFechaAlta()+"',cuota="+c.getCuota()+" where id="+id);
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
    
    //Metodos controladores para la tabla CLASES
    public void verDatosClases(){
        
        try{
            conectar();
            rs = statement.executeQuery("SELECT * FROM clases");
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String profesor = rs.getString("profesor");
                String dia = rs.getString("dia");
                String hora = rs.getString("hora");
                int apuntados = rs.getInt("apuntados");

                System.out.println(String.format("%d, %s, %s, %s, %s, %d", id, nombre, profesor, dia, hora, apuntados));
            }
            cerrar();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public void insertClase(Clase c){
        try{
            conectar();
            ejecucion = statement.execute("insert into clases (nombre,profesor,dia,hora) " +
            "values ('"+c.getNombre()+"','"+c.getProfesor()+"','"+c.getDia()+"','"+c.getHora()+"');");
            cerrar();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public void updateClase(int id, Clase c){
        
        try{
            conectar();
            ejecucion = statement.execute("update clases set nombre='"+c.getNombre() +
            "',profesor='"+c.getProfesor()+"',dia='"+c.getDia()+"',hora="+c.getHora()+" where id="+id);
            cerrar();
        }
        catch(SQLException e){
            System.out.println(e+"\n");
        }
    }
    
    public void deleteClase(int id){
        try{
            conectar();
            ejecucion = statement.execute("delete from clases where id="+id+";");
            cerrar();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    
    //Metodos controladores para APUNTAR CLIENTES A CLASES
    public void apuntarCliente(int idCliente, int idClase){
        try{
            conectar();
            ejecucion = statement.execute("INSERT INTO clientes_clases (idCliente, nombreCliente, idClase, nombreClase)\n " +
                                          "SELECT "+idCliente+", clientes.nombre, "+idClase+", clases.nombre FROM clientes, clases\n " +
                                          "WHERE clientes.id="+idCliente+" AND clases.id="+idClase+";");
            
            ejecucion = statement.execute("UPDATE clases SET apuntados=apuntados+1 WHERE id="+idClase);
            cerrar();
        }
        catch(SQLException e){
            System.out.println("Cliente o clase no existente\n");
        }
    }
    
    public void desapuntarCliente(int idCliente, int idClase){
        try{
            conectar();
            ejecucion = statement.execute("DELETE FROM clientes_clases WHERE idCliente="+idCliente+"AND idClase="+idClase);
            ejecucion = statement.execute("UPDATE clases SET apuntados=apuntados-1 WHERE id="+idClase);
            cerrar();
        }
        catch(SQLException e){
            System.out.println("No se ha podido borrar registro\n");
        }
    }
    
    
}
