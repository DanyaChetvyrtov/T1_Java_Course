package ru.ex.clientms.mapepr;

import org.mapstruct.Mapper;
import ru.ex.clientms.dto.ProductDto;
import ru.ex.clientms.models.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);
}
