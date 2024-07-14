package com.ebank.repository;

import com.ebank.model.BankCard;
import com.ebank.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankCardRepository extends JpaRepository<BankCard, Long> {
    @Query("SELECT b FROM BankCard b WHERE b.account.id = :accountId")
    List<BankCard> findAllByAccountId(@Param("accountId") Long accountId);
}
