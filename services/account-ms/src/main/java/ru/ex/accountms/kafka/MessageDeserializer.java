package ru.ex.accountms.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.header.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@NoArgsConstructor
public class MessageDeserializer<T> extends JsonDeserializer<T> {

    public MessageDeserializer(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    private static String getMessage(byte[] data) {
        return new String(data, StandardCharsets.UTF_8);
    }

    @Override
    public T deserialize(String topic, Headers headers, byte[] data) {
        try {
            return super.deserialize(topic, headers, data);
        } catch (Exception e) {
            log.warn("Ошибка десериализации (topic={}): {}", topic, getMessage(data), e);
            return null;
        }
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        try {
            return super.deserialize(topic, data);
        } catch (Exception e) {
            log.warn("Ошибка десериализации (topic={}): {}", topic, getMessage(data), e);
            return null;
        }
    }
}
