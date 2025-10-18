package com.desafios_backend.criptography;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Card")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class CardModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @JsonProperty("userDocument")
    @Column(name = "userDocument", nullable = false)
    private String userDocument;

    @JsonProperty("creditCardToken")
    @Column(name = "creditCardToken", nullable = false)
    private String creditCardToken;

    @JsonProperty("value")
    @Column(name = "value", nullable = false)
    private Long value;
}
