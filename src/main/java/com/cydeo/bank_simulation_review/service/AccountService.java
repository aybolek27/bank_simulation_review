package com.cydeo.bank_simulation_review.service;

import com.cydeo.bank_simulation_review.model.Account;
import com.cydeo.bank_simulation_review.enums.AccountType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface AccountService {


    Account createNewAccount(BigDecimal balance, Date creationDate, AccountType accountType, Long userId);

    List<Account> listAllAccount();

    void deleteAccount(UUID userId);

    Account retrieveById(UUID account);
}
