package ru.ex.creditms.kafka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "t1.kafka")
public class KafkaProperties {

    private String bootstrapServers;

    private Consumer consumer = new Consumer();
    private Topics topics = new Topics();
    private Listener listener = new Listener();

    @Data
    public static class Consumer {
        private String groupId;
        private Integer sessionTimeoutMs = 45000;
        private Integer heartbeatIntervalMs = 3000;
        private Integer maxPollRecords = 1;
        private Integer maxPartitionFetchBytes = 300000;
        private Integer maxPollIntervalMs = 300000;
        private String autoOffsetReset = "earliest";
    }

    @Data
    public static class Topics {
        private String clientProducts;
        private String clientCreditProducts;
        private String clientCards;
    }

    @Data
    public static class Listener {
        private Long pollTimeout = 1000L;
    }
}

