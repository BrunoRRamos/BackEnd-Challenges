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
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {

    @JsonProperty("email")
    @NotEmpty
    private String email;

    @JsonProperty("password")
    @NotEmpty
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRoles role;
}
