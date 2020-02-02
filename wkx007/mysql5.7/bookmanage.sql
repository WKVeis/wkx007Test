/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : bookmanage

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-08-23 21:20:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `age` int(100) DEFAULT NULL,
  `Create_Time` datetime DEFAULT NULL,
  `Update_Time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for account_role
-- ----------------------------
DROP TABLE IF EXISTS `account_role`;
CREATE TABLE `account_role` (
  `id` int(11) NOT NULL,
  `account_id` int(11) DEFAULT NULL COMMENT '用户信息表的ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色表的id',
  `create_Time` datetime DEFAULT NULL,
  `update_Time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ap` (`account_id`),
  KEY `rp` (`role_id`),
  CONSTRAINT `ap` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `rp` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(200) DEFAULT NULL,
  `role_level` int(20) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `create_Time` datetime DEFAULT NULL,
  `update_Time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log` (
  `id` varchar(200) NOT NULL,
  `userName` varchar(200) DEFAULT NULL,
  `operation` varchar(4000) DEFAULT NULL,
  `method` varchar(2000) DEFAULT NULL,
  `params` varchar(2000) DEFAULT NULL,
  `ip` varchar(2000) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `Create_Date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
