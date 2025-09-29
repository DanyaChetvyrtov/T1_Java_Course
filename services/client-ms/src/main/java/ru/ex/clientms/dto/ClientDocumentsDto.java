package ru.ex.clientms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDocumentsDto {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private List<DocumentDto> documents;
}
