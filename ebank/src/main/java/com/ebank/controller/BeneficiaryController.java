package com.ebank.controller;

import com.ebank.model.Beneficiary;
import com.ebank.service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/beneficiary")
public class BeneficiaryController {

    @Autowired
    private BeneficiaryService beneficiaryService;

    @GetMapping("/all/{accountId}")
    public ResponseEntity<List<Beneficiary>> getAllBeneficiaries(@PathVariable Long accountId) {
        List<Beneficiary> beneficiaries =  beneficiaryService.getAllBeneficiaries(accountId);
        return ResponseEntity.ok(beneficiaries);
    }

    @PostMapping("/add/{accountId}")
    public ResponseEntity<Beneficiary> addBeneficiary(@PathVariable Long accountId, @RequestBody Beneficiary beneficiary) {
        Beneficiary beneficiary1 = beneficiaryService.updateBeneficiary(accountId, beneficiary);
        return ResponseEntity.ok(beneficiary1);
    }

    @PutMapping("/update/{beneficiaryId}")
    public ResponseEntity<Beneficiary> updateBeneficiary(@PathVariable Long beneficiaryId, @RequestBody Beneficiary beneficiary) {
        beneficiary.setId(beneficiaryId);
        Beneficiary beneficiary1 =  beneficiaryService.saveBeneficiary(beneficiary);
        return ResponseEntity.ok(beneficiary1);
    }

    @DeleteMapping("/delete/{beneficiaryId}")
    public ResponseEntity<Void> deleteBeneficiary(@PathVariable Long beneficiaryId) {
        beneficiaryService.deleteBeneficiary(beneficiaryId);
        return ResponseEntity.noContent().build();
    }
}
