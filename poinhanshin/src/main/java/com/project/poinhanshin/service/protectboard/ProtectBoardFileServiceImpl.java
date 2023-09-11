package com.project.poinhanshin.service.protectboard;

import com.project.poinhanshin.domain.protectboard.ProtectBoardFileDto;
import com.project.poinhanshin.mapper.protectboard.ProtectBoardFileMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProtectBoardFileServiceImpl implements ProtectBoardFileService{
    
    ProtectBoardFileMapper protectBoardFileMapper;

    @Autowired
    public ProtectBoardFileServiceImpl(ProtectBoardFileMapper protectBoardFileMapper) {
        this.protectBoardFileMapper = protectBoardFileMapper;
    }
    // 이미지 첨부 파일 불러오기
    @Override
    public List<ProtectBoardFileDto> selectFiles(Integer protectboardno) {
        return protectBoardFileMapper.selectFiles(protectboardno);
    }

    // 이미지 첨부 파일 삭제
    @Override
    public int deleteFile(String stored_File_Name) {
        return protectBoardFileMapper.deleteFile(stored_File_Name);
    }
}
