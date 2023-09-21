package com.project.poinhanshin.domain.map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


@Getter
@Setter
@ToString

// 실종/발견신고지도

public class MapBoardDto {
    private String id; // 회원 id
    private Integer mapboard_userno;    // FK, 회원번호
    private Integer mapboardno;         // PK, 게시물내용
    private String mapboard_title;    // 게시물 제목
    private String mapboard_content;             // sqltype : Text, 게시물 내용
    private java.sql.Timestamp missingtime;  // sqltype : date, 실종&발견 시간
    private String missingaddress;      // 실종&발견 장소
    private java.math.BigDecimal latitude;  // sqltype : decimal, 위도
    private java.math.BigDecimal longitude; // sqltype : decimal, 경도
    private java.sql.Timestamp mapboard_reg_date;     // sqltype : date, 글 작성 시간
    private Integer mapboard_viewcount;          // 조회수
    private boolean mapboard_ani_category;       // 강아지 0, 고양이 1
    private boolean writertype;         // 신고자 0, 발견자 1

    // 이미지 저장 관련 멤버변수
    // html -> Controller 파일 담는 용도
    private List<MultipartFile> protectboardFile = null;
    // 원본 파일 이름
    private List<String> originalFileName;
    // 서버 저장용 파일 이름
    private List<String> storedFileName;
    // 파일 첨부 여부(첨부 1,  미첨부 0) , boolean로 할 경우 엔티티(DB)에서 손이 많이 가게 됨

    private Integer fileAttached;                // sqltype : longblob -> int & byte, 게시물 이미지 경로

    public MapBoardDto(){}


    public MapBoardDto(String id, Integer mapboard_userno, Integer mapboardno, String mapboard_title, String mapboard_content, Timestamp missingtime, String missingaddress, BigDecimal latitude, BigDecimal longitude, Timestamp mapboard_reg_date, Integer mapboard_viewcount, boolean mapboard_ani_category, boolean writertype, Integer fileAttached) {
        this.id = id;
        this.mapboard_userno = mapboard_userno;
        this.mapboardno = mapboardno;
        this.mapboard_title = mapboard_title;
        this.mapboard_content = mapboard_content;
        this.missingtime = missingtime;
        this.missingaddress = missingaddress;
        this.latitude = latitude;
        this.longitude = longitude;
        this.mapboard_reg_date = mapboard_reg_date;
        this.mapboard_viewcount = mapboard_viewcount;
        this.mapboard_ani_category = mapboard_ani_category;
        this.writertype = writertype;
        this.fileAttached = fileAttached;
    }
}
