-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-11-2023 a las 17:05:59
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `turnero`
--
CREATE DATABASE IF NOT EXISTS `turnero` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci;
USE `turnero`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudadano`
--

DROP TABLE IF EXISTS `ciudadano`;
CREATE TABLE IF NOT EXISTS `ciudadano` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `APELLIDO` varchar(255) DEFAULT NULL,
  `DNI` varchar(255) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `TELEFONO` varchar(255) DEFAULT NULL,
  `direccion_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CIUDADANO_direccion_id` (`direccion_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `ciudadano`
--

INSERT INTO `ciudadano` (`ID`, `APELLIDO`, `DNI`, `NOMBRE`, `TELEFONO`, `direccion_id`) VALUES
(1, 'jaquez', '06287373R', 'Anny', '54126593', 1),
(2, 'jaquez', '04863591B', 'Carlos', '54126593', 1),
(3, 'Patatencio', '02317715M', 'Adela', '54126593', 2),
(4, 'Palomares', '54867953E', 'Daniel', '684259754', 3),
(5, 'Probando cambios', '02747669C', 'Prueba1', '987 456 321', 4),
(6, 'Probando cambios', '412547896e', 'Prueba2', '615824726', 5),
(7, 'Patata', '412547896e', 'Prueba2', '854712245', 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direccion`
--

DROP TABLE IF EXISTS `direccion`;
CREATE TABLE IF NOT EXISTS `direccion` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CALLE` varchar(255) DEFAULT NULL,
  `CIUDAD` varchar(255) DEFAULT NULL,
  `CP` varchar(255) DEFAULT NULL,
  `PISO` varchar(255) DEFAULT NULL,
  `PORTAL` varchar(255) DEFAULT NULL,
  `PROVINCIA` varchar(255) DEFAULT NULL,
  `PUERTA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `direccion`
--

INSERT INTO `direccion` (`ID`, `CALLE`, `CIUDAD`, `CP`, `PISO`, `PORTAL`, `PROVINCIA`, `PUERTA`) VALUES
(1, 'Patata', 'madrid', '28000', '2', '2', 'madrid', '3'),
(2, '123456', 'madrid', '28550', '2', '2', 'torrejon', '4'),
(3, 'Plaza de la habana', 'Torrejon de Ardoz', '28850', '6', '2', 'Madrid', 'C'),
(4, 'Prueba ', 'Controladora', '1', '1', '1', 'Logica', '1'),
(5, 'Prueba ', 'Controladora', '2', '2', '2', 'Logica', '2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tramite`
--

DROP TABLE IF EXISTS `tramite`;
CREATE TABLE IF NOT EXISTS `tramite` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DESCRIPCION` varchar(255) DEFAULT NULL,
  `turno_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_TRAMITE_turno_id` (`turno_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `tramite`
--

INSERT INTO `tramite` (`ID`, `DESCRIPCION`, `turno_id`) VALUES
(1, 'eafs', 1),
(2, 'afadads', 2),
(3, 'dyhgref<szdxmhegrtmnfsczdvfxb', 3),
(4, 'djshtrge', 4),
(5, 'Morbi non eleifend orci. Sed sit amet justo sit amet mi viverra suscipit sed in lacus. Duis erat risus, tempor ac ex a, euismod fermentum magna. Suspendisse enim odio, lacinia tincidunt volutpat eget, finibus nec dolor.', 5),
(6, 'Sed vulputate elit in leo volutpat faucibus. Curabitur dictum venenatis dapibus. Nam pulvinar faucibus justo et placerat. In felis mi, efficitur quis ullamcorper vitae, dapibus at odio. Cras auctor et turpis sed imperdiet. Sed vitae iaculis justo.', 6),
(7, 'Probando los cambios hechos a la controladora logica', 7),
(8, 'Probando cambios en controladora 2', 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `turno`
--

DROP TABLE IF EXISTS `turno`;
CREATE TABLE IF NOT EXISTS `turno` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ESTADO` int(11) DEFAULT NULL,
  `FECHA` date DEFAULT NULL,
  `NUMERO` varchar(255) DEFAULT NULL,
  `ciudadano_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_TURNO_ciudadano_id` (`ciudadano_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `turno`
--

INSERT INTO `turno` (`ID`, `ESTADO`, `FECHA`, `NUMERO`, `ciudadano_id`) VALUES
(1, 2, '2023-11-22', '27-Nov-0', 3),
(2, 1, '2023-11-28', '27-Nov-0', 2),
(3, 0, '2023-11-30', '28-Nov-0', 3),
(4, 0, '2023-11-29', '28-Nov-0', 1),
(5, 2, '2023-12-07', '28-Nov-0', 4),
(6, 0, '2023-12-12', '28-Nov-0', 2),
(7, 2, '2023-12-10', '29-Nov-0', 5),
(8, 0, '2023-12-10', '29-Nov-0', 6);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ciudadano`
--
ALTER TABLE `ciudadano`
  ADD CONSTRAINT `FK_CIUDADANO_direccion_id` FOREIGN KEY (`direccion_id`) REFERENCES `direccion` (`ID`);

--
-- Filtros para la tabla `tramite`
--
ALTER TABLE `tramite`
  ADD CONSTRAINT `FK_TRAMITE_turno_id` FOREIGN KEY (`turno_id`) REFERENCES `turno` (`ID`);

--
-- Filtros para la tabla `turno`
--
ALTER TABLE `turno`
  ADD CONSTRAINT `FK_TURNO_ciudadano_id` FOREIGN KEY (`ciudadano_id`) REFERENCES `ciudadano` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
