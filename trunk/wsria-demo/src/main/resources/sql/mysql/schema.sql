drop table if exists city;

/*==============================================================*/
/* Table: city                                                  */
/*==============================================================*/
create table city
(
   ID                   bigint(20) not null auto_increment comment '城市ID',
   CITY_NAME            varchar(20) not null comment '城市名称',
   SUPER_ID             bigint(20) not null comment '上级城市(没有为-1)',
   REMARK               varchar(255) comment '城市备注',
   primary key (ID)
);

alter table city comment '城市';
