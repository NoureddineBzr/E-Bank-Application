package com.E_Bank.bank.controller;

import com.E_Bank.bank.model.Transaction;
import com.E_Bank.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/createTransaction/comptes/{compteId}")
    public Transaction createTransaction(@PathVariable int compteId, @RequestBody Transaction transaction) {
        return transactionService.createTransaction(compteId, transaction);
    }

    @GetMapping("/getTransaction/{id}")
    public Transaction getTransaction(@PathVariable int id) {
        return transactionService.getTransaction(id);
    }

    @GetMapping("/getAllTransactions")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/getTransactionsByCompte/comptes/{compteId}")
    public List<Transaction> getTransactionsByCompte(@PathVariable int compteId) {
        return transactionService.getTransactionsByCompte(compteId);
    }
}
