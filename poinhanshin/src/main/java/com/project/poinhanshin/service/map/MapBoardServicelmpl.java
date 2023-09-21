package com.project.poinhanshin.service.map;

import com.project.poinhanshin.domain.board.BoardFileDto;
import com.project.poinhanshin.domain.etc.SearchCondition;
import com.project.poinhanshin.domain.map.MapBoardDto;
import com.project.poinhanshin.mapper.map.MapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class MapBoardServicelmpl implements MapBoardService{
    MapMapper mapMapper;

    @Autowired
    public MapBoardServicelmpl( MapMapper mapMapper) {
        this.mapMapper = mapMapper;
    }

    // 검색된 게시물 리스트를 가져온다.
    @Override
    public List<MapBoardDto> getMapBoardListService(SearchCondition sc) {
        return mapMapper.selectMapList(sc);
    }
    // 검색된 게시물 리스트 개수를 반환
    @Override
    public int getMapBoardListCnt(SearchCondition sc) {
        return mapMapper.selectMapCnt(sc);
    }

    // 게시물을 하나 가져오기
    @Override
    public MapBoardDto bringMapBoardOne(Integer mapboardno) {
        return mapMapper.selectMapBoardOne(mapboardno);
    }

    // 게시물 등록
    @Override
    public int writeMapBoard(MapBoardDto mapBoardDto) throws IOException {
        return mapMapper.insertMapBoard(mapBoardDto);
    }

    // 게시물 수정
    @Override
    public int modifyMapBoard(MapBoardDto mapBoardDto, Integer loginUser) throws IOException {
        return 0;
    }

    // 게시물 삭제
    @Override
    public int removeMapBoard(Integer mapboardno, Long loginUser) throws IOException {
        return 0;
    }

    // 최근 등록 게시물 번호 반환
    @Override
    public int bringRecentContentNo(Integer mapboard_userno) {
        return 0;
    }

    // 파일 저장 여부 값 수정
    @Override
    public int updateFileAttached(Integer mapboardno, Integer fileAttached) {
        return 0;
    }

    // 이미지 저장을 위해 사용하는 메서드
    @Override
    public void addImgFiles(MapBoardDto mapBoardDto) throws IOException {
        /*for (MultipartFile boardFile : mapBoardDto.getBoardFile()) {

            // 파일 이름 저장 (서버 이름 X)
            String originalFileName = boardFile.getOriginalFilename();
            // 서버 저장용 이름 // System.currentTimeMillis - 현재 몇 밀리초가 지났는지 - 겹치면 안 되기 때문
            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
            // 서버 컴퓨터 파일 저장 위치
            String savePath = "/Users/yuyeong-u/fileStorage/board/" + storedFileName;
            // java.io.File; // 지정된 경로로 파일을 넘긴다.
            boardFile.transferTo(new File(savePath));

            // 파일 테이블에 데이터 저장 ( 원본 파일 , 서버에 저장할 이름 , 부모 게시물 번호 )
            BoardFileDto boardFileDto = new BoardFileDto(boardDto.getBoardno() , null , null , originalFileName , storedFileName , boardFile.getSize());
            boardFileMapper.boardInsertFile(boardFileDto);
        }*/
    }

    // 마이페이지 - 자신의 게시판 즐겨찾기 리스트 불러오기
    @Override
    public List<MapBoardDto> SelectMyMapService(Integer userno) {
        List<MapBoardDto> SelectMyMapService = mapMapper.SelectMyMap(userno);
        return SelectMyMapService;
    }


    // 마이페이지 - 자신이 작성한 실종/발견 지도 게시글 불러오기
    @Override
    public List<MapBoardDto> WriteMyMapService(Integer userno) {
        List<MapBoardDto> WriteMyMapService = mapMapper.WriteMyMap(userno);
        return WriteMyMapService;
    }
}
