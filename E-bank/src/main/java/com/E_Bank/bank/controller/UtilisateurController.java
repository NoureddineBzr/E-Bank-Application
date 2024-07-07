package com.E_Bank.bank.controller;

import com.E_Bank.bank.model.Compte;
import com.E_Bank.bank.model.Utillisateur;
import com.E_Bank.bank.service.CompteService;
import com.E_Bank.bank.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Utilisateur")
public class UtilisateurController {

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    CompteService compteService;

    @PostMapping("/saveUtilisateur")
    public Utillisateur saveUtilisateur(@RequestBody Utillisateur utilisateur) {
        return utilisateurService.saveUtillisateur(utilisateur);
    }

    @GetMapping("/getUtilisateur/{id}")
    public Utillisateur getUtilisateur(@PathVariable int id) {
        return utilisateurService.getUtilisateurById(id);
    }

    @GetMapping("/showAllUtilisateur")
    public List<Utillisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @PutMapping("/edit/{id}")
    public Utillisateur updateUtilisateur(@PathVariable int id, @RequestBody Utillisateur utilisateurDetails) {
        return utilisateurService.updateUtilisateur(id, utilisateurDetails);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUtilisateur(@PathVariable int id) {
        utilisateurService.deleteUtilisateur(id);
    }

    @PostMapping("/creerCompteUtilisateur/{id}/comptes")
    public Compte creerCompteUtilisateur(@PathVariable int id, @RequestBody Compte compte) {
        return utilisateurService.creerCompteUtilisateur(id, compte);
    }
}
