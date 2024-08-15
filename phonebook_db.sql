###############################################
# phonebook 만들기
###############################################

-- --------------------------------------------
# root 계정에서 실행
-- --------------------------------------------
-- phonebook 계정생성
create user 'phonebook'@'%' identified by 'phonebook';

-- phonebook 권한부여
grant all privileges on phonebook_db.* to 'phonebook'@'%';

-- phonebook_db 생성
create database phonebook_db
	default character set utf8mb4
    collate utf8mb4_general_ci
    default encryption='n'
;

-- database 조회
show databases;


-- --------------------------------------------
# phonebook 계정에서 실행
-- --------------------------------------------
-- db 선택(phonebook 계정에서 실행)
use phonebook_db;

-- table 삭제
drop table person;

-- person 테이블 생성(phonebook 계정에서 실행)
create table person(
	person_id integer primary key auto_increment,
    name varchar(30) not null,
    hp varchar(20),
    company varchar(20)
);

-- 조회
select * from person;

select 	person_id,
		name,
        hp,
        company
from person;

-- 등록
insert into person
values(null, '정우성', '010-1111-1111', '02-1111-1111');

insert into person
values(null, '이효리', '010-2222-2222', '02-2222-2222');

-- 수정
update person
set name = '강호동',
	hp = '010-9999-9999',
    company = '02-9999-9999'
where person_id = 3 ;

-- 삭제
delete from person
where person_id = 1;