

-- MySQL dump 10.13  Distrib 5.1.63, for apple-darwin10.3.0 (i386)
--
-- Host: localhost    Database: ECCS
-- ------------------------------------------------------
-- Server version	5.1.63

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table 't_handling_cost'
--

DROP TABLE IF EXISTS t_handling_cost;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE 't_handling_cost' (
  'pk_id' int(11) NOT NULL AUTO_INCREMENT,
  'air_price' double DEFAULT NULL,
  'area' varchar(600) NOT NULL,
  'air_low_price' double DEFAULT NULL,
  'car_low_price' double DEFAULT NULL,
  'car_operate_price' double DEFAULT NULL,
  'car_weight_price' double DEFAULT NULL,
  'include_area' varchar(1000) DEFAULT NULL,
  PRIMARY KEY ('pk_id')
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table 't_handling_cost'
--

LOCK TABLES 't_handling_cost' WRITE;
/*!40000 ALTER TABLE 't_handling_cost' DISABLE KEYS */;
/*!40000 ALTER TABLE 't_handling_cost' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table 't_menu'
--

DROP TABLE IF EXISTS 't_menu';
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE 't_menu' (
  'pk_id' int(11) NOT NULL AUTO_INCREMENT,
  'menu_name' varchar(50) NOT NULL,
  'type' int(11) NOT NULL,
  'url' varchar(200) NOT NULL,
  PRIMARY KEY ('pk_id')
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table 't_menu'
--

LOCK TABLES 't_menu' WRITE;
/*!40000 ALTER TABLE 't_menu' DISABLE KEYS */;
/*!40000 ALTER TABLE 't_menu' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table 't_user'
--

DROP TABLE IF EXISTS 't_user';
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE 't_user' (
  'pk_id' int(11) NOT NULL AUTO_INCREMENT,
  'idCard' varchar(20) DEFAULT NULL,
  'password' varchar(30) NOT NULL,
  'phone' varchar(30) DEFAULT NULL,
  'user_name' varchar(50) NOT NULL,
  'user_number' varchar(20) NOT NULL,
  'userSex' int(11) NOT NULL,
  'userType' int(11) NOT NULL,
  PRIMARY KEY ('pk_id'),
  UNIQUE KEY 'UK_3ol67qm82xfpw0uqrjma8vi4q' ('user_number')
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table 't_user'
--

LOCK TABLES 't_user' WRITE;
/*!40000 ALTER TABLE 't_user' DISABLE KEYS */;
/*!40000 ALTER TABLE 't_user' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table 't_waybll'
--

DROP TABLE IF EXISTS 't_waybll';
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE 't_waybll' (
  'pk_id' int(11) NOT NULL AUTO_INCREMENT,
  'added_weight' double DEFAULT NULL,
  'address' varchar(600) DEFAULT NULL,
  'awb' varchar(20) NOT NULL,
  'create_time' bigint(20) NOT NULL,
  'handling_cost' double DEFAULT NULL,
  'total' double DEFAULT NULL,
  'type' int(11) NOT NULL,
  'weight' double NOT NULL,
  'fk_handling_cost_id' int(11) DEFAULT NULL,
  'fk_user_id' int(11) DEFAULT NULL,
  PRIMARY KEY ('pk_id'),
  KEY 'FK_nffhb1e5sfurl7ijnr9k5w8ch' ('fk_handling_cost_id'),
  KEY 'FK_47y1f1rfuvfl0yhmtwpswrvbq' ('fk_user_id'),
  CONSTRAINT 'FK_47y1f1rfuvfl0yhmtwpswrvbq' FOREIGN KEY ('fk_user_id') REFERENCES 't_user' ('pk_id'),
  CONSTRAINT 'FK_nffhb1e5sfurl7ijnr9k5w8ch' FOREIGN KEY ('fk_handling_cost_id') REFERENCES 't_handling_cost' ('pk_id')
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table 't_waybll'
--

LOCK TABLES 't_waybll' WRITE;
/*!40000 ALTER TABLE 't_waybll' DISABLE KEYS */;
/*!40000 ALTER TABLE 't_waybll' ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-08 17:25:33




<-- admin init -->
INSERT INTO t_user (idCard, PASSWORD, phone, user_name, user_number, userSex, userType) VALUES("510625111111111111", "123456", "13888888888","admin","admin",1,1)