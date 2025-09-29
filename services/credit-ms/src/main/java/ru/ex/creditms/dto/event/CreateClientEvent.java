package ru.ex.creditms.dto.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientEvent {
    @JsonProperty("client_id")
    private Long id;
    @JsonProperty("product_id")
    private String productId;
}
