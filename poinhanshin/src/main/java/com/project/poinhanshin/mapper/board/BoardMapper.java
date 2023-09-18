package com.project.poinhanshin.mapper.board;

import com.project.poinhanshin.domain.board.BoardDto;
import com.project.poinhanshin.domain.etc.SearchCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BoardMapper {

    // 검색된 게시물 개수 반환
    int boardSearchResultCnt(SearchCondition sc);

    // 검색된 게시물 리스트 반환
    List<BoardDto> boardSearchResultList(SearchCondition sc);

    // 특정 게시물 하나를 읽어온다.
    BoardDto boardSelectContentOne(Integer boardno);

    // 게시물을 등록한다.
    int boardInsertContent(BoardDto boardDto);

    // 게시물을 수정한다.
    int boardUpdateContent(BoardDto boardDto , Integer LoginId);

    // 게시물을 삭제한다.
    int boardDeleteContent(Integer boardno , Integer LoginId);

    // 최근 사용자가 등록한 게시물의 번호를 가져온다.
    int boardSelectRecentBoardNo(Integer board_userno);

    // 게시물의 파일 여부 값을 수정한다.
    int boardUpdateFileAttached(Integer boardno , Integer fileAttached);

    // 조회수 업데이트
    int updateViewCnt(Integer boardno);

    // 조회수 Top3
    List<BoardDto> selectViewCntTop();

    // 댓글 개수 업데이트
    int updateCommentCnt(Integer boardno, Integer num);
}
