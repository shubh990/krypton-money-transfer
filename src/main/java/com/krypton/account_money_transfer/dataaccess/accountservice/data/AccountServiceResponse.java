package com.krypton.account_money_transfer.dataaccess.accountservice.data;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AccountServiceResponse {
    private BigDecimal balance;
    private String currency;
    private Limits limits;
}
