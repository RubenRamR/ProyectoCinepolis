/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author crazy
 */
public class EntidadSala {
    
    int id;
    String nombre;
    int asientos;
    int idSucursal;

    public EntidadSala() {
    }

    // constructor con todos los atributos
    public EntidadSala(int id, String nombre, int asientos, int idSucursal) {
        this.id = id;
        this.nombre = nombre;
        this.asientos = asientos;
        this.idSucursal = idSucursal;
    }

    // constructor sin id
    public EntidadSala(String nombre, int asientos, int asientosDisponibles, int idSucursal) {
        this.nombre = nombre;
        this.asientos = asientos;
        this.idSucursal = idSucursal;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAsientos() {
        return asientos;
    }

    public void setAsientos(int asientos) {
        this.asientos = asientos;
    }


    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    @Override
    public String toString() {
        return "EntidadSala{" + "id=" + id + ", nombre=" + nombre + ", asientos=" + asientos + ", idSucursal=" + idSucursal + '}';
    }
    
    
    
    
}
