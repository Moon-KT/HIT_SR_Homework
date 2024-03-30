package org.week2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @PostMapping("/login")
    public String login(@RequestParam("taikhoan") String taikhoan, @RequestParam("matkhau") String matkhau, Model model) {
        if ("trang".equals(taikhoan) && "trang".equals(matkhau)) {
            model.addAttribute("loidangnhap", "");
            return "home";
        } else {
            model.addAttribute("loidangnhap", "Sai mật khẩu.Vui lòng nhập lại");
            return "index";
        }
    }
}
