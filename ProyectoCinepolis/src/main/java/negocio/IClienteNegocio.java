/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio;

import dtos.ClienteDTO;
import java.util.List;

/**
 *
 * @author rramirez
 */
public interface IClienteNegocio {

    public void insertarCliente(ClienteDTO clienteDTO) throws NegocioException;

    public void editarCliente(ClienteDTO clienteDTO) throws NegocioException;

    public void eliminarCliente(ClienteDTO clienteDTO) throws NegocioException;

    public List<ClienteDTO> consultarClientes(int limit, int offset) throws NegocioException;

    public ClienteDTO consultarClientePorID(int id) throws NegocioException;
    
    public boolean consultarClienteLogin(String correo, String contrasena) throws NegocioException;
    
}
