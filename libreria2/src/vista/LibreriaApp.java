package vista;

import controlador.*;
import modelo.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibreriaApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Connection conexion = ConexionBD.conectar()) {
            boolean salir = false;
            while (!salir) {
                System.out.println("\n--- Menú Principal ---");
                System.out.println("1. Mostrar autores");
                System.out.println("2. Agregar nuevo autor");
                System.out.println("3. Registrar cliente");
                System.out.println("4. Mostrar clientes");
                System.out.println("5. Registrar libro");
                System.out.println("6. Registrar venta");
                System.out.println("7. Salir");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        mostrarAutores(conexion);
                        break;
                    case 2:
                        agregarAutor(conexion, scanner);
                        break;
                    case 3:
                        registrarCliente(conexion, scanner);
                        break;
                    case 4:
                        mostrarClientes(conexion);
                        break;
                    case 5:
                        registrarLibro(conexion, scanner);
                        break;
                    case 6:
                        registrarVenta(conexion, scanner);
                        break;
                    case 7:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarAutores(Connection conexion) {
        try {
            AutorControlador.obtenerAutores(conexion).forEach(autor ->
                    System.out.printf("ID: %d, Nombre: %s, Fecha de Nacimiento: %s%n",
                            autor.getIdAutor(), autor.getNombre(), autor.getFechaNacimiento()));
        } catch (SQLException e) {
            System.out.println("Error al mostrar autores.");
        }
    }

    private static void agregarAutor(Connection conexion, Scanner scanner) {
        System.out.print("Ingrese el nombre del autor: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la fecha de nacimiento (YYYY-MM-DD): ");
        String fechaNacimiento = scanner.nextLine();
        try {
            AutorControlador.agregarAutor(conexion, new Autor(0, nombre, LocalDate.parse(fechaNacimiento)));
            System.out.println("Autor agregado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar autor.");
        }
    }

    private static void registrarCliente(Connection conexion, Scanner scanner) {
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Ingrese el teléfono: ");
        String telefono = scanner.nextLine();
        try {
            ClienteControlador.registrarCliente(conexion, new Cliente(0, nombre, direccion, telefono));
            System.out.println("Cliente registrado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar cliente.");
        }
    }

    private static void mostrarClientes(Connection conexion) {
        try {
            ClienteControlador.obtenerClientes(conexion).forEach(cliente ->
                    System.out.printf("ID: %d, Nombre: %s, Dirección: %s, Teléfono: %s%n",
                            cliente.getIdCliente(), cliente.getNombre(), cliente.getDireccion(), cliente.getTelefono()));
        } catch (SQLException e) {
            System.out.println("Error al mostrar clientes.");
        }
    }

    private static void registrarLibro(Connection conexion, Scanner scanner) {
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese el precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Ingrese el ID del autor: ");
        int idAutor = scanner.nextInt();
        try {
            LibroControlador.registrarLibro(conexion, new Libro(0, titulo, precio, idAutor));
            System.out.println("Libro registrado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar libro.");
        }
    }

    private static void registrarVenta(Connection conexion, Scanner scanner) {
        try {
            // Paso 1: Seleccionar cliente
            System.out.print("Ingrese el ID del cliente: ");
            int idCliente = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            // Paso 2: Registrar la fecha de la venta
            System.out.print("Ingrese la fecha de la venta (YYYY-MM-DD): ");
            String fechaVenta = scanner.nextLine();

            // Paso 3: Seleccionar autor
            System.out.println("Lista de autores disponibles:");
            AutorControlador.obtenerAutores(conexion).forEach(autor ->
                    System.out.printf("ID: %d, Nombre: %s%n", autor.getIdAutor(), autor.getNombre()));

            System.out.print("Ingrese el ID del autor: ");
            int idAutor = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            // Paso 4: Seleccionar libros del autor
            System.out.println("Libros disponibles del autor seleccionado:");
            LibroControlador.obtenerLibrosPorAutor(conexion, idAutor).forEach(libro ->
                    System.out.printf("ID: %d, Título: %s, Precio: %.2f%n", libro.getIdLibro(), libro.getTitulo(), libro.getPrecio()));

            boolean agregarMasLibros = true;
            List<VentaLibro> librosVendidos = new ArrayList<>();

            while (agregarMasLibros) {
                System.out.print("Ingrese el ID del libro a vender: ");
                int idLibro = scanner.nextInt();
                System.out.print("Ingrese la cantidad: ");
                int cantidad = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                librosVendidos.add(new VentaLibro(0, idLibro, cantidad)); // ID de venta será asignado luego

                System.out.print("¿Desea agregar más libros a esta venta? (s/n): ");
                String respuesta = scanner.nextLine();
                agregarMasLibros = respuesta.equalsIgnoreCase("s");
            }

            // Paso 5: Registrar la venta
            int idVenta = VentaControlador.registrarVenta(conexion, new Venta(0, idCliente, LocalDate.parse(fechaVenta), 0));

            // Paso 6: Registrar los libros vendidos
            for (VentaLibro ventaLibro : librosVendidos) {
                ventaLibro.setIdVenta(idVenta); // Asociar ID de venta generado
                VentaLibroControlador.registrarVentaLibro(conexion, ventaLibro);
            }

            System.out.println("Venta registrada exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar la venta.");
            e.printStackTrace();
        }
    }
}
