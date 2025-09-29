package ru.ex.accountms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ex.accountms.dto.AccountDto;
import ru.ex.accountms.mapper.AccountMapper;
import ru.ex.accountms.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getProductByClientId(@PathVariable Long id) {
        return ResponseEntity.ok(
                accountMapper.toDto(accountService.getAccountById(id)));
    }
}
