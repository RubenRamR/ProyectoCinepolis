package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que implementa la interfaz IConexionBD y proporciona métodos para establecer la conexión con la base de datos.
 */
public class ConexionBD implements IConexionBD {
    
    final String SERVER = "localhost";
    final String BASE_DATOS = "cinepolis";
    private final String CADENA_CONEXION = "jdbc:mysql://" + SERVER + "/" + BASE_DATOS;
    final String USUARIO = "root";
    final String CONTRASEÑA = "";
    
    /**
     * Método para crear una conexión con la base de datos.
     * @return Una instancia de Connection que representa la conexión establecida.
     * @throws SQLException Si ocurre un error durante la conexión con la base de datos.
     */
    @Override
    public Connection crearConexion() throws SQLException {
        Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASEÑA);
        return conexion;   
    }
    
}
