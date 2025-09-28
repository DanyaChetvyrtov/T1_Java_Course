package ru.ex.clientms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ex.clientms.enums.DocumentType;
import ru.ex.clientms.validation.OnUpdate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {
    @NotNull(message = "ID must not be null", groups = {OnUpdate.class})
    private Long id;

    @NotBlank(message = "Document ID must not be blank")
    private String documentId;

    @NotNull(message = "Document type must not be null")
    private DocumentType documentType;

    @Size(max = 10, message = "Prefix length must not exceed 10 characters")
    private String documentPrefix;

    @Size(max = 10, message = "Suffix length must not exceed 10 characters")
    private String documentSuffix;
}
