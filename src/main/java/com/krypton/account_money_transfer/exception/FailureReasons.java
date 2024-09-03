package com.krypton.account_money_transfer.exception;

public enum FailureReasons {
    DAILY_LIMIT,
    INVALID_CURRENCY,
    INSUFFICIENT_AMOUNT,
    ACCOUNT_NOT_FOUND,
    ACCOUNT_SERVICE_FAILED,
    MONEY_TRANSFER_FAILED
}
