package negocio;

import dtos.FuncionDTO;
import entidades.EntidadFuncion;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.IFuncionDAO;
import persistencia.PersistenciaException;

/**
 * Clase que representa la lógica de negocio relacionada con las funciones de
 * cine. Esta clase implementa la interfaz IFuncionNegocio.
 *
 * @author rramirez
 */
public class FuncionNegocio implements IFuncionNegocio {

    private IFuncionDAO funcionDAO;
    private static final Logger LOGGER = Logger.getLogger(FuncionNegocio.class.getName());

    public FuncionNegocio(IFuncionDAO funcionDAO) {
        this.funcionDAO = funcionDAO;
    }

    private FuncionDTO convertirFuncionDTO(EntidadFuncion funcion) throws NegocioException {
        if (funcion == null)
        {
            throw new NegocioException("La función es nula");
        }

        FuncionDTO dto = new FuncionDTO();
        dto.setId(funcion.getId());
        dto.setPrecio(funcion.getPrecio());
        dto.setDia(funcion.getDia());
        dto.setInicio(funcion.getInicio());
        dto.setFin(funcion.getFin());
        dto.setTiempoLimpieza(funcion.getTiempoLimpieza());
        dto.setAsientosDisponibles(funcion.getAsientosDisponibles());
        dto.setIdPelicula(funcion.getIdPelicula());
        dto.setIdSala(funcion.getIdSala());

        return dto;
    }

    private EntidadFuncion convertirDTOAEntidad(FuncionDTO funcionDTO) throws NegocioException {
        if (funcionDTO == null)
        {
            throw new NegocioException("La función DTO es nula");
        }

        EntidadFuncion funcion = new EntidadFuncion();
        funcion.setId(funcionDTO.getId());
        funcion.setPrecio(funcionDTO.getPrecio());
        funcion.setDia(funcionDTO.getDia());
        funcion.setInicio(funcionDTO.getInicio());
        funcion.setFin(funcionDTO.getFin());
        funcion.setTiempoLimpieza(funcionDTO.getTiempoLimpieza());
        funcion.setAsientosDisponibles(funcionDTO.getAsientosDisponibles());
        funcion.setIdPelicula(funcionDTO.getIdPelicula());
        funcion.setIdSala(funcionDTO.getIdSala());

        return funcion;
    }

    @Override
    public void insertarFuncion(FuncionDTO funcionDTO) throws NegocioException {
        try
        {
            EntidadFuncion funcion = convertirDTOAEntidad(funcionDTO);
            this.funcionDAO.insertarFuncion(funcion);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al insertar función", ex);
            throw new NegocioException("Error al insertar función: " + ex.getMessage());
        }
    }

    @Override
    public void editarFuncion(FuncionDTO funcionDTO) throws NegocioException {
        try
        {
            EntidadFuncion funcion = convertirDTOAEntidad(funcionDTO);
            this.funcionDAO.editarFuncion(funcion);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al editar función", ex);
            throw new NegocioException("Error al editar función: " + ex.getMessage());
        }
    }

    @Override
    public void eliminarFuncion(FuncionDTO funcionDTO) throws NegocioException {
        try
        {
            EntidadFuncion funcion = convertirDTOAEntidad(funcionDTO);
            this.funcionDAO.eliminarFuncion(funcion);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al eliminar función", ex);
            throw new NegocioException("Error al eliminar función: " + ex.getMessage());
        }
    }

    @Override
    public List<FuncionDTO> consultarFunciones(int limit, int offset) throws NegocioException {
        try
        {
            List<EntidadFuncion> funciones = this.funcionDAO.consultarFunciones(limit, offset);
            List<FuncionDTO> funcionesDTO = new ArrayList<>();
            for (EntidadFuncion funcion : funciones)
            {
                funcionesDTO.add(convertirFuncionDTO(funcion));
            }
            return funcionesDTO;
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al consultar funciones", ex);
            throw new NegocioException("Error al consultar funciones: " + ex.getMessage());
        }
    }

    @Override
    public FuncionDTO consultarFuncionPorID(int id) throws NegocioException {
        try
        {
            EntidadFuncion funcion = this.funcionDAO.consultarFuncionPorID(id);
            return convertirFuncionDTO(funcion);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al consultar función por ID", ex);
            throw new NegocioException("Error al consultar función por ID: " + ex.getMessage());
        }
    }

    @Override
    public List<FuncionDTO> consultarFuncionesPorPeliculaYSucursal(int idSucursal, int idPelicula, int limit, int offset) throws NegocioException {
        try
        {
            List<EntidadFuncion> funciones = this.funcionDAO.consultarFuncionesPorPeliculaYSucursal(idSucursal, idPelicula, limit, offset);
            List<FuncionDTO> funcionesDTO = new ArrayList<>();
            for (EntidadFuncion funcion : funciones)
            {
                funcionesDTO.add(convertirFuncionDTO(funcion));
            }
            return funcionesDTO;
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al consultar funciones por película y sucursal", ex);
            throw new NegocioException("Error al consultar funciones por película y sucursal: " + ex.getMessage());
        }
    }

    @Override
    public void insertarFuncionPorNombreSala(FuncionDTO funcion, String nombreSala) throws NegocioException {
        EntidadFuncion entidadFuncion = convertirDTOAEntidad(funcion);
        try {
            funcionDAO.insertarFuncionPorNombreSala(entidadFuncion, nombreSala);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FuncionNegocio.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException ("Error al insertar la funcion: " + ex.getMessage());
        }
    }
}
