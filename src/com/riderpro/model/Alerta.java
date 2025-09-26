public class Alerta {
    private int id;
    private String mensaje;

    public Alerta(int id, String mensaje) {
        this.id = id;
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "Alerta[ID=" + id + ", Mensaje=" + mensaje + "]";
    }
}