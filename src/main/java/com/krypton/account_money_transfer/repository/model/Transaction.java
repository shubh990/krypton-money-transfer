package com.krypton.account_money_transfer.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Transaction {

    @Id
    private String transactionNumber;
    private String timestamp;
    private String transactionStatus;
    private String failureReason;
}
