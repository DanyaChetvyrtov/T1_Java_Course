package ru.ex.clientms.service;

import ru.ex.clientms.dto.ClientDocumentsDto;
import ru.ex.clientms.dto.ClientDto;
import ru.ex.clientms.dto.DocumentDto;

import java.util.List;

public interface ClientService {
    ClientDto createClient(ClientDto dto);

    List<ClientDto> getAllClients();

    ClientDto getClientById(Long id);

    ClientDto addNewDocument(Long id, DocumentDto documentDto);

    ClientDocumentsDto getClientDocuments(Long id);
}
