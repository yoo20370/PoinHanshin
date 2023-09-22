package com.project.poinhanshin.mapper.map;

import com.project.poinhanshin.domain.etc.SearchCondition;
import com.project.poinhanshin.domain.map.MapBoardDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class MapMapperImpl implements MapMapper{

    SqlSession sqlSession;

    public MapMapperImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    private String namespace = "com.project.poinhanshin.mapper.map.MapMapper.";


    @Override
    public int selectMapCnt(SearchCondition sc) {
        return sqlSession.selectOne(namespace+"selectMapCnt",sc);
    }

    @Override
    public List<MapBoardDto> selectMapList(SearchCondition sc) {
        return sqlSession.selectList(namespace+"selectMapList",sc);
    }

    @Override
    public MapBoardDto selectMapBoardOne(Integer mapboardno) {
        return sqlSession.selectOne(namespace+"selectMapBoardOne" , mapboardno);
    }

    @Override
    public int insertMapBoard(MapBoardDto mapBoardDto) {
        return sqlSession.insert(namespace+"insertMapBoard" , mapBoardDto);
    }

    @Override
    public int updateMapBoard(MapBoardDto mapBoardDto) {
        return sqlSession.update(namespace+"updateMapBoard" , mapBoardDto);
    }

    @Override
    public int deleteMapBoard(Integer mapboard_userno, Integer mapboardno) {
        HashMap<String , Integer> hashMap = new HashMap<>();
        hashMap.put("mapboard_userno", mapboard_userno);
        hashMap.put("mapboardno" , mapboardno);
        return sqlSession.delete(namespace+"deleteMapBoard", hashMap);
    }

    @Override
    public int selectRecentMapBoardNo(Integer mapboard_userno) {
        return 0;
    }

    @Override
    public int mapBoardUpdateFileAttached(Integer mapboardno, Integer fileAttached) {
        return 0;
    }

    // 마이페이지 - 자신의 게시판 즐겨찾기 리스트 불러오기
    @Override
    public List<MapBoardDto> SelectMyMap(Integer userno) {
        return sqlSession.selectList(namespace+"SelectMyMap", userno);
    }

    // 마이페이지 - 자신이 작성한 실종/발견 지도 게시글 불러오기
    @Override
    public List<MapBoardDto> WriteMyMap(Integer userno) {
        return sqlSession.selectList(namespace+"WriteMyMap", userno);
    }
}
