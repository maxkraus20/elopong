package com.max.elopong.backend.services;

import com.max.elopong.backend.controllers.dto.AuthRequestDto;
import com.max.elopong.backend.controllers.dto.RegisterDto;
import com.max.elopong.backend.controllers.dto.UserDto;
import com.max.elopong.backend.security.AuthResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<AuthResponse> registerUser(RegisterDto request);

    ResponseEntity<AuthResponse> authenticate(AuthRequestDto request);
}
