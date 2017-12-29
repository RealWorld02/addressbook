/*
Navicat MySQL Data Transfer

Source Server         : deng
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : addressbook

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-12-29 17:26:47
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
  `gender` varchar(10) DEFAULT NULL COMMENT '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
  `language` varchar(10) DEFAULT NULL COMMENT '用户的语言，简体中文为zh_CN',
  `city` varchar(10) DEFAULT NULL COMMENT '城市',
  `province` varchar(10) DEFAULT NULL COMMENT '省份',
  `country` varchar(10) DEFAULT NULL COMMENT '国家',
  `avatarUrl` varchar(300) DEFAULT NULL COMMENT '用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。',
  `addCountry` varchar(10) DEFAULT NULL COMMENT '地址中的国家',
  `addProvince` varchar(10) DEFAULT NULL COMMENT '地址中的省份',
  `addCity` varchar(10) DEFAULT NULL COMMENT '地址中的城市',
  `addDistrict` varchar(10) DEFAULT NULL COMMENT '地址中的城市区县',
  `latitude` varchar(50) DEFAULT NULL COMMENT '纬度',
  `longitude` varchar(50) DEFAULT NULL COMMENT '经度',
  `detailAddName` varchar(50) DEFAULT NULL COMMENT '用户选择的地址名称',
  `detailAddress` varchar(100) DEFAULT NULL COMMENT '用户选择的详细地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
