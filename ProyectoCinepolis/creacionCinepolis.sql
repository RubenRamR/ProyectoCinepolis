create database cinepolis;
use cinepolis;


CREATE TABLE Ciudad(
	nombre varchar(20) primary key
);

CREATE TABLE Sucursal(
	id int auto_increment primary key,
    nombre varchar(35),
    coordenadaX int,
    coordenadaY int,
    nombreCiudad varchar(20),
    eliminado bit(1) not null default b'0',
    FOREIGN KEY (nombreCiudad) references Ciudad (nombre)
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
    imagen varchar(100),
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



