package org.sb_week4.controller;

import jakarta.annotation.PostConstruct;
import org.sb_week4.exception.ExistException;
import org.sb_week4.exception.NotFoundException;
import org.sb_week4.exception.NotMatchException;
import org.sb_week4.model.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountController {
    private List<Account> accountList = new ArrayList<>();

    @PostConstruct
    public void init() {
        Account st1 = new Account("Phuong", "2222");
        Account st2 = new Account("Kieu", "abcd");
        Account st3 = new Account("Nam", "12ab");

        accountList.add(st1);
        accountList.add(st2);
        accountList.add(st3);
    }
    @GetMapping("/list")
    public String viewAll(Model model){
        model.addAttribute("List", accountList);
        return "home";
    }

    @ExceptionHandler(NotFoundException.class)
    @GetMapping("/list/pro")
    public String viewAccount(@RequestParam String name, Model model){
        for(Account i: accountList){
            if( i.getUseName().equals(name)){
                model.addAttribute("account", i);
            }
        }
        return "details";
    }

    @GetMapping("/add")
        public String addList(){
        return "addA";
    }

    @ExceptionHandler({ExistException.class, NotMatchException.class})
    @PostMapping("/add/pro")
    public String addAccount(@RequestParam String name,
                             @RequestParam String pass,
                             @RequestParam String passreturn,
                             Model model){

        if( pass.equals(passreturn)){
            Account st = new Account(name, pass);
            accountList.add(st);
            return "redirect:/list";
        }else{
            model.addAttribute("re", "Password doesn't match");
            return "addA";
        }

    }

    @GetMapping("/delete")
    public String deleteList(){
        return "deleteA";
    }

    @ExceptionHandler(NotFoundException.class)
    @PostMapping("/delete/pro")
    public String deleteAcount(@RequestParam String name, Model model){
        for(Account i: accountList){
            if( i.getUseName().equals(name)){
                accountList.remove(i);
                return "redirect:/list";
            }
        }
         model.addAttribute("er", "Not user!!!");
         return "deleteA";

    }
}
