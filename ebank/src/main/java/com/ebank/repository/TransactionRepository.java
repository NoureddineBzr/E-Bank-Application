package com.ebank.repository;

import com.ebank.model.Account;
import com.ebank.model.Beneficiary;
import com.ebank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.account.id = :accountId")
    List<Transaction> findAllByAccountId(@Param("accountId") Long accountId);
}
