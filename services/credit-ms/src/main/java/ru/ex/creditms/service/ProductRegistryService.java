package ru.ex.creditms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ex.creditms.client.AccountMsFeignClient;
import ru.ex.creditms.dto.event.CreateClientEvent;
import ru.ex.creditms.models.PaymentRegistry;
import ru.ex.creditms.models.ProductRegistry;
import ru.ex.creditms.repository.PaymentRegistryRepository;
import ru.ex.creditms.repository.ProductRegistryRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductRegistryService {
    private final PaymentRegistryRepository paymentRegistryRepository;
    private final ProductRegistryRepository productRegistryRepository;
    private final AccountMsFeignClient accountMsFeignClient;

    public ProductRegistry openProduct(CreateClientEvent event) {
        ProductRegistry productRegistry = new ProductRegistry();

        productRegistry.setClientId(event.getId());
        productRegistry.setProductId(event.getProductId());
        productRegistry.setOpenDate(LocalDate.now());

        return productRegistry;
    }
}
