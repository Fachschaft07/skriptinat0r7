-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server Version:               5.5.32 - MySQL Community Server (GPL)
-- Server Betriebssystem:        Win32
-- HeidiSQL Version:             8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Exportiere Datenbank Struktur für ifw
CREATE DATABASE IF NOT EXISTS `ifw` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `ifw`;


-- Exportiere Struktur von Tabelle ifw.ifw
CREATE TABLE IF NOT EXISTS `ifw` (
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(50) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Exportiere Daten aus Tabelle ifw.ifw: ~2 rows (ungefähr)
/*!40000 ALTER TABLE `ifw` DISABLE KEYS */;
INSERT INTO `ifw` (`name`, `password`) VALUES
	('ifw20000', 'ifw20000'),
	('ifw10000', 'ifw10000');
/*!40000 ALTER TABLE `ifw` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
