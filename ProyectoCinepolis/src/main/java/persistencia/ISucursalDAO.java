/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.EntidadSucursal;
import java.util.List;

/**
 *
 * @author David Elier Campa Chaparro 245178
 */
public interface ISucursalDAO {
//    public void insertarSucursal(EntidadSucursal entidadSucursal) throws PersistenciaException;
//    public void editarSucursal(EntidadSucursal entidadSucursal) throws PersistenciaException;
//    public void eliminarSucursal(EntidadSucursal entidadSucursal) throws PersistenciaException;
//    public List<EntidadSucursal> consultarSucursales(int limit, int offset) throws PersistenciaException;
//    public EntidadSucursal consultarSucursalPorID(int id) throws PersistenciaException;
    public double calcularGananciasPorSucursal(int idSucursal) throws PersistenciaException;
    public EntidadSucursal encontrarSucursalMasCercana(int cordXCliente, int cordYCliente) throws PersistenciaException;

}
