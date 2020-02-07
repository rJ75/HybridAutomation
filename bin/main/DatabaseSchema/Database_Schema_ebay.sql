-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               8.0.19 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             10.3.0.5858
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for ebay_data
CREATE DATABASE IF NOT EXISTS `ebay_data` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ebay_data`;

-- Dumping structure for table ebay_data.object_manager
CREATE TABLE IF NOT EXISTS `object_manager` (
  `ID` int NOT NULL,
  `SCREEN` varchar(50) NOT NULL DEFAULT '',
  `OBJECT` varchar(50) NOT NULL DEFAULT '',
  `MOBILE_COMMON` varchar(1000) DEFAULT NULL,
  `ANDROID` varchar(1000) DEFAULT NULL,
  `IPHONE` varchar(1000) DEFAULT NULL,
  `IPAD` varchar(1000) DEFAULT NULL,
  `WEB_COMMON` varchar(1000) DEFAULT NULL,
  `CHROME` varchar(1000) DEFAULT NULL,
  `FIREFOX` varchar(1000) DEFAULT NULL,
  `IE` varchar(1000) DEFAULT NULL,
  `SAFARI` varchar(1000) DEFAULT NULL,
  `EDGE` varchar(1000) DEFAULT NULL,
  `OPERA` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table ebay_data.test_manager
CREATE TABLE IF NOT EXISTS `test_manager` (
  `EXECUTE_FLAG` char(2) NOT NULL DEFAULT 'N',
  `ALMID` int NOT NULL DEFAULT '0',
  `JSON` varchar(5000) NOT NULL DEFAULT '{}',
  `ANDROID_ELIGIBLE` char(2) NOT NULL DEFAULT 'Y',
  `IPHONE_ELIGIBLE` char(2) NOT NULL DEFAULT 'Y',
  `IPAD_ELIGIBLE` char(2) NOT NULL DEFAULT 'Y',
  `CHROME_ELIGIBLE` char(2) NOT NULL DEFAULT 'Y',
  `FIREFOX_ELIGIBLE` char(2) NOT NULL DEFAULT 'Y',
  `IE_ELIGIBLE` char(2) NOT NULL DEFAULT 'Y',
  `SAFARI_ELIGIBLE` char(2) NOT NULL DEFAULT 'Y',
  `EDGE_ELIGIBLE` char(2) NOT NULL DEFAULT 'Y',
  `OPERA_ELIGIBLE` char(2) NOT NULL DEFAULT 'Y',
  `MODULE` varchar(50) NOT NULL DEFAULT '',
  `SUB_MODULE` varchar(50) NOT NULL DEFAULT '',
  `TC_ID` varchar(50) NOT NULL DEFAULT '',
  `USERNAME` varchar(50) NOT NULL DEFAULT '',
  `PASSWORD` varchar(50) NOT NULL DEFAULT '',
  `PACKAGE_NAME` varchar(50) NOT NULL DEFAULT '',
  `CLASS_NAME` varchar(50) NOT NULL DEFAULT '',
  `TC_NAME` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`ALMID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
