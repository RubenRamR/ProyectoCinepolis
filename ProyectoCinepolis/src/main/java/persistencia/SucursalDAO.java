/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.EntidadSucursal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author crazy
 */
public class SucursalDAO implements ISucursalDAO{
    private IConexionBD conexionBD;

    public SucursalDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    @Override
    public void insertarSucursal(EntidadSucursal entidadSucursal) throws PersistenciaException {
        Connection conexion = null;
        try {
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
    } // fin metodo insertarSucursal

    @Override
    public void editarSucursal(EntidadSucursal entidadSucursal) throws PersistenciaException {
    
    } // fin metodo editarSucursal

    @Override
    public void eliminarSucursal(EntidadSucursal entidadSucursal) throws PersistenciaException {
    
    } // fin metodo eliminarSucursal
    
}
