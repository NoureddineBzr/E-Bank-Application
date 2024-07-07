package com.E_Bank.bank.controller;

import com.E_Bank.bank.model.Compte;
import com.E_Bank.bank.model.Transaction;
import com.E_Bank.bank.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/comptes")
public class CompteController {

    @Autowired
    private CompteService compteService;

    @PostMapping("/saveCompte")
    public Compte createCompte(@RequestBody Compte compte) {
        return compteService.saveCompte(compte);
    }

    @GetMapping("/getComptes/{id}")
    public Compte getCompte(@PathVariable int id) {
        return compteService.getCompte(id);
    }

    @GetMapping("/getAllComptes")
    public List<Compte> getAllComptes() {
        return compteService.getAllComptes();
    }

    @PutMapping("/editCompte/{id}")
    public Compte updateCompte(@PathVariable int id, @RequestBody Compte compteDetails) {
        return compteService.updateCompte(id, compteDetails);
    }

    @DeleteMapping("/deleteCompte/{id}")
    public void deleteCompte(@PathVariable int id) {
        compteService.deleteCompte(id);
    }

    @GetMapping("/solde/{id}/solde")
    public Double consulterSolde(@PathVariable int id) {
        return compteService.consulterSolde(id);
    }

    @GetMapping("/historique/{id}/historique")
    public List<Transaction> consulterHistorique(@PathVariable int id) {
        return compteService.consulterHistorique(id);
    }

    @PostMapping("/fermerCompte/{id}/fermer")
    public void fermerCompte(@PathVariable int id) {
        compteService.fermerCompte(id);
    }
}

