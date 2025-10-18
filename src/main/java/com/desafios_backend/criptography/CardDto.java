package com.desafios_backend.criptography;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @NotEmpty
    private String userDocument;

    @JsonProperty("creditCardToken")
    @NotEmpty
    private String creditCardToken;

    @JsonProperty("value")
    @NotNull
    @Positive
    private Long value;
}
