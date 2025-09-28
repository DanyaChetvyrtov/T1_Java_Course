package ru.ex.clientms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ex.clientms.dto.ClientDto;
import ru.ex.clientms.dto.DocumentDto;
import ru.ex.clientms.service.ClientService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/clients")
    public ResponseEntity<ClientDto> createClient(@Validated @RequestBody ClientDto clientDto) {
        ClientDto client = clientService.createClient(clientDto);
        return ResponseEntity
                .created(URI.create("/api/clients/" + client.getId()))
                .body(client);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientDto>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @PostMapping("/clients/{id}/documents")
    public ResponseEntity<ClientDto> addNewDocument(
            @PathVariable Long id, @Validated @RequestBody DocumentDto documentDto) {
        return ResponseEntity.ok(clientService.addNewDocument(id, documentDto));
    }
}
