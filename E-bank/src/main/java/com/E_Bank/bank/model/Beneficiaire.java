package com.E_Bank.bank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Beneficiaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBenificiaire;

    private String nomBenificiaire;

    private String numeroCompte;

    private String banque;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @OneToMany(mappedBy = "beneficiaire", cascade = CascadeType.ALL)
    private List<TransfertArgent> transferts;
}
