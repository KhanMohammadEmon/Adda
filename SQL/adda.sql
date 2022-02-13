-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 13, 2022 at 06:35 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `adda`
--

-- --------------------------------------------------------

--
-- Table structure for table `adda`
--

CREATE TABLE `adda` (
  `Serial` int(10) NOT NULL,
  `a_name` varchar(50) NOT NULL,
  `a_userName` varchar(20) NOT NULL,
  `a_password` varchar(20) NOT NULL,
  `a_email` varchar(50) NOT NULL,
  `a_phn` varchar(20) NOT NULL,
  `a_gender` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `adda`
--

INSERT INTO `adda` (`Serial`, `a_name`, `a_userName`, `a_password`, `a_email`, `a_phn`, `a_gender`) VALUES
(19, 'Emon', 'emon', '123', 'emon1@gmail.com', '01797144206', 'Male'),
(20, 'Emon', 'emon', '123', 'emon1@gmail.com', '01797144206', 'Male'),
(23, 'Emon Khan', 'emon1', 'emon', 'emon2@gmail.com', '01571337468', 'Male'),
(24, 'Emon Khan', 'emon1', 'emon', 'emon2@gmail.com', '01571337468', 'Male'),
(25, 'Oishe Atoshi', 'oishe', 'oishe123', 'oishe12@gmail.com', '01347892341', 'Female'),
(26, 'Oishe Atoshi', 'oishe', 'oishe123', 'oishe12@gmail.com', '01347892341', 'Female'),
(31, 'Md. Mumtahin Habib Ullah Mazumder', 'habib', '123', 'mumtahin@cse.uiu.ac.bd', '017923432432', 'Male'),
(32, 'Md. Mumtahin Habib Ullah Mazumder', 'habib', '123', 'mumtahin@cse.uiu.ac.bd', '017923432432', 'Male'),
(33, 'Sakib Al Hasan', 'sakib', 'sakib', 'sakib@gmail.com', '01789347654', 'Male'),
(34, 'Sakib Al Hasan', 'sakib', 'sakib', 'sakib@gmail.com', '01789347654', 'Male');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `adda`
--
ALTER TABLE `adda`
  ADD PRIMARY KEY (`Serial`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `adda`
--
ALTER TABLE `adda`
  MODIFY `Serial` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
