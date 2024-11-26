public class Cliente {
    private int idCliente;
    private String nombreCliente;
    private String direccion;
    private String telefono;

    // Constructor, getters y setters
    public Cliente(int idCliente, String nombreCliente, String direccion, String telefono) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }
}
