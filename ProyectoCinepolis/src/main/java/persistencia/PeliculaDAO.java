/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.EntidadPelicula;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa la interfaz IPeliculaDAO y define los métodos para
 * acceder a la persistencia de datos relacionados con las películas en la base
 * de datos.
 *
 * @author David Elier Campa Chaparro 245178
 */
public class PeliculaDAO implements IPeliculaDAO {

    private IConexionBD conexionBD;

    public PeliculaDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Inserta una nueva película en la base de datos junto con la asociación a
     * una sucursal.
     *
     * @param entidadPelicula Objeto de tipo EntidadPelicula que representa la
     * película a insertar.
     * @param idSucursal ID de la sucursal a la que se asociará la película.
     * @throws PersistenciaException Si ocurre un error durante la inserción en
     * la base de datos.
     */
    @Override
    public void insertarPelicula(EntidadPelicula entidadPelicula, int idSucursal) throws PersistenciaException {
        Connection conexion = null;
        CallableStatement callableStatement = null;
        try
        {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);

            String sql = "{CALL InsertPeliculaConSucursal(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            callableStatement = conexion.prepareCall(sql);

            callableStatement.setString(1, entidadPelicula.getTitulo());
            callableStatement.setString(2, entidadPelicula.getGenero());
            callableStatement.setString(3, entidadPelicula.getClasificacion());
            callableStatement.setString(4, entidadPelicula.getSinopsis());
            callableStatement.setTime(5, entidadPelicula.getDuracion());
            callableStatement.setString(6, entidadPelicula.getPaisOrigen());
            callableStatement.setString(7, entidadPelicula.getTrailerLink());
            callableStatement.setBytes(8, entidadPelicula.getImagen());
            callableStatement.setInt(9, idSucursal); // idSucursal pasado como parámetro

            callableStatement.execute();

            // Confirmar la transacción
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
            throw new PersistenciaException("Ocurrió un error al realizar el rollback");
        } finally
        {
            if (callableStatement != null)
            {
                try
                {
                    callableStatement.close();
                } catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                }
            }
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
    }

    /**
     * Edita una película existente en la base de datos.
     *
     * @param entidadPelicula Objeto de tipo EntidadPelicula que representa la
     * película a editar.
     * @throws PersistenciaException Si ocurre un error durante la edición en la
     * base de datos.
     */
    @Override
    public void editarPelicula(EntidadPelicula entidadPelicula) throws PersistenciaException {
        Connection conexion = null;
        try
        {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);
            String codigoSQL = "UPDATE Pelicula SET titulo = ?, genero = ?, clasificacion = ?, sinopsis = ?, duracion = ?, paisOrigen = ?, trailerLink = ?, imagen = ? WHERE id = ?;";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);

            preparedStatement.setString(1, entidadPelicula.getTitulo());
            preparedStatement.setString(2, entidadPelicula.getGenero());
            preparedStatement.setString(3, entidadPelicula.getClasificacion());
            preparedStatement.setString(4, entidadPelicula.getSinopsis());
            preparedStatement.setTime(5, entidadPelicula.getDuracion());
            preparedStatement.setString(6, entidadPelicula.getPaisOrigen());
            preparedStatement.setString(7, entidadPelicula.getTrailerLink());
            preparedStatement.setBytes(8, entidadPelicula.getImagen());
            preparedStatement.setInt(9, entidadPelicula.getId());
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
            throw new PersistenciaException("Ocurrió un error al realizar el rollback");

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
    }

    /**
     * Elimina una película de la base de datos.
     *
     * @param entidadPelicula Objeto de tipo EntidadPelicula que representa la
     * película a eliminar.
     * @throws PersistenciaException Si ocurre un error durante la eliminación
     * en la base de datos.
     */
    @Override
    public void eliminarPelicula(EntidadPelicula entidadPelicula) throws PersistenciaException {
        Connection conexion = null;
        try
        {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);
            String codigoSQL = "UPDATE Pelicula SET eliminado = b'1' WHERE id = ?;";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, entidadPelicula.getId());
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
            throw new PersistenciaException("Ocurrió un error al realizar el rollback");

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
    }

    /**
     * Consulta todas las películas almacenadas en la base de datos.
     *
     * @param limit Límite de resultados a consultar.
     * @param offset Desplazamiento de los resultados.
     * @return Una lista de objetos de tipo EntidadPelicula que representan las
     * películas consultadas.
     * @throws PersistenciaException Si ocurre un error durante la consulta en
     * la base de datos.
     */
    @Override
    public List<EntidadPelicula> consultarPeliculas(int limit, int offset) throws PersistenciaException {
        try
        {
            List<EntidadPelicula> sucursalLista = new ArrayList<>();
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT id, titulo, genero, clasificacion, sinopsis, duracion, paisOrigen, trailerLink, imagen, eliminado FROM Pelicula WHERE eliminado = b'0' LIMIT " + limit + " OFFSET " + offset;
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            while (resultado.next())
            {
                EntidadPelicula pelicula = new EntidadPelicula();
                pelicula.setId(resultado.getInt("id"));
                pelicula.setTitulo(resultado.getString("titulo"));
                pelicula.setGenero(resultado.getString("genero"));
                pelicula.setClasificacion(resultado.getString("clasificacion"));
                pelicula.setSinopsis(resultado.getString("sinopsis"));
                pelicula.setDuracion(resultado.getTime("duracion"));
                pelicula.setPaisOrigen(resultado.getString("paisOrigen"));
                pelicula.setTrailerLink(resultado.getString("trailerLink"));
                pelicula.setImagen(resultado.getBytes("imagen"));

                sucursalLista.add(pelicula);
                System.out.println(pelicula.toString());
            }
            conexion.close();
            return sucursalLista;
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
            throw new PersistenciaException("Ocurrió un error");
        }
    }

    /**
     * Consulta una película en particular en la base de datos usando su ID.
     *
     * @param id ID de la película a consultar.
     * @return Un objeto de tipo EntidadPelicula que representa la película
     * consultada.
     * @throws PersistenciaException Si ocurre un error durante la consulta en
     * la base de datos.
     */
    @Override
    public EntidadPelicula consultarPeliculaPorID(int id) throws PersistenciaException {
        Connection conexion = null;
        try
        {
            conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT id, titulo, genero, clasificacion, sinopsis, duracion, paisOrigen, trailerLink, imagenURL, eliminado FROM Pelicula WHERE id = ?;";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, id);
            ResultSet resultado = preparedStatement.executeQuery();

            if (resultado.next())
            {
                EntidadPelicula pelicula = new EntidadPelicula();
                pelicula.setId(resultado.getInt("id"));
                pelicula.setTitulo(resultado.getString("titulo"));
                pelicula.setGenero(resultado.getString("genero"));
                pelicula.setClasificacion(resultado.getString("clasificacion"));
                pelicula.setSinopsis(resultado.getString("sinopsis"));
                pelicula.setDuracion(resultado.getTime("duracion"));
                pelicula.setPaisOrigen(resultado.getString("paisOrigen"));
                pelicula.setTrailerLink(resultado.getString("trailerLink"));
                pelicula.setImagen(resultado.getBytes("imagen"));

                System.out.println(pelicula.toString());
                return pelicula;
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
    }

    /**
     * Consulta todas las películas asociadas a una sucursal en particular.
     *
     * @param idSucursal ID de la sucursal.
     * @param limit Límite de resultados a consultar.
     * @param offset Desplazamiento de los resultados.
     * @return Una lista de objetos de tipo EntidadPelicula que representan las
     * películas consultadas.
     * @throws PersistenciaException Si ocurre un error durante la consulta en
     * la base de datos.
     */
    @Override
    public List<EntidadPelicula> consultarPeliculasPorSucursal(int idSucursal, int limit, int offset) throws PersistenciaException {
        List<EntidadPelicula> sucursalLista = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultado = null;

        try
        {
            conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT p.id, p.titulo, p.genero, p.clasificacion, p.sinopsis, p.duracion, p.paisOrigen, p.trailerLink, p.imagen, p.eliminado "
                    + "FROM Pelicula p "
                    + "JOIN Sucursal_Tiene_Pelicula stp ON p.id = stp.idPelicula "
                    + "WHERE stp.idSucursal = ? AND p.eliminado = b'0' "
                    + "LIMIT ? OFFSET ?";

            preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, idSucursal);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);

            resultado = preparedStatement.executeQuery();

            while (resultado.next())
            {
                EntidadPelicula pelicula = new EntidadPelicula();
                pelicula.setId(resultado.getInt("id"));
                pelicula.setTitulo(resultado.getString("titulo"));
                pelicula.setGenero(resultado.getString("genero"));
                pelicula.setClasificacion(resultado.getString("clasificacion"));
                pelicula.setSinopsis(resultado.getString("sinopsis"));
                pelicula.setDuracion(resultado.getTime("duracion"));
                pelicula.setPaisOrigen(resultado.getString("paisOrigen"));
                pelicula.setTrailerLink(resultado.getString("trailerLink"));
                pelicula.setImagen(resultado.getBytes("imagen"));

                sucursalLista.add(pelicula);
                System.out.println(pelicula.toString());
            }

        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
            throw new PersistenciaException("Ocurrió un error al consultar las películas por sucursal");
        } finally
        {
            if (resultado != null)
            {
                try
                {
                    resultado.close();
                } catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                }
            }
            if (preparedStatement != null)
            {
                try
                {
                    preparedStatement.close();
                } catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                }
            }
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

        return sucursalLista;
    }

    /**
     * Calcula las ganancias totales generadas por una película específica.
     *
     * @param idPelicula ID de la película.
     * @return Las ganancias totales generadas por la película.
     * @throws PersistenciaException Si ocurre un error durante el cálculo en la
     * base de datos.
     */
    @Override
    public double calcularGananciasPorPelicula(int idPelicula) throws PersistenciaException {
        double ganancias = 0;
        Connection conexion = null;
        CallableStatement callableStatement = null;

        try
        {
            conexion = conexionBD.crearConexion();
            callableStatement = conexion.prepareCall("{CALL CalcularGananciasPorPelicula(?)}");
            callableStatement.setInt(1, idPelicula);
            callableStatement.execute();

            // Obtener el resultado del procedimiento almacenado
            ResultSet resultSet = callableStatement.getResultSet();
            if (resultSet.next())
            {
                ganancias = resultSet.getDouble("GananciasTotales");
            }
        } catch (SQLException e)
        {
            throw new PersistenciaException("Error al calcular las ganancias por película: " + e.getMessage());
        } finally
        {
            if (callableStatement != null)
            {
                try
                {
                    callableStatement.close();
                } catch (SQLException e)
                {
                    System.out.println("Error al cerrar el CallableStatement: " + e.getMessage());
                }
            }
            if (conexion != null)
            {
                try
                {
                    conexion.close();
                } catch (SQLException e)
                {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }

        return ganancias;
    }

}
