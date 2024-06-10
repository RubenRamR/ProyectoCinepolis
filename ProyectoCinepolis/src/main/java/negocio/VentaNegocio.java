/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.IVentaDAO;
import persistencia.PersistenciaException;

/**
 * Clase que representa la lógica de negocio relacionada con las ventas. Esta
 * clase implementa la interfaz IVentaNegocio.
 *
 * @author rramirez
 */
public class VentaNegocio implements IVentaNegocio {

    private IVentaDAO ventaDAO;
    private static final Logger LOGGER = Logger.getLogger(VentaNegocio.class.getName());

    /**
     * Constructor de la clase VentaNegocio.
     *
     * @param ventaDAO Objeto que implementa la interfaz IVentaDAO para la
     * persistencia de datos relacionados con las ventas.
     */
    public VentaNegocio(IVentaDAO ventaDAO) {
        this.ventaDAO = ventaDAO;
    }

    /**
     * Inserta una nueva venta en el sistema.
     *
     * @param idCliente El ID del cliente que realiza la compra.
     * @param idFuncion El ID de la función relacionada con la venta.
     * @throws NegocioException Si los ID del cliente y/o función son negativos
     * o si ocurre un error durante la inserción de la venta.
     */
    @Override
    public void insertarVenta(int idCliente, int idFuncion) throws NegocioException {
        try
        {
            if (idCliente < 0 || idFuncion < 0)
            {
                throw new NegocioException("Los id del cliente y/o funcion no pueden ser negativos");
            }

            this.ventaDAO.insertarVenta(idCliente, idFuncion);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al insertar venta", ex);
            throw new NegocioException("Error al insertar venta: " + ex.getMessage());
        }
    }

}
