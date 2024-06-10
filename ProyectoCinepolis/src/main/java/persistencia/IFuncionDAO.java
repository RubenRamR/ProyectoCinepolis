/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.EntidadFuncion;
import java.util.List;

/**
 *
 * @author David Elier Campa Chaparro 245178
 */
public interface IFuncionDAO {
    public void insertarFuncion(EntidadFuncion entidadFuncion) throws PersistenciaException;
    public void editarFuncion(EntidadFuncion entidadFuncion) throws PersistenciaException;
    public void eliminarFuncion(EntidadFuncion entidadFuncion) throws PersistenciaException;
    public List<EntidadFuncion> consultarFunciones(int limit, int offset) throws PersistenciaException;
    public EntidadFuncion consultarFuncionPorID(int id) throws PersistenciaException;
    public List<EntidadFuncion> consultarFuncionesPorPeliculaYSucursal(int idSucursal, int idPelicula, int limit, int offset) throws PersistenciaException;

}
