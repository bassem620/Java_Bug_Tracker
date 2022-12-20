create database bug_tracker
on primary (
	name= bug_tracker,
	filename= 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\bug_tracker.mdf',
	size= 10MB,
	maxsize= 200MB,
	filegrowth= 10MB
)
log on (
	name= bug_tracker_log,
	filename= 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\bug_tracker_log.ldf',
	size= 10MB,
	maxsize= 200MB,
	filegrowth= 5MB
)

use bug_tracker

create table employee(
	id int identity(1,1),
	username nvarchar(20) unique NOT NULL,
	[password] nvarchar(50) NOT NULL,
	firstName nvarchar(15) NOT NULL,
	lastName nvarchar(15) NOT NULL,
	[role] nvarchar(15) Check([role] in ('Admin','Project Manager', 'Developer', 'Tester')) NOT NULL,
	createdAt DateTime NOT NULL,
	PRIMARY KEY(id, username),
)

create table email(
	id int primary key identity(1,1),
	sender nvarchar(50) NOT NULL,
	[to] nvarchar(50) NOT NULL,
	[message] nvarchar(200) NOT NULL,
	createdAt DateTime NOT NULL,
)

create table project(
	id int identity(1,1),
	[name] nvarchar(30) unique,
	[description] nvarchar(200),
	createdBy nvarchar(20) foreign key references employee(username) on update cascade NOT NULL,
	createdAt DateTime NOT NULL,
	PRIMARY KEY (id, [name])
)

create table bug(
	id int identity(1,1),
	[name] nvarchar(50) unique NOT NULL,
	[type] nvarchar(30) check([type] in 
		('Functional errors', 'Syntax errors', 'Logic errors', 'Calculation errors' ,
		'Unit-level bugs' ,'System-level integration bugs', 'Out of bounds bugs')) NOT NULL,
	[priority] nvarchar(4) check([priority] in ('Low', 'Med', 'High')) NOT NULL,
	[level] nvarchar(2) check([level] in ('L1', 'L2', 'L3')) NOT NULL,
	project_name nvarchar(30) foreign key references project([name]) NOT NULL,
	[status] nvarchar(10) check([status] in ('Open','Fix', 'Closed')) NOT NULL,
	createdBy nvarchar(20) foreign key references employee(username) on update cascade,
	assignedTo nvarchar(20) foreign key references employee(username),
	createdAt DateTime NOT NULL,
	finishedAt DateTime,
	primary key(id, [name])
)

select * from employee
select * from email
select * from project
select * from bug
