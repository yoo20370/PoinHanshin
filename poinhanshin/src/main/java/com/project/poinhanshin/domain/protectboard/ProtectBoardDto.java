package com.project.poinhanshin.domain.protectboard;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProtectBoardDto {
     private Integer protectboard_userno;   // FK, 회원번호
     private Integer protectboardno;    // PK, 임보자공고 게시물 번호
     private String protectboard_content;   // sqltype : varchar, 공고내용
     private byte[] protectboard_imagepath; // sqltype : longblob, 이미지 경로
     private String breeds;    // sqltype : varchar, 품종
     boolean protectboard_ani_category;  // 강아지 0 or 고양이 1
     private java.sql.Date protectboard_reg_date;    // sqltype : date, 임보자 게시물 작성날짜
     boolean protectstatus;    // 공고중 확인, 공고중 0 or 공고완료 1
     private java.sql.Date deadline; // sqltype : date, 마감날짜

     public ProtectBoardDto(Integer protectboard_userno, Integer protectboardno, String protectboard_content, byte[] protectboard_imagepath, String breeds, boolean protectboard_ani_category, Date protectboard_reg_date, boolean protectstatus, Date deadline) {
          this.protectboard_userno = protectboard_userno;
          this.protectboardno = protectboardno;
          this.protectboard_content = protectboard_content;
          this.protectboard_imagepath = protectboard_imagepath;
          this.breeds = breeds;
          this.protectboard_ani_category = protectboard_ani_category;
          this.protectboard_reg_date = protectboard_reg_date;
          this.protectstatus = protectstatus;
          this.deadline = deadline;
     }


}




