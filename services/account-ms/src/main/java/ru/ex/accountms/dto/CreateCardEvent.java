package ru.ex.accountms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.ex.accountms.enums.CardPaymentSystem;

@AllArgsConstructor
@NoArgsConstructor
public class CreateCardEvent {
    @JsonProperty("account_owner_id")
    private Long accountId;
    @JsonProperty("card_payment_system")
    private CardPaymentSystem cardPaymentSystem;
}