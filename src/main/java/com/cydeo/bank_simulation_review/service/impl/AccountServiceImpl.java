package com.cydeo.bank_simulation_review.service.impl;

import com.cydeo.bank_simulation_review.entity.Account;
import com.cydeo.bank_simulation_review.enums.AccountStatus;
import com.cydeo.bank_simulation_review.enums.AccountType;
import com.cydeo.bank_simulation_review.repository.AccountRepository;
import com.cydeo.bank_simulation_review.service.AccountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Component
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public Account createNewAccount(BigDecimal balance, Date creationDate, AccountType accountType, Long userId, AccountStatus accountStatus) {
        Account account=Account.builder().id(UUID.randomUUID()).userId(userId).accountType(accountType).balance(balance).creationDate(creationDate).accountStatus(accountStatus).build();
        return accountRepository.save(account);
    }

    @Override
    public List<Account> listAllAccount() {
        return accountRepository.findAll();
    }
}
