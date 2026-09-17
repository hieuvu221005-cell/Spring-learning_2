-- MySQL dump 10.13  Distrib 9.7.0, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: products
-- ------------------------------------------------------
-- Server version	9.7.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ 'faed4092-4e31-11f1-b7dc-1c8549dc74b1:1-398';

--
-- Table structure for table `product_detail`
--

DROP TABLE IF EXISTS `product_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `feature` varchar(255) DEFAULT NULL,
  `power` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `weight` mediumtext,
  `image` varchar(255) DEFAULT NULL,
  `video` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_detail`
--

LOCK TABLES `product_detail` WRITE;
/*!40000 ALTER TABLE `product_detail` DISABLE KEYS */;
INSERT INTO `product_detail` VALUES (1,'feature_1','1000','brand_1','10','anh_1','video_1'),(2,'feature_2','1000','brand_2','20','anh_2','video_2'),(3,'feature_3','1000','brand_3','30','anh_3','video_3'),(4,'feature_4','1000','brand_4','40','anh_4','video_4'),(21,'Tiết kiệm điện','1500','Samsung','50','link_hinh_anh.jpg','link_video.mp4'),(22,'Tiết kiệm điện','1500','Samsung','50','https://hinh_anh.jpg','https://video.mp4'),(23,'Tiết kiệm điện','1500','Samsung','50','https://hinh_anh.jpg','https://video.mp4');
/*!40000 ALTER TABLE `product_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_offering_detail`
--

DROP TABLE IF EXISTS `product_offering_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_offering_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_offering_id` int NOT NULL,
  `product_detail_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `product_offering_id` (`product_offering_id`),
  KEY `product_detail_id` (`product_detail_id`),
  CONSTRAINT `product_offering_detail_ibfk_1` FOREIGN KEY (`product_offering_id`) REFERENCES `product_offerings` (`id`),
  CONSTRAINT `product_offering_detail_ibfk_2` FOREIGN KEY (`product_detail_id`) REFERENCES `product_detail` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_offering_detail`
--

LOCK TABLES `product_offering_detail` WRITE;
/*!40000 ALTER TABLE `product_offering_detail` DISABLE KEYS */;
INSERT INTO `product_offering_detail` VALUES (1,1,1),(2,2,1),(3,2,2),(4,3,1),(5,3,2),(6,2,3),(7,1,2),(8,1,3),(9,10,4);
/*!40000 ALTER TABLE `product_offering_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_offerings`
--

DROP TABLE IF EXISTS `product_offerings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_offerings` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `price` mediumtext,
  `color` varchar(255) DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_offerings`
--

LOCK TABLES `product_offerings` WRITE;
/*!40000 ALTER TABLE `product_offerings` DISABLE KEYS */;
INSERT INTO `product_offerings` VALUES (1,'product_1','1000','red_1',NULL),(2,'product_2','1000','red_2',NULL),(3,'product_3','1000','red_3',NULL),(4,'product_4','1000','red_4',NULL),(5,'product_5','1000','red_5',NULL),(6,'product_6','1000','red_6',NULL),(7,'product_7','1000','red_7','ACTIVE'),(8,'product_8','1000','red_8','ACTIVE'),(9,'product_9','1000','red_9','ACTIVE'),(10,'product_10','1000','red_10','ACTIVE'),(11,'product_11','1000','red_11','ACTIVE'),(12,'product_12','1000','red_12','ACTIVE'),(13,'product_13','1000','red_13','ACTIVE'),(14,'product_14','1000','red_14','ACTIVE'),(15,'product_15','1000','red_15','ACTIVE'),(16,'product_16','1000','red_16','ACTIVE'),(17,'product_17','1000','red_17','ACTIVE'),(18,'product_18','1000','red_18','ACTIVE'),(19,'product_19','1000','red_19','ACTIVE'),(20,'product_20','1000','red_20','ACTIVE'),(21,'product_21','1000','red_21','ACTIVE'),(22,'product_22','1000','red_22','ACTIVE'),(23,'product_39','634738','red_39','INACTIVE'),(24,'product_24','1000','red_24','ACTIVE'),(25,'product_25','1000','red_25','ACTIVE'),(26,'product_26','1000','red_26','ACTIVE'),(27,'product_27','1000','red_27','ACTIVE'),(28,'product_28','1000','red_28','ACTIVE'),(29,'product_29','1000','red_29','ACTIVE'),(30,'product_30','1000','red_30','ACTIVE'),(31,'product_31','1000','red_31','ACTIVE'),(34,'product_34','1000','red_34','ACTIVE'),(35,'product_35','1000','red_35','ACTIVE'),(36,'product_36','1000','red_36','ACTIVE'),(37,'product_37','6438349','red_37','ACTIVE'),(38,'product_37','6438349','red_37','ACTIVE'),(39,'product_39','634738','red_39','ACTIVE'),(40,'iPhone 17 Pro','25000000','red_40','ACTIVE'),(41,'iPhone 16 Pro','26000000','red_41','ACTIVE'),(42,'iPhone 15 Pro','27000000','red_42','ACTIVE'),(43,'iPhone 14 Pro','28000000','red_43','ACTIVE'),(44,'iPhone 13 Pro','25000000','red_44','ACTIVE'),(45,'iPhone 12 Pro','27000000','red_45','ACTIVE'),(46,'iPhone 11 Pro','26000000','red_46','ACTIVE'),(47,'iPhone X Pro','28000000','red_47','ACTIVE'),(48,'iPhone XS Pro','23000000','red_48','ACTIVE'),(49,'product_155','1000','red_155','ACTIVE'),(50,'product_155','1000','red_155','ACTIVE'),(51,'product_800','100009','red_','ACTIVE');
/*!40000 ALTER TABLE `product_offerings` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-17 12:18:42
