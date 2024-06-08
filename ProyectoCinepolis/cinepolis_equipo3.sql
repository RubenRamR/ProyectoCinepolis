create database cinepolis;
use cinepolis;

-- SECCIÓN DEL SCRIPT PARA PRUEBAS ----------------------------------------------------------------------------------

-- pruebas con la entidad Sucursal
INSERT INTO Sucursal(nombre, ciudad, coordenadaX, coordenadaY)
values ('Cinepolis bella vista', 'Obregon', 100, 200);

SELECT * FROM Sucursal;
DELETE FROM Sucursal WHERE ID = 2;
UPDATE Sucursal SET nombre = "prueba", ciudad = "prueba", coordenadaX = 400, coordenadaY = 400 WHERE id = 1;
SELECT id, nombre, ciudad, coordenadaX, coordenadaY FROM Sucursal LIMIT 5 OFFSET 0;

-- pruebas con la entidad Pelicula
INSERT INTO Pelicula (titulo, genero, clasificacion, sinopsis, duracion, paisOrigen, trailerLink, imagenURL)
values('tituloxd', 'generoxd', 'A', 'sinopsisxd', '02:15:00', 'paisxd', 'trailerLink', 'imagenURL');
UPDATE Pelicula SET titulo = 'hola', genero = 'hola', clasificacion = 'd', sinopsis = 'hola', duracion = '01:05:00', paisOrigen = 'hola', trailerLink = 'hola', imagenURL = 'hola' WHERE id = 1;
SELECT * FROM Pelicula;
SELECT id, titulo, genero, clasificacion, sinopsis, duracion, paisOrigen, trailerLink, imagenURL, eliminado FROM Pelicula LIMIT 1000 OFFSET 0;
SELECT id, titulo, genero, clasificacion, sinopsis, duracion, paisOrigen, trailerLink, imagenURL, eliminado FROM Pelicula WHERE id = 5;

-- pruebas cliente
INSERT INTO Cliente(nombre, apellidos, ciudad, correo, fechaNacimiento, coordenadaX, coordenadaY) values ('David Elier', 'Campa Chaparro', 'Ciudad obregon', 'davidelier@gmail.com', '2004-10-07', 100, 300);
SELECT * FROM Cliente;
SELECT id, nombre, apellidos, ciudad, correo, fechaNacimiento, coordenadaX, coordenadaY FROM Cliente LIMIT 100 OFFSET 0;
UPDATE Cliente SET nombre = 'asdasd', apellidos = 'asdasd', ciudad = 'asdasd', correo = 'asdasd', fechaNacimiento = '2000-10-10', coordenadaX = 1000, coordenadaY = 100 WHERE id = 2;

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
    imagenURL varchar(100),
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
    asientosDisponibles int,
    idSucursal int,
    eliminado bit(1) not null default b'0',
    foreign key (idSucursal) references Sucursal (id)
);

CREATE TABLE Funcion(
	id int auto_increment primary key,
    precio float,
    inicio datetime,
    fin datetime,
    tiempoLimpieza time,
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

-- STORED PROCEDURES -------------------------------------------------------------------------------------------------









