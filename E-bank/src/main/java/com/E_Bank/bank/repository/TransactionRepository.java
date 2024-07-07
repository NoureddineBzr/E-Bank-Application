package com.E_Bank.bank.repository;

import com.E_Bank.bank.model.Compte;
import com.E_Bank.bank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByCompte(Compte compte);
}
