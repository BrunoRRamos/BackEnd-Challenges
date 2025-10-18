package com.desafios_backend.criptography;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.Collection;

@RestController
@RequestMapping(value = "/card", produces = MediaType.APPLICATION_JSON_VALUE)
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    @Operation(description = "This endpoint is responsible for saving a card with encrypted data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Card successfully saved"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<CardDto> addCreditCard(@RequestBody @Valid CardDto cardDto) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.cardService.createCard(cardDto));
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @GetMapping
    @PostMapping
    @Operation(description = "This endpoint is responsible for getting all cards data decrypted")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Collection<CardDto>> getAllCards() throws Exception {
        try {
            return ResponseEntity.ok(this.cardService.getAllCards());
        } catch (Exception e) {
            throw new Exception(e);
        }

    }
}
