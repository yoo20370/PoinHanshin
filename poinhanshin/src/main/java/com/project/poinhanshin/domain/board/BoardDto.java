package com.project.poinhanshin.domain.board;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BoardDto {
        private String id;
        private Integer board_userno;
        private Integer boardno;
        private String board_title;
        private String board_content;
        private boolean board_ani_category;
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date board_reg_date;
        private Integer board_viewcount;
        private Integer commentcount;
        private Integer likecount;
        private List<MultipartFile> boardFile = null;
        private List<String> originalFileName;
        private List<String> storedFileName;
        private Integer fileAttached;

        public BoardDto(String id, Integer board_userno, Integer boardno, String board_title, String board_content, boolean board_ani_category, Date board_reg_date, Integer board_viewcount, Integer commentcount, Integer likecount, Integer fileAttached) {
                this.id = id;
                this.board_userno = board_userno;
                this.boardno = boardno;
                this.board_title = board_title;
                this.board_content = board_content;
                this.board_ani_category = board_ani_category;
                this.board_reg_date = board_reg_date;
                this.board_viewcount = board_viewcount;
                this.commentcount = commentcount;
                this.likecount = likecount;
                this.fileAttached = fileAttached;
        }
}
