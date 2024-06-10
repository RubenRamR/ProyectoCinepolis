/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author rramirez
 */
public class SucursalDTO {

    private int idSucursal;
    private String nombre;
    private String ciudad;
    private int coordenadaX;
    private int coordenadaY;

    public SucursalDTO() {
    }

    public SucursalDTO(int idSucursal, String nombre, String cuidad, int coordenadaX, int coordenadaY) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.ciudad = cuidad;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String cuidad) {
        this.ciudad = cuidad;
    }

    public int getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(int coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public int getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(int coordenadaY) {
        this.coordenadaY = coordenadaY;
    }
}
