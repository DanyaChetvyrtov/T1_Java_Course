package ru.ex.clientms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ex.clientms.dto.ClientProductDto;
import ru.ex.clientms.models.ClientProduct;
import ru.ex.clientms.repository.ClientProductRepository;
import ru.ex.clientms.service.ClientProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientProductServiceImpl implements ClientProductService {

    private final ClientProductRepository repository;

    @Override
    public ClientProductDto assignProductToClient(ClientProductDto dto) {
        ClientProduct cp = new ClientProduct();
//        cp.setClientId(dto.getClientId());
//        cp.setProductId(dto.getProductId());
        cp.setOpenDate(dto.getOpenDate());
        cp.setCloseDate(dto.getCloseDate());
        cp.setStatus(dto.getStatus());

        cp = repository.save(cp);

//        return new ClientProductDto(cp.getId(), cp.getClientId(), cp.getProductId(),
//                cp.getOpenDate(), cp.getCloseDate(), cp.getStatus());
        return new ClientProductDto();
    }

    @Override
    public List<ClientProductDto> getAllClientProducts() {
//        return repository.findAll().stream()
//                .map(cp -> new ClientProductDto(cp.getId(), cp.getClientId(), cp.getProductId(),
//                        cp.getOpenDate(), cp.getCloseDate(), cp.getStatus()))
//                .collect(Collectors.toList());
        return repository.findAll().stream()
                .map(cp -> new ClientProductDto())
                .collect(Collectors.toList());
    }

    @Override
    public ClientProductDto getClientProductById(Long id) {
        ClientProduct cp = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ClientProduct not found"));
//        return new ClientProductDto(cp.getId(), cp.getClientId(), cp.getProductId(),
//                cp.getOpenDate(), cp.getCloseDate(), cp.getStatus());
        return new ClientProductDto();
    }
}

