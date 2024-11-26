import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection conexion = ConexionBD.conectar();
             Scanner scanner = new Scanner(System.in)) {

            if (conexion != null) {
                System.out.println("Conexión exitosa a la base de datos.");
            } else {
                System.out.println("No se pudo conectar a la base de datos.");
                return;
            }

            int opcion;
            do {
                System.out.println("\n=== Menú de la Librería ===");
                System.out.println("1. Mostrar autores");
                System.out.println("2. Agregar un nuevo autor");
                System.out.println("3. Mostrar clientes");
                System.out.println("4. Registrar un nuevo cliente");
                System.out.println("5. Registrar una venta");
                System.out.println("6. Mostrar todas las ventas");
                System.out.println("7. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1:
                        mostrarAutores(conexion);
                        break;
                    case 2:
                        agregarAutor(conexion, scanner);
                        break;
                    case 3:
                        mostrarClientes(conexion);
                        break;
                    case 4:
                        registrarCliente(conexion, scanner);
                        break;
                    case 5:
                        registrarVenta(conexion, scanner);
                        break;
                    case 6:
                        mostrarVentas(conexion);
                        break;
                    case 7:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida, intente nuevamente.");
                }
            } while (opcion != 7);

        } catch (SQLException e) {
            System.out.println("Error al interactuar con la base de datos.");
            e.printStackTrace();
        }
    }

    private static void mostrarAutores(Connection conexion) {
        String consulta = "SELECT * FROM Autor";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(consulta)) {

            System.out.println("\n--- Lista de Autores ---");
            while (rs.next()) {
                int id = rs.getInt("id_autor");
                String nombre = rs.getString("nombre");
                Date fechaNacimiento = rs.getDate("fecha_nacimiento");
                System.out.printf("ID: %d, Nombre: %s, Fecha de Nacimiento: %s%n", id, nombre, fechaNacimiento);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar autores.");
            e.printStackTrace();
        }
    }

    private static void agregarAutor(Connection conexion, Scanner scanner) {
        System.out.println("\n--- Agregar Nuevo Autor ---");
        System.out.print("Ingrese el nombre del autor: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la fecha de nacimiento (YYYY-MM-DD): ");
        String fechaNacimiento = scanner.nextLine();

        String insercion = "INSERT INTO Autor (nombre, fecha_nacimiento) VALUES (?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(insercion)) {
            pstmt.setString(1, nombre);
            pstmt.setDate(2, Date.valueOf(fechaNacimiento));
            int filasInsertadas = pstmt.executeUpdate();

            if (filasInsertadas > 0) {
                System.out.println("Autor agregado exitosamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al agregar el autor.");
            e.printStackTrace();
        }
    }

    private static void mostrarClientes(Connection conexion) {
        String consulta = "SELECT * FROM Cliente";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(consulta)) {

            System.out.println("\n--- Lista de Clientes ---");
            while (rs.next()) {
                int id = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre_cliente");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                System.out.printf("ID: %d, Nombre: %s, Dirección: %s, Teléfono: %s%n", id, nombre, direccion, telefono);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar clientes.");
            e.printStackTrace();
        }
    }

    private static void registrarCliente(Connection conexion, Scanner scanner) {
        System.out.println("\n--- Registrar Nuevo Cliente ---");
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la dirección del cliente: ");
        String direccion = scanner.nextLine();

        System.out.print("Ingrese el teléfono del cliente: ");
        String telefono = scanner.nextLine();

        String insercion = "INSERT INTO Cliente (nombre_cliente, direccion, telefono) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(insercion)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, direccion);
            pstmt.setString(3, telefono);
            int filasInsertadas = pstmt.executeUpdate();

            if (filasInsertadas > 0) {
                System.out.println("Cliente agregado exitosamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al agregar el cliente.");
            e.printStackTrace();
        }
    }

    private static void registrarVenta(Connection conexion, Scanner scanner) {
        try {
            System.out.println("\n--- Registrar Nueva Venta ---");
            System.out.print("Ingrese el ID del cliente que realiza la compra: ");
            int idCliente = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            System.out.print("Ingrese la fecha de la venta (YYYY-MM-DD): ");
            String fechaVenta = scanner.nextLine();

            System.out.print("Ingrese el monto total de la venta: ");
            double total = scanner.nextDouble();

            String insercion = "INSERT INTO Venta (id_cliente, fecha_venta, total) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conexion.prepareStatement(insercion, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setInt(1, idCliente);
                pstmt.setDate(2, Date.valueOf(fechaVenta));
                pstmt.setDouble(3, total);
                int filasInsertadas = pstmt.executeUpdate();

                if (filasInsertadas > 0) {
                    System.out.println("Venta registrada exitosamente.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar la venta.");
            e.printStackTrace();
        }
    }

    private static void mostrarVentas(Connection conexion) {
        String consulta = "SELECT * FROM Venta";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(consulta)) {

            System.out.println("\n--- Lista de Ventas ---");
            while (rs.next()) {
                int id = rs.getInt("id_venta");
                int idCliente = rs.getInt("id_cliente");
                Date fechaVenta = rs.getDate("fecha_venta");
                double total = rs.getDouble("total");
                System.out.printf("ID Venta: %d, ID Cliente: %d, Fecha: %s, Total: %.2f%n", id, idCliente, fechaVenta, total);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar ventas.");
            e.printStackTrace();
        }
    }
}
