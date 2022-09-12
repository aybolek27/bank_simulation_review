package com.cydeo.bank_simulation_review.service.impl;

import com.cydeo.bank_simulation_review.model.Account;
import com.cydeo.bank_simulation_review.model.Transaction;
import com.cydeo.bank_simulation_review.enums.AccountType;
import com.cydeo.bank_simulation_review.exceptions.AccountOwnerShipException;
import com.cydeo.bank_simulation_review.exceptions.BadRequestException;
import com.cydeo.bank_simulation_review.exceptions.BalanceNotSufficientException;
import com.cydeo.bank_simulation_review.exceptions.UnderConstructionException;
import com.cydeo.bank_simulation_review.repository.AccountRepository;
import com.cydeo.bank_simulation_review.repository.TransactionRepository;
import com.cydeo.bank_simulation_review.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Component
public class TransactionServiceImpl implements TransactionService {


    @Value("${under_construction}")
    private boolean underConstruction;
    AccountRepository accountRepository;
    TransactionRepository transactionRepository;


    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction makeTransfer(BigDecimal amount, Date creationDate, Account sender, Account receiver, String message) {
        if(!underConstruction){
        checkAccoiuntOwnerShip(sender, receiver);
        validateAccounts(sender,receiver);
        executeBalanceAndUpdateIfRequired(amount, sender, receiver);
        return transactionRepository.save(Transaction.builder()
                                                    .amount(amount)
                                                    .creationDate(creationDate)
                                                    .sender(sender.getId())
                                                    .receiver(receiver.getId())
                                                    .message(message).build());
        }else {
           throw new UnderConstructionException("Make transfer is not possible for now. Please try again later.");
        }
    }

    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, Account sender, Account receiver) {

        if(checkSenderBalance(sender, amount)){

            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));

        }else {
            throw new BalanceNotSufficientException("Balance is not enough for this transaction");
        }

    }

    private boolean checkSenderBalance(Account sender, BigDecimal amount) {

        return sender.getBalance().subtract(amount).compareTo(BigDecimal.ZERO)>0;
    }

    private void validateAccounts(Account sender, Account receiver) {

        if(sender==null || receiver==null){
            throw new BadRequestException("Sender or receiver can not be NULL");
        }

        if(sender.getId().equals(receiver.getId())){
            throw new BadRequestException("Sender account needs to be different than receiver account");
        }

        findAccountById(sender.getId());
        findAccountById(receiver.getId());
    }

    private Account findAccountById(UUID accountId) {


    return accountRepository.findById(accountId);
    }

    private void checkAccoiuntOwnerShip(Account sender, Account receiver) {
        if((sender.getAccountType().equals(AccountType.SAVINGS)||receiver.getAccountType().equals(AccountType.SAVINGS))
                && !sender.getUserId().equals(receiver.getUserId())){

            throw new AccountOwnerShipException("When one of the account type is SAVINGS, sender and receiver has to be the same person");
        }


    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> retrieveLastTransaction() {
        return transactionRepository.retrieveLastTransactions();
    }

    @Override
    public List<Transaction> findTransactionListById(UUID id) {
        return transactionRepository.findTransactionListById(id);
    }
}
