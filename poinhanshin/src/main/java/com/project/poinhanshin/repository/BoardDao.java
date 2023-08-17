package com.project.poinhanshin.repository;

import com.project.poinhanshin.domain.BoardDto;
import com.project.poinhanshin.domain.SearchCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardDao {
    int count();

    int deleteAll();

    int delete(Integer bno);

    int insert(BoardDto dto);

    List<BoardDto> selectAll();

    BoardDto select(Integer bno);

    List<BoardDto> selectPage(Map map);

    int update(BoardDto dto);

    int increaseViewCnt(Integer bno);

    List<BoardDto> searchSelectPage(SearchCondition sc);

    int searchResultCnt(SearchCondition sc);

    int updateCommentCnt(@Param("bno") Integer bno, @Param("cnt") int cnt);
}
