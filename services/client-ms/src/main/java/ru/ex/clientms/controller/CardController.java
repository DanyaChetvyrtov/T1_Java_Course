package ru.ex.clientms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ex.clientms.dto.events.CreateCardEvent;
import ru.ex.clientms.service.CardService;


@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @PostMapping
    public ResponseEntity<Void> createCard(@RequestBody CreateCardEvent card) {
        cardService.sendCreateCardEvent(card);
        return ResponseEntity.ok().build();
    }
}
