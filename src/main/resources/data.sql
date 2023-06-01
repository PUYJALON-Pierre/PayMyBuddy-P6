-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           8.0.31 - MySQL Community Server - GPL
-- SE du serveur:                Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Listage de la structure de la base pour pay_my_buddy
CREATE DATABASE IF NOT EXISTS `pay_my_buddy` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pay_my_buddy`;

-- Listage de la structure de table pay_my_buddy. app_account
CREATE TABLE IF NOT EXISTS `app_account` (
  `app_account_id` int NOT NULL AUTO_INCREMENT,
  `balance` double DEFAULT NULL,
  PRIMARY KEY (`app_account_id`)
) ENGINE=MyISAM AUTO_INCREMENT=160 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table pay_my_buddy.app_account : 10 rows
/*!40000 ALTER TABLE `app_account` DISABLE KEYS */;
INSERT INTO `app_account` (`app_account_id`, `balance`) VALUES
	(1, 129.3),
	(2, 39.65),
	(3, 69.6),
	(4, 49.7),
	(5, 14.95),
	(6, 44.725),
	(7, 995),
	(8, 547.45),
	(9, 69.75),
	(10, 9.75);
/*!40000 ALTER TABLE `app_account` ENABLE KEYS */;

-- Listage de la structure de table pay_my_buddy. deposit
CREATE TABLE IF NOT EXISTS `deposit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `currency` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `fee` double DEFAULT NULL,
  `bank_account_iban` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `source_user` int NOT NULL,
  `operator` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr6332ul87j3osb8sw9kmmlcrv` (`source_user`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table pay_my_buddy.deposit : 10 rows
/*!40000 ALTER TABLE `deposit` DISABLE KEYS */;
INSERT INTO `deposit` (`id`, `amount`, `currency`, `date`, `description`, `fee`, `bank_account_iban`, `source_user`, `operator`) VALUES
	(1, 120, '$', '2020-04-27 17:39:57', NULL, 0.005, 'FR332500000550', 1, '+'),
	(2, 50, '$', '2020-04-27 17:43:13', 'deposit', 0.005, 'BE784525468541', 2, '+'),
	(3, 70, '$', '2020-01-27 17:43:13', NULL, 0.005, 'FR875155633344', 3, '+'),
	(4, 50, '$', '2020-01-27 17:43:13', NULL, 0.005, 'FR784520943314', 4, '+'),
	(5, 10, '$', '2020-01-27 17:43:13', NULL, 0.005, 'FR365128963344', 5, '+'),
	(6, 50, '$', '2021-04-27 17:52:02', NULL, 0.005, 'FR441596532547', 6, '+'),
	(7, 1000, '$', '2021-07-27 17:52:02', 'deposit', 0.005, 'FR778596345824', 7, '+'),
	(8, 550, '$', '2019-07-27 17:52:02', 'start', 0.005, 'FR115962465638', 8, '+'),
	(9, 50, '$', '2021-07-17 17:52:02', 'first deposit', 0.005, 'FR236154258612', 9, '+'),
	(10, 30, '$', '2020-03-27 17:52:02', 'first deposit', 0.005, 'FR236963695412', 10, '+');
/*!40000 ALTER TABLE `deposit` ENABLE KEYS */;

-- Listage de la structure de table pay_my_buddy. friend
CREATE TABLE IF NOT EXISTS `friend` (
  `user_id` int NOT NULL,
  `friend_id` int NOT NULL,
  KEY `FKf9qk6e95h3da7o1u83ihtwrqa` (`friend_id`),
  KEY `FK3uu8s7yyof1qmenthngm24hry` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table pay_my_buddy.friend : 10 rows
/*!40000 ALTER TABLE `friend` DISABLE KEYS */;
INSERT INTO `friend` (`user_id`, `friend_id`) VALUES
	(1, 2),
	(1, 8),
	(2, 1),
	(3, 4),
	(4, 3),
	(5, 6),
	(6, 5),
	(8, 1),
	(9, 10),
	(10, 9);
/*!40000 ALTER TABLE `friend` ENABLE KEYS */;

-- Listage de la structure de table pay_my_buddy. transfert
CREATE TABLE IF NOT EXISTS `transfert` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `currency` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `fee` double DEFAULT NULL,
  `source_user` int NOT NULL,
  `user_recipient` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt7fx6f3rx9hrsjrwr545q7ncn` (`source_user`),
  KEY `FK4ufo83wxaevhnc56tv2sxrpgt` (`user_recipient`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table pay_my_buddy.transfert : 10 rows
/*!40000 ALTER TABLE `transfert` DISABLE KEYS */;
INSERT INTO `transfert` (`id`, `amount`, `currency`, `date`, `description`, `fee`, `source_user`, `user_recipient`) VALUES
	(1, 10, '$', '2023-04-27 18:39:09', 'movie', 0.005, 2, 1),
	(2, 10, '$', '2023-04-27 18:39:09', 'food', 0.005, 1, 2),
	(3, 10, '$', '2023-04-27 18:39:09', 'movie', 0.005, 4, 3),
	(4, 5, '$', '2023-04-27 18:39:09', NULL, 0.005, 6, 5),
	(5, 10, '$', '2023-04-27 18:39:09', 'placeticket', 0.005, 1, 8),
	(6, 10, '$', '2023-04-27 18:39:09', 'shopping', 0.005, 10, 9),
	(7, 10, '$', '2023-04-27 18:39:09', 'shopping', 0.005, 10, 9),
	(8, 10, '$', '2023-04-27 18:39:09', 'hotel', 0.005, 8, 1),
	(9, 10, '$', '2023-04-27 18:39:09', 'restaurant', 0.005, 2, 1),
	(10, 10, '$', '2023-04-27 18:39:09', 'gift', 0.005, 3, 4);
/*!40000 ALTER TABLE `transfert` ENABLE KEYS */;

-- Listage de la structure de table pay_my_buddy. user
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `birthdate` datetime DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `app_account_id` int NOT NULL,
  `user_account_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKsk6harw8nolriwfiuqosnri2` (`app_account_id`),
  KEY `FKa2ixh18irxw16xxl1ka3gfth6` (`user_account_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table pay_my_buddy.user : 10 rows
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `birthdate`, `firstname`, `lastname`, `app_account_id`, `user_account_id`) VALUES
	(4, '1993-01-05 00:00:00', 'Anna', 'Moreau', 4, 4),
	(5, '1998-08-19 00:00:00', 'Marta', 'Feret', 5, 5),
	(6, '1995-03-29 00:00:00', 'Tim', 'Blond', 6, 6),
	(7, '1996-05-14 00:00:00', 'Jacques', 'Hugues', 7, 7),
	(8, '2000-03-12 00:00:00', 'Kylian', 'Mbappe', 8, 8),
	(1, '2000-05-11 00:00:00', 'Emma', 'Bolt', 1, 1),
	(9, '1999-07-07 00:00:00', 'Florian', 'Messier', 9, 9),
	(3, '2003-09-09 00:00:00', 'Isaac', 'Shelby', 3, 3),
	(2, '1970-04-21 00:00:00', 'Eden', 'Arnold', 2, 2),
	(10, '2004-06-06 00:00:00', 'Leslie', 'Berroua', 10, 10);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Listage de la structure de table pay_my_buddy. user_account
CREATE TABLE IF NOT EXISTS `user_account` (
  `user_account_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `last_connection` datetime DEFAULT NULL,
  `online_status` bit(1) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_account_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table pay_my_buddy.user_account : 10 rows
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` (`user_account_id`, `email`, `last_connection`, `online_status`, `password`) VALUES
	(1, 'emmabolt@gmail.com', '2022-04-21 17:06:34', b'1', '$2y$10$LU7mY5XJuA7vygZqAVzureDTz/9kUHi8EfaI7zjnp3PM.NlPbNHWm'),
	(2, 'edenarnold@gmail.com', '2023-07-11 17:10:54', b'1', '$2y$10$R7napE5jfZWi5NtMzVIS2.Kk4l3hITvSxe.YsaK9Gygb9mcKsqppi'),
	(3, 'isaacshelby@gmail.com', '2019-03-01 17:11:58', b'1', '$2y$10$bxAe8pg6JACDIxulxXs3qOGEfHMfiAlmg9wP6S4uO4vdE.roi/4fi'),
	(4, 'annamoreau@gmail.com', '2018-06-11 17:13:04', b'1', '$2y$10$jiIPFEEqbDv4wrTl0LymZOhjWZEENKHU/EJ.UiPp0Ea5f6PXDZG7m'),
	(5, 'martaferet@gmail.com', '2023-01-22 07:13:56', b'1', '$2y$10$Bax38RGWksWcoWr3pKtPQewBfBAfmwSqtuP/R41AsEf41OKqHTBnu'),
	(6, 'timblond@gmail.com', '2023-03-24 17:14:57', b'1', '$2y$10$R3U3ldJQ6e/a.alhr/0pPOiMmGS0ZIYm6a412sW8kjt1Yi/xCSvXi'),
	(7, 'jacqueshugues@gmail.com', '2023-02-09 17:16:20', b'1', '$2y$10$DvCGBXALqvH0iF2kCdLyK.S50.r3uC41fpz4L46utelb1atrNunFi'),
	(8, 'kylianmbappe@gmail.com', '2023-01-14 17:18:53', b'1', '$2y$10$rVYl7ymyRXlKPxMMNqMY8.f/fDlJnPv7yJXL2oW6kZhsf0SPStCcy'),
	(9, 'florianmessier@gmail.com', '2023-04-03 17:19:47', b'1', '$2y$10$h8Zd6n/RNEP2FgAI2KuDwuaUH/S5B0NUOo9SLRvpNrEUDlJvowudK'),
	(10, 'leslieberroua@gmail.com', '2020-04-21 17:20:37', b'0', '$2y$10$4ElhS4vHoVPnx29XlWP4eOmZFswW0e9T8sOLtKAK0aPvte8GkmQ3i');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;


-- Listage de la structure de la base pour pay_my_buddy_test
CREATE DATABASE IF NOT EXISTS `pay_my_buddy_test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pay_my_buddy_test`;

-- Listage de la structure de table pay_my_buddy_test. app_account
CREATE TABLE IF NOT EXISTS `app_account` (
  `app_account_id` int NOT NULL AUTO_INCREMENT,
  `balance` double DEFAULT NULL,
  PRIMARY KEY (`app_account_id`)
) ENGINE=MyISAM AUTO_INCREMENT=160 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table pay_my_buddy_test.app_account : 4 rows
/*!40000 ALTER TABLE `app_account` DISABLE KEYS */;
INSERT INTO `app_account` (`app_account_id`, `balance`) VALUES
	(1, 119.45),
	(2, 39.65),
	(3, 69.6),
	(4, 49.7);
/*!40000 ALTER TABLE `app_account` ENABLE KEYS */;

-- Listage de la structure de table pay_my_buddy_test. deposit
CREATE TABLE IF NOT EXISTS `deposit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `currency` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `fee` double DEFAULT NULL,
  `bank_account_iban` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `source_user` int NOT NULL,
  `operator` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr6332ul87j3osb8sw9kmmlcrv` (`source_user`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table pay_my_buddy_test.deposit : 4 rows
/*!40000 ALTER TABLE `deposit` DISABLE KEYS */;
INSERT INTO `deposit` (`id`, `amount`, `currency`, `date`, `description`, `fee`, `bank_account_iban`, `source_user`, `operator`) VALUES
	(1, 120, '$', '2020-04-27 17:39:57', NULL, 0.005, 'FR332500000550', 1, '+'),
	(2, 50, '$', '2020-04-27 17:43:13', 'deposit', 0.005, 'BE784525468541', 2, '+'),
	(3, 70, '$', '2020-01-27 17:43:13', NULL, 0.005, 'FR875155633344', 3, '+'),
	(4, 50, '$', '2020-01-27 17:43:13', NULL, 0.005, 'FR784520943314', 4, '+');
/*!40000 ALTER TABLE `deposit` ENABLE KEYS */;

-- Listage de la structure de table pay_my_buddy_test. friend
CREATE TABLE IF NOT EXISTS `friend` (
  `user_id` int NOT NULL,
  `friend_id` int NOT NULL,
  KEY `FKf9qk6e95h3da7o1u83ihtwrqa` (`friend_id`),
  KEY `FK3uu8s7yyof1qmenthngm24hry` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table pay_my_buddy_test.friend : 4 rows
/*!40000 ALTER TABLE `friend` DISABLE KEYS */;
INSERT INTO `friend` (`user_id`, `friend_id`) VALUES
	(1, 2),
	(2, 1),
	(3, 4),
	(4, 3);
/*!40000 ALTER TABLE `friend` ENABLE KEYS */;

-- Listage de la structure de table pay_my_buddy_test. transfert
CREATE TABLE IF NOT EXISTS `transfert` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `currency` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `fee` double DEFAULT NULL,
  `source_user` int NOT NULL,
  `user_recipient` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt7fx6f3rx9hrsjrwr545q7ncn` (`source_user`),
  KEY `FK4ufo83wxaevhnc56tv2sxrpgt` (`user_recipient`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table pay_my_buddy_test.transfert : 4 rows
/*!40000 ALTER TABLE `transfert` DISABLE KEYS */;
INSERT INTO `transfert` (`id`, `amount`, `currency`, `date`, `description`, `fee`, `source_user`, `user_recipient`) VALUES
	(1, 10, '$', '2023-04-27 18:39:09', 'movie', 0.005, 2, 1),
	(2, 10, '$', '2023-04-27 18:39:09', 'food', 0.005, 1, 2),
	(3, 10, '$', '2023-04-27 18:39:09', 'movie', 0.005, 4, 3),
	(4, 10, '$', '2023-04-27 18:39:09', 'gift', 0.005, 3, 4);
/*!40000 ALTER TABLE `transfert` ENABLE KEYS */;

-- Listage de la structure de table pay_my_buddy_test. user
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `birthdate` datetime DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `app_account_id` int NOT NULL,
  `user_account_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKsk6harw8nolriwfiuqosnri2` (`app_account_id`),
  KEY `FKa2ixh18irxw16xxl1ka3gfth6` (`user_account_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table pay_my_buddy_test.user : 4 rows
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `birthdate`, `firstname`, `lastname`, `app_account_id`, `user_account_id`) VALUES
	(4, '1993-01-05 00:00:00', 'Sophie', 'Moreau', 4, 4),
	(1, '2000-05-11 00:00:00', 'Jean', 'Bolt', 1, 1),
	(3, '2003-09-09 00:00:00', 'Paul', 'Shelby', 3, 3),
	(2, '1970-04-21 00:00:00', 'Patrick', 'Arnold', 2, 2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Listage de la structure de table pay_my_buddy_test. user_account
CREATE TABLE IF NOT EXISTS `user_account` (
  `user_account_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `last_connection` datetime DEFAULT NULL,
  `online_status` bit(1) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_account_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table pay_my_buddy_test.user_account : 4 rows
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` (`user_account_id`, `email`, `last_connection`, `online_status`, `password`) VALUES
	(1, 'jeanbolt@gmail.com', '2022-04-21 17:06:34', b'0', '$2y$10$LU7mY5XJuA7vygZqAVzureDTz/9kUHi8EfaI7zjnp3PM.NlPbNHWm'),
	(2, 'patrickarnold@gmail.com', '2023-07-11 17:10:54', b'1', '$2y$10$R7napE5jfZWi5NtMzVIS2.Kk4l3hITvSxe.YsaK9Gygb9mcKsqppi'),
	(3, 'paulshelby@gmail.com', '2019-03-01 17:11:58', b'0', '$2y$10$bxAe8pg6JACDIxulxXs3qOGEfHMfiAlmg9wP6S4uO4vdE.roi/4fi'),
	(4, 'sophiemoreau@gmail.com', '2018-06-11 17:13:04', b'0', '$2y$10$jiIPFEEqbDv4wrTl0LymZOhjWZEENKHU/EJ.UiPp0Ea5f6PXDZG7m');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
