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
    public List<ProtectBoardFileDto> selectFiles(Integer protectboardno) {
        return sqlSession.selectOne(namespace+"selectFiles" , protectboardno);
    }

    @Override
    public int insertFiles(ProtectBoardFileDto protectBoardFileDto) {
        return sqlSession.insert(namespace+"insertFiles" , protectBoardFileDto);
    }

    @Override
    public int deleteFile(Integer protectboardfileno) {
        return sqlSession.delete(namespace+"deleteFile" , protectboardfileno);
    }

    @Override
    public int deleteFile2(String stored_file_name) {
        return sqlSession.delete(namespace+"deleteFile2",stored_file_name );
    }

    @Override
    public int selectCnt(Integer protectboardno) {
        return sqlSession.selectOne(namespace+"selectCnt",protectboardno );
    }

    @Override
    public List<String> selectFilesName(Integer protectboardno) {
        return sqlSession.selectList(namespace+"selectFilesName",protectboardno);
    }
}
