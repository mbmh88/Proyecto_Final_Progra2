package modelo;

import java.time.LocalDate;

public class Venta {
    private int idVenta;
    private int idCliente;
    private LocalDate fecha;
    private double total;

    // Constructor, getters y setters


    public Venta(int idVenta, int idCliente, LocalDate fecha, double total) {
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.total = total;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getTotal() {
        return total;
    }
}
