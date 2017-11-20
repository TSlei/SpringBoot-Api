-- Create Database
CREATE DATABASE springboot;

-- Create Table
CREATE TABLE springboot.`t_user` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `age` int(10) DEFAULT NULL,
  `hight` int(10) DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `birthday` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- insert user
insert into `t_user` (`id`, `name`, `age`, `hight`, `sex`, `description`, `birthday`) values('1','张三丰','16','180','男','习武奇才','1988-12-01');
insert into `t_user` (`id`, `name`, `age`, `hight`, `sex`, `description`, `birthday`) values('2','周芷若','15','165','女','大美女','1996-12-11');
insert into `t_user` (`id`, `name`, `age`, `hight`, `sex`, `description`, `birthday`) values('4','李云龙','12','170','男','天才','2017');