/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainDavid;

import entidades.EntidadCliente;
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
/**
 *
 * @author David Elier Campa Chaparro
 */
public class mainDavid {
    
    public static void main(String[] args) {
        IConexionBD conexionBD = new ConexionBD();
        
//        ISucursalDAO sucursalDAO = new SucursalDAO(conexionBD);
//        EntidadSucursal entidadSucursal = new EntidadSucursal( "prueba1", "prueba1", 100, 300);
//
//        IPeliculaDAO peliculaDAO = new PeliculaDAO(conexionBD);
//        Time duracion = Time.valueOf("02:15:00");
//        EntidadPelicula entidadPelicula = new EntidadPelicula(1, "asdasdasd","genero","A","asdasd", duracion ,"asdasd","asdasd","asdd");
        
        IClienteDAO clienteDAO = new ClienteDAO(conexionBD);
        Date fechaNacimiento = Date.valueOf("2003-10-07");
        EntidadCliente entidadCliente = new EntidadCliente(1, "Prueba", "Apellidos prueba", "Ciudad prueba", "Correo prueba", fechaNacimiento, 400, 500);
        
        try {
            clienteDAO.editarCliente(entidadCliente);
        } catch (PersistenciaException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
}
