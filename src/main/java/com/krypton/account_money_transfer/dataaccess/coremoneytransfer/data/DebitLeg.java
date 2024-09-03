package com.krypton.account_money_transfer.dataaccess.coremoneytransfer.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DebitLeg {

    private String account;
    private String amount;
    private String currency;
}
