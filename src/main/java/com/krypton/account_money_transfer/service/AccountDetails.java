package com.krypton.account_money_transfer.service;

import com.krypton.account_money_transfer.dataaccess.accountservice.AccountServiceConnector;
import com.krypton.account_money_transfer.dataaccess.accountservice.data.AccountServiceRequest;
import com.krypton.account_money_transfer.dataaccess.accountservice.data.AccountServiceResponse;
import com.krypton.account_money_transfer.exception.MoneyTransferFailure;
import com.krypton.account_money_transfer.exception.FailureReasons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

@Service
public class AccountDetails {

    @Autowired
    AccountServiceConnector accountServiceConnector;

    public boolean getAccountDetails(AccountServiceRequest request) {
        AccountServiceResponse response =
                    accountServiceConnector.getAccountDetails(request);
        if (response.equals(HttpStatusCode.valueOf(400))) {

        }
            return validateAccountTransfer(request, response);
    }

    public boolean validateAccountTransfer(AccountServiceRequest request, AccountServiceResponse response) {
        if (response.getBalance().compareTo(request.getAmount()) < 0) {
            throw new MoneyTransferFailure(FailureReasons.INSUFFICIENT_AMOUNT, request.getTransactionNumber());
        } else if (response.getLimits().getDaily().compareTo(request.getAmount()) < 0 ||
                    response.getLimits().getMonthly().compareTo(request.getAmount()) < 0) {
            throw new MoneyTransferFailure(FailureReasons.DAILY_LIMIT, request.getTransactionNumber());
        } else if (!response.getCurrency().equals(request.getCurrency())) {
            throw new MoneyTransferFailure(FailureReasons.INVALID_CURRENCY, request.getTransactionNumber());
        }
        return true;
    }
}
