package ru.ex.clientms.dto;


import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import ru.ex.clientms.validation.OnUpdate;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    @NotNull(groups = {OnUpdate.class}, message = "ID must not be null")
    private Long id;

    @NotNull(message = "User ID must not be null")
    private Long userId;

    @NotNull(groups = {OnUpdate.class}, message = "Client ID must not be null")
    private String clientId;

    @NotNull(message = "Region Code must not be null")
    @Range(min = 1, max = 99, message = "Region Code should be in range between 1 and 99")
    private Integer regionCode;

    @NotNull(message = "Branch Code must not be null")
    @Range(min = 1, max = 99, message = "Branch Code should be in range between 1 and 99")
    private Integer branchCode;

    @NotBlank(message = "First name must not be blank")
    private String firstName;

    private String middleName;

    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Valid
    private List<DocumentDto> documents;
}
