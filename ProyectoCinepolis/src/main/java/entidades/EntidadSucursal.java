/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author crazy
 */
public class EntidadSucursal {
    int id;
    String nombre;
    String ciudad;
    int coordenadaX;
    int coordenadaY;

    public EntidadSucursal() {
    }
    
    // constructor con todos los atributos
    public EntidadSucursal(int id, String nombre, String ciudad, int coordenadaX, int coordenadaY) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    // constructor sin id
    public EntidadSucursal(String nombre, String ciudad, int coordenadaX, int coordenadaY) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
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

    @Override
    public String toString() {
        return "EntidadSucursal{" + "id=" + id + ", nombre=" + nombre + ", ciudad=" + ciudad + ", coordenadaX=" + coordenadaX + ", coordenadaY=" + coordenadaY + '}';
    }
    
    
    
}
