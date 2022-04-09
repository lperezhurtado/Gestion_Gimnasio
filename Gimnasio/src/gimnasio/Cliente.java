
package gimnasio;

public class Cliente {
    
    private String nombre;
    private String dni;
    private String telefono;
    private String fechaAlta;
    private String cuota;
    
    //constructor
    public Cliente(String nombre, String dni, String telefono, String fechaAlta, String cuota) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.fechaAlta = fechaAlta;
        this.cuota = cuota;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getCuota() {
        return cuota;
    }

    public void setCuota(String cuota){
        this.cuota = cuota;
    }
   
}
