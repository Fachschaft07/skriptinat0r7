-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server Version:               5.5.32 - MySQL Community Server (GPL)
-- Server Betriebssystem:        Win32
-- HeidiSQL Version:             9.2.0.4947
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Exportiere Struktur von Tabelle test.skriptor_copy_shop_order
CREATE TABLE IF NOT EXISTS `skriptor_copy_shop_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_date` date DEFAULT NULL,
  `printout_delivery` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Daten Export vom Benutzer nicht ausgewählt


-- Exportiere Struktur von Tabelle test.skriptor_copy_shop_order_student_orders
CREATE TABLE IF NOT EXISTS `skriptor_copy_shop_order_student_orders` (
  `skriptor_copy_shop_order` int(11) NOT NULL,
  `student_orders` int(11) NOT NULL,
  PRIMARY KEY (`skriptor_copy_shop_order`,`student_orders`),
  UNIQUE KEY `UK_bc8uog3c6mepkq54274x35sib` (`student_orders`),
  CONSTRAINT `FK_bc8uog3c6mepkq54274x35sib` FOREIGN KEY (`student_orders`) REFERENCES `skriptor_student_order` (`student_order_id`),
  CONSTRAINT `FK_rufrsetc048pejb1j7mamwnon` FOREIGN KEY (`skriptor_copy_shop_order`) REFERENCES `skriptor_copy_shop_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Daten Export vom Benutzer nicht ausgewählt


-- Exportiere Struktur von Tabelle test.skriptor_lecture
CREATE TABLE IF NOT EXISTS `skriptor_lecture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `study_program` varchar(255) DEFAULT NULL,
  `reading_professor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_acahi964a98pxou7wxm036ot0` (`reading_professor`),
  CONSTRAINT `FK_acahi964a98pxou7wxm036ot0` FOREIGN KEY (`reading_professor`) REFERENCES `skriptor_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Daten Export vom Benutzer nicht ausgewählt


-- Exportiere Struktur von Tabelle test.skriptor_script
CREATE TABLE IF NOT EXISTS `skriptor_script` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `semester_type` varchar(255) DEFAULT NULL,
  `semester_year` int(11) DEFAULT NULL,
  `submitted_completely` bit(1) NOT NULL,
  `submitter` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gqj5al2pfyi9grh1r0932rmc` (`submitter`),
  CONSTRAINT `FK_gqj5al2pfyi9grh1r0932rmc` FOREIGN KEY (`submitter`) REFERENCES `skriptor_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Daten Export vom Benutzer nicht ausgewählt


-- Exportiere Struktur von Tabelle test.skriptor_script_authors
CREATE TABLE IF NOT EXISTS `skriptor_script_authors` (
  `skriptor_script` int(11) NOT NULL,
  `authors` int(11) NOT NULL,
  PRIMARY KEY (`skriptor_script`,`authors`),
  KEY `FK_9vxmw3om91hnr4bmy6bewptjd` (`authors`),
  CONSTRAINT `FK_9vxmw3om91hnr4bmy6bewptjd` FOREIGN KEY (`authors`) REFERENCES `skriptor_user` (`id`),
  CONSTRAINT `FK_h8h0x3jlwltrekfv4hvfxmmap` FOREIGN KEY (`skriptor_script`) REFERENCES `skriptor_script` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Daten Export vom Benutzer nicht ausgewählt


-- Exportiere Struktur von Tabelle test.skriptor_script_document
CREATE TABLE IF NOT EXISTS `skriptor_script_document` (
  `hashvalue` bigint(20) NOT NULL,
  `file` longblob NOT NULL,
  `file_size` int(11) NOT NULL,
  `filename` varchar(255) NOT NULL,
  `is_password_missing` bit(1) NOT NULL,
  `note` longtext,
  `password` varchar(255) DEFAULT NULL,
  `review_state` int(11) DEFAULT NULL,
  `sortnumber` int(11) NOT NULL,
  PRIMARY KEY (`hashvalue`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Daten Export vom Benutzer nicht ausgewählt


-- Exportiere Struktur von Tabelle test.skriptor_script_document_scripts
CREATE TABLE IF NOT EXISTS `skriptor_script_document_scripts` (
  `script_documents` bigint(20) NOT NULL,
  `scripts` int(11) NOT NULL,
  PRIMARY KEY (`script_documents`,`scripts`),
  KEY `FK_e0o2tw8qpex83u5diy732f0nl` (`scripts`),
  CONSTRAINT `FK_e0o2tw8qpex83u5diy732f0nl` FOREIGN KEY (`scripts`) REFERENCES `skriptor_script` (`id`),
  CONSTRAINT `FK_strd7fe4tqmyqxfsj107j78qr` FOREIGN KEY (`script_documents`) REFERENCES `skriptor_script_document` (`hashvalue`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Daten Export vom Benutzer nicht ausgewählt


-- Exportiere Struktur von Tabelle test.skriptor_script_lectures
CREATE TABLE IF NOT EXISTS `skriptor_script_lectures` (
  `used_scripts` int(11) NOT NULL,
  `lectures` int(11) NOT NULL,
  PRIMARY KEY (`used_scripts`,`lectures`),
  KEY `FK_lq4n6910pyk1qx4iwgfp00mw7` (`lectures`),
  CONSTRAINT `FK_k5oejl5kke5wcdpw8mbd1daoq` FOREIGN KEY (`used_scripts`) REFERENCES `skriptor_script` (`id`),
  CONSTRAINT `FK_lq4n6910pyk1qx4iwgfp00mw7` FOREIGN KEY (`lectures`) REFERENCES `skriptor_lecture` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Daten Export vom Benutzer nicht ausgewählt


-- Exportiere Struktur von Tabelle test.skriptor_student_order
CREATE TABLE IF NOT EXISTS `skriptor_student_order` (
  `student_order_id` int(11) NOT NULL AUTO_INCREMENT,
  `notes` longtext,
  `order_date` date DEFAULT NULL,
  `student_pickup` date DEFAULT NULL,
  `copy_shop_order` int(11) DEFAULT NULL,
  `orderer` int(11) DEFAULT NULL,
  PRIMARY KEY (`student_order_id`),
  KEY `FK_hec13c3i17klgph1eodiam46h` (`copy_shop_order`),
  KEY `orderer` (`orderer`),
  CONSTRAINT `FK_hec13c3i17klgph1eodiam46h` FOREIGN KEY (`copy_shop_order`) REFERENCES `skriptor_copy_shop_order` (`id`),
  CONSTRAINT `FK_ORDERER` FOREIGN KEY (`orderer`) REFERENCES `skriptor_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Daten Export vom Benutzer nicht ausgewählt


-- Exportiere Struktur von Tabelle test.skriptor_student_order_script_documents
CREATE TABLE IF NOT EXISTS `skriptor_student_order_script_documents` (
  `orders` int(11) NOT NULL,
  `script_documents` bigint(20) NOT NULL,
  PRIMARY KEY (`orders`,`script_documents`),
  KEY `script_documents` (`script_documents`),
  CONSTRAINT `FK_1purkycgh3th33pi3xy8uq0oj` FOREIGN KEY (`orders`) REFERENCES `skriptor_student_order` (`student_order_id`),
  CONSTRAINT `FK_tihovei84i3j1oi4bchn21ntq` FOREIGN KEY (`script_documents`) REFERENCES `skriptor_script_document` (`hashvalue`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Daten Export vom Benutzer nicht ausgewählt


-- Exportiere Struktur von Tabelle test.skriptor_user
CREATE TABLE IF NOT EXISTS `skriptor_user` (
  `dtype` varchar(31) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(254) DEFAULT NULL,
  `facultyid` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `role` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Daten Export vom Benutzer nicht ausgewählt


-- Exportiere Struktur von Tabelle test.skriptor_user_student_orders
CREATE TABLE IF NOT EXISTS `skriptor_user_student_orders` (
  `skriptor_user` int(11) NOT NULL,
  `student_orders` int(11) NOT NULL,
  PRIMARY KEY (`skriptor_user`,`student_orders`),
  UNIQUE KEY `UK_651x567fp47en6lm5oxh9132l` (`student_orders`),
  CONSTRAINT `FK_651x567fp47en6lm5oxh9132l` FOREIGN KEY (`student_orders`) REFERENCES `skriptor_student_order` (`student_order_id`),
  CONSTRAINT `FK_iri1jota7vl1xiftj34wmt8ei` FOREIGN KEY (`skriptor_user`) REFERENCES `skriptor_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Daten Export vom Benutzer nicht ausgewählt
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
