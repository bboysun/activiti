CREATE TABLE `virtual_account` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `bank_name` varchar(100) DEFAULT NULL COMMENT '银行名称',
  `deposit_bank` varchar(100) DEFAULT NULL COMMENT '开户行',
  `bank_account` varchar(100) DEFAULT NULL COMMENT '银行账号',
  `account_name` varchar(100) DEFAULT NULL COMMENT '户名',
  `use_status` int(11) DEFAULT '0' COMMENT '使用状态',
  `approval_status` int(11) DEFAULT '0' COMMENT '审批状态',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `create_by` varchar(64) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_by` varchar(64) DEFAULT NULL,
  `def_id` varchar(60) DEFAULT NULL,
  `inst_id` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='虚拟账号';
