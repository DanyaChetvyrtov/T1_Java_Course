package ru.ex.creditms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ex.creditms.enums.DocumentType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {
    private Long id;
    private String documentId;
    private DocumentType documentType;
    private String documentPrefix;
    private String documentSuffix;
}
