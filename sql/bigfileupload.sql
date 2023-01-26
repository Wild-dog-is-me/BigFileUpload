/*
 Navicat Premium Data Transfer

 Source Server         : mac本地
 Source Server Type    : MySQL
 Source Server Version : 50731 (5.7.31-log)
 Source Host           : localhost:3306
 Source Schema         : bigfileupload

 Target Server Type    : MySQL
 Target Server Version : 50731 (5.7.31-log)
 File Encoding         : 65001

 Date: 26/01/2023 15:26:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chunk
-- ----------------------------
DROP TABLE IF EXISTS `chunk`;
CREATE TABLE `chunk` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `md5` varchar(50) DEFAULT NULL,
  `chunk_index` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2139242499 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `md5` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1921138690 DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
