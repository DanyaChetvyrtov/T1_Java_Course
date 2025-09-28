package ru.ex.clientms.service;

import ru.ex.clientms.dto.ClientProductDto;

import java.util.List;

public interface ClientProductService {
    ClientProductDto assignProductToClient(ClientProductDto dto);

    List<ClientProductDto> getAllClientProducts();

    ClientProductDto getClientProductById(Long id);
}

