package modelo;

public class VentaLibro {
    private int idVenta;
    private int idLibro;
    private int cantidad;

    // Constructor, getters y setters


    public VentaLibro(int idVenta, int idLibro, int cantidad) {
        this.idVenta = idVenta;
        this.idLibro = idLibro;
        this.cantidad = cantidad;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
