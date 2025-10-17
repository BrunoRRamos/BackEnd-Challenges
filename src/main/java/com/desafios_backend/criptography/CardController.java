package com.desafios_backend.criptography;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/card", produces = MediaType.APPLICATION_JSON_VALUE)
public class CardController {

    @PostMapping
    @Transactional
    public ResponseEntity<CardDto> addCreditCard(@RequestBody @Valid CardDto cardDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cardDto);
    }

    @GetMapping
    @Transactional
    public ResponseEntity<CardDto> getAllCards() {
        return ResponseEntity.ok(new CardDto());
    }
}
