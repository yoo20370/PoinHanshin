package com.project.poinhanshin.domain.map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class MapBoardDto {
    private integer mapboard_userno;    // FK
    private integer mapboardno; // PK
    private text content;
    private longblob mapimagepath;
    private date missingtime;
    private varchar missingaddress;
    private decimal latitude;
    private decimal longitude;
    private date reg_date;
    private integer viewcount;
    private boolean ani_category;
    private boolean writertype
}
