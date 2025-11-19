
public class servicioItem {
    String descripcion;
    int cantidad;
    double precio;

    public servicioItem(String descripcion, int cantidad, double precio) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public double getSubtotal() {
        return cantidad * precio;
    }
}