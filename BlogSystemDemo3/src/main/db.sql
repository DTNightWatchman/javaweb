create table user(
     userId int primary key auto_increment,
     username varchar(128) unique,
     password varchar(128),
     number int,
     email varchar(128) NULL,
     github varchar(128) NULL
);
CREATE TABLE blog  (
     `blogId` int NOT NULL AUTO_INCREMENT,
     `title` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
     `blogDesc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
     `postTime` datetime(0) NULL DEFAULT NULL,
     `userId` int NULL DEFAULT NULL,
     `likes` int NULL DEFAULT NULL,
     PRIMARY KEY (`blogId`)
);

drop table blogdetail;
CREATE TABLE blogDetail  (
     `blogId` int NOT NULL,
     `content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
     PRIMARY KEY (`blogId`)
);

CREATE TABLE discuss  (
     `discussId` int NOT NULL AUTO_INCREMENT,
     `blogId` int NULL DEFAULT NULL,
     `userId` int NULL DEFAULT NULL,
     `username` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
     `postTime` datetime(0) NULL DEFAULT NULL,
     `content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
     PRIMARY KEY (`discussId`)
);

ALTER TABLE blog
    ADD COLUMN `status` int NULL AFTER likes;