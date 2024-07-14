package com.ebank.model;

import com.ebank.enums.BankName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Beneficiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String rib;
    @Enumerated(EnumType.STRING)
    private BankName bankName;

    @ManyToOne
    @JsonIgnore
    private Account account;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
