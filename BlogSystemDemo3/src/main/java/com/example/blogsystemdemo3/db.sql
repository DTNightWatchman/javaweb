drop table blog;
CREATE TABLE blog (
   `blogId` int NOT NULL AUTO_INCREMENT,
   `title` varchar(1024) NULL,
   `blogDesc` varchar(1024) NULL,
   `postTime` datetime NULL,
   `userId` int NULL,
   `likes` int NULL,
   PRIMARY KEY (`blogId`)
);

CREATE TABLE blogDetail  (
    `blogId` int,
    `content` mediumtext,
    PRIMARY KEY (`blogId`)
);