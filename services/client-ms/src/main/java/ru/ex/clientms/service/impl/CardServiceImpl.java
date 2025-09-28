package ru.ex.clientms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.ex.clientms.dto.events.ClientCreatedEvent;
import ru.ex.clientms.dto.events.CreateCardEvent;
import ru.ex.clientms.kafka.KafkaClientProducer;
import ru.ex.clientms.models.Product;
import ru.ex.clientms.service.CardService;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final KafkaClientProducer kafkaClientProducer;
    @Value("${t1.kafka.topic.client_cards}")
    private String cardTopic;

    @Override
    public void sendCreateCardEvent(CreateCardEvent createCardEvent) {
        kafkaClientProducer.sendTo(cardTopic, createCardEvent);
    }
}
