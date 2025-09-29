package ru.ex.clientms.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ex.clientms.enums.Status;
import ru.ex.clientms.validation.OnUpdate;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientProductDto {
    @NotNull(groups = {OnUpdate.class}, message = "ID must not be null")
    private Long id;

    @NotNull(message = "Client ID must not be null")
    private Long clientId;

    @NotNull(message = "Product ID must not be null")
    private Long productId;

    @PastOrPresent(message = "Open date must be in the past or today")
    private LocalDate openDate;

    @FutureOrPresent(message = "Close date must be today or in the future")
    private LocalDate closeDate;

    @NotNull(message = "Status must not be null")
    private Status status;
}

