CREATE TABLE `tb_user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(100) DEFAULT NULL COMMENT '用户名',
  `password` VARCHAR(100) DEFAULT NULL COMMENT '密码',
  `name` VARCHAR(100) DEFAULT NULL COMMENT '姓名',
  `age` INT(10) DEFAULT NULL COMMENT '年龄',
  `sex` TINYINT(1) DEFAULT NULL COMMENT '性别:1男性 2女性',
  `birthday` DATE DEFAULT NULL COMMENT '出生日期',
  `note` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `created` DATETIME DEFAULT NULL COMMENT '创建时间',
  `updated` DATETIME DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username`(`user_name`)
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;