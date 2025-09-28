package ru.ex.clientms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ex.clientms.enums.Key;
import ru.ex.clientms.validation.OnUpdate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @NotNull(groups = {OnUpdate.class}, message = "ID must not be null")
    private Long id;

    @NotBlank(message = "Product name must not be blank")
    private String name;

    @NotNull(message = "Product key must not be null")
    private Key key;

    private String productId;
}
