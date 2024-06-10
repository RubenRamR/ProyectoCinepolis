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
 * Esta clase implementa la interfaz IClienteNegocio y proporciona métodos para
 * manipular clientes en la base de datos.
 *
 * @author rramirez
 */
public class ClienteNegocio implements IClienteNegocio {

    private IClienteDAO clienteDAO;
    private static final Logger LOGGER = Logger.getLogger(ClienteNegocio.class.getName());

    /**
     * Constructor de la clase ClienteNegocio.
     *
     * @param clienteDAO Una instancia de la interfaz IClienteDAO que se
     * utilizará para interactuar con la capa de persistencia.
     */
    public ClienteNegocio(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    /**
     * Convierte una instancia de EntidadCliente en un objeto ClienteDTO.
     *
     * @param cliente La instancia de EntidadCliente a ser convertida.
     * @return El objeto ClienteDTO correspondiente a la entidad cliente.
     * @throws NegocioException Si la entidad cliente proporcionada es nula.
     */
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

    /**
     * Convierte un objeto ClienteDTO en una instancia de EntidadCliente.
     *
     * @param clienteDTO El objeto ClienteDTO a ser convertido.
     * @return La instancia de EntidadCliente correspondiente al objeto
     * clienteDTO.
     * @throws NegocioException Si el objeto clienteDTO proporcionado es nulo.
     */
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

    /**
     * Inserta un nuevo cliente en la base de datos.
     *
     * @param clienteDTO El objeto ClienteDTO que contiene la información del
     * cliente a insertar.
     * @throws NegocioException Si ocurre un error durante la inserción del
     * cliente.
     */
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

    /**
     * Edita un cliente existente en la base de datos.
     *
     * @param clienteDTO El objeto ClienteDTO que contiene la información
     * actualizada del cliente.
     * @throws NegocioException Si ocurre un error durante la edición del
     * cliente.
     */
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

    /**
     * Elimina un cliente existente de la base de datos.
     *
     * @param clienteDTO El objeto ClienteDTO que contiene la información del
     * cliente a eliminar.
     * @throws NegocioException Si ocurre un error durante la eliminación del
     * cliente.
     */
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

    /**
     * Consulta una lista de clientes desde la base de datos.
     *
     * @param limit El número máximo de resultados a retornar.
     * @param offset El número de resultados a omitir al principio.
     * @return Una lista de objetos ClienteDTO.
     * @throws NegocioException Si ocurre un error durante la consulta de
     * clientes.
     */
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

    /**
     * Consulta un cliente por su ID en la base de datos.
     *
     * @param id El ID del cliente a consultar.
     * @return El objeto ClienteDTO correspondiente al cliente encontrado.
     * @throws NegocioException Si no se encuentra el cliente o si ocurre un
     * error durante la consulta.
     */
    @Override
    public ClienteDTO consultarClientePorID(int id) throws NegocioException {
        try
        {
            EntidadCliente cliente = this.clienteDAO.consultarClientePorID(id);
            return convertirClienteDTO(cliente);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al consultar cliente por ID", ex);
            throw new NegocioException("Error al consultar cliente por ID: " + ex.getMessage());
        }
    }

}
