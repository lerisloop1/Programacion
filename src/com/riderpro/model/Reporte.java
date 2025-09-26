

public class Reporte {
    private int id;
    private String titulo;
    private String descripcion;

    public Reporte(int id, String titulo, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Reporte[ID=" + id + ", Titulo=" + titulo + ", Descripcion=" + descripcion + "]";
    }
}