package com.weavus.weavusshoppingmall.controller;

import com.weavus.weavusshoppingmall.entity.User;
import com.weavus.weavusshoppingmall.repo.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserRepository userRepository;


    //todo 중요기록을 하는 명령어 현장방식이 존재함
    @GetMapping("/login")
    private String login(){

        return "login";
    }

    @PostMapping("/login")
    private String login(String id, String password, Model model, HttpServletRequest request){
       User user = userRepository.findByIdAndPassword(id, password);
        if (user != null) {
            HttpSession session = request.getSession();//세션 사용 방법 (1
            session.setAttribute("sessionUserName", user.getUserName()); // (2 통째로 넣어두 됨 ("sessionUserName",seresult)
            return "redirect:/";
        } else {
            model.addAttribute("msg", "아이디 또는 비밀번호를 확인해 주세요.");
            return "login"; //컨트롤러를 재 구축하는 것->"redirect:/main" **
        }
    }
}