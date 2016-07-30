/*
 Navicat Premium Data Transfer

 Source Server         : admin-recent
 Source Server Type    : MySQL
 Source Server Version : 50544
 Source Host           : localhost
 Source Database       : ns_runtime

 Target Server Type    : MySQL
 Target Server Version : 50544
 File Encoding         : utf-8

 Date: 05/23/2016 14:47:14 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `ns_endpoint`
-- ----------------------------
DROP TABLE IF EXISTS `ns_endpoint`;
CREATE TABLE `ns_endpoint` (
  `ID` char(36) NOT NULL,
  `USER_ID` varchar(255) NOT NULL,
  `PROTOCOL_ID` char(36) NOT NULL,
  `LINK` varchar(100) NOT NULL,
  `DESCRIPTION` varchar(1000) DEFAULT NULL,
  `CREATED_BY` char(36) NOT NULL,
  `CREATED_ON` bigint(20) NOT NULL,
  `LAST_UPDATED_BY` char(36) DEFAULT NULL,
  `LAST_UPDATED_ON` bigint(20) DEFAULT NULL,
  `VERSION_NUMBER` bigint(20) DEFAULT '1',
  `INACTIVE` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ENDPOINT_UNIQUE_KEY` (`USER_ID`,`PROTOCOL_ID`,`LINK`),
  KEY `PROTOCOL_ID` (`PROTOCOL_ID`),
  CONSTRAINT `fk_ns_endpoint_ns_protocol_id` FOREIGN KEY (`PROTOCOL_ID`) REFERENCES `ns_protocol` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ns_event`
-- ----------------------------
DROP TABLE IF EXISTS `ns_event`;
CREATE TABLE `ns_event` (
  `ID` char(36) NOT NULL,
  `EVENTTYPE_ID` char(36) NOT NULL DEFAULT '',
  `STATUS` char(20) DEFAULT NULL,
  `TRANSACTION_ID` char(36) NOT NULL DEFAULT '',
  `CREATED_BY` char(36) NOT NULL,
  `CREATED_ON` bigint(20) NOT NULL,
  `LAST_UPDATED_BY` char(36) DEFAULT NULL,
  `LAST_UPDATED_ON` bigint(20) DEFAULT NULL,
  `VERSION_NUMBER` bigint(20) DEFAULT '1',
  `INACTIVE` tinyint(1) DEFAULT '0',
  `PAYLOAD` mediumblob,
  `OVERRIDES` mediumblob,
  PRIMARY KEY (`ID`),
  KEY `EVENTTYPE_ID` (`EVENTTYPE_ID`),
  CONSTRAINT `fk_ns_event_ns_eventtype_id` FOREIGN KEY (`EVENTTYPE_ID`) REFERENCES `ns_eventtype` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ns_eventtype`
-- ----------------------------
DROP TABLE IF EXISTS `ns_eventtype`;
CREATE TABLE `ns_eventtype` (
  `ID` char(36) NOT NULL,
  `PRODUCT` char(36) NOT NULL,
  `RESOURCE` char(36) NOT NULL,
  `ACTION` char(36) NOT NULL,
  `CREATED_BY` char(36) NOT NULL,
  `CREATED_ON` bigint(20) NOT NULL,
  `LAST_UPDATED_BY` char(36) DEFAULT NULL,
  `LAST_UPDATED_ON` bigint(20) DEFAULT NULL,
  `VERSION_NUMBER` bigint(20) DEFAULT '1',
  `INACTIVE` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `unique_eventtype` (`PRODUCT`,`RESOURCE`,`ACTION`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 alter table ns_eventtype ADD CONSTRAINT uc_PRODUCT_RESOURCE_ACTION UNIQUE(PRODUCT,RESOURCE,ACTION);
-- ----------------------------
--  Table structure for `ns_eventtypeprotocol`
-- ----------------------------
DROP TABLE IF EXISTS `ns_eventtypeprotocol`;
CREATE TABLE `ns_eventtypeprotocol` (
  `ID` char(36) NOT NULL,
  `EVENTTYPE_ID` char(36) NOT NULL,
  `PROTOCOL_ID` char(36) NOT NULL,
  `TEMPLATE_CONTENT_ID` char(36) NOT NULL DEFAULT '',
  `CREATED_BY` char(36) NOT NULL,
  `CREATED_ON` bigint(20) NOT NULL,
  `LAST_UPDATED_BY` char(36) DEFAULT NULL,
  `LAST_UPDATED_ON` bigint(20) DEFAULT NULL,
  `VERSION_NUMBER` bigint(20) DEFAULT '1',
  `INACTIVE` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EVENTTYPE_UNIQUE_KEY` (`EVENTTYPE_ID`,`PROTOCOL_ID`),
  KEY `fk_ns_eventtypeprotocol_ns_template_content_id` (`TEMPLATE_CONTENT_ID`),
  KEY `fk_ns_eventtypeprotocol_ns_protocol_id` (`PROTOCOL_ID`),
  CONSTRAINT `fk_ns_eventtypeprotocol_ns_template_content_id` FOREIGN KEY (`TEMPLATE_CONTENT_ID`) REFERENCES `ns_templatecontent` (`ID`),
  CONSTRAINT `fk_ns_eventtypeprotocol_ns_eventtype_id` FOREIGN KEY (`EVENTTYPE_ID`) REFERENCES `ns_eventtype` (`ID`),
  CONSTRAINT `fk_ns_eventtypeprotocol_ns_protocol_id` FOREIGN KEY (`PROTOCOL_ID`) REFERENCES `ns_protocol` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ns_protocol`
-- ----------------------------
DROP TABLE IF EXISTS `ns_protocol`;
CREATE TABLE `ns_protocol` (
  `ID` char(36) NOT NULL,
  `CODE` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(1000) DEFAULT NULL,
  `CREATED_BY` char(36) NOT NULL,
  `CREATED_ON` bigint(20) NOT NULL,
  `LAST_UPDATED_BY` char(36) DEFAULT NULL,
  `LAST_UPDATED_ON` bigint(20) DEFAULT NULL,
  `VERSION_NUMBER` bigint(20) DEFAULT '1',
  `INACTIVE` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ns_subscription`
-- ----------------------------
DROP TABLE IF EXISTS `ns_subscription`;
CREATE TABLE `ns_subscription` (
  `ID` char(36) NOT NULL,
  `USER_ID` varchar(255) NOT NULL,
  `EVENTTYPE_ID` char(36) NOT NULL DEFAULT '',
  `ENDPOINT_ID` char(36) NOT NULL DEFAULT '',
  `CREATED_BY` char(36) NOT NULL,
  `CREATED_ON` bigint(20) NOT NULL,
  `LAST_UPDATED_BY` char(36) DEFAULT NULL,
  `LAST_UPDATED_ON` bigint(20) DEFAULT NULL,
  `VERSION_NUMBER` bigint(20) DEFAULT '1',
  `INACTIVE` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `SUBSCRIPTION_UNIQUE_KEY` (`USER_ID`,`EVENTTYPE_ID`,`ENDPOINT_ID`),
  KEY `EVENTTYPE_ID` (`EVENTTYPE_ID`),
  KEY `ENDPOINT_ID` (`ENDPOINT_ID`),
  CONSTRAINT `fk_ns_subscription_ns_eventtype_id` FOREIGN KEY (`EVENTTYPE_ID`) REFERENCES `ns_eventtype` (`ID`),
  CONSTRAINT `fk_ns_subscription_ns_endpoint_id` FOREIGN KEY (`ENDPOINT_ID`) REFERENCES `ns_endpoint` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ns_templatecontent`
-- ----------------------------
DROP TABLE IF EXISTS `ns_templatecontent`;
CREATE TABLE `ns_templatecontent` (
  `ID` char(36) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `SUBJECT` varchar(100) DEFAULT NULL,
  `FROM_INFO` varchar(100) DEFAULT NULL,
  `CONTENT` text,
  `CREATED_BY` char(36) NOT NULL,
  `CREATED_ON` bigint(20) NOT NULL,
  `LAST_UPDATED_BY` char(36) DEFAULT NULL,
  `LAST_UPDATED_ON` bigint(20) DEFAULT NULL,
  `VERSION_NUMBER` bigint(20) DEFAULT '1',
  `INACTIVE` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


SET FOREIGN_KEY_CHECKS = 1;
