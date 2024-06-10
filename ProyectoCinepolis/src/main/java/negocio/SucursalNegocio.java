/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.SucursalDTO;
import dtos.SucursalTablaDTO;
import entidades.EntidadSucursal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.ISucursalDAO;
import persistencia.PersistenciaException;

/**
 * * Clase que representa la lógica de negocio relacionada con las sucursales.
 * Esta clase implementa la interfaz ISucursalNegocio.
 * 
 * @author rramirez
 */
public class SucursalNegocio implements ISucursalNegocio {

    private ISucursalDAO sucursalDAO;

    private static final Logger LOGGER = Logger.getLogger(SucursalNegocio.class.getName());

    /**
     * Constructor de la clase SucursalNegocio.
     * 
     * @param sucursalDAO Objeto que implementa la interfaz ISucursalDAO para la persistencia de datos relacionados con las sucursales.
     */
    public SucursalNegocio(ISucursalDAO sucursalDAO) {
        this.sucursalDAO = sucursalDAO;
    }

    
    private List<SucursalTablaDTO> convertirSucursalTablaDTO(List<EntidadSucursal> sucursales) throws NegocioException {
        if (sucursales == null)
        {
            throw new NegocioException("No se pudieron obtener las sucursales");
        }

        List<SucursalTablaDTO> sucursalDTO = new ArrayList<>();
        for (EntidadSucursal sucursal : sucursales)
        {
            SucursalTablaDTO dto = new SucursalTablaDTO();
            dto.setIdSucursal(sucursal.getId());
            dto.setNombre(sucursal.getNombre());
            dto.setCoordenadaX(sucursal.getCoordenadaX());
            dto.setCoordenadaY(sucursal.getCoordenadaY());
            sucursalDTO.add(dto);
        }
        return sucursalDTO;
    }

    private SucursalDTO convertirSucursalDTO(EntidadSucursal sucursal) throws NegocioException {
        if (sucursal == null)
        {
            throw new NegocioException("La sucursal es nula");
        }

        SucursalDTO dto = new SucursalDTO();
        dto.setIdSucursal(sucursal.getId());
        dto.setNombre(sucursal.getNombre());
        dto.setCiudad(sucursal.getCiudad());
        dto.setCoordenadaX(sucursal.getCoordenadaX());
        dto.setCoordenadaY(sucursal.getCoordenadaY());

        return dto;
    }

    /**
     * Busca las sucursales disponibles y las convierte en una lista de objetos SucursalTablaDTO.
     * 
     * @param limit Límite de resultados por consulta.
     * @param offset Desplazamiento de resultados por consulta.
     * @return Una lista de objetos SucursalTablaDTO que representan las sucursales consultadas.
     * @throws NegocioException Si ocurre un error durante la búsqueda de las sucursales.
     */
    @Override
    public List<SucursalTablaDTO> buscarSucursalTabla(int limit, int offset) throws NegocioException {
        try
        {
            List<EntidadSucursal> sucursales = this.sucursalDAO.consultarSucursales(limit, offset);
            return this.convertirSucursalTablaDTO(sucursales);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al buscar sucursales", ex);
            System.out.println(ex.getMessage());
            throw new NegocioException(ex.getMessage());
        }
    }

    /**
     * Busca una sucursal por su ID y la convierte en un objeto SucursalDTO.
     * 
     * @param id El ID de la sucursal a buscar.
     * @return El objeto SucursalDTO que representa la sucursal consultada.
     * @throws NegocioException Si ocurre un error durante la búsqueda de la sucursal por ID.
     */
    @Override
    public SucursalDTO buscarSucursalPorId(int id) throws NegocioException {
        try
        {
            EntidadSucursal sucursal = this.sucursalDAO.consultarSucursalPorID(id);
            return this.convertirSucursalDTO(sucursal);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al buscar sucursales", ex);
            System.out.println(ex.getMessage());
            throw new NegocioException(ex.getMessage());
        }
    }

    /**
     * Agrega una nueva sucursal al sistema.
     * 
     * @param sucursalDTO El objeto SucursalDTO que representa la sucursal a ser agregada.
     * @throws NegocioException Si ocurre un error durante la inserción de la sucursal.
     */
    @Override
    public void agregarSucursal(SucursalDTO sucursalDTO) throws NegocioException {
        try
        {
            if (sucursalDTO == null)
            {
                throw new NegocioException("La sucursal es nula.");
            }
            if (sucursalDTO.getNombre().length() > 35)
            {
                throw new NegocioException("El nombre de la sucursal excede el límite de caracteres permitidos.");
            }
            if (sucursalDTO.getCiudad().length() > 20)
            {
                throw new NegocioException("La ciudad de la sucursal excede el límite de caracteres permitidos.");
            }

            EntidadSucursal sucursal = new EntidadSucursal();
            sucursal.setId(sucursalDTO.getIdSucursal());
            sucursal.setNombre(sucursalDTO.getNombre());
            sucursal.setCiudad(sucursalDTO.getCiudad());
            sucursal.setCoordenadaX(sucursalDTO.getCoordenadaX());
            sucursal.setCoordenadaY(sucursalDTO.getCoordenadaY());

            this.sucursalDAO.insertarSucursal(sucursal);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al agregar sucursal", ex);
            System.out.println(ex.getMessage());
            throw new NegocioException("Error al agregar sucursal: " + ex.getMessage());
        }
    }

     /**
     * Edita una sucursal existente en el sistema.
     * 
     * @param sucursalDTO El objeto SucursalDTO que representa la sucursal a ser editada.
     * @throws NegocioException Si ocurre un error durante la edición de la sucursal.
     */
    @Override
    public void editarSucursal(SucursalDTO sucursalDTO) throws NegocioException {
        try
        {
            if (sucursalDTO == null)
            {
                throw new NegocioException("La sucursal es nula.");
            }
            if (sucursalDTO.getNombre().length() > 35)
            {
                throw new NegocioException("El nombre de la sucursal excede el límite de caracteres permitidos.");
            }
            if (sucursalDTO.getCiudad().length() > 20)
            {
                throw new NegocioException("La ciudad de la sucursal excede el límite de caracteres permitidos.");
            }
            EntidadSucursal sucursal = new EntidadSucursal();
            sucursal.setId(sucursalDTO.getIdSucursal());
            sucursal.setNombre(sucursalDTO.getNombre());
            sucursal.setCiudad(sucursalDTO.getCiudad());
            sucursal.setCoordenadaX(sucursalDTO.getCoordenadaX());
            sucursal.setCoordenadaY(sucursalDTO.getCoordenadaY());

            this.sucursalDAO.editarSucursal(sucursal);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al editar sucursal", ex);
            System.out.println(ex.getMessage());
            throw new NegocioException("Error al editar sucursal: " + ex.getMessage());
        }
    }

    /**
     * Elimina una sucursal del sistema.
     * 
     * @param sucursalDTO El objeto SucursalDTO que representa la sucursal a ser eliminada.
     * @throws NegocioException Si ocurre un error durante la eliminación de la sucursal.
     */
    @Override
    public void eliminarSucursal(SucursalDTO sucursalDTO) throws NegocioException {
        try
        {
            EntidadSucursal sucursal = new EntidadSucursal();
            sucursal.setId(sucursalDTO.getIdSucursal());
            this.sucursalDAO.eliminarSucursal(sucursal);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al eliminar sucursal", ex);
            System.out.println(ex.getMessage());
            throw new NegocioException("Error al eliminar sucursal: " + ex.getMessage());
        }
    }

    /**
     * Calcula las ganancias totales de una sucursal.
     * 
     * @param idSucursal El ID de la sucursal.
     * @return El valor de las ganancias totales de la sucursal.
     * @throws NegocioException Si ocurre un error durante el cálculo de las ganancias de la sucursal.
     */
    @Override
    public double calcularGananciasPorSucursal(int idSucursal) throws NegocioException {
        try
        {
            return this.sucursalDAO.calcularGananciasPorSucursal(idSucursal);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al calcular ganancias por sucursal", ex);
            throw new NegocioException("Error al calcular ganancias por sucursal: " + ex.getMessage());
        }
    }

}
