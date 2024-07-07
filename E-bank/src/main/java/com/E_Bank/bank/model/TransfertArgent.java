package com.E_Bank.bank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TransfertArgent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTranfert;

    private Double montant;

    private String description;

    @ManyToOne
    @JoinColumn(name = "id_compte")
    private Compte compte;

    @ManyToOne
    @JoinColumn(name = "id_beneficiaire")
    private Beneficiaire beneficiaire;
}
