/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.EntidadCliente;
import java.util.List;

/**
 *
 * @author David Elier Campa Chaparro 245178
 */
public interface IClienteDAO {

    public void registrarCliente(EntidadCliente entidadCliente) throws PersistenciaException;

    public void editarCliente(EntidadCliente entidadCliente) throws PersistenciaException;

    public void eliminarCliente(EntidadCliente entidadCliente) throws PersistenciaException;

    public List<EntidadCliente> consultarClientes(int limit, int offset) throws PersistenciaException;

    public EntidadCliente consultarClientePorID(int id) throws PersistenciaException;

    public int consultarClienteLogin(String correo, String contrasena) throws PersistenciaException;
    
    public EntidadCliente obtenerCoordenadasClientePorId(int id) throws PersistenciaException;
}
