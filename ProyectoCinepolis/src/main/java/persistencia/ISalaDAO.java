/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.EntidadSala;
import java.util.List;

/**
 *
 * @author David Elier Campa Chaparro 245178
 */
public interface ISalaDAO {

    public void insertarSala(EntidadSala entidadSala) throws PersistenciaException;

    public void editarSala(EntidadSala entidadSala) throws PersistenciaException;

    public void eliminarSala(EntidadSala entidadSala) throws PersistenciaException;

    public List<EntidadSala> consultarSalas(int limit, int offset) throws PersistenciaException;

    public EntidadSala consultarSalaPorID(int id) throws PersistenciaException;

    public List<EntidadSala> consultarSalasSegunSucursal(int idSucursal, int limit, int offset) throws PersistenciaException;
}
