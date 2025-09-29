package ru.ex.creditms.dto;

import java.time.LocalDate;
import java.util.List;

public class ClientDto {
    private Long id;
    private Long userId;
    private String clientId;
    private Integer regionCode;
    private Integer branchCode;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private List<DocumentDto> documents;
}
