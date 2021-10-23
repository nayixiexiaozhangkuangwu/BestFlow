

-- auto-generated definition
create table exec_info
(
    id          varchar(36)              not null comment '主键ID'
        primary key,
    obj_id      varchar(36)   default '' not null comment '操作对象ID',
    user_id     int                      not null comment '用户ID',
    flow_action varchar(16)   default '' not null comment '任务流action',
    flow_param  varchar(1024) default '' not null comment '任务流详情',
    flow_state  varchar(8)    default '' not null comment '任务流状态',
    err_msg     text          default '' not null comment '错误信息',
    insert_date datetime                 not null comment '插入时间',
    update_date datetime                 not null comment '更新日期'
)
    comment '主任务执行表';



-- auto-generated definition
create table exec_sub
(
    id          varchar(36)            not null comment '主键ID'
        primary key,
    obj_id      varchar(36) default '' not null comment '操作对象ID',
    flow_id     varchar(36) default '' not null comment '主任务ID',
    sub_action  varchar(16) default '' not null comment '子任务action',
    sub_state   varchar(8)  default '' not null comment '任务流状态',
    err_msg     text        default '' not null comment '错误信息',
    insert_date datetime               not null comment '插入时间',
    update_date datetime               not null comment '更新日期'
)
    comment '子任务执行表';


-- auto-generated definition
create table flow_main
(
    id          tinyint auto_increment comment '主键'
        primary key,
    flow_name   varchar(16) default '' not null comment '任务流名称',
    insert_date datetime               not null comment '插入时间',
    update_date datetime               not null comment '更新时间'
)
    comment '工作流主任务表';


-- auto-generated definition
create table flow_main_sub_task
(
    id          int auto_increment comment '主键'
        primary key,
    main_id     int      not null comment '主任务ID',
    sub_id      int      not null comment '子任务ID',
    project_id  int      not null comment '项目ID',
    exec_level  int      not null comment '子任务执行级别',
    insert_date datetime not null comment '插入时间',
    update_date datetime not null comment '更新时间'
)
    comment '任务关联表';

-- auto-generated definition
create table flow_project
(
    id           tinyint auto_increment comment '主键'
        primary key,
    project_name varchar(16)  default '' not null comment '项目名称',
    project_url  varchar(16)  default '' not null comment '项目URL',
    project_desc varchar(128) default '' not null comment '项目描述',
    insert_date  datetime                not null comment '插入时间',
    update_date  datetime                not null comment '更新时间'
)
    comment '工作流对接项目表';

-- auto-generated definition
create table flow_sub
(
    id          tinyint auto_increment comment '主键'
        primary key,
    sub_name    varchar(16) default '子任务名称' not null,
    insert_date datetime                    not null comment '插入时间',
    update_date datetime                    not null comment '更新时间'
)
    comment '工作流子任务表';


