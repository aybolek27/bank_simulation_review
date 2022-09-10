package com.cydeo.bank_simulation_review.entity;

import com.cydeo.bank_simulation_review.enums.AccountStatus;
import com.cydeo.bank_simulation_review.enums.AccountType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class Account {

    private UUID id;
    private BigDecimal balance;
    private AccountType accountType;
    private Date creationDate;
    private Long userId;
    private AccountStatus accountStatus;

}
