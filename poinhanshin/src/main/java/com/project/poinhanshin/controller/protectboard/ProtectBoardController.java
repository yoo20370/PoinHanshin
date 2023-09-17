package com.project.poinhanshin.controller.protectboard;

import com.project.poinhanshin.domain.etc.PageHandler;
import com.project.poinhanshin.domain.etc.SearchCondition;
import com.project.poinhanshin.domain.protectboard.ProtectBoardDto;
import com.project.poinhanshin.service.protectboard.ProtectBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/protectboard")
public class ProtectBoardController {

    ProtectBoardService protectBoardService;

    @Autowired
    public ProtectBoardController(ProtectBoardService protectBoardService) {
        this.protectBoardService = protectBoardService;
    }

    // 임보자 게시물 리스트
    @GetMapping("/list")
    public String protectBoardList (SearchCondition sc , Model m , @ModelAttribute("msg") String msg
                                    //,@SessionAttribute(name = "loginUser", required = false) User loginUser
    ){
        System.out.println(sc);
        // 모든 임보자 게시물을 읽어온다.
        List<ProtectBoardDto> list = protectBoardService.searchResultList(sc);
        int totalCnt = protectBoardService.searchResultCnt(sc);

        PageHandler ph = new PageHandler(totalCnt ,sc);

        // 임보자 리스트
        m.addAttribute("list",list);
        m.addAttribute("sc",sc);
        m.addAttribute("ph",ph);
        m.addAttribute("msg", msg);
        return "protect/protecterlist";
    }

    // 임보자 게시물 상세화면
    @GetMapping("/read")
    public String protectBoardRead(Integer protectboardno , SearchCondition sc , Model m , @ModelAttribute("msg")  String msg
                                   //,@SessionAttribute(name = "loginUser", required = false) User loginUser
    ){
        // 로그인
        //Integer LoginId = loginUser.id;

        // 임시 로그인
        Integer LoginId = 1;

        // 임보자 게시물 하나를 가져온다.
        ProtectBoardDto protectBoardDto = protectBoardService.bringBoardOne(protectboardno);

        // 로그인 아이디와 작성자가 같은 경우 Mode WRITER
        if(LoginId.equals(protectBoardDto.getProtectboard_userno()))
            m.addAttribute("WriterCheck", "OK");

        m.addAttribute("LoginId", LoginId);
        m.addAttribute("protectboard" , protectBoardDto);
        m.addAttribute("sc",sc);
        m.addAttribute("msg" , msg);
        return "/protect/protecter";
    }

    // 임보자 게시물 작성 상세화면으로 이동
    @GetMapping("/write")
    public String protectBoardWritePage(SearchCondition sc , Model m , RedirectAttributes redirectAttributes , @ModelAttribute("msg") String msg
                                        //,@SessionAttribute(name = "loginUser", required = false) User loginUser
    ){
        // 로그인
        //Integer LoginId = loginUser.id;

        // 임시 로그인
        Integer LoginId = 1;

        // 로그인 확인 (나중에 loginUser == null로 변경
        // 로그인이 안 된 경우 임보자 게시물 리스트로 이동 ( 로그인 페이지로 이동시키면 좋을 것 )
        if(LoginId == null) {
            redirectAttributes.addAttribute("page", sc.getPage());
            redirectAttributes.addAttribute("pageSize", sc.getPageSize());
            redirectAttributes.addAttribute("keyword", sc.getKeyword());
            redirectAttributes.addAttribute("ani_category", sc.getAni_category());
            redirectAttributes.addFlashAttribute("msg", "NO_LOGIN");
            return "redirect:/protectboard/list";
        }
        m.addAttribute("sc",sc);
        m.addAttribute("LoginId", LoginId);
        m.addAttribute("msg" , msg);
        return "protect/protecterreg";
    }

    // 임보자 게시물 및 이미지 등록
    @PostMapping("/write")
    @ResponseBody
    public ResponseEntity<Integer> protectBoardWrite(@RequestParam(required = false) Integer protectboard_userno , @RequestParam String protectboard_title , @RequestParam String protectboard_content , @RequestParam String breeds ,
                                                     @RequestParam Boolean protectboard_ani_category , @RequestParam Boolean protectstatus , @RequestParam Date starttime , @RequestParam Date deadline ,
                                                     @RequestParam(required = false) List<MultipartFile> protectboardFile , @RequestParam Integer fileAttached
    ) throws IOException {
        ProtectBoardDto protectboardDto = new ProtectBoardDto(protectboard_userno , null , protectboard_title , protectboard_content , breeds , protectboard_ani_category , null , protectstatus , starttime ,deadline , fileAttached );
        protectboardDto.setProtectboardFile(protectboardFile);
        // 첨부 파일이 있는 경우 fileAttached 값을 변경해준다.
        if(protectboardDto.getProtectboardFile() != null){
            protectboardDto.setFileAttached(1);
        }
        Integer currentPbn = protectBoardService.insertProductBoard(protectboardDto);
        return new ResponseEntity<Integer> ( currentPbn , HttpStatus.OK);
    }

    // 임보자 게시물 수정 상세페이지로 이동
    @GetMapping("/modify")
    public String ProtectBoardModifyMove(Integer protectboardno , SearchCondition sc , Model m , RedirectAttributes redirectAttributes
                                         //,@SessionAttribute(name = "loginUser", required = false) User loginUser
    ){
        //Integer LoginId = loginUser.id;

        // 임시 로그인
        Integer LoginId = 1;

        // 로그인 확인 (나중에 loginUser == null로 변경
        if(LoginId == null) {
            redirectAttributes.addAttribute("page", sc.getPage());
            redirectAttributes.addAttribute("pageSize", sc.getPageSize());
            redirectAttributes.addAttribute("keyword", sc.getKeyword());
            redirectAttributes.addAttribute("ani_category", sc.getAni_category());
            redirectAttributes.addFlashAttribute("msg", "NO_LOGIN");
            return "redirect:/protectboard/list";
        }

        ProtectBoardDto protectBoardDto = protectBoardService.bringBoardOne(protectboardno);

        System.out.println(protectBoardDto);
        m.addAttribute("LoginId" ,LoginId);
        m.addAttribute("protectboard" , protectBoardDto);
        m.addAttribute("sc",sc);
        return "/protect/protecteredit";
    }

    // 임보자 게시물 수정
    @PostMapping("/modify")
    @ResponseBody
    public ResponseEntity<Integer> protectBoardModify(@RequestParam Integer protectboard_userno ,@RequestParam Integer protectboardno , @RequestParam String protectboard_title , @RequestParam String protectboard_content , @RequestParam String breeds ,
                                                      @RequestParam Boolean protectboard_ani_category , @RequestParam Boolean protectstatus , @RequestParam Date starttime , @RequestParam Date deadline ,
                                                      @RequestParam(required = false) List<MultipartFile> protectboardFile , @RequestParam Integer fileAttached) throws IOException {
        ProtectBoardDto protectBoardDto = new ProtectBoardDto(protectboard_userno , protectboardno , protectboard_title , protectboard_content , breeds , protectboard_ani_category , null , protectstatus , starttime ,deadline , fileAttached );
        protectBoardDto.setProtectboardFile(protectboardFile);

        // 로그인 연결 시 수정 필요 
        protectBoardService.updateProductBoard(protectBoardDto);
        return new ResponseEntity<Integer>( protectboardno , HttpStatus.OK);

    }
    // 임보자 게시물 삭제
    @PostMapping("/remove")
    public String protectBoardRemove(Integer protectboardno , SearchCondition sc , RedirectAttributes redirectAttributes
                                     //,@SessionAttribute(name = "loginUser", required = false) User loginUser
    ){
        // 임시 로그인
        Integer LoginId = 1;

        // 로그인 여부 확인
        if(LoginId == null) {
            redirectAttributes.addFlashAttribute("msg", "NO_LOGIN");
            return "redirect:/protectboard/list";
        }
        // 삭제 실패했을 때
        if(protectBoardService.deleteProductBoard(protectboardno , LoginId) != 1){

            redirectAttributes.addAttribute("page" , sc.getPage());
            redirectAttributes.addAttribute("pageSize" , sc.getPageSize());
            redirectAttributes.addAttribute("keyword", sc.getKeyword());
            redirectAttributes.addAttribute("ani_category", sc.getAni_category());
            redirectAttributes.addAttribute("protectboardno",protectboardno);
            redirectAttributes.addFlashAttribute("msg", "FAIL_REMOVE");
            return "redirect:/protectboard/read";
        }

        // 삭제 성공
        redirectAttributes.addFlashAttribute("msg", "SUCCESS_REMOVE");
        return "redirect:/protectboard/list";
    }
}
