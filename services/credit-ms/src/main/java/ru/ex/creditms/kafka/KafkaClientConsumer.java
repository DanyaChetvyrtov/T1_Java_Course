package ru.ex.creditms.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import ru.ex.creditms.client.ClientMsFeignClient;
import ru.ex.creditms.dto.ClientDocumentsDto;
import ru.ex.creditms.dto.event.CreateClientEvent;
import ru.ex.creditms.service.ProductRegistryService;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaClientConsumer {
    private final ClientMsFeignClient clientMsFeignClient;
    private final ProductRegistryService productRegistryService;

    @KafkaListener(topics = "client_credit_products", containerFactory = "clientEventKafkaListenerContainerFactory")
    public void onClientEvent(CreateClientEvent event, Acknowledgment ack) {
        log.info("Got client event {}", event);
        ClientDocumentsDto response = new ClientDocumentsDto();

//        if (event.getId() != null)
//            response = clientMsFeignClient.getTasks(1L); // захардкоженое значение-заглушка
        productRegistryService.openProduct(event);

        log.info("Got client response {}", response);
        ack.acknowledge();
    }
}
