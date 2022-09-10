package com.cydeo.bank_simulation_review;

import com.cydeo.bank_simulation_review.entity.Account;
import com.cydeo.bank_simulation_review.enums.AccountStatus;
import com.cydeo.bank_simulation_review.enums.AccountType;
import com.cydeo.bank_simulation_review.service.AccountService;
import com.cydeo.bank_simulation_review.service.TransactionService;
import com.cydeo.bank_simulation_review.service.impl.AccountServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class BankSimulationReviewApplication {

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(BankSimulationReviewApplication.class, args);

        AccountService accountService= applicationContext.getBean(AccountServiceImpl.class);
        TransactionService transactionService=applicationContext.getBean(TransactionService.class);

        Account receiver= accountService.createNewAccount(BigDecimal.TEN, new Date(), AccountType.CHECKING, 1L, AccountStatus.ACTIVE);
        Account sender=accountService.createNewAccount(new BigDecimal(70), new Date(), AccountType.CHECKING, 2L, AccountStatus.ACTIVE);

        accountService.listAllAccount().forEach(System.out::println);

        transactionService.makeTransfer(BigDecimal.TEN, new Date(), sender, receiver,"transfer no:1");

        System.out.println(transactionService.findAll().get(0));

        accountService.listAllAccount().forEach(System.out::println);

        transactionService.makeTransfer(new BigDecimal(5), new Date(), sender, receiver,"transfer no:2");

        System.out.println(transactionService.findAll().get(1));
        accountService.listAllAccount().forEach(System.out::println);



    }

}
