package ru.ex.accountms.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import ru.ex.accountms.dto.CreateCardEvent;
import ru.ex.accountms.dto.CreateClientEvent;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaClientConsumer {

//    @KafkaListener(
//            id = "#{@kafkaProperties.consumer.groupId}",
//            topics = {
//                    "#{@kafkaProperties.topics.clientProducts}",
//                    "#{@kafkaProperties.topics.clientCreditProducts}",
//                    "#{@kafkaProperties.topics.clientCards}"
//            },
//            containerFactory = "kafkaListenerContainerFactory"
//    )
//    public void listener(CreateCardEvent message,
//                         Acknowledgment ack,
//                         @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
//                         @Header(KafkaHeaders.RECEIVED_KEY) String key) {
//        log.debug("Client consumer: обработка одного сообщения");
//        try {
//            log.info("Topic: {}, Key: {}, Value: {}", topic, key, message);
//        } finally {
//            ack.acknowledge();
//        }
//    }

    @KafkaListener(topics = "client_cards", containerFactory = "cardEventKafkaListenerContainerFactory")
    public void onCardEvent(CreateCardEvent event, Acknowledgment ack) {
        log.info("Got card event {}", event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "client_products", containerFactory = "clientEventKafkaListenerContainerFactory")
    public void onClientEvent(CreateClientEvent event, Acknowledgment ack) {
        log.info("Got client event {}", event);
        ack.acknowledge();
    }
}
