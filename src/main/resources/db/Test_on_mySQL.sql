USE test_foro_api;

create table usuarios
(
id Bigint NOT NULL AUTO_INCREMENT,
nombre varchar(100) NOT NULL,
email varchar(300) NOT NULL UNIQUE,
contrasena varchar(300) NOT NULL,
deshabilitado TINYINT DEFAULT 0,
primary key(id)
);

create table curso
(
id Bigint NOT NULL AUTO_INCREMENT,
Nombre varchar(200) NOT NULL,
categoria varchar(100) NOT NULL,
deshabilitado TINYINT DEFAULT 0,
primary key(id)
);

create table topico
(
id Bigint NOT NULL AUTO_INCREMENT,
titulo varchar(200) NOT NULL,
mensaje varchar(300) NOT NULL,
fechaCreacion date DEFAULT NULL,
StatusTopico varchar(100) NOT NULL,
usuario_id Bigint NOT NULL,
curso_id Bigint NOT NULL,
eliminado TINYINT DEFAULT 0,
primary key(id),
FOREIGN KEY (usuario_id)
      REFERENCES usuario(id),
FOREIGN KEY (curso_id)
      REFERENCES curso(id)
);

create table respuesta
(
id Bigint NOT NULL AUTO_INCREMENT,
mensaje varchar(300) NOT NULL,
topico_id Bigint NOT NULL,
usuario_id Bigint NOT NULL,
fechaCreacion date DEFAULT NULL,
solucion TINYINT DEFAULT 0,
eliminado TINYINT DEFAULT 0,
primary key(id),
FOREIGN KEY (topico_id)
      REFERENCES topico(id),
FOREIGN KEY (usuario_id)
      REFERENCES usuario(id)
);