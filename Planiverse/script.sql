create table tblUser(
    userSeq number primary key,
    id VARCHAR2(100) not null, 
    pw VARCHAR2(50),
    name VARCHAR2(20) not null
);
insert into tblUser values (1, 'test', '1111', '테스트');
create table tblCalList(
    calListSeq number primary key,
    userSeq NUMBER references tblUser(userSeq)
);
insert into tblCalList values (1, 1);
create table tblCal (
    calSeq number primary key,
    shareInfo CHAR(1) default 'n' not null,
    name VARCHAR2(100),
    calListSeq NUMBER references tblCalList(calListSeq),
    check (shareInfo in ('y', 'n'))
);
insert into tblCal values (1, 'n', 'test', 1);

);
create table tblColor(
    colSeq VARCHAR2(10) primary key,
    name VARCHAR2(30)
);
insert into tblColor values ('#FFCF96', '주황');
insert into tblEvent values (1, 'Party', 'n', '2023-01-29T20:00:00', 
'', '', '',null,'','',1,1
);
insert into tblEvent(eventSeq, title, allDay, "start", "end", colSeq, calSeq) 
values (1, 'Party', 'n', '2023-01-29 20:00:00','','#FFCF96',1);
commit;
create table tblEvent (
    eventSeq number primary key,
    title VARCHAR2(100) not null,
    allDay CHAR(1) default 'y' not null,
    "start" TIMESTAMP not null,
    "end" TIMESTAMP,
    loc VARCHAR2(100),
    "content" CLOB,
    eDel number,
    googleCalendarId VARCHAR2(200),
    className VARCHAR2(100),
    colSeq VARCHAR2(10) references tblColor(colSeq),
    calSeq NUMBER references tblCal(calSeq) not null,
    check (allDay in ('y', 'n'))
);
drop table tblEvent;
create table tblEvent (
    userSeq NUMBER references tblUser(userSeq) not null,
    calSeq NUMBER references tblCal(calSeq) not null,
    shareTk VARCHAR2(1000)
);

create table tblSync (
    userSeq NUMBER references tblUser(userSeq) primary key,
    syncTk VARCHAR2(1000) not null,
    googleCalendarApiKey VARCHAR2(300)
);

create table tblWhat (
    whatSeq NUMBER primary key,
    what VARCHAR2(50)
);

create table tblLog (
    logSeq NUMBER primary key,
    userSeq NUMBER references tblUser(userSeq),
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