package com.ebank.service;

import com.ebank.enums.AccountType;
import com.ebank.enums.Status;
import com.ebank.enums.CardType;
import com.ebank.exception.*;
import com.ebank.model.Account;
import com.ebank.model.BankCard;
import com.ebank.repository.AccountRepository;
import com.ebank.repository.BankCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
public class BankCardService {

    @Autowired
    private BankCardRepository bankCardRepository;

    @Autowired
    private AccountRepository accountRepository;


public List<BankCard> getAllBankCards(Long accountId) {
    return bankCardRepository.findAllByAccountId(accountId);
}


    public BankCard addDefaultBankCard(Account account) {
        BankCard bankCard = new BankCard();
        bankCard.setCardNumber(generateCardNumber());
        bankCard.setExpirationDate(generateExpirationDate(5));
        bankCard.setCardType(CardType.DEBIT);
        bankCard.setStatus(Status.ACTIVATE);
        bankCard.setAccount(account);

        bankCardRepository.save(bankCard);

        return bankCard;
    }

    public BankCard addBankCard(Long accountId, BankCard bankCard) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(AccountNotFoundException::new);

        boolean cardExists = account.getBankCards().stream() .anyMatch(card -> card.getCardType() == bankCard.getCardType());

        if (account.getAccountType() == AccountType.SAVING && bankCard.getCardType() == CardType.CREDIT) {
            throw new CardNotAllowedForSavingsException();
        }

        if (cardExists) {
            throw new CardAlreadyExistsException();
        }

        bankCard.setCardNumber(generateCardNumber());
        bankCard.setExpirationDate(generateExpirationDate(5));
        bankCard.setStatus(Status.ACTIVATE);
        bankCard.setAccount(account);

        bankCardRepository.save(bankCard);

        return bankCard;
    }

    private String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            cardNumber.append(random.nextInt(10));
        }
        return cardNumber.toString();
    }

    private String generateExpirationDate(int yearsToAdd) {
        LocalDate currentDate = LocalDate.now();
        LocalDate expirationDate = currentDate.plusYears(yearsToAdd);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/yy");
        return expirationDate.format(format);
    }

    public BankCard activateOrDeactivateBankCard(Long bankCardId, boolean activate) {
        BankCard bankCard = bankCardRepository.findById(bankCardId)
                .orElseThrow(BankCardNotFoundException::new);

        if (activate) {
            bankCard.setStatus(Status.ACTIVATE);
        } else {
            bankCard.setStatus(Status.DEACTIVATE);
        }

        bankCardRepository.save(bankCard);
        return bankCard;
    }

    public BankCard blockBankCard(Long bankCardId, String blockRaison) {
        BankCard bankCard = bankCardRepository.findById(bankCardId)
                .orElseThrow(BankCardNotFoundException::new);

        if (blockRaison == null || blockRaison.trim().isEmpty()) {
            throw new BlockReasonEmptyException();
        }

        if (bankCard.getStatus() != Status.BLOCK) {
            bankCard.setStatus(Status.BLOCK);
            bankCard.setBlockRaison(blockRaison);
            return bankCardRepository.save(bankCard);
        } else {
            throw new BankCardAlreadyBlockedException();
        }
    }
}
