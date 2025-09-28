package ru.ex.clientms.dto.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import ru.ex.clientms.enums.CardPaymentSystem;

@Builder
@AllArgsConstructor
public class CreateCardEvent {
    @JsonProperty("account_owner_id")
    private Long accountId;
    @JsonProperty("card_payment_system")
    private CardPaymentSystem cardPaymentSystem;
}
