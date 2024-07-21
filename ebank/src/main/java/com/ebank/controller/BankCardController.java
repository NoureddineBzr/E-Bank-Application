package com.ebank.controller;

import com.ebank.model.BankCard;
import com.ebank.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/bankCard")
public class BankCardController {
    @Autowired
    private BankCardService bankCardService;

    @GetMapping("/all/{accountId}")
    public ResponseEntity<List<BankCard>> getAllBankCards(@PathVariable Long accountId) {
        List<BankCard> bankCards = bankCardService.getAllBankCards(accountId);
        return ResponseEntity.ok(bankCards);
    }

    @PostMapping("/add/{accountId}")
    public ResponseEntity<BankCard> addBankCard(@PathVariable Long accountId, @RequestBody BankCard bankCard) {
        bankCardService.addBankCard(accountId, bankCard);
        return ResponseEntity.ok(bankCard);
    }

    @PutMapping("/activate/{bankCardId}")
    public ResponseEntity<BankCard> activateBankCard(@PathVariable Long bankCardId, @RequestParam boolean activate) {
        BankCard bankCard = bankCardService.activateOrDeactivateBankCard(bankCardId, activate);
        return ResponseEntity.ok(bankCard);
    }

    @PutMapping("/block/{bankCardId}")
    public ResponseEntity<BankCard> blockBankCard(@PathVariable Long bankCardId, @RequestBody String blockRaison) {
        BankCard bankCard = bankCardService.blockBankCard(bankCardId, blockRaison);
        return ResponseEntity.ok(bankCard);
    }
}
