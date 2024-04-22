alter session set "_oracle_script" = true;
grant connect, resource, dba to planiverse;
create user planiverse identified by java1234;

--여기부터 시작
create table tblUser(
    id VARCHAR2(100) primary key,
    pw VARCHAR2(50) not null,
    name VARCHAR2(20) not null
);
--아래는 CRUD
insert into tblUser values ('test', '1111', '테스트');

select * from tblUser;

update tblUser set pw = ? where id = ?;

delete from tblUser where id = ?;
--------------------------------------------------------------유저테이블
create table tblCalList(
    calListSeq number primary key,
    id VARCHAR2(100) not null references tblUser(id)
);
create sequence seqCalList;
--아래는 CRUD
insert into tblCalList values (1, 'test');

select * from tblCalList where id = 'test';

update tblCalList set id = ? where calListSeq = ?;

delete from tblCalList where calListSeq = ?;
---------------------------------------------------------------달력리스트
create table tblCal (
    calSeq number primary key,
    shareInfo CHAR(1) default 'n' not null,
    name VARCHAR2(100),
    calListSeq NUMBER references tblCalList(calListSeq),
    check (shareInfo in ('y', 'n'))
);
create sequence seqCal;
--아래는 CRUD
insert into tblCal values (1, 'n', 'test', 1);

select * from tblCal c
    inner join tblCalList cl
        on c.calListSeq = cl.calListSeq
            where cl.id = 'test';

update tblCal set shareInfo = ? where calSeq = ?;

delete from tblCal where calSeq = ?;
----------------------------------------------------------------달력
create table tblColor(
    color VARCHAR2(10) primary key,
    name VARCHAR2(30)
);
--아래는 CRUD
insert into tblColor values ('#F1932E', '주황');
insert into tblColor values ('#A8D8EA', '하늘');
insert into tblColor values ('#AA96DA', '보라');
insert into tblColor values ('#F9B8D1', '분홍');
insert into tblColor values ('#FFFFD2', '노랑');

select * from tblColor;

delete from tblColor where color = ?;
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
--아래는 CRUD
insert into tblEvent(eventSeq, title, allDay, "start", "end", loc, "content", color, calSeq) 
values (seqEvent.nextVal, 'Party', 'n', '2023-01-05 20:00:00','', '우리집','재밌겠다','#A8D8EA',1);

select * from tblEvent;

select * from tblEvent e 
    inner join tblCal c
        on e.calSeq = c.calSeq
            inner join tblCalList cl
                on c.calListSeq = cl.calListSeq
                    where cl.id = 'test';

update tblEvent set title = ? where eventSeq = ?;

delete from tblEvent where eventSeq = ?;
----------------------------------------------------------------일정
create table tblShare (
    id VARCHAR2(100) not null references tblUser(id),
    calSeq NUMBER references tblCal(calSeq) not null,
    shareTk VARCHAR2(1000)
);
--아래는 CRUD
insert into tblShare values ('test', ?, '');

select * from tblShare;

update tblShare set shareTk =? where id =? and calSeq = ?;

delete from tblShare where id =? and calSeq = ?;
----------------------------------------------------------------공유여부
--뷰!!!!!!!!!!!!!!!!!!!!
create view vwEvent
as
select e.*, c.shareinfo, c.name, c.callistSeq, cl.id from tblEvent e 
    inner join tblCal c
        on e.calSeq = c.calSeq
            inner join tblCalList cl
                on c.calListSeq = cl.calListSeq;
select * from vwEvent where id = 'test';

commit;

rollback;


-----------------------------여기까지 정보 넣으면 테스트 가능
--아래는 논의 필요


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