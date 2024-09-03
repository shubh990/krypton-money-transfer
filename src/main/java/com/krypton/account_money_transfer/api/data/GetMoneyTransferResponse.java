package com.krypton.account_money_transfer.api.data;

import com.krypton.account_money_transfer.exception.FailureReasons;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;
import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
public class GetMoneyTransferResponse {

    private FailureReasons failureReasons;
    public MoneyTransferResponse getMoneyTransferResponse(Status transactionStatus, String transactionNumber) {
        Data data = new Data(
                ZonedDateTime.now(),
                transactionNumber,
                transactionStatus,
                failureReasons
        );
        return new MoneyTransferResponse(Status.SUCCESS, data);
    }

    public static String getTransactionNumber() {
        SecureRandom secureRandom = new SecureRandom();
        return String.valueOf(100_000_000 + secureRandom.nextInt(900_000_000));
    }

}
