/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : test3

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 21/02/2024 20:08:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for company_detail
-- ----------------------------
DROP TABLE IF EXISTS `company_detail`;
CREATE TABLE `company_detail`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` int NOT NULL COMMENT '企业账户id',
  `company_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '企业名称',
  `industry_type` int NOT NULL COMMENT '行业类型',
  `gender_require` enum('Male','Female','Any') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '性别需求',
  `position_start` date NOT NULL COMMENT '开始时间',
  `position_end` date NOT NULL COMMENT '结束时间',
  `quota` int NOT NULL COMMENT '名额',
  `position_describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '职位介绍',
  `contact_person` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人电话',
  `company_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '企业地址',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uid`(`uid` ASC) USING BTREE,
  INDEX `ind_id`(`industry_type` ASC) USING BTREE,
  CONSTRAINT `ind_id` FOREIGN KEY (`industry_type`) REFERENCES `industry` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `sys_user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 202 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '反馈id',
  `job_match_id` int NOT NULL COMMENT '配对表id',
  `student_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生评价',
  `student_rating` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生打分',
  `student_feedback_time` datetime NULL DEFAULT NULL COMMENT '学生反馈时间',
  `company_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '企业评价',
  `company_rating` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '企业打分',
  `company_feedback_time` datetime NULL DEFAULT NULL COMMENT '企业反馈时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `job_match_id`(`job_match_id` ASC) USING BTREE,
  CONSTRAINT `job_match_id` FOREIGN KEY (`job_match_id`) REFERENCES `job_match` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 500 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for industry
-- ----------------------------
DROP TABLE IF EXISTS `industry`;
CREATE TABLE `industry`  (
  `id` int NOT NULL COMMENT '行业id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '行业名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for job_match
-- ----------------------------
DROP TABLE IF EXISTS `job_match`;
CREATE TABLE `job_match`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '配对id',
  `student_id` int NOT NULL COMMENT '配对的学生id',
  `company_id` int NOT NULL COMMENT '配对的企业id',
  `match_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '配对时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `student_id`(`student_id` ASC) USING BTREE,
  INDEX `company_id`(`company_id` ASC) USING BTREE,
  CONSTRAINT `job_match_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `job_match_ibfk_2` FOREIGN KEY (`company_id`) REFERENCES `company_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 305 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for student_detail
-- ----------------------------
DROP TABLE IF EXISTS `student_detail`;
CREATE TABLE `student_detail`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` int NOT NULL COMMENT '学生账户id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生姓名',
  `industry_type` int NOT NULL COMMENT '行业类型',
  `age` int NOT NULL COMMENT '年龄',
  `gender` enum('Male','Female') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '性别',
  `available_start` date NOT NULL COMMENT '可开始时间',
  `available_end` date NOT NULL COMMENT '可结束时间',
  `match_status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '配对状态',
  `describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '自我介绍',
  `supervisor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '监护人姓名',
  `supervisor_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '监护人联系电话',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`uid` ASC) USING BTREE,
  INDEX `industry_id`(`industry_type` ASC) USING BTREE,
  CONSTRAINT `industry_id` FOREIGN KEY (`industry_type`) REFERENCES `industry` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_id` FOREIGN KEY (`uid`) REFERENCES `sys_user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `uid` int NOT NULL AUTO_INCREMENT COMMENT '账户UID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账户名称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账户密码',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系邮箱',
  `user_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户类型',
  `create_time` datetime NOT NULL COMMENT '账户创建时间',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 202445 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Procedure structure for automated_pairing
-- ----------------------------
DROP PROCEDURE IF EXISTS `automated_pairing`;
delimiter ;;
CREATE PROCEDURE `automated_pairing`()
BEGIN
    -- 定义变量
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_student_id INT;
    DECLARE v_company_id INT;
    DECLARE v_match_time TIMESTAMP;

    -- 定义游标
    DECLARE cur1 CURSOR FOR
        SELECT 
            s.id, c.id
        FROM 
            student_detail s
        JOIN 
            company_detail c ON s.industry_type = c.industry_type
        WHERE 
            (c.gender_require = 'Any' OR c.gender_require = s.gender)
            AND s.available_start <= c.position_start
            AND s.available_end >= c.position_end
            AND s.match_status = 0 -- 仅选择未被匹配的学生
            AND c.quota > 0 -- 仅选择有名额的公司
        FOR UPDATE; -- 使用悲观锁锁定选中的行

    -- 定义未找到记录时的继续处理程序
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    -- 开启事务
    START TRANSACTION;

    -- 打开游标
    OPEN cur1;

    read_loop: LOOP
        -- 从游标取得数据
        FETCH cur1 INTO v_student_id, v_company_id;
        
        -- 当游标结束时退出循环
        IF done THEN
            LEAVE read_loop;
        END IF;

        -- 更新公司配额，使用悲观锁确保数据一致性
        UPDATE company_detail SET quota = quota - 1 WHERE id = v_company_id AND quota > 0;

        -- 如果公司配额更新成功，则更新学生状态
        IF ROW_COUNT() > 0 THEN
            UPDATE student_detail SET match_status = 1 WHERE id = v_student_id AND match_status = 0;
            
            -- 如果学生状态更新成功，则记录配对结果
            IF ROW_COUNT() > 0 THEN
                -- 将配对结果插入到job_match表
                INSERT INTO job_match (student_id, company_id, match_time)
                VALUES (v_student_id, v_company_id, NOW());
            END IF;
        END IF;
        
    END LOOP;

    CLOSE cur1;

    -- 提交事务
    COMMIT;

END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
