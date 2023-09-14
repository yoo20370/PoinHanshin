package com.project.poinhanshin.service.protectboard;

import com.project.poinhanshin.domain.protectboard.ProtectBoardFileDto;
import com.project.poinhanshin.mapper.protectboard.ProtectBoardFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public int deleteFile(Integer protectboardfileno) {
        return protectBoardFileMapper.deleteFile(protectboardfileno);
    }

    @Override
    public int selectCnt(Integer protectboardfileno) {
        return protectBoardFileMapper.selectCnt(protectboardfileno);
    }

    @Override
    public int insetFile(MultipartFile[] multipartFile, Integer protectboardno){
        if(multipartFile == null)
            return 0;
        for(int i = 0 ; i < multipartFile.length; i++){

            String originalFileName = multipartFile[i].getOriginalFilename();

            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;

            String savePath = "/Users/yuyeong-u/fileStorage/protectboard/" + storedFileName;

            ProtectBoardFileDto protectBoardFileDto = new ProtectBoardFileDto(null , null , originalFileName, storedFileName , protectboardno );
            protectBoardFileMapper.insertFiles(protectBoardFileDto);
        }
        return 1;
    }
}
