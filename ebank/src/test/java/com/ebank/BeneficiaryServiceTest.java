package com.ebank;

import com.ebank.enums.BankName;
import com.ebank.exception.AccountNotFoundException;
import com.ebank.exception.BeneficiaryNotFoundException;
import com.ebank.model.Account;
import com.ebank.model.Beneficiary;
import com.ebank.repository.AccountRepository;
import com.ebank.repository.BeneficiaryRepository;
import com.ebank.service.BeneficiaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BeneficiaryServiceTest {

    @Mock
    private BeneficiaryRepository beneficiaryRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private BeneficiaryService beneficiaryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBeneficiaries_shouldReturnAllBeneficiaries() {
        List<Beneficiary> beneficiaries = new ArrayList<>();
        beneficiaries.add(new Beneficiary(1L, "John Doe", "1234567890", BankName.ATTIJARI, null, null));
        beneficiaries.add(new Beneficiary(2L, "Jane Smith", "0987654321", BankName.CIH, null, null));

        when(beneficiaryRepository.findAllByAccountId(1L)).thenReturn(beneficiaries);

        List<Beneficiary> result = beneficiaryService.getAllBeneficiaries(1L);

        assertEquals(2, result.size());
    }

    @Test
    void getBeneficiaryById_shouldReturnBeneficiary() {
        Beneficiary beneficiary = new Beneficiary(1L, "John Doe", "1234567890", BankName.ATTIJARI, null, null);

        when(beneficiaryRepository.findById(1L)).thenReturn(Optional.of(beneficiary));

        Beneficiary result = beneficiaryService.getBeneficiaryById(1L);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }

    @Test
    void getBeneficiaryById_shouldThrowBeneficiaryNotFoundException() {
        when(beneficiaryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BeneficiaryNotFoundException.class, () -> beneficiaryService.getBeneficiaryById(1L));
    }

    @Test
    void saveBeneficiary_shouldSaveBeneficiary() {
        Beneficiary beneficiaryToSave = new Beneficiary(null, "Alice Brown", "5555555555", BankName.CERDIT_DU_MAROC, null, null);
        Beneficiary savedBeneficiary = new Beneficiary(1L, "Alice Brown", "5555555555", BankName.CERDIT_DU_MAROC, null, null);

        when(beneficiaryRepository.save(beneficiaryToSave)).thenReturn(savedBeneficiary);

        Beneficiary result = beneficiaryService.saveBeneficiary(beneficiaryToSave);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Alice Brown", result.getName());
    }

    @Test
    void updateBeneficiary_shouldUpdateBeneficiary() {
        Account account = new Account();
        account.setId(1L);

        Beneficiary existingBeneficiary = new Beneficiary(1L, "John Doe", "1234567890", BankName.ATTIJARI, null, null);
        Beneficiary updatedBeneficiary = new Beneficiary(1L, "John Doe", "1234567890", BankName.ATTIJARI, account, null);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(beneficiaryRepository.save(existingBeneficiary)).thenReturn(updatedBeneficiary);

        Beneficiary result = beneficiaryService.updateBeneficiary(1L, existingBeneficiary);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(account, result.getAccount());
    }

    @Test
    void updateBeneficiary_shouldThrowAccountNotFoundException() {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setId(1L);

        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> beneficiaryService.updateBeneficiary(1L, beneficiary));
    }

    @Test
    void deleteBeneficiary_shouldDeleteBeneficiary() {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setId(1L);

        doNothing().when(beneficiaryRepository).deleteById(1L);

        beneficiaryService.deleteBeneficiary(1L);

        verify(beneficiaryRepository, times(1)).deleteById(1L);
    }
}
