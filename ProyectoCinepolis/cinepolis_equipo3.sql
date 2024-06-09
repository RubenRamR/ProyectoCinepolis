create database cinepolis;
use cinepolis;

drop database cinepolis;

select * from Cliente;
select * from Sucursal;
select * from Pelicula;
select * from Sala;




-- SECCIÓN DEL SCRIPT PARA PRUEBAS ----------------------------------------------------------------------------------

-- pruebas con la entidad Sucursal
INSERT INTO Sucursal(nombre, ciudad, coordenadaX, coordenadaY) values ('Cinepolis bella vista', 'Ciudad Obregon', 100, 200);
INSERT INTO Sucursal(nombre, ciudad, coordenadaX, coordenadaY) values ('Cinepolis de hermosillo', 'Hermosillo', -1555, 1000);
INSERT INTO Sucursal(nombre, ciudad, coordenadaX, coordenadaY) values ('Cinepolis de navojoa', 'Navojoa', -555, -400);
INSERT INTO Sucursal(nombre, ciudad, coordenadaX, coordenadaY) values ('Cinepolis de guaymas', 'Guaymas', 1044, 545);


-- pruebas con la entidad Pelicula
INSERT INTO Pelicula (titulo, genero, clasificacion, sinopsis, duracion, paisOrigen, trailerLink, imagenURL) values('Rapido y furioso', 'Accion', 'AB', 'Sinopsis', '01:50:00', 'USA', 'trailerLink', 'imagenURL');
INSERT INTO Pelicula (titulo, genero, clasificacion, sinopsis, duracion, paisOrigen, trailerLink, imagenURL) values('50 sombras', 'Drama', 'C', 'Sinopsis', '02:05:00', 'Suiza', 'trailerLink', 'imagenURL');
INSERT INTO Pelicula (titulo, genero, clasificacion, sinopsis, duracion, paisOrigen, trailerLink, imagenURL) values('Avengers endgame', 'Accion', 'B', 'Sinopsis', '04:54:00', 'Noruega', 'trailerLink', 'imagenURL');
INSERT INTO Pelicula (titulo, genero, clasificacion, sinopsis, duracion, paisOrigen, trailerLink, imagenURL) values('Avengers infinity', 'Accion', 'B', 'Sinopsis', '01:15:00', 'Mexico', 'trailerLink', 'imagenURL');
INSERT INTO Pelicula (titulo, genero, clasificacion, sinopsis, duracion, paisOrigen, trailerLink, imagenURL) values('Spiderman', 'Ficcion', 'A', 'Sinopsis', '02:10:00', 'Canada', 'trailerLink', 'imagenURL');
INSERT INTO Pelicula (titulo, genero, clasificacion, sinopsis, duracion, paisOrigen, trailerLink, imagenURL) values('Xmen', 'Ficcion', 'B', 'Sinopsis', '02:50:00', 'Italia', 'trailerLink', 'imagenURL');
INSERT INTO Pelicula (titulo, genero, clasificacion, sinopsis, duracion, paisOrigen, trailerLink, imagenURL) values('Titanic', 'Romance', 'B15', 'Sinopsis', '03:00:10', 'Inglaterra', 'trailerLink', 'imagenURL');
INSERT INTO Pelicula (titulo, genero, clasificacion, sinopsis, duracion, paisOrigen, trailerLink, imagenURL) values('El conjuro', 'Terror', 'C', 'Sinopsis', '03:40:00', 'Suiza', 'trailerLink', 'imagenURL');


-- pruebas cliente
INSERT INTO Cliente(nombre, apellidos, ciudad, correo, fechaNacimiento, coordenadaX, coordenadaY) values ('David Elier', 'Campa Chaparro', 'Ciudad Obregon', 'davidelier@gmail.com', '2004-10-07', 50, 200);
INSERT INTO Cliente(nombre, apellidos, ciudad, correo, fechaNacimiento, coordenadaX, coordenadaY) values ('Carlos damian', 'garcia', 'Navojoa', 'carlos@gmail.com', '2004-08-07', -500, -300);
INSERT INTO Cliente(nombre, apellidos, ciudad, correo, fechaNacimiento, coordenadaX, coordenadaY) values ('Ruben', 'ramirez', 'Hermosillo', 'ruben@gmail.com', '2004-01-07', 1000, 1000);
INSERT INTO Cliente(nombre, apellidos, ciudad, correo, fechaNacimiento, coordenadaX, coordenadaY) values ('Edgar', 'Solano', 'Guaymas', 'edgar@gmail.com', '2000-04-01', 200, 300);

-- pruebas sala
INSERT INTO Sala (nombre, asientos, idSucursal) values ('Sala Obregonense', 50, 1);
INSERT INTO Sala (nombre, asientos, idSucursal) values ('Sala Obesa', 45, 1);
INSERT INTO Sala (nombre, asientos, idSucursal) values ('Sala Ohio', 30, 1);
INSERT INTO Sala (nombre, asientos, idSucursal) values ('Sala Ontas', 100, 1);

INSERT INTO Sala (nombre, asientos, idSucursal) values ('Sala Hermosillense', 50, 2);
INSERT INTO Sala (nombre, asientos, idSucursal) values ('Sala Helada', 45, 2);
INSERT INTO Sala (nombre, asientos, idSucursal) values ('Sala Harry', 53, 2);
INSERT INTO Sala (nombre, asientos, idSucursal) values ('Sala Hanna', 42, 2);

INSERT INTO Sala (nombre, asientos, idSucursal) values ('Sala Navojoense', 50, 3);
INSERT INTO Sala (nombre, asientos, idSucursal) values ('Sala Navarro', 35, 3);
INSERT INTO Sala (nombre, asientos, idSucursal) values ('Sala Nevarez', 113, 3);
INSERT INTO Sala (nombre, asientos, idSucursal) values ('Sala Nevada', 10, 3);

INSERT INTO Sala (nombre, asientos, idSucursal) values ('Sala Guaymaense', 52, 4);
INSERT INTO Sala (nombre, asientos, idSucursal) values ('Sala Guayaba', 90, 4);
INSERT INTO Sala (nombre, asientos, idSucursal) values ('Sala God', 53, 4);
INSERT INTO Sala (nombre, asientos, idSucursal) values ('Sala Grande', 80, 4);
	
-- pruebas funcion
SET @idPelicula = 1;
SET @idSala = 1;
SET @precio = 100;
SET @dia = '2025-01-01';
SET @inicio = '12:00:00';
SET @duracion = (SELECT duracion FROM Pelicula WHERE id = @idPelicula);
SET @tiempoLimpieza = '00:30:00';
SET @fin = ADDTIME(ADDTIME(@inicio, @duracion), @tiempoLimpieza);
SET @asientosDisponibles = (SELECT asientos FROM Sala WHERE id = @idSala);
INSERT INTO Funcion (precio, dia, inicio, fin, tiempoLimpieza, asientosDisponibles, idPelicula, idSala) 
VALUES (@precio, @dia, @inicio, @fin, @tiempoLimpieza, @asientosDisponibles, @idPelicula, @idSala);

SELECT * FROM funcion;
delete from funcion;


select * from pelicula;

select * from Sucursal_Tiene_Pelicula;

select * from Sucursal_Tiene_Pelicula;
select p.titulo, s.nombre from Sucursal_Tiene_Pelicula as sp inner join Pelicula as p inner join Sucursal as s
on s.id = sp.idSucursal and p.id = sp.idPelicula
WHERE s.id = 1;



SELECT id, titulo, genero, clasificacion, sinopsis, duracion, paisOrigen, trailerLink, imagenURL, eliminado FROM Pelicula WHERE eliminado = b'0' LIMIT  1000 offset 0;

-- pruebas Funcion




-- FIN SECCIÓN PRUEBAS--------------------------------------------------------------------------------------------------

CREATE TABLE Sucursal(
	id int auto_increment primary key,
    nombre varchar(35),
    ciudad varchar(20),
    coordenadaX int,
    coordenadaY int,
    eliminado bit(1) not null default b'0'
);


CREATE TABLE Pelicula(
	id int auto_increment primary key,
    titulo varchar(50),
    genero varchar(50),
    clasificacion varchar(5),
    sinopsis varchar(200),
    duracion time,
    paisOrigen varchar(30),
	trailerLink varchar(80),
    imagen blob,
    eliminado bit(1) not null default b'0'
);



CREATE TABLE Cliente(
	id int auto_increment primary key,
    nombre varchar(50),
    apellidos varchar(50),
    ciudad varchar(40),
    correo varchar(50),
    fechaNacimiento date,
	coordenadaX int,
    coordenadaY int,
	eliminado bit(1) not null default b'0'
);

CREATE TABLE Sala(
	id int auto_increment primary key,
    nombre varchar(50),
    asientos int,
    idSucursal int not null,
    eliminado bit(1) not null default b'0',
    foreign key (idSucursal) references Sucursal (id)
);


CREATE TABLE Funcion(
	id int auto_increment primary key,
    precio float,
    dia date,
    inicio time,
    fin time,
    tiempoLimpieza time,
	asientosDisponibles int,
    idPelicula int,
    idSala int,
    eliminado bit(1) not null default b'0',
    foreign key (idPelicula) references Pelicula (id),
    foreign key (idSala) references Sala (id)
);

CREATE TABLE Sucursal_Tiene_Pelicula(
	id int auto_increment primary key,
	idSucursal int,
    idPelicula int,
    foreign key (idSucursal) references Sucursal(id),
	foreign key (idPelicula) references Pelicula(id)
);

CREATE TABLE Cliente_Compra_Funcion(
	id int auto_increment primary key,
    idCliente int,
    idFuncion int,
    foreign key (idCliente) references Cliente (id),
	foreign key (idFuncion) references Funcion (id)
);
-- STORED PROCEDURES -------------------------------------------------------------------------------------------------
-- STORED PROCEDURES -------------------------------------------------------------------------------------------------
-- STORED PROCEDURES -------------------------------------------------------------------------------------------------
-- STORED PROCEDURES -------------------------------------------------------------------------------------------------
-- STORED PROCEDURES -------------------------------------------------------------------------------------------------
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
    IN p_imagenURL VARCHAR(100),
    IN p_idSucursal INT
)
BEGIN
    -- Insertar la película
    INSERT INTO Pelicula (titulo, genero, clasificacion, sinopsis, duracion, paisOrigen, trailerLink, imagenURL)
    VALUES (p_titulo, p_genero, p_clasificacion, p_sinopsis, p_duracion, p_paisOrigen, p_trailerLink, p_imagenURL);

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







