package com.max.elopong.backend.controllers;

import com.max.elopong.backend.controllers.dto.GameCreationDto;
import com.max.elopong.backend.controllers.dto.GameDto;
import com.max.elopong.backend.services.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);
    private final GameService gameService;


    @PostMapping("/add")
    public ResponseEntity<GameDto> addGame(@RequestBody @Valid GameCreationDto gameCreationDto) {
        LOGGER.info("POST /games/add body: {}", gameCreationDto);
        try {
            return gameService.addGame(gameCreationDto);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<GameDto>> findAllGames() {
        LOGGER.info("GET /games/all");
        try {
            return gameService.findAllGames();
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/history/{id}")
    public ResponseEntity<List<GameDto>> getHistory(@PathVariable Long id) {
        LOGGER.info("GET /games/history/{}", id);
        try {
            return gameService.getHistory(id);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
