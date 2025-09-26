
public class Main {
    public static void main(String[] args) {
        Inventario inventario = new Inventario(1, 50, 10);
        Cliente cliente = new Cliente(1, "Juan Perez", "Calle Falsa 123", "juan@gmail.com");
        Motocicleta moto = new Motocicleta(1, "Yamaha", "MT-07", 2023, 35000.0);
        Venta venta = new Venta(1, cliente, moto, 35000.0);
        Usuario usuario = new Usuario(1, "admin", "1234", "Administrador");
        Reporte reporte = new Reporte(1, "Ventas Diarias", "Resumen de ventas del dia");
        Alerta alerta = new Alerta(1, "Stock bajo en inventario");

        System.out.println("Objetos creados exitosamente:");
        System.out.println(inventario);
        System.out.println(cliente);
        System.out.println(moto);
        System.out.println(venta);
        System.out.println(usuario);
        System.out.println(reporte);
        System.out.println(alerta);
    }
}
