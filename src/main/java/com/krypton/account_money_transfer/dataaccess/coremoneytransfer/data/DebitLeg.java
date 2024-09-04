package com.krypton.account_money_transfer.dataaccess.coremoneytransfer.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class DebitLeg {

    private String account;
    private BigDecimal amount;
    private String currency;
}
