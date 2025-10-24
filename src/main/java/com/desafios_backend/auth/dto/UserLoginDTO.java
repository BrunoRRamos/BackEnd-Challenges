package com.desafios_backend.auth.dto;


public record UserLoginDTO(
    String email,
    String password
) {

}
