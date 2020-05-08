-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: recommendersys
-- ------------------------------------------------------
-- Server version	8.0.19
drop database  recommendersys IF EXISTS `recommendersys`;
create database  recommendersys;
use recommendersys;


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
  KEY `ward_id` (`ward_id`),
  CONSTRAINT `comppol_features_ibfk_1` FOREIGN KEY (`comppolicy_id`) REFERENCES `comp_policies` (`comppolicy_id`),
  CONSTRAINT `comppol_features_ibfk_2` FOREIGN KEY (`policyfea_id`) REFERENCES `policy_features` (`policyfea_id`),
  CONSTRAINT `comppol_features_ibfk_3` FOREIGN KEY (`ward_id`) REFERENCES `ward_type` (`ward_id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comppol_features`
--

LOCK TABLES `comppol_features` WRITE;
/*!40000 ALTER TABLE `comppol_features` DISABLE KEYS */;
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (1,4,2,1,'As Charged',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (2,8,2,1,'As Charged',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (3,12,2,1,'As Charged',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (4,16,2,1,'As Charged',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (5,21,2,1,'As Charged',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (6,25,2,1,'As Charged',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (7,29,2,1,'As Charged',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (8,4,5,100,'Number of Days',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (9,8,5,100,'Number of Days',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (10,12,5,120,'Number of Days',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (11,16,5,180,'Number of Days',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (12,21,5,90,'Number of Days',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (13,25,5,180,'Number of Days',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (14,29,5,180,'Number of Days',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (15,4,3,1,'As Charged',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (16,8,3,1,'As Charged',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (17,12,3,1,'As Charged',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (18,16,3,1,'As Charged',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (19,21,3,1,'As Charged',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (20,25,3,1,'As Charged',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (21,29,3,1,'As Charged',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (22,4,6,100,'Number of Days',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (23,8,6,100,'Number of Days',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (24,12,6,120,'Number of Days',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (25,16,6,365,'Number of Days',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (26,21,6,90,'Number of Days',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (27,25,6,365,'Number of Days',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (28,29,6,365,'Number of Days',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (29,4,4,1500000,'Annual Coverage Limit',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (30,8,4,2000000,'Annual Coverage Limit',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (31,12,4,1500000,'Annual Coverage Limit',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (32,16,4,1200000,'Annual Coverage Limit',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (33,21,4,1000000,'Annual Coverage Limit',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (34,25,4,1000000,'Annual Coverage Limit',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (35,29,4,600000,'Annual Coverage Limit',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (64,4,7,5,'CoInsurance Percentage Payment',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (65,8,7,8,'CoInsurance Percentage Payment',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (66,12,7,15,'CoInsurance Percentage Payment',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (67,16,7,12,'CoInsurance Percentage Payment',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (68,21,7,18,'CoInsurance Percentage Payment',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (69,25,7,20,'CoInsurance Percentage Payment',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (70,29,7,11,'CoInsurance Percentage Payment',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (71,4,8,2000,'Deductible Payment',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (72,8,8,2500,'Deductible Payment',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (73,12,8,3500,'Deductible Payment',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (74,16,8,3000,'Deductible Payment',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (75,21,8,4000,'Deductible Payment',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (76,25,8,4500,'Deductible Payment',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (77,29,8,5000,'Deductible Payment',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (78,4,9,5,'Percentage of Non Panel Surcharge',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (79,8,9,2,'Percentage of Non Panel Surcharge',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (80,12,9,4,'Percentage of Non Panel Surcharge',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (81,16,9,5,'Percentage of Non Panel Surcharge',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (82,21,9,6,'Percentage of Non Panel Surcharge',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (83,25,9,2,'Percentage of Non Panel Surcharge',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (84,29,9,1,'Percentage of Non Panel Surcharge',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (85,4,10,0,'CoPayCappedAt Max Amount',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (86,8,10,3000,'CoPayCappedAt Max Amount',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (87,12,10,0,'CoPayCappedAt Max Amount',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (88,16,10,3000,'CoPayCappedAt Max Amount',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (89,21,10,0,'CoPayCappedAt Max Amount',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (90,25,10,3000,'CoPayCappedAt Max Amount',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (91,29,10,0,'CoPayCappedAt Max Amount',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (92,4,11,45,'CommunityHospital',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (93,8,11,50,'CommunityHospital',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (94,12,11,45,'CommunityHospital',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (95,16,11,80,'CommunityHospital',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (96,21,11,90,'CommunityHospital',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (97,25,11,75,'CommunityHospital',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (98,29,11,85,'CommunityHospital',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (99,4,12,50000,'Surgery Coverage',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (100,8,12,50000,'Surgery Coverage',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (101,12,12,50000,'Surgery Coverage',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (102,16,12,17000,'Surgery Coverage',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (103,21,12,14000,'Surgery Coverage',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (104,25,12,50000,'Surgery Coverage',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (105,29,12,50000,'Surgery Coverage',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (106,4,13,80,'Major Organ Transplant Coverage',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (107,8,13,70,'Major Organ Transplant Coverage',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (108,12,13,100,'Major Organ Transplant Coverage',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (109,16,13,10,'Major Organ Transplant Coverage',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (110,21,13,50,'Major Organ Transplant Coverage',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (111,25,13,70,'Major Organ Transplant Coverage',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (112,29,13,100,'Major Organ Transplant Coverage',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (113,4,14,1,'Claims Processing Duration',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (114,8,14,1,'Claims Processing Duration',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (115,12,14,3,'Claims Processing Duration',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (116,16,14,2,'Claims Processing Duration',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (117,21,14,1,'Claims Processing Duration',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (118,25,14,0,'Claims Processing Duration',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (119,29,14,5,'Claims Processing Duration',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (120,4,15,10000,'Critical Illnesses Coverage',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (121,8,15,15000,'Critical Illnesses Coverage',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (122,12,15,0,'Critical Illnesses Coverage',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (123,16,15,0,'Critical Illnesses Coverage',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (124,21,15,0,'Critical Illnesses Coverage',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (125,25,15,0,'Critical Illnesses Coverage',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (126,29,15,0,'Critical Illnesses Coverage',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (127,4,16,100,'Emergency Overseas Treatment',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (128,8,16,100,'Emergency Overseas Treatment',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (129,12,16,100,'Emergency Overseas Treatment',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (130,16,16,100,'Emergency Overseas Treatment',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (131,21,16,75,'Emergency Overseas Treatment',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (132,25,16,75,'Emergency Overseas Treatment',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (133,29,16,100,'Emergency Overseas Treatment',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (134,4,17,25000,'Prosthesis',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (135,8,17,25000,'Prosthesis',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (136,12,17,25000,'Prosthesis',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (137,16,17,25000,'Prosthesis',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (138,21,17,20000,'Prosthesis',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (139,25,17,10000,'Prosthesis',5);
INSERT INTO `comppol_features` (`indexid`,`comppolicy_id`,`policyfea_id`,`benefit_value`,`descr`,`ward_id`) VALUES (140,29,17,25000,'Prosthesis',5);
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
  KEY `ward_id` (`ward_id`),
  CONSTRAINT `comppol_prem_ibfk_1` FOREIGN KEY (`comppolicy_id`) REFERENCES `comp_policies` (`comppolicy_id`),
  CONSTRAINT `comppol_prem_ibfk_2` FOREIGN KEY (`ward_id`) REFERENCES `ward_type` (`ward_id`),
  CONSTRAINT `comppol_prem_ibfk_3` FOREIGN KEY (`ward_id`) REFERENCES `ward_type` (`ward_id`),
  CONSTRAINT `comppol_prem_ibfk_4` FOREIGN KEY (`ward_id`) REFERENCES `ward_type` (`ward_id`)
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policy_features`
--

LOCK TABLES `policy_features` WRITE;
/*!40000 ALTER TABLE `policy_features` DISABLE KEYS */;
INSERT INTO `policy_features` (`policyfea_id`,`policyfea_sname`,`policyfea_fname`,`descr`) VALUES (1,'Premium_Price','Price of premiums',NULL);
INSERT INTO `policy_features` (`policyfea_id`,`policyfea_sname`,`policyfea_fname`,`descr`) VALUES (2,'Pre_Hosp_Covg','Pre-hospitalisation coverage',NULL);
INSERT INTO `policy_features` (`policyfea_id`,`policyfea_sname`,`policyfea_fname`,`descr`) VALUES (3,'Post_Hosp_Covg','Post-hospitalisation coverage',NULL);
INSERT INTO `policy_features` (`policyfea_id`,`policyfea_sname`,`policyfea_fname`,`descr`) VALUES (4,'Annual_Covg','Annual coverage limit',NULL);
INSERT INTO `policy_features` (`policyfea_id`,`policyfea_sname`,`policyfea_fname`,`descr`) VALUES (5,'PreHospCovg_days','Pre-hospitalisation coverage days',NULL);
INSERT INTO `policy_features` (`policyfea_id`,`policyfea_sname`,`policyfea_fname`,`descr`) VALUES (6,'PostHospCovg_days','Post-hospitalisation coverage days',NULL);
INSERT INTO `policy_features` (`policyfea_id`,`policyfea_sname`,`policyfea_fname`,`descr`) VALUES (7,'CoInsurance','Amount to pay',NULL);
INSERT INTO `policy_features` (`policyfea_id`,`policyfea_sname`,`policyfea_fname`,`descr`) VALUES (8,'Deductible','Amount to pay',NULL);
INSERT INTO `policy_features` (`policyfea_id`,`policyfea_sname`,`policyfea_fname`,`descr`) VALUES (9,'NonPanelSurcharge','Non Panel Percentage payment ',NULL);
INSERT INTO `policy_features` (`policyfea_id`,`policyfea_sname`,`policyfea_fname`,`descr`) VALUES (10,'CoPayCappedAt','Co-Payment capped for Panel',NULL);
INSERT INTO `policy_features` (`policyfea_id`,`policyfea_sname`,`policyfea_fname`,`descr`) VALUES (11,'CommunityHospital','Community hospital',NULL);
INSERT INTO `policy_features` (`policyfea_id`,`policyfea_sname`,`policyfea_fname`,`descr`) VALUES (12,'Surgery_Covg','Surgery coverage',NULL);
INSERT INTO `policy_features` (`policyfea_id`,`policyfea_sname`,`policyfea_fname`,`descr`) VALUES (13,'MajorOrganTransplant_Covg','Major Organ Transplant Coverage',NULL);
INSERT INTO `policy_features` (`policyfea_id`,`policyfea_sname`,`policyfea_fname`,`descr`) VALUES (14,'ClaimsProcessingDuration','Claims Processing Duration',NULL);
INSERT INTO `policy_features` (`policyfea_id`,`policyfea_sname`,`policyfea_fname`,`descr`) VALUES (15,'CriticalIllnesses_Covg','Critical Illnesses Coverage',NULL);
INSERT INTO `policy_features` (`policyfea_id`,`policyfea_sname`,`policyfea_fname`,`descr`) VALUES (16,'EmergencyOverseasTreatment','Emergency Overseas Treatment',NULL);
INSERT INTO `policy_features` (`policyfea_id`,`policyfea_sname`,`policyfea_fname`,`descr`) VALUES (17,'Prosthesis','Prosthesis',NULL);
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
  `typ` varchar(30) NOT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `maxbeds` int DEFAULT NULL,
  PRIMARY KEY (`ward_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ward_type`
--

LOCK TABLES `ward_type` WRITE;
/*!40000 ALTER TABLE `ward_type` DISABLE KEYS */;
INSERT INTO `ward_type` VALUES (1,'Class C','Govt_Restruc','equivalent or just slightly better than MediShield Life',9),(2,'Class B2','Govt_Restruc','coverage falls somewhere in between Basic and Class B1',6),(3,'Class B1','Govt_Restruc','coverage for treatment in public hospital B1 wards',4),(4,'Class A','Govt_Restruc','coverage for treatment in public hospital A wards',1),(5,'Private Hospital','Private','coverage for treatment in private hospitals',1);
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
/*!50001 VIEW `comp_pol_fea_view` AS select `w`.`category` AS `WARD_CATEGORY`,`w`.`typ` AS `WARD_TYPE`, concat(`pf`.`policyfea_sname`,'_',`p`.`policy_id`) as  `POLICY_FEATURE_ID`,`pf`.`policyfea_sname` AS `POLICY_FEATURE`,`c`.`comp_name` AS `COMPANY`,`p`.`policy_name` AS `POLICY_NAME`,`cpf`.`benefit_value` AS `BENEFITS`,`cpf`.`descr` AS `DESCRIPTION` from (((((`insr_comp` `c` join `ward_type` `w`) join `policy_features` `pf`) join `policies` `p`) join `comppol_features` `cpf`) join `comp_policies` `cp`) where ((`cpf`.`ward_id` = `w`.`ward_id`) and (`cpf`.`policyfea_id` = `pf`.`policyfea_id`) and (`cpf`.`comppolicy_id` = `cp`.`comppolicy_id`) and (`cp`.`policy_id` = `p`.`policy_id`) and (`cp`.`insrcomp_no` = `c`.`insrcomp_no`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

---
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
/*!50001 VIEW `comp_pol_prem_view` AS select `c`.`comp_name` AS `COMPANY`,`w`.`category` AS `WARD_CATEGORY`,`w`.`typ` AS `WARD_TYPE`,concat(`p`.`policy_id`,'_',`cpp`.`age`) AS `POLICY_ID_AGE`, `p`.`policy_name` AS `POLICY_NAME`,`cpp`.`age` AS `AGE`,`cpp`.`prem_amount` AS `PREMIUM_AMOUNT` from ((((`insr_comp` `c` join `ward_type` `w`) join `policies` `p`) join `comppol_prem` `cpp`) join `comp_policies` `cp`) where ((`cpp`.`ward_id` = `w`.`ward_id`) and (`cpp`.`comppolicy_id` = `cp`.`comppolicy_id`) and (`cp`.`policy_id` = `p`.`policy_id`) and (`cp`.`insrcomp_no` = `c`.`insrcomp_no`)) */;
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

DROP TABLE `recommendersys`.`question`;

CREATE TABLE `recommendersys`.`question` (
`id` INT NOT NULL,
`name` VARCHAR(100) NULL,
`value` VARCHAR(100) NULL,
`stage` INT NULL,
`extra_data` VARCHAR(450) NULL);

INSERT INTO `question` (`id`,`name`,`value`,`stage`,`extra_data`) VALUES (0,'Invalid Question','Invalid Request',0,'[{\"\",\"\"}]');
INSERT INTO `question` (`id`,`name`,`value`,`stage`,`extra_data`) VALUES (1,'[1/6] What type of Hospital would you prefer?','HospitalType',1,'[{\"GOVT_HOSPITAL\":\"Govt & Restructured Hospitals\"},{\"PVT_HOSPITAL\":\"Private Hospitals\"}]');
INSERT INTO `question` (`id`,`name`,`value`,`stage`,`extra_data`) VALUES (2,'[2/6] What type of Ward would you prefer?','WardType',1,'[{\"PRIVATE\":\"Private (Single-Bedded)\"},{\"CLASS_A\":\"Class A (2-Bedded)\"},{\"Class_B1\":\" B1 (4-Bedded)\"},{\"LOWER\":\"Standard/Basic (4-9 Bedded)\"}]');
INSERT INTO `question` (`id`,`name`,`value`,`stage`,`extra_data`) VALUES (3,'[3/6] Do you have any pre-exisiting health conditions?','PreExistingCond',1,'[{\"TRUE\":\"Yes\"},{\"FALSE\":\"No\"}]');
INSERT INTO `question` (`id`,`name`,`value`,`stage`,`extra_data`) VALUES (4,'[4/6] Do you work for any hazardious profession?','HighRiskProf',1,'[{\"TRUE\":\"Yes\"},{\"FALSE\":\"No\"}]');
INSERT INTO `question` (`id`,`name`,`value`,`stage`,`extra_data`) VALUES (5,'[5/6] Are you smoker or drinker?','HighRiskHealth',1,'[{\"TRUE\":\"Yes\"},{\"FALSE\":\"No\"}]');
INSERT INTO `question` (`id`,`name`,`value`,`stage`,`extra_data`) VALUES (6,'[6/6] Are you covered under any other/employer insurence?','AdditionalCover',1,'[{\"TRUE\":\"Yes\"},{\"FALSE\":\"No\"}]');
INSERT INTO `question` (`id`,`name`,`value`,`stage`,`extra_data`) VALUES (7,'Would you be interested in providing other preferences?','Preference',1,'[{\"TRUE\":\"Yes\"},{\"FALSE\":\"No\"}]');
INSERT INTO `question` (`id`,`name`,`value`,`stage`,`extra_data`) VALUES (8,'[1/15] How much premium would you like to pay above medisafe payment?','Premium',2,'POST_FIX: SGD');
INSERT INTO `question` (`id`,`name`,`value`,`stage`,`extra_data`) VALUES (9,'[2/15] How much percentage of co-payment you would like to bear?','CoInsurance',2,'[{\"5\":\"5%\"},{\"10\":\"10%\"},{\"15\":\"15%\"},{\"20\":\"20% \"}]');
INSERT INTO `question` (`id`,`name`,`value`,`stage`,`extra_data`) VALUES (10,'[3/15] How much of the max co-insurence would you like to pay?','CoPayCappedAt',2,'[{\"0\":\"0 SGD\"},{\"1000\":\"1000 SGD\"},{\"2000\":\"2000 SGD\"},{\"3000\":\"3000 SGD\"}]');
INSERT INTO `question` (`id`,`name`,`value`,`stage`,`extra_data`) VALUES (11,'[4/15] How much annual deductibles you like to pay?','Deductible',2,'[{\"2000\":\"2000 SGD\"},{\"3000\":\"3000 SGD\"},{\"4000\":\"4000 SGD\"},{\"5000\":\"5000 SGD\"}]');
INSERT INTO `question` (`id`,`name`,`value`,`stage`,`extra_data`) VALUES (12,'[5/15] How much min pre-hospitalization coverage necessary?','PreHospCovg_days',2,'[{\"90\":\"3 Months\"},{\"120\":\"4 Months\"},{\"180\":\"6 Months\"}]');
INSERT INTO `question` (`id`,`name`,`value`,`stage`,`extra_data`) VALUES (13,'[6/15] How much min post-hospitalization coverage necessary?','PostHospCovg_days',2,'[{\"90\":\"3 Months\"},{\"120\":\"4 Months\"},{\"180\":\"6 Months\"},{\"365\":\"1 Year\"}]');
INSERT INTO `question` (`id`,`name`,`value`,`stage`,`extra_data`) VALUES (14,'[7/15] How much min annual coverage would you like to have?','Annual_Covg',2,'[{\"600000\":\"600K or less\"},{\"1000000\":\"600K to 1 Million\"},{\"1200000\":\"1 Million to 1.2 Million\"},{\"1500000\":\"1 Million to 1.5 Million\"},{\"2000000\":\"1.5 Million or above\"}]');
INSERT INTO `question` (`id`,`name`,`value`,`stage`,`extra_data`) VALUES (15,'[8/15] How much non-panel surcharge is acceptible?','NonPanelSurcharge',2,'POST_FIX: x1000 SGD');
INSERT INTO `question` (`id`,`name`,`value`,`stage`,`extra_data`) VALUES (16,'[9/15] How much min community hospital coverage necessary?','CommunityHospital',2,'[{\"45\":\"45 Days or Below\"},{\"60\":\"60 Days\"},{\"75\":\"75 Days\"},{\"90\":\"90 Days\"}]');
INSERT INTO `question` (`id`,`name`,`value`,`stage`,`extra_data`) VALUES (17,'[10/15] How much surgery coverage necessary?','Surgery_Covg',2,'[{\"14000\":\"15K SGD or less\"},{\"16000\":\"16K ~ 20K SGD\"},{\"21000\":\"21K ~ 45K SGD\"},{\"45000\":\"45K SGD or above\"}]');
INSERT INTO `question` (`id`,`name`,`value`,`stage`,`extra_data`) VALUES (18,'[11/15] How much organ transplant coverage necessary?','MajorOrganTransplant_Covg',2,'[{\"60\":\" Selected organs are covered\"},{\"90\":\"No limitation\"}]');
INSERT INTO `question` (`id`,`name`,`value`,`stage`,`extra_data`) VALUES (19,'[12/15] How much add on to policy year limit for critical illnesses coverage necessary?','CriticalIllnesses_Covg',2,'[{\"0\":\"9K or less\"},{\"10000\":\"10K ~ 14K SGD\"}, {\"15000\":\"15K or above\"}]');
INSERT INTO `question` (`id`,`name`,`value`,`stage`,`extra_data`) VALUES (20,'[13/15] How much emergency overseas treatment coverage necessary?','EmergencyOverseasTreatment',2,'[{\"75\":\"Limited to policy Day coverage\"},{\"90\":\" Limited to Singapore Private Hospital charges\"}]');
INSERT INTO `question` (`id`,`name`,`value`,`stage`,`extra_data`) VALUES (21,'[14/15] How much prosthesis benefit per year necessary?','Prosthesis',2,'[{\"10000\":\"10K ~ 19K\"},{\"20000\":\"20K ~ 24K\"},{\"25000\":\"25K or above\"}]');
INSERT INTO `question` (`id`,`name`,`value`,`stage`,`extra_data`) VALUES (22,'[15/15] How long claims processing duration is acceptible?','ClaimsProcessingDuration',2,'[{\"0\":\"Same day\"},{\"1\":\"1 Day\"},{\"2\":\"2 Days\"},{\"3\":\"3 Days\"},{\"4\":\"4 Days or above\"}]');


DROP TABLE `recommendersys`.`user_feedback`;

CREATE TABLE `recommendersys`.`user_feedback` (
`id` INT NOT NULL,
`name` VARCHAR(30) NULL,
`age` INT NULL,
`gender` CHAR(6) NULL,
`status` CHAR(15) NULL,
`rating` INT NULL,
`comments` VARCHAR(100) NULL
)
;

INSERT INTO `recommendersys`.`user_feedback`
(id,name,age,gender,status,rating,comments) VALUES
(0,'Amanda Brussels',23,'Female','Singaporean',5,'it is a good application'),
(1,'Tom Hawks',25,'Male','Singapore PR',3,'it is an ok application'),
(2,'Harry Hawks',55,'Male','Singapore PR',4,'it is a fine application')
;
