/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.EntidadSucursal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa la interfaz ISucursalDAO para realizar operaciones CRUD
 * en la tabla Sucursal de la base de datos. Esta clase proporciona métodos para
 * insertar, editar, eliminar y consultar sucursales. Además, contiene un método
 * para calcular las ganancias totales de una sucursal.
 *
 * @author David Elier Campa Chaparro 245178 - Ruben
 */
public class SucursalDAO implements ISucursalDAO {

    private IConexionBD conexionBD;

    /**
     * Constructor de la clase SucursalDAO.
     *
     * @param conexionBD Objeto que implementa la interfaz IConexionBD para
     * establecer la conexión con la base de datos.
     */
    public SucursalDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Método para insertar una nueva sucursal en la base de datos.
     *
     * @param entidadSucursal Objeto de tipo EntidadSucursal que contiene la
     * información de la sucursal a insertar.
     * @throws PersistenciaException Si ocurre un error durante la inserción de
     * la sucursal.
     */
    @Override
    public void insertarSucursal(EntidadSucursal entidadSucursal) throws PersistenciaException {
        Connection conexion = null;
        try
        {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);
            String codigoSQL = "INSERT INTO Sucursal(nombre, ciudad, coordenadaX, coordenadaY) values (?, ?, ?, ?);";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);

            preparedStatement.setString(1, entidadSucursal.getNombre());
            preparedStatement.setString(2, entidadSucursal.getCiudad());
            preparedStatement.setInt(3, entidadSucursal.getCoordenadaX());
            preparedStatement.setInt(4, entidadSucursal.getCoordenadaY());
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
    } // fin metodo insertarSucursal

    /**
     * Método para editar una sucursal existente en la base de datos.
     *
     * @param entidadSucursal Objeto de tipo EntidadSucursal que contiene la
     * información actualizada de la sucursal.
     * @throws PersistenciaException Si ocurre un error durante la actualización
     * de la sucursal.
     */
    @Override
    public void editarSucursal(EntidadSucursal entidadSucursal) throws PersistenciaException {
        Connection conexion = null;
        try
        {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);
            String codigoSQL = "UPDATE Sucursal SET nombre = ?, ciudad = ?, coordenadaX = ?, coordenadaY = ? WHERE id = ?;";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);

            preparedStatement.setString(1, entidadSucursal.getNombre());
            preparedStatement.setString(2, entidadSucursal.getCiudad());
            preparedStatement.setInt(3, entidadSucursal.getCoordenadaX());
            preparedStatement.setInt(4, entidadSucursal.getCoordenadaY());
            preparedStatement.setInt(5, entidadSucursal.getId());
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
    } // fin metodo editarSucursal

    /**
     * Método para eliminar una sucursal de la base de datos.
     *
     * @param entidadSucursal Objeto de tipo EntidadSucursal que contiene la
     * información de la sucursal a eliminar.
     * @throws PersistenciaException Si ocurre un error durante la eliminación
     * de la sucursal.
     */
    @Override
    public void eliminarSucursal(EntidadSucursal entidadSucursal) throws PersistenciaException {
        Connection conexion = null;
        try
        {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);
            String codigoSQL = "DELETE FROM Sucursal WHERE id = ?;";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, entidadSucursal.getId());
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
    } // fin metodo eliminarSucursal

    /**
     * Método para consultar todas las sucursales almacenadas en la base de
     * datos.
     *
     * @param limit Límite de resultados por página.
     * @param offset Índice de inicio de los resultados.
     * @return Una lista de objetos de tipo EntidadSucursal que representan las
     * sucursales consultadas.
     * @throws PersistenciaException Si ocurre un error durante la consulta de
     * las sucursales.
     */
    @Override
    public List<EntidadSucursal> consultarSucursales(int limit, int offset) throws PersistenciaException {
        try
        {
            List<EntidadSucursal> sucursalLista = new ArrayList<>();
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT id, nombre, ciudad, coordenadaX, coordenadaY FROM Sucursal WHERE eliminado = b'0' LIMIT " + limit + " OFFSET " + offset;
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            while (resultado.next())
            {
                EntidadSucursal sucursal = new EntidadSucursal();
                sucursal.setId(resultado.getInt("id"));
                sucursal.setNombre(resultado.getString("nombre"));
                sucursal.setCiudad(resultado.getString("ciudad"));
                sucursal.setCoordenadaX(resultado.getInt("coordenadaX"));
                sucursal.setCoordenadaY(resultado.getInt("coordenadaY"));
                sucursalLista.add(sucursal);
                System.out.println(sucursal.toString());
            }
            conexion.close();
            return sucursalLista;
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
            throw new PersistenciaException("Ocurrió un error");
        }
    } // fin metodo buscarSucursales

    /**
     * Método para consultar una sucursal por su ID en la base de datos.
     *
     * @param id El ID de la sucursal a consultar.
     * @return Un objeto de tipo EntidadSucursal que representa la sucursal
     * consultada.
     * @throws PersistenciaException Si la sucursal con el ID especificado no
     * existe.
     */
    @Override
    public EntidadSucursal consultarSucursalPorID(int id) throws PersistenciaException {
        Connection conexion = null;
        try
        {
            conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT id, nombre, ciudad, coordenadaX, coordenadaY FROM Sucursal WHERE id = ?;";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, id);
            ResultSet resultado = preparedStatement.executeQuery();

            if (resultado.next())
            {
                EntidadSucursal sucursal = new EntidadSucursal();
                sucursal.setId(resultado.getInt("id"));
                sucursal.setNombre(resultado.getString("nombre"));
                sucursal.setCiudad(resultado.getString("ciudad"));
                sucursal.setCoordenadaX(resultado.getInt("coordenadaX"));
                sucursal.setCoordenadaY(resultado.getInt("coordenadaY"));
                return sucursal;
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
     * Método para calcular las ganancias totales de una sucursal.
     *
     * @param idSucursal El ID de la sucursal para la cual se calcularán las
     * ganancias.
     * @return Las ganancias totales de la sucursal especificada.
     * @throws PersistenciaException Si ocurre un error durante el cálculo de
     * las ganancias.
     */
    @Override
    public double calcularGananciasPorSucursal(int idSucursal) throws PersistenciaException {
        double ganancias = 0;
        Connection conexion = null;
        CallableStatement callableStatement = null;

        try
        {
            conexion = conexionBD.crearConexion();
            callableStatement = conexion.prepareCall("{CALL CalcularGananciasPorSucursal(?)}");
            callableStatement.setInt(1, idSucursal);
            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next())
            {
                ganancias = resultSet.getDouble("GananciasTotales");
            }
        } catch (SQLException e)
        {
            throw new PersistenciaException("Error al calcular las ganancias por sucursal: " + e.getMessage());
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
