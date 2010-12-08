/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50150
Source Host           : localhost:3306
Source Database       : wsriademo

Target Server Type    : MYSQL
Target Server Version : 50150
File Encoding         : 65001

Date: 2010-10-16 16:18:37
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `city`
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '城市ID',
  `CITY_NAME` varchar(20) NOT NULL COMMENT '城市名称',
  `SUPER_ID` bigint(20) NOT NULL COMMENT '上级城市(没有为-1)',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '城市备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='城市';

-- ----------------------------
-- Table structure for `wsria_demo_area_info`
-- ----------------------------
DROP TABLE IF EXISTS `wsria_demo_area_info`;
CREATE TABLE `wsria_demo_area_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '地区ID',
  `area_code` varchar(32) NOT NULL COMMENT '地区编码',
  `area_name` varchar(60) NOT NULL COMMENT '地区名称',
  `parent_area_id` bigint(20) NOT NULL COMMENT '上级ID',
  `area_number` char(6),
  `country_code` varchar(32),
  `area_level` int(11) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `sort` int(11),
  `remark` varchar(255),
  PRIMARY KEY (`id`),
  KEY `bmc_bd_uqareainfo` (`area_code`)
) ENGINE=InnoDB AUTO_INCREMENT=10421 DEFAULT CHARSET=utf8;