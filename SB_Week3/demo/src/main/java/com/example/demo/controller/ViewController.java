package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/users")
public class ViewController {
    @GetMapping("/")
    public @ResponseBody ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(DemoApplication.userList);
    }
}
