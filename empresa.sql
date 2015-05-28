-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-03-2015 a las 04:19:56
-- Versión del servidor: 5.6.16
-- Versión de PHP: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `empresa`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE IF NOT EXISTS `administrador` (
  `id_admin` int(11) NOT NULL,
  `rut_admin` varchar(45) DEFAULT NULL,
  `id_edificio` int(11) DEFAULT NULL,
  `nombre_admin` varchar(45) DEFAULT NULL,
  `telefono_admin` varchar(45) DEFAULT NULL,
  `email_admin` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_admin`),
  KEY `admin_ed_idx` (`id_edificio`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `artefacto`
--

CREATE TABLE IF NOT EXISTS `artefacto` (
  `id_artefacto` int(11) NOT NULL,
  `id_edificio` int(11) NOT NULL,
  `tipo_artefacto` varchar(25) NOT NULL,
  `potencia_artefacto` int(11) NOT NULL,
  PRIMARY KEY (`id_artefacto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `conducto`
--

CREATE TABLE IF NOT EXISTS `conducto` (
  `id_conducto` int(11) NOT NULL AUTO_INCREMENT,
  `id_edificio` int(11) DEFAULT NULL,
  `cant_depto_conducto` int(11) DEFAULT NULL,
  `sello_conducto` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_conducto`),
  KEY `edificio_idx` (`id_edificio`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `conducto`
--

INSERT INTO `conducto` (`id_conducto`, `id_edificio`, `cant_depto_conducto`, `sello_conducto`) VALUES
(2, 1, 1, 'verde'),
(3, 1, 7, 'amarillo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departamento`
--

CREATE TABLE IF NOT EXISTS `departamento` (
  `id_departamento` int(11) NOT NULL AUTO_INCREMENT,
  `id_conducto` int(11) DEFAULT NULL,
  `cant_conductos` int(11) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `sello_departamento` varchar(45) DEFAULT NULL,
  `num_departamento` int(45) DEFAULT NULL,
  PRIMARY KEY (`id_departamento`),
  KEY `conducto_idx` (`id_conducto`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `departamento`
--

INSERT INTO `departamento` (`id_departamento`, `id_conducto`, `cant_conductos`, `descripcion`, `sello_departamento`, `num_departamento`) VALUES
(1, 2, 1, 'ww', '1', 111);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalledepto`
--

CREATE TABLE IF NOT EXISTS `detalledepto` (
  `id_detalledepto` int(11) NOT NULL AUTO_INCREMENT,
  `id_departamento` int(11) NOT NULL,
  `detalle` varchar(300) NOT NULL,
  PRIMARY KEY (`id_detalledepto`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `detalledepto`
--

INSERT INTO `detalledepto` (`id_detalledepto`, `id_departamento`, `detalle`) VALUES
(1, 1, '111');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleedificio`
--

CREATE TABLE IF NOT EXISTS `detalleedificio` (
  `id_detalle` int(11) NOT NULL,
  `id_edificio` int(11) NOT NULL,
  `tipo_instalacion` int(2) DEFAULT NULL,
  `tipo_construccion` varchar(100) NOT NULL,
  `ciige_anterior` varchar(45) DEFAULT NULL,
  `ubicacion_medidores` varchar(45) DEFAULT NULL,
  `fecha_modificacion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_detalle`,`id_edificio`),
  KEY `detalle_ed_idx` (`id_edificio`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `edificio`
--

CREATE TABLE IF NOT EXISTS `edificio` (
  `id_edificio` int(11) NOT NULL AUTO_INCREMENT,
  `id_gas` int(11) DEFAULT NULL,
  `id_detalle` int(11) DEFAULT NULL,
  `nombre_ejecutivo` varchar(45) DEFAULT NULL,
  `rut_edificio` varchar(45) DEFAULT NULL,
  `nombre_edificio` varchar(45) DEFAULT NULL,
  `ano_edificio` int(11) DEFAULT NULL,
  `direccion_edificio` varchar(45) DEFAULT NULL,
  `telefono_edificio` varchar(45) DEFAULT NULL,
  `sello_edificio` varchar(45) DEFAULT NULL,
  `norma_aplicada` varchar(45) DEFAULT NULL,
  `cant_departamentos` int(11) DEFAULT NULL,
  `cant_conductos` int(11) DEFAULT NULL,
  `cant_calderas` int(11) DEFAULT NULL,
  `cant_pisos` int(11) DEFAULT NULL,
  `potencia_real` varchar(45) DEFAULT NULL,
  `potencia_estimada` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_edificio`),
  KEY `detalle_idx` (`id_detalle`),
  KEY `gas_idx` (`id_gas`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `edificio`
--

INSERT INTO `edificio` (`id_edificio`, `id_gas`, `id_detalle`, `nombre_ejecutivo`, `rut_edificio`, `nombre_edificio`, `ano_edificio`, `direccion_edificio`, `telefono_edificio`, `sello_edificio`, `norma_aplicada`, `cant_departamentos`, `cant_conductos`, `cant_calderas`, `cant_pisos`, `potencia_real`, `potencia_estimada`) VALUES
(1, 1, 1, '1', '1', '1', 1, '1', '1', '1', '1', 1, 1, 1, 1, '1', '1'),
(2, 0, 0, 'hola', '17501167-6', 't', 1999, 'a', '1111', NULL, 'DS222', 1, 1, 1, 1, '11', '0'),
(3, 3, 0, 'jose Donoso', '17501167-6', 'Santa Marta', 1961, 'alvarez 56', '76465680', NULL, 'DI3952', 2, 3, 6, 2, '11', '0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresagas`
--

CREATE TABLE IF NOT EXISTS `empresagas` (
  `id_empresa` int(11) NOT NULL AUTO_INCREMENT,
  `empresa_gas` int(11) NOT NULL,
  PRIMARY KEY (`id_empresa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fecha`
--

CREATE TABLE IF NOT EXISTS `fecha` (
  `id_edificio` int(11) NOT NULL,
  `fecha_inspeccion` varchar(45) DEFAULT NULL,
  `fecha_primera` varchar(45) DEFAULT NULL,
  `fecha_segunda` varchar(45) DEFAULT NULL,
  `fecha_cierre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_edificio`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gas`
--

CREATE TABLE IF NOT EXISTS `gas` (
  `id_gas` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_gas` varchar(45) DEFAULT NULL,
  `id_empresa` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_gas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) NOT NULL,
  `nombre_usuario` varchar(45) DEFAULT NULL,
  `contrasena` varchar(45) DEFAULT NULL,
  `tipo_usuario` varchar(45) DEFAULT NULL,
  `email_usuario` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD CONSTRAINT `admin_ed` FOREIGN KEY (`id_edificio`) REFERENCES `edificio` (`id_edificio`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `conducto`
--
ALTER TABLE `conducto`
  ADD CONSTRAINT `conducto_ibfk_1` FOREIGN KEY (`id_edificio`) REFERENCES `edificio` (`id_edificio`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `departamento`
--
ALTER TABLE `departamento`
  ADD CONSTRAINT `conducto` FOREIGN KEY (`id_conducto`) REFERENCES `conducto` (`id_conducto`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `detalleedificio`
--
ALTER TABLE `detalleedificio`
  ADD CONSTRAINT `detalle_ed` FOREIGN KEY (`id_edificio`) REFERENCES `edificio` (`id_edificio`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `fecha`
--
ALTER TABLE `fecha`
  ADD CONSTRAINT `fecha_ed` FOREIGN KEY (`id_edificio`) REFERENCES `edificio` (`id_edificio`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
