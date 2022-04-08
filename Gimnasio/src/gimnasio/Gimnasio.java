
package gimnasio;
        
import java.util.Scanner;

public class Gimnasio {
    //metodo para imprimir menu
    public static void menu(){
        System.out.println("MENU DE OPCIONES\n"
                + "1. Añadir nueva clase\n"
                + "2. Actualizar datos de un clase\n"
                + "3. Eliminar una clase del registro\n"
                + "4. Ver todas las clases disponibles\n"
                + "5. Añadir nuevo cliente\n"
                + "6. Actualizar datos de un cliente\n"
                + "7. Eliminar cliente del registro\n"
                + "8. Ver todos los clientes\n"
                + "9. Apuntar cliente a una clase\n"
                + "10. Borrar cliente de una clase\n"
                + "11. SALIR\n");
    }
    //metodo para leer del Scanner
    public static String pedirDatos() {
        Scanner lector = new Scanner(System.in);
        return lector.nextLine();

    }
    //Metodo para pedir datos del cliente y crearlo
    public static String[] datosCliente(){
        
        System.out.println("Nombre?");
        String nombre = pedirDatos();
        System.out.println("Dni?");
        String dni = pedirDatos();
        System.out.println("Telefono");
        String tel = pedirDatos();
        System.out.println("Fecha de Alta? (aaaa-mm-dd)");
        String fecha = pedirDatos();
        System.out.println("Cuota mensual que paga?");
        String cuota = pedirDatos();
        
        String[] datos = {nombre,dni,tel,fecha,cuota};
        return datos;
    }
    //metodo para pedir datos de la clase y crearla
    public static String[] datosClase(){
        System.out.println("Nombre de la clase?");
        String nombre = pedirDatos();
        System.out.println("Profesor que impartirá la clase");
        String profesor = pedirDatos();
        System.out.println("Que dia se impartirá?");
        String dia = pedirDatos();
        System.out.println("A que hora?");
        String hora = pedirDatos();
        
        String[] datos = {nombre, profesor, dia, hora};
        return datos;
    }
    //===================== MAIN =========================
    public static void main(String[] args) {
        
        Scanner lector = new Scanner(System.in);
        controladorDDBBGimnasio controlador = new controladorDDBBGimnasio();
        Cliente cliente;
        Clase clase;
        int opcion;
        int id;
        String[] datosCli = new String[5];
        String[] datosCla = new String[4];
        
        do {
            menu();
            opcion = lector.nextInt();
            
            switch (opcion) {
                case 1:
                    datosCla = datosClase();
                    clase = new Clase(datosCla[0],datosCla[1],datosCla[2],datosCla[3]);
                    controlador.insertClase(clase);
                    break;
                case 2:
                    System.out.println("ID de la clase ha cambiar?");
                    id = lector.nextInt();
                    
                    System.out.println("Introduce los datos nuevos de la clase");
                    datosCla = datosClase();
                    clase = new Clase(datosCla[0],datosCla[1],datosCla[2],datosCla[3]);
                    controlador.updateClase(id, clase);
                    break;
                case 3:
                    System.out.println("ID de la clase a borrar?");
                    id = lector.nextInt();
                    controlador.deleteClase(id);
                    break;
                case 4:
                    controlador.verDatosClases();
                    break;
                case 5:
                    datosCli = datosCliente();
                    cliente = new Cliente(datosCli[0],datosCli[1],datosCli[2],datosCli[3],datosCli[4]);
                    controlador.insertCliente(cliente);
                    break;
                case 6:
                    System.out.println("ID del cliente a actualizar?");
                    id = lector.nextInt();
                    
                    System.out.println("Introduce los datos nuevos del cliente");
                    datosCli = datosCliente();
                    cliente = new Cliente(datosCli[0],datosCli[1],datosCli[2],datosCli[3],datosCli[4]);
                    controlador.updateCliente(id, cliente);
                    break;
                case 7:
                    System.out.println("ID del cliente a eliminar?");
                    id = lector.nextInt();
                    controlador.deleteCliente(id);
                    break;
                case 8:
                    controlador.verDatosClientes();
                    break;
                case 9:
                    System.out.println("ID del cliente?");
                    id = lector.nextInt();
                    lector.nextLine();
                    System.out.println("ID de la clase a la cual apuntar?");
                    int id2 = lector.nextInt();
                    controlador.apuntarCliente(id, id2);
                    break;
                case 10:
                    System.out.println("ID del cliente?");
                    id = lector.nextInt();
                    lector.nextLine();
                    System.out.println("ID de la clase de la cual borrar?");
                    id2 = lector.nextInt();
                    controlador.desapuntarCliente(id, id2);
                    break;
                case 11:
                    System.out.println("HASTA LUEGO");
                    break;
                default:
                    throw new AssertionError();
            }
        } while (opcion != 11);
        
        
        //Cliente c = new Cliente("Josue","24514324d","61792834","2022-02-21",450);
        //controlador.insertCliente(c);
       
       //controlador.updateCliente(1,cliente);
       //controlador.deleteCliente(2);
        //controlador.verDatosClientes();
        //controlador.verDatosClientes();
        
       
        
      /* try{
           Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
       }
       catch(Exception e){
           System.out.println("No se ha encontrado el driver");
       }*/
      
    }
    
    
    
}
