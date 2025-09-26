public class Venta {
    private int id;
    private Cliente cliente;
    private Motocicleta moto;
    private double precioFinal;

    public Venta(int id, Cliente cliente, Motocicleta moto, double precioFinal) {
        this.id = id;
        this.cliente = cliente;
        this.moto = moto;
        this.precioFinal = precioFinal;
    }

    @Override
    public String toString() {
        return "Venta[ID=" + id + ", Cliente=" + cliente.getNombre() + 
               ", Moto=" + moto.getModelo() + ", PrecioFinal=$" + precioFinal + "]";
    }
}