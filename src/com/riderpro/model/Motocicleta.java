public class Motocicleta {
    private int id;
    private String marca;
    private String modelo;
    private int anio;
    private double precio;

    public Motocicleta(int id, String marca, String modelo, int anio, double precio) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.precio = precio;
    }
    
    public int getId() { return id; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAnio() { return anio; }
    public double getPrecio() { return precio; }
  

    @Override
    public String toString() {
        return "Motocicleta[ID=" + id + ", Marca=" + marca + ", Modelo=" + modelo + 
               ", Año=" + anio + ", Precio=$" + precio + "]";
    }
}