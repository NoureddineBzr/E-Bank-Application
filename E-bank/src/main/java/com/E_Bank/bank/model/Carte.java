package com.E_Bank.bank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Carte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCarte;

    private String numeroCarte;

    private String typeCarte;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateExpiration;

    @ManyToOne
    @JoinColumn(name = "id_compte")
    private Compte compte;
}
