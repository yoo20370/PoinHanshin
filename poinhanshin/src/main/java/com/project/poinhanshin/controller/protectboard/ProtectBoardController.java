package com.project.poinhanshin.controller.protectboard;

import com.project.poinhanshin.domain.etc.PageHandler1;
import com.project.poinhanshin.domain.etc.SearchCondition1;
import com.project.poinhanshin.domain.protectboard.ProtectBoardDto;
import com.project.poinhanshin.service.protectboard.ProtectBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Calendar;
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
    public String ProtectBoardList (SearchCondition1 sc , Model m , @ModelAttribute("msg") String msg
    //,@SessionAttribute(name = "loginUser", required = false) User loginUser
    ){
        System.out.println(sc);
        // 모든 임보자 게시물을 읽어온다.
        List<ProtectBoardDto> list = protectBoardService.searchResultList(sc);
        int totalCnt = protectBoardService.searchResultCnt(sc);

        PageHandler1 ph = new PageHandler1(totalCnt ,sc);

        // 임보자 리스트
        m.addAttribute("list",list);
        m.addAttribute("sc",sc);
        m.addAttribute(m.addAttribute("ph" , ph));
        m.addAttribute("msg", msg);
        return "protect/protecterlist";
    }

    // 임보자 게시물 상세화면
    @GetMapping("/read")
    public String ProtectBoardRead(Integer protectboardno , SearchCondition1 sc , Model m , @ModelAttribute("msg")  String msg
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

        m.addAttribute("protectboard" , protectBoardDto);
        m.addAttribute("sc",sc);
        m.addAttribute("msg" , msg);
        return "/protect/protecter";
    }

    // 임보자 게시물 작성 상세화면으로 이동
    @GetMapping("/write")
    public String ProtectBoardWritePage(SearchCondition1 sc , Model m , RedirectAttributes redirectAttributes , @ModelAttribute("msg") String msg
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
        m.addAttribute("mode","WRITE");
        return "protect/protecteredit";
    }

    // 임보자 공고 등록
    @PostMapping("/write")
    public String ProtectBoardWrite(ProtectBoardDto protectBoardDto, SearchCondition1 sc ,  RedirectAttributes redirectAttributes
    //,@SessionAttribute(name = "loginUser", required = false) User loginUser
    ){
        redirectAttributes.addAttribute("page" , sc.getPage());
        redirectAttributes.addAttribute("pageSize", sc.getPageSize());
        redirectAttributes.addAttribute("keyword", sc.getKeyword());
        redirectAttributes.addAttribute("ani_category", sc.getAni_category());

        // 정상적으로 글이 등록되지 않은 경우 작성 페이지로 다시 이동
        if( protectBoardService.insertProductBoard(protectBoardDto) != 1){
            redirectAttributes.addFlashAttribute("msg", "FAIL_INSERT");
            return "redirect:/protectboard/write";
        }

        redirectAttributes.addFlashAttribute("msg", "SUCCESS_INSERT");
        redirectAttributes.addAttribute("protectboardno",protectBoardService.readWritedBoardno(protectBoardDto.getProtectboard_userno()));
        // 자신이 등록한 게시물 내용을 가져오기 위해서 최근 자신의 글을 읽어온다.


        return "redirect:/protectboard/read";
    }

    // 임보자 게시물 상세화면
    @GetMapping("/modify")
    public String ProtectBoardModifyMove(ProtectBoardDto protectBoardDto , SearchCondition1 sc , Model m , RedirectAttributes redirectAttributes
    //,@SessionAttribute(name = "loginUser", required = false) User loginUser
    ){
        // 로그인
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

        m.addAttribute("LoginId" ,LoginId);
        m.addAttribute("protectboard" , protectBoardDto);
        m.addAttribute("sc",sc);
        m.addAttribute("mode" , "MODIFY");
        return "/protect/protecteredit";
    }

    // 임보자 게시물 수정
    @PostMapping("/modify")
    public String ProtectBoardModify(ProtectBoardDto protectBoardDto , SearchCondition1 sc , RedirectAttributes redirectAttributes){

        // 임시 로그인
        Integer LoginId = 1;

        redirectAttributes.addAttribute("page" , sc.getPage());
        redirectAttributes.addAttribute("pageSize", sc.getPageSize());
        redirectAttributes.addAttribute("keyword", sc.getKeyword());
        redirectAttributes.addAttribute("ani_category", sc.getAni_category());


        if(protectBoardService.updateProductBoard(protectBoardDto, LoginId) != 1){
            redirectAttributes.addFlashAttribute("msg" , "FAIL_UPDATE");
        }
        else{
            redirectAttributes.addFlashAttribute("msg" , "SUCCESS_UPDATE");
        }

        redirectAttributes.addAttribute("protectboardno" , protectBoardDto.getProtectboardno());
        return "redirect:/protectboard/read";
    }

    // 임보자 게시물 삭제
    @PostMapping("/remove")
    public String ProtectBoardRemove(Integer protectboardno ,SearchCondition1 sc , RedirectAttributes redirectAttributes
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
