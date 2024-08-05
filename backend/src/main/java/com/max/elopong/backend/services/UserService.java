package com.max.elopong.backend.services;

import com.max.elopong.backend.controllers.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<List<UserDto>> findAllUsers();
}
