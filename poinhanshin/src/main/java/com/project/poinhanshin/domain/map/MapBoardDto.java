package com.project.poinhanshin.domain.map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@ToString
public class MapBoardDto {
    public MapBoardDto(Integer mapboard_userno, Integer mapboardno, String content, byte mapimagepath, Date missingtime, String missingaddress, BigDecimal latitude, BigDecimal longitude, Date reg_date, Integer viewcount, boolean ani_category, boolean writertype) {
        this.mapboard_userno = mapboard_userno;
        this.mapboardno = mapboardno;
        this.content = content;
        this.mapimagepath = mapimagepath;
        this.missingtime = missingtime;
        this.missingaddress = missingaddress;
        this.latitude = latitude;
        this.longitude = longitude;
        this.reg_date = reg_date;
        this.viewcount = viewcount;
        this.ani_category = ani_category;
        this.writertype = writertype;
    }

    // 실종/발견신고지도
    private Integer mapboard_userno;    // FK, 회원번호
    private Integer mapboardno;         // PK, 게시물내용
    private String content;             // sqltype : Text, 게시물 내용
    private byte mapimagepath;          // sqltype : longblob -> int & byte, 게시물 이미지 경로
    private java.sql.Date missingtime;  // sqltype : date, 실종&발견 시간
    private String missingaddress;      // 실종&발견 장소
    private java.math.BigDecimal latitude;  // sqltype : decimal, 위도
    private java.math.BigDecimal longitude; // sqltype : decimal, 경도
    private java.sql.Date reg_date;     // sqltype : date, 글 작성 시간
    private Integer viewcount;          // 조회수
    private boolean ani_category;       // 강아지 0, 고양이 1
    private boolean writertype;         // 신고자 0, 발견자 1
}
