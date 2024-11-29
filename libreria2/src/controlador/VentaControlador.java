package controlador;

import modelo.Venta;

import java.sql.*;

public class VentaControlador {
    public static int registrarVenta(Connection conexion, Venta venta) throws SQLException {
        String query = "INSERT INTO ventas (id_cliente, fecha) VALUES (?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, venta.getIdCliente());
            pstmt.setDate(2, Date.valueOf(venta.getFecha()));
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }
}
