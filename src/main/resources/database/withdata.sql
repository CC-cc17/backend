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

 Date: 21/02/2024 20:07:50
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
-- Records of company_detail
-- ----------------------------
INSERT INTO `company_detail` VALUES (200, 202402, '小天才公司', 5, 'Any', '2024-02-29', '2024-03-30', 4, '66611171', '小李', '1232131', '我不是天才');
INSERT INTO `company_detail` VALUES (201, 202403, '可口可乐有限公司', 6, 'Any', '2024-02-27', '2024-03-02', 2, '可口可乐公司', '小张', '13712313112', '上读路');

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
-- Records of feedback
-- ----------------------------

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
-- Records of industry
-- ----------------------------
INSERT INTO `industry` VALUES (1, '农,林,牧,渔业');
INSERT INTO `industry` VALUES (2, '采矿业');
INSERT INTO `industry` VALUES (3, '电力,热力,燃气及水生产和供应业');
INSERT INTO `industry` VALUES (4, '建筑业');
INSERT INTO `industry` VALUES (5, '制造业');
INSERT INTO `industry` VALUES (6, '批发和零售业');
INSERT INTO `industry` VALUES (7, '交通运输,仓储和邮政业');
INSERT INTO `industry` VALUES (8, '住宿和餐饮业');
INSERT INTO `industry` VALUES (9, '信息传输,软件和信息技术服务业');
INSERT INTO `industry` VALUES (10, '金融业');
INSERT INTO `industry` VALUES (11, '房地产业');
INSERT INTO `industry` VALUES (12, '租赁和商务服务业');
INSERT INTO `industry` VALUES (13, '科学研究和技术服务业');
INSERT INTO `industry` VALUES (14, '水利,环境和公共设施管理业');
INSERT INTO `industry` VALUES (15, '居民服务,修理和其他服务业');
INSERT INTO `industry` VALUES (16, '教育');
INSERT INTO `industry` VALUES (17, '卫生和社会工作');
INSERT INTO `industry` VALUES (18, '文化,体育和娱乐业');
INSERT INTO `industry` VALUES (19, '公共管理,社会保障和社会组织');
INSERT INTO `industry` VALUES (20, '国际组织');

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
-- Records of job_match
-- ----------------------------
INSERT INTO `job_match` VALUES (300, 100, 201, '2024-02-20 13:36:40');
INSERT INTO `job_match` VALUES (301, 101, 201, '2024-02-20 18:18:19');
INSERT INTO `job_match` VALUES (302, 102, 201, '2024-02-21 19:21:39');
INSERT INTO `job_match` VALUES (303, 104, 201, '2024-02-21 20:03:19');
INSERT INTO `job_match` VALUES (304, 105, 201, '2024-02-21 20:05:40');

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
-- Records of student_detail
-- ----------------------------
INSERT INTO `student_detail` VALUES (100, 202405, '张飞', 6, 18, 'Male', '2024-01-31', '2024-04-29', 1, '我叫小张', '大张', '8888888888');
INSERT INTO `student_detail` VALUES (101, 202407, '李航', 6, 18, 'Male', '2024-01-31', '2024-03-30', 1, '我叫做李航', '李文', '12838929323');
INSERT INTO `student_detail` VALUES (102, 202443, '张老师', 6, 18, 'Male', '2024-01-31', '2024-03-30', 1, '张老师', '小张', '232131231231');
INSERT INTO `student_detail` VALUES (103, 202444, '辉戈', 3, 19, 'Male', '2024-02-29', '2024-03-29', 0, '无', '辉辉', '987654321');
INSERT INTO `student_detail` VALUES (104, 202409, '皇忍心', 6, 16, 'Female', '2024-02-17', '2024-03-17', 1, '我喜欢可口可乐', '皇仁', '13233122142');
INSERT INTO `student_detail` VALUES (105, 202411, '唐仁学', 6, 17, 'Male', '2024-02-03', '2024-03-04', 1, '我叫唐仁学', '唐山', '12393828423');

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
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (202401, 'admin', '$2a$10$3tWp4RQD2vlRwTFK3Jo.FuNkossK0KKa0E9qctUJOCRWdmcRZ/6fy', '18884162616', '111@qq.com', 'admin', '2024-02-17 23:31:48');
INSERT INTO `sys_user` VALUES (202402, 'peterzhang', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '76066814383', 'kwokkuenfung5@icloud.com', 'company', '2024-01-14 00:00:00');
INSERT INTO `sys_user` VALUES (202403, 'company', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '7601692120', 'tszhin1012@mail.com', 'company', '2024-01-27 00:00:00');
INSERT INTO `sys_user` VALUES (202404, 'Yuen111', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '2036479682', 'tszcyue9@gmail.com', 'company', '2024-02-12 00:00:00');
INSERT INTO `sys_user` VALUES (202405, 'student', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '14073580116', 'jesus9@outlook.com', 'student', '2024-01-21 00:00:00');
INSERT INTO `sys_user` VALUES (202406, 'Johnson', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '2018109810', 'johar@gmail.com', 'company', '2024-02-01 00:00:00');
INSERT INTO `sys_user` VALUES (202407, 'Hung', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '16167966547', 'hchunyu8@gmail.com', 'student', '2024-01-08 00:00:00');
INSERT INTO `sys_user` VALUES (202408, 'Ichikawa', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '216381718', 'reichikawa1105@icloud.com', 'student', '2024-01-17 00:00:00');
INSERT INTO `sys_user` VALUES (202409, 'Hsuan', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '18138993297', 'chunyu6@gmail.com', 'student', '2024-01-31 00:00:00');
INSERT INTO `sys_user` VALUES (202410, 'Maeda', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '19616538356', 'mayamato@outlook.com', 'company', '2024-02-15 00:00:00');
INSERT INTO `sys_user` VALUES (202411, 'Tsang', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '14756435746', 'tkw@outlook.com', 'student', '2024-02-02 00:00:00');
INSERT INTO `sys_user` VALUES (202412, 'Yamada', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '17207126188', 'takuyyamada@gmail.com', 'student', '2024-01-23 00:00:00');
INSERT INTO `sys_user` VALUES (202413, 'Morgan', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '16245838789', 'wmorga@gmail.com', 'company', '2024-02-13 00:00:00');
INSERT INTO `sys_user` VALUES (202414, 'Yuen', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '7550997287', 'kkyu5@icloud.com', 'student', '2024-01-06 00:00:00');
INSERT INTO `sys_user` VALUES (202415, 'Boyd', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '15032235679', 'boydju9@gmail.com', 'company', '2024-01-30 00:00:00');
INSERT INTO `sys_user` VALUES (202416, 'Jordan', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '7694281394', 'jocarol@gmail.com', 'company', '2024-02-13 00:00:00');
INSERT INTO `sys_user` VALUES (202417, 'Yeow', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '19689145429', 'syyeow@hotmail.com', 'company', '2024-01-30 00:00:00');
INSERT INTO `sys_user` VALUES (202418, 'Bennett', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '76023975299', 'barbara75@hotmail.com', 'student', '2024-01-04 00:00:00');
INSERT INTO `sys_user` VALUES (202419, 'Soto', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '2153923677', 'sotobra@mail.com', 'student', '2024-01-10 00:00:00');
INSERT INTO `sys_user` VALUES (202420, 'Schmidt', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '205815763', 'schmikathy5@gmail.com', 'company', '2024-02-16 00:00:00');
INSERT INTO `sys_user` VALUES (202421, 'Sano', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '16930221735', 'sano82@outlook.com', 'student', '2024-01-21 00:00:00');
INSERT INTO `sys_user` VALUES (202422, 'Martin', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '2846132018', 'marticlifford1965@icloud.com', 'student', '2024-02-09 00:00:00');
INSERT INTO `sys_user` VALUES (202423, 'Yue', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '16807961676', 'ykwokming@mail.com', 'student', '2024-01-09 00:00:00');
INSERT INTO `sys_user` VALUES (202424, 'Lau', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '18382826513', 'lwingsze@icloud.com', 'student', '2024-01-12 00:00:00');
INSERT INTO `sys_user` VALUES (202425, 'Ono', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '13000816066', 'onoryot@mail.com', 'student', '2024-02-15 00:00:00');
INSERT INTO `sys_user` VALUES (202426, 'Imai', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '76024059562', 'imai1010@icloud.com', 'company', '2024-01-16 00:00:00');
INSERT INTO `sys_user` VALUES (202427, 'Kondo', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '17593534661', 'ks908@yahoo.com', 'company', '2024-02-08 00:00:00');
INSERT INTO `sys_user` VALUES (202428, 'Dai', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '17997613535', 'chiyuen123@outlook.com', 'company', '2024-01-21 00:00:00');
INSERT INTO `sys_user` VALUES (202429, 'Brown', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '75567717457', 'joshuabr@icloud.com', 'student', '2024-01-16 00:00:00');
INSERT INTO `sys_user` VALUES (202430, 'Sato', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '103441454', 'sato4@gmail.com', 'company', '2024-02-03 00:00:00');
INSERT INTO `sys_user` VALUES (202431, 'Hamilton', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '284988136', 'andreahamilton77@gmail.com', 'student', '2024-01-19 00:00:00');
INSERT INTO `sys_user` VALUES (202432, '你好', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '18884162616', 'qqweqqw@qq.com', 'student', '2024-02-18 11:03:39');
INSERT INTO `sys_user` VALUES (202433, 'xiaozhang', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '132831232', 'qweq@qq.com', 'company', '2024-02-18 11:05:04');
INSERT INTO `sys_user` VALUES (202434, 'canyon', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '12372131218', 'sadasda', 'student', '2024-02-18 11:11:02');
INSERT INTO `sys_user` VALUES (202435, 'xiaozhang2', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '12331312', '31313', 'company', '2024-02-18 11:11:34');
INSERT INTO `sys_user` VALUES (202436, 'johnchu', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '19980853324', 'idk@qq.com', 'admin', '2024-02-18 11:22:38');
INSERT INTO `sys_user` VALUES (202437, 'nihaohao', '$2a$10$VDT42U4HII5tMgvTorsIbOqdXYpsfZfIyTdNFQf3nFHLtEZBLndNS', '1231312321', 'wewqeqe', 'company', '2024-02-18 11:23:49');
INSERT INTO `sys_user` VALUES (202443, 'zhanglaoshi', '$2a$10$TXIsEa4xyxf/Pkbkc0XB6unTek1kqLQmr1tee3wk1P2k2sL//EB5K', '88888888', 'daasddsa@qq.com', 'student', '2024-02-20 22:52:36');
INSERT INTO `sys_user` VALUES (202444, 'qwer', '$2a$10$Z1VPVshbcydVgAytdVeqXePfOutrHIKHGZhfXs29KvNncvUGj6J0C', '123456789', 'gg@gg.com', 'student', '2024-02-21 19:07:11');

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
