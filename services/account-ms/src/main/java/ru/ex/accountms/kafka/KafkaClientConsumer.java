package ru.ex.accountms.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import ru.ex.accountms.dto.events.CreateCardEvent;
import ru.ex.accountms.dto.events.CreateClientEvent;
import ru.ex.accountms.dto.events.TransactionEvent;
import ru.ex.accountms.service.AccountService;
import ru.ex.accountms.service.CardService;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaClientConsumer {
    private final CardService cardService;
    private final AccountService accountService;

    @KafkaListener(topics = "client_cards", containerFactory = "cardEventKafkaListenerContainerFactory")
    public void onCardEvent(CreateCardEvent event, Acknowledgment ack) {
        log.info("Got card event {}", event);
        cardService.createCard(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "client_products", containerFactory = "clientEventKafkaListenerContainerFactory")
    public void onClientEvent(CreateClientEvent event, Acknowledgment ack) {
        log.info("Got client event {}", event);
        accountService.createAccount(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "client_transactions", containerFactory = "transactionEventKafkaListenerContainerFactory")
    public void onTransactionEvent(TransactionEvent event, Acknowledgment ack) {
        log.info("Got transaction event {}", event);
        ack.acknowledge();
    }
}
