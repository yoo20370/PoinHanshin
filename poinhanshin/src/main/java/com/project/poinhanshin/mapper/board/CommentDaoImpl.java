package com.project.poinhanshin.mapper.board;

import com.project.poinhanshin.domain.board.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao{

    private final CommentDao commentDao;

    @Autowired
    public CommentDaoImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }


    @Override
    public int count(Integer bno) {
        return commentDao.count(bno);
    }

    @Override
    public int deleteAll(Integer bno) {
        return commentDao.deleteAll(bno);
    }

    @Override
    public int delete(Integer cno, String commenter) {
        return commentDao.delete(cno, commenter);
    }

    @Override
    public int insert(CommentDto dto) {
        return commentDao.insert(dto);
    }

    @Override
    public List<CommentDto> selectAll(Integer bno) {
        return commentDao.selectAll(bno);
    }

    @Override
    public CommentDto select(Integer cno) {
        return commentDao.select(cno);
    }

    @Override
    public int update(CommentDto dto) {
        return commentDao.update(dto);
    }
}
