package ru.ex.clientms.dto.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.ex.clientms.models.Product;

@AllArgsConstructor
@NoArgsConstructor
public class ClientCreatedEvent {
    @JsonProperty("client_id")
    private Long id;
    @JsonProperty("product_id")
    private String productId;

    public ClientCreatedEvent(Product client) {
        this.id = client.getId();
        this.productId = client.getProductId();
    }
}
