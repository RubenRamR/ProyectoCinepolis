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

    /**
     * Constructor de la clase FuncionNegocio.
     *
     * @param funcionDAO Objeto que implementa la interfaz IFuncionDAO para la
     * persistencia de datos relacionados con las funciones de cine.
     */
    public FuncionNegocio(IFuncionDAO funcionDAO) {
        this.funcionDAO = funcionDAO;
    }

    /**
     * Convierte una instancia de EntidadFuncion en un objeto FuncionDTO.
     *
     * @param funcion La instancia de EntidadFuncion a ser convertida.
     * @return El objeto FuncionDTO correspondiente a la entidad funcion.
     * @throws NegocioException Si la entidad funcion proporcionada es nula.
     */
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

    /**
     * Convierte un objeto FuncionDTO en una instancia de EntidadFuncion.
     *
     * @param funcionDTO El objeto FuncionDTO a ser convertido.
     * @return La instancia de EntidadFuncion correspondiente al objeto
     * funcionDTO.
     * @throws NegocioException Si el objeto funcionDTO proporcionado es nulo.
     */
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

    /**
     * Inserta una nueva función en el sistema.
     *
     * @param funcionDTO El objeto FuncionDTO que representa la función a ser
     * insertada.
     * @throws NegocioException Si ocurre un error durante la inserción de la
     * función.
     */
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

    /**
     * Edita una función existente en el sistema.
     *
     * @param funcionDTO El objeto FuncionDTO que representa la función a ser
     * editada.
     * @throws NegocioException Si ocurre un error durante la edición de la
     * función.
     */
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

    /**
     * Elimina una función del sistema.
     *
     * @param funcionDTO El objeto FuncionDTO que representa la función a ser
     * eliminada.
     * @throws NegocioException Si ocurre un error durante la eliminación de la
     * función.
     */
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

    /**
     * Consulta todas las funciones disponibles en el sistema.
     *
     * @param limit Límite de resultados por consulta.
     * @param offset Desplazamiento de resultados por consulta.
     * @return Una lista de objetos FuncionDTO que representan las funciones
     * consultadas.
     * @throws NegocioException Si ocurre un error durante la consulta de las
     * funciones.
     */
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

    /**
     * Consulta una función por su ID.
     *
     * @param id El ID de la función a consultar.
     * @return El objeto FuncionDTO que representa la función consultada.
     * @throws NegocioException Si ocurre un error durante la consulta de la
     * función por ID.
     */
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

    /**
     * Consulta todas las funciones de una película en una sucursal específica.
     *
     * @param idSucursal El ID de la sucursal.
     * @param idPelicula El ID de la película.
     * @param limit Límite de resultados por consulta.
     * @param offset Desplazamiento de resultados por consulta.
     * @return Una lista de objetos FuncionDTO que representan las funciones
     * consultadas.
     * @throws NegocioException Si ocurre un error durante la consulta de las
     * funciones.
     */
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
}
