/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author rramirez
 */
public class SalaDTO {

    int id;
    String nombre;
    int asientos;
    int asientosDisponibles;
    int idSucursal;

    public SalaDTO() {
    }

    public SalaDTO(int id, String nombre, int asientos, int asientosDisponibles, int idSucursal) {
        this.id = id;
        this.nombre = nombre;
        this.asientos = asientos;
        this.asientosDisponibles = asientosDisponibles;
        this.idSucursal = idSucursal;
    }

    public SalaDTO(String nombre, int asientos, int asientosDisponibles, int idSucursal) {
        this.nombre = nombre;
        this.asientos = asientos;
        this.asientosDisponibles = asientosDisponibles;
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

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public void setAsientosDisponibles(int asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }
}
