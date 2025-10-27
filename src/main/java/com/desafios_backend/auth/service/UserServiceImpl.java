package com.desafios_backend.auth.service;

import com.desafios_backend.auth.configuration.SecurityConfiguration;
import com.desafios_backend.auth.dto.CreateUserDTO;
import com.desafios_backend.auth.dto.RecoveryJwtTokenDto;
import com.desafios_backend.auth.dto.UserLoginDTO;
import com.desafios_backend.auth.middleware.UserDetailsImpl;
import com.desafios_backend.auth.model.RoleModel;
import com.desafios_backend.auth.model.UserModel;
import com.desafios_backend.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    @Override
    public RecoveryJwtTokenDto authenticateUser(UserLoginDTO userLoginDTO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginDTO.email(), userLoginDTO.password());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

    @Override
    public void createUser(CreateUserDTO createUserDTO) {
        UserModel newUser = UserModel.builder()
                .email(createUserDTO.getEmail())
                .password(securityConfiguration.passwordEncoder().encode(createUserDTO.getPassword()))
                .roles(List.of(RoleModel.builder().roleName(createUserDTO.getRole()).build()))
                .build();

        this.userRepository.save(newUser);
    }
}
