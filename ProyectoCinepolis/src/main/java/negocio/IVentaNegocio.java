/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

/**
 *
 * @author rramirez
 */
public interface IVentaNegocio {
    
    public void insertarVenta(int idCliente, int idFuncion) throws NegocioException;
    
}