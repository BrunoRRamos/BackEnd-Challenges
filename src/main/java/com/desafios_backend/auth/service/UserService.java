package com.desafios_backend.auth.service;

import com.desafios_backend.auth.dto.CreateUserDTO;
import com.desafios_backend.auth.dto.RecoveryJwtTokenDto;
import com.desafios_backend.auth.dto.UserLoginDTO;

public interface UserService {
    RecoveryJwtTokenDto authenticateUser(UserLoginDTO userLoginDTO);
    void createUser(CreateUserDTO createUserDTO);

}
