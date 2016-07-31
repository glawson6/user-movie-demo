/*
 Navicat Premium Data Transfer

 Source Server         : admin-recent
 Source Server Type    : MySQL
 Source Server Version : 50544
 Source Host           : localhost
 Source Database       : user_movie_demo

 Target Server Type    : MySQL
 Target Server Version : 50544
 File Encoding         : utf-8

 Date: 07/30/2016 20:03:27 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `genre`
-- ----------------------------
DROP TABLE IF EXISTS `genre`;
CREATE TABLE `genre` (
  `genreId` char(36) NOT NULL,
  `name` varchar(25) NOT NULL,
  PRIMARY KEY (`genreId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `movie`
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `movieId` char(36) NOT NULL,
  `name` varchar(100) NOT NULL,
  `genreId` char(36) NOT NULL,
  PRIMARY KEY (`movieId`),
  KEY `genreId` (`genreId`),
  CONSTRAINT `fk_m_genreId` FOREIGN KEY (`genreId`) REFERENCES `genre` (`genreId`)
);

-- ----------------------------
--  Table structure for `movie_rating`
-- ----------------------------
DROP TABLE IF EXISTS `movie_rating`;
CREATE TABLE `movie_rating` (
  `id` char(36) NOT NULL,
  `rating` int(11) NOT NULL,
  `movieId` char(36) NOT NULL,
  `userId` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `movieId` (`movieId`),
  CONSTRAINT `fk_mr_movieid` FOREIGN KEY (`movieId`) REFERENCES `movie` (`movieId`),
  CONSTRAINT `fk_mr_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
);

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` char(50) NOT NULL,
  `age` int(11) NOT NULL,
  PRIMARY KEY (`userId`)
);

