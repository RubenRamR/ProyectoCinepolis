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
    contrasena varchar(50),
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

-- SP para insertar una pelicula en una sucursal y que tome nuevo ID
DELIMITER //
CREATE PROCEDURE InsertPeliculaConSucursal(
    IN p_titulo VARCHAR(50),
    IN p_genero VARCHAR(50),
    IN p_clasificacion VARCHAR(5),
    IN p_sinopsis VARCHAR(200),
    IN p_duracion TIME,
    IN p_paisOrigen VARCHAR(30),
    IN p_trailerLink VARCHAR(80),
    IN p_imagen blob,
    IN p_idSucursal INT
)
BEGIN
    INSERT INTO Pelicula (titulo, genero, clasificacion, sinopsis, duracion, paisOrigen, trailerLink, imagen)
    VALUES (p_titulo, p_genero, p_clasificacion, p_sinopsis, p_duracion, p_paisOrigen, p_trailerLink, p_imagen);
    SET @newPeliculaID = LAST_INSERT_ID();
    INSERT INTO Sucursal_Tiene_Pelicula (idSucursal, idPelicula)
    VALUES (p_idSucursal, @newPeliculaID);
END//
DELIMITER ;



-- sp para insertar la funcion y que se calcula el tiempo fin con el tiempo de limpieza y la duracion de la pelicula
DELIMITER //
CREATE PROCEDURE InsertarFuncion(
    IN p_idPelicula INT,
    IN p_idSala INT,
    IN p_precio FLOAT,
    IN p_dia DATE,
    IN p_inicio TIME
)
BEGIN
    DECLARE p_duracion TIME;
    DECLARE p_tiempoLimpieza TIME DEFAULT '00:30:00';
    DECLARE p_fin TIME;
    DECLARE p_asientosDisponibles INT;
    SET p_duracion = (SELECT duracion FROM Pelicula WHERE id = p_idPelicula);
    SET p_fin = ADDTIME(ADDTIME(p_inicio, p_duracion), p_tiempoLimpieza);
    SET p_asientosDisponibles = (SELECT asientos FROM Sala WHERE id = p_idSala);
    INSERT INTO Funcion (precio, dia, inicio, fin, tiempoLimpieza, asientosDisponibles, idPelicula, idSala)
    VALUES (p_precio, p_dia, p_inicio, p_fin, p_tiempoLimpieza, p_asientosDisponibles, p_idPelicula, p_idSala);
END//

DELIMITER ;


-- sp para editar una funcion y que se actualice automaticamente el tiempo de fin
DELIMITER //
CREATE PROCEDURE EditarFuncion(
    IN p_idFuncion INT,
    IN p_nuevoInicio TIME,
    IN p_nuevoPrecio FLOAT,
    IN p_nuevoDia DATE
)
BEGIN
    DECLARE p_idPelicula INT;
    DECLARE p_duracion TIME;
    DECLARE p_tiempoLimpieza TIME;
    DECLARE p_nuevoFin TIME;
    SELECT idPelicula INTO p_idPelicula FROM Funcion WHERE id = p_idFuncion;
    SELECT duracion INTO p_duracion FROM Pelicula WHERE id = p_idPelicula;
    SELECT tiempoLimpieza INTO p_tiempoLimpieza FROM Funcion WHERE id = p_idFuncion;
    SET p_nuevoFin = ADDTIME(ADDTIME(p_nuevoInicio, p_duracion), p_tiempoLimpieza);
    UPDATE Funcion
    SET inicio = p_nuevoInicio,
        precio = p_nuevoPrecio,
        dia = p_nuevoDia,
        fin = p_nuevoFin
    WHERE id = p_idFuncion;
END//
DELIMITER ;

-- SP par poder consutlar las funciones de la misma pelicula que estén en la misma sucursal 
DELIMITER //
CREATE PROCEDURE ConsultarFuncionesPorSucursalYPelicula(
    IN p_idSucursal INT,
    IN p_idPelicula INT,
    IN p_limit INT,
    IN p_offset INT
)
BEGIN
    SELECT f.id, f.precio, f.dia, f.inicio, f.fin, f.tiempoLimpieza, f.asientosDisponibles, f.idPelicula, f.idSala
    FROM Funcion f
    JOIN Sala s ON f.idSala = s.id
    WHERE s.idSucursal = p_idSucursal AND f.idPelicula = p_idPelicula AND f.eliminado = b'0'
    LIMIT p_limit OFFSET p_offset;
END//
DELIMITER ;

-- SP para insertar 5 funciones por dia por cada sala durante la siguiente semana
DELIMITER //
CREATE PROCEDURE InsertarFuncionesSemana()
BEGIN
	DECLARE precio FLOAT;
    DECLARE currentt_date DATE;
    DECLARE end_date DATE;
    DECLARE sala_id INT;
    DECLARE pelicula_id INT;
    DECLARE hora_inicio TIME;
    DECLARE i INT;
    DECLARE k INT;
    DECLARE random_index INT;
    DECLARE pelicula_count INT;
    SET currentt_date = CURDATE();
    SET end_date = DATE_ADD(currentt_date, INTERVAL 7 DAY);
    SET pelicula_count = (SELECT COUNT(*) FROM Pelicula);
    -- Ciclo a través de cada sala
    SET i = 1;
    WHILE i <= (SELECT COUNT(*) FROM Sala) DO
        SET sala_id = i;
        -- Ciclo a través de cada día de la semana
        SET currentt_date = CURDATE();
        WHILE currentt_date < end_date DO
            -- Insertar 5 funciones por día para cada sala
            SET k = 1;
            WHILE k <= 5 DO
                -- Generar hora de inicio aleatoria entre 8:00 y 18:00
                SET hora_inicio = SEC_TO_TIME(28800 + FLOOR(RAND() * 36000)); -- 28800s = 8hrs, 36000s = 10hrs
                -- Seleccionar una película aleatoria
                SET random_index = FLOOR(RAND() * pelicula_count);
                SET pelicula_id = (SELECT id FROM Pelicula LIMIT random_index, 1);
                -- Generar precio aleatorio entre 100 y 400
                SET precio = 100 + FLOOR(RAND() * 301); -- 301 es el rango de números entre 100 y 400
                -- Insertar la función
                CALL InsertarFuncion(pelicula_id, sala_id, precio, currentt_date, hora_inicio);
                SET k = k + 1;
            END WHILE;
            -- Incrementar fecha al siguiente día
            SET currentt_date = DATE_ADD(currentt_date, INTERVAL 1 DAY);
        END WHILE;
        SET i = i + 1;
    END WHILE;
END //
DELIMITER ;

select * from pelicula;


-- sp restar num asiento cuando se hace una venta
DELIMITER //

CREATE PROCEDURE InsertarVenta(
    IN p_idCliente INT,
    IN p_idFuncion INT
)
BEGIN
    -- Insertar la venta
    INSERT INTO Cliente_Compra_Funcion (idCliente, idFuncion) VALUES (p_idCliente, p_idFuncion);

    -- Actualizar el número de asientos disponibles en la función
    UPDATE Funcion
    SET asientosDisponibles = asientosDisponibles - 1
    WHERE id = p_idFuncion;
END//

DELIMITER ;

DELIMITER // 
CREATE PROCEDURE CalcularGananciasPorSucursal(
    IN p_idSucursal INT
)
BEGIN
    DECLARE totalGanancias FLOAT;
    SELECT SUM(f.precio) INTO totalGanancias
    FROM Funcion f
    JOIN Sucursal_Tiene_Pelicula stp ON f.idPelicula = stp.idPelicula
    WHERE stp.idSucursal = p_idSucursal;

    SELECT totalGanancias AS GananciasTotales;
END//
DELIMITER ;




