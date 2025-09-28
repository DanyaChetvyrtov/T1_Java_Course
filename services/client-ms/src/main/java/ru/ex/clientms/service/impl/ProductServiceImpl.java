package ru.ex.clientms.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ex.clientms.dto.ProductDto;
import ru.ex.clientms.dto.events.ClientCreatedEvent;
import ru.ex.clientms.enums.Key;
import ru.ex.clientms.exception.custom.ProductNotFound;
import ru.ex.clientms.kafka.KafkaClientProducer;
import ru.ex.clientms.mapepr.ProductMapper;
import ru.ex.clientms.models.Product;
import ru.ex.clientms.repository.ProductRepository;
import ru.ex.clientms.service.ProductService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final KafkaClientProducer kafkaClientProducer;
    private final ProductMapper productMapper;

    private final Set<Key> clientProductKeys = Set.of(Key.DC, Key.CC, Key.NS, Key.PENS);
    private final Set<Key> clientCreditProductsKeys = Set.of(Key.IPO, Key.PC, Key.AC);

    @Override
    @Transactional
    public ProductDto createProduct(ProductDto dto) {
        Product product = productRepository.save(productMapper.toEntity(dto));

        ClientCreatedEvent message = new ClientCreatedEvent(product);
        kafkaClientProducer.sendTo(clientProductKeys.contains(product.getKey()) ?
                "client_products" : "client_credit_products", message);

        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        return productMapper.toDto(product);
    }

    @Override
    @Transactional
    public ProductDto updateProduct(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new ProductNotFound("Product with id '" + productDto.getId() + "' not found"));

        product.setName(productDto.getName());
        productRepository.save(product);

        return productMapper.toDto(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

