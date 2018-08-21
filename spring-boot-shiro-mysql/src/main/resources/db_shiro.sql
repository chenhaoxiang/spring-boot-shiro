/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : db_shiro

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2018-08-21 12:09:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '权限名',
  `url` varchar(100) NOT NULL COMMENT '权限对应的访问路径，细分到每个路径',
  `comment` varchar(255) NOT NULL DEFAULT '' COMMENT '权限说明',
  PRIMARY KEY (`id`),
  KEY `roleId` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', 'user:insert', '/user/insert', '添加用户操作');
INSERT INTO `t_permission` VALUES ('2', 'user:select', '/user/select', '根据用户名查询');
INSERT INTO `t_permission` VALUES ('3', 'user:update', '/user/update', '修改');
INSERT INTO `t_permission` VALUES ('4', 'user:delete', '/user/delete', '删除');
INSERT INTO `t_permission` VALUES ('5', 'user:updatePermission', '/user/updatePermission', '动态更新权限');
INSERT INTO `t_permission` VALUES ('6', 'test', '/test', '测试');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '角色名',
  `comment` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'admin', '管理员');
INSERT INTO `t_role` VALUES ('2', 'user', '用户');

-- ----------------------------
-- Table structure for t_r_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `t_r_permission_role`;
CREATE TABLE `t_r_permission_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `permission_id` int(11) unsigned NOT NULL COMMENT '权限id',
  `role_id` int(11) unsigned NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='角色权限关系表';

-- ----------------------------
-- Records of t_r_permission_role
-- ----------------------------
INSERT INTO `t_r_permission_role` VALUES ('1', '1', '1');
INSERT INTO `t_r_permission_role` VALUES ('2', '2', '1');
INSERT INTO `t_r_permission_role` VALUES ('3', '3', '1');
INSERT INTO `t_r_permission_role` VALUES ('4', '4', '1');
INSERT INTO `t_r_permission_role` VALUES ('5', '3', '2');
INSERT INTO `t_r_permission_role` VALUES ('6', '4', '2');
INSERT INTO `t_r_permission_role` VALUES ('7', '5', '1');
INSERT INTO `t_r_permission_role` VALUES ('9', '6', '2');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `role_id` int(11) unsigned NOT NULL COMMENT '角色id',
  `salt` varchar(64) NOT NULL COMMENT '盐',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '1234', '1', '1');
INSERT INTO `t_user` VALUES ('2', 'user', '1234', '2', '1');
INSERT INTO `t_user` VALUES ('3', 'marry', '123', '1', '1');
INSERT INTO `t_user` VALUES ('4', 'json', '123', '2', '1');
INSERT INTO `t_user` VALUES ('6', 'chen', '8a53a0fb00e208466a401ef7ee2e0023', '1', '4433beeb529fd7815765464f9ea093f9');
INSERT INTO `t_user` VALUES ('7', 'user2', '77103ca14788cc02741f506a507459e0', '2', '2695142bc10837c000e585eea5146155');
