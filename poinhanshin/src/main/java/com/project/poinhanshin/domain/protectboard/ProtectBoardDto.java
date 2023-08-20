package com.project.poinhanshin.domain.protectboard;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProtectBoardDto {

    // 임보자 게시물 작성 아이디
    private String protectboard_id;

    // 임보자 게시물 번호
    private String protectboardno;

    // 내용
    private String content;

    // 임보자 게시물 이미지 경로
    private byte[] protectimagepath;

    // 품종
    private String breeds;

    // 동물 카테고리 강아지/고양이
    private Boolean ani_category;

    // 게시물 등록일
    private java.sql.Date writedate;

    // 공고 여부
    private Boolean status;

    // Deadline
    private Date deadline;

    public ProtectBoardDto(String protectboard_id, String protectboardno, String content, byte[] protectimagepath, String breeds, Boolean ani_category, Date writedate, Boolean status, Date deadline) {
        this.protectboard_id = protectboard_id;
        this.protectboardno = protectboardno;
        this.content = content;
        this.protectimagepath = protectimagepath;
        this.breeds = breeds;
        this.ani_category = ani_category;
        this.writedate = writedate;
        this.status = status;
        this.deadline = deadline;
    }
}




