package com.project.poinhanshin.controller.protectboard;

import com.project.poinhanshin.domain.etc.PageHandler1;
import com.project.poinhanshin.domain.etc.SearchCondition1;
import com.project.poinhanshin.domain.member.User;
import com.project.poinhanshin.domain.protectboard.ProtectBoardDto;
import com.project.poinhanshin.service.protectboard.ProtectBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
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

        List<ProtectBoardDto> list = protectBoardService.bringBoardList(sc);
        int totalCnt = protectBoardService.countListAll();
        System.out.println(list.toString());

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

        ProtectBoardDto protectBoardDto = protectBoardService.bringBoardOne(protectboardno);

        if(LoginId.equals(protectBoardDto.getProtectboard_userno()))
            m.addAttribute("Mode", "WRITER");

        m.addAttribute("protectDto" , protectBoardDto);
        m.addAttribute("page",sc.getPage());
        m.addAttribute("pageSize",sc.getPageSize());
        return "protect/protecter";
    }

    // 임보자 공고 작성화면
    @GetMapping("/write")
    public String ProtectBoardWritePage(SearchCondition1 sc , Model m , RedirectAttributes redirectAttributes
    //,@SessionAttribute(name = "loginUser", required = false) User loginUser
    ){
        Integer LoginId = 1;

        m.addAttribute("page", sc.getPage());
        m.addAttribute("pageSize", sc.getPageSize());
        if(LoginId == null) {
            redirectAttributes.addFlashAttribute("msg", "NO_LOGIN");
            return "redirect:/protectboard/list";
        }

        //model.addAttribute("user", loginUser);
        return "protect/protecterreg";
    }
    @PostMapping("/write")
    public String ProtectBoardWrite(ProtectBoardDto protectBoardDto,  Model m
    //,@SessionAttribute(name = "loginUser", required = false) User loginUser
    ){
        System.out.println(protectBoardDto);

        return "redirect:/protectboard/write";
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
    public String ProtectBoardRemove(){
        return "";
    }

    @GetMapping("/test")
    public String test(){
       //protectBoardService.test();
        byte[] array = null;
        ProtectBoardDto protectBoardDto2 = new ProtectBoardDto(1, "공고 테스트 수정" , array , "고양이" , true , true );

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
