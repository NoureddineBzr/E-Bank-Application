package com.ebank.model;


import com.ebank.enums.TransactionType;
import com.ebank.enums.CardType;
import com.ebank.enums.TransferType;
import lombok.*;
import jakarta.persistence.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date transactionDate;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private String description;
    @Enumerated(EnumType.STRING)
    private TransferType transferType;
    private String targetAccountNumber;

    @ManyToOne
    private Account account;

    @ManyToOne
    private Beneficiary beneficiary;
}
