/*
Navicat MySQL Data Transfer

Source Server         : hotel
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : hotel

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2022-08-10 20:16:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `habron_levelsystem_levels`
-- ----------------------------
DROP TABLE IF EXISTS `habron_levelsystem_levels`;
CREATE TABLE `habron_levelsystem_levels` (
  `level` int(11) NOT NULL,
  `XP` int(11) DEFAULT NULL,
  PRIMARY KEY (`level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of habron_levelsystem_levels
-- ----------------------------
INSERT INTO `habron_levelsystem_levels` VALUES ('1', '100');
INSERT INTO `habron_levelsystem_levels` VALUES ('2', '500');
INSERT INTO `habron_levelsystem_levels` VALUES ('3', '1200');

-- ----------------------------
-- Table structure for `habron_levelsystem_tasks`
-- ----------------------------
DROP TABLE IF EXISTS `habron_levelsystem_tasks`;
CREATE TABLE `habron_levelsystem_tasks` (
  `id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_bin NOT NULL,
  `description` varchar(1000) COLLATE utf8_bin NOT NULL,
  `image` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `type` enum('','g','e') COLLATE utf8_bin NOT NULL,
  `room_id` int(11) DEFAULT NULL,
  `XP` int(11) NOT NULL,
  `item_id` int(11) DEFAULT NULL,
  `enabled` enum('1','0') COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of habron_levelsystem_tasks
-- ----------------------------
INSERT INTO `habron_levelsystem_tasks` VALUES ('3', 'Habrons TESTT', 'Eyup loves you b', 'https://images.habbo.com/habbo-web/america/nl/assets/images/app_summary_image-1200x628.3bc8bbb2.png', 'e', '54', '500', '2595974', '1');

-- ----------------------------
-- Table structure for `habron_levelsystem_users`
-- ----------------------------
DROP TABLE IF EXISTS `habron_levelsystem_users`;
CREATE TABLE `habron_levelsystem_users` (
  `id` int(11) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `XP` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of habron_levelsystem_users
-- ----------------------------
INSERT INTO `habron_levelsystem_users` VALUES ('36', '3', '625');

-- ----------------------------
-- Table structure for `habron_levelsystem_users_data`
-- ----------------------------
DROP TABLE IF EXISTS `habron_levelsystem_users_data`;
CREATE TABLE `habron_levelsystem_users_data` (
  `user_id` int(11) DEFAULT NULL,
  `level_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of habron_levelsystem_users_data
-- ----------------------------
INSERT INTO `habron_levelsystem_users_data` VALUES ('36', '3');

-- ----------------------------
-- Table structure for `habron_levelsystem_users_info`
-- ----------------------------
DROP TABLE IF EXISTS `habron_levelsystem_users_info`;
CREATE TABLE `habron_levelsystem_users_info` (
  `user_id` int(11) NOT NULL,
  `respects` int(11) NOT NULL,
  `respects_given` int(11) NOT NULL,
  `messages_amount` int(11) NOT NULL,
  `online_time` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of habron_levelsystem_users_info
-- ----------------------------
INSERT INTO `habron_levelsystem_users_info` VALUES ('36', '0', '0', '1193', '120');
