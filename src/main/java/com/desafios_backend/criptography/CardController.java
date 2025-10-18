package com.desafios_backend.criptography;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/card", produces = MediaType.APPLICATION_JSON_VALUE)
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    @Transactional
    public ResponseEntity<CardDto> addCreditCard(@RequestBody @Valid CardDto cardDto) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.cardService.createCard(cardDto));
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @GetMapping
    @Transactional
    public ResponseEntity<Collection<CardDto>> getAllCards() throws Exception {
        try {
            return ResponseEntity.ok(this.cardService.getAllCards());
        } catch (Exception e) {
            throw new Exception(e);
        }

    }
}
