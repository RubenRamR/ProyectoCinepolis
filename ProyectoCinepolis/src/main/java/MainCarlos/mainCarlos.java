/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package MainCarlos;

import dtos.SucursalDTO;
import negocio.ClienteNegocio;
import negocio.FuncionNegocio;
import negocio.IClienteNegocio;
import negocio.IFuncionNegocio;
import negocio.IPeliculaNegocio;
import negocio.ISucursalNegocio;
import negocio.NegocioException;
import negocio.PeliculaNegocio;
import negocio.SucursalNegocio;
import persistencia.ClienteDAO;
import persistencia.ConexionBD;
import persistencia.FuncionDAO;
import persistencia.IClienteDAO;
import persistencia.IConexionBD;
import persistencia.IFuncionDAO;
import persistencia.IPeliculaDAO;
import persistencia.ISucursalDAO;
import persistencia.PeliculaDAO;
import persistencia.PersistenciaException;
import persistencia.SucursalDAO;
import presentacion.ctlogo.frmAgregarPeli;
import presentacion.ctlogo.frmCompraBoletos;
import presentacion.ctlogo.frmInicioSesion;
import presentacion.ctlogo.frmMenuCatalogo;
import presentacion.ctlogo.frmRegistroUsu;

/**
 *
 * @author caarl
 */
public class mainCarlos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IConexionBD conexionBD = new ConexionBD();
        IClienteDAO clienteDAO = new ClienteDAO(conexionBD);
        IClienteNegocio clienteNegocio = new ClienteNegocio(clienteDAO);

        frmInicioSesion fis = new frmInicioSesion(clienteNegocio);
        fis.setVisible(true);

//        IConexionBD conexionBD = new ConexionBD();
//        ISucursalDAO sucursalDAO = new SucursalDAO(conexionBD);
//        ISucursalNegocio sucursalNegocio = new SucursalNegocio(sucursalDAO);
//
//        int cordXCliente = 500;
//        int cordYCliente = 600;
//
//        try
//        {
//            SucursalDTO sucursalMasCercana = sucursalNegocio.encontrarSucursalMasCercana(cordXCliente, cordYCliente);
//
//            System.out.println("Sucursal más cercana");
//            System.out.println("Nombre: " + sucursalMasCercana.getNombre());
//            System.out.println("Coordenada X: " + sucursalMasCercana.getCoordenadaX());
//            System.out.println("Coordenada Y: " + sucursalMasCercana.getCoordenadaY());
//            System.out.println("nombreCiudadd: " + sucursalMasCercana.getNombreCiudad());
//            System.out.println("Eliminado: " + sucursalMasCercana.isEliminado());
//            
//        } catch (NegocioException e)
//        {
//            System.err.println("Error al encontrar la sucursal más cercana: " + e.getMessage());
//            e.printStackTrace();
//        }
    }
}
