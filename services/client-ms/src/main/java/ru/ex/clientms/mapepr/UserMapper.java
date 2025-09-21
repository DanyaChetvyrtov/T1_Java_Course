package ru.ex.clientms.mapepr;

import org.mapstruct.Mapper;
import ru.ex.clientms.dto.UserDto;
import ru.ex.clientms.dto.request.UserRegisterRequest;
import ru.ex.clientms.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    User toEntity(UserRegisterRequest userRegisterRequest);
}
