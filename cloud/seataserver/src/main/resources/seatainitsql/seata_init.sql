DROP TABLE IF EXISTS `branch_table`;
CREATE TABLE `branch_table`
(
    `branch_id`         bigint(20)    NOT NULL,
    `xid`               varchar(128)  NOT NULL,
    `transaction_id`    bigint(20)    NULL DEFAULT NULL,
    `resource_group_id` varchar(32)   NULL DEFAULT NULL,
    `resource_id`       varchar(256)  NULL DEFAULT NULL,
    `lock_key`          varchar(128)  NULL DEFAULT NULL,
    `branch_type`       varchar(8)    NULL DEFAULT NULL,
    `status`            tinyint(4)    NULL DEFAULT NULL,
    `client_id`         varchar(64)   NULL DEFAULT NULL,
    `application_data`  varchar(2000) NULL DEFAULT NULL,
    `gmt_create`        datetime(0)   NULL DEFAULT NULL,
    `gmt_modified`      datetime(0)   NULL DEFAULT NULL,
    PRIMARY KEY (`branch_id`) USING BTREE,
    INDEX `idx_xid` (`xid`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for global_table
-- ----------------------------
DROP TABLE IF EXISTS `global_table`;
CREATE TABLE `global_table`
(
    `xid`                       varchar(128)  NOT NULL,
    `transaction_id`            bigint(20)    NULL DEFAULT NULL,
    `status`                    tinyint(4)    NOT NULL,
    `application_id`            varchar(64)   NULL DEFAULT NULL,
    `transaction_service_group` varchar(64)   NULL DEFAULT NULL,
    `transaction_name`          varchar(64)   NULL DEFAULT NULL,
    `timeout`                   int(11)       NULL DEFAULT NULL,
    `begin_time`                bigint(20)    NULL DEFAULT NULL,
    `application_data`          varchar(2000) NULL DEFAULT NULL,
    `gmt_create`                datetime(0)   NULL DEFAULT NULL,
    `gmt_modified`              datetime(0)   NULL DEFAULT NULL,
    PRIMARY KEY (`xid`) USING BTREE,
    INDEX `idx_gmt_modified_status` (`gmt_modified`, `status`) USING BTREE,
    INDEX `idx_transaction_id` (`transaction_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for lock_table
-- ----------------------------
DROP TABLE IF EXISTS `lock_table`;
CREATE TABLE `lock_table`
(
    `row_key`        varchar(128) NOT NULL,
    `xid`            varchar(96)  NULL DEFAULT NULL,
    `transaction_id` mediumtext   NULL,
    `branch_id`      mediumtext   NULL,
    `resource_id`    varchar(256) NULL DEFAULT NULL,
    `table_name`     varchar(32)  NULL DEFAULT NULL,
    `pk`             varchar(32)  NULL DEFAULT NULL,
    `gmt_create`     datetime(0)  NULL DEFAULT NULL,
    `gmt_modified`   datetime(0)  NULL DEFAULT NULL,
    PRIMARY KEY (`row_key`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;
