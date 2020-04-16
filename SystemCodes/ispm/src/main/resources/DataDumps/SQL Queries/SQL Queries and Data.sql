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
-- Temporary view structure for view `comp_pol_fea_view`
--

DROP TABLE IF EXISTS `comp_pol_fea_view`;
/*!50001 DROP VIEW IF EXISTS `comp_pol_fea_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `comp_pol_fea_view` AS SELECT 
 1 AS `WARD_CATEGORY`,
 1 AS `WARD_TYPE`,
 1 AS `POLICY_FEATURE`,
 1 AS `COMPANY`,
 1 AS `POLICY_NAME`,
 1 AS `BENEFITS`,
 1 AS `DESCRIPTION`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `comp_pol_prem_view`
--

DROP TABLE IF EXISTS `comp_pol_prem_view`;
/*!50001 DROP VIEW IF EXISTS `comp_pol_prem_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `comp_pol_prem_view` AS SELECT 
 1 AS `COMPANY`,
 1 AS `WARD_CATEGORY`,
 1 AS `WARD_TYPE`,
 1 AS `POLICY_NAME`,
 1 AS `AGE`,
 1 AS `PREMIUM_AMOUNT`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `comp_policies`
--

DROP TABLE IF EXISTS `comp_policies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comp_policies` (
  `comppolicy_id` int NOT NULL,
  `insrcomp_no` int NOT NULL,
  `policy_id` int NOT NULL,
  PRIMARY KEY (`comppolicy_id`),
  KEY `insrcomp_no` (`insrcomp_no`),
  KEY `policy_id` (`policy_id`),
  CONSTRAINT `comp_policies_ibfk_1` FOREIGN KEY (`insrcomp_no`) REFERENCES `insr_comp` (`insrcomp_no`),
  CONSTRAINT `comp_policies_ibfk_2` FOREIGN KEY (`policy_id`) REFERENCES `policies` (`policy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comp_policies`
--

LOCK TABLES `comp_policies` WRITE;
/*!40000 ALTER TABLE `comp_policies` DISABLE KEYS */;
INSERT INTO `comp_policies` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,2,5),(6,2,6),(7,2,7),(8,2,8),(9,3,9),(10,3,10),(11,3,11),(12,3,12),(13,4,13),(14,4,14),(15,4,15),(16,4,16),(17,5,17),(18,5,18),(19,5,19),(20,5,20),(21,5,21),(22,6,22),(23,6,23),(24,6,24),(25,6,25),(26,7,26),(27,7,27),(28,7,28),(29,7,29);
/*!40000 ALTER TABLE `comp_policies` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `comppol_prem`
--

DROP TABLE IF EXISTS `comppol_prem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comppol_prem` (
  `indexid` int NOT NULL AUTO_INCREMENT,
  `comppolicy_id` int NOT NULL,
  `age` int NOT NULL,
  `prem_amount` int NOT NULL,
  `ward_id` int NOT NULL,
  PRIMARY KEY (`indexid`),
  KEY `comppolicy_id` (`comppolicy_id`),
  CONSTRAINT `comppol_prem_ibfk_1` FOREIGN KEY (`comppolicy_id`) REFERENCES `comp_policies` (`comppolicy_id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comppol_prem`
--

LOCK TABLES `comppol_prem` WRITE;
/*!40000 ALTER TABLE `comppol_prem` DISABLE KEYS */;
INSERT INTO `comppol_prem` VALUES (1,4,20,382,5),(2,8,20,320,5),(3,12,20,331,5),(4,16,20,328,5),(5,21,20,322,5),(6,25,20,278,5),(7,29,20,324,5),(8,4,30,450,5),(9,8,30,450,5),(10,12,30,428,5),(11,16,30,420,5),(12,21,30,427,5),(13,25,30,368,5),(14,29,30,420,5),(15,4,40,700,5),(16,8,40,644,5),(17,12,40,600,5),(18,16,40,610,5),(19,21,40,720,5),(20,25,40,590,5),(21,29,40,650,5),(22,4,50,1200,5),(23,8,50,1200,5),(24,12,50,1078,5),(25,16,50,1030,5),(26,21,50,1350,5),(27,25,50,1030,5),(28,29,50,1060,5),(29,4,60,1800,5),(30,8,60,2000,5),(31,12,60,1700,5),(32,16,60,1900,5),(33,21,60,2113,5),(34,25,60,1600,5),(35,29,60,1700,5),(36,4,65,2300,5),(37,8,65,2700,5),(38,12,65,2300,5),(39,16,65,2500,5),(40,21,65,2700,5),(41,25,65,2060,5),(42,29,65,2300,5),(43,4,70,3000,5),(44,8,70,3750,5),(45,12,70,3000,5),(46,16,70,3500,5),(47,21,70,3500,5),(48,25,70,3000,5),(49,29,70,3050,5),(50,4,75,4500,5),(51,8,75,5300,5),(52,12,75,4500,5),(53,16,75,4900,5),(54,21,75,5300,5),(55,25,75,3890,5),(56,29,75,4300,5),(57,4,80,5600,5),(58,8,80,7500,5),(59,12,80,5500,5),(60,16,80,6600,5),(61,21,80,6700,5),(62,25,80,5080,5),(63,29,80,5900,5),(64,4,85,6700,5),(65,8,85,8200,5),(66,12,85,6600,5),(67,16,85,8800,5),(68,21,85,8500,5),(69,25,85,9500,5),(70,29,85,10620,5),(71,4,90,7900,5),(72,8,90,8400,5),(73,12,90,8000,5),(74,16,90,10214,5),(75,21,90,6300,5),(76,25,90,7600,5),(77,29,90,11395,5),(78,4,100,9800,5),(79,8,100,9700,5),(80,12,100,9000,5),(81,16,100,11289,5),(82,21,100,7900,5),(83,25,100,9020,5),(84,29,100,11040,5);
/*!40000 ALTER TABLE `comppol_prem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insr_comp`
--

DROP TABLE IF EXISTS `insr_comp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `insr_comp` (
  `insrcomp_no` int NOT NULL,
  `comp_name` varchar(30) NOT NULL,
  `claimproc_median` int DEFAULT NULL,
  PRIMARY KEY (`insrcomp_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insr_comp`
--

LOCK TABLES `insr_comp` WRITE;
/*!40000 ALTER TABLE `insr_comp` DISABLE KEYS */;
INSERT INTO `insr_comp` VALUES (1,'AIA',0),(2,'AVIVA',0),(3,'AXA',1),(4,'Great Eastern',0),(5,'Income',0),(6,'Prudential',0),(7,'Raffles Health Insurance',0);
/*!40000 ALTER TABLE `insr_comp` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `policy_features`
--

DROP TABLE IF EXISTS `policy_features`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `policy_features` (
  `policyfea_id` int NOT NULL AUTO_INCREMENT,
  `policyfea_sname` varchar(30) NOT NULL,
  `policyfea_fname` varchar(50) NOT NULL,
  `descr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`policyfea_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policy_features`
--

LOCK TABLES `policy_features` WRITE;
/*!40000 ALTER TABLE `policy_features` DISABLE KEYS */;
INSERT INTO `policy_features` VALUES (1,'Premium_Price','Price of premiums',NULL),(2,'Pre_Hosp_Covg','Pre-hospitalisation coverage',NULL),(3,'Post_Hosp_Covg','Post-hospitalisation coverage',NULL),(4,'Annual_Covg','Annual coverage limit',NULL),(5,'PreHospCovg_days','Pre-hospitalisation coverage days',NULL),(6,'PostHospCovg_days','Post-hospitalisation coverage days',NULL);
/*!40000 ALTER TABLE `policy_features` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ward_type`
--

DROP TABLE IF EXISTS `ward_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ward_type` (
  `ward_id` int NOT NULL AUTO_INCREMENT,
  `category` varchar(30) NOT NULL,
  `typ` varchar(10) NOT NULL,
  `descr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ward_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ward_type`
--

LOCK TABLES `ward_type` WRITE;
/*!40000 ALTER TABLE `ward_type` DISABLE KEYS */;
INSERT INTO `ward_type` VALUES (1,'Basic','B2C','equivalent or just slightly better than MediShield Life'),(2,'Standard','B1','coverage falls somewhere in between Basic and Class B1'),(3,'Class','B1','coverage for treatment in public hospital B1 wards'),(4,'Class','A','coverage for treatment in public hospital A wards'),(5,'Private','Private','coverage for treatment in private hospitals');
/*!40000 ALTER TABLE `ward_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `comp_pol_fea_view`
--

/*!50001 DROP VIEW IF EXISTS `comp_pol_fea_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `comp_pol_fea_view` AS select `w`.`category` AS `WARD_CATEGORY`,`w`.`typ` AS `WARD_TYPE`,`pf`.`policyfea_sname` AS `POLICY_FEATURE`,`c`.`comp_name` AS `COMPANY`,`p`.`policy_name` AS `POLICY_NAME`,`cpf`.`benefit_value` AS `BENEFITS`,`cpf`.`descr` AS `DESCRIPTION` from (((((`insr_comp` `c` join `ward_type` `w`) join `policy_features` `pf`) join `policies` `p`) join `comppol_features` `cpf`) join `comp_policies` `cp`) where ((`cpf`.`ward_id` = `w`.`ward_id`) and (`cpf`.`policyfea_id` = `pf`.`policyfea_id`) and (`cpf`.`comppolicy_id` = `cp`.`comppolicy_id`) and (`cp`.`policy_id` = `p`.`policy_id`) and (`cp`.`insrcomp_no` = `c`.`insrcomp_no`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `comp_pol_prem_view`
--

/*!50001 DROP VIEW IF EXISTS `comp_pol_prem_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `comp_pol_prem_view` AS select `c`.`comp_name` AS `COMPANY`,`w`.`category` AS `WARD_CATEGORY`,`w`.`typ` AS `WARD_TYPE`,`p`.`policy_name` AS `POLICY_NAME`,`cpp`.`age` AS `AGE`,`cpp`.`prem_amount` AS `PREMIUM_AMOUNT` from ((((`insr_comp` `c` join `ward_type` `w`) join `policies` `p`) join `comppol_prem` `cpp`) join `comp_policies` `cp`) where ((`cpp`.`ward_id` = `w`.`ward_id`) and (`cpp`.`comppolicy_id` = `cp`.`comppolicy_id`) and (`cp`.`policy_id` = `p`.`policy_id`) and (`cp`.`insrcomp_no` = `c`.`insrcomp_no`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-05  1:56:06
