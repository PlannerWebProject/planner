create table tblEvent (
    eventSeq number primary key,
    title VARCHAR2(100) not null,
    allDay CHAR(1) default y,
    start TIMESTAMP not null,
    end TIMESTAMP,
    loc VARCHAR2(100),
    content CLOB,
    eDel number,
    googleCalendarId VARCHAR2(200),
    className VARCHAR2(100),
    colSeq VARCHAR2(10) references tblColor(colSeq),
    calSeq NUMBER references tblCal(calSeq)
);