/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
 *
 * @author David Elier Campa Chaparro 245178
 */
public class SalaDAO implements ISalaDAO {
    private IConexionBD conexionBD;

    public SalaDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    @Override
    public void insertarSala(EntidadSala entidadSala) throws PersistenciaException {
        Connection conexion = null;
        try {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);
            String codigoSQL = "INSERT INTO Sala (nombre, asientos, idSucursal) values (?, ?, ?);";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            
            preparedStatement.setString(1, entidadSala.getNombre());
            preparedStatement.setInt(2, entidadSala.getAsientos());
            preparedStatement.setInt(3, entidadSala.getIdSucursal());
            
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
    } // fin metodo

    @Override
    public void editarSala(EntidadSala entidadSala) throws PersistenciaException {
        Connection conexion = null;
        try {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);
            String codigoSQL = "UPDATE Sala SET nombre = ?, asientos = ? WHERE id = ?;";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            
            preparedStatement.setString(1, entidadSala.getNombre());
            preparedStatement.setInt(2, entidadSala.getAsientos());
            preparedStatement.setInt(3, entidadSala.getId());

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
    } // fin metodo

    @Override
    public void eliminarSala(EntidadSala entidadSala) throws PersistenciaException {
        Connection conexion = null;
        try {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);
            String codigoSQL = "UPDATE Sala SET eliminado = b'1' WHERE id = ?;";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, entidadSala.getId());
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
    } // fin metodo

    @Override
    public List<EntidadSala> consultarSalas(int limit, int offset) throws PersistenciaException {
        try {
            List<EntidadSala> salaLista = new ArrayList<>();
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT id, nombre, asientos, idSucursal FROM Sala WHERE eliminado = b'0' LIMIT " + limit  + " OFFSET " + offset;
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            while (resultado.next()) {
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PersistenciaException("Ocurrió un error");
        }
    } // fin metodo

    @Override
    public EntidadSala consultarSalaPorID(int id) throws PersistenciaException {
        Connection conexion = null;
        try {
            conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT id, nombre, asientos, idSucursal FROM sala WHERE id = ? and eliminado = b'0';";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, id);
            ResultSet resultado = preparedStatement.executeQuery();
            
            if (resultado.next()) {
                EntidadSala sala = new EntidadSala();
                sala.setId(resultado.getInt("id"));
                sala.setNombre(resultado.getString("nombre"));
                sala.setAsientos(resultado.getInt("asientos"));
                sala.setIdSucursal(resultado.getInt("idSucursal"));
                System.out.println(sala.toString());
                return sala;
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
    } // fin metodo

    @Override
    public List<EntidadSala> consultarSalasSegunSucursal(int idSucursal, int limit, int offset) throws PersistenciaException {
        try {
            List<EntidadSala> salaLista = new ArrayList<>();
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT id, nombre, asientos, idSucursal FROM Sala WHERE eliminado = b'0' and idSucursal = " + idSucursal + " LIMIT " + limit  + " OFFSET " + offset;
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            while (resultado.next()) {
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PersistenciaException("Ocurrió un error");
        }
    }

    
    
    
    
}
