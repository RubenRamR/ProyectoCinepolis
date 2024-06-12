/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio;

import dtos.FuncionDTO;
import java.util.List;
import persistencia.PersistenciaException;

/**
 *
 * @author rramirez
 */
public interface IFuncionNegocio {

    public void insertarFuncion(FuncionDTO funcion) throws NegocioException;

    public void editarFuncion(FuncionDTO funcion) throws NegocioException;

    public void eliminarFuncion(FuncionDTO funcion) throws NegocioException;

    public List<FuncionDTO> consultarFunciones(int limit, int offset) throws NegocioException;

    public FuncionDTO consultarFuncionPorID(int id) throws NegocioException;

    public List<FuncionDTO> consultarFuncionesPorPeliculaYSucursal(int idSucursal, int idPelicula, int limit, int offset) throws NegocioException;
    
    public void insertarFuncionPorNombreSala(FuncionDTO funcion, String nombreSala) throws NegocioException;
    

}
