
package gimnasio;

public class Clase {
    
    private String nombre;
    private String profesor;
    private String hora;
    private String dia;
    
    public Clase(String n, String p, String h, String d){
        setNombre(n);
        setProfesor(p);
        setHora(h);
        setDia(d);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
    
    
}