/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package MainCarlos;

import presentacion.ctlogo.frmInicioSesion;



/**
 *
 * @author caarl
 */
public class mainCarlos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Crea y muestra el formulario 
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new frmInicioSesion().setVisible(true);
        }
    });
        
        
    }
}

