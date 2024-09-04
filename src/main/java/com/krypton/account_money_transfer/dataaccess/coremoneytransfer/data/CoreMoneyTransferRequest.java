package com.krypton.account_money_transfer.dataaccess.coremoneytransfer.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CoreMoneyTransferRequest {
    private DebitLeg debitLeg;
    private CreditLeg creditLeg;
    private String reason;
    private String notes;
    private String transactionNumber;
}
