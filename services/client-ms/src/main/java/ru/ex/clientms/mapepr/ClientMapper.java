package ru.ex.clientms.mapepr;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ex.clientms.dto.ClientDto;
import ru.ex.clientms.models.Client;

@Mapper(componentModel = "spring", uses = {DocumentMapper.class})
public interface ClientMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "documents", source = "documents")
    ClientDto toDto(Client client);

    @Mapping(target = "user", ignore = true)
    Client toEntity(ClientDto clientDto);
}
