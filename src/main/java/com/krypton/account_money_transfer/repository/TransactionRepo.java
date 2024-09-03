package com.krypton.account_money_transfer.repository;

import com.krypton.account_money_transfer.repository.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, String> {
}
