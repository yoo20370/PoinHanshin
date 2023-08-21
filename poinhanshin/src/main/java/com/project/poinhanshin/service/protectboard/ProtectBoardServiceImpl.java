package com.project.poinhanshin.service.protectboard;

import com.project.poinhanshin.domain.etc.SearchCondition1;
import com.project.poinhanshin.mapper.protectboard.ProtectBoardMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProtectBoardServiceImpl implements ProtectBoardService{

    ProtectBoardMapper protectBoardMapper;

    // 의존성 주입
    public ProtectBoardServiceImpl(ProtectBoardMapper protectBoardMapper) {
        this.protectBoardMapper = protectBoardMapper;
    }

    // 모든 게시물 리스트를 가져온다.
    @Override
    public List<ProtectBoardDto> bringBoardList(SearchCondition1 sc) {
        System.out.println("실행됨1");
        return protectBoardMapper.selectContentAll();
    }

    // 게시물 하나만 가져온다.
    @Override
    public ProtectBoardDto bringBoardOne(Integer protectboardno) {
        return protectBoardMapper.SelectContentOne(protectboardno);
    }

    // 모든 게시물의 개수를 구한다.
    @Override
    public int countListAll() {
        return protectBoardMapper.countAll();
    }

    // 게시물을 데이터베이스에 저장한다.
    @Override
    public int insertProductBoard(ProtectBoardDto protectBoardDto) {
        return protectBoardMapper.insertContent(protectBoardDto);
    }

}

