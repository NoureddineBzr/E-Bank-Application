package com.ebank;

import com.ebank.enums.BankName;
import com.ebank.exception.InsufficientBalanceException;
import com.ebank.model.Account;
import com.ebank.model.Beneficiary;
import com.ebank.model.Transaction;
import com.ebank.repository.AccountRepository;
import com.ebank.repository.BeneficiaryRepository;
import com.ebank.repository.TransactionRepository;
import com.ebank.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private BeneficiaryRepository beneficiaryRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createInternalTransfer_shouldTransferSuccessfully() {
        Account fromAccount = new Account();
        fromAccount.setId(1L);
        fromAccount.setAccountNumber("1234567890123456");
        fromAccount.setBalance(1000.0);
        fromAccount.setAccountClosed(false);

        Account toAccount = new Account();
        toAccount.setId(2L);
        toAccount.setAccountNumber("1234567890123457");
        toAccount.setBalance(500.0);
        toAccount.setAccountClosed(false);

        String toAccountNumber = toAccount.getAccountNumber();
        Double amount = 200.0;
        String description = "Internal transfer test";

        when(accountRepository.findById(1L)).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findByAccountNumber(toAccountNumber)).thenReturn(toAccount);

        transactionService.createInternalTransfer(1L, toAccountNumber, amount, description);

        assertEquals(800.0, fromAccount.getBalance());
        assertEquals(700.0, toAccount.getBalance());

        verify(transactionRepository, times(2)).save(any(Transaction.class));
        verify(accountRepository, times(2)).save(any(Account.class));
    }

    @Test
    void createInternalTransfer_shouldThrowInsufficientBalanceException() {
        Account fromAccount = new Account();
        fromAccount.setId(1L);
        fromAccount.setBalance(100.0);

        Account toAccount = new Account();
        toAccount.setId(2L);
        toAccount.setAccountNumber("1234567890123457");

        String toAccountNumber = toAccount.getAccountNumber();
        Double amount = 200.0;
        String description = "Internal transfer test";

        when(accountRepository.findById(1L)).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findByAccountNumber(toAccountNumber)).thenReturn(toAccount);

        assertThrows(InsufficientBalanceException.class, () -> {
            transactionService.createInternalTransfer(1L, toAccountNumber, amount, description);
        });

        verify(transactionRepository, never()).save(any(Transaction.class));
    }

    @Test
    void createExternalTransfer_shouldTransferSuccessfully() {
        Account fromAccount = new Account();
        fromAccount.setId(1L);
        fromAccount.setAccountNumber("1234567890123456");
        fromAccount.setBalance(1000.0);
        fromAccount.setAccountClosed(false);

        String rib = "5555555555";
        BankName bankName = BankName.CERDIT_DU_MAROC;
        Double amount = 200.0;
        String description = "External transfer test";

        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setId(1L);
        beneficiary.setRib(rib);
        beneficiary.setBankName(bankName);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(fromAccount));
        when(beneficiaryRepository.findByRibAndBankName(rib, bankName)).thenReturn(beneficiary);

        transactionService.createExternalTransfer(1L, rib, bankName, amount, description);

        assertEquals(800.0, fromAccount.getBalance());

        verify(transactionRepository).save(any(Transaction.class));
        verify(accountRepository).save(any(Account.class));
    }

    @Test
    void createExternalTransfer_shouldThrowInsufficientBalanceException() {
        Account fromAccount = new Account();
        fromAccount.setId(1L);
        fromAccount.setBalance(100.0);

        String rib = "5555555555";
        BankName bankName = BankName.CERDIT_DU_MAROC;
        Double amount = 200.0;
        String description = "External transfer test";

        when(accountRepository.findById(1L)).thenReturn(Optional.of(fromAccount));

        assertThrows(InsufficientBalanceException.class, () -> {
            transactionService.createExternalTransfer(1L, rib, bankName, amount, description);
        });

        verify(transactionRepository, never()).save(any(Transaction.class));
    }
}
