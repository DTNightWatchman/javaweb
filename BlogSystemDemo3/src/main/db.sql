create table user(
     userId int primary key auto_increment,
     username varchar(128) unique,
     password varchar(128),
     number int,
     email varchar(128) NULL,
     github varchar(128) NULL
);