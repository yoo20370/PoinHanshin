package com.project.poinhanshin.mapper.protectboard;

import com.project.poinhanshin.domain.etc.SearchCondition1;
import com.project.poinhanshin.domain.protectboard.ProtectBoardDto;
import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class ProtectBoardMapperImpl implements ProtectBoardMapper{


    SqlSession sqlSession;

    @Autowired
    public ProtectBoardMapperImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    private String namespace = "com.project.poinhanshin.mapper.protectboard.ProtectBoardMapper.";

    // 모든 게시물 개수를 가져온다.
    @Override
    public int countAll() {
        return sqlSession.selectOne(namespace+"countAll");
    }

    // 임보자 게시물 전체를 가져온다.
    @Override
    public List<ProtectBoardDto> selectContentAll(SearchCondition1 sc) {
        return sqlSession.selectList(namespace+"selectContentAll",sc);
    }

    @Override
    public List<ProtectBoardDto> animalFilterList(SearchCondition1 sc ) {
        return sqlSession.selectList(namespace+"animalFilterList",sc);
    }

    // 특정 임보자 게시물 하나를 가져온다.
    @Override
    public ProtectBoardDto selectContentOne(Integer protectboardno) {
        return sqlSession.selectOne(namespace+"selectContentOne", protectboardno);
    }

    // 임보자 게시물을 등록한다.
    @Override
    public int insertContent(ProtectBoardDto protectBoardDto) {
        return sqlSession.insert(namespace+"insertContent" , protectBoardDto);
    }

    // 임보자 게시물을 수정한다.
    @Override
    public int updateContent(ProtectBoardDto protectBoardDto) {
        return sqlSession.update(namespace+"updateContent",protectBoardDto);
    }

    // 임보자 게시물을 삭제한다.
    @Override
    public int deleteContent(Integer protectboardno, Integer protectboard_userno) {
        HashMap<String , Integer> hashMap = new HashMap<>();
        hashMap.put("protectboardno" , protectboardno);
        hashMap.put("protectboard_userno", protectboard_userno );
        return sqlSession.delete(namespace+"deleteContent", hashMap);

    }

    // 검색된 임보자 게시물 수를 가져온다.
    @Override
    public int selectResultCnt(SearchCondition1 sc) {

        return sqlSession.selectOne(namespace+"searchResultCnt",sc);
    }

    // 검색된 임보자 게시물 리스트를 가져온다.
    @Override
    public List<ProtectBoardDto> searchResultList(SearchCondition1 sc) {
        return sqlSession.selectList(namespace+"searchResultList", sc);
    }

    // 가장 최근에 등록한 자신 게시물을 읽어온다.
    @Override
    public int selectRecentBoardno(Integer protectboard_userno) {
        return sqlSession.selectOne(namespace+"selectRecentBoardno" , protectboard_userno);
    }


}
