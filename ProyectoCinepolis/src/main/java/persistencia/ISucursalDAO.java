/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.EntidadSucursal;
import java.util.List;

/**
 *
 * @author crazy
 */
public interface ISucursalDAO {
    public void insertarSucursal(EntidadSucursal entidadSucursal) throws PersistenciaException;
    public void editarSucursal(EntidadSucursal entidadSucursal) throws PersistenciaException;
    public void eliminarSucursal(EntidadSucursal entidadSucursal) throws PersistenciaException;
    public List<EntidadSucursal> buscarSucursalesTabla(int limit, int offset) throws PersistenciaException;

}
