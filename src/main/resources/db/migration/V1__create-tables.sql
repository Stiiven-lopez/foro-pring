USE foro_api;

create table usuarios
(
id Bigint NOT NULL AUTO_INCREMENT,
nombreusuario varchar(100) NOT NULL,
contrasena varchar(300) NOT NULL,
deshabilitado TINYINT DEFAULT 0,
primary key(id)
);

create table perfiles
(
id Bigint NOT NULL AUTO_INCREMENT,
nombrecompleto varchar(100) NOT NULL,
email varchar(300) NOT NULL UNIQUE,
idusuario Bigint NOT NULL,
deshabilitado TINYINT DEFAULT 0,
primary key(id),
FOREIGN KEY (idusuario)
      REFERENCES usuarios(id)
);

create table cursos
(
id Bigint NOT NULL AUTO_INCREMENT,
Nombre varchar(200) NOT NULL,
categoria varchar(100) NOT NULL,
deshabilitado TINYINT DEFAULT 0,
primary key(id)
);

create table topicos
(
id Bigint NOT NULL AUTO_INCREMENT,
titulo varchar(200) NOT NULL,
mensaje varchar(300) NOT NULL,
fechaCreacion date DEFAULT NULL,
StatusTopico varchar(100) NOT NULL,
idperfil Bigint NOT NULL,
idcurso Bigint NOT NULL,
eliminado TINYINT DEFAULT 0,
primary key(id),
FOREIGN KEY (idperfil)
      REFERENCES perfiles(id),
FOREIGN KEY (idcurso)
      REFERENCES cursos(id)
);

create table respuestas
(
id Bigint NOT NULL AUTO_INCREMENT,
mensaje varchar(300) NOT NULL,
idtopico Bigint NOT NULL,
idperfil Bigint NOT NULL,
fechaCreacion date DEFAULT NULL,
solucion TINYINT DEFAULT 0,
eliminado TINYINT DEFAULT 0,
primary key(id),
FOREIGN KEY (idtopico)
      REFERENCES topicos(id),
FOREIGN KEY (idperfil)
      REFERENCES perfiles(id)
);
