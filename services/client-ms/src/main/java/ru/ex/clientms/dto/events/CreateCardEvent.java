package ru.ex.clientms.dto.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.ex.clientms.enums.CardPaymentSystem;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCardEvent {
    @JsonProperty("account_owner_id")
    private Long accountId;
    @JsonProperty("card_payment_system")
    private CardPaymentSystem cardPaymentSystem;
}
