
alter session set "_oracle_script" = true;
grant connect, resource, dba to planiverse;
create user planiverse identified by java1234;

create table tblUser(
    id VARCHAR2(100) primary key,
    pw VARCHAR2(50) not null,
    name VARCHAR2(20) not null
);
--------------------------------------------------------------유저테이블
create table tblCalList(
    calListSeq number primary key,
    id VARCHAR2(100) not null references tblUser(id)
);
create sequence seqCalList;
---------------------------------------------------------------달력리스트
create table tblCal (
    calSeq number primary key,
    shareInfo CHAR(1) default 'n' not null,
    name VARCHAR2(100),
    calListSeq NUMBER references tblCalList(calListSeq),
    check (shareInfo in ('y', 'n'))
);
create sequence seqCal;
----------------------------------------------------------------달력
create table tblColor(
    color VARCHAR2(10) primary key,
    name VARCHAR2(30)
);
----------------------------------------------------------------컬러
create table tblEvent (
    eventSeq number primary key,
    title VARCHAR2(100) not null,
    allDay char(1) default 'y' not null,
    "start" TIMESTAMP not null,
    "end" TIMESTAMP,
    loc VARCHAR2(100),
    "content" CLOB,
    color VARCHAR2(10) references tblColor(color),
    calSeq NUMBER references tblCal(calSeq) not null,
    check (allDay in ('y', 'n'))
);
create sequence seqEvent;
insert into tblEvent(eventSeq, title, allDay, "start", "end", loc, "content", color, calSeq) 
values (seqEvent.nextVal, 'Party', 'n', '2023-01-05 20:00:00','', '우리집','재밌겠다','#A8D8EA',1);
----------------------------------------------------------------일정
create table tblShare (
    id VARCHAR2(100) not null references tblUser(id),
    calSeq NUMBER references tblCal(calSeq) not null,
    shareTk VARCHAR2(1000)
);
----------------------------------------------------------------공유여부
select * from tblEvent;

insert into tblUser values ('test', '1111', '테스트');

--

insert into tblCalList values (1, 'test');
--


insert into tblCal values (1, 'n', 'test', 1);

--

insert into tblColor values ('#F1932E', '주황');
insert into tblColor values ('#A8D8EA', '하늘');
insert into tblColor values ('#AA96DA', '보라');
insert into tblColor values ('#F9B8D1', '분홍');
insert into tblColor values ('#FFFFD2', '노랑');

delete from tblColor where name='주황';
--
update tblEvent set colSeq = '#AA96DA' where eventSeq=1;



commit;
select * from tblEvent;
update tblEvent set "start"='2023-01-23 20:00',"end"='' where eventSeq = 1;
rollback;


-----------------------------여기까지 정보 넣으면 테스트 가능



create table tblWhat (
    whatSeq NUMBER primary key,
    what VARCHAR2(50)
);

create table tblLog (
    logSeq NUMBER primary key,
    id VARCHAR2(100) not null references tblUser(id),
    whatSeq NUMBER references tblWhat(whatSeq),
    logStamp TIMESTAMP
);

--create table tblAnn (
--    annSeq NUMBER primary key,
--    title VARCHAR2(100),
--    post CLOB,
--    
--);

--create table tblSet (
--    setSeq NUMBER primary key,
--    .....
--);