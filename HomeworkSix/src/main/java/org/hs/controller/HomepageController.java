package org.hs.controller;

import org.hs.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {
    private final AccountRepository accountRepository;

    @Autowired
    public HomepageController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("accounts", accountRepository.findAll());
        return "home";
    }
}
