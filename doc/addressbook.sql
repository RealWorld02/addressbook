/*
Navicat MySQL Data Transfer

Source Server         : deng
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : addressbook

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-12-26 17:32:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `openId` varchar(30) DEFAULT NULL COMMENT '用户openId',
  `nickName` varchar(10) DEFAULT NULL COMMENT '用户微信昵称',
  `gender` varchar(10) DEFAULT NULL COMMENT '性别',
  `language` varchar(10) DEFAULT NULL COMMENT '使用语言',
  `city` varchar(10) DEFAULT NULL COMMENT '城市',
  `province` varchar(10) DEFAULT NULL COMMENT '省份',
  `country` varchar(10) DEFAULT NULL COMMENT '国家',
  `avatarUrl` varchar(100) DEFAULT NULL COMMENT '微信头像路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '0', '0', '0', '0', '0', '0', '0', '0');
