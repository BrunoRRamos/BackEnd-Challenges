package com.desafios_backend.auth.dto;

import com.desafios_backend.auth.RegexPatterns;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @JsonProperty("userEmail")
    @NotEmpty
    private String email;

    @JsonProperty("userPasword")
    @NotEmpty
    @Size(min = 6, max = 16)
    @Pattern(
        regexp = RegexPatterns.PASSWORD_PATTERN,
        message = "Password must have some special character and number"
    )
    private String password;
}
