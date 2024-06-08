/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.EntidadPelicula;
import java.util.List;

/**
 *
 * @author David Elier Campa Chaparro 245178
 */
public interface IPeliculaDAO {
    public void insertarPelicula (EntidadPelicula entidadPelicula) throws PersistenciaException;
    public void editarPelicula (EntidadPelicula entidadPelicula) throws PersistenciaException;
    public void eliminarPelicula (EntidadPelicula entidadPelicula) throws PersistenciaException;
    public List <EntidadPelicula> consultarPeliculas (int limit, int offset) throws PersistenciaException;
    public EntidadPelicula consultarPeliculaPorID(int id) throws PersistenciaException;
    public List <EntidadPelicula> consultarPeliculasPorSucursal (int idSucursal, int limit, int offset) throws PersistenciaException;

}
