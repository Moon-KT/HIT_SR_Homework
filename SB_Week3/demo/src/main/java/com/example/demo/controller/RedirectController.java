package com.example.demo.controller;

import com.example.demo.DemoApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {
    @GetMapping("/")
    public String redirect() {
        return "index";
    }
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("userList", DemoApplication.userList);
        return "home";
    }
    @GetMapping("register")
    public String register() {
        return "register";
    }
}
