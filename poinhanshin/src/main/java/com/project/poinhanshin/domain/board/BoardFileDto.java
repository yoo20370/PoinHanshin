package com.project.poinhanshin.domain.board;

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
public class BoardFileDto {

    private Integer boardno;
    private Integer boardfileno;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reg_date;
    private String original_file_name;
    private String stored_file_name;
    private Long fileSize;

    public BoardFileDto(Integer boardno, Integer boardfileno, Date reg_date, String original_file_name, String stored_file_name, Long fileSize) {
        this.boardno = boardno;
        this.boardfileno = boardfileno;
        this.reg_date = reg_date;
        this.original_file_name = original_file_name;
        this.stored_file_name = stored_file_name;
        this.fileSize = fileSize;
    }
}
