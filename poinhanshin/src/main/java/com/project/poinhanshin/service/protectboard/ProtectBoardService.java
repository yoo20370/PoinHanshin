package com.project.poinhanshin.service.protectboard;

import com.project.poinhanshin.domain.etc.SearchCondition1;
import com.project.poinhanshin.domain.protectboard.ProtectBoardDto;

import java.util.List;

public interface ProtectBoardService {


    // 카테고리별 게시글 리스트 가져오기

    List<ProtectBoardDto> bringanimalFilterList(SearchCondition1 sc);

    // 특정 임보자 게시물 하나 가져오기
    ProtectBoardDto bringBoardOne(Integer protectboardno);

    // 임보자 게시물을 등록한다.
    int insertProductBoard(ProtectBoardDto protectBoardDto);

    // 임보자 게시물의 내용을 수정한다.
    int updateProductBoard(ProtectBoardDto protectBoardDto , Integer LoginId);

    // 임보자 게시물을 삭제한다.
    int deleteProductBoard(Integer bno , Integer LoginId);

    // 검색된 임보자 게시물 개수를 가져온다.
    int searchResultCnt(SearchCondition1 sc);

    // 검색된 임보자 게시물 리스트를 가져온다.
    List<ProtectBoardDto> searchResultList(SearchCondition1 sc);

    // 최근 자신이 등록한 임보자 게시물의 번호를 가져온다.
    int readWritedBoardno(Integer protectboard_userno);



}
