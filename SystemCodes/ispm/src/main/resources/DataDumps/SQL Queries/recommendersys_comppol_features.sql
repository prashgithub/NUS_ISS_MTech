-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: recommendersys
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comppol_features`
--

DROP TABLE IF EXISTS `comppol_features`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comppol_features` (
  `indexid` int NOT NULL AUTO_INCREMENT,
  `comppolicy_id` int NOT NULL,
  `policyfea_id` int NOT NULL,
  `benefit_value` int NOT NULL,
  `descr` varchar(55) DEFAULT NULL,
  `ward_id` int NOT NULL,
  PRIMARY KEY (`indexid`),
  KEY `comppolicy_id` (`comppolicy_id`),
  KEY `policyfea_id` (`policyfea_id`),
  CONSTRAINT `comppol_features_ibfk_1` FOREIGN KEY (`comppolicy_id`) REFERENCES `comp_policies` (`comppolicy_id`),
  CONSTRAINT `comppol_features_ibfk_2` FOREIGN KEY (`policyfea_id`) REFERENCES `policy_features` (`policyfea_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comppol_features`
--

LOCK TABLES `comppol_features` WRITE;
/*!40000 ALTER TABLE `comppol_features` DISABLE KEYS */;
INSERT INTO `comppol_features` VALUES (1,4,2,1,'As Charged',5),(2,8,2,1,'As Charged',5),(3,12,2,1,'As Charged',5),(4,16,2,1,'As Charged',5),(5,21,2,1,'As Charged',5),(6,25,2,1,'As Charged',5),(7,29,2,1,'As Charged',5),(8,4,5,100,'Number of Days',5),(9,8,5,100,'Number of Days',5),(10,12,5,120,'Number of Days',5),(11,16,5,180,'Number of Days',5),(12,21,5,90,'Number of Days',5),(13,25,5,180,'Number of Days',5),(14,29,5,180,'Number of Days',5),(15,4,3,1,'As Charged',5),(16,8,3,1,'As Charged',5),(17,12,3,1,'As Charged',5),(18,16,3,1,'As Charged',5),(19,21,3,1,'As Charged',5),(20,25,3,1,'As Charged',5),(21,29,3,1,'As Charged',5),(22,4,6,100,'Number of Days',5),(23,8,6,100,'Number of Days',5),(24,12,6,120,'Number of Days',5),(25,16,6,365,'Number of Days',5),(26,21,6,90,'Number of Days',5),(27,25,6,365,'Number of Days',5),(28,29,6,365,'Number of Days',5),(29,4,4,1500000,'Annual Coverage Limit',5),(30,8,4,2000000,'Annual Coverage Limit',5),(31,12,4,1500000,'Annual Coverage Limit',5),(32,16,4,1200000,'Annual Coverage Limit',5),(33,21,4,1000000,'Annual Coverage Limit',5),(34,25,4,1000000,'Annual Coverage Limit',5),(35,29,4,600000,'Annual Coverage Limit',5);
/*!40000 ALTER TABLE `comppol_features` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-05  1:51:57
