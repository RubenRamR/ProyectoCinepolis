/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.sql.Time;

/**
 *
 * @author crazy
 */
public class EntidadPelicula {
    int id;
    String titulo;
    String genero;
    String clasificacion;
    String sinopsis;
    Time duracion;
    String paisOrigen;
    String trailerLink;
    String imagenURL;
    
    public EntidadPelicula() {
    }
    
    // constructor con todos los atributos
    public EntidadPelicula(int id, String titulo, String genero, String clasificacion, String sinopsis, Time duracion, String paisOrigen, String trailerLink, String imagenURL) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.clasificacion = clasificacion;
        this.sinopsis = sinopsis;
        this.duracion = duracion;
        this.paisOrigen = paisOrigen;
        this.trailerLink = trailerLink;
        this.imagenURL = imagenURL;
    }

    // constructor sin ID
    public EntidadPelicula(String titulo, String genero, String clasificacion, String sinopsis, Time duracion, String paisOrigen, String trailerLink, String imagenURL) {
        this.titulo = titulo;
        this.genero = genero;
        this.clasificacion = clasificacion;
        this.sinopsis = sinopsis;
        this.duracion = duracion;
        this.paisOrigen = paisOrigen;
        this.trailerLink = trailerLink;
        this.imagenURL = imagenURL;
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

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    } 

    @Override
    public String toString() {
        return "EntidadPelicula{" + "id=" + id + ", titulo=" + titulo + ", genero=" + genero + ", clasificacion=" + clasificacion + ", sinopsis=" + sinopsis + ", duracion=" + duracion + ", paisOrigen=" + paisOrigen + ", trailerLink=" + trailerLink + ", imagenURL=" + imagenURL + '}';
    }
    
    
    
}
