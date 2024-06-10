/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainRuben;

import dtos.FuncionDTO;
import entidades.EntidadSucursal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import negocio.FuncionNegocio;
import negocio.ISucursalNegocio;
import negocio.NegocioException;
import negocio.SucursalNegocio;
import persistencia.ConexionBD;
import persistencia.FuncionDAO;
import persistencia.IConexionBD;
import persistencia.IFuncionDAO;
import persistencia.ISucursalDAO;
import persistencia.IVentaDAO;
import persistencia.PeliculaDAO;
import persistencia.PersistenciaException;
import persistencia.SucursalDAO;
import persistencia.VentaDAO;

/**
 *
 * @author crazy
 */
public class mainRuben {

    public static void main(String[] args) {
        
//        Ganancias sucursales
//        ConexionBD conexionBD = new ConexionBD(); // Implementación de IConexionBD
//        SucursalDAO sucursalDAO = new SucursalDAO(conexionBD);
//
//        int idSucursal = 1; // ID de la sucursal que quieres probar
//        try {
//            double ganancias = sucursalDAO.calcularGananciasPorSucursal(idSucursal);
//            System.out.println("Las ganancias totales para la sucursal con ID " + idSucursal + " son: " + ganancias);
//        } catch (PersistenciaException e) {
//            System.out.println("Error al calcular las ganancias: " + e.getMessage());
//        }
        
//        Ganancias peliculas
//        IConexionBD conexionBD = new ConexionBD(); // Implementación de IConexionBD
//        PeliculaDAO peliculaDAO = new PeliculaDAO(conexionBD);
//
//        int idPelicula = 1; // ID de la película que quieres probar
//        try {
//            double ganancias = peliculaDAO.calcularGananciasPorPelicula(idPelicula);
//            System.out.println("Las ganancias totales para la pelicula con ID " + idPelicula + " son: " + ganancias);
//        } catch (PersistenciaException e) {
//            System.out.println("Error al calcular las ganancias: " + e.getMessage());
//        }
        
        
//        // Simulando la conexión a la base de datos
//        IConexionBD conexionBD = new ConexionBD(); // Reemplaza "MiConexionBD" con tu clase de conexión real
//
//        // Crear una instancia de VentaDAO
//        VentaDAO ventaDAO = new VentaDAO(conexionBD);
//
//        // ID del cliente y función de prueba
//        int idCliente = 1;
//        int idFuncion = 7; // Reemplaza este valor con el ID de la función que desees probar
//
//        try {
//            // Insertar la venta
//            ventaDAO.insertarVenta(idCliente, idFuncion);
//            System.out.println("Venta realizada con éxito.");
//
//        } catch (PersistenciaException e) {
//            System.out.println("Error al insertar la venta: " + e.getMessage());
//        }
//        IConexionBD conexionBD = new ConexionBD();
//        ISucursalDAO sucursalDAO = new SucursalDAO(conexionBD);
//        ISucursalNegocio sucursalNegocio = new SucursalNegocio(sucursalDAO);
//
//        frmCrudSucursal frmCrud = new frmCrudSucursal(sucursalNegocio);
//        frmCrud.show();
    }
    }
