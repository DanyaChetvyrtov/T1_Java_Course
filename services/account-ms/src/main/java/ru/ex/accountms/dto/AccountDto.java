package ru.ex.accountms.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.ex.accountms.enums.AccountStatus;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private Long clientId;
    private String productId;
    private BigDecimal balance;
    private BigDecimal interestRate;
    private Boolean isRecalc;
    private Boolean cardExist;
    private AccountStatus status;
}
