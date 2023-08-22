package com.project.poinhanshin.service.board;

import com.project.poinhanshin.domain.board.BoardDto;
import com.project.poinhanshin.domain.board.LikeBoardDto;

import com.project.poinhanshin.domain.etc.SearchCondition;
import com.project.poinhanshin.mapper.board.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService{
    private final BoardMapper boardDao;

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardDao = boardMapper;
    }

    @Override
    public int getCount() {
        return boardDao.count();
    }

    @Override
    public int remove(Integer bno) {
        return boardDao.delete(bno);
    }

    @Override
    public int write(BoardDto boardDto) {
        return boardDao.insert(boardDto);
    }

    @Override
    public List<BoardDto> getList() {
        return boardDao.selectAll();
    }

    @Override
    public BoardDto read(Integer bno) {
        BoardDto boardDto = boardDao.select(bno);
        boardDao.increaseViewCnt(bno);

        return boardDto;
    }

    @Override
    public BoardDto readV2(Integer bno) {
        return boardDao.select(bno);
    }


    @Override
    public List<BoardDto> getPage(Map map) {
        return boardDao.selectPage(map);
    }

    @Override
    public int modify(BoardDto boardDto) {
        return boardDao.update(boardDto);
    }

    @Override
    public List<BoardDto> getSearchResultPage(SearchCondition sc) {
        return boardDao.searchSelectPage(sc);
    }

    @Override
    public int getSearchResultCnt(SearchCondition sc) {
        return boardDao.searchResultCnt(sc);
    }

    @Override
    public int likeCheck(Integer bno , Integer uno) {
        HashMap<String , Integer> hashMap = new HashMap<String , Integer>();
        hashMap.put("like_boardno",bno);
        hashMap.put("like_userno", uno);

        return 0;
    }

    @Override
    public int addLike(String userno, LikeBoardDto likeDto) {
        return 0;
    }

    @Override
    public int deleteLike(String userno, LikeBoardDto likeDto) {
        return 0;
    }

}
