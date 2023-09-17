package com.project.poinhanshin.mapper.board;

import com.project.poinhanshin.domain.board.BoardFileDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardFileMapperImpl implements BoardFileMapper{

    SqlSession sqlSession;

    @Autowired
    public BoardFileMapperImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    private String namespace = "com.project.poinhanshin.mapper.board.BoardFileMapper.";

    // 게시물 번호에 해당하는 이미지를 불러온다.
    @Override
    public List<BoardFileDto> boardSelectFile(Integer boardno) {
        return sqlSession.selectOne(namespace+"boardSelectFile",boardno);
    }

    // 이미지 파일을 등록한다.
    @Override
    public int boardInsertFile(BoardFileDto boardFileDto) {
        return sqlSession.insert(namespace+"boardInsertFile" , boardFileDto);
    }

    // 이미지 파일을 삭제한다.
    @Override
    public int boardDeleteFile(String stored_file_name) {
        return sqlSession.delete(namespace+"boardDeleteFile" ,stored_file_name );
    }

    // 해당 게시물의 파일 게수 반환
    @Override
    public int boardSelectCnt(Integer boardno) {
        return sqlSession.selectOne(namespace+"boardSelectCnt", boardno);
    }

    // 해당  게시물의 이미지 이름 반환
    @Override
    public List<String> boardSelectFileName(Integer boardno) {
        return sqlSession.selectOne(namespace+"boardSelectFileName",boardno);
    }
}
