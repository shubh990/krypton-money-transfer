package com.krypton.account_money_transfer.dataaccess.accountservice.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class AccountServiceRequest {
    private String cif;
    private String accountNumber;
    private BigDecimal amount;
    private String currency;
    private String transactionNumber;
}
