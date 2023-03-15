package com.bit.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
    @RequestMapping("/")
    public String showIndex(Model model) {
        System.out.println("인덱스 화면으로 이동합니다.");
        String name = "황유진";
        model.addAttribute("name", name);
        return "index";
    }

}

