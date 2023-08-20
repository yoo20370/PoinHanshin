package com.project.poinhanshin.service.protectboard;

import com.project.poinhanshin.domain.protectboard.ProtectBoardDto;

import java.util.List;

public interface ProtectBoardService {

    // 임보자 게시글 리스트 가져오기
    List<ProtectBoardDto> bringBoardList();

    // 특정 임보자 게시물 하나 가져오기
    ProtectBoardDto bringBoardOne(String protectboardno);

    int searchListCnt();


}
