package com.krypton.account_money_transfer.service;

import com.krypton.account_money_transfer.dataaccess.accountservice.AccountServiceConnector;
import com.krypton.account_money_transfer.dataaccess.accountservice.data.AccountServiceRequest;
import com.krypton.account_money_transfer.dataaccess.accountservice.data.AccountServiceResponse;
import com.krypton.account_money_transfer.exception.MoneyTransferFailure;
import com.krypton.account_money_transfer.exception.FailureReasons;
import org.springframework.stereotype.Service;

@Service
public class AccountDetails {


    private final AccountServiceConnector accountServiceConnector;

    public AccountDetails(AccountServiceConnector accountServiceConnector) {
        this.accountServiceConnector = accountServiceConnector;
    }

    public boolean getAccountDetails(AccountServiceRequest request) {
        AccountServiceResponse response =
                    accountServiceConnector.getAccountDetails(request);
        return validateAccountTransfer(request, response);
    }

    public boolean validateAccountTransfer(AccountServiceRequest request, AccountServiceResponse response) {

        if (!response.getCurrency().equals(request.getCurrency())) {
            throw new MoneyTransferFailure(FailureReasons.INVALID_CURRENCY, request.getTransactionNumber());
        } else if (response.getBalance().compareTo(request.getAmount()) < 0) {
            throw new MoneyTransferFailure(FailureReasons.INSUFFICIENT_AMOUNT, request.getTransactionNumber());
        } else if (response.getLimits().getDaily().compareTo(request.getAmount()) < 0 ||
                    response.getLimits().getMonthly().compareTo(request.getAmount()) < 0) {
            throw new MoneyTransferFailure(FailureReasons.DAILY_LIMIT, request.getTransactionNumber());
        }
        return true;
    }
}
