package negocio;

import dtos.PeliculaDTO;
import entidades.EntidadPelicula;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.IPeliculaDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author rramirez
 */
public class PeliculaNegocio implements IPeliculaNegocio {

    private IPeliculaDAO peliculaDAO;
    private static final Logger LOGGER = Logger.getLogger(PeliculaNegocio.class.getName());

    public PeliculaNegocio(IPeliculaDAO peliculaDAO) {
        this.peliculaDAO = peliculaDAO;
    }

    private PeliculaDTO convertirPeliculaDTO(EntidadPelicula pelicula) throws NegocioException {
        if (pelicula == null)
        {
            throw new NegocioException("La pelicula es nula");
        }

        PeliculaDTO dto = new PeliculaDTO();
        dto.setId(pelicula.getId());
        dto.setTitulo(pelicula.getTitulo());
        dto.setGenero(pelicula.getGenero());
        dto.setClasificacion(pelicula.getClasificacion());
        dto.setSinopsis(pelicula.getSinopsis());
        dto.setDuracion(pelicula.getDuracion());
        dto.setPaisOrigen(pelicula.getPaisOrigen());
        dto.setTrailerLink(pelicula.getTrailerLink());
        dto.setImagenURL(pelicula.getImagenURL());

        return dto;
    }

    private EntidadPelicula convertirADTOEntidad(PeliculaDTO peliculaDTO) throws NegocioException {
        if (peliculaDTO == null)
        {
            throw new NegocioException("La pelicula DTO es nula");
        }

        if (peliculaDTO.getTitulo().length() > 50)
        {
            throw new NegocioException("El título de la película no puede exceder los 50 caracteres");
        }

        EntidadPelicula pelicula = new EntidadPelicula();
        pelicula.setId(peliculaDTO.getId());
        pelicula.setTitulo(peliculaDTO.getTitulo());
        pelicula.setGenero(peliculaDTO.getGenero());
        pelicula.setClasificacion(peliculaDTO.getClasificacion());
        pelicula.setSinopsis(peliculaDTO.getSinopsis());
        pelicula.setDuracion(peliculaDTO.getDuracion());
        pelicula.setPaisOrigen(peliculaDTO.getPaisOrigen());
        pelicula.setTrailerLink(peliculaDTO.getTrailerLink());
        pelicula.setImagenURL(peliculaDTO.getImagenURL());

        return pelicula;
    }

    @Override
    public void insertarPelicula(PeliculaDTO peliculaDTO, int idSucursal) throws NegocioException {
        try
        {
            EntidadPelicula pelicula = convertirADTOEntidad(peliculaDTO);

            if (pelicula.getTitulo().length() > 50)
            {
                throw new NegocioException("El título de la película no puede exceder los 50 caracteres");
            }
            if (pelicula.getGenero().length() > 50)
            {
                throw new NegocioException("El género de la película no puede exceder los 50 caracteres");
            }
            if (pelicula.getClasificacion().length() > 5)
            {
                throw new NegocioException("La clasificación de la película no puede exceder los 5 caracteres");
            }
            if (pelicula.getSinopsis().length() > 200)
            {
                throw new NegocioException("La sinopsis de la película no puede exceder los 200 caracteres");
            }
            if (pelicula.getPaisOrigen().length() > 30)
            {
                throw new NegocioException("El país de origen de la película no puede exceder los 30 caracteres");
            }
            if (pelicula.getTrailerLink().length() > 80)
            {
                throw new NegocioException("El enlace del tráiler de la película no puede exceder los 80 caracteres");
            }

            this.peliculaDAO.insertarPelicula(pelicula, idSucursal);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al insertar pelicula", ex);
            throw new NegocioException("Error al insertar pelicula: " + ex.getMessage());
        }
    }

    @Override
    public void editarPelicula(PeliculaDTO peliculaDTO) throws NegocioException {
        try
        {
            EntidadPelicula pelicula = convertirADTOEntidad(peliculaDTO);

            if (pelicula.getTitulo().length() > 50)
            {
                throw new NegocioException("El título de la película no puede exceder los 50 caracteres");
            }
            if (pelicula.getGenero().length() > 50)
            {
                throw new NegocioException("El género de la película no puede exceder los 50 caracteres");
            }
            if (pelicula.getClasificacion().length() > 5)
            {
                throw new NegocioException("La clasificación de la película no puede exceder los 5 caracteres");
            }
            if (pelicula.getSinopsis().length() > 200)
            {
                throw new NegocioException("La sinopsis de la película no puede exceder los 200 caracteres");
            }
            if (pelicula.getPaisOrigen().length() > 30)
            {
                throw new NegocioException("El país de origen de la película no puede exceder los 30 caracteres");
            }
            if (pelicula.getTrailerLink().length() > 80)
            {
                throw new NegocioException("El enlace del tráiler de la película no puede exceder los 80 caracteres");
            }

            this.peliculaDAO.editarPelicula(pelicula);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al editar pelicula", ex);
            throw new NegocioException("Error al editar pelicula: " + ex.getMessage());
        }
    }

    @Override
    public void eliminarPelicula(PeliculaDTO peliculaDTO) throws NegocioException {
        try
        {
            EntidadPelicula pelicula = convertirADTOEntidad(peliculaDTO);
            
            if (pelicula.getTitulo().length() > 50)
            {
                throw new NegocioException("El título de la película no puede exceder los 50 caracteres");
            }
            if (pelicula.getGenero().length() > 50)
            {
                throw new NegocioException("El género de la película no puede exceder los 50 caracteres");
            }
            if (pelicula.getClasificacion().length() > 5)
            {
                throw new NegocioException("La clasificación de la película no puede exceder los 5 caracteres");
            }
            if (pelicula.getSinopsis().length() > 200)
            {
                throw new NegocioException("La sinopsis de la película no puede exceder los 200 caracteres");
            }
            if (pelicula.getPaisOrigen().length() > 30)
            {
                throw new NegocioException("El país de origen de la película no puede exceder los 30 caracteres");
            }
            if (pelicula.getTrailerLink().length() > 80)
            {
                throw new NegocioException("El enlace del tráiler de la película no puede exceder los 80 caracteres");
            }
            
            this.peliculaDAO.eliminarPelicula(pelicula);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al eliminar pelicula", ex);
            throw new NegocioException("Error al eliminar pelicula: " + ex.getMessage());
        }
    }

    @Override
    public List<PeliculaDTO> consultarPeliculas(int limit, int offset) throws NegocioException {
        try
        {
            List<EntidadPelicula> peliculas = this.peliculaDAO.consultarPeliculas(limit, offset);
            List<PeliculaDTO> peliculasDTO = new ArrayList<>();
            for (EntidadPelicula pelicula : peliculas)
            {
                peliculasDTO.add(convertirPeliculaDTO(pelicula));
            }
            return peliculasDTO;
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al consultar peliculas", ex);
            throw new NegocioException("Error al consultar peliculas: " + ex.getMessage());
        }
    }

    @Override
    public PeliculaDTO consultarPeliculaPorID(int id) throws NegocioException {
        try
        {
            EntidadPelicula pelicula = this.peliculaDAO.consultarPeliculaPorID(id);
            return convertirPeliculaDTO(pelicula);
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al consultar pelicula por ID", ex);
            throw new NegocioException("Error al consultar pelicula por ID: " + ex.getMessage());
        }
    }

    @Override
    public List<PeliculaDTO> consultarPeliculasPorSucursal(int idSucursal, int limit, int offset) throws NegocioException {
        try
        {
            List<EntidadPelicula> peliculas = this.peliculaDAO.consultarPeliculasPorSucursal(idSucursal, limit, offset);
            List<PeliculaDTO> peliculasDTO = new ArrayList<>();
            for (EntidadPelicula pelicula : peliculas)
            {
                peliculasDTO.add(convertirPeliculaDTO(pelicula));
            }
            return peliculasDTO;
        } catch (PersistenciaException ex)
        {
            LOGGER.log(Level.SEVERE, "Error al consultar peliculas por sucursal", ex);
            throw new NegocioException("Error al consultar peliculas por sucursal: " + ex.getMessage());
        }
    }
}
