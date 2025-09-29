package ru.ex.creditms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.ex.creditms.dto.ClientDocumentsDto;

@FeignClient(name = "client-ms", url = "http://localhost:4001")
public interface AccountMsFeignClient {
    @GetMapping(value = "api/accounts/{id}", consumes = "application/json")
    ClientDocumentsDto getAccount(@PathVariable Long id);
}
