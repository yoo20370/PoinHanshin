package com.project.poinhanshin.mapper.protectboard;

import com.project.poinhanshin.domain.etc.SearchCondition1;
import com.project.poinhanshin.domain.protectboard.ProtectBoardDto;
import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProtectBoardMapperImpl implements ProtectBoardMapper{


    SqlSession sqlSession;

    @Autowired
    public ProtectBoardMapperImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    private String namespace = "com.project.poinhanshin.mapper.protectboard.ProtectBoardMapper.";

    @Override
    public int countAll() {
        return sqlSession.selectOne(namespace+"countAll");
    }

    @Override
    public List<ProtectBoardDto> selectContentAll() {
        return sqlSession.selectList(namespace+"selectContentAll");
    }

    @Override
    public ProtectBoardDto SelectContentOne(Integer protectboardno) {
        return sqlSession.selectOne(namespace+"SelectContentOne", protectboardno);
    }

    @Override
    public int insertContent(ProtectBoardDto protectBoardDto) {
        return sqlSession.insert(namespace+"insertContent" , protectBoardDto);
    }

    @Override
    public int updateContent(ProtectBoardDto protectBoardDto) {
        return 0;
    }

    @Override
    public int deleteContent(ProtectBoardDto protectBoardDto, String writer) {
        return 0;
    }

    @Override
    public int selectResultCnt(SearchCondition1 sc) {
        return 0;
    }

    @Override
    public int increaseViewCnt(Integer protectboardno) {
        return 0;
    }


}
