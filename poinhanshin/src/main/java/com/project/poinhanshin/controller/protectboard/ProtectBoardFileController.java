package com.project.poinhanshin.controller.protectboard;

import com.project.poinhanshin.domain.protectboard.ProtectBoardFileDto;
import com.project.poinhanshin.service.protectboard.ProtectBoardFileService;
import com.project.poinhanshin.service.protectboard.ProtectBoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProtectBoardFileController {


    ProtectBoardServiceImpl protectBoardService;
    ProtectBoardFileService protectBoardFileService;

    @Autowired
    public ProtectBoardFileController(ProtectBoardServiceImpl protectBoardService, ProtectBoardFileService protectBoardFileService) {
        this.protectBoardService = protectBoardService;
        this.protectBoardFileService = protectBoardFileService;
    }

    // 첨부 파일 읽어오기
    @GetMapping("/protectboard/file")   // /protectboard/file?protectboardno=100;
    @ResponseBody
    public ResponseEntity<List<ProtectBoardFileDto>> read(Integer protectboardno) {

        /*// 특정 게시물의 첨부파일 데이터들을 읽어온다.
        List<ProtectBoardFileDto> protectBoardFileDtoList = protectBoardFileService.selectFiles(protectboardno);
        List<String> storedFileNameList = new ArrayList<>();

        // 전달할 서버 저장 이름을 List에 담는다.
        for (ProtectBoardFileDto protectBoardFileDto : protectBoardFileDtoList) {
            storedFileNameList.add(protectBoardFileDto.getStored_file_name());
        }*/
        // 특정 게시물의 첨부파일 데이터들을 읽어온다.
        List<ProtectBoardFileDto> protectBoardFileDtoList = protectBoardFileService.selectFiles(protectboardno);

        // 서버 이름을 저장한 List를 반환한다.
        return new ResponseEntity<List<ProtectBoardFileDto>>(protectBoardFileDtoList, HttpStatus.OK);
    }

    // 첨부 파일 삭제
    @DeleteMapping("/protectboard/file") // /protectboard/file/{stored_file_name}?=Hello.jpg;
    @ResponseBody
    public ResponseEntity<String> remove(Integer protectboardfileno) {

        try{
            if( protectBoardFileService.deleteFile(protectboardfileno) != 1 )
                throw new Exception("remove_Fail");

            // 첨부 파일 모두 삭제시 fileAttached 값 0으로 수정 
            if(protectBoardFileService.selectCnt(protectboardfileno) == 0){
                protectBoardService.updateFileAttached(protectboardfileno , 0);
            }
            return new ResponseEntity<String>("Remove_OK" , HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("Remove_ERROR" , HttpStatus.BAD_REQUEST);
        }
    }
}
