import java.util.Date;


public class Autor {
    private int idAutor;
    private String nombre;
    private Date fechaNacimiento;

    // Constructor, getters y setters
    public Autor(int idAutor, String nombre, Date fechaNacimiento) {
        this.idAutor = idAutor;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
}
