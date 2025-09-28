package ru.ex.accountms.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;
import ru.ex.accountms.dto.KafkaProperties;
import ru.ex.accountms.dto.events.CreateCardEvent;
import ru.ex.accountms.dto.events.CreateClientEvent;
import ru.ex.accountms.dto.events.TransactionEvent;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

    private final KafkaProperties kafkaProps;

    /**
     * Общие настройки для всех ConsumerFactory
     */
    private Map<String, Object> commonProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProps.getBootstrapServers());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProps.getConsumer().getGroupId());
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, kafkaProps.getConsumer().getSessionTimeoutMs());
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, kafkaProps.getConsumer().getHeartbeatIntervalMs());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, kafkaProps.getConsumer().getMaxPollRecords());
        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, kafkaProps.getConsumer().getMaxPartitionFetchBytes());
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, kafkaProps.getConsumer().getMaxPollIntervalMs());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaProps.getConsumer().getAutoOffsetReset());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        return props;
    }

    /**
     * Конфиг для обработки событий карточек
     */
    @Bean
    public ConsumerFactory<String, CreateCardEvent> cardEventConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                commonProps(),
                new StringDeserializer(),
                new JsonDeserializer<>(CreateCardEvent.class)
                        .ignoreTypeHeaders()
                        .trustedPackages("*")
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CreateCardEvent> cardEventKafkaListenerContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, CreateCardEvent>();
        factory.setConsumerFactory(cardEventConsumerFactory());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        factory.setCommonErrorHandler(errorHandler());
        return factory;
    }

    /**
     * Конфиг для обработки событий клиента
     */
    @Bean
    public ConsumerFactory<String, CreateClientEvent> clientEventConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                commonProps(),
                new StringDeserializer(),
                new JsonDeserializer<>(CreateClientEvent.class)
                        .ignoreTypeHeaders()
                        .trustedPackages("*")
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CreateClientEvent> clientEventKafkaListenerContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, CreateClientEvent>();
        factory.setConsumerFactory(clientEventConsumerFactory());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        factory.setCommonErrorHandler(errorHandler());
        return factory;
    }

    /**
     * Конфиг для обработки событий транзакции
     */
    @Bean
    public ConsumerFactory<String, TransactionEvent> transactionEventConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                commonProps(),
                new StringDeserializer(),
                new JsonDeserializer<>(TransactionEvent.class)
                        .ignoreTypeHeaders()
                        .trustedPackages("*")
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TransactionEvent> transactionEventKafkaListenerContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, TransactionEvent>();
        factory.setConsumerFactory(transactionEventConsumerFactory());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        factory.setCommonErrorHandler(errorHandler());
        return factory;
    }

    /**
     * Общий error handler
     */
    private CommonErrorHandler errorHandler() {
        DefaultErrorHandler handler = new DefaultErrorHandler(new FixedBackOff(1000L, 3L));
        handler.addNotRetryableExceptions(IllegalStateException.class);
        handler.setRetryListeners((record, ex, deliveryAttempt) ->
                log.error("RetryListeners message = {}, offset = {}, deliveryAttempt = {}",
                        ex.getMessage(), record.offset(), deliveryAttempt));
        return handler;
    }
}
