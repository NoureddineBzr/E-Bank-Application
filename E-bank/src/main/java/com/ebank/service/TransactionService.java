package com.ebank.service;

import com.ebank.enums.BankName;
import com.ebank.enums.TransactionType;
import com.ebank.enums.TransferType;
import com.ebank.exception.AccountClosedException;
import com.ebank.exception.AccountNotFoundException;
import com.ebank.exception.InsufficientBalanceException;
import com.ebank.model.Account;
import com.ebank.model.Beneficiary;
import com.ebank.model.Transaction;
import com.ebank.repository.AccountRepository;
import com.ebank.repository.BeneficiaryRepository;
import com.ebank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAccountTransactions(Long accountId) {
        return transactionRepository.findAllByAccountId(accountId);
    }

    @Transactional
    public void createInternalTransfer(Long accountId, String toAccountNumber, Double amount, String description) {
        Account fromAccount = accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber);

        if (fromAccount.getAccountClosed() || toAccount.getAccountClosed()) {
            throw new AccountClosedException();
        }

        if (fromAccount.getBalance() < amount) {
            throw new InsufficientBalanceException();
        }

        Transaction debitTransaction = new Transaction();
        debitTransaction.setTransactionDate(new Date(System.currentTimeMillis()));
        debitTransaction.setAmount(amount);
        debitTransaction.setTransactionType(TransactionType.DEBIT);
        debitTransaction.setDescription(description + " (Debit)");
        debitTransaction.setAccount(fromAccount);
        debitTransaction.setTransferType(TransferType.INTERNAL);
        debitTransaction.setTargetAccountNumber(toAccountNumber);

        Transaction creditTransaction = new Transaction();
        creditTransaction.setTransactionDate(new Date(System.currentTimeMillis()));
        creditTransaction.setAmount(amount);
        creditTransaction.setTransactionType(TransactionType.CREDIT);
        creditTransaction.setDescription(description + " (Credit)");
        creditTransaction.setAccount(toAccount);
        creditTransaction.setTransferType(TransferType.INTERNAL);
        creditTransaction.setTargetAccountNumber(fromAccount.getAccountNumber());

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        transactionRepository.save(debitTransaction);
        transactionRepository.save(creditTransaction);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    @Transactional
    public void createExternalTransfer(Long accountId, String rib, BankName bankName, Double amount, String description) {
        Account fromAccount = accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);

        Beneficiary beneficiary = beneficiaryRepository.findByRibAndBankName(rib, bankName);

        if (fromAccount.getAccountClosed()) {
            throw new AccountClosedException();
        }

        if (fromAccount.getBalance() < amount) {
            throw new InsufficientBalanceException();
        }

        Transaction debitTransaction = new Transaction();
        debitTransaction.setTransactionDate(new Date(System.currentTimeMillis()));
        debitTransaction.setAmount(amount);
        debitTransaction.setTransactionType(TransactionType.DEBIT);
        debitTransaction.setDescription(description);
        debitTransaction.setAccount(fromAccount);
        debitTransaction.setTransferType(TransferType.EXTERNAL);
        debitTransaction.setTargetAccountNumber(beneficiary.getRib());
        debitTransaction.setBeneficiary(beneficiary);

        fromAccount.setBalance(fromAccount.getBalance() - amount);

        transactionRepository.save(debitTransaction);
        accountRepository.save(fromAccount);
    }

}

