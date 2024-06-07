create database cinepolis;
use cinepolis;

INSERT INTO Sucursal(nombre, ciudad, coordenadaX, coordenadaY)
values ('Cinepolis bella vista', 'Obregon', 100, 200);

SELECT * FROM Sucursal;
DELETE FROM Sucursal WHERE ID = 2;
UPDATE Sucursal SET nombre = "prueba", ciudad = "prueba", coordenadaX = 400, coordenadaY = 400 WHERE id = 1;

SELECT id, nombre, ciudad, coordenadaX, coordenadaY FROM Sucursal LIMIT 5 OFFSET 0;


CREATE TABLE Sucursal(
	id int auto_increment primary key,
    nombre varchar(35),
    ciudad varchar(20),
    coordenadaX int,
    coordenadaY int
);

CREATE TABLE Pelicula(
	id int auto_increment primary key,
    titulo varchar(40),
    genero varchar(40),
    clasificacion varchar(4),
    sinopsis varchar(200),
    duracion time,
    paisOrigen varchar(20),
	trailerLink varchar(80)
);

CREATE TABLE Cliente(
	id int auto_increment primary key,
    nombre varchar(40),
    apellidos varchar(50),
    ciudad varchar(25),
    correo varchar(30),
    fechaNacimiento date,
	coordenadaX int,
    coordenadaY int
);

CREATE TABLE Sala(
	id int auto_increment primary key,
    nombre varchar(30),
    asientos int,
    asientosDisponibles int,
    idSucursal int,
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









