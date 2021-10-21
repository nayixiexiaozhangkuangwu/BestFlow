
-- 主任务表

create table flow_info
(
    id          varchar(36)              null comment '主键ID',
    obj_id      varchar(36)   default '' not null comment '操作对象ID',
    flow_action varchar(16)   default '' not null comment '任务流action',
    flow_param  varchar(1024) default '' not null comment '任务流详情',
    flow_state  varchar(8)    default '' not null comment '任务流状态',
    err_msg     text          default '' not null comment '错误信息',
    insert_date date                     not null comment '插入时间',
    update_date date                     not null comment '更新日期',
    constraint flow_info_pk
        primary key (id)
);

-- 子任务表

create table flow_sub
(
    id          varchar(36)            null comment '主键ID',
    obj_id      varchar(36) default '' not null comment '操作对象ID',
    flow_id     varchar(36) default '' not null comment '主任务ID',
    sub_action  varchar(16) default '' not null comment '子任务action',
    sub_state   varchar(8)  default '' not null comment '任务流状态',
    err_msg     text        default '' not null comment '错误信息',
    insert_date date                   not null comment '插入时间',
    update_date date                   not null comment '更新日期',
    constraint flow_sub_pk
        primary key (id)
);

