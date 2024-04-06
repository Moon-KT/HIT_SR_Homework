package com.example.demo;

import com.example.demo.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {
    public static List<User> userList = new ArrayList<User>();
    public static void main(String[] args) {
        userList.add(new User("tr", "tr"));
        SpringApplication.run(DemoApplication.class, args);
    }
}
