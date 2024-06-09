package negocio;

import dtos.ClienteDTO;
import entidades.EntidadCliente;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.IClienteDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author rramirez
 */
public class ClienteNegocio implements IClienteNegocio {

    private IClienteDAO clienteDAO;
    private static final Logger LOGGER = Logger.getLogger(ClienteNegocio.class.getName());

    public ClienteNegocio(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    private ClienteDTO convertirClienteDTO(EntidadCliente cliente) throws NegocioException {
        if (cliente == null)
        {
            throw new NegocioException("El cliente es nulo");
        }

        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setApellidos(cliente.getApellidos());
        dto.setCiudad(cliente.getCiudad());
        dto.setCorreo(cliente.getCorreo());
        dto.setFechaNacimiento(cliente.getFechaNacimiento());
        dto.setCoordenadaX(cliente.getCoordenadaX());
        dto.setCoordenadaY(cliente.getCoordenadaY());

        return dto;
    }

    private EntidadCliente convertirDTOAEntidad(ClienteDTO clienteDTO) throws NegocioException {
        if (clienteDTO == null)
        {
            throw new NegocioException("El cliente es nulo");
        }

        EntidadCliente cliente = new EntidadCliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellidos(clienteDTO.getApellidos());
        cliente.setCiudad(clienteDTO.getCiudad());
        cliente.setCorreo(clienteDTO.getCorreo());
        cliente.setFechaNacimiento(clienteDTO.getFechaNacimiento());
        cliente.setCoordenadaX(clienteDTO.getCoordenadaX());
        cliente.setCoordenadaY(clienteDTO.getCoordenadaY());

        return cliente;
    }

    @Override
    public void insertarCliente(ClienteDTO clienteDTO) throws NegocioException {
        try
        {
            if (clienteDTO == null)
            {
                throw new NegocioException("El cliente DTO es nulo");
            }
            if (clienteDTO.getNombre().length() > 50)
            {
                throw new NegocioException("El nombre del cliente no puede exceder los 50 caracteres");
            }
            if (clienteDTO.getApellidos().length() > 50)
            {
                throw new NegocioException("Los apellidos del cliente no pueden exceder los 50 caracteres");
            }
            if (clienteDTO.getCiudad().length() > 40)
            {
                throw new NegocioException("La ciudad del cliente no puede exceder los 40 caracteres");
            }
            if (clienteDTO.getCorreo().length() > 50)
            {
                throw new NegocioException("El correo del cliente no puede exceder los 50 caracteres");
            }

            EntidadCliente cliente = convertirDTOAEntidad(clienteDTO);
            this.clienteDAO.registrarCliente(cliente);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al insertar cliente", ex);
            throw new NegocioException("Error al insertar cliente: " + ex.getMessage());
        }
    }

    @Override
    public void editarCliente(ClienteDTO clienteDTO) throws NegocioException {
        try
        {
            if (clienteDTO == null)
            {
                throw new NegocioException("El cliente DTO es nulo");
            }
            if (clienteDTO.getNombre().length() > 50)
            {
                throw new NegocioException("El nombre del cliente no puede exceder los 50 caracteres");
            }
            if (clienteDTO.getApellidos().length() > 50)
            {
                throw new NegocioException("Los apellidos del cliente no pueden exceder los 50 caracteres");
            }
            if (clienteDTO.getCiudad().length() > 40)
            {
                throw new NegocioException("La ciudad del cliente no puede exceder los 40 caracteres");
            }
            if (clienteDTO.getCorreo().length() > 50)
            {
                throw new NegocioException("El correo del cliente no puede exceder los 50 caracteres");
            }
            
            EntidadCliente cliente = convertirDTOAEntidad(clienteDTO);
            this.clienteDAO.editarCliente(cliente);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al editar cliente", ex);
            throw new NegocioException("Error al editar cliente: " + ex.getMessage());
        }
    }

    @Override
    public void eliminarCliente(ClienteDTO clienteDTO) throws NegocioException {
        try
        {
            EntidadCliente cliente = convertirDTOAEntidad(clienteDTO);
            this.clienteDAO.eliminarCliente(cliente);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al eliminar cliente", ex);
            throw new NegocioException("Error al eliminar cliente: " + ex.getMessage());
        }
    }

    @Override
    public List<ClienteDTO> consultarClientes(int limit, int offset) throws NegocioException {
        try
        {
            List<EntidadCliente> clientes = this.clienteDAO.consultarClientes(limit, offset);
            List<ClienteDTO> clientesDTO = new ArrayList<>();
            for (EntidadCliente cliente : clientes)
            {
                clientesDTO.add(convertirClienteDTO(cliente));
            }
            return clientesDTO;
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al consultar clientes", ex);
            throw new NegocioException("Error al consultar clientes: " + ex.getMessage());
        }
    }

    @Override
    public ClienteDTO consultarClientePorID(int id) throws NegocioException {
        try
        {
            EntidadCliente cliente = this.clienteDAO.consultarClientePorID(id);
            return convertirClienteDTO(cliente);
        } catch (PersistenciaException ex)
        {
            LOGGER.log( Level.SEVERE, "Error al consultar cliente por ID", ex);
            throw new NegocioException("Error al consultar cliente por ID: " + ex.getMessage());
        }
    }

}
