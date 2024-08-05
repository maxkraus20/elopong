package com.max.elopong.backend.services.mapper;

import com.max.elopong.backend.controllers.dto.GameCreationDto;
import com.max.elopong.backend.controllers.dto.GameDto;
import com.max.elopong.backend.models.GameEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GameMapper {

    GameEntity gameCreationDtoToGameEntity(GameCreationDto gameCreationDto);

    GameDto gameEntityToGameDto(GameEntity gameEntity);

    List<GameDto> gameEntityListToGameDtoList(List<GameEntity> gameEntities);
}
