package com.ebank.repository;

import com.ebank.enums.BankName;
import com.ebank.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    @Query("SELECT b FROM Beneficiary b WHERE b.account.id = :accountId")
    List<Beneficiary> findAllByAccountId(@Param("accountId") Long accountId);

    @Query("SELECT b FROM Beneficiary b WHERE b.rib = :rib and b.bankName = :bankname")
    Beneficiary findByRibAndBankName(@Param("rib") String rib, @Param("bankname") BankName bankname);
}
