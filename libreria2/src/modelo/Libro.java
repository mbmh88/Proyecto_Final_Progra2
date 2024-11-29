package modelo;

public class Libro {
    private int idLibro;
    private String titulo;
    private double precio;
    private int idAutor;

    // Constructor, getters y setters


    public Libro(int idLibro, String titulo, double precio, int idAutor) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.precio = precio;
        this.idAutor = idAutor;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getPrecio() {
        return precio;
    }

    public int getIdAutor() {
        return idAutor;
    }
}
