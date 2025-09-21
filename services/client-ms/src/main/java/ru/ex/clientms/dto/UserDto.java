package ru.ex.clientms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotBlank(message = "ID must not be blank")
    private Long id;
    @NotBlank(message = "Login must not be blank")
    private String login;
    @Email(message = "Email must be valid")
    @NotBlank(message = "Email must not be blank")
    private String email;
}
