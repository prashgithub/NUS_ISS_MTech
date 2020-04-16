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
-- Table structure for table `policies`
--

DROP TABLE IF EXISTS `policies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `policies` (
  `policy_id` int NOT NULL AUTO_INCREMENT,
  `policy_name` varchar(255) NOT NULL,
  PRIMARY KEY (`policy_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policies`
--

LOCK TABLES `policies` WRITE;
/*!40000 ALTER TABLE `policies` DISABLE KEYS */;
INSERT INTO `policies` VALUES (1,'AIA HealthShield Gold Max Standard Plan'),(2,'AIA HealthShield Gold Max C*'),(3,'AIA HealthShield Gold Max B'),(4,'AIA HealthShield Gold Max A'),(5,'Aviva MyShield Standard Plan'),(6,'Aviva MyShield Plan 3'),(7,'Aviva MyShield Plan 2'),(8,'Aviva MyShield Plan 1'),(9,'AXA Shield Standard Plan'),(10,'AXA Shield Plan C'),(11,'AXA Shield Plan B'),(12,'AXA Shield Plan A'),(13,'Great Eastern Supreme Health Standard Plan'),(14,'Great Eastern Supreme Health B Plus'),(15,'Great Eastern Supreme Health A Plus'),(16,'Great Eastern Supreme Health P Plus'),(17,'Income Enhanced IncomeShield C'),(18,'Income IncomeShield Standard Plan'),(19,'Income Enhanced IncomeShield Basic'),(20,'Income Enhanced IncomeShield Advantage'),(21,'Income Enhanced IncomeShield Preferred'),(22,'Prudential PruShield Standard Plan'),(23,'Prudential PruShield B'),(24,'Prudential PruShield A Plus'),(25,'Prudential PruShield A Premier'),(26,'Raffles Shield Standard'),(27,'Raffles Shield B'),(28,'Raffles Shield A'),(29,'Raffles Shield Private');
/*!40000 ALTER TABLE `policies` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-05  1:51:58
