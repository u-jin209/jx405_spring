package com.bit.spring.controller;

import com.bit.spring.model.BoardDTO;
import com.bit.spring.model.UserDTO;
import com.bit.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserController {

    private UserService userService;
    private BoardController boardController;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;

    }

    @RequestMapping("/auth")
    public String auth(HttpSession session, Model model, UserDTO attempt) {
        UserDTO result = userService.auth(attempt);


        if (result != null){
            session.setAttribute("logIn", result);
            return "redirect:/board/showAll/1";

        }else {
            model.addAttribute("message","로그인 정보를 다시 확인해주세요");
            return "index";
        }

    }


//
//    @GetMapping("update/{id}") //update 작성 페이지
//    public String showUpdate(HttpSession session, RedirectAttributes redirectAttributes, Model model, @PathVariable int id){
//        UserDTO logIn =(UserDTO) session.getAttribute("logIn");
//        if(logIn == null){
//            redirectAttributes.addFlashAttribute("message","다시 로그인해주세요");
//            return "redirect:/";
//        }
//        UserDTO u = userService.selectOne(id);
//        if(u==null || u.getId() != logIn.getId() ){
//            redirectAttributes.addFlashAttribute("message","유효하지 않은 접근입니다.");
//            return "redirect:/";
//        }
//        model.addAttribute("result",u);
//        return "/user/update";
//
//    }
//
//    @PostMapping("register")//insert 로직
//    public String insertBoard(HttpSession session,RedirectAttributes redirectAttributes, UserDTO userDTO,Model model){
//
//
//        userService.insert(userDTO);
//
//        return "redirect:/";
//    }
//    @GetMapping("register/new")//insert 작성 페이지
//    public String showInsert(HttpSession session,RedirectAttributes redirectAttributes, Model model) {
//
//        return "/user/register";
//
//    }
//
//
//
//    @PostMapping("update") //update 로직
//    public String updateBoard(HttpSession session,RedirectAttributes redirectAttributes, Model model,UserDTO userDTO ){
//        UserDTO logIn =(UserDTO) session.getAttribute("logIn");
//        if(logIn == null){
//            return "redirect:/";
//        }
//        UserDTO origin = userService.selectOne(logIn.getId());
//        if(origin == null){
//            return "redirect:/board/showAll/1";
//        }
//
//        origin.setNickname(userDTO.getNickname());
//        session.setAttribute("logIn", origin);
//
//        userService.update(origin);
//        return "redirect:/board/showAll/1";
//    }
//
//    @GetMapping("delete/{id}")
//    public String delete(HttpSession session,RedirectAttributes redirectAttributes, Model model,@PathVariable int id){
//        UserDTO logIn =(UserDTO) session.getAttribute("logIn");
//        if(logIn == null){
//            redirectAttributes.addFlashAttribute("message","다시 로그인해주세요");
//            return "redirect:/";
//        }
//        UserDTO u = userService.selectOne(id);
//
//
//        userService.delete(id);
//        return "redirect:/";
//    }

}
