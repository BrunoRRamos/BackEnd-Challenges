package com.desafios_backend.criptography;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {
    @JsonProperty("userDocument")
    @NotBlank
    String userDocument;

    @JsonProperty("creditCardToken")
    @NotBlank
    private String creditCardToken;

    @JsonProperty("value")
    @NotBlank
    private Long value;
}
