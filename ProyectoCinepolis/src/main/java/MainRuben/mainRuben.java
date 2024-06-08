/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainRuben;

import entidades.EntidadSucursal;
import negocio.ISucursalNegocio;
import negocio.SucursalNegocio;
import persistencia.ConexionBD;
import persistencia.IConexionBD;
import persistencia.ISucursalDAO;
import persistencia.PersistenciaException;
import persistencia.SucursalDAO;
import presentacion.frmCrudSucursal;
import presentacion.frmInicioSesion;

/**
 *
 * @author crazy
 */
public class mainRuben {

    public static void main(String[] args) {
        IConexionBD conexionBD = new ConexionBD();
        ISucursalDAO sucursalDAO = new SucursalDAO(conexionBD);
        EntidadSucursal entidadSucursal = new EntidadSucursal( "prueba1", "prueba1", 100, 300);
        EntidadSucursal entidadSucursa2 = new EntidadSucursal( "prueba3", "prueba3", 343, 54);
        EntidadSucursal entidadSucursa3 = new EntidadSucursal( "prueba4", "prueba4", 456, 565);
        EntidadSucursal entidadSucursa4 = new EntidadSucursal( "prueba5", "prueba5", 56, 223);
        try {
            sucursalDAO.consultarSucursales(1000, 0);
        } catch (PersistenciaException ex) {
            System.out.println(ex.getMessage());
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new frmInicioSesion().setVisible(true);
        }
    });
//        IConexionBD conexionBD = new ConexionBD();
//        ISucursalDAO sucursalDAO = new SucursalDAO(conexionBD);
//        ISucursalNegocio sucursalNegocio = new SucursalNegocio(sucursalDAO);
//
//        frmCrudSucursal frmCrud = new frmCrudSucursal(sucursalNegocio);
//        frmCrud.show();
    }

}
