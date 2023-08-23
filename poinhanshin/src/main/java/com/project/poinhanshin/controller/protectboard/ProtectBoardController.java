package com.project.poinhanshin.controller.protectboard;

import com.project.poinhanshin.domain.etc.PageHandler1;
import com.project.poinhanshin.domain.etc.SearchCondition1;
import com.project.poinhanshin.domain.protectboard.ProtectBoardDto;
import com.project.poinhanshin.service.protectboard.ProtectBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/protectboard")
public class ProtectBoardController {

    ProtectBoardService protectBoardService;

    @Autowired
    public ProtectBoardController(ProtectBoardService protectBoardService) {
        this.protectBoardService = protectBoardService;
    }

    // 임보자 공고 리스트
    @GetMapping("/list")
    public String ProtectBoardList(PageHandler1 ph , SearchCondition1 sc , Model m){

        // 모든 임보자 게시물을 읽어온다.
        List<ProtectBoardDto> list = protectBoardService.bringBoardList(sc);
        int totalCnt = protectBoardService.countListAll();


        m.addAttribute("list",list);
        m.addAttribute("totalCnt",totalCnt);
        m.addAttribute("page", sc.getPage());
        m.addAttribute("pageSize", sc.getPageSize());
        m.addAttribute(m.addAttribute("ph" , ph));
        return "protect/protecterlist";
    }

    // 임보자 공고 상세화면
    @GetMapping("/read")
    public String ProtectBoardRead(Integer protectboardno , SearchCondition1 sc , Model m
            //,@SessionAttribute(name = "loginUser", required = false) User loginUser
    ){
        // 임시 로그인
        Integer LoginId = 1;

        // 임보자 게시물 하나를 가져온다.
        ProtectBoardDto protectBoardDto = protectBoardService.bringBoardOne(protectboardno);

        // 로그인 아이디와 작성자가 같은 경우 Mode WRITER
        if(LoginId.equals(protectBoardDto.getProtectboard_userno()))
            m.addAttribute("WriterCheck", "OK");

        m.addAttribute("protectboard" , protectBoardDto);
        m.addAttribute("page",sc.getPage());
        m.addAttribute("pageSize",sc.getPageSize());
        return "protect/protecter";
    }

    // 임보자 공고 작성화면
    @GetMapping("/write")
    public String ProtectBoardWritePage(SearchCondition1 sc , Model m , RedirectAttributes redirectAttributes
    //,@SessionAttribute(name = "loginUser", required = false) User loginUser
    ){
        // 임시 로그인
        Integer LoginId = 1;

        m.addAttribute("page", sc.getPage());
        m.addAttribute("pageSize", sc.getPageSize());

        // 로그인이 안 된 경우 임보자 게시물 리스트로 이동 ( 로그인 페이지로 이동시키면 좋을 것 )
        if(LoginId == null) {
            redirectAttributes.addFlashAttribute("msg", "NO_LOGIN");
            return "redirect:/protectboard/list";
        }

        m.addAttribute("LoginId", LoginId);
        return "protect/protecterreg";
    }
    @PostMapping("/write")
    public String ProtectBoardWrite(ProtectBoardDto protectBoardDto, SearchCondition1 sc , Model m , RedirectAttributes redirectAttributes
    //,@SessionAttribute(name = "loginUser", required = false) User loginUser
    ){
        m.addAttribute("page" , sc.getPage());
        m.addAttribute("pageSize", sc.getPageSize());

        // 정상적으로 글이 등록되지 않은 경우 작성 페이지로 다시 이동
        if( protectBoardService.insertProductBoard(protectBoardDto) != 1){
            redirectAttributes.addFlashAttribute("msg", "FAIL_INSERT");
            return "redirect:/protectboard/write";
        }

        redirectAttributes.addFlashAttribute("msg", "SUCCESS_INSERT");

        // 자신이 등록한 게시물 내용을 가져오기 위해서 최근 자신의 글을 읽어온다.
        return "redirect:/protectboard/read"+"?protectboardno="+protectBoardService.readWritedBoardno(protectBoardDto.getProtectboard_userno());
    }
    @GetMapping("/modify")
    public String ProtectBoardModifyPage(){
        return "protecteredit";
    }

    @PostMapping("/modify")
    public String ProtectBoardModify(){
        return "";
    }

    @PostMapping("/remove")
    public String ProtectBoardRemove(Integer protectboardno , RedirectAttributes redirectAttributes
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
            redirectAttributes.addFlashAttribute("msg", "FAIL_REMOVE");
            return "redirect:/protectboard/read?protectboardno="+protectboardno;
        }

        // 삭제 성공
        redirectAttributes.addFlashAttribute("msg", "SUCCESS_REMOVE");
        return "redirect:/protectboard/list";
    }

    @GetMapping("/test")
    public String test(){
       //protectBoardService.test();
        byte[] array = null;
        //ProtectBoardDto protectBoardDto2 = new ProtectBoardDto(1, "공고 테스트 수정" , array , "고양이" , true , true );

        //protectBoardService.updateProductBoard(protectBoardDto2 , 2);

        // 삭제 테스트 O
        //protectBoardService.deleteProductBoard(11 , 2);

        // 전체 리스트 , 카테고리 리스트 테스트 O
        SearchCondition1 sc = new SearchCondition1();
        sc.setProtectboard_ani_category(false);
        System.out.println(sc);
        System.out.println(sc.getOffset());
        //System.out.println(protectBoardService.bringanimalFilterList(sc));
        //System.out.println(protectBoardService.bringBoardList(sc));

        return "/test/protectTest";
    }
}
