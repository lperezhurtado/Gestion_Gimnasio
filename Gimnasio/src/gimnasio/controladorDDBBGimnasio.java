
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
    
    //metodo para conectar con el servidor
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
    
    //metodo para cerrar las conexiones
    public void cerrar(){
        try{
            rs.close();
            statement.close();
            connection.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    //======= Metodos controladores para la tabla CLIENTES ==========
    public void verDatosClientes(){ 
        try{
            conectar();
            rs = statement.executeQuery("SELECT * FROM clientes");
            
            System.out.println(String.format("%-2s | %-14s | %-10s | %-10s | %-12s | %-5s |", "ID","NOMBRE","DNI","TELEFONO","FECHA","CUOTA"));
            System.out.println("----------------------------------------------------------------"); 
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String dni = rs.getString("DNI");
                String telefono = rs.getString("telefono");
                Date fecha = rs.getDate("fecha_alta");
                String cuota = rs.getString("cuota");

                System.out.println(String.format("%-2d | %-14s | %-10s | %-10s | %-12s | %-5s |", id, nombre, dni, telefono, fecha, cuota));
            }                                                       
            cerrar();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    //metodo para AÑADIR nuevo cliente 
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
    //metodo para ACTUALIZAR datos de un cliente existente
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
    //metodo para BORRAR un cliente de la base de datos
    public void deleteCliente(int id){
        try{
            conectar();
            ejecucion = statement.execute("DELETE FROM clientes WHERE id="+id+";");
            cerrar();
            System.out.println("CLIENTE ELIMINADO\n");
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    //========= Metodos controladores para la tabla CLASES =============
    public void verDatosClases(){
        try{
            conectar();
            rs = statement.executeQuery("SELECT * FROM clases");
            System.out.println(String.format("%-2s | %-10s | %-14s | %-10s | %-10s |", "ID", "NOMBRE", "PROFESOR", "DIA", "HORA"));
            System.out.println("----------------------------------------------------------");                                                         
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String profesor = rs.getString("profesor");
                String dia = rs.getString("dia");
                String hora = rs.getString("hora");
                
                System.out.println(String.format("%-2d | %-10s | %-14s | %-10s | %-10s |", id, nombre, profesor, dia, hora));
            }
            cerrar();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    //metodo para AÑADIR una nueva clase
    public void insertClase(Clase c){
        try{
            conectar();
            ejecucion = statement.execute("insert into clases (nombre,profesor,dia,hora) " +
            "values ('"+c.getNombre()+"','"+c.getProfesor()+"','"+c.getDia()+"','"+c.getHora()+"');");
            cerrar();
            System.out.println("CLASE AÑADIDA CORRECTAMENTE \n");
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    //metodo para ACTUALIZAR  datos de una clase existente 
    public void updateClase(int id, Clase c){
        try{
            conectar();
            ejecucion = statement.execute("update clases set nombre='"+c.getNombre() +
            "',profesor='"+c.getProfesor()+"',dia='"+c.getDia()+"',hora='"+c.getHora()+"' where id="+id);
            cerrar();
            System.out.println("CLASE ACTUALIZADA \n");
        }
        catch(SQLException e){
            System.out.println(e+"\n");
        }
    }
    //metodo para ELIMINAR una clase de la base de datos 
    public void deleteClase(int id){
        try{
            conectar();
            ejecucion = statement.execute("delete from clases where id="+id+";");
            cerrar();
            System.out.println("CLASE ELIMINADA DEL REGISTRO \n");
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    
    //========= Metodos controladores para APUNTAR CLIENTES A CLASES ===========
    //metodo para apuntar un cliente
    public void apuntarCliente(int idCliente, int idClase){
        try{
            conectar();
            ejecucion = statement.execute("INSERT INTO clientes_clases (idCliente, nombreCliente, idClase, nombreClase)\n " +
                                          "SELECT "+idCliente+", clientes.nombre, "+idClase+", clases.nombre FROM clientes, clases\n " +
                                          "WHERE clientes.id="+idCliente+" AND clases.id="+idClase+";");
            cerrar();
            System.out.println("SE HA ANOTADO CLIENTE CORRECTAMENTE \n");
        }
        catch(SQLException e){
            System.out.println("Cliente o clase no existente\n");
        }
    }
    //metodo para quitar un cliente de una clase
    public void desapuntarCliente(int idCliente, int idClase){
        try{
            conectar();
            ejecucion = statement.execute("DELETE FROM clientes_clases WHERE idCliente="+idCliente+" AND idClase="+idClase+";");
            System.out.println("REGISTRO BORRADO \n");
            cerrar();
        }
        catch(SQLException e){
            System.out.println("No se ha podido eliminar registro\n");
        }
    }
    //metodo para ver todos los cliente apunntados a las diversas clases
    public void verClientesApuntados(){
        try{
            conectar();
            rs = statement.executeQuery("SELECT * FROM clientes_clases");
            
            System.out.println(String.format("%-2s | %-10s | %-2s | %-10s", "ID CLIENTE", "NOMBRE CLIENTE", "ID CLASE", "NOMBRE CLASE"));
            System.out.println("-------------------------------------------------------");
            while (rs.next()) {
                int idCliente = rs.getInt("idCliente");
                String nombreCli = rs.getString("nombreCliente");
                int idClase = rs.getInt("idClase");
                String nombreCla = rs.getString("nombreClase");
               
                System.out.println(String.format("%-10d | %-14s | %-8d | %-10s", idCliente, nombreCli, idClase, nombreCla));
            }
            cerrar();
            System.out.println("\n");
            conectar();
            rs = statement.executeQuery("SELECT nombreClase ,count(idCliente) FROM clientes_clases GROUP BY nombreClase ORDER BY count(idCliente) DESC;");
            System.out.println(String.format("| %-15s |", "TOTAL APUNTADOS EN:"));
            System.out.println("------------------------------");
            while (rs.next()) {
                String nombreClase = rs.getString("nombreClase");
                int cont = rs.getInt("count(idCliente)");
               
                System.out.println(String.format("%-10s | %-8d |", nombreClase, cont));
            }
            System.out.println("");
            cerrar();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }        
}
