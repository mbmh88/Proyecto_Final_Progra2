package controlador;

import modelo.VentaLibro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VentaLibroControlador {
    public static void registrarVentaLibro(Connection conexion, VentaLibro ventaLibro) throws SQLException {
        String query = "INSERT INTO venta_libro (id_venta, id_libro, cantidad) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, ventaLibro.getIdVenta());
            pstmt.setInt(2, ventaLibro.getIdLibro());
            pstmt.setInt(3, ventaLibro.getCantidad());
            pstmt.executeUpdate();
        }
    }
}
