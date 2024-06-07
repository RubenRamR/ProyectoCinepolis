/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.EntidadSucursal;

/**
 *
 * @author crazy
 */
public interface ISucursalDAO {
    public void insertarSucursal(EntidadSucursal entidadSucursal) throws PersistenciaException;
    public void editarSucursal(EntidadSucursal entidadSucursal) throws PersistenciaException;
    public void eliminarSucursal(EntidadSucursal entidadSucursal) throws PersistenciaException;
}
