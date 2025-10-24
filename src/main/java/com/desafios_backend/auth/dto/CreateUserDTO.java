package com.desafios_backend.auth.dto;

import com.desafios_backend.auth.RegexPatterns;
import com.desafios_backend.auth.enums.UserRoles;
import com.desafios_backend.auth.model.RoleModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class CreateUserDTO {

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

    @Enumerated(EnumType.STRING)
    private RoleModel role;
}
