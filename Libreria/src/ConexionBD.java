import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // Configuración de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/libreria";  // Cambiar según sea necesario
    private static final String USUARIO = "root"; // Usuario por defecto de MySQL en localhost
    private static final String CONTRASENA = "";  // La contraseña por defecto de MySQL es vacía en localhost

    // Método para obtener la conexión
    public static Connection conectar() throws SQLException {
        try {
            // Cargar el driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Crear y retornar la conexión a la base de datos
            return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Driver JDBC no encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error: No se pudo conectar a la base de datos.");
            e.printStackTrace();
        }
        return null;
    }

    // Método principal para probar la conexión
    public static void main(String[] args) {
        try (Connection conexion = conectar()) {
            if (conexion != null) {
                System.out.println("Conexión exitosa a la base de datos.");
            } else {
                System.out.println("No se pudo conectar a la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al intentar conectarse.");
            e.printStackTrace();
        }
    }
}
