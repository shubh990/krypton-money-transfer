package com.krypton.account_money_transfer.exception;

import lombok.Getter;

@Getter
public class MoneyTransferFailure extends RuntimeException{

    private final FailureReasons failureReasons;
    private final String transactionNumber;

    public MoneyTransferFailure(FailureReasons failureReasons, String transactionNumber) {
        super();
        this.failureReasons = failureReasons;
        this.transactionNumber = transactionNumber;
    }
}
