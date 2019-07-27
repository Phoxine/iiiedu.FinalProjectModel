CREATE TABLE member(
mID decimal(4) identity PRIMARY KEY,
name varchar(10),
account varchar(20),
password varchar(20),
email varchar(50),
tel varchar(10),
addr varchar(100),
rdate datetime,
birthday datetime,
sex varchar(6)
);

CREATE TABLE vendor(
vID decimal(4) identity PRIMARY KEY,
vname varchar(100),
addr varchar(100),
tel varchar(10),
email varchar(50),
c_person varchar(10),
c_tel varchar(10)
);

CREATE TABLE product(
pID decimal(4) identity PRIMARY KEY,
pname varchar(100),
price decimal(6),
vID decimal(4) FOREIGN KEY REFERENCES vendor(vID),
amount decimal(3),
category varchar(20),
sdate datetime,
expdate datetime
);

CREATE TABLE ordertable(
oID decimal(4) identity PRIMARY KEY,
pID decimal(4) FOREIGN KEY REFERENCES product(pID),
mID decimal(4) FOREIGN KEY REFERENCES member(mID),
odate datetime,
price decimal(7),
status varchar(20),
);



CREATE TABLE manager(
ID decimal(4) identity,
name varchar(10),
account varchar(20),
password varchar(20)
);

DROP TABLE member, ordertable, product, vendor, manager;


---test command

use FinalProject

select * from member
delete from member

select * from ordertable
delete from ordertable

select * from product
delete from product

select * from vendor
delete from vendor

select * from manager
delete from manager

CREATE TABLE ordertable(
oID decimal(4) identity PRIMARY KEY,
pID decimal(4) ,
mID decimal(4) ,
odate datetime,
price decimal(7),
status varchar(20),
);
CREATE TABLE product(
pID decimal(4) identity PRIMARY KEY,
pname varchar(100),
price decimal(6),
vID decimal(4) ,
amount decimal(3),
category varchar(20),
sdate datetime,
expdate datetime
);

SELECT COLUMN_Name ,DATA_TYPE,CHARACTER_MAXIMUM_LENGTH ,IS_NULLABLE , COLUMN_DEFAULT FROM INFORMATION_SCHEMA.Columns Where Table_Name = 'member'