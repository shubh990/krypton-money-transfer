package com.krypton.account_money_transfer.repository.service;

import com.krypton.account_money_transfer.api.data.MoneyTransferResponse;
import com.krypton.account_money_transfer.repository.TransactionRepo;
import com.krypton.account_money_transfer.repository.model.Transaction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SaveTransaction {

    private final TransactionRepo repo;

    public SaveTransaction(TransactionRepo repo) {
        this.repo = repo;
    }

    @Async
    public void saveTransactionInDB(MoneyTransferResponse response) {
        Transaction transaction = new Transaction(
                response.getData().getTransactionNumber(),
                response.getData().getTimestamp().toString(),
                response.getData().getTransactionStatus().toString(),
                String.valueOf(response.getData().getFailureReason())
        );
        repo.save(transaction);
    }
}
