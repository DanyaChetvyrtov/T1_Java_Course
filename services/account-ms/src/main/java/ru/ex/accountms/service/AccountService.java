package ru.ex.accountms.service;

import ru.ex.accountms.dto.events.CreateClientEvent;
import ru.ex.accountms.models.Account;

public interface AccountService {
    Account getAccountById(Long id);

    Account createAccount(CreateClientEvent event);
}
