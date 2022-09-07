package com.cydeo.bank_simulation_review.service;

import com.cydeo.bank_simulation_review.entity.Account;
import com.cydeo.bank_simulation_review.entity.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TransactionService {

    Transaction makeTransfer(BigDecimal amount, Date creationDate, Account sender, Account receiver, String message);


    List<Transaction> findAll();

}
