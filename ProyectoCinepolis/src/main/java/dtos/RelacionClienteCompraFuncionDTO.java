/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author rramirez
 */
public class RelacionClienteCompraFuncionDTO {

    int id;
    int idCliente;
    int idFuncion;

    public RelacionClienteCompraFuncionDTO() {
    }

    public RelacionClienteCompraFuncionDTO(int id, int idCliente, int idFuncion) {
        this.id = id;
        this.idCliente = idCliente;
        this.idFuncion = idFuncion;
    }

    public RelacionClienteCompraFuncionDTO(int idCliente, int idFuncion) {
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
