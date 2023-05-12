## 定义监控端表
#############################################################################
drop table if exists `sys_monitor`;

CREATE TABLE `sys_monitor` (
                               `monitor_id` bigint(64) NOT NULL AUTO_INCREMENT,
                               `monitor_name` varchar(64) NOT NULL ,
                               `monitor_url` varchar(64) NOT NULL ,
                               `permission_level` varchar(64) DEFAULT 'default',
                               `status` char(1) default '0',
                               `del_flag` char(1) default '0',
                               `create_by` varchar(64),
                               `create_time` datetime,
                               `update_by` varchar(64),
                               `update_time` datetime,
                               PRIMARY KEY (`monitor_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='监控端表';

insert into sys_monitor (monitor_id, monitor_name, monitor_url)
values (1, 'test', 'localhost:8080');
##############################################################################
