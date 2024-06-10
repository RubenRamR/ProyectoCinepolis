package negocio;

import dtos.SalaDTO;
import entidades.EntidadSala;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.ISalaDAO;
import persistencia.PersistenciaException;

/**
 * Clase que representa la lógica de negocio relacionada con las salas de cine.
 * Esta clase implementa la interfaz ISalaNegocio.
 *
 * @author rramirez
 */
public class SalaNegocio implements ISalaNegocio {

    private ISalaDAO salaDAO;
    private static final Logger LOGGER = Logger.getLogger(SalaNegocio.class.getName());

    /**
     * Constructor de la clase SalaNegocio.
     *
     * @param salaDAO Objeto que implementa la interfaz ISalaDAO para la
     * persistencia de datos relacionados con las salas.
     */
    public SalaNegocio(ISalaDAO salaDAO) {
        this.salaDAO = salaDAO;
    }

    /**
     * Inserta una nueva sala en el sistema.
     *
     * @param salaDTO El objeto SalaDTO que representa la sala a ser insertada.
     * @throws NegocioException Si ocurre un error durante la inserción de la
     * sala.
     */
    @Override
    public void insertarSala(SalaDTO salaDTO) throws NegocioException {
        try
        {
            if (salaDTO == null)
            {
                throw new NegocioException("La sala DTO es nula");
            }
            if (salaDTO.getNombre().length() > 50)
            {
                throw new NegocioException("El nombre de la sala no puede exceder los 50 caracteres");
            }
            if (salaDTO.getAsientos() <= 0)
            {
                throw new NegocioException("El número de asientos debe ser mayor que cero");
            }

            EntidadSala sala = convertirADTOEntidad(salaDTO);
            this.salaDAO.insertarSala(sala);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al insertar sala", ex);
            throw new NegocioException("Error al insertar sala: " + ex.getMessage());
        }
    }

    /**
     * Edita una sala existente en el sistema.
     *
     * @param salaDTO El objeto SalaDTO que representa la sala a ser editada.
     * @throws NegocioException Si ocurre un error durante la edición de la
     * sala.
     */
    @Override
    public void editarSala(SalaDTO salaDTO) throws NegocioException {
        try
        {
            if (salaDTO == null)
            {
                throw new NegocioException("La sala DTO es nula");
            }
            if (salaDTO.getNombre().length() > 50)
            {
                throw new NegocioException("El nombre de la sala no puede exceder los 50 caracteres");
            }
            if (salaDTO.getAsientos() <= 0)
            {
                throw new NegocioException("El número de asientos debe ser mayor que cero");
            }
            EntidadSala sala = convertirADTOEntidad(salaDTO);
            this.salaDAO.editarSala(sala);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al editar sala", ex);
            throw new NegocioException("Error al editar sala: " + ex.getMessage());
        }
    }

    /**
     * Elimina una sala del sistema.
     *
     * @param salaDTO El objeto SalaDTO que representa la sala a ser eliminada.
     * @throws NegocioException Si ocurre un error durante la eliminación de la
     * sala.
     */
    @Override
    public void eliminarSala(SalaDTO salaDTO) throws NegocioException {
        try
        {
            EntidadSala sala = convertirADTOEntidad(salaDTO);
            this.salaDAO.eliminarSala(sala);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al eliminar sala", ex);
            throw new NegocioException("Error al eliminar sala: " + ex.getMessage());
        }
    }

    /**
     * Consulta todas las salas disponibles en el sistema.
     *
     * @param limit Límite de resultados por consulta.
     * @param offset Desplazamiento de resultados por consulta.
     * @return Una lista de objetos SalaDTO que representan las salas
     * consultadas.
     * @throws NegocioException Si ocurre un error durante la consulta de las
     * salas.
     */
    @Override
    public List<SalaDTO> consultarSalas(int limit, int offset) throws NegocioException {
        try
        {
            List<EntidadSala> salas = this.salaDAO.consultarSalas(limit, offset);
            List<SalaDTO> salasDTO = new ArrayList<>();
            for (EntidadSala sala : salas)
            {
                salasDTO.add(convertirSalaDTO(sala));
            }
            return salasDTO;
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al consultar salas", ex);
            throw new NegocioException("Error al consultar salas: " + ex.getMessage());
        }
    }

    /**
     * Consulta una sala por su ID.
     *
     * @param id El ID de la sala a consultar.
     * @return El objeto SalaDTO que representa la sala consultada.
     * @throws NegocioException Si ocurre un error durante la consulta de la
     * sala por ID.
     */
    @Override
    public SalaDTO consultarSalaPorID(int id) throws NegocioException {
        try
        {
            EntidadSala sala = this.salaDAO.consultarSalaPorID(id);
            return convertirSalaDTO(sala);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al consultar sala por ID", ex);
            throw new NegocioException("Error al consultar sala por ID: " + ex.getMessage());
        }
    }

    /**
     * Consulta todas las salas de una sucursal específica.
     *
     * @param idSucursal El ID de la sucursal.
     * @param limit Límite de resultados por consulta.
     * @param offset Desplazamiento de resultados por consulta.
     * @return Una lista de objetos SalaDTO que representan las salas
     * consultadas.
     * @throws NegocioException Si ocurre un error durante la consulta de las
     * salas.
     */
    @Override
    public List<SalaDTO> consultarSalasSegunSucursal(int idSucursal, int limit, int offset) throws NegocioException {
        try
        {
            List<EntidadSala> salas = this.salaDAO.consultarSalasSegunSucursal(idSucursal, limit, offset);
            List<SalaDTO> salasDTO = new ArrayList<>();
            for (EntidadSala sala : salas)
            {
                salasDTO.add(convertirSalaDTO(sala));
            }
            return salasDTO;
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al consultar salas según sucursal", ex);
            throw new NegocioException("Error al consultar salas según sucursal: " + ex.getMessage());
        }
    }

    /**
     * Convierte una instancia de EntidadSala en un objeto SalaDTO.
     *
     * @param sala La instancia de EntidadSala a ser convertida.
     * @return El objeto SalaDTO correspondiente a la entidad sala.
     */
    private SalaDTO convertirSalaDTO(EntidadSala sala) {
        SalaDTO dto = new SalaDTO();
        dto.setId(sala.getId());
        dto.setNombre(sala.getNombre());
        dto.setAsientos(sala.getAsientos());
        dto.setIdSucursal(sala.getIdSucursal());
        return dto;
    }

    /**
     * Convierte un objeto SalaDTO en una instancia de EntidadSala.
     *
     * @param salaDTO El objeto SalaDTO a ser convertido.
     * @return La instancia de EntidadSala correspondiente al objeto salaDTO.
     */
    private EntidadSala convertirADTOEntidad(SalaDTO salaDTO) {
        EntidadSala entidad = new EntidadSala();
        entidad.setId(salaDTO.getId());
        entidad.setNombre(salaDTO.getNombre());
        entidad.setAsientos(salaDTO.getAsientos());
        entidad.setIdSucursal(salaDTO.getIdSucursal());
        return entidad;
    }
}
