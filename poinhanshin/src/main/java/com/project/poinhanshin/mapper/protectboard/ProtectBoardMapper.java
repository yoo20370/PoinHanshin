package com.project.poinhanshin.mapper.protectboard;

import com.project.poinhanshin.domain.etc.SearchCondition1;
import com.project.poinhanshin.domain.protectboard.ProtectBoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProtectBoardMapper {

    // 모든 게시물 개수를 가져온다.
    int countAll();

    // 임보자 게시물 전체 리스트를 가져온다.
    List<ProtectBoardDto> selectContentAll(SearchCondition1 sc);

    // 임보자 게시물 중 특정 동물의 게시물 리스트를 가져온다.
    List<ProtectBoardDto> animalFilterList(SearchCondition1 sc);

    // 특정 임보자 게시물 하나를 가져온다.
    ProtectBoardDto selectContentOne(Integer protectboardno);

    // 임보자 게시물을 등록한다.
    int insertContent(ProtectBoardDto protectBoardDto);

    // 임보자 게시물을 수정한다.
    int updateContent(ProtectBoardDto protectBoardDto);

    // 임보자 게시물을 삭제한다.( 사용자 )
    int deleteContent(Integer protectboardno, Integer protectboard_userno);

    // 검색된 임보자 게시물 개수 카운팅
    int selectResultCnt(SearchCondition1 sc);

    // 검색 창을 이용하여 특정 임보자 게시물을 검색할 때 사용하는 메서드
    List<ProtectBoardDto> searchResultList(SearchCondition1 sc);

    int selectRecentBoardno(Integer protectboardno);










}
