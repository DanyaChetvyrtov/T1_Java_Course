package ru.ex.clientms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ex.clientms.dto.ClientDocumentsDto;
import ru.ex.clientms.dto.ClientDto;
import ru.ex.clientms.dto.DocumentDto;
import ru.ex.clientms.exception.custom.ClientNotFound;
import ru.ex.clientms.exception.custom.UserNotFound;
import ru.ex.clientms.mapepr.ClientMapper;
import ru.ex.clientms.mapepr.DocumentMapper;
import ru.ex.clientms.models.Client;
import ru.ex.clientms.models.Document;
import ru.ex.clientms.repository.ClientRepository;
import ru.ex.clientms.repository.UserRepository;
import ru.ex.clientms.service.ClientService;
import ru.ex.clientms.utils.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final ClientMapper clientMapper;
    private final DocumentMapper documentMapper;
    private final IdGenerator idGenerator;

    @Override
    @Transactional
    public ClientDto createClient(ClientDto dto) {
        var user = userRepository.findById(dto.getUserId()).orElseThrow(UserNotFound::new);

        Client client = clientMapper.toEntity(dto);
        client.setUser(user);

        String clientId = idGenerator.nextClientId(dto.getRegionCode(), dto.getBranchCode());
        client.setClientId(clientId);

        List<DocumentDto> documents = dto.getDocuments();
        client.setDocuments(new ArrayList<>());
        if (documents != null && !documents.isEmpty())
            for (var document : documents) {
                Document doc = documentMapper.toEntity(document);
                client.addDocument(doc);
            }

        client = clientRepository.save(client);
        return clientMapper.toDto(client);
    }

    @Override
    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream().map(clientMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ClientDto getClientById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(ClientNotFound::new);
        return clientMapper.toDto(client);
    }

    @Override
    public ClientDto addNewDocument(Long id, DocumentDto documentDto) {
        Client client = clientRepository.findById(id).orElseThrow(ClientNotFound::new);
        client.addDocument(documentMapper.toEntity(documentDto));
        client = clientRepository.save(client);
        return clientMapper.toDto(client);
    }

    @Override
    public ClientDocumentsDto getClientDocuments(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(ClientNotFound::new);
        List<DocumentDto> documents = client.getDocuments().stream()
                .map(documentMapper::toDto)
                .collect(Collectors.toList());
        return ClientDocumentsDto.builder()
                .id(id)
                .firstName(client.getFirstName())
                .middleName(client.getMiddleName())
                .lastName(client.getLastName())
                .documents(documents)
                .build();
    }
}

