package com.krypton.account_money_transfer.dataaccess.accountservice;

import com.krypton.account_money_transfer.dataaccess.accountservice.data.AccountServiceRequest;
import com.krypton.account_money_transfer.dataaccess.accountservice.data.AccountServiceResponse;
import com.krypton.account_money_transfer.exception.MoneyTransferFailure;
import com.krypton.account_money_transfer.exception.FailureReasons;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountServiceConnector {

    private final AccountServiceClient client;

    public AccountServiceConnector(AccountServiceClient client) {
        this.client = client;
    }

    public AccountServiceResponse getAccountDetails(AccountServiceRequest request) {
        try {
            ResponseEntity<AccountServiceResponse> response =
                    client.getAccountDetails(request.getCif(), request.getAccountNumber());
            if (response.getStatusCode().is4xxClientError()) {
                throw new MoneyTransferFailure(FailureReasons.ACCOUNT_NOT_FOUND, request.getTransactionNumber());
            }
            return response.getBody();
        } catch (Exception ex) {
            throw new MoneyTransferFailure(FailureReasons.ACCOUNT_SERVICE_FAILED, request.getTransactionNumber());
        }
    }
}
