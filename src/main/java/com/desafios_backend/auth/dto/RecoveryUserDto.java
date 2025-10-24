package com.desafios_backend.auth.dto;

import com.desafios_backend.auth.model.RoleModel;
import java.util.List;

public record RecoveryUserDto(
    Long id,
    String email,
    List<RoleModel> roles
) {
}
