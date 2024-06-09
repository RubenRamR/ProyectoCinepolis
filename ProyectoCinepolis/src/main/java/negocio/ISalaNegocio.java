/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio;

import dtos.SalaDTO;
import java.util.List;

/**
 *
 * @author rramirez
 */
public interface ISalaNegocio {

    public void insertarSala(SalaDTO Sala) throws NegocioException;

    public void editarSala(SalaDTO entidadSala) throws NegocioException;

    public void eliminarSala(SalaDTO entidadSala) throws NegocioException;

    public List<SalaDTO> consultarSalas(int limit, int offset) throws NegocioException;

    public SalaDTO consultarSalaPorID(int id) throws NegocioException;

    public List<SalaDTO> consultarSalasSegunSucursal(int idSucursal, int limit, int offset) throws NegocioException;
}
