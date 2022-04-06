
package gimnasio;
        
public class Gimnasio {

    public static void main(String[] args) {
        
        controladorClientes controlador = new controladorClientes();
        //Cliente c = new Cliente("Josue","24514324d","61792834","2022-02-21",450);
        //controlador.insertCliente(c);
        //controlador.updateCliente(2, "Carlos", "1234", "9876", "1997-06-26", "500");
       
        controlador.verDatosClientes();
        //controlador.verDatosClientes();
        
      /* try{
           Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
       }
       catch(Exception e){
           System.out.println("No se ha encontrado el driver");
       }
       
       try{
           String url = "jdbc:mysql://localhost:3306/gimnasio";
           String usuario = "root";
           String contraseña = "luis";
           
           Connection connection = DriverManager.getConnection(url, usuario, contraseña);
           Statement statement = connection.createStatement();
           ResultSet rs = statement.executeQuery("SELECT * FROM clientes");
           
           while(rs.next()){
               int id = rs.getInt("id");
               String nombre = rs.getString("nombre");
               String dni = rs.getString("DNI");
               String telefono = rs.getString("telefono");
               Date fecha = rs.getDate("fecha_alta");
               double cuota = rs.getDouble("cuota");
               
               System.out.println(String.format("%d, %s, %s, %s, %s, %f", id, nombre, dni, telefono, fecha, cuota));
           }
           rs.close();
           statement.close();
           connection.close();
       }
       catch(SQLException e){
           System.out.println(e);
       }*/
    }
    
    
    
}
