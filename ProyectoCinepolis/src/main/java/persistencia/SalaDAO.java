/*
 * SalaDAO.java
 * Clase que implementa el acceso a datos para la entidad Sala.
 * Implementa la interfaz ISalaDAO.
 */
package persistencia;

import entidades.EntidadSala;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa el acceso a datos para la entidad Sala. Implementa la
 * interfaz ISalaDAO.
 *
 * @author David Elier Campa Chaparro 245178
 */
public class SalaDAO implements ISalaDAO {

    private IConexionBD conexionBD;

    /**
     * Constructor de la clase SalaDAO.
     *
     * @param conexionBD Objeto que implementa la interfaz IConexionBD para
     * establecer la conexión a la base de datos.
     */
    public SalaDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Inserta una sala en la base de datos.
     *
     * @param entidadSala Objeto de tipo EntidadSala que representa la sala a
     * insertar.
     * @throws PersistenciaException Si ocurre un error durante la inserción.
     */
    @Override
    public void insertarSala(EntidadSala entidadSala) throws PersistenciaException {
        Connection conexion = null;
        try
        {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);
            String codigoSQL = "INSERT INTO Sala (nombre, asientos, idSucursal) values (?, ?, ?);";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);

            preparedStatement.setString(1, entidadSala.getNombre());
            preparedStatement.setInt(2, entidadSala.getAsientos());
            preparedStatement.setInt(3, entidadSala.getIdSucursal());

            preparedStatement.executeUpdate();
            conexion.commit();
        } catch (SQLException ex)
        {
            if (conexion != null)
            {
                try
                {
                    conexion.rollback();
                } catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrio un error en el rollback");
        } finally
        {
            if (conexion != null)
            {
                try
                {
                    conexion.close();
                } catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }
    } // fin metodo

    /**
     * Edita una sala en la base de datos.
     *
     * @param entidadSala Objeto de tipo EntidadSala que representa la sala a
     * editar.
     * @throws PersistenciaException Si ocurre un error durante la edición.
     */
    @Override
    public void editarSala(EntidadSala entidadSala) throws PersistenciaException {
        Connection conexion = null;
        try
        {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);
            String codigoSQL = "UPDATE Sala SET nombre = ?, asientos = ? WHERE id = ?;";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);

            preparedStatement.setString(1, entidadSala.getNombre());
            preparedStatement.setInt(2, entidadSala.getAsientos());
            preparedStatement.setInt(3, entidadSala.getId());

            preparedStatement.executeUpdate();
            conexion.commit();
        } catch (SQLException ex)
        {
            if (conexion != null)
            {
                try
                {
                    conexion.rollback();
                } catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrio un error en el rollback");
        } finally
        {
            if (conexion != null)
            {
                try
                {
                    conexion.close();
                } catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }
    } // fin metodo

    /**
     * Elimina una sala en la base de datos.
     *
     * @param entidadSala Objeto de tipo EntidadSala que representa la sala a
     * eliminar.
     * @throws PersistenciaException Si ocurre un error durante la eliminación.
     */
    @Override
    public void eliminarSala(EntidadSala entidadSala) throws PersistenciaException {
        Connection conexion = null;
        try
        {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);
            String codigoSQL = "UPDATE Sala SET eliminado = b'1' WHERE id = ?;";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, entidadSala.getId());
            preparedStatement.executeUpdate();
            conexion.commit();
        } catch (SQLException ex)
        {
            if (conexion != null)
            {
                try
                {
                    conexion.rollback();
                } catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrio un error en el rollback");
        } finally
        {
            if (conexion != null)
            {
                try
                {
                    conexion.close();
                } catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }
    } // fin metodo

    /**
     * Consulta una lista de salas en la base de datos.
     *
     * @param limit Límite de resultados a obtener.
     * @param offset Desplazamiento para la consulta.
     * @return Una lista de objetos de tipo EntidadSala.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    @Override
    public List<EntidadSala> consultarSalas(int limit, int offset) throws PersistenciaException {
        try
        {
            List<EntidadSala> salaLista = new ArrayList<>();
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT id, nombre, asientos, idSucursal FROM Sala WHERE eliminado = b'0' LIMIT " + limit + " OFFSET " + offset;
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            while (resultado.next())
            {
                EntidadSala sala = new EntidadSala();
                sala.setId(resultado.getInt("id"));
                sala.setNombre(resultado.getString("nombre"));
                sala.setAsientos(resultado.getInt("asientos"));
                sala.setIdSucursal(resultado.getInt("idSucursal"));
                salaLista.add(sala);
                System.out.println(sala.toString());
            }
            conexion.close();
            return salaLista;
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
            throw new PersistenciaException("Ocurrió un error");
        }
    } // fin metodo

    /**
     * Consulta una sala por su ID en la base de datos.
     *
     * @param id El ID de la sala a consultar.
     * @return Un objeto de tipo EntidadSala.
     * @throws PersistenciaException Si no se encuentra la sala o si ocurre un
     * error durante la consulta.
     */
    @Override
    public EntidadSala consultarSalaPorID(int id) throws PersistenciaException {
        Connection conexion = null;
        try
        {
            conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT id, nombre, asientos, idSucursal FROM sala WHERE id = ? and eliminado = b'0';";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, id);
            ResultSet resultado = preparedStatement.executeQuery();

            if (resultado.next())
            {
                EntidadSala sala = new EntidadSala();
                sala.setId(resultado.getInt("id"));
                sala.setNombre(resultado.getString("nombre"));
                sala.setAsientos(resultado.getInt("asientos"));
                sala.setIdSucursal(resultado.getInt("idSucursal"));
                System.out.println(sala.toString());
                return sala;
            } else
            {
                throw new PersistenciaException("No se encontró la sucursal con ID: " + id);
            }
        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al buscar la sucursal");
        } finally
        {
            if (conexion != null)
            {
                try
                {
                    conexion.close();
                } catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }
    } // fin metodo

    /**
     * Consulta una lista de salas según la sucursal en la base de datos.
     *
     * @param idSucursal El ID de la sucursal.
     * @param limit Límite de resultados a obtener.
     * @param offset Desplazamiento para la consulta.
     * @return Una lista de objetos de tipo EntidadSala.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    @Override
    public List<EntidadSala> consultarSalasSegunSucursal(int idSucursal, int limit, int offset) throws PersistenciaException {
        try
        {
            List<EntidadSala> salaLista = new ArrayList<>();
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT id, nombre, asientos, idSucursal FROM Sala WHERE eliminado = b'0' and idSucursal = " + idSucursal + " LIMIT " + limit + " OFFSET " + offset;
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            while (resultado.next())
            {
                EntidadSala sala = new EntidadSala();
                sala.setId(resultado.getInt("id"));
                sala.setNombre(resultado.getString("nombre"));
                sala.setAsientos(resultado.getInt("asientos"));
                sala.setIdSucursal(resultado.getInt("idSucursal"));
                salaLista.add(sala);
                System.out.println(sala.toString());
            }
            conexion.close();
            return salaLista;
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
            throw new PersistenciaException("Ocurrió un error");
        }
    }

}
