package com.krypton.account_money_transfer.service;

import com.krypton.account_money_transfer.api.data.*;
import com.krypton.account_money_transfer.dataaccess.coremoneytransfer.CoreMoneyTransferConnector;
import com.krypton.account_money_transfer.dataaccess.coremoneytransfer.data.CoreMoneyTransferRequest;
import com.krypton.account_money_transfer.dataaccess.coremoneytransfer.data.CoreMoneyTransferResponse;
import com.krypton.account_money_transfer.dataaccess.coremoneytransfer.data.CreditLeg;
import com.krypton.account_money_transfer.dataaccess.coremoneytransfer.data.DebitLeg;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CoreMoneyTransferImpl {

    private final CoreMoneyTransferConnector coreMoneyTransferConnector;

    public CoreMoneyTransferImpl(CoreMoneyTransferConnector coreMoneyTransferConnector) {
        this.coreMoneyTransferConnector = coreMoneyTransferConnector;
    }

    public CoreMoneyTransferResponse coreMoneyTransfer(MoneyTransferRequest request, String transactionNumber) {
        return coreMoneyTransferConnector.moneyTransfer(getRequest(request, transactionNumber));
    }

    public CoreMoneyTransferRequest getRequest(MoneyTransferRequest request, String transactionNumber) {
        DebitLeg debitLeg = new DebitLeg(
                request.getSourceAccount(),
                new BigDecimal(request.getAmount()),
                request.getCurrency()
        );
        CreditLeg creditLeg = new CreditLeg(request.getDestinationAccount());
        return new CoreMoneyTransferRequest(
                debitLeg,
                creditLeg,
                request.getReason(),
                request.getNotes(),
                transactionNumber
        );
    }
}
