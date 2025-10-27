package com.desafios_backend.auth.controller;

import com.desafios_backend.auth.dto.CreateUserDTO;
import com.desafios_backend.auth.dto.RecoveryJwtTokenDto;
import com.desafios_backend.auth.dto.UserLoginDTO;
import com.desafios_backend.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody UserLoginDTO userLoginDTO) {
        RecoveryJwtTokenDto token = userService.authenticateUser(userLoginDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }

    @PostMapping()
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDTO createUserDto) {
        userService.createUser(createUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/test")
    public ResponseEntity<String> getAuthenticationTest() {
        return ResponseEntity.status(HttpStatus.OK).body("Authenticated successfully");
    }

    @GetMapping("/test/customer")
    public ResponseEntity<String> getCustomerAuthenticationTest() {
        return new ResponseEntity<>("Customer authenticated successfully", HttpStatus.OK);
    }

    @GetMapping("/test/administrator")
    public ResponseEntity<String> getAdminAuthenticationTest() {
        return new ResponseEntity<>("Administrator authenticated successfully", HttpStatus.OK);
    }
}
