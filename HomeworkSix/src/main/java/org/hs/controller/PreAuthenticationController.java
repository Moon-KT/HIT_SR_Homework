package org.hs.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.hs.entity.Account;
import org.hs.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PreAuthenticationController {
    private final AccountRepository accountRepository;

    @Autowired
    public PreAuthenticationController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("account", new Account());
        return "register";
    }

    @Transactional
    @PostMapping("/register/proceed")
    public String proceed(@ModelAttribute Account account, Model model) {
        List<Account> accounts = accountRepository.findAll();

        for (Account a : accounts) {
            if (a.getUsername().equals(account.getUsername())) {
                model.addAttribute("err", "Duplicate username!");
                return "register";
            }
        }

        model.addAttribute("err", "");
        accountRepository.save(account);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session,
                        Model model) {
        List<Account> accounts = accountRepository.findAll();

        for (Account a : accounts) {
            if (a.getUsername().equals(username) && a.getPassword().equals(password)) {
                session.setAttribute("account", a);
                model.addAttribute("err", "");
                return "redirect:/home";
            }
        }

        model.addAttribute("err", "Wrong username or password!");
        return "index";
    }

    @GetMapping("/forget")
    public String forget() {
        return "forget-password";
    }

    @GetMapping("/forget/send-code")
    public String sendCode() {
        return "not-supported";
    }
}
