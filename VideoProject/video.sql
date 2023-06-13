/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : shixun

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 12/06/2023 21:34:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP DATABASE IF EXISTS `shixun`;
CREATE DATABASE `shixun` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `shixun`;
-- ----------------------------
-- Table structure for chapter
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '章节ID',
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '课程ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '章节名称',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '显示排序',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '作品章节' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of chapter
-- ----------------------------
INSERT INTO `chapter` VALUES ('1668187747476598785', '1668186640419450881', '第一讲 C语言简介', 1, '2023-06-12 17:25:27', '2023-06-12 18:43:28');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论用户Id',
  `course_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论课程id',
  `comment_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `gmt_create` datetime(0) NOT NULL COMMENT '评论时间',
  `reply_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '-1' COMMENT '回复用户id',
  `parent_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '-1' COMMENT '父评论id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `comment_user`(`user_id`) USING BTREE,
  INDEX `comment_course`(`course_id`) USING BTREE,
  INDEX `comment_parent`(`parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1668237341845651458', '1111', '1668186640419450881', '不错', '2023-06-12 20:42:31', '-1', '-1');
INSERT INTO `comment` VALUES ('1668237431758946305', '2222', '1668186640419450881', 'good!', '2023-06-12 20:42:53', '-1', '-1');
INSERT INTO `comment` VALUES ('1668237673799647234', '3333', '1668186640419450881', 'thanks!', '2023-06-12 20:43:50', '2222', '1668237431758946305');
INSERT INTO `comment` VALUES ('1668237812085850113', '4444', '1668186640419450881', 'you are welcome!', '2023-06-12 20:44:23', '3333', '1668237673799647234');
INSERT INTO `comment` VALUES ('1668245039349768193', '5555', '1668186640419450881', '哈哈哈哈', '2023-06-12 21:13:07', '4444', '1668237812085850113');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '课程ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '课程名称',
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '课程简介',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '作品简介' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1668186640419450881', 'C程序设计基础', '11C语言是富有活力的编程语言，广泛用于多种编程领域，在各类编程语言排行榜上常据榜首，其魅力毋庸置疑。 这是一门实践性很强的课程，​这里提供了基础的知识点讲解，配合编程训练，帮助你在实践中掌握编程知识，培养编程能力', '2023-06-12 17:21:03', '2023-06-12 18:51:23');

-- ----------------------------
-- Table structure for video_content
-- ----------------------------
DROP TABLE IF EXISTS `video_content`;
CREATE TABLE `video_content`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '视频ID',
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '-1' COMMENT '课程ID',
  `chapter_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '-1' COMMENT '章节ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '节点名称',
  `video_source_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '云端视频资源',
  `video_original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '原始文件名称',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序字段',
  `duration` float NOT NULL DEFAULT 0 COMMENT '视频时长（秒）',
  `size` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '视频源文件大小（KB）',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE,
  INDEX `idx_chapter_id`(`chapter_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '作品视频' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of video_content
-- ----------------------------
INSERT INTO `video_content` VALUES ('1668188528892198913', '1668186640419450881', '1668187747476598785', 'C语言的集成开发环境', '6b786bf0090371ee804a6732b78e0102', '287328e0-17e7ba46996.mp4', 1, 0, 1289838, '2023-06-12 17:28:33', '2023-06-12 17:28:33');
INSERT INTO `video_content` VALUES ('1668205638410387457', '1668186640419450881', '1668187747476598785', 'C程序的编写、编译和运行', 'e951e520090c71ee80346732b68e0102', '287328e0-17e7ba46996.mp4', 2, 0, 1289838, '2023-06-12 18:36:33', '2023-06-12 18:36:33');

SET FOREIGN_KEY_CHECKS = 1;
