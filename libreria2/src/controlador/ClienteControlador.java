package controlador;

import modelo.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteControlador {
    public static void registrarCliente(Connection conexion, Cliente cliente) throws SQLException {
        String query = "INSERT INTO clientes (nombre, direccion, telefono) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getDireccion());
            pstmt.setString(3, cliente.getTelefono());
            pstmt.executeUpdate();
        }
    }

    public static List<Cliente> obtenerClientes(Connection conexion) throws SQLException {
        String query = "SELECT * FROM clientes";
        List<Cliente> clientes = new ArrayList<>();
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                clientes.add(new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono")
                ));
            }
        }
        return clientes;
    }
}
