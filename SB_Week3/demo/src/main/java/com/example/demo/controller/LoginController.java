package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @PostMapping("login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        for (User u : DemoApplication.userList) {
            if (username.equals(u.getUserName()) && password.equals(u.getUserPassword())) {
                model.addAttribute("userList", DemoApplication.userList);
                return "redirect:/home";
            }
        }
        model.addAttribute("errorLogIn", "Invalid username or password");
        return "index";
    }
}
