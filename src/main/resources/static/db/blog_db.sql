CREATE DATABASE blogapp CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE blogapp;

CREATE TABLE usuarios (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          nombre VARCHAR(100) NOT NULL,
                          email VARCHAR(150) UNIQUE NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          rol VARCHAR(20) NOT NULL DEFAULT 'ROLE_USER',
                          fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de publicaciones
CREATE TABLE publicaciones (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               titulo VARCHAR(255) NOT NULL,
                               contenido TEXT NOT NULL,
                               archivo LONGBLOB NULL,
                               nombre_archivo VARCHAR(255),
                               tipo_archivo VARCHAR(100),
                               fecha_publicacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               usuario_id INT NOT NULL,
                               FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Tabla de comentarios
CREATE TABLE comentarios (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             contenido TEXT NOT NULL,
                             fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             usuario_id INT NOT NULL,
                             publicacion_id INT NOT NULL,
                             FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
                             FOREIGN KEY (publicacion_id) REFERENCES publicaciones(id) ON DELETE CASCADE
);

ALTER TABLE publicaciones
DROP COLUMN archivo,
  ADD COLUMN ruta_archivo VARCHAR(500) NULL AFTER tipo_archivo;


SHOW TABLES;
SHOW CREATE TABLE usuarios;
SHOW CREATE TABLE publicaciones;

select * from usuarios;
select * from publicaciones;
select * from comentarios;

drop database blogapp;