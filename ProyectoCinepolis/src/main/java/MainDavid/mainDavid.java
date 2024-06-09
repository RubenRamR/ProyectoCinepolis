/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainDavid;

import entidades.EntidadCliente;
import entidades.EntidadFuncion;
import entidades.EntidadPelicula;
import entidades.EntidadSucursal;
import persistencia.ConexionBD;
import persistencia.IConexionBD;
import persistencia.IPeliculaDAO;
import persistencia.ISucursalDAO;
import persistencia.PeliculaDAO;
import persistencia.PersistenciaException;
import persistencia.SucursalDAO;


import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import persistencia.ClienteDAO;
import persistencia.IClienteDAO;


import java.sql.Date;
import persistencia.FuncionDAO;
import persistencia.IFuncionDAO;
/**
 *
 * @author David Elier Campa Chaparro
 */
public class mainDavid {
    
    public static void main(String[] args) {
        IConexionBD conexionBD = new ConexionBD();
        IFuncionDAO funcionDAO = new FuncionDAO(conexionBD);
        
        Date fecha = Date.valueOf("2300-10-10");
        Time inicio = Time.valueOf("14:00:00");
        
        EntidadFuncion funcion = new EntidadFuncion(2, 300, fecha, inicio, inicio, inicio, 50, 2, 2);
        
        try {
            funcionDAO.consultarFuncionPorID(1);
        } catch (PersistenciaException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    
}
