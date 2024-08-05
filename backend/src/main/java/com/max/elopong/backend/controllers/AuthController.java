package com.max.elopong.backend.controllers;

import com.max.elopong.backend.controllers.dto.AuthRequestDto;
import com.max.elopong.backend.controllers.dto.RegisterDto;
import com.max.elopong.backend.controllers.dto.UserDto;
import com.max.elopong.backend.security.AuthResponse;
import com.max.elopong.backend.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody @Valid RegisterDto request) {
        LOGGER.info("POST /auth/register body: {}", request);
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        try {
            return authService.registerUser(request);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody @Valid AuthRequestDto request) {
        LOGGER.info("POST /auth/login body: {}", request);
        try {
            return authService.authenticate(request);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
