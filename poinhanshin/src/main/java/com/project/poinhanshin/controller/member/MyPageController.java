package com.project.poinhanshin.controller.member;

import com.project.poinhanshin.domain.member.User;
import com.project.poinhanshin.service.member.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class MyPageController {

    MyPageService myPageService;
    @Autowired
    public MyPageController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    @GetMapping("/myPage")
    public String myPage(@SessionAttribute(name = "loginUser", required = false) User loginUser, Model model) {
        model.addAttribute("loginUser", loginUser);
        return "members/myPage";
    }
    @GetMapping("/modifyPage")
    public String modifyPageForm(@SessionAttribute(name = "loginUser", required = false) User loginUser, Model model) {
        model.addAttribute("loginUser", loginUser);
        return "members/myPageUpdate";
    }

    @PostMapping("/dismiss")
    public String dismiss(@SessionAttribute(name = "loginUser", required = false) User loginUser, Model model) {
        myPageService.dismiss(loginUser.getUserno());
        return "redirect:/logout";
    }

    @PostMapping("/modifyPage")
    public String modifyPage(User user) {
        myPageService.updateUser(user);
        return "redirect:/logout";
    }

}
