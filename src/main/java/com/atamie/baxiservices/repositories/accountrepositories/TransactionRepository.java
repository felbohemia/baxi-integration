package com.atamie.baxiservices.repositories.accountrepositories;

import com.atamie.baxiservices.domain.account.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findByAccount(String account);

}
