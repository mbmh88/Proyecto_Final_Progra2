package controlador;

import modelo.Autor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorControlador {
    public static List<Autor> obtenerAutores(Connection conexion) throws SQLException {
        String query = "SELECT * FROM autores";
        List<Autor> autores = new ArrayList<>();
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                autores.add(new Autor(
                        rs.getInt("id_autor"),
                        rs.getString("nombre"),
                        rs.getDate("fecha_nacimiento").toLocalDate()
                ));
            }
        }
        return autores;
    }

    public static void agregarAutor(Connection conexion, Autor autor) throws SQLException {
        String query = "INSERT INTO autores (nombre, fecha_nacimiento) VALUES (?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, autor.getNombre());
            pstmt.setDate(2, Date.valueOf(autor.getFechaNacimiento()));
            pstmt.executeUpdate();
        }
    }
}
