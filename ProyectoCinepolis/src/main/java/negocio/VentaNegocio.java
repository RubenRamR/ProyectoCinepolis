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
 *
 * @author rramirez
 */
public class VentaNegocio implements IVentaNegocio {

    private IVentaDAO ventaDAO;
    private static final Logger LOGGER = Logger.getLogger(VentaNegocio.class.getName());

    public VentaNegocio(IVentaDAO ventaDAO) {
        this.ventaDAO = ventaDAO;
    }

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
