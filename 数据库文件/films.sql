-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: films
-- ------------------------------------------------------
-- Server version	8.0.19

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

--
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `actor_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `film_id` int DEFAULT NULL,
  `comment_text` text,
  PRIMARY KEY (`id`),
  KEY `film_id` (`film_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`film_id`) REFERENCES `film` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direct`
--

DROP TABLE IF EXISTS `direct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direct` (
  `id` int NOT NULL AUTO_INCREMENT,
  `direct_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direct`
--

LOCK TABLES `direct` WRITE;
/*!40000 ALTER TABLE `direct` DISABLE KEYS */;
/*!40000 ALTER TABLE `direct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `film`
--

DROP TABLE IF EXISTS `film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `film` (
  `id` int NOT NULL AUTO_INCREMENT,
  `picname` varchar(255) DEFAULT NULL,
  `film_name` varchar(255) NOT NULL,
  `film_length` int DEFAULT NULL,
  `score` double DEFAULT NULL,
  `person_number` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film`
--

LOCK TABLES `film` WRITE;
/*!40000 ALTER TABLE `film` DISABLE KEYS */;
/*!40000 ALTER TABLE `film` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `film_actor_relation`
--

DROP TABLE IF EXISTS `film_actor_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `film_actor_relation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `film_id` int DEFAULT NULL,
  `actor_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `film_id` (`film_id`),
  KEY `actor_id` (`actor_id`),
  CONSTRAINT `film_actor_relation_ibfk_1` FOREIGN KEY (`film_id`) REFERENCES `film` (`id`),
  CONSTRAINT `film_actor_relation_ibfk_2` FOREIGN KEY (`actor_id`) REFERENCES `actor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film_actor_relation`
--

LOCK TABLES `film_actor_relation` WRITE;
/*!40000 ALTER TABLE `film_actor_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `film_actor_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `film_direct_relation`
--

DROP TABLE IF EXISTS `film_direct_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `film_direct_relation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `film_id` int DEFAULT NULL,
  `direct_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `film_id` (`film_id`),
  KEY `direct_id` (`direct_id`),
  CONSTRAINT `film_direct_relation_ibfk_1` FOREIGN KEY (`film_id`) REFERENCES `film` (`id`),
  CONSTRAINT `film_direct_relation_ibfk_2` FOREIGN KEY (`direct_id`) REFERENCES `direct` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film_direct_relation`
--

LOCK TABLES `film_direct_relation` WRITE;
/*!40000 ALTER TABLE `film_direct_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `film_direct_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `film_writer_relation`
--

DROP TABLE IF EXISTS `film_writer_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `film_writer_relation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `film_id` int DEFAULT NULL,
  `writer_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `film_id` (`film_id`),
  KEY `writer_id` (`writer_id`),
  CONSTRAINT `film_writer_relation_ibfk_1` FOREIGN KEY (`film_id`) REFERENCES `film` (`id`),
  CONSTRAINT `film_writer_relation_ibfk_2` FOREIGN KEY (`writer_id`) REFERENCES `writer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film_writer_relation`
--

LOCK TABLES `film_writer_relation` WRITE;
/*!40000 ALTER TABLE `film_writer_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `film_writer_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grade` (
  `id` int NOT NULL AUTO_INCREMENT,
  `star_one` double DEFAULT NULL,
  `star_two` double DEFAULT NULL,
  `star_three` double DEFAULT NULL,
  `star_four` double DEFAULT NULL,
  `star_five` double DEFAULT NULL,
  `film_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `film_id` (`film_id`),
  CONSTRAINT `grade_ibfk_1` FOREIGN KEY (`film_id`) REFERENCES `film` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade`
--

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `writer`
--

DROP TABLE IF EXISTS `writer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `writer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `writer_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `writer`
--

LOCK TABLES `writer` WRITE;
/*!40000 ALTER TABLE `writer` DISABLE KEYS */;
/*!40000 ALTER TABLE `writer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-16 16:02:19
