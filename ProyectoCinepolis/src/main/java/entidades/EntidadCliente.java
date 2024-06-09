/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.sql.Date;


/**
 *
 * @author crazy
 */
public class EntidadCliente {
    int id;
    String nombre;
    String apellidos;
    String ciudad;
    String correo;
    Date fechaNacimiento;
    int coordenadaX;
    int coordenadaY;

    public EntidadCliente() {
    }

    // constructor con todos los atributos
    public EntidadCliente(int id, String nombre, String apellidos, String ciudad, String correo, Date fechaNacimiento, int coordenadaX, int coordenadaY) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.ciudad = ciudad;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }
    
    // constructor sin id
    public EntidadCliente(String nombre, String apellidos, String ciudad, String correo, Date fechaNacimiento, int coordenadaX, int coordenadaY) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.ciudad = ciudad;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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
        return "EntidadCliente{" + "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", ciudad=" + ciudad + ", correo=" + correo + ", fechaNacimiento=" + fechaNacimiento + ", coordenadaX=" + coordenadaX + ", coordenadaY=" + coordenadaY + '}';
    }

}
