package com.krypton.account_money_transfer.api;

import com.krypton.account_money_transfer.api.data.*;
import com.krypton.account_money_transfer.dataaccess.accountservice.data.AccountServiceRequest;
import com.krypton.account_money_transfer.dataaccess.coremoneytransfer.data.CoreMoneyTransferResponse;
import com.krypton.account_money_transfer.repository.service.SaveTransaction;
import com.krypton.account_money_transfer.service.AccountDetails;
import com.krypton.account_money_transfer.service.CoreMoneyTransferImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class MoneyTransferController {

    private final AccountDetails accountDetails;
    private final CoreMoneyTransferImpl coreMoneyTransferImpl;
    private final SaveTransaction saveTransaction;

    public MoneyTransferController(AccountDetails accountDetails, CoreMoneyTransferImpl coreMoneyTransferImpl, SaveTransaction saveTransaction) {
        this.accountDetails = accountDetails;
        this.coreMoneyTransferImpl = coreMoneyTransferImpl;
        this.saveTransaction = saveTransaction;
    }

    @PostMapping(value = "/transfer")
    public MoneyTransferResponse moneyTransfer(@Valid @RequestBody MoneyTransferRequest request) {
        String transactionNumber = GetMoneyTransferResponse.getTransactionNumber();
        AccountServiceRequest accountServiceRequest = new AccountServiceRequest(
                request.getCif(),
                request.getSourceAccount(),
                new BigDecimal(request.getAmount()),
                request.getCurrency(),
                transactionNumber
        );
        CoreMoneyTransferResponse coreMoneyTransferResponse = null;
        if (accountDetails.getAccountDetails(accountServiceRequest))
            coreMoneyTransferResponse = coreMoneyTransferImpl.coreMoneyTransfer(request, transactionNumber);

        MoneyTransferResponse moneyTransferResponse;
        GetMoneyTransferResponse getMoneyTransferResponse = new GetMoneyTransferResponse();
        if (null != coreMoneyTransferResponse) {
            moneyTransferResponse = (coreMoneyTransferResponse.getStatus().equals(Status.CREDITED.toString())) ?
                    getMoneyTransferResponse.getMoneyTransferResponse(Status.SUCCESS, transactionNumber) :
                    getMoneyTransferResponse.getMoneyTransferResponse(Status.FAILED, transactionNumber);
        } else {
            moneyTransferResponse = getMoneyTransferResponse.getMoneyTransferResponse(Status.FAILED, transactionNumber);
        }
        saveTransaction.saveTransactionInDB(moneyTransferResponse);
        return moneyTransferResponse;
    }
}
