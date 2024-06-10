/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.RelacionClienteCompraFuncion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Clase que implementa la interfaz IVentaDAO para realizar operaciones
 * relacionadas con las ventas en la base de datos. Esta clase proporciona un
 * método para insertar una nueva venta en la base de datos.
 *
 * @author rramirez
 */
public class VentaDAO implements IVentaDAO {

    private IConexionBD conexionBD;

    /**
     * Constructor de la clase VentaDAO.
     *
     * @param conexionBD Objeto que implementa la interfaz IConexionBD para
     * establecer la conexión con la base de datos.
     */
    public VentaDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Método para insertar una nueva venta en la base de datos.
     *
     * @param idCliente El ID del cliente que realizó la compra.
     * @param idFuncion El ID de la función para la cual se realizó la compra.
     * @throws PersistenciaException Si ocurre un error durante la inserción de
     * la venta.
     */
    @Override
    public void insertarVenta(int idCliente, int idFuncion) throws PersistenciaException {
        Connection conexion = null;
        CallableStatement callableStatement = null;
        try
        {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);

            String sql = "{CALL InsertarVenta(?, ?)}";
            callableStatement = conexion.prepareCall(sql);

            callableStatement.setInt(1, idCliente);
            callableStatement.setInt(2, idFuncion);

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

}
