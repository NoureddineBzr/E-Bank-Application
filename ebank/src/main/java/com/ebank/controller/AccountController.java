package com.ebank.controller;

import com.ebank.model.Account;
import com.ebank.model.User;
import com.ebank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/account")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccounts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        List<Account> accounts= accountService.getAllAccounts(currentUser.getId());
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("/add")
    public ResponseEntity<Account> addAccount(@RequestBody Account account) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        Account account_ = accountService.createAccount(currentUser.getId(), account);
        return ResponseEntity.ok(account_);
    }

    @PutMapping("/update/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long accountId, @RequestBody Account account) {
        Account account_ = accountService.updateAccount(accountId, account);
        return ResponseEntity.ok(account_);
    }

    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/close/{accountId}")
    public ResponseEntity<Void> closeAccount(@PathVariable Long accountId, @RequestBody String raisonClosing) {
        accountService.closeAccount(accountId, raisonClosing);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/balance/{accountId}")
    public ResponseEntity<Double> getBalance(@PathVariable Long accountId){
        double balance = accountService.getAccountBalance(accountId);
        return ResponseEntity.ok(balance);
    }
}
