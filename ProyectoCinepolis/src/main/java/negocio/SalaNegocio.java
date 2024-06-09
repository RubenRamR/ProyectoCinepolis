package negocio;

import dtos.SalaDTO;
import entidades.EntidadSala;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.ISalaDAO;
import persistencia.PersistenciaException;

public class SalaNegocio implements ISalaNegocio {

    private ISalaDAO salaDAO;
    private static final Logger LOGGER = Logger.getLogger(SalaNegocio.class.getName());

    public SalaNegocio(ISalaDAO salaDAO) {
        this.salaDAO = salaDAO;
    }

    @Override
    public void insertarSala(SalaDTO salaDTO) throws NegocioException {
        try
        {
            EntidadSala sala = convertirADTOEntidad(salaDTO);
            this.salaDAO.insertarSala(sala);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al insertar sala", ex);
            throw new NegocioException("Error al insertar sala: " + ex.getMessage());
        }
    }

    @Override
    public void editarSala(SalaDTO salaDTO) throws NegocioException {
        try
        {
            EntidadSala sala = convertirADTOEntidad(salaDTO);
            this.salaDAO.editarSala(sala);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al editar sala", ex);
            throw new NegocioException("Error al editar sala: " + ex.getMessage());
        }
    }

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

    private SalaDTO convertirSalaDTO(EntidadSala sala) {
        SalaDTO dto = new SalaDTO();
        dto.setId(sala.getId());
        dto.setNombre(sala.getNombre());
        dto.setAsientos(sala.getAsientos());
        dto.setAsientosDisponibles(sala.getAsientosDisponibles());
        dto.setIdSucursal(sala.getIdSucursal());
        return dto;
    }

    private EntidadSala convertirADTOEntidad(SalaDTO salaDTO) {
        EntidadSala entidad = new EntidadSala();
        entidad.setId(salaDTO.getId());
        entidad.setNombre(salaDTO.getNombre());
        entidad.setAsientos(salaDTO.getAsientos());
        entidad.setAsientosDisponibles(salaDTO.getAsientosDisponibles());
        entidad.setIdSucursal(salaDTO.getIdSucursal());
        return entidad;
    }
}
