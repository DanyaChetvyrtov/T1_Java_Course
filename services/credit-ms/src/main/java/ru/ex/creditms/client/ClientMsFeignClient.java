package ru.ex.creditms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.ex.creditms.dto.ClientDocumentsDto;

@FeignClient(name = "client-ms", url = "http://localhost:4000")
public interface ClientMsFeignClient {
    @GetMapping(value = "api/clients/{id}/documents", consumes = "application/json")
    ClientDocumentsDto getClientDocuments(@PathVariable Long id);
}
