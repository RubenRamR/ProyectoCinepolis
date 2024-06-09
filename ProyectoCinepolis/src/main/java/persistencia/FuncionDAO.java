/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.EntidadFuncion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            String codigoSQL =  "SET @idPelicula = 1; " +
                                "SET @idSala = 1; " +
                                "SET @precio = 100; " +
                                "SET @dia = '2025-01-01'; " +
                                "SET @inicio = '12:00:00'; " +
                                "SET @duracion = (SELECT duracion FROM Pelicula WHERE id = @idPelicula); " +
                                "SET @tiempoLimpieza = '00:30:00'; " +
                                "SET @fin = ADDTIME(ADDTIME(@inicio, @duracion), @tiempoLimpieza); " +
                                "SET @asientosDisponibles = (SELECT asientos FROM Sala WHERE id = @idSala); " +
                                "INSERT INTO Funcion (precio, dia, inicio, fin, tiempoLimpieza, asientosDisponibles, idPelicula, idSala) " +
                                "VALUES (@precio, @dia, @inicio, @fin, @tiempoLimpieza, @asientosDisponibles, @idPelicula, @idSala);";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarFuncion(EntidadFuncion entidadFuncion) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<EntidadFuncion> consultarFunciones(int limit, int offset) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public EntidadFuncion consultarFuncionPorID(int id) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
