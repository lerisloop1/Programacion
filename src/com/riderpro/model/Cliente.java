


public class Cliente {
    private int id;
    private String nombre;
    private String direccion;
    private String email;

    public Cliente(int id, String nombre, String direccion, String email) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
    }
    
   public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String getEmail() { return email; }


    @Override
    public String toString() {
        return "Cliente[ID=" + id + ", Nombre=" + nombre + ", Direccion=" + direccion + ", Email=" + email + "]";
    }
}