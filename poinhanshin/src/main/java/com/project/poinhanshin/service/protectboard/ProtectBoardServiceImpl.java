package com.project.poinhanshin.service.protectboard;

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

    @Override
    public List<ProtectBoardDto> bringBoardList() {
        System.out.println("실행됨1");
        return protectBoardMapper.selectContentAll();
    }

    @Override
    public ProtectBoardDto bringBoardOne(String protectboardno) {
        return protectBoardMapper.SelectContentOne(protectboardno);
    }

    @Override
    public int searchListCnt() {
        System.out.println("실행됨2");
        return protectBoardMapper.count();
    }
}