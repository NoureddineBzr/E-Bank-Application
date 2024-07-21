package com.ebank.controller;

import com.ebank.dto.TransferRequestDTO;
import com.ebank.model.Transaction;
import com.ebank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/all/{accountId}")
    public List<Transaction> getAllTransactions(@PathVariable Long accountId) {
        return transactionService.getAccountTransactions(accountId);
    }

    @PostMapping("/internal/{accountId}")
    public void internalTransaction(@PathVariable Long accountId, @RequestBody TransferRequestDTO transferRequest) {
        transactionService.createInternalTransfer(accountId, transferRequest.getTargetAccountNumber(), transferRequest.getAmount(), transferRequest.getDescription());
    }

    @PostMapping("/external/{accountId}")
    public void createExternalTransfer(@PathVariable Long accountId, @RequestBody TransferRequestDTO transferRequest){
        transactionService.createExternalTransfer(accountId, transferRequest.getRib(), transferRequest.getBankName(), transferRequest.getAmount(), transferRequest.getDescription());
    }

}
