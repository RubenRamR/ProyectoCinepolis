/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

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
            throw new NegocioException("No se pudieron obtener los alumnos");
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

    @Override
    public List<SucursalTablaDTO> buscarSucursalTabla() throws NegocioException {
        try
        {
            List<EntidadSucursal> sucursales = this.sucursalDAO.buscarSucursalesTabla(2, 1);
            return this.convertirSucursalTablaDTO(sucursales);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al buscar sucursales", ex);
            System.out.println(ex.getMessage());
            throw new NegocioException(ex.getMessage());
        }
    }

}
