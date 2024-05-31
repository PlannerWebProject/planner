package com.planiverse.user.model;

import lombok.Data;

//사용자 정보를 담는 DTO 클래스
@Data
public class UserDTO {
 // 사용자 ID
 private String id;
 // 사용자 비밀번호
 private String pw;
 // 사용자 이름
 private String name;
}