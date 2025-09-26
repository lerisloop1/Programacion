

public class Inventario {
    private int id;
    private int stock;
    private int minimo;

    public Inventario(int id, int stock, int minimo) {
        this.id = id;
        this.stock = stock;
        this.minimo = minimo;
    }

    @Override
    public String toString() {
        return "Inventario[ID=" + id + ", Stock=" + stock + ", Minimo=" + minimo + "]";
    }
}