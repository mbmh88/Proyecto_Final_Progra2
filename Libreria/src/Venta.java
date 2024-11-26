import java.util.List;
import java.util.Date;

public class Venta {
    private int idVenta;
    private Cliente cliente;
    private Date fechaVenta;
    private double total;
    private List<Libro> libros;

    // Constructor, getters y setters
    public Venta(int idVenta, Cliente cliente, Date fechaVenta, double total, List<Libro> libros) {
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.libros = libros;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public double getTotal() {
        return total;
    }

    public List<Libro> getLibros() {
        return libros;
    }

}
