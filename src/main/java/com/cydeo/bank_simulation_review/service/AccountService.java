package com.cydeo.bank_simulation_review.service;

import com.cydeo.bank_simulation_review.entity.Account;
import com.cydeo.bank_simulation_review.enums.AccountType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface AccountService {


    Account createNewAccount(BigDecimal balance, Date creationDate, AccountType accountType, Long userId);

    List<Account> listAllAccount();

}
