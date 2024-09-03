package com.krypton.account_money_transfer.dataaccess.coremoneytransfer;

import com.krypton.account_money_transfer.api.data.MoneyTransferRequest;
import com.krypton.account_money_transfer.dataaccess.coremoneytransfer.data.CoreMoneyTransferRequest;
import com.krypton.account_money_transfer.dataaccess.coremoneytransfer.data.CoreMoneyTransferResponse;
import com.krypton.account_money_transfer.dataaccess.coremoneytransfer.data.DebitLeg;
import com.krypton.account_money_transfer.exception.FailureReasons;
import com.krypton.account_money_transfer.exception.MoneyTransferFailure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CoreMoneyTransferConnector {

    @Autowired
    CoreMoneyTransferServiceClient client;

    public CoreMoneyTransferResponse moneyTransfer(MoneyTransferRequest request, String transactionNumber) {
        try {
            ResponseEntity<CoreMoneyTransferResponse> response =
                    client.coreMoneyTransfer(getRequest(request, transactionNumber));
            return response.getBody();
        } catch (Exception ex) {
            throw new MoneyTransferFailure(FailureReasons.MONEY_TRANSFER_FAILED, transactionNumber);
        }
    }

    public CoreMoneyTransferRequest getRequest(MoneyTransferRequest request, String transactionNumber) {
        DebitLeg debitLeg = new DebitLeg(
                request.getSourceAccount(),
                request.getAmount(),
                request.getCurrency()
        );
        return new CoreMoneyTransferRequest(
                debitLeg,
                request.getDestinationAccount(),
                request.getReason(),
                request.getNotes(),
                transactionNumber
        );
    }

}
