package com.ebank.service;

import com.ebank.exception.AccountNotFoundException;
import com.ebank.exception.BeneficiaryNotFoundException;
import com.ebank.model.Account;
import com.ebank.model.Beneficiary;
import com.ebank.repository.AccountRepository;
import com.ebank.repository.BeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiaryService {

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @Autowired
    private AccountRepository accountRepository;

    public List<Beneficiary> getAllBeneficiaries(Long accountId) {
        return beneficiaryRepository.findAllByAccountId(accountId);
    }

    public Beneficiary getBeneficiaryById(Long id) {
        return beneficiaryRepository.findById(id).orElseThrow(BeneficiaryNotFoundException::new);
    }

    public Beneficiary saveBeneficiary(Beneficiary beneficiary) {
        beneficiary.setId(null);
        return beneficiaryRepository.save(beneficiary);
    }

    public Beneficiary updateBeneficiary(Long accountId, Beneficiary beneficiary) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(AccountNotFoundException::new);
        beneficiary.setAccount(account);
        return beneficiaryRepository.save(beneficiary);
    }

    public void deleteBeneficiary(Long id) {
        beneficiaryRepository.deleteById(id);
    }
}
