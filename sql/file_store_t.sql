/*
 Navicat Premium Data Transfer

 Source Server         : 42.194.251.131
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : 42.194.251.131:3306
 Source Schema         : jelly

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 31/08/2022 17:00:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file_store_t
-- ----------------------------
DROP TABLE IF EXISTS `file_store_t`;
CREATE TABLE `file_store_t`  (
  `store_id` bigint NOT NULL COMMENT '记录标识',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL COMMENT '更新时间',
  `file_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件检索名称',
  `file_real_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件真实存储名称',
  `share_link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件分享链接',
  `share_time` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件分享时长  -1：永久 1：7天   2：14天',
  PRIMARY KEY (`store_id`) USING BTREE,
  FULLTEXT INDEX `file_name_index`(`file_name`) COMMENT '全文搜索'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件存储记录表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
