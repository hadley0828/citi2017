# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: localhost (MySQL 5.7.17)
# Database: citi
# Generation Time: 2017-08-07 03:45:24 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


#Dump of database citi
CREATE DATABASE /*!32312 IF NOT EXISTS*/`@@@dbName@@@` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `@@@dbName@@@`;

# Dump of table account_set
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account_set`;

CREATE TABLE `account_set` (
  `company_name` varchar(100) NOT NULL,
  `location` varchar(100) NOT NULL,
  `industry` varchar(40) NOT NULL,
  `start_date` date DEFAULT NULL,
  PRIMARY KEY (`company_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table receipts
# ------------------------------------------------------------

DROP TABLE IF EXISTS `receipts`;

CREATE TABLE `receipts` (
  `receipts_id` varchar(30) NOT NULL,
  `path` varchar(255) NOT NULL,
  PRIMARY KEY (`receipts_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table voucher
# ------------------------------------------------------------

DROP TABLE IF EXISTS `voucher`;

CREATE TABLE `voucher` (
  `v_id` varchar(10) NOT NULL,
  `date` date NOT NULL,
  `is_addedreceipts` tinyint(1) NOT NULL,
  `abstract` varchar(100) NOT NULL,
  `subject` varchar(20) NOT NULL,
  `debit_amount` double NOT NULL,
  `credit_amount` double NOT NULL,
  `voucher_maker` varchar(50) NOT NULL,
  `remark` varchar(200) NOT NULL,
  PRIMARY KEY (`v_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table voucher_template
# ------------------------------------------------------------

DROP TABLE IF EXISTS `voucher_template`;

CREATE TABLE `voucher_template` (
  `template_id` varchar(10) NOT NULL,
  `abstract` varchar(100) NOT NULL,
  `subject` varchar(20) NOT NULL,
  PRIMARY KEY (`template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
