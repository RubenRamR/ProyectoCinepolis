/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio;

import dtos.PeliculaDTO;
import entidades.EntidadPelicula;
import java.util.List;

/**
 *
 * @author rramirez
 */
public interface IPeliculaNegocio {
    
    public void insertarPelicula(PeliculaDTO peliculaDTO, int idSucursal) throws NegocioException;

    public void editarPelicula(PeliculaDTO peliculaDTO) throws NegocioException;

    public void eliminarPelicula(PeliculaDTO peliculaDTO) throws NegocioException;

    public List<PeliculaDTO> consultarPeliculas(int limit, int offset) throws NegocioException;

    public PeliculaDTO consultarPeliculaPorID(int id) throws NegocioException;

    public List<PeliculaDTO> consultarPeliculasPorSucursal(int idSucursal, int limit, int offset) throws NegocioException;
}
