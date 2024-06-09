drop database cinepolis;






-- STORED PROCEDURES -------------------------------------------------------------------------------------------------
-- STORED PROCEDURES -------------------------------------------------------------------------------------------------
DELIMITER //

CREATE PROCEDURE InsertPeliculaConSucursal(
    IN p_titulo VARCHAR(50),
    IN p_genero VARCHAR(50),
    IN p_clasificacion VARCHAR(5),
    IN p_sinopsis VARCHAR(200),
    IN p_duracion TIME,
    IN p_paisOrigen VARCHAR(30),
    IN p_trailerLink VARCHAR(80),
    IN p_imagen VARCHAR(100),
    IN p_idSucursal INT
)
BEGIN
    -- Insertar la película
    INSERT INTO Pelicula (titulo, genero, clasificacion, sinopsis, duracion, paisOrigen, trailerLink, imagen)
    VALUES (p_titulo, p_genero, p_clasificacion, p_sinopsis, p_duracion, p_paisOrigen, p_trailerLink, p_imagen);

    -- Obtener el ID de la película recién insertada
    SET @newPeliculaID = LAST_INSERT_ID();

    -- Insertar en Sucursal_Tiene_Pelicula
    INSERT INTO Sucursal_Tiene_Pelicula (idSucursal, idPelicula)
    VALUES (p_idSucursal, @newPeliculaID);
END//

DELIMITER ;


-- TRIGGERS ------------------------------------------------------------------------------------------------------------
-- TRIGGERS ------------------------------------------------------------------------------------------------------------
-- TRIGGERS ------------------------------------------------------------------------------------------------------------
-- TRIGGERS ------------------------------------------------------------------------------------------------------------
-- TRIGGERS ------------------------------------------------------------------------------------------------------------
-- TRIGGERS ------------------------------------------------------------------------------------------------------------
-- TRIGGERS ------------------------------------------------------------------------------------------------------------
-- TRIGGERS ------------------------------------------------------------------------------------------------------------
CREATE TABLE historiales(
	idHistorial int auto_increment primary key,
    FechaHoraRegistro datetime ,
    nombre varchar(100)
);

DELIMITER //


CREATE TRIGGER despues_insertar_sucursal
AFTER INSERT ON Sucursal
FOR EACH ROW

BEGIN
	INSERT INTO historiales (FechaHoraRegistro, nombre) VALUE (now(), CONCAT('Se insertó un alumno ', new.id));
END//

DELIMITER //

INSERT INTO alumnos(nombres, apellidoPaterno, apellidoMaterno) values ('trigger', 'trigger', 'trigger');







