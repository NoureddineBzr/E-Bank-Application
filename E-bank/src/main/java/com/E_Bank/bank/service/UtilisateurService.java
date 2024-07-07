package com.E_Bank.bank.service;

import com.E_Bank.bank.model.Compte;
import com.E_Bank.bank.model.Utillisateur;
import com.E_Bank.bank.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private CompteService compteService;

    public Utillisateur saveUtillisateur(Utillisateur utillisateur) {
        // Save the user first
        Utillisateur savedUtillisateur = utilisateurRepository.save(utillisateur);

        // Create a default account for the user
        Compte defaultCompte = new Compte();
        defaultCompte.setUtilisateur(savedUtillisateur);
        defaultCompte.setType("default"); // Set your default type
        defaultCompte.setSoldeInitial(0.0); // Set your default balance
        compteService.saveCompte(defaultCompte);

        return savedUtillisateur;
    }

    public List<Utillisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Utillisateur getUtilisateurById(int id) {
        return utilisateurRepository.findById(id).orElseThrow();
    }

    public Utillisateur updateUtilisateur(int id, Utillisateur utilisateurDetails) {
        Utillisateur utilisateur = getUtilisateurById(id);
        utilisateur.setNomUser(utilisateurDetails.getNomUser());
        utilisateur.setEmail(utilisateurDetails.getEmail());
        utilisateur.setMotDePasse(utilisateurDetails.getMotDePasse());
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteUtilisateur(int id) {
        Utillisateur utilisateur = getUtilisateurById(id);
        utilisateurRepository.delete(utilisateur);
    }

    public Compte creerCompteUtilisateur(int idUtilisateur, Compte compte) {
        Utillisateur utilisateur = getUtilisateurById(idUtilisateur);
        compte.setUtilisateur(utilisateur);
        return compteService.saveCompte(compte);
    }
}
