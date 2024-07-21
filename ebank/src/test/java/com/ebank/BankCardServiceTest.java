package com.ebank;

import com.ebank.enums.AccountType;
import com.ebank.enums.CardType;
import com.ebank.enums.Status;
import com.ebank.exception.*;
import com.ebank.model.Account;
import com.ebank.model.BankCard;
import com.ebank.repository.AccountRepository;
import com.ebank.repository.BankCardRepository;
import com.ebank.service.BankCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BankCardServiceTest {

    @Mock
    private BankCardRepository bankCardRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private BankCardService bankCardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBankCards_shouldReturnAllBankCards() {
        List<BankCard> bankCards = Arrays.asList(
                new BankCard(1L, "1234567890123456", null, CardType.DEBIT, Status.ACTIVATE, null, null),
                new BankCard(2L, "1234567890123457", null, CardType.DEBIT, Status.ACTIVATE, null, null)
        );

        when(bankCardRepository.findAllByAccountId(1L)).thenReturn(bankCards);

        List<BankCard> result = bankCardService.getAllBankCards(1L);

        assertEquals(2, result.size());
    }

    @Test
    void addDefaultBankCard_shouldAddDefaultBankCard() {
        Account account = new Account();

        BankCard result = bankCardService.addDefaultBankCard(account);

        assertNotNull(result);
        assertEquals(CardType.DEBIT, result.getCardType());
        assertEquals(Status.ACTIVATE, result.getStatus());
    }

    @Test
    void addBankCard_shouldAddBankCard() {
        Account account = new Account();
        account.setAccountType(AccountType.CURRENT);
        BankCard bankCard = new BankCard();
        bankCard.setCardType(CardType.CREDIT);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(bankCardRepository.save(any(BankCard.class))).thenReturn(bankCard);

        BankCard result = bankCardService.addBankCard(1L, bankCard);

        assertNotNull(result);
        assertEquals(CardType.CREDIT, result.getCardType());
        assertEquals(Status.ACTIVATE, result.getStatus());
    }

    @Test
    void addBankCard_shouldThrowCardNotAllowedForSavingsException() {
        Account account = new Account();
        account.setAccountType(AccountType.SAVING);
        BankCard bankCard = new BankCard();
        bankCard.setCardType(CardType.CREDIT);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        assertThrows(CardNotAllowedForSavingsException.class, () -> bankCardService.addBankCard(1L, bankCard));
    }

    @Test
    void activateOrDeactivateBankCard_shouldActivateBankCard() {
        BankCard bankCard = new BankCard();
        bankCard.setStatus(Status.DEACTIVATE);

        when(bankCardRepository.findById(1L)).thenReturn(Optional.of(bankCard));

        BankCard result = bankCardService.activateOrDeactivateBankCard(1L, true);

        assertEquals(Status.ACTIVATE, result.getStatus());
    }

    @Test
    void activateOrDeactivateBankCard_shouldDeactivateBankCard() {
        BankCard bankCard = new BankCard();
        bankCard.setStatus(Status.ACTIVATE);

        when(bankCardRepository.findById(1L)).thenReturn(Optional.of(bankCard));

        BankCard result = bankCardService.activateOrDeactivateBankCard(1L, false);

        assertEquals(Status.DEACTIVATE, result.getStatus());
    }

    @Test
    void blockBankCard_shouldBlockBankCard() {
        BankCard bankCard = new BankCard();
        bankCard.setStatus(Status.ACTIVATE);

        when(bankCardRepository.findById(1L)).thenReturn(Optional.of(bankCard));
        when(bankCardRepository.save(any(BankCard.class))).thenReturn(bankCard);

        BankCard result = bankCardService.blockBankCard(1L, "Lost card");

        assertNotNull(result);
        assertEquals(Status.BLOCK, result.getStatus());
        assertEquals("Lost card", result.getBlockRaison());
    }

    @Test
    void blockBankCard_shouldThrowBlockReasonEmptyException() {
        BankCard bankCard = new BankCard();

        when(bankCardRepository.findById(1L)).thenReturn(Optional.of(bankCard));

        assertThrows(BlockReasonEmptyException.class, () -> bankCardService.blockBankCard(1L, ""));
    }

    @Test
    void blockBankCard_shouldThrowBankCardAlreadyBlockedException() {
        BankCard bankCard = new BankCard();
        bankCard.setStatus(Status.BLOCK);

        when(bankCardRepository.findById(1L)).thenReturn(Optional.of(bankCard));

        assertThrows(BankCardAlreadyBlockedException.class, () -> bankCardService.blockBankCard(1L, "Lost card"));
    }
}
