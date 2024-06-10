/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.EntidadPelicula;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David Elier Campa Chaparro 245178
 */
public class PeliculaDAO implements IPeliculaDAO {
    
    private IConexionBD conexionBD;
    
    public PeliculaDAO(IConexionBD conexionBD){
        this.conexionBD = conexionBD;
    }
    
    @Override
    public void insertarPelicula(EntidadPelicula entidadPelicula, int idSucursal) throws PersistenciaException {
        Connection conexion = null;
        CallableStatement callableStatement = null;
        try {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);

            String sql = "{CALL InsertPeliculaConSucursal(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            callableStatement = conexion.prepareCall(sql);

            callableStatement.setString(1, entidadPelicula.getTitulo());
            callableStatement.setString(2, entidadPelicula.getGenero());
            callableStatement.setString(3, entidadPelicula.getClasificacion());
            callableStatement.setString(4, entidadPelicula.getSinopsis());
            callableStatement.setTime(5, entidadPelicula.getDuracion());
            callableStatement.setString(6, entidadPelicula.getPaisOrigen());
            callableStatement.setString(7, entidadPelicula.getTrailerLink());
            callableStatement.setBytes(8, entidadPelicula.getImagen());
            callableStatement.setInt(9, idSucursal); // idSucursal pasado como parámetro

            callableStatement.execute();

            // Confirmar la transacción
            conexion.commit();
        } catch (SQLException ex) {
            if (conexion != null) {
                try {
                    conexion.rollback();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al realizar el rollback");
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public void editarPelicula(EntidadPelicula entidadPelicula) throws PersistenciaException {
        Connection conexion = null;
        try {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);
            String codigoSQL = "UPDATE Pelicula SET titulo = ?, genero = ?, clasificacion = ?, sinopsis = ?, duracion = ?, paisOrigen = ?, trailerLink = ?, imagen = ? WHERE id = ?;";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            
            preparedStatement.setString(1, entidadPelicula.getTitulo());
            preparedStatement.setString(2, entidadPelicula.getGenero());
            preparedStatement.setString(3, entidadPelicula.getClasificacion());
            preparedStatement.setString(4, entidadPelicula.getSinopsis());
            preparedStatement.setTime(5, entidadPelicula.getDuracion());
            preparedStatement.setString(6, entidadPelicula.getPaisOrigen());
            preparedStatement.setString(7, entidadPelicula.getTrailerLink());
            preparedStatement.setBytes(8, entidadPelicula.getImagen());
            preparedStatement.setInt(9, entidadPelicula.getId());
            preparedStatement.executeUpdate();
            conexion.commit();
        } catch (SQLException ex) {
            if (conexion != null){
                try {
                    conexion.rollback();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al realizar el rollback");
            
        } finally {
            if (conexion != null){
                try {
                    conexion.close();
                } catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public void eliminarPelicula(EntidadPelicula entidadPelicula) throws PersistenciaException {
        Connection conexion = null;
        try {
            conexion = this.conexionBD.crearConexion();
            conexion.setAutoCommit(false);
            String codigoSQL = "UPDATE Pelicula SET eliminado = b'1' WHERE id = ?;";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, entidadPelicula.getId());
            preparedStatement.executeUpdate();
            conexion.commit();
        } catch (SQLException ex) {
            if (conexion != null){
                try {
                    conexion.rollback();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al realizar el rollback");
            
        } finally {
            if (conexion != null){
                try {
                    conexion.close();
                } catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public List<EntidadPelicula> consultarPeliculas(int limit, int offset) throws PersistenciaException {
        try {
            List<EntidadPelicula> sucursalLista = new ArrayList<>();
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT id, titulo, genero, clasificacion, sinopsis, duracion, paisOrigen, trailerLink, imagen, eliminado FROM Pelicula WHERE eliminado = b'0' LIMIT " +  limit + " OFFSET " + offset;
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            while (resultado.next()) {
                EntidadPelicula pelicula = new EntidadPelicula();
                pelicula.setId(resultado.getInt("id"));
                pelicula.setTitulo(resultado.getString("titulo"));
                pelicula.setGenero(resultado.getString("genero"));
                pelicula.setClasificacion(resultado.getString("clasificacion"));
                pelicula.setSinopsis(resultado.getString("sinopsis"));
                pelicula.setDuracion(resultado.getTime("duracion"));
                pelicula.setPaisOrigen(resultado.getString("paisOrigen"));
                pelicula.setTrailerLink(resultado.getString("trailerLink"));
                pelicula.setImagen(resultado.getBytes("imagen"));
                
                sucursalLista.add(pelicula);
                System.out.println(pelicula.toString());
            }
            conexion.close();
            return sucursalLista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PersistenciaException("Ocurrió un error");
        }
    }

    @Override
    public EntidadPelicula consultarPeliculaPorID(int id) throws PersistenciaException {
        Connection conexion = null;
        try {
            conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT id, titulo, genero, clasificacion, sinopsis, duracion, paisOrigen, trailerLink, imagen, eliminado FROM Pelicula WHERE id = ?;";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, id);
            ResultSet resultado = preparedStatement.executeQuery();
            
            if (resultado.next()) {
                EntidadPelicula pelicula = new EntidadPelicula();
                pelicula.setId(resultado.getInt("id"));
                pelicula.setTitulo(resultado.getString("titulo"));
                pelicula.setGenero(resultado.getString("genero"));
                pelicula.setClasificacion(resultado.getString("clasificacion"));
                pelicula.setSinopsis(resultado.getString("sinopsis"));
                pelicula.setDuracion(resultado.getTime("duracion"));
                pelicula.setPaisOrigen(resultado.getString("paisOrigen"));
                pelicula.setTrailerLink(resultado.getString("trailerLink"));
                pelicula.setImagen(resultado.getBytes("imagen"));
                
                System.out.println(pelicula.toString());
                return pelicula;
            } else {
                throw new PersistenciaException("No se encontró la sucursal con ID: " + id);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al buscar la sucursal");
        } finally {
            if (conexion != null){
                try {
                    conexion.close();
                } catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    
    @Override
    public List<EntidadPelicula> consultarPeliculasPorSucursal(int idSucursal, int limit, int offset) throws PersistenciaException {
        List<EntidadPelicula> sucursalLista = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultado = null;

        try {
            conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT p.id, p.titulo, p.genero, p.clasificacion, p.sinopsis, p.duracion, p.paisOrigen, p.trailerLink, p.imagen, p.eliminado " +
                               "FROM Pelicula p " +
                               "JOIN Sucursal_Tiene_Pelicula stp ON p.id = stp.idPelicula " +
                               "WHERE stp.idSucursal = ? AND p.eliminado = b'0' " +
                               "LIMIT ? OFFSET ?";

            preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setInt(1, idSucursal);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);

            resultado = preparedStatement.executeQuery();

            while (resultado.next()) {
                EntidadPelicula pelicula = new EntidadPelicula();
                pelicula.setId(resultado.getInt("id"));
                pelicula.setTitulo(resultado.getString("titulo"));
                pelicula.setGenero(resultado.getString("genero"));
                pelicula.setClasificacion(resultado.getString("clasificacion"));
                pelicula.setSinopsis(resultado.getString("sinopsis"));
                pelicula.setDuracion(resultado.getTime("duracion"));
                pelicula.setPaisOrigen(resultado.getString("paisOrigen"));
                pelicula.setTrailerLink(resultado.getString("trailerLink"));
                pelicula.setImagen(resultado.getBytes("imagen"));

                sucursalLista.add(pelicula);
                System.out.println(pelicula.toString());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new PersistenciaException("Ocurrió un error al consultar las películas por sucursal");
        } finally {
            if (resultado != null) {
                try {
                    resultado.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return sucursalLista;
    }

}
