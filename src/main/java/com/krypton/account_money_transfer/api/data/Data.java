package com.krypton.account_money_transfer.api.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.krypton.account_money_transfer.exception.FailureReasons;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {
    private ZonedDateTime timestamp;
    private String transactionNumber;
    private Status transactionStatus;
    private FailureReasons failureReason = null;

    public Data(ZonedDateTime timestamp, String transactionNumber, Status transactionStatus) {
        this.transactionNumber = transactionNumber;
        this.timestamp = timestamp;
        this.transactionStatus = transactionStatus;
    }
}
