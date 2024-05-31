package com.planiverse.event.model;

import lombok.Data;

//캘린더 관련 정보를 담는 DTO 클래스
@Data
public class CalDTO {
 // 캘린더 시퀀스 번호
 private String calSeq;
 // 공유 정보
 private String shareInfo;
 // 캘린더 이름
 private String name;
 // 캘린더 목록 시퀀스 번호
 private String calListSeq;
 // 토큰
	private String token;
	
}
