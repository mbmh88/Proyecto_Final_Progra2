package modelo;

import java.time.LocalDate;

public class Autor {
    private int idAutor;
    private String nombre;
    private LocalDate fechaNacimiento;

    // Constructor, getters y setters


    public Autor(int idAutor, String nombre, LocalDate fechaNacimiento) {
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
}
