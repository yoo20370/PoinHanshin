package com.project.poinhanshin.service.board;

import com.project.poinhanshin.domain.board.CommentDto;
import com.project.poinhanshin.mapper.board.BoardMapper;
import com.project.poinhanshin.mapper.board.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {
    private final BoardMapper boardDao;
    private final CommentDao commentDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao, BoardMapper boardDao) {
        this.commentDao = commentDao;
        this.boardDao = boardDao;
    }

    @Override
    public int getCount(Integer bno) {
        return commentDao.count(bno);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(Integer cno, Integer bno, String commenter) {
        int rowCnt = boardDao.updateCommentCnt(bno, -1);
        System.out.println("updateCommentCnt - rowCnt = " + rowCnt);
        rowCnt = commentDao.delete(cno, commenter);
        System.out.println("rowCnt = " + rowCnt);
        return rowCnt;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int write(CommentDto commentDto) {
        boardDao.updateCommentCnt(commentDto.getBno(), 1);
        return commentDao.insert(commentDto);
    }

    @Override
    public List<CommentDto> getList(Integer bno) {
        return commentDao.selectAll(bno);
    }

    @Override
    public CommentDto read(Integer cno) {
        return commentDao.select(cno);
    }

    @Override
    public int modify(CommentDto commentDto) {
        return commentDao.update(commentDto);
    }
}
