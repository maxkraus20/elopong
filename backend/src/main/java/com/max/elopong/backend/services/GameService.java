package com.max.elopong.backend.services;

import com.max.elopong.backend.controllers.dto.GameCreationDto;
import com.max.elopong.backend.controllers.dto.GameDto;
import com.max.elopong.backend.controllers.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GameService {

    ResponseEntity<GameDto> addGame(GameCreationDto gameCreationDto);

    ResponseEntity<List<GameDto>> findAllGames();

    ResponseEntity<List<GameDto>> getHistory(Long id);
}
