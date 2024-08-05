package com.max.elopong.backend.services.impl;

import com.max.elopong.backend.controllers.dto.AuthRequestDto;
import com.max.elopong.backend.controllers.dto.RegisterDto;
import com.max.elopong.backend.controllers.dto.UserDto;
import com.max.elopong.backend.models.UserEntity;
import com.max.elopong.backend.repositories.UserRepository;
import com.max.elopong.backend.security.AuthResponse;
import com.max.elopong.backend.security.JwtService;
import com.max.elopong.backend.services.AuthService;
import com.max.elopong.backend.services.mapper.AuthMapper;
import com.max.elopong.backend.types.Role;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final AuthMapper authMapper;

    @Override
    public ResponseEntity<AuthResponse> registerUser(RegisterDto request) {
        UserEntity user = authMapper.registerDtoToUserEntity(request);
        user.setRole(Role.USER);
        user.setEloRating(1000.0);
        userRepository.save(user);

        var token = jwtService.generateToken(user);

        return new ResponseEntity<>(AuthResponse.builder()
                .token(token)
                .build(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AuthResponse> authenticate(AuthRequestDto request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.generateToken(user);

        return new ResponseEntity<>(AuthResponse.builder()
                .token(token)
                .build(), HttpStatus.OK);
    }
}
