package com.project.poinhanshin.service.protectboard;

import com.project.poinhanshin.domain.etc.SearchCondition1;
import com.project.poinhanshin.domain.protectboard.ProtectBoardDto;
import com.project.poinhanshin.mapper.protectboard.ProtectBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProtectBoardServiceImpl implements ProtectBoardService{

    ProtectBoardMapper protectBoardMapper;

    // 의존성 주입
    public ProtectBoardServiceImpl(ProtectBoardMapper protectBoardMapper) {
        this.protectBoardMapper = protectBoardMapper;
    }


    // 동물 필터에 맞는 임보자 게시물 리스트를 읽어온다.
    @Override
    public List<ProtectBoardDto> bringanimalFilterList(SearchCondition1 sc) {
        return protectBoardMapper.animalFilterList(sc);
    }

    // 한 게시물의 정보를 읽어온다.
    @Override
    public ProtectBoardDto bringBoardOne(Integer protectboardno) {
        return protectBoardMapper.selectContentOne(protectboardno);
    }

    // 게시물을 등록한다.
    @Override
    public int insertProductBoard(ProtectBoardDto protectBoardDto) {
        return protectBoardMapper.insertContent(protectBoardDto);
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

