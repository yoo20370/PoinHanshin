package com.project.poinhanshin.service.board;

import com.project.poinhanshin.domain.board.BoardDto;
import com.project.poinhanshin.domain.board.LikeBoardDto;
import com.project.poinhanshin.domain.etc.SearchCondition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BoardService {
    int getCount();

    int remove(Integer bno);

    int write(BoardDto boardDto);

    List<BoardDto> getList();

    BoardDto read(Integer bno);

    BoardDto readV2(Integer bno);

    List<BoardDto> getPage(Map map);

    int modify(BoardDto boardDto);

    List<BoardDto> getSearchResultPage(SearchCondition sc);

    int getSearchResultCnt(SearchCondition sc);

    int likeCheck(Integer bno , Integer uno);

    int addLike(String userno , LikeBoardDto likeDto );

    int deleteLike(String userno , LikeBoardDto likeDto );
}
