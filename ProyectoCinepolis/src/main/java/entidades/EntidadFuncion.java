/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.sql.Time;
import java.sql.Timestamp;

/**
 *
 * @author crazy
 */
public class EntidadFuncion {
    int id;
    float precio;
    Timestamp inicio;
    Timestamp fin;
    Time tiempoLimpieza;
    int idPelicula;
    int idSala;

    
    public EntidadFuncion() {
    }
    
    // constructor con todos los atributos
    public EntidadFuncion(int id, float precio, Timestamp inicio, Timestamp fin, Time tiempoLimpieza, int idPelicula, int idSala) {
        this.id = id;
        this.precio = precio;
        this.inicio = inicio;
        this.fin = fin;
        this.tiempoLimpieza = tiempoLimpieza;
        this.idPelicula = idPelicula;
        this.idSala = idSala;
    }
    
    // Constructor sin id
    public EntidadFuncion(float precio, Timestamp inicio, Timestamp fin, Time tiempoLimpieza, int idPelicula, int idSala) {
        this.precio = precio;
        this.inicio = inicio;
        this.fin = fin;
        this.tiempoLimpieza = tiempoLimpieza;
        this.idPelicula = idPelicula;
        this.idSala = idSala;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Timestamp getInicio() {
        return inicio;
    }

    public void setInicio(Timestamp inicio) {
        this.inicio = inicio;
    }

    public Timestamp getFin() {
        return fin;
    }

    public void setFin(Timestamp fin) {
        this.fin = fin;
    }

    public Time getTiempoLimpieza() {
        return tiempoLimpieza;
    }

    public void setTiempoLimpieza(Time tiempoLimpieza) {
        this.tiempoLimpieza = tiempoLimpieza;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    
    
    
    
}
