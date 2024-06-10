/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package MainCarlos;

import negocio.ClienteNegocio;
import negocio.FuncionNegocio;
import negocio.IClienteNegocio;
import negocio.IFuncionNegocio;
import negocio.IPeliculaNegocio;
import negocio.PeliculaNegocio;
import persistencia.ClienteDAO;
import persistencia.ConexionBD;
import persistencia.FuncionDAO;
import persistencia.IClienteDAO;
import persistencia.IConexionBD;
import persistencia.IFuncionDAO;
import persistencia.IPeliculaDAO;
import persistencia.PeliculaDAO;
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
    }
}

