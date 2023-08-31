package com.project.poinhanshin.controller.login;


import com.project.poinhanshin.domain.member.User;
import com.project.poinhanshin.mapper.login.UserDao;
import com.project.poinhanshin.mapper.login.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {
    private final UserDao userDao;

    @Autowired
    public LoginController(UserDaoImpl userDaoImpl) {
        this.userDao = userDaoImpl;
    }

    @GetMapping("/register")
    public String addForm(@ModelAttribute("user") User user) {
        return "login/registerForm";
    }

    @PostMapping("/register")
    public String add(@Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "login/registerForm";
        User findUser = userDao.findByLoginId(user.getId());
        if (findUser != null) {
            bindingResult.reject("exist", "이 아이디는 사용중입니다.");
            return "login/registerForm";
        }
        userDao.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("user") User user) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated User user, BindingResult bindingResult, HttpServletRequest request) {
        User findUser = userDao.login(user.getId(), user.getPassword());
        if (findUser == null) {
            bindingResult.reject("none", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", findUser);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
