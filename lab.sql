/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : lab

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-03-11 20:16:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_account
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
  `aid` int(11) NOT NULL AUTO_INCREMENT COMMENT '账户编号',
  `username` varchar(45) NOT NULL COMMENT '账户姓名；账号使用学号',
  `password` varchar(45) NOT NULL COMMENT '密码',
  `privileges` int(11) NOT NULL COMMENT '管理权限；0:超级管理员，1:研究人员，2:普通用户',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_account
-- ----------------------------
INSERT INTO `t_account` VALUES ('1', '的', '的', '5', '2019-03-11 10:08:27');

-- ----------------------------
-- Table structure for t_activity
-- ----------------------------
DROP TABLE IF EXISTS `t_activity`;
CREATE TABLE `t_activity` (
  `aid` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动编号',
  `name` varchar(255) NOT NULL COMMENT '活动名称',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '活动时间',
  `introduction` varchar(2048) NOT NULL COMMENT '活动介绍',
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_activity
-- ----------------------------

-- ----------------------------
-- Table structure for t_project
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目编号',
  `name` varchar(45) NOT NULL COMMENT '项目名称',
  `introduction` varchar(1024) NOT NULL COMMENT '项目简介',
  `project` varchar(200) NOT NULL COMMENT '项目工程',
  `video` varchar(200) NOT NULL COMMENT '项目视频',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_project
-- ----------------------------

-- ----------------------------
-- Table structure for t_relationshaip
-- ----------------------------
DROP TABLE IF EXISTS `t_relationshaip`;
CREATE TABLE `t_relationshaip` (
  `rsid` int(11) NOT NULL AUTO_INCREMENT COMMENT '关系编号',
  `rid` int(11) NOT NULL COMMENT '研究员编号',
  `pid` int(11) NOT NULL COMMENT '项目编号',
  `tid` int(11) NOT NULL COMMENT '研究成果编号',
  PRIMARY KEY (`rsid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_relationshaip
-- ----------------------------

-- ----------------------------
-- Table structure for t_researcher
-- ----------------------------
DROP TABLE IF EXISTS `t_researcher`;
CREATE TABLE `t_researcher` (
  `rid` int(255) NOT NULL AUTO_INCREMENT COMMENT '研究员编号',
  `aid` int(11) NOT NULL COMMENT '账户编号',
  `name` varchar(45) NOT NULL COMMENT '姓名',
  `post` varchar(45) NOT NULL COMMENT '职称',
  `image` varchar(45) NOT NULL COMMENT '头像图片链接',
  `person_type` int(10) NOT NULL COMMENT '人员类别；0:导师，1:硕士生，2:博士生，3:其他',
  `affiliated_tutor` int(20) NOT NULL DEFAULT '-1' COMMENT '学生隶属导师编号',
  `direction` varchar(20) NOT NULL COMMENT '研究方向',
  `introduction` varchar(2048) NOT NULL COMMENT '人员介绍',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_researcher
-- ----------------------------
INSERT INTO `t_researcher` VALUES ('1', '100001', '张三', '教授', 'asdf', '0', '-1', '数据挖掘', 'xxxx');
INSERT INTO `t_researcher` VALUES ('2', '100002', '李四', '副教授', 'qwer', '1', '-1', '深度学习', 'xxxx');
INSERT INTO `t_researcher` VALUES ('4', '100003', '王五', '讲师', 'zxcv', '0', '-1', '计算机视觉', '啦啦啦啦啦啦');
INSERT INTO `t_researcher` VALUES ('6', '100005', '赵六', '讲师', 'zxcv', '0', '-1', '计算机视觉', '啦啦啦啦啦啦');
INSERT INTO `t_researcher` VALUES ('7', '100007', '赵六六六', '讲师', 'zxcv', '0', '-1', '计算机视觉', '啦啦啦啦啦啦');

-- ----------------------------
-- Table structure for t_thsis
-- ----------------------------
DROP TABLE IF EXISTS `t_thsis`;
CREATE TABLE `t_thsis` (
  `tid` int(11) NOT NULL AUTO_INCREMENT COMMENT '成果编号',
  `name` varchar(45) NOT NULL COMMENT '论文名称',
  `abstract` varchar(1024) NOT NULL COMMENT '摘要',
  `keywords` varchar(200) NOT NULL COMMENT '关键字',
  `content` varchar(200) NOT NULL COMMENT '文章正文',
  `code` varchar(200) NOT NULL COMMENT '代码',
  `data` varchar(200) NOT NULL COMMENT '数据',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_thsis
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `age` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'liming', '123', '18');
