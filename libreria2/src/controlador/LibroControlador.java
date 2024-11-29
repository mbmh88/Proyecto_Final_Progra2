package controlador;

import modelo.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroControlador {
    public static void registrarLibro(Connection conexion, Libro libro) throws SQLException {
        String query = "INSERT INTO libros (titulo, precio, id_autor) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, libro.getTitulo());
            pstmt.setDouble(2, libro.getPrecio());
            pstmt.setInt(3, libro.getIdAutor());
            pstmt.executeUpdate();
        }
    }

    public static List<Libro> obtenerLibrosPorAutor(Connection conexion, int idAutor) throws SQLException {
        String query = "SELECT * FROM libros WHERE id_autor = ?";
        List<Libro> libros = new ArrayList<>();
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, idAutor);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    libros.add(new Libro(
                            rs.getInt("id_libro"),
                            rs.getString("titulo"),
                            rs.getDouble("precio"),
                            rs.getInt("id_autor")
                    ));
                }
            }
        }
        return libros;
    }
}
