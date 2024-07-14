package com.ebank.controller;

import com.ebank.model.Beneficiary;
import com.ebank.service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/beneficiary")
public class BeneficiaryController {

    @Autowired
    private BeneficiaryService beneficiaryService;

    @GetMapping("/all/{accountId}")
    public List<Beneficiary> getAllBeneficiaries(@PathVariable Long accountId) {
        return beneficiaryService.getAllBeneficiaries(accountId);
    }

    @PostMapping("/add/{accountId}")
    public Beneficiary addBeneficiary(@PathVariable Long accountId, @RequestBody Beneficiary beneficiary) {
        return beneficiaryService.updateBeneficiary(accountId, beneficiary);
    }

    @PutMapping("/update/{beneficiaryId}")
    public Beneficiary updateBeneficiary(@PathVariable Long beneficiaryId, @RequestBody Beneficiary beneficiary) {
        beneficiary.setId(beneficiaryId);
        return beneficiaryService.saveBeneficiary(beneficiary);
    }

    @DeleteMapping("/delete/{beneficiaryId}")
    public void deleteBeneficiary(@PathVariable Long beneficiaryId) {
        beneficiaryService.deleteBeneficiary(beneficiaryId);
    }
}
