package com.project.poinhanshin.mapper.map;

import com.project.poinhanshin.domain.etc.SearchCondition1;
import com.project.poinhanshin.domain.map.MapBoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MapMapper {

    // 검색된 맵 게시물 수를 가져온다.
    int selectMapCnt(SearchCondition1 sc);

    // 검색된 맵 게시물 리스트를 가져온다.
    List<MapBoardDto> selectMapList(SearchCondition1 sc);

    // 하나의 맵 게시물 정보를 가져온다.
    MapBoardDto selectMapBoardOne(Integer mapboardno);

    // 맵 게시물 등록
    int insertMapBoard(MapBoardDto mapBoardDto);

    // 맵 게시물 수정
    int updateMapBoard(MapBoardDto mapBoardDto);

    // 맵 게시물 삭제
    int deleteMapBoard(Integer mapboard_userno , Integer mapboardno);




}
