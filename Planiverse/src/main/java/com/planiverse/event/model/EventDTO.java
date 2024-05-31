package com.planiverse.event.model;

import lombok.Data;

//이벤트 관련 정보를 담는 DTO 클래스
@Data
public class EventDTO {
 // 이벤트 시퀀스 번호
 private String eventSeq;
 // 이벤트 제목
 private String title;
 // 종일 이벤트 여부 ("y" or "n")
 private String allDay;
 // 이벤트 시작 시간
 private String start;
 // 이벤트 종료 시간
 private String end;
 // 이벤트 장소
 private String loc;
 // 이벤트 내용
 private String content;
 // 이벤트 색상
 private String color;
 // 캘린더 시퀀스 번호
 private String calSeq;
}
