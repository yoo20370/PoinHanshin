package com.project.poinhanshin.domain.protectboard;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProtectBoardDto {
     private Integer protectboard_userno;   // FK, 회원번호
     private Integer protectboardno;    // PK, 임보자공고 게시물 번호
     private String protectboard_title; // 임보자 게시물 제목
     private String protectboard_content;   // sqltype : varchar, 공고내용
     private byte[] protectboard_imagepath; // sqltype : longblob, 이미지 경로
     private String breeds;    // sqltype : varchar, 품종
     boolean protectboard_ani_category;  // 강아지 0 or 고양이 1
     @DateTimeFormat(pattern = "yyyy-mm-dd")
     private Date protectboard_reg_date;    // util.Date : date, 임보자 게시물 작성날짜
     boolean protectstatus;    // 공고중 확인, 공고중 0 or 공고완료 1
     @DateTimeFormat(pattern = "yyyy-mm-dd")
     private Date starttime; // util.Date : date, 공고 시작일
     @DateTimeFormat(pattern = "yyyy-mm-dd")
     private Date deadline; // util.Date : date, 마감날짜

     public ProtectBoardDto(Integer protectboard_userno, Integer protectboardno, String protectboard_title, String protectboard_content, byte[] protectboard_imagepath, String breeds, boolean protectboard_ani_category, Date protectboard_reg_date, boolean protectstatus, Date starttime, Date deadline) {
          this.protectboard_userno = protectboard_userno;
          this.protectboardno = protectboardno;
          this.protectboard_title = protectboard_title;
          this.protectboard_content = protectboard_content;
          this.protectboard_imagepath = protectboard_imagepath;
          this.breeds = breeds;
          this.protectboard_ani_category = protectboard_ani_category;
          this.protectboard_reg_date = protectboard_reg_date;
          this.protectstatus = protectstatus;
          this.starttime = starttime;
          this.deadline = deadline;
     }
}




