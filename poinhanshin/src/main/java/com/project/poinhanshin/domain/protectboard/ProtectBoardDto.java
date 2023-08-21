package com.project.poinhanshin.domain.protectboard;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProtectBoardDto {
     private Integer protectboard_userno;   // FK, 회원번호
     private Integer protectboardno;    // PK, 임보자공고 게시물 번호
     private String content;   // sqltype : varchar, 공고내용
     private byte[] protectimagepath; // sqltype : longblob, 이미지 경로
     private String breeds;    // sqltype : varchar, 품종
     boolean ani_category;  // 강아지 0 or 고양이 1
     private java.sql.Date writedate;    // sqltype : date, 임보자 게시물 작성날짜
     boolean status;    // 공고중 확인, 공고중 0 or 공고완료 1
     private java.sql.Date deadline; // sqltype : date, 마감날짜
}


