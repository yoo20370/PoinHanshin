package com.project.poinhanshin.service.protectboard;

import com.project.poinhanshin.domain.etc.SearchCondition1;

import java.util.List;

public interface ProtectBoardService {

    // 임보자 게시글 리스트 가져오기
    List<ProtectBoardDto> bringBoardList(SearchCondition1 sc);

    // 특정 임보자 게시물 하나 가져오기
    ProtectBoardDto bringBoardOne(Integer protectboardno);

    int countListAll();

    int insertProductBoard(ProtectBoardDto protectBoardDto);


}
