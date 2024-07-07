package com.E_Bank.bank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompte;

    private String type;

    private Double soldeInitial;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateCreation;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonBackReference
    private Utillisateur utilisateur;

    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL)
    private List<Carte> cartes;

    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL)
    private List<TransfertArgent> transferts;



}
