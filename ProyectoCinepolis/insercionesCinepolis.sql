use cinepolis;

INSERT INTO Ciudad (nombre) values ('Obregon'),
									('Hermosillo'),
									('Navojoa'),
									('Mazatlan'),
									('Culiacan');

INSERT INTO Sucursal(nombre, nombreCiudad, coordenadaX, coordenadaY) values ('Cinepolis bella vista', 'Obregon', 100, 200);
INSERT INTO Sucursal(nombre, nombreCiudad, coordenadaX, coordenadaY) values ('Cinepolis de hermosillo', 'Hermosillo', -1555, 1000);
INSERT INTO Sucursal(nombre, nombreCiudad, coordenadaX, coordenadaY) values ('Cinepolis de navojoa', 'Navojoa', -555, -400);
INSERT INTO Sucursal(nombre, nombreCiudad, coordenadaX, coordenadaY) values ('Cinepolis de culiacan', 'Culiacan', 1044, 545);
INSERT INTO Sucursal(nombre, nombreCiudad, coordenadaX, coordenadaY) values ('Cinepolis de mazatlan', 'Mazatlan', 3000, -3000);
select * from sucursal;

-- pruebas con la entidad Pelicula
-- Sucursal 1: Cinepolis bella vista
CALL InsertPeliculaConSucursal('Inception', 'Sci-Fi', 'B', 'A thief who steals corporate secrets through the use of dream-sharing technology.', '02:28:00', 'USA', 'link1', 'imagenURL', 1);
CALL InsertPeliculaConSucursal('The Dark Knight', 'Action', 'B15', 'When the menace known as the Joker emerges from his mysterious past.', '02:32:00', 'USA', 'link2', 'imagen2', 1);
CALL InsertPeliculaConSucursal('Interstellar', 'Adventure', 'B', 'A team of explorers travel through a wormhole in space.', '02:49:00', 'USA', 'link3', 'imagen3', 1);
CALL InsertPeliculaConSucursal('Gladiator', 'Action', 'B15', 'A former Roman General sets out to exact vengeance.', '02:35:00', 'USA', 'link4', 'imagen4', 1);
CALL InsertPeliculaConSucursal('Jurassic Park', 'Adventure', 'A', 'During a preview tour, a theme park suffers a major power breakdown.', '02:07:00', 'USA', 'link5', 'imagen5', 1);
CALL InsertPeliculaConSucursal('Avatar', 'Fantasy', 'A', 'A paraplegic Marine dispatched to the moon Pandora.', '02:42:00', 'USA', 'link6', 'imagen6', 1);
CALL InsertPeliculaConSucursal('The Matrix', 'Sci-Fi', 'B15', 'A computer hacker learns about the true nature of reality.', '02:16:00', 'USA', 'link7', 'imagen7', 1);
CALL InsertPeliculaConSucursal('Titanic', 'Romance', 'B', 'A seventeen-year-old aristocrat falls in love with a kind but poor artist.', '03:14:00', 'USA', 'link8', 'imagen8', 1);
CALL InsertPeliculaConSucursal('Pulp Fiction', 'Crime', 'C', 'The lives of two mob hitmen, a boxer, a gangster and his wife.', '02:34:00', 'USA', 'link9', 'imagen9', 1);
CALL InsertPeliculaConSucursal('Forrest Gump', 'Drama', 'B', 'The presidencies of Kennedy and Johnson, Vietnam, Watergate, and other history unfold.', '02:22:00', 'USA', 'link10', 'imagen10', 1);

-- Sucursal 2: Cinepolis de hermosillo
CALL InsertPeliculaConSucursal('Shrek', 'Animation', 'A', 'A mean lord exiles fairy tale creatures to the swamp of a grumpy ogre.', '01:30:00', 'USA', 'link11', 'imagen11', 2);
CALL InsertPeliculaConSucursal('Toy Story', 'Animation', 'A', 'A cowboy doll is profoundly threatened and jealous when a new spaceman figure supplants him.', '01:21:00', 'USA', 'link12', 'imagen12', 2);
CALL InsertPeliculaConSucursal('Finding Nemo', 'Animation', 'A', 'After his son is captured in the Great Barrier Reef and taken to Sydney.', '01:40:00', 'USA', 'link13', 'imagen13', 2);
CALL InsertPeliculaConSucursal('Monsters, Inc.', 'Animation', 'A', 'In order to power the city, monsters have to scare children.', '01:32:00', 'USA', 'link14', 'imagen14', 2);
CALL InsertPeliculaConSucursal('The Incredibles', 'Animation', 'A', 'A family of undercover superheroes, while trying to live the quiet suburban life.', '01:55:00', 'USA', 'link15', 'imagen15', 2);
CALL InsertPeliculaConSucursal('Up', 'Animation', 'A', '78-year-old Carl Fredricksen travels to Paradise Falls in his house.', '01:36:00', 'USA', 'link16', 'imagen16', 2);
CALL InsertPeliculaConSucursal('Coco', 'Animation', 'A', 'Aspiring musician Miguel, confronted with his family\'s ancestral ban on music.', '01:45:00', 'USA', 'link17', 'imagen17', 2);
CALL InsertPeliculaConSucursal('Frozen', 'Animation', 'A', 'When the newly crowned Queen Elsa accidentally uses her power to turn things into ice.', '01:42:00', 'USA', 'link18', 'imagen18', 2);
CALL InsertPeliculaConSucursal('Zootopia', 'Animation', 'A', 'In a city of anthropomorphic animals, a rookie bunny cop and a cynical con artist fox.', '01:48:00', 'USA', 'link19', 'imagen19', 2);
CALL InsertPeliculaConSucursal('Inside Out', 'Animation', 'A', 'After young Riley is uprooted from her Midwest life and moved to San Francisco.', '01:35:00', 'USA', 'link20', 'imagen20', 2);

-- Sucursal 3: Cinepolis de navojoa
CALL InsertPeliculaConSucursal('Star Wars: A New Hope', 'Sci-Fi', 'B', 'Luke Skywalker joins forces with a Jedi Knight, a cocky pilot.', '02:01:00', 'USA', 'link21', 'imagen21', 3);
CALL InsertPeliculaConSucursal('The Empire Strikes Back', 'Sci-Fi', 'B', 'After the Rebels are brutally overpowered by the Empire on the ice planet Hoth.', '02:04:00', 'USA', 'link22', 'imagen22', 3);
CALL InsertPeliculaConSucursal('Return of the Jedi', 'Sci-Fi', 'B', 'After a daring mission to rescue Han Solo from Jabba the Hutt.', '02:11:00', 'USA', 'link23', 'imagen23', 3);
CALL InsertPeliculaConSucursal('The Lord of the Rings: The Fellowship of the Ring', 'Fantasy', 'B', 'A meek Hobbit from the Shire and eight companions set out on a journey.', '02:58:00', 'USA', 'link24', 'imagen24', 3);
CALL InsertPeliculaConSucursal('The Lord of the Rings: The Two Towers', 'Fantasy', 'B', 'While Frodo and Sam edge closer to Mordor with the help of the shifty Gollum.', '02:59:00', 'USA', 'link25', 'imagen25', 3);
CALL InsertPeliculaConSucursal('The Lord of the Rings: The Return of the King', 'Fantasy', 'B', 'Gandalf and Aragorn lead the World of Men against Sauron\'s army.', '03:21:00', 'USA', 'link26', 'imagen26', 3);
CALL InsertPeliculaConSucursal('Harry Potter and the Sorcerer\'s Stone', 'Fantasy', 'A', 'An orphaned boy enrolls in a school of wizardry.', '02:32:00', 'USA', 'link27', 'imagen27', 3);
CALL InsertPeliculaConSucursal('Harry Potter and the Chamber of Secrets', 'Fantasy', 'A', 'An ancient prophecy seems to be coming true when a mysterious presence begins stalking.', '02:41:00', 'USA', 'link28', 'imagen28', 3);
CALL InsertPeliculaConSucursal('Harry Potter and the Prisoner of Azkaban', 'Fantasy', 'A', 'Harry Potter, Ron and Hermione return to Hogwarts School of Witchcraft and Wizardry.', '02:22:00', 'USA', 'link29', 'imagen29', 3);
CALL InsertPeliculaConSucursal('Harry Potter and the Goblet of Fire', 'Fantasy', 'A', 'Harry Potter finds himself competing in a hazardous tournament between rival schools.', '02:37:00', 'USA', 'link30', 'imagen30', 3);

-- Sucursal 4: Cinepolis de culiacan
CALL InsertPeliculaConSucursal('The Lion King', 'Animation', 'A', 'Lion prince Simba and his father are targeted by his bitter uncle.', '01:28:00', 'USA', 'link31', 'imagen31', 4);
CALL InsertPeliculaConSucursal('Aladdin', 'Animation', 'A', 'A kind-hearted street urchin and a power-hungry Grand Vizier.', '01:30:00', 'USA', 'link32', 'imagen32', 4);
CALL InsertPeliculaConSucursal('Beauty and the Beast', 'Animation', 'A', 'A young woman whose father has been imprisoned by a terrifying beast.', '01:24:00', 'USA', 'link33', 'imagen33', 4);
CALL InsertPeliculaConSucursal('The Little Mermaid', 'Animation', 'A', 'A mermaid princess makes a Faustian bargain in an attempt to become human.', '01:23:00', 'USA', 'link34', 'imagen34', 4);
CALL InsertPeliculaConSucursal('Mulan', 'Animation', 'A', 'To save her father from death in the army, a young maiden secretly goes in his place.', '01:28:00', 'USA', 'link35', 'imagen35', 4);
CALL InsertPeliculaConSucursal('Tarzan', 'Animation', 'A', 'A man raised by gorillas must decide where he really belongs.', '01:28:00', 'USA', 'link36', 'imagen36', 4);
CALL InsertPeliculaConSucursal('Pocahontas', 'Animation', 'A', 'An English soldier and the daughter of an Algonquin chief share a romance.', '01:21:00', 'USA', 'link37', 'imagen37', 4);
CALL InsertPeliculaConSucursal('Hercules', 'Animation', 'A', 'The son of the Greek gods Zeus and Hera is stripped of his immortality.', '01:33:00', 'USA', 'link38', 'imagen38', 4);
CALL InsertPeliculaConSucursal('Cinderella', 'Animation', 'A', 'When Cinderella s cruel stepmother prevents her from attending the Royal Ball.', '01:14:00', 'USA', 'link39', 'imagen39', 4);
CALL InsertPeliculaConSucursal('Snow White and the Seven Dwarfs', 'Animation', 'A', 'Exiled into the dangerous forest by her wicked stepmother.', '01:23:00', 'USA', 'link40', 'imagen40', 4);

-- Sucursal 5: Cinepolis de mazatlan
CALL InsertPeliculaConSucursal('The Godfather', 'Crime', 'C', 'The aging patriarch of an organized crime dynasty transfers control of his empire.', '02:55:00', 'USA', 'link41', 'imagen41', 5);
CALL InsertPeliculaConSucursal('Goodfellas', 'Crime', 'C', 'The story of Henry Hill and his life in the mob.', '02:26:00', 'USA', 'link42', 'imagen42', 5);
CALL InsertPeliculaConSucursal('Scarface', 'Crime', 'C', 'In 1980 Miami, a determined Cuban immigrant takes over a drug cartel.', '02:50:00', 'USA', 'link43', 'imagen43', 5);
CALL InsertPeliculaConSucursal('The Departed', 'Crime', 'C', 'An undercover cop and a mole in the police attempt to identify each other.', '02:31:00', 'USA', 'link44', 'imagen44', 5);
CALL InsertPeliculaConSucursal('Heat', 'Crime', 'C', 'A group of high-end professional thieves start to feel the heat.', '02:50:00', 'USA', 'link45', 'imagen45', 5);
CALL InsertPeliculaConSucursal('City of God', 'Crime', 'C', 'In the slums of Rio, two kids paths diverge as one struggles to become a photographer.', '02:10:00', 'Brazil', 'link46', 'imagen46', 5);
CALL InsertPeliculaConSucursal('Taxi Driver', 'Crime', 'C', 'A mentally unstable veteran works as a nighttime taxi driver.', '01:54:00', 'USA', 'link47', 'imagen47', 5);
CALL InsertPeliculaConSucursal('Reservoir Dogs', 'Crime', 'A', 'After a simple jewelry heist goes terribly wrong.', '01:39:00', 'USA', 'link48', 'imagen48', 5);
CALL InsertPeliculaConSucursal('Léon: The Professional', 'Crime', 'C', 'Mathilda, a 12-year-old girl, is reluctantly taken in by Léon.', '01:50:00', 'France', 'link49', 'imagen49', 5);
CALL InsertPeliculaConSucursal('Casino', 'Crime', 'C', 'A tale of greed, deception, money, power, and murder.', '02:58:00', 'USA', 'link50', 'imagen50', 5);




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

-- pruebas sucursal tiene peliculas
select * from Sucursal_Tiene_Pelicula;
select p.titulo, s.nombre from Sucursal_Tiene_Pelicula as sp inner join Pelicula as p inner join Sucursal as s
on s.id = sp.idSucursal and p.id = sp.idPelicula
WHERE s.id = 1;