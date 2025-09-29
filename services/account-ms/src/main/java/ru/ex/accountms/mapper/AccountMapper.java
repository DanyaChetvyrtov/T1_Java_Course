package ru.ex.accountms.mapper;

import org.mapstruct.Mapper;
import ru.ex.accountms.dto.AccountDto;
import ru.ex.accountms.models.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto toDto(Account entity);

    Account toEntity(AccountDto dto);
}