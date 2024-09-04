package com.krypton.account_money_transfer.dataaccess.coremoneytransfer;

import com.krypton.account_money_transfer.dataaccess.coremoneytransfer.data.CoreMoneyTransferRequest;
import com.krypton.account_money_transfer.dataaccess.coremoneytransfer.data.CoreMoneyTransferResponse;
import com.krypton.account_money_transfer.exception.FailureReasons;
import com.krypton.account_money_transfer.exception.MoneyTransferFailure;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CoreMoneyTransferConnector {

    private final CoreMoneyTransferServiceClient client;

    public CoreMoneyTransferConnector(CoreMoneyTransferServiceClient client) {
        this.client = client;
    }

    public CoreMoneyTransferResponse moneyTransfer(CoreMoneyTransferRequest request) {
        try {
            ResponseEntity<CoreMoneyTransferResponse> response =
                    client.coreMoneyTransfer(request);
            return response.getBody();
        } catch (Exception ex) {
            throw new MoneyTransferFailure(FailureReasons.MONEY_TRANSFER_FAILED, request.getTransactionNumber());
        }
    }

}
