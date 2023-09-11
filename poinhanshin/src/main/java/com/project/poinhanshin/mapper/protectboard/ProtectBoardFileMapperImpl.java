package com.project.poinhanshin.mapper.protectboard;

import com.project.poinhanshin.domain.protectboard.ProtectBoardFileDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProtectBoardFileMapperImpl implements ProtectBoardFileMapper{

    SqlSession sqlSession;

    @Autowired
    public ProtectBoardFileMapperImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    private String namespace = "com.project.poinhanshin.mapper.protectboard.ProtectBoardFileMapper.";

    @Override
    public ProtectBoardFileDto selectFiles(Integer protectboardno) {
        return sqlSession.selectOne(namespace+"selectFiles" , protectboardno);
    }

    @Override
    public int insertFiles(ProtectBoardFileDto protectBoardFileDto) {
        return sqlSession.insert(namespace+"insertFiles" , protectBoardFileDto);
    }
}
