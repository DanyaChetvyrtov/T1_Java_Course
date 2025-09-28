package ru.ex.accountms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ex.accountms.dto.events.CreateClientEvent;
import ru.ex.accountms.enums.AccountStatus;
import ru.ex.accountms.exception.custom.AccountNotFound;
import ru.ex.accountms.models.Account;
import ru.ex.accountms.repository.AccountRepository;
import ru.ex.accountms.service.AccountService;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFound("Account with id " + id + " not found"));
    }

    @Override
    public Account createAccount(CreateClientEvent event) {
        Account account = new Account();

        account.setClientId(event.getId());
        account.setProductId(event.getProductId());
        account.setBalance(new BigDecimal(0));
        account.setInterestRate(new BigDecimal(0));
        account.setStatus(AccountStatus.ACTIVE);

        return accountRepository.save(account);
    }
}
