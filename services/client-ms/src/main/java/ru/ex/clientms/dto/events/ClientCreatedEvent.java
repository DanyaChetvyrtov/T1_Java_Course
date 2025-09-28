package ru.ex.clientms.dto.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import ru.ex.clientms.enums.Key;
import ru.ex.clientms.models.Product;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
public class ClientCreatedEvent {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("key")
    private Key key;
    @JsonProperty("product_id")
    private String productId;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    public ClientCreatedEvent(Product client) {
        this.id = client.getId();
        this.name = client.getName();
        this.key = client.getKey();
        this.productId = client.getProductId();
        this.createdAt = LocalDateTime.now();
    }
}
