/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package MainCarlos;

import negocio.ClienteNegocio;
import negocio.IClienteNegocio;
import negocio.IPeliculaNegocio;
import negocio.PeliculaNegocio;
import persistencia.ClienteDAO;
import persistencia.ConexionBD;
import persistencia.IClienteDAO;
import persistencia.IConexionBD;
import persistencia.IPeliculaDAO;
import persistencia.PeliculaDAO;
import presentacion.ctlogo.frmAgregarPeli;
import presentacion.ctlogo.frmInicioSesion;
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
        IConexionBD conexion = new ConexionBD();
        
        IPeliculaDAO peliculaDAO = new PeliculaDAO(conexion);
        
        IPeliculaNegocio peliculaNegocio = new PeliculaNegocio(peliculaDAO);
        
        frmAgregarPeli registro = new frmAgregarPeli(peliculaNegocio);

        // Hace visible el nuevo formulario
        registro.setVisible(true);

        
    }
}

