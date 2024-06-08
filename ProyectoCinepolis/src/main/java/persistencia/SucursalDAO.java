/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.EntidadSucursal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author crazy
 */
public class SucursalDAO implements ISucursalDAO {

    private IConexionBD conexionBD;

    public SucursalDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

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
    } 

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

    @Override
    public List<EntidadSucursal> buscarSucursalesTabla(int limit, int offset) throws PersistenciaException {
        try
        {
            List<EntidadSucursal> sucursalLista = new ArrayList<>();
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT id, nombre, ciudad, coordenadaX, coordenadaY FROM Sucursal LIMIT " + limit + " OFFSET " + offset;
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
    }

    @Override
    public EntidadSucursal buscarSucursalPorId(int id) throws PersistenciaException {
        Connection conexion = null;
        EntidadSucursal sucursal = null;
        try
        {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);

            String codigoSQL = "SELECT id, nombre, ciudad, coordenadaX, coordenadaY FROM Sucursal WHERE id = ?";
            PreparedStatement comandoSQL = conexion.prepareStatement(codigoSQL);
            comandoSQL.setInt(1, id);
            ResultSet resultado = comandoSQL.executeQuery();

            if (resultado.next())
            {
                sucursal = new EntidadSucursal();
                sucursal.setId(resultado.getInt("id"));
                sucursal.setNombre(resultado.getString("nombre"));
                sucursal.setCiudad(resultado.getString("ciudad"));
                sucursal.setCoordenadaX(resultado.getInt("coordenadaX"));
                sucursal.setCoordenadaY(resultado.getInt("coordenadaY"));
            }

            conexion.commit(); 
        } catch (SQLException e)
        {
            if (conexion != null)
            {
                try
                {
                    conexion.rollback(); 
                } catch (SQLException rollbackEx)
                {
                    System.out.println(rollbackEx.getMessage());
                    throw new PersistenciaException("Error al revertir la transacción");
                }
            }
            System.out.println(e.getMessage());
            throw new PersistenciaException("Ocurrió un error al buscar la sucursal por ID");
        } finally
        {
            if (conexion != null)
            {
                try
                {
                    conexion.setAutoCommit(true);
                    conexion.close();
                } catch (SQLException closeEx)
                {
                    System.out.println(closeEx.getMessage());
                    throw new PersistenciaException("Error al cerrar la conexión");
                }
            }
        }
        return sucursal;
    }

}
