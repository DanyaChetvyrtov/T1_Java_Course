package ru.ex.clientms.mapepr;

import org.mapstruct.Mapper;
import ru.ex.clientms.dto.DocumentDto;
import ru.ex.clientms.models.Document;

@Mapper(componentModel = "spring")
public interface DocumentMapper {
    DocumentDto toDto(Document document);

    Document toEntity(DocumentDto documentDto);
}


