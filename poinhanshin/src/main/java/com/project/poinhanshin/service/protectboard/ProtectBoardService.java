package com.project.poinhanshin.service.protectboard;

import com.project.poinhanshin.domain.etc.SearchCondition1;
import com.project.poinhanshin.domain.protectboard.ProtectBoardDto;

import java.util.List;

public interface ProtectBoardService {

    // 임보자 게시글 리스트 가져오기
    List<ProtectBoardDto> bringBoardList(SearchCondition1 sc);

    // 카테고리별 게시글 리스트 가져오기

    List<ProtectBoardDto> bringanimalFilterList(SearchCondition1 sc);

    // 특정 임보자 게시물 하나 가져오기
    ProtectBoardDto bringBoardOne(Integer protectboardno);

    int countListAll();

    int insertProductBoard(ProtectBoardDto protectBoardDto);

    int updateProductBoard(ProtectBoardDto protectBoardDto , Integer LoginId);

    int deleteProductBoard(Integer bno , Integer LoginId);

    int readWritedBoardno(Integer protectboard_userno);




}
