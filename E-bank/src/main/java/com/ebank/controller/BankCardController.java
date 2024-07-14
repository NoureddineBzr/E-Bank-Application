package com.ebank.controller;

import com.ebank.model.BankCard;
import com.ebank.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/bankCard")
public class BankCardController {
    @Autowired
    private BankCardService bankCardService;

    @GetMapping("/all/{accountId}")
    public List<BankCard> getAllBankCard(@PathVariable Long accountId){
        return bankCardService.getAllBankCards(accountId);
    }

    @PostMapping("/add/{accountId}")
    public BankCard createBankCard(@PathVariable Long accountId, @RequestBody BankCard bankCard) {
        return bankCardService.addBankCard(accountId, bankCard);
    }

    @PutMapping("/activate/{bankCardId}")
    public BankCard activateOrDeactivateBankCard(@PathVariable Long bankCardId, @RequestParam boolean activate) {
        return bankCardService.activateOrDeactivateBankCard(bankCardId, activate);
    }

    @PutMapping("/block/{bankCardId}")
    public BankCard blockBankCard(@PathVariable Long bankCardId, @RequestBody String blockRaison) {
        return bankCardService.blockBankCard(bankCardId, blockRaison);
    }
}
