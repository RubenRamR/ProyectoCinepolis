/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author rramirez
 */
public class FuncionDTO {

    private int id;
    private float precio;
    private Date dia;
    private Time inicio;
    private Time fin;
    private Time tiempoLimpieza;
    private int asientosDisponibles;
    private int idPelicula;
    private int idSala;

    public FuncionDTO() {
    }

    public FuncionDTO(float precio, Date dia, Time inicio, Time fin, Time tiempoLimpieza, int asientosDisponibles, int idPelicula, int idSala) {
        this.precio = precio;
        this.dia = dia;
        this.inicio = inicio;
        this.fin = fin;
        this.tiempoLimpieza = tiempoLimpieza;
        this.asientosDisponibles = asientosDisponibles;
        this.idPelicula = idPelicula;
        this.idSala = idSala;
    }

    public FuncionDTO(int id, float precio, Date dia, Time inicio, Time fin, Time tiempoLimpieza, int asientosDisponibles, int idPelicula, int idSala) {
        this.id = id;
        this.precio = precio;
        this.dia = dia;
        this.inicio = inicio;
        this.fin = fin;
        this.tiempoLimpieza = tiempoLimpieza;
        this.asientosDisponibles = asientosDisponibles;
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

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Time getInicio() {
        return inicio;
    }

    public void setInicio(Time inicio) {
        this.inicio = inicio;
    }

    public Time getFin() {
        return fin;
    }

    public void setFin(Time fin) {
        this.fin = fin;
    }

    public Time getTiempoLimpieza() {
        return tiempoLimpieza;
    }

    public void setTiempoLimpieza(Time tiempoLimpieza) {
        this.tiempoLimpieza = tiempoLimpieza;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public void setAsientosDisponibles(int asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
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
