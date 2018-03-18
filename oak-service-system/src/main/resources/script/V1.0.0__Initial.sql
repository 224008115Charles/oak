CREATE TABLE `sys_user`  (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `login` varchar(50) NOT NULL COMMENT '登录名',
    `password` varchar(50) NOT NULL COMMENT '密码',
    `name` varchar(50) COMMENT '姓名',
    `email` varchar(50) COMMENT '邮件',
    `phone` varchar(50) COMMENT '电话',
    `mobile` varchar(50) COMMENT '手机',
    `is_enabled` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否可用',
    `remarks` varchar(255) COMMENT '备注',
    `gmt_create` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `uk_login`(`login`)
) COMMENT = '系统用户';

CREATE TABLE `sys_role`  (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `code` varchar(50) NOT NULL COMMENT '角色代码',
    `name` varchar(50) NOT NULL COMMENT '角色名称',
    `remarks` varchar(255) COMMENT '备注',
    `gmt_create` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` tinyint(1) UNSIGNED DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `uk_name`(`name`)
) COMMENT = '系统角色';

CREATE TABLE `sys_user_role`  (
    `user_id` int(11) NOT NULL COMMENT '用户ID',
    `role_id` int(11) NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`user_id`, `role_id`)
) COMMENT = '用户角色';

CREATE TABLE `sys_menu`  (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `pid` int(11) NOT NULL DEFAULT 0 COMMENT '父ID',
    `name` varchar(50) NOT NULL COMMENT '菜单名称',
    `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
    `href` varchar(255) COMMENT '链接',
    `icon` varchar(50) COMMENT '图标',
    `url` varchar(100) COMMENT '请求路径',
    `method` varchar(10) COMMENT '请求方法',
    `type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '菜单类型（0菜单 1权限）',
    `permission` varchar(200) COMMENT '权限标识',
    `remarks` varchar(255) COMMENT '备注',
    `gmt_create` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` tinyint(1) UNSIGNED DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (`id`)
) COMMENT = '系统菜单';

CREATE TABLE `sys_role_menu`  (
    `role_id` int(11) NOT NULL COMMENT '角色ID',
    `menu_id` int(11) NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (`role_id`, `menu_id`)
) COMMENT = '角色菜单';

CREATE TABLE `sys_org`  (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `pid` int(11) NOT NULL DEFAULT 0 COMMENT '父ID',
    `name` varchar(50) NOT NULL COMMENT '组织名称',
    `remarks` varchar(255) COMMENT '备注',
    `gmt_create` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` tinyint(1) UNSIGNED DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (`id`)
) COMMENT = '组织机构';

CREATE TABLE `sys_user_org`  (
    `user_id` int(11) NOT NULL COMMENT '用户ID',
    `org_id` int(11) NOT NULL COMMENT '组织ID',
    PRIMARY KEY (`user_id`, `org_id`)
) COMMENT = '用户组织';
