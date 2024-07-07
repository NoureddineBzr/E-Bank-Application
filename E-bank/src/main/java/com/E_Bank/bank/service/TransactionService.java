package com.E_Bank.bank.service;

import com.E_Bank.bank.model.Compte;
import com.E_Bank.bank.model.Transaction;
import com.E_Bank.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CompteService compteService;

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction getTransaction(int id) {
        return transactionRepository.findById(id).orElseThrow();
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByCompte(int compteId) {
        Compte compte = compteService.getCompte(compteId);
        return transactionRepository.findByCompte(compte);
    }

    public Transaction createTransaction(int compteId, Transaction transaction) {
        Compte compte = compteService.getCompte(compteId);
        transaction.setCompte(compte);
        transaction.setDateHeure(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }
}