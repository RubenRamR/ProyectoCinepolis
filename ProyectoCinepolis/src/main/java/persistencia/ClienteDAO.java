/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.EntidadCliente;
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
public class ClienteDAO implements IClienteDAO {
    
    private IConexionBD conexionBD;
    
    public ClienteDAO(IConexionBD conexionBD){
        this.conexionBD = conexionBD;
    }
    
    @Override
    public void registrarCliente(EntidadCliente entidadCliente) throws PersistenciaException {
        Connection conexion = null;
        try {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);
            String codigoSQL = "INSERT INTO Cliente(nombre, apellidos, ciudad, correo, fechaNacimiento, contrasena, coordenadaX, coordenadaY) values (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            
            preparedStatement.setString(1, entidadCliente.getNombre());
            preparedStatement.setString(2, entidadCliente.getApellidos());
            preparedStatement.setString(3, entidadCliente.getCiudad());
            preparedStatement.setString(4, entidadCliente.getCorreo());
            preparedStatement.setDate(5, entidadCliente.getFechaNacimiento());
            preparedStatement.setString(6, entidadCliente.getContrasena());
            preparedStatement.setInt(7, entidadCliente.getCoordenadaX());
            preparedStatement.setInt(8, entidadCliente.getCoordenadaY());
            
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
    public List<EntidadCliente> consultarClientes(int limit, int offset) throws PersistenciaException {
        try {
            List<EntidadCliente> clienteLista = new ArrayList<>();
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT id, nombre, apellidos, ciudad, correo, fechaNacimiento, coordenadaX, coordenadaY FROM Cliente WHERE eliminado = b'0' LIMIT " + limit + " OFFSET " + offset;
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            while (resultado.next()) {
                EntidadCliente cliente = new EntidadCliente();
                cliente.setId(resultado.getInt("id"));
                cliente.setNombre(resultado.getString("id"));
                cliente.setNombre(resultado.getString("nombre"));
                cliente.setApellidos(resultado.getString("apellidos"));
                cliente.setCiudad(resultado.getString("ciudad"));
                cliente.setCorreo(resultado.getString("correo"));
                cliente.setFechaNacimiento(resultado.getDate("fechaNacimiento"));
                cliente.setCoordenadaX(resultado.getInt("coordenadaX"));
                cliente.setCoordenadaY(resultado.getInt("coordenadaY"));

                clienteLista.add(cliente);
                System.out.println(cliente.toString());
            }
            conexion.close();
            return clienteLista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PersistenciaException("Ocurrió un error");
        }
    }// fin metodo

    @Override
    public EntidadCliente consultarClientePorID(int id) throws PersistenciaException {
        Connection conexion = null;
        try {
            conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT id, nombre, apellidos, ciudad, correo, fechaNacimiento, coordenadaX, coordenadaY FROM Cliente WHERE id = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, id);
            ResultSet resultado = preparedStatement.executeQuery();
            
            if (resultado.next()) {
                EntidadCliente cliente = new EntidadCliente();
                cliente.setId(resultado.getInt("id"));
                cliente.setNombre(resultado.getString("nombre"));
                cliente.setApellidos(resultado.getString("apellidos"));
                cliente.setCiudad(resultado.getString("ciudad"));
                cliente.setCorreo(resultado.getString("correo"));
                cliente.setFechaNacimiento(resultado.getDate("fechaNacimiento"));
                cliente.setCoordenadaX(resultado.getInt("coordenadaX"));
                cliente.setCoordenadaY(resultado.getInt("coordenadaY"));
                
                System.out.println(cliente.toString());
                return cliente;
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
    }// fin metodo

    @Override
    public void eliminarCliente(EntidadCliente entidadCliente) throws PersistenciaException {
        Connection conexion = null;
        try {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);
            String codigoSQL = "UPDATE Cliente SET eliminado = b'1' WHERE id = ?;";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, entidadCliente.getId());
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
    public void editarCliente(EntidadCliente entidadCliente) throws PersistenciaException {
        Connection conexion = null;
        try {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);
            String codigoSQL = "UPDATE Cliente SET nombre = ?, apellidos = ?, ciudad = ?, correo = ?, fechaNacimiento = ?, contrasena = ?, coordenadaX = ?, coordenadaY = ? WHERE id = ?;";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);

            preparedStatement.setString(1, entidadCliente.getNombre());
            preparedStatement.setString(2, entidadCliente.getApellidos());
            preparedStatement.setString(3, entidadCliente.getCiudad());
            preparedStatement.setString(4, entidadCliente.getCorreo());
            preparedStatement.setDate(5, entidadCliente.getFechaNacimiento());
            preparedStatement.setString(6, entidadCliente.getContrasena());
            preparedStatement.setInt(7, entidadCliente.getCoordenadaX());
            preparedStatement.setInt(8, entidadCliente.getCoordenadaY());
            preparedStatement.setInt(9, entidadCliente.getId());
            
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
    public boolean consultarClienteLogin(String correo, String contrasena) throws PersistenciaException {
        Connection conexion = null;
        try {
            conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT * FROM Cliente WHERE correo = ? and contrasena = ?;";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setString(1, correo);
            preparedStatement.setString(2, contrasena);
            ResultSet resultado = preparedStatement.executeQuery();
            
            if (resultado.next()) {
                
                return true;
            } else {
                throw new PersistenciaException("No se encontró el cliente con correo: " + correo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al buscar el cliente");
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
