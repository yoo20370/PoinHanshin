package com.project.poinhanshin.domain.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class LikeBoardDto {
    private integer likeboard_boardno;  // FK
    private integer likeboard_userno;   // FK
    private integer likeboardno;    // PK
}
