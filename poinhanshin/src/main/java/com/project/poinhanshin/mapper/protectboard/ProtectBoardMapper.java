package com.project.poinhanshin.mapper.protectboard;

import com.project.poinhanshin.domain.etc.SearchCondition1;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProtectBoardMapper {

    // 모든 게시물 개수를 가져온다.
    int countAll();

    // 임보자 게시물 전체를 가져온다.
    List<ProtectBoardDto> selectContentAll();

    // 특정 임보자 게시물 하나를 가져온다.
    ProtectBoardDto SelectContentOne(Integer protectboardno);

    // 임보자 게시물을 등록한다.
    int insertContent(ProtectBoardDto protectBoardDto);

    // 임보자 게시물을 수정한다.
    int updateContent(ProtectBoardDto protectBoardDto);

    // 임보자 게시물을 삭제한다.( 사용자 )
    int deleteContent(ProtectBoardDto protectBoardDto , String writer);

    // 검색된 임보자 게시물 개수 카운팅
    int selectResultCnt(SearchCondition1 sc );

    // 임보자 게시물 클릭시
    int increaseViewCnt(Integer protectboardno);







}
