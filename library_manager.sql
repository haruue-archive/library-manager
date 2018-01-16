-- MySQL dump 10.16  Distrib 10.1.30-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: library_manager
-- ------------------------------------------------------
-- Server version	10.1.30-MariaDB

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
-- Table structure for table `t_book`
--

DROP TABLE IF EXISTS `t_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_book` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(50) DEFAULT NULL,
  `isbn` char(13) DEFAULT NULL,
  `book_type` varchar(50) DEFAULT NULL,
  `book_publish_date` varchar(50) DEFAULT NULL,
  `book_publish_company` varchar(50) DEFAULT NULL,
  `book_buy_date` varchar(50) DEFAULT NULL,
  `book_status` int(11) DEFAULT NULL,
  `book_del` int(11) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_book`
--

LOCK TABLES `t_book` WRITE;
/*!40000 ALTER TABLE `t_book` DISABLE KEYS */;
INSERT INTO `t_book` VALUES (1,'了不起的盖茨比','9787532748983','文学','1970-01-01','上海译文出版社','1970-01-01',0,NULL),(3,'了不起的盖茨比','9787532748983','文学','2015/1/1','上海译文出版社','2015/12/1',1,1),(4,'理想国','9787100017565','哲学','2010-10-10','商务印书馆','2015-01-01',0,1),(5,'理想国','9787100017565','哲学','2010-10-10','商务印书馆','2015-01-01',1,1),(6,'算法导论','9787111407010','计算机','2013-07-01','机械工业出版社','2015-01-01',1,NULL),(7,'编译原理','9787111251217','计算机','2009-01-01','机械工业出版社','2017-01-01',1,NULL),(8,'如何阅读一本书','9787100040945','文学','2004-01-01','商务印书馆','2017-01-01',1,NULL),(9,'经济学原理（上册）','9787301256909','经济学','2015-05-01','北京大学出版社','2017-01-01',1,1),(10,'经济学原理（下册）','9787301256886','经济学','2015-05-01','北京大学出版社','2017-01-01',1,NULL),(11,'国民财富的性质和原因的研究（上卷）','9787100005005','经济学','1972-12-01','商务印书馆','2010-01-01',0,NULL),(12,'国民财富的性质和原因的研究（下卷）','9787100005012','经济学','2002-11-01','商务印书馆','2010-01-01',0,NULL),(13,'如何合理避账','2333233123456','经济','2008-01-01','重庆邮电大学财务处','2017-01-01',0,NULL),(14,'积极性打压技巧与提升','7417417474741','教育','2018-01-01','重庆邮电大学教务处','2018-01-01',0,NULL),(15,'测试书籍','1234567890123','科学','2012-12-12','袁八戒出版公司','2017-02-01',0,NULL);
/*!40000 ALTER TABLE `t_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_note`
--

DROP TABLE IF EXISTS `t_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_note` (
  `note_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `note_date` varchar(50) DEFAULT NULL,
  `note_deadline` varchar(50) DEFAULT NULL,
  `note_status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`note_id`),
  KEY `FK_T_NOTE_REFERENCE_T_BOOK` (`book_id`),
  KEY `FK_T_NOTE_REFERENCE_T_READER` (`user_id`),
  CONSTRAINT `FK_T_NOTE_REFERENCE_T_BOOK` FOREIGN KEY (`book_id`) REFERENCES `t_book` (`book_id`),
  CONSTRAINT `FK_T_NOTE_REFERENCE_T_READER` FOREIGN KEY (`user_id`) REFERENCES `t_reader` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_note`
--

LOCK TABLES `t_note` WRITE;
/*!40000 ALTER TABLE `t_note` DISABLE KEYS */;
INSERT INTO `t_note` VALUES (1,1,1,'2017-12-01','2018-01-02',0),(2,2,3,'2015-12-01','2016-01-01',0),(3,4,4,'2017-01-01','2017-02-01',0),(4,2,6,'2017-12-15','2018-01-15',0),(5,2,3,'2015-01-01','2015-03-01',0);
/*!40000 ALTER TABLE `t_note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_reader`
--

DROP TABLE IF EXISTS `t_reader`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_reader` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `user_email` varchar(50) DEFAULT NULL,
  `user_tel` varchar(50) DEFAULT NULL,
  `user_del` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_reader`
--

LOCK TABLES `t_reader` WRITE;
/*!40000 ALTER TABLE `t_reader` DISABLE KEYS */;
INSERT INTO `t_reader` VALUES (1,'alan','123@gmail.com','10086',1),(2,'haruue','i@haruue.moe','15377986758',NULL),(3,'Olivia','olivia@example.com','15377986758',NULL),(4,'Excaburay','abc@gmail.com','13625177700',NULL);
/*!40000 ALTER TABLE `t_reader` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-16 21:50:17
