package com.max.elopong.backend.controllers;

import com.max.elopong.backend.controllers.dto.UserDto;
import com.max.elopong.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/leaderboard")
@RequiredArgsConstructor
public class LeaderboardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LeaderboardController.class);
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> findAllUsers() {
        LOGGER.info("GET /leaderboard/users");
        try {
            return userService.findAllUsers();
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
