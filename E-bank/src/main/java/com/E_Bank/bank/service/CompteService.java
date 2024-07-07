package com.E_Bank.bank.service;

import com.E_Bank.bank.model.Compte;
import com.E_Bank.bank.model.Transaction;
import com.E_Bank.bank.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CompteService {
    @Autowired
    private CompteRepository compteRepository;

    public Compte saveCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    public Compte getCompte(int id) {
        return compteRepository.findById(id).orElseThrow();
    }

    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    public Compte updateCompte(int id, Compte compteDetails) {
        Compte compte = getCompte(id);
        compte.setType(compteDetails.getType());
        compte.setSoldeInitial(compteDetails.getSoldeInitial());
        return compteRepository.save(compte);
    }

    public void deleteCompte(int id) {
        Compte compte = getCompte(id);
        compteRepository.delete(compte);
    }

    public Double consulterSolde(int id) {
        Compte compte = getCompte(id);
        return compte.getSoldeInitial();
    }

    public List<Transaction> consulterHistorique(int id) {
        Compte compte = getCompte(id);
        return compte.getTransactions();
    }

    public void fermerCompte(int id) {
        Compte compte = getCompte(id);
        compte.setDateCreation(new Date());
        compteRepository.save(compte);
    }
}
