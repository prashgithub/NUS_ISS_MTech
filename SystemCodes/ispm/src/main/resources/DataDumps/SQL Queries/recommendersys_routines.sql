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

-- Dump completed on 2020-04-05  1:51:58
