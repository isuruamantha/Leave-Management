-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 05, 2017 at 06:02 PM
-- Server version: 10.1.10-MariaDB
-- PHP Version: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `leavemanagementsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `nic` varchar(45) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `dob` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `total_leaves` varchar(45) DEFAULT NULL,
  `is_hr` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`nic`, `first_name`, `last_name`, `telephone`, `email`, `dob`, `password`, `total_leaves`, `is_hr`) VALUES
('1', '1', '1', '0719720510', 'isuru.amantha@gmail.com', '1995-10-08', '1', '12', 'true'),
('12', 'isuru', 'amantha', '0712017601', 'isuru.co@gmail.com', '1995-10-08', '12345', '12', 'true'),
('941353140V', 'Thanusha', 'Nirmana', '0712017601', 'thanu.co@gmail.com', '1995-10-08', '12345', '12', 'true'),
('952822160v', 'Sachith', 'Perera', '0712017601', 'sachith.co@gmail.com', '1995-10-08', '12345', '12', 'false');

-- --------------------------------------------------------

--
-- Table structure for table `leaves`
--

CREATE TABLE `leaves` (
  `leave_id` int(11) NOT NULL,
  `emp_id` varchar(45) NOT NULL,
  `from_date` varchar(45) NOT NULL,
  `to_date` varchar(45) NOT NULL,
  `reason` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `leaves`
--

INSERT INTO `leaves` (`leave_id`, `emp_id`, `from_date`, `to_date`, `reason`, `status`) VALUES
(1012, '1012', '12345', '12345', '12345', 'Rejected'),
(1014, '952822160v1', '2017-10-03', '2017-10-03', 'Personal Reason', 'accepted'),
(1015, '952822160v1', '2017-10-03', '2017-10-03', 'Personal Reason', 'accepted'),
(1017, '952822160v2', '2017-10-03', '2017-10-03', 'Personal Reason', 'accepted'),
(1018, '952822160v', '2017-10-03', '2017-10-03', 'Personal Reason', 'accepted'),
(1019, '952822160v', '2017-10-03', '2017-10-03', 'Personal Reason', 'accepted'),
(1022, '1022', '10/04/17', '10/27/17', 'tugu', 'Accepted'),
(1024, '952822160v', '10/22/17', '10/31/17', 'yoguhugjhy', 'Rejected'),
(1025, '952822160v', '', '', '', 'Accepted'),
(1030, '12', '10/20/17', '10/28/17', 'ii', 'Accepted'),
(1031, '12', '10/05/17', '10/05/17', 'yo', 'Accepted'),
(1032, '12', '10/20/17', '10/28/17', 'yo', 'Rejected'),
(1033, '952822160v', '10/28/17', '10/28/20', 'h', 'Rejected'),
(1034, '952822160v', '10/05/17', '10/20/17', 'h', 'Accepted');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`nic`);

--
-- Indexes for table `leaves`
--
ALTER TABLE `leaves`
  ADD PRIMARY KEY (`leave_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `leaves`
--
ALTER TABLE `leaves`
  MODIFY `leave_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1035;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
