CREATE TABLE admin_table  (
   `adminId` int primary key auto_increment,
   `username` varchar(128) unique,
   `password` varchar(128) NULL
);