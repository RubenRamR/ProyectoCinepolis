/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author crazy
 */
public class RelacionClienteCompraFuncion {
    int id;
    int idCliente;
    int idFuncion;
    

    public RelacionClienteCompraFuncion() {
    }

    // constructor con id
    public RelacionClienteCompraFuncion(int id, int idCliente, int idFuncion) {
        this.id = id;
        this.idCliente = idCliente;
        this.idFuncion = idFuncion;
    }
    
    // constructor sin id
    public RelacionClienteCompraFuncion(int idCliente, int idFuncion) {
        this.idCliente = idCliente;
        this.idFuncion = idFuncion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(int idFuncion) {
        this.idFuncion = idFuncion;
    }
      
}
