package ru.ex.accountms.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ex.accountms.dto.events.CreateCardEvent;
import ru.ex.accountms.enums.AccountStatus;
import ru.ex.accountms.enums.CardStatus;
import ru.ex.accountms.exception.custom.AccountIsBlockedException;
import ru.ex.accountms.models.Account;
import ru.ex.accountms.models.Card;
import ru.ex.accountms.repository.CardRepository;
import ru.ex.accountms.service.AccountService;
import ru.ex.accountms.service.CardService;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final AccountService accountService;

    @Override
    @Transactional
    public Card createCard(CreateCardEvent event) {
        Card card = new Card();
        Account account = accountService.getAccountById(event.getAccountId());

        if (account.getStatus().equals(AccountStatus.BLOCKED))
            throw new AccountIsBlockedException();

        card.setAccount(account);
        card.setCardPaymentSystem(event.getCardPaymentSystem());
        card.setCardId("" + new Random().nextInt(1000000)); // mock value
        card.setStatus(CardStatus.ACTIVE);

        return cardRepository.save(card);
    }
}
