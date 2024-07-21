package com.ebank.dto;

import com.ebank.enums.BankName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequestDTO {

    // For internal transfers
    private String targetAccountNumber;

    // For external transfers
    private String rib;

    private BankName bankName;

    // Common attributes
    private Double amount;

    private String description;
}
