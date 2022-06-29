create database if not exists blogsystem1;

use blogsystem1;
drop table if exists blog;
create table blog(
    blogId int primary key auto_increment,
    title varchar(1024),
    content mediumtext,
    postTime datetime,
    userId int
);
drop table if exists user;
create table user(
    userId int primary key auto_increment,
    username varchar(128) unique,
    password varchar(128),
    number int
);

drop table if exists discuss;
create table discuss(
    discussId int primary key auto_increment,
    blogId int,
    userId int,
    username varchar(128),
    postTime datetime,
    content mediumtext
);
insert into discuss values (null,1,1,"YT",now(),"评论");
insert into discuss values (3,1,"YT","评论");

-- 创建主机权限
CREATE USER `normal`@`localhost` IDENTIFIED BY '0000';

GRANT Delete, Select, Update ON `blogsystem1`.* TO `normal`@`localhost`;
