package com.cydeo.bank_simulation_review.repository;

import com.cydeo.bank_simulation_review.model.Account;
import com.cydeo.bank_simulation_review.enums.AccountStatus;
import com.cydeo.bank_simulation_review.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Component
public class AccountRepository {

    public static List<Account> accountList=new ArrayList<>();

    public Account save(Account account){
        accountList.add(account);
        return account;
    }

    public List<Account> findAll() {
        return accountList;
    }

    public Account findById(UUID accountId) {

        return accountList.stream().filter(account -> account.getId().equals(accountId)).findAny().orElseThrow(()-> new RecordNotFoundException("This account is not in database"));

    }

    public void deleteAccount(UUID userId) {
       findById(userId).setAccountStatus(AccountStatus.DELETED);
    }
}
