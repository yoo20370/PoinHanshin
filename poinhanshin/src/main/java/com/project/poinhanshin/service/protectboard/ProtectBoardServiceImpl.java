package com.project.poinhanshin.service.protectboard;

import com.project.poinhanshin.domain.etc.SearchCondition1;
import com.project.poinhanshin.domain.protectboard.ProtectBoardDto;
import com.project.poinhanshin.domain.protectboard.ProtectBoardFileDto;
import com.project.poinhanshin.mapper.protectboard.ProtectBoardFileMapper;
import com.project.poinhanshin.mapper.protectboard.ProtectBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ProtectBoardServiceImpl implements ProtectBoardService{

    ProtectBoardMapper protectBoardMapper;
    ProtectBoardFileMapper protectBoardFileMapper;

    // 의존성 주입
    @Autowired
    public ProtectBoardServiceImpl(ProtectBoardMapper protectBoardMapper, ProtectBoardFileMapper protectBoardFileMapper) {
        this.protectBoardMapper = protectBoardMapper;
        this.protectBoardFileMapper = protectBoardFileMapper;
    }

    // 동물 필터에 맞는 임보자 게시물 리스트를 읽어온다.
    @Override
    public List<ProtectBoardDto> bringanimalFilterList(SearchCondition1 sc) {
        return protectBoardMapper.animalFilterList(sc);
    }

    // 한 게시물의 정보를 읽어온다.
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProtectBoardDto bringBoardOne(Integer protectboardno) {
        ProtectBoardDto protectBoard =  protectBoardMapper.selectContentOne(protectboardno);
        if(protectBoard.getFileAttached() == 0){
            return protectBoard;
        }
        else {
            // 파일 이름을 가져가야 함.
            ProtectBoardFileDto protectBoardFileDto = protectBoardFileMapper.selectFiles(protectboardno);
            //ProtectBoardFileDto protectBoardFileDto =  protectBoardFileDtoList.get(0);

            String storedFileName = protectBoardFileDto.getStored_file_name();
            String originalFileName = protectBoardFileDto.getOriginal_file_name();
            protectBoard.setStoredFileName(storedFileName);
            protectBoard.setOriginalFileName(originalFileName);
            System.out.println(protectBoardFileDto.toString()+" "+protectboardno);

            return protectBoard;
        }
    }

    // 게시물을 등록한다.
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertProductBoard(ProtectBoardDto protectBoardDto) throws IOException {
        // 파일 첨부 여부에 따라 로직 분리
        if(protectBoardDto.getProtectboardFile().isEmpty()){
            // 첨부 파일 없음
            protectBoardDto.setFileAttached(0);
            return protectBoardMapper.insertContent(protectBoardDto);
        } else {
            // 첨부 파일 있음 , 첨부 파일이 없으므로 값 1로 설정
            protectBoardDto.setFileAttached(1);
            // protectboardDto에 담긴 파일을 가져온다.
            MultipartFile protectboardFile = protectBoardDto.getProtectboardFile();
            // 파일 이름 저장 (서버 이름 X)
            String originalFileName = protectboardFile.getOriginalFilename();
            // 서버 저장용 이름 // System.currentTimeMillis - 현재 몇 밀리초가 지났는지 - 겹치면 안 되기 때문
            String storedFileName = System.currentTimeMillis() + "_" +originalFileName;
            // 서버 컴퓨터 파일 저장 위치
            String savePath = "/Users/yuyeong-u/fileStorage/protectboard/" + storedFileName;
            // java.io.File; // 지정된 경로로 파일을 넘긴다.
            protectboardFile.transferTo(new File(savePath));

            // protectboard 테이블에 데이터 저장
            protectBoardMapper.insertContent(protectBoardDto);

            // protectboardFile 테이블에 데이터 저장하기 위해 부모의 protectboardno 값을 가져온다.
            // Integer currentProtectboardno = protectBoardDto.getProtectboardno();

            // 매개변수 protectBoardDto를 사용할 수 없는 이유 - 아직 protectboardno 값이 설정되지 않았기 때문
            Integer currentProtectboardno = protectBoardMapper.selectRecentBoardno(protectBoardDto.getProtectboard_userno());

            // 파일 테이블에 데이터 저장 ( 원본 파일 , 서버에 저장할 이름 , 부모 게시물 번호 )
            ProtectBoardFileDto protectBoardFileDto = new ProtectBoardFileDto(null , null , originalFileName, storedFileName , currentProtectboardno );

            // DB에 저장
            protectBoardFileMapper.insertFiles(protectBoardFileDto);

            return 1;
        }
    }



    // 게시물을 수정한다.
    @Override
    public int updateProductBoard(ProtectBoardDto protectBoardDto, Integer LoginId) {
        protectBoardDto.setProtectboard_userno(LoginId);
        return protectBoardMapper.updateContent(protectBoardDto);
    }

    // 게시물을 삭제한다.
    @Override
    public int deleteProductBoard(Integer bno, Integer LoginId) {
        return protectBoardMapper.deleteContent(bno ,LoginId);
    }

    // 검색된 게시물의 개수를 읽어온다.
    @Override
    public int searchResultCnt(SearchCondition1 sc) {
        return protectBoardMapper.searchResultCnt(sc);
    }

    // 검색된 게시물 리스트를 읽어온다.
    @Override
    public List<ProtectBoardDto> searchResultList(SearchCondition1 sc) {
        return protectBoardMapper.searchResultList(sc);
    }

    // 최근 등록한 게시물의 번호를 읽어온다.
    @Override
    public int readWritedBoardno(Integer protectboard_userno) {
        return protectBoardMapper.selectRecentBoardno(protectboard_userno);
    }



}

