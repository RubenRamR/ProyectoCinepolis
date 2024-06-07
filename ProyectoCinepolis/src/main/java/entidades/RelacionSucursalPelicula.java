/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author crazy
 */
public class RelacionSucursalPelicula {
    int id;
    int idSucursal;
    int idPelicula;

    public RelacionSucursalPelicula() {
    }
    
    // constructor con id
    public RelacionSucursalPelicula(int id, int idSucursal, int idPelicula) {
        this.id = id;
        this.idSucursal = idSucursal;
        this.idPelicula = idPelicula;
    }
    
    // constructor sin id
    public RelacionSucursalPelicula(int idSucursal, int idPelicula) {
        this.idSucursal = idSucursal;
        this.idPelicula = idPelicula;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }
    
    
    
}
