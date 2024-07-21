package com.ebank;

import com.ebank.enums.AccountType;
import com.ebank.exception.AccountAlreadyExistsException;
import com.ebank.exception.AccountNotFoundException;
import com.ebank.exception.NonZeroBalanceException;
import com.ebank.model.Account;
import com.ebank.model.BankCard;
import com.ebank.model.User;
import com.ebank.repository.AccountRepository;
import com.ebank.repository.UserRepository;
import com.ebank.service.AccountService;
import com.ebank.service.BankCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BankCardService bankCardService;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAccounts_shouldReturnAllAccounts() {
        List<Account> accounts = Arrays.asList(
                new Account(1L, "123456789012", AccountType.CURRENT, 0.0, new Date(System.currentTimeMillis()), false, null, null, null, null, null),
                new Account(2L, "123456789013", AccountType.SAVING, 0.0, new Date(System.currentTimeMillis()), false, null, null, null, null, null)
        );

        when(accountRepository.findAllAccountsByUserId(1L)).thenReturn(accounts);

        List<Account> result = accountService.getAllAccounts(1L);

        assertEquals(2, result.size());
    }

    @Test
    void createAccount_shouldCreateAccount() {
        User user = new User();
        user.setId(1L);
        user.setAccounts(new ArrayList<>());
        Account account = new Account();
        account.setAccountType(AccountType.SAVING);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        when(bankCardService.addDefaultBankCard(any(Account.class))).thenReturn(new BankCard());

        Account result = accountService.createAccount(1L, account);

        assertNotNull(result);
        assertEquals(AccountType.SAVING, result.getAccountType());
    }


    @Test
    void createAccount_shouldThrowAccountAlreadyExistsException() {
        User user = new User();
        user.setId(1L);
        Account existingAccount = new Account();
        existingAccount.setAccountType(AccountType.SAVING);

        user.setAccounts(Arrays.asList(existingAccount));

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Account newAccount = new Account();
        newAccount.setAccountType(AccountType.SAVING);

        assertThrows(AccountAlreadyExistsException.class, () -> accountService.createAccount(1L, newAccount));
    }

    @Test
    void getAccountBalance_shouldReturnBalance() {
        Account account = new Account();
        account.setBalance(100.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        double balance = accountService.getAccountBalance(1L);

        assertEquals(100.0, balance);
    }

    @Test
    void closeAccount_shouldCloseAccount() {
        Account account = new Account();
        account.setBalance(0.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        accountService.closeAccount(1L, "Closing account");

        assertTrue(account.getAccountClosed());
        assertEquals("Closing account", account.getRaisonClosing());
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void closeAccount_shouldThrowNonZeroBalanceException() {
        Account account = new Account();
        account.setBalance(100.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        assertThrows(NonZeroBalanceException.class, () -> accountService.closeAccount(1L, "Closing account"));
    }

    @Test
    void updateAccount_shouldUpdateAccount() {
        Account existingAccount = new Account();
        existingAccount.setBalance(100.0);

        Account updateAccount = new Account();
        updateAccount.setBalance(200.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(existingAccount));
        when(accountRepository.save(existingAccount)).thenReturn(existingAccount);

        Account result = accountService.updateAccount(1L, updateAccount);

        assertNotNull(result);
        assertEquals(200.0, result.getBalance());
    }

    @Test
    void deleteAccount_shouldDeleteAccount() {
        doNothing().when(accountRepository).deleteById(1L);

        accountService.deleteAccount(1L);

        verify(accountRepository, times(1)).deleteById(1L);
    }

    @Test
    void getAccountById_shouldReturnAccount() {
        Account account = new Account();

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        Account result = accountService.getAccountById(1L);

        assertNotNull(result);
    }

    @Test
    void getAccountById_shouldThrowAccountNotFoundException() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> accountService.getAccountById(1L));
    }
}
