package com.E_Bank.bank.repository;

import com.E_Bank.bank.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Integer> {
}
