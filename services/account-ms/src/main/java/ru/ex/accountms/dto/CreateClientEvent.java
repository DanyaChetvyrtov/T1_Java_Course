package ru.ex.accountms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
public class CreateClientEvent {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("product_id")
    private String productId;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}