package org.hs.controller;

import org.hs.entity.Account;
import org.hs.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountRestController {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountRestController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/view")
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountRepository.deleteById(id);
    }

    @PutMapping("/update")
    public void updateAccount(@RequestBody Account account) {
        accountRepository.save(account);
    }
}
