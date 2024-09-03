package com.krypton.account_money_transfer.api.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MoneyTransferResponse {
    private Status status;
    private Data data;
}
