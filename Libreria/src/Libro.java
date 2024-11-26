public class Libro {
    private int idLibro;
    private String titulo;
    private double precio;
    private Autor autor;

    // Constructor, getters y setters
    public Libro(int idLibro, String titulo, double precio, Autor autor) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.precio = precio;
        this.autor = autor;
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

    public Autor getAutor() {
        return autor;
    }
}
