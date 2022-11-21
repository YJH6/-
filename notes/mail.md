多个发送者（前端暂设计为只有一个发送者）和多个接收者



2、使用模板发送

使用Freemarker作为模版引擎

3、发送者：sys_mail_sender

| 列名        | 含义                     | 类型         | 备注   |
| ----------- | ------------------------ | ------------ | ------ |
| id          | 主键，只做区分，不含业务 | int          |        |
| send_addr   | 邮箱地址                 | varchar(254) | unique |
| sender_name | 发送者名称               | varchar(40)  |        |
| password    | 邮箱授权码（非密码）     | varchar(40)  |        |
| smtp_host   | smtp服务器地址           | varchar(40)  |        |
| smtp_port   | smtp服务器端口           | int          |        |
| ssl_enable  | 是否使用SSL安全连接      | bit(1)       |        |
| type        | 邮箱类型                 | int          |        |
| enable      | 是否可用                 | bit(1)       |        |
| create_time | 创建时间                 | datetime     |        |
| update_time | 修改时间                 | datetime     |        |

4、接收者：sys_mail_receiver

| 列名          | 含义         | 类型         | 备注   |
| ------------- | ------------ | ------------ | ------ |
| id            | 主键         | int          |        |
| receive_addr  | 接收邮箱地址 | varchar(254) | unique |
| receiver_name | 接收者名称   | varchar(40)  |        |
| type          | 邮箱类型     | int          |        |
| enable        | 是否可用     | bit(1)       |        |
| create_time   | 创建时间     | datetime     |        |
| update_time   | 修改时间     | datetime     |        |

5、发送者创建和修改暂为一个接口，如后多个发送者则将修改改为根据id，创建时就不需要传id到后端了。













附录：

建表语句：

```sql
drop table if exists `sys_mail_sender`;
CREATE TABLE `sys_mail_sender` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `send_addr` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邮箱地址',
  `sender_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '发送者名称',
  `password` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱授权码',
  `smtp_host` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'smtp服务器地址',
  `smtp_port` int DEFAULT NULL COMMENT 'smtp服务器端口',
  `ssl_enable` bit(1) DEFAULT b'0' COMMENT '是否使用SSL安全连接',
  `type` int DEFAULT '1' COMMENT '邮箱类型：1-系统邮箱receiver，其他邮箱receiver',
  `enable` bit(1) DEFAULT b'0' COMMENT '是否可用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	UNIQUE(send_addr),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

drop table if exists `sys_mail_receiver`;
CREATE TABLE `sys_mail_receiver` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `receive_addr` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邮箱接收地址',
  `receiver_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '接收者名称',
  `type` int DEFAULT '1' COMMENT '邮箱类型：1-系统邮箱receiver，其他邮箱receiver',
  `enable` bit(1) DEFAULT b'0' COMMENT '是否可用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	UNIQUE(`receive_addr`),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
```

