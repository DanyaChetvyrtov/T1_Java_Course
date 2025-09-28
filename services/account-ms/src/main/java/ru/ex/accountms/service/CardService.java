package ru.ex.accountms.service;

import ru.ex.accountms.dto.events.CreateCardEvent;
import ru.ex.accountms.models.Card;

public interface CardService {
    Card createCard(CreateCardEvent event);
}
