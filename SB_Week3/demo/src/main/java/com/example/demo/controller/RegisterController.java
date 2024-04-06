package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
    @PostMapping("/register/process")
    public String register(@RequestParam String username, @RequestParam String password,
                           @RequestParam String confirm, Model model) {
        for (User u : DemoApplication.userList) {
            if (username.equals(u.getUserName())) {
                model.addAttribute("registerErr", "Existed username!");
                return "register";
            }
        }
        if (!confirm.equals(password)) {
            model.addAttribute("registerErr", "Confirm password didn't match password!");
            return "register";
        }
        DemoApplication.userList.add(new User(username, password));
        return "register-successful";
    }
}
