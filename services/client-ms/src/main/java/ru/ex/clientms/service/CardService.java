package ru.ex.clientms.service;

import ru.ex.clientms.dto.events.CreateCardEvent;

public interface CardService {
    void sendCreateCardEvent(CreateCardEvent createCardEvent);
}
