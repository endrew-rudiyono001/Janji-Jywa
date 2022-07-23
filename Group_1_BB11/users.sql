-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 09, 2022 at 11:34 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `janji_jywa`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UserID` char(5) DEFAULT NULL,
  `UserName` varchar(30) DEFAULT NULL,
  `UserEmail` varchar(50) DEFAULT NULL,
  `UserPassword` varchar(30) DEFAULT NULL,
  `UserDOB` date DEFAULT NULL,
  `UserGender` varchar(10) DEFAULT NULL,
  `UserAddress` varchar(255) DEFAULT NULL,
  `UserPhone` varchar(30) DEFAULT NULL,
  `UserRole` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserID`, `UserName`, `UserEmail`, `UserPassword`, `UserDOB`, `UserGender`, `UserAddress`, `UserPhone`, `UserRole`) VALUES
('US001', 'Revaldi Mijaya', 'admin', 'admin', NULL, 'Male', 'asdasdasdasd Street', '0920398193812319', 'Admin'),
('US002', 'daniel fujiono', 'customer', 'customer', NULL, 'Male', 'binus Street', '012345678911', 'Customer'),
('US003', 'lilnederu', 'lilnederu@gmail.com', '@Minato1234', NULL, 'Male', 'grove Street', '082382728032', 'Customer'),
('US004', 'lildeneru', 'lildeneru@outlook.com', '@Minato1234', NULL, 'Male', 'Grove 4Life Street', '082382728031', 'Customer'),
('US005', 'lalalala', 'hehe@gmail.com', '@Qwerty1234', NULL, 'Male', 'Dallas 4 Live Street', '080123456789', 'Admin'),
('US006', 'adasd', 'lalala@gmail.com', 'lalalaa', NULL, 'Male', 'asd Street', '122345324123', 'Admin'),
('US007', 'asdasd', 'lallalala@gmail.com', 'aaaaaaaaaaa', NULL, 'Male', 'asdasda Street', '082382728032', 'Admin'),
('US008', 'lalalalal', 'lalala@outlook.com', 'lalalala', NULL, 'Male', 'grove 4 live Street', '082382728033', 'Admin'),
('US009', 'asdada', 'asdasda@gmail.com', 'Aaaaa112233', NULL, 'Male', 'Grove.5 Street', '082381712345', 'Admin');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
