/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50613
 Source Host           : localhost
 Source Database       : philips_db

 Target Server Type    : MySQL
 Target Server Version : 50613
 File Encoding         : utf-8

 Date: 10/24/2016 11:27:01 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `ss_role`
-- ----------------------------
DROP TABLE IF EXISTS `ss_role`;
CREATE TABLE `ss_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `permissions` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ss_team`
-- ----------------------------
DROP TABLE IF EXISTS `ss_team`;
CREATE TABLE `ss_team` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ss_user`
-- ----------------------------
DROP TABLE IF EXISTS `ss_user`;
CREATE TABLE `ss_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `login_name` varchar(255) NOT NULL COMMENT '登录名称',
  `name` varchar(64) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(64) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `status` varchar(32) DEFAULT '1',
  `team_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8 COMMENT='登录用户表';

-- ----------------------------
--  Records of `ss_user`
-- ----------------------------
BEGIN;
INSERT INTO `ss_user` VALUES ('1', 'admin', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1', 'admin@nn.com', '1', '1'), ('2', 'af', 'aa', 'e10adc3949ba59abbe56e057f20f883e', null, 'aa@aa.com', '0', null), ('3', 'test1', '测试1', 'e10adc3949ba59abbe56e057f20f883e', null, 'test@nn.com', '1', null), ('5', 'efdhg', 'admin', 'e10adc3949ba59abbe56e057f20f883e', null, '132545465@223.com', '1', null), ('6', 'ttt', 'admin', 'e10adc3949ba59abbe56e057f20f883e', null, 'admin@nn.com', '0', null), ('8', 'admin1', '2323', 'e10adc3949ba59abbe56e057f20f883e', null, 'admin@nn.com', '1', null), ('9', 'admin2', 'admin', 'e10adc3949ba59abbe56e057f20f883e', null, '2640@qq.com', '1', null), ('10', 'ceshi123', 'admin', 'e10adc3949ba59abbe56e057f20f883e', null, 'admin@hesine.com', '1', null), ('11', '23123', 'aa', 'd41d8cd98f00b204e9800998ecf8427e', null, 'admin@nn1.com', '0', null), ('12', 'admin3', 'admin', 'e10adc3949ba59abbe56e057f20f883e', null, 'admin@nn3.com', '1', null), ('13', 'admin6', 'lottery Warning', 'e10adc3949ba59abbe56e057f20f883e', null, 'admin@dnn.com', '1', null), ('14', '测试1', '乌有', 'e10adc3949ba59abbe56e057f20f883e', null, 'wuyou@koukou.com', '1', null), ('15', '测试', '测速', 'e10adc3949ba59abbe56e057f20f883e', null, 'zhb@163.com', '1', null), ('16', '周一', '测试人', 'e10adc3949ba59abbe56e057f20f883e', null, 'zhouyi@hesine.com', '1', null), ('17', 'zhouer', '周二', 'e10adc3949ba59abbe56e057f20f883e', null, 'zhouer@hesine.com', '1', null), ('18', 'zhousan', '周三', 'e10adc3949ba59abbe56e057f20f883e', null, 'zhousan@hesine.com', '1', null), ('19', 'zhousi', '周四', 'e10adc3949ba59abbe56e057f20f883e', null, 'zhousi@hesine.com', '1', null), ('20', '测试20141023', 'admin', 'e10adc3949ba59abbe56e057f20f883e', null, 'test@hesine.com', '1', null), ('21', 'test121', 'test121', 'e10adc3949ba59abbe56e057f20f883e', null, 'test121@hesine.com', '1', null), ('27', 'test12', 'test123', 'e10adc3949ba59abbe56e057f20f883e', null, 'test12@hesine.com', '1', null), ('58', 'hesine_dev', 'hesine_dev', 'e10adc3949ba59abbe56e057f20f883e', null, 'hesine_dev@hesine.com', '1', null), ('59', 'hesine_test', 'hesine_test', 'e10adc3949ba59abbe56e057f20f883e', null, 'hesine_test@hesine.com', '1', null), ('60', 'hesine_verify', 'hesine_verify', 'e10adc3949ba59abbe56e057f20f883e', null, 'hesine_verify@hesine.com', '1', null), ('61', '', '', '', null, '', '0', null), ('62', 'test123', 'test123', 'e10adc3949ba59abbe56e057f20f883e', null, 'test123.hesine.com', '1', null), ('63', 'test_en', 'test_en', 'e10adc3949ba59abbe56e057f20f883e', null, 'test_en@hesine.com', '1', null);
COMMIT;

-- ----------------------------
--  Table structure for `ss_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `ss_user_role`;
CREATE TABLE `ss_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
