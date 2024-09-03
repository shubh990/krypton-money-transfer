package com.krypton.account_money_transfer.service;

import com.krypton.account_money_transfer.api.data.*;
import com.krypton.account_money_transfer.dataaccess.coremoneytransfer.CoreMoneyTransferConnector;
import com.krypton.account_money_transfer.dataaccess.coremoneytransfer.data.CoreMoneyTransferResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoreMoneyTransferImpl {

    @Autowired
    CoreMoneyTransferConnector coreMoneyTransferConnector;

    public CoreMoneyTransferResponse coreMoneyTransfer(MoneyTransferRequest request, String transactionNumber) {
        CoreMoneyTransferResponse coreMoneyTransferResponse =
                coreMoneyTransferConnector.moneyTransfer(request, transactionNumber);
        return coreMoneyTransferResponse;
    }
}
