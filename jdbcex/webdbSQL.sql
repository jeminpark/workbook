-- 이클립스내 SQL파일에서 SQL 실행문 수행시 해당 SQL 문 블록지정후 Alt+x 눌러서 블록지정한 SQL문 만 실행 수행 

SELECT now();

create table tbl_todo (
	tno int auto_increment primary key,
	title varchar(100) not null,
	dueDate date not null,
	finished tinyint default 0
);


insert into tbl_todo (title, dueDate, finished) 
	values ('Test...', '2022-12-31', 1);


	
	

SELECT * FROM tbl_todo;
