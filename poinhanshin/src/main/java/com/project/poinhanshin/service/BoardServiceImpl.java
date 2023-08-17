package com.project.poinhanshin.service;

import com.project.poinhanshin.domain.BoardDto;
import com.project.poinhanshin.domain.SearchCondition;
import com.project.poinhanshin.repository.BoardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService{
    private final BoardDao boardDao;

    @Autowired
    public BoardServiceImpl(BoardDao boardDao) {
        this.boardDao = boardDao;
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

}
