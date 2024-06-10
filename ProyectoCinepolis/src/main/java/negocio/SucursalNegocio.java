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
 *
 * @author rramirez
 */
public class SucursalNegocio implements ISucursalNegocio {

    private ISucursalDAO sucursalDAO;

    private static final Logger LOGGER = Logger.getLogger(SucursalNegocio.class.getName());

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

}
