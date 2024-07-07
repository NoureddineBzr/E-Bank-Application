package com.E_Bank.bank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTransaction;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dateHeure;

    private Double montant;

    private String description;

    @ManyToOne
    @JoinColumn(name = "id_compte")
    private Compte compte;

}
