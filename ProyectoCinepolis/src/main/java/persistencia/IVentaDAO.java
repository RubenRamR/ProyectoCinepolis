/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

/**
 *
 * @author rramirez
 */
public interface IVentaDAO {
    
    public void insertarVenta(int idCliente, int idFuncion) throws PersistenciaException;
}