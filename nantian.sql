/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : nantian

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 07/09/2023 21:25:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for job_test
-- ----------------------------
DROP TABLE IF EXISTS `job_test`;
CREATE TABLE `job_test`  (
  `TABLE_ID` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_RUN_ID` varchar(21) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`TABLE_ID`) USING BTREE,
  INDEX `JOB_RUN_ID`(`JOB_RUN_ID`) USING BTREE,
  CONSTRAINT `job_test_ibfk_1` FOREIGN KEY (`JOB_RUN_ID`) REFERENCES `uccp_eng_job_jnl` (`JOB_RUN_ID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of job_test
-- ----------------------------
INSERT INTO `job_test` VALUES ('12323', '1233');
INSERT INTO `job_test` VALUES ('43242', '212354');

-- ----------------------------
-- Table structure for uccp_eng_job_jnl
-- ----------------------------
DROP TABLE IF EXISTS `uccp_eng_job_jnl`;
CREATE TABLE `uccp_eng_job_jnl`  (
  `JOB_RUN_ID` varchar(21) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作业运行ID',
  `JOB_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作业ID',
  `START_TIME` date NULL DEFAULT NULL COMMENT '开始日期',
  `END_TIME` date NULL DEFAULT NULL COMMENT '结束日期',
  `JOB_TYPE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作业类型',
  `plat` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作业来源',
  `JOB_USER` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行作业用户',
  `JOB_ROLE` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行作业角色',
  `JOB_TENANT` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行作业租户',
  `JOB_STATE` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '0：准备，1：执行，2：结束',
  `JOB_RLT` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '1：正常，0：出错',
  `ERR_INFO` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '错误信息',
  `EXEC_ACTUON` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行动作',
  PRIMARY KEY (`JOB_RUN_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of uccp_eng_job_jnl
-- ----------------------------
INSERT INTO `uccp_eng_job_jnl` VALUES ('1233', '10001', '2023-07-16', '2023-07-17', 'A', 'nantian', 'zgh', 'employee', 'zgh', '2', '1', NULL, NULL);
INSERT INTO `uccp_eng_job_jnl` VALUES ('212354', '10002', '2023-07-17', '2023-07-18', 'B', 'yunzhiwei', 'zgh', 'employee', 'zgh', '1', '1', NULL, NULL);
INSERT INTO `uccp_eng_job_jnl` VALUES ('3214', '10004', '2023-07-19', '2023-07-20', 'B', 'nantian', 'zgh', 'employee', 'zgh', '0', '0', NULL, NULL);
INSERT INTO `uccp_eng_job_jnl` VALUES ('32412', '10005', '2023-07-16', '2023-07-17', 'A', 'yunzhiwei', 'zgh', 'employee', 'zgh', '2', '0', NULL, NULL);
INSERT INTO `uccp_eng_job_jnl` VALUES ('33123', '10003', '2023-07-18', '2023-07-19', 'A', 'yunzhiwei', 'zgh', 'employee', 'zgh', '0', '1', NULL, NULL);
INSERT INTO `uccp_eng_job_jnl` VALUES ('6341', '10006', '2023-07-17', '2023-07-18', 'B', 'nantian', 'zgh', 'employee', 'zgh', '1', '0', NULL, NULL);

-- ----------------------------
-- Table structure for uccp_eng_task_jnl
-- ----------------------------
DROP TABLE IF EXISTS `uccp_eng_task_jnl`;
CREATE TABLE `uccp_eng_task_jnl`  (
  `TASK_ID` varchar(21) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '任务运行ID',
  `JOB_RUN_ID` varchar(21) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作业运行ID',
  `JOB_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作业ID',
  `DEV_IP` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备IP',
  `START_TIME` date NULL DEFAULT NULL COMMENT '开始日期',
  `END_TIME` date NULL DEFAULT NULL COMMENT '结束日期',
  `TASK_STATE` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '0：准备，1：执行，2：结束',
  `TASK_RLT` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '1：正常，0：出错',
  `ERR_INFO` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '错误信息',
  PRIMARY KEY (`TASK_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of uccp_eng_task_jnl
-- ----------------------------
INSERT INTO `uccp_eng_task_jnl` VALUES ('101', '1233', '10001', '23124', '2023-07-16', '2023-07-17', '2', '1', NULL);
INSERT INTO `uccp_eng_task_jnl` VALUES ('102', '212354', '10002', '12312', '2023-07-17', '2023-07-18', '1', '1', NULL);
INSERT INTO `uccp_eng_task_jnl` VALUES ('103', '33123', '10003', '43243', '2023-07-18', '2023-07-19', '0', '1', NULL);
INSERT INTO `uccp_eng_task_jnl` VALUES ('104', '3214', '10004', '43245', '2023-07-19', '2023-07-20', '0', '0', 'error');
INSERT INTO `uccp_eng_task_jnl` VALUES ('105', '32412', '10005', '76862', '2023-07-16', '2023-07-17', '2', '0', 'error');
INSERT INTO `uccp_eng_task_jnl` VALUES ('106', '6341', '10006', '87463', '2023-07-17', '2023-07-18', '1', '0', 'error');

-- ----------------------------
-- Table structure for uccp_oid_info
-- ----------------------------
DROP TABLE IF EXISTS `uccp_oid_info`;
CREATE TABLE `uccp_oid_info`  (
  `OID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'OID',
  `OID_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'OID名称',
  `PUB_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '1：公共OID，0：私有OID',
  `OID_DEVICE_TYPE` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'OID类型：计算机，交换机，路由器，防火墙',
  `ENTERPRISE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '厂商',
  `OID_DEVICE_CODE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备型号',
  `OID_DEVICE_VERSION` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '版本',
  `DESCRIPTION` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`OID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of uccp_oid_info
-- ----------------------------
INSERT INTO `uccp_oid_info` VALUES ('1003', 'nantian', '0', 'A', 'nantian', 'nt1115', 'version1', 'private switchboard');
INSERT INTO `uccp_oid_info` VALUES ('100331233213 ', 'nantian', '0', 'A', 'nantian', 'nt1115', 'version1', 'private switchboard');
INSERT INTO `uccp_oid_info` VALUES ('11111', '13213', '0', 'C', '312', '', 'rwer', 'tyrwe');
INSERT INTO `uccp_oid_info` VALUES ('1231', '大飒飒的', '1', 'B', '', '', '', '');
INSERT INTO `uccp_oid_info` VALUES ('123123', '2312423', '0', 'C', NULL, NULL, NULL, NULL);
INSERT INTO `uccp_oid_info` VALUES ('1234563', 'dasda', '0', 'C', '', 'dasda', 'dsad', 'dasfe');
INSERT INTO `uccp_oid_info` VALUES ('1234876', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `uccp_oid_info` VALUES ('123543', 'werewe', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `uccp_oid_info` VALUES ('123987', 'adasda', '1', 'A', 'qew', 'rtff', 'sdg', 'dgsf');
INSERT INTO `uccp_oid_info` VALUES ('132123', '大苏打', '0', 'B', 'dasdas', 'sdasd', 'fsdfsd', 'ffsdfsdf');
INSERT INTO `uccp_oid_info` VALUES ('13672', 'asd', '1', 'C', 'fdsf', 'werrw', 'tewr', 'qwre');
INSERT INTO `uccp_oid_info` VALUES ('32342', '435热', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `uccp_oid_info` VALUES ('4236567', NULL, '1', 'A', '123', '46发顺丰的', '打撒十大', 'gdfdfs');
INSERT INTO `uccp_oid_info` VALUES ('52654', NULL, '0', 'B', 'qre', 'dsffg', 'eqwqr', NULL);

-- ----------------------------
-- Triggers structure for table uccp_eng_job_jnl
-- ----------------------------
DROP TRIGGER IF EXISTS `update_test`;
delimiter ;;
CREATE TRIGGER `update_test` AFTER UPDATE ON `uccp_eng_job_jnl` FOR EACH ROW BEGIN
UPDATE uccp_eng_task_jnl SET JOB_RUN_ID=new.JOB_RUN_ID WHERE JOB_RUN_ID=old.JOB_RUN_ID;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
