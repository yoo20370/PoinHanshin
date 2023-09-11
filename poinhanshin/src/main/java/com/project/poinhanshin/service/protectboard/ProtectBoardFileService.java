package com.project.poinhanshin.service.protectboard;

import com.project.poinhanshin.domain.protectboard.ProtectBoardFileDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProtectBoardFileService {

    // 이미지 불러오기
    List<ProtectBoardFileDto> selectFiles(Integer protectboardno);

    // 이미지 삭제 
    int deleteFile(String stored_File_Name );
}
