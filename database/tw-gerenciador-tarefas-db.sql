-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: tw_gerenciador_tarefas
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (2);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tar_tarefas`
--

DROP TABLE IF EXISTS `tar_tarefas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tar_tarefas` (
  `tar_id` int(11) NOT NULL AUTO_INCREMENT,
  `tar_titulo` varchar(50) NOT NULL,
  `tar_descricao` varchar(100) DEFAULT NULL,
  `tar_data_expiracao` date NOT NULL,
  `tar_concluida` bit(1) DEFAULT b'0',
  `usr_id` int(11) NOT NULL,
  PRIMARY KEY (`tar_id`),
  KEY `usr_id` (`usr_id`),
  CONSTRAINT `tar_tarefas_ibfk_1` FOREIGN KEY (`usr_id`) REFERENCES `usr_usuarios` (`usr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tar_tarefas`
--

LOCK TABLES `tar_tarefas` WRITE;
/*!40000 ALTER TABLE `tar_tarefas` DISABLE KEYS */;
INSERT INTO `tar_tarefas` VALUES (1,'Tarefa 1','Descricao da tarefa 2','2019-09-04','\0',2),(2,'tarefa4','Descricao da tarefa 2','2019-09-13','\0',2);
/*!40000 ALTER TABLE `tar_tarefas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usr_usuarios`
--

DROP TABLE IF EXISTS `usr_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usr_usuarios` (
  `usr_id` int(11) NOT NULL AUTO_INCREMENT,
  `usr_email` varchar(100) NOT NULL,
  `usr_senha` varchar(100) NOT NULL,
  PRIMARY KEY (`usr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usr_usuarios`
--

LOCK TABLES `usr_usuarios` WRITE;
/*!40000 ALTER TABLE `usr_usuarios` DISABLE KEYS */;
INSERT INTO `usr_usuarios` VALUES (1,'ayrton_pereira1996@hotmail.com','$2a$10$oRscz4mt6xzGkvA6b2ob8eB1hn/zUvGfnHEhQh8ni5SpPiQh6G8By'),(2,'teste@teste.com','$2a$10$gHObu4dZIM4VnILe6qrgYOaqHWk781brAzNnZ8kC/LexALJ.gY.p6'),(3,'teste2@teste.com','$2a$10$DBsGm9FgTaFe7aldiD2qE.4g63cJzP6aA7laaCLoycGYp7M0.DAeq');
/*!40000 ALTER TABLE `usr_usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-07 23:24:31
