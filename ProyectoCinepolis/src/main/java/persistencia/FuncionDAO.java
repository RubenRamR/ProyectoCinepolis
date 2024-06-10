/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.EntidadFuncion;
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
public class FuncionDAO implements IFuncionDAO{
    
    private IConexionBD conexionBD;

    public FuncionDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    @Override
    public void insertarFuncion(EntidadFuncion entidadFuncion) throws PersistenciaException {
        Connection conexion = null;
        try {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);
            String codigoSQL =  "CALL InsertarFuncion(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, entidadFuncion.getIdPelicula());
            preparedStatement.setInt(2, entidadFuncion.getIdSala());
            preparedStatement.setFloat(3, entidadFuncion.getPrecio());
            preparedStatement.setDate(4, entidadFuncion.getDia());
            preparedStatement.setTime(5, entidadFuncion.getInicio());
            
            preparedStatement.executeUpdate();
            conexion.commit();
        } catch (SQLException ex) {
            if (conexion != null){
                try {
                    conexion.rollback();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrio un error en el rollback");
        } finally {
            if (conexion != null){
                try {
                    conexion.close();
                } catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public void editarFuncion(EntidadFuncion entidadFuncion) throws PersistenciaException {
        Connection conexion = null;
        try {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);
            String codigoSQL = "CALL EditarFuncion(?, ?, ?, ?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, entidadFuncion.getId());
            preparedStatement.setTime(2, entidadFuncion.getInicio());
            preparedStatement.setFloat(3, entidadFuncion.getPrecio());
            preparedStatement.setDate(4, entidadFuncion.getDia());
            
            preparedStatement.executeUpdate();
            conexion.commit();
        } catch (SQLException ex) {
            if (conexion != null){
                try {
                    conexion.rollback();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrio un error en el rollback");
        } finally {
            if (conexion != null){
                try {
                    conexion.close();
                } catch (SQLException e){
                    System.out.println(e.getMessage());
               }
            }
        }
    }

    @Override
    public void eliminarFuncion(EntidadFuncion entidadFuncion) throws PersistenciaException {
        Connection conexion = null;
        try {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);
            String codigoSQL = "UPDATE Funcion SET eliminado = b'0' WHERE id = ?;";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, entidadFuncion.getId());
            
            preparedStatement.executeUpdate();
            conexion.commit();
        } catch (SQLException ex) {
            if (conexion != null){
                try {
                    conexion.rollback();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrio un error en el rollback");
        } finally {
            if (conexion != null){
                try {
                    conexion.close();
                } catch (SQLException e){
                    System.out.println(e.getMessage());
               }
            }
        }
    }

    @Override
    public List<EntidadFuncion> consultarFunciones(int limit, int offset) throws PersistenciaException {
        try {
            List<EntidadFuncion> funcionesLista = new ArrayList<>();
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT id, precio, dia, inicio, fin, tiempoLimpieza, asientosDisponibles, idPelicula, idSala FROM Funcion WHERE eliminado = b'0';";
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            while (resultado.next()) {
                EntidadFuncion funcion = new EntidadFuncion();
                funcion.setId(resultado.getInt("id"));
                funcion.setPrecio(resultado.getFloat("precio"));
                funcion.setDia(resultado.getDate("dia"));
                funcion.setInicio(resultado.getTime("inicio"));
                funcion.setFin(resultado.getTime("fin"));
                funcion.setTiempoLimpieza(resultado.getTime("tiempoLimpieza"));
                funcion.setAsientosDisponibles(resultado.getInt("asientosDisponibles"));
                funcion.setIdSala(resultado.getInt("idSala"));
                funcion.setIdPelicula(resultado.getInt("idPelicula"));
                
                funcionesLista.add(funcion);
                System.out.println(funcion.toString());
            }
            conexion.close();
            return funcionesLista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PersistenciaException("Ocurrió un error");
        }
    }
    
    @Override
    public List<EntidadFuncion> consultarFuncionesPorPeliculaYSucursal(int idSucursal, int idPelicula, int limit, int offset) throws PersistenciaException {
        try {
            List<EntidadFuncion> funcionesLista = new ArrayList<>();
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "call ConsultarFuncionesPorSucursalYPelicula(?, ?, ?, ?);";
            PreparedStatement comandoSQL = conexion.prepareStatement(codigoSQL);
            comandoSQL.setInt(1, idSucursal);
            comandoSQL.setInt(2, idPelicula);
            comandoSQL.setInt(3, limit);
            comandoSQL.setInt(4, offset);
            ResultSet resultado = comandoSQL.executeQuery();
            while (resultado.next()) {
                EntidadFuncion funcion = new EntidadFuncion();
                funcion.setId(resultado.getInt("id"));
                funcion.setPrecio(resultado.getFloat("precio"));
                funcion.setDia(resultado.getDate("dia"));
                funcion.setInicio(resultado.getTime("inicio"));
                funcion.setFin(resultado.getTime("fin"));
                funcion.setAsientosDisponibles(resultado.getInt("asientosDisponibles"));
                funcion.setIdSala(resultado.getInt("idSala"));
                funcion.setIdPelicula(resultado.getInt("idPelicula"));
                
                funcionesLista.add(funcion);
                System.out.println(funcion.toString());
            }
            conexion.close();
            return funcionesLista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PersistenciaException("Ocurrió un error");
        }
    }

    @Override
    public EntidadFuncion consultarFuncionPorID(int id) throws PersistenciaException {
        Connection conexion = null;
        try {
            conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT id, precio, dia, inicio, fin, asientosDisponibles, idPelicula, idSala FROM Funcion WHERE id = ?;";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, id);
            ResultSet resultado = preparedStatement.executeQuery();
            
            if (resultado.next()) {
                EntidadFuncion funcion = new EntidadFuncion();
                funcion.setId(resultado.getInt("id"));
                funcion.setPrecio(resultado.getFloat("precio"));
                funcion.setDia(resultado.getDate("dia"));
                funcion.setInicio(resultado.getTime("inicio"));
                funcion.setFin(resultado.getTime("fin"));
                funcion.setAsientosDisponibles(resultado.getInt("asientosDisponibles"));
                funcion.setIdSala(resultado.getInt("idSala"));
                funcion.setIdPelicula(resultado.getInt("idPelicula"));
                System.out.println(funcion.toString());
                return funcion;
            } else {
                throw new PersistenciaException("No se encontró la sucursal con ID: " + id);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al buscar la sucursal");
        } finally {
            if (conexion != null){
                try {
                    conexion.close();
                } catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    
}
