DROP DATABASE IF EXISTS lab8;

CREATE DATABASE IF NOT EXISTS lab8;

USE lab8;

-- ---------------------------------------------------------------------- --
-- Tables                                                                 --
-- ---------------------------------------------------------------------- --
-- ---------------------------------------------------------------------- --
-- Add table "Facultad"                                                 --
-- ---------------------------------------------------------------------- --

CREATE TABLE `Facultad` (
    `idFacultad` INTEGER NOT NULL AUTO_INCREMENT,
    `nombreFacultad` VARCHAR(30) NOT NULL,
    
    CONSTRAINT `PK_Facultad` PRIMARY KEY (`idFacultad`)
);

CREATE INDEX `nombreFacultad` ON `Facultad` (`nombreFacultad`);



-- ---------------------------------------------------------------------- --
-- Add table "Estudiante"                                                   --
-- ---------------------------------------------------------------------- --

CREATE TABLE `Estudiante` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(40) NOT NULL,
    `idFacultad` INTEGER,
    `gpa` DECIMAL(10,4) DEFAULT 0,
    `creditosCompletados` DECIMAL(10,4) DEFAULT 0,
    CONSTRAINT `PK_Estudiante` PRIMARY KEY (`id`)
);

CREATE INDEX `nombre` ON `Estudiante` (`nombrefacultad`);


