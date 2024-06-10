/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.sql.Time;

/**
 *
 * @author rramirez
 */
public class PeliculaDTO {
    private int id;
    private String titulo;
    private String genero;
    private String clasificacion;
    private String sinopsis;
    private Time duracion;
    private String paisOrigen;
    private String trailerLink;
    private byte[] imagen;

    public PeliculaDTO() {
    }
    
    public PeliculaDTO(String titulo, String genero, String clasificacion, String sinopsis, Time duracion, String paisOrigen, String trailerLink, byte[] imagen) {
        this.titulo = titulo;
        this.genero = genero;
        this.clasificacion = clasificacion;
        this.sinopsis = sinopsis;
        this.duracion = duracion;
        this.paisOrigen = paisOrigen;
        this.trailerLink = trailerLink;
        this.imagen = imagen;
    }

    public PeliculaDTO(int id, String titulo, String genero, String clasificacion, String sinopsis, Time duracion, String paisOrigen, String trailerLink, byte[] imagen) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.clasificacion = clasificacion;
        this.sinopsis = sinopsis;
        this.duracion = duracion;
        this.paisOrigen = paisOrigen;
        this.trailerLink = trailerLink;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public Time getDuracion() {
        return duracion;
    }

    public void setDuracion(Time duracion) {
        this.duracion = duracion;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    
    
}