package ru.ex.clientms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ex.clientms.dto.ClientProductDto;
import ru.ex.clientms.service.ClientProductService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClientProductController {
    private final ClientProductService clientProductService;

    @PostMapping("/client-products")
    public ResponseEntity<ClientProductDto> assignProductToClient(@Validated @RequestBody ClientProductDto dto) {
        ClientProductDto saved = clientProductService.assignProductToClient(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/client-products")
    public ResponseEntity<List<ClientProductDto>> getAllClientProducts() {
        return ResponseEntity.ok(clientProductService.getAllClientProducts());
    }

    @GetMapping("/client-products/{id}")
    public ResponseEntity<ClientProductDto> getClientProductById(@PathVariable Long id) {
        return ResponseEntity.ok(clientProductService.getClientProductById(id));
    }
}
