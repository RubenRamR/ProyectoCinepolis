/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio;

import dtos.SucursalDTO;
import dtos.SucursalTablaDTO;
import java.util.List;
import java.util.logging.Logger;
import persistencia.ISucursalDAO;

/**
 *
 * @author rramirez
 */
public interface ISucursalNegocio {
    
        public List<SucursalTablaDTO> buscarSucursalTabla(int limit, int offset) throws NegocioException;
        public SucursalDTO buscarSucursalPorId(int id) throws NegocioException;
}
