package org.hs.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.hs.entity.Account;
import org.hs.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/account")
public class AccountController {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/search")
    public String search(@RequestParam String name, Model model) {
        List<Account> accounts = new ArrayList<>();
        List<Account> all = accountRepository.findAll();

        for (Account a : all) {
            if (a.getUsername().equals(name)) {
                accounts.add(a);
            }
        }

        model.addAttribute("accounts", accounts);
        return "home";
    }

    @GetMapping("/update")
    public String update(@RequestParam Long id, Model model) {
        Optional<Account> a = accountRepository.findById(id);

        a.ifPresent(account -> model.addAttribute("account", account));

        return "update";
    }

    @Transactional
    @PostMapping("/update/proceed")
    public String proceedUpdate(@ModelAttribute Account account, @RequestParam Long id, HttpSession session) {
        session.removeAttribute("err");
        Optional<Account> updated = accountRepository.findById(id);
        List<Account> accounts = accountRepository.findAll();
        boolean isExisted = false;

        if (updated.isPresent()) {
            Account a = updated.get();
            for (Account acc : accounts) {
                if (acc.getUsername().equals(account.getUsername())) {
                    isExisted = true;
                    break;
                }
            }
            if (!isExisted) {
                a.setUsername(account.getUsername());
                a.setPassword(account.getPassword());
                a.setEmail(account.getEmail());
                accountRepository.save(a);
                return "redirect:/home";
            }
        }

        session.setAttribute("err", "Duplicate username!");
        return "redirect:/account/update?id=" + id;
    }

    @Transactional
    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {
        accountRepository.deleteById(id);

        return "redirect:/home";
    }
}
