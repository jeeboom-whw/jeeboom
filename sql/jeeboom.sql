/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost
 Source Database       : jeeboom

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 07/25/2017 13:15:43 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `auto_table`
-- ----------------------------
DROP TABLE IF EXISTS `auto_table`;
CREATE TABLE `auto_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(50) NOT NULL DEFAULT '' COMMENT '数据库表名',
  `name` varchar(50) NOT NULL COMMENT '表别名',
  `label` varchar(200) DEFAULT NULL COMMENT '表注释',
  `type` int(1) NOT NULL DEFAULT '1' COMMENT '表生成类型1单表 2树行列表结构 3包含关联关系表(扩展以后用)',
  `is_del` int(1) DEFAULT '0' COMMENT '是否有删除功能(真删除)',
  `is_show` int(1) DEFAULT '0' COMMENT '是否有展示隐藏功能(假删除)',
  `is_status` int(1) DEFAULT '0' COMMENT '是否有上下架功能',
  `is_all_del` int(1) DEFAULT '0' COMMENT '批量删除功能(真删除)',
  `is_all_show` int(1) DEFAULT '0' COMMENT '批量展示隐藏功能(假删除)',
  `is_all_status` int(1) DEFAULT '0' COMMENT '批量上下架功能',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='代码自动生成 — 已生成数据库表配置';

-- ----------------------------
--  Table structure for `auto_table_column`
-- ----------------------------
DROP TABLE IF EXISTS `auto_table_column`;
CREATE TABLE `auto_table_column` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID编号',
  `table_id` bigint(20) DEFAULT NULL COMMENT '表id',
  `column_name` varchar(50) DEFAULT NULL COMMENT '数据库属性名称',
  `name` varchar(50) DEFAULT NULL COMMENT '属性别名',
  `column_type` varchar(50) NOT NULL COMMENT '数据库类型',
  `type` varchar(50) NOT NULL COMMENT 'JAVA类型',
  `length` int(6) DEFAULT NULL COMMENT '长度',
  `label` varchar(255) DEFAULT NULL COMMENT '注释',
  `nullable` int(1) DEFAULT '0' COMMENT '是否为非空',
  `is_list` int(1) NOT NULL DEFAULT '1' COMMENT '是否展示在列表',
  `is_select` int(1) NOT NULL DEFAULT '0' COMMENT '是否为查询条件',
  `is_select_type` int(1) DEFAULT '1' COMMENT '查询种类 1 相等 2 like',
  `order_no` int(4) NOT NULL DEFAULT '0' COMMENT '排序',
  `mdict_title` varchar(50) DEFAULT NULL COMMENT '数据字典title',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='代码自动生成 — 数据库表对应的属性列配置';

-- ----------------------------
--  Table structure for `goods_item`
-- ----------------------------
DROP TABLE IF EXISTS `goods_item`;
CREATE TABLE `goods_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id编号',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `price` bigint(20) DEFAULT NULL COMMENT '价格',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  `img` varchar(200) DEFAULT NULL COMMENT '图片',
  `info` varchar(100) DEFAULT NULL COMMENT '介绍',
  `detail` varchar(500) DEFAULT NULL COMMENT '详情',
  `count` int(7) DEFAULT NULL COMMENT '库存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='商品';

-- ----------------------------
--  Records of `goods_item`
-- ----------------------------
BEGIN;
INSERT INTO `goods_item` VALUES ('1', '测试商品', '1001', '1', 'www.baidu.com', '测试介绍', '测试详情', '100');
COMMIT;

-- ----------------------------
--  Table structure for `sys_mdict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_mdict`;
CREATE TABLE `sys_mdict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID编号',
  `title` varchar(100) NOT NULL COMMENT '标题',
  `name` varchar(100) NOT NULL COMMENT '标题名称',
  `value` varchar(50) NOT NULL COMMENT '值',
  `info` varchar(100) NOT NULL COMMENT '值描述',
  `order_no` int(4) NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`),
  UNIQUE KEY `title` (`title`,`value`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统数据字典';

-- ----------------------------
--  Records of `sys_mdict`
-- ----------------------------
BEGIN;
INSERT INTO `sys_mdict` VALUES ('1', 'sys_status', '是否禁用', '1', '禁用', '10'), ('2', 'sys_status', '是否禁用', '2', '开启', '20'), ('4', 'is_show', '是否显示', '1', '是', '10'), ('5', 'is_show', '是否显示', '2', '否', '20'), ('6', 'auto_select_type', '查询分类', '1', '相等', '10'), ('7', 'auto_select_type', '查询分类', '2', 'LIKE匹配', '20'), ('8', 'auto_select_type', '查询分类', '3', '> 大于', '30'), ('9', 'auto_select_type', '查询分类', '4', '< 小于', '40'), ('10', 'auto_create_type', '代码生成类型', '1', '前后台代码', '10'), ('11', 'auto_create_type', '代码生成类型', '2', 'Service服务(无页面)', '20'), ('12', 'auto_create_type', '代码生成类型', '3', '接口提供(无页面)', '30');
COMMIT;

-- ----------------------------
--  Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID编号',
  `name` varchar(40) NOT NULL COMMENT '菜单名称',
  `pid` bigint(20) NOT NULL COMMENT '父级ID',
  `level` int(4) NOT NULL DEFAULT '0' COMMENT '当前级别',
  `order_no` int(4) NOT NULL DEFAULT '10' COMMENT '排序号码',
  `path` varchar(255) DEFAULT NULL COMMENT '访问路径',
  `permision` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `img` varchar(100) DEFAULT NULL COMMENT '图标标识',
  `is_show` int(2) NOT NULL DEFAULT '1' COMMENT '是否显示 1是 2否',
  `status` int(2) NOT NULL DEFAULT '2' COMMENT '是否禁用 1是 2否',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_by` bigint(20) NOT NULL COMMENT '创建人',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统菜单表';

-- ----------------------------
--  Records of `sys_menu`
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES ('2', '系统设置', '0', '1', '10', '', '', '', '1', '2', '2017-06-07 18:48:45', '2017-07-01 23:19:30', '1', '1'), ('3', '权限管理', '2', '2', '10', '', '', '', '1', '2', '2017-06-08 15:12:07', '2017-06-11 13:41:13', '1', '1'), ('4', '用户管理', '3', '3', '10', '/sysUser/page', '', '', '1', '2', '2017-06-08 15:12:07', '2017-06-15 09:27:00', '1', '1'), ('5', '角色管理', '3', '3', '20', '/sysRole/page', '', '', '1', '2', '2017-06-08 21:46:56', '2017-06-11 13:42:20', '1', '1'), ('6', '菜单管理', '3', '3', '30', '/sysMenu/treeList', '', '', '1', '2', '2017-06-08 21:47:49', '2017-06-11 13:42:35', '1', '1'), ('9', '编辑', '4', '4', '20', '', 'sys:sysUser:edit', '', '2', '2', '2017-06-09 11:22:38', '2017-06-11 09:59:50', '1', '1'), ('12', '查看', '4', '4', '10', '', 'sys:sysUser:view', '', '2', '2', '2017-06-11 09:56:12', '2017-06-11 09:57:29', '1', '1'), ('13', '查看', '5', '4', '10', '', 'sys:sysRole:view', '', '2', '2', '2017-06-11 09:58:27', '2017-06-11 09:58:27', '1', '1'), ('14', '编辑', '5', '4', '20', '', 'sys:sysRole:edit', '', '2', '2', '2017-06-11 09:58:43', '2017-06-11 09:58:43', '1', '1'), ('15', '查看', '6', '4', '10', '', 'sys:sysMenu:view', '', '2', '2', '2017-06-11 09:59:19', '2017-06-11 09:59:19', '1', '1'), ('16', '编辑', '6', '4', '20', '', 'sys:sysMenu:edit', '', '2', '2', '2017-06-11 09:59:38', '2017-06-11 09:59:38', '1', '1'), ('54', '系统设置', '2', '2', '20', '', '', '', '1', '2', '2017-07-01 14:54:54', '2017-07-01 14:55:29', '1', '1'), ('55', '数据字典', '54', '3', '10', '/sysMdict/page', '', '', '1', '2', '2017-07-01 14:57:13', '2017-07-01 14:57:13', '1', '1'), ('56', '查看', '55', '4', '10', '', 'sys:sysMdict:view', '', '2', '2', '2017-07-01 15:42:14', '2017-07-01 15:42:14', '1', '1'), ('57', '编辑', '55', '4', '20', '', 'sys:sysMdict:edit', '', '2', '2', '2017-07-01 15:42:40', '2017-07-01 15:42:40', '1', '1'), ('58', '代码生成', '0', '1', '20', '', '', '', '1', '2', '2017-07-17 21:35:55', '2017-07-17 21:35:55', '1', '1'), ('59', '代码生成', '58', '2', '10', '', '', '', '1', '2', '2017-07-17 21:36:17', '2017-07-17 21:36:17', '1', '1'), ('62', '代码生成', '59', '3', '5', '/autoTable/showTables', '', '', '1', '2', '2017-07-17 23:27:46', '2017-07-17 23:27:46', '1', '1');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID编号',
  `name` varchar(40) NOT NULL COMMENT '名称',
  `status` int(2) NOT NULL DEFAULT '2' COMMENT '是否禁用 1是 2否',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_by` bigint(20) NOT NULL COMMENT '创建人',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统角色表';

-- ----------------------------
--  Records of `sys_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('1', '系统管理员', '2', '2017-06-05 12:03:41', '2017-07-01 16:44:28', '1', '1'), ('2', '测试人员', '2', '2017-06-05 14:53:22', '2017-06-11 13:25:26', '1', '1'), ('25', '后台开发', '2', '2017-06-11 12:41:46', '2017-06-11 13:25:04', '1', '1'), ('26', 'H5开发', '2', '2017-06-11 13:25:41', '2017-06-11 13:25:41', '1', '1'), ('27', '移动端开发', '2', '2017-06-11 13:25:57', '2017-06-11 13:25:57', '1', '1');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID编号',
  `sys_role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `sys_menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_role_id` (`sys_role_id`,`sys_menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=562 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统角色菜单关联表';

-- ----------------------------
--  Records of `sys_role_menu`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES ('549', '1', '1'), ('538', '1', '2'), ('539', '1', '3'), ('540', '1', '4'), ('542', '1', '5'), ('544', '1', '6'), ('550', '1', '10'), ('551', '1', '11'), ('541', '1', '12'), ('543', '1', '13'), ('545', '1', '15'), ('557', '1', '19'), ('553', '1', '20'), ('555', '1', '21'), ('554', '1', '22'), ('552', '1', '23'), ('556', '1', '24'), ('558', '1', '25'), ('559', '1', '39'), ('560', '1', '40'), ('561', '1', '41'), ('546', '1', '54'), ('547', '1', '55'), ('548', '1', '56'), ('191', '2', '1'), ('180', '2', '2'), ('181', '2', '3'), ('182', '2', '4'), ('185', '2', '5'), ('188', '2', '6'), ('184', '2', '9'), ('192', '2', '10'), ('193', '2', '11'), ('183', '2', '12'), ('186', '2', '13'), ('187', '2', '14'), ('189', '2', '15'), ('190', '2', '16'), ('163', '25', '1'), ('164', '25', '10'), ('165', '25', '11'), ('205', '26', '1'), ('194', '26', '2'), ('195', '26', '3'), ('196', '26', '4'), ('199', '26', '5'), ('202', '26', '6'), ('198', '26', '9'), ('206', '26', '10'), ('207', '26', '11'), ('197', '26', '12'), ('200', '26', '13'), ('201', '26', '14'), ('203', '26', '15'), ('204', '26', '16'), ('219', '27', '1'), ('208', '27', '2'), ('209', '27', '3'), ('210', '27', '4'), ('213', '27', '5'), ('216', '27', '6'), ('212', '27', '9'), ('220', '27', '10'), ('221', '27', '11'), ('211', '27', '12'), ('214', '27', '13'), ('215', '27', '14'), ('217', '27', '15'), ('218', '27', '16');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID编号',
  `username` varchar(60) DEFAULT NULL COMMENT '用户名',
  `password` varchar(60) DEFAULT NULL COMMENT '密码',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `status` int(2) DEFAULT NULL COMMENT '是否禁用 1是 2否',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `error_date` datetime DEFAULT NULL COMMENT '登录错误时间',
  `error_time` int(3) DEFAULT NULL COMMENT '一段时间内登录出错次数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `username_2` (`username`),
  KEY `password` (`password`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统用户表';

-- ----------------------------
--  Records of `sys_user`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '系统超级管理员', '18646888888', '2', '2017-07-25 13:03:52', null, '0', '2017-06-07 10:11:35', '2017-06-13 10:39:12', '1', '1'), ('3', 'jeeboom', '46f94c8de14fb36680850768ff1b7f2a', '系统管理员', '18212345678', '2', '2017-07-01 16:44:39', null, '0', '2017-06-13 02:24:02', '2017-06-16 00:31:17', null, '1'), ('4', 'yunying', '46f94c8de14fb36680850768ff1b7f2a', '运营人员', '13811223344', '2', '2017-06-13 02:26:55', null, '0', '2017-06-13 02:26:38', '2017-06-13 02:26:38', '3', '3'), ('5', 'h5admin', '46f94c8de14fb36680850768ff1b7f2a', 'H5开发', '18845612347', '2', null, null, null, '2017-06-13 10:52:20', '2017-07-20 12:02:04', '1', '1');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID编号',
  `sys_user_id` bigint(20) NOT NULL COMMENT '系统用户ID',
  `sys_role_id` bigint(20) NOT NULL COMMENT '系统角色ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_user_id` (`sys_user_id`,`sys_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统用户角色关联表';

-- ----------------------------
--  Records of `sys_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('89', '1', '1'), ('90', '1', '2'), ('97', '3', '1'), ('84', '4', '1'), ('112', '5', '1'), ('113', '5', '2'), ('114', '5', '25'), ('115', '5', '26');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
